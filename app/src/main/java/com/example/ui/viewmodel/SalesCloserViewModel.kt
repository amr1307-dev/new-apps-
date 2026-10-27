package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.CloserPitch
import com.example.data.model.CounterTactic
import com.example.data.model.Language
import com.example.data.model.ObjectionCase
import com.example.data.model.ScannedProductInfo
import com.example.data.repository.BenchmarkData
import com.example.util.TtsManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CloserUiState(
    val selectedLanguage: Language = Language.DEFAULT, // German
    val currentProduct: ScannedProductInfo = BenchmarkData.PRODUCTS[0],
    val userAskingPriceEur: String = "400",
    val activeCurrencySymbol: String = "€",
    val isScanningImage: Boolean = false,
    val isMicActive: Boolean = false,
    val micTranscriptForeign: String? = null,
    val micTranscriptArabic: String? = null,
    val detectedObjection: ObjectionCase? = null,
    val selectedCounterTactic: CounterTactic? = null,
    val showCustomerDialog: Boolean = false,
    val activeTab: CloserTab = CloserTab.SCAN_BENCHMARK
)

enum class CloserTab(val titleAr: String, val emoji: String) {
    SCAN_BENCHMARK("مسح ومقارنة الأسعار", "📸"),
    CLOSING_PITCH("إقناع العميل بـ 400€", "💎"),
    LIVE_MIC("ميكروفون الاعتراضات", "🎙️"),
    MANUAL_CATALOG("الكتالوج السريع", "🛍️")
}

class SalesCloserViewModel(application: Application) : AndroidViewModel(application) {

    val ttsManager = TtsManager(application)

    private val _uiState = MutableStateFlow(CloserUiState())
    val uiState: StateFlow<CloserUiState> = _uiState.asStateFlow()

    fun selectLanguage(language: Language) {
        _uiState.value = _uiState.value.copy(
            selectedLanguage = language,
            micTranscriptForeign = null,
            micTranscriptArabic = null,
            detectedObjection = null,
            selectedCounterTactic = null
        )
        ttsManager.stop()
    }

    fun setCloserTab(tab: CloserTab) {
        _uiState.value = _uiState.value.copy(activeTab = tab)
    }

    fun updateAskingPrice(price: String) {
        _uiState.value = _uiState.value.copy(userAskingPriceEur = price)
    }

    fun scanProduct(productId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isScanningImage = true)
            delay(1200) // Realistic AI scanning and online price fetching latency
            val matched = BenchmarkData.PRODUCTS.find { it.id == productId }
                ?: BenchmarkData.PRODUCTS[0]
            _uiState.value = _uiState.value.copy(
                isScanningImage = false,
                currentProduct = matched,
                activeTab = CloserTab.SCAN_BENCHMARK
            )
        }
    }

    fun simulateCameraCapture() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isScanningImage = true)
            delay(1500)
            // Cycle between product 0, 1, 2
            val nextIndex = (BenchmarkData.PRODUCTS.indexOf(_uiState.value.currentProduct) + 1) % BenchmarkData.PRODUCTS.size
            val matched = BenchmarkData.PRODUCTS[nextIndex]
            _uiState.value = _uiState.value.copy(
                isScanningImage = false,
                currentProduct = matched,
                userAskingPriceEur = "${matched.suggestedRetailPriceEur.toInt()}",
                activeTab = CloserTab.SCAN_BENCHMARK
            )
        }
    }

    fun toggleMicrophone(customSpokenText: String? = null) {
        if (_uiState.value.isMicActive) {
            _uiState.value = _uiState.value.copy(isMicActive = false)
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isMicActive = true,
                micTranscriptForeign = null,
                micTranscriptArabic = null
            )
            delay(1400) // Simulating live speech recognition

            val langCode = _uiState.value.selectedLanguage.code
            val matchedObjection = BenchmarkData.OBJECTIONS.find { it.languageCode == langCode }
                ?: BenchmarkData.OBJECTIONS.first()

            val foreignText = customSpokenText ?: matchedObjection.touristSpokenForeign
            val arabicText = matchedObjection.touristSpokenArabic

            _uiState.value = _uiState.value.copy(
                isMicActive = false,
                micTranscriptForeign = foreignText,
                micTranscriptArabic = arabicText,
                detectedObjection = matchedObjection,
                selectedCounterTactic = matchedObjection.tactics.firstOrNull(),
                activeTab = CloserTab.LIVE_MIC
            )
        }
    }

    fun selectCounterTactic(tactic: CounterTactic) {
        _uiState.value = _uiState.value.copy(selectedCounterTactic = tactic)
    }

    fun setShowCustomerDialog(show: Boolean) {
        _uiState.value = _uiState.value.copy(showCustomerDialog = show)
    }

    fun playNaturalVoice(text: String, isSlow: Boolean = false) {
        ttsManager.speak(
            text = text,
            languageCode = _uiState.value.selectedLanguage.code,
            isSlow = isSlow
        )
    }

    fun stopAudio() {
        ttsManager.stop()
    }

    override fun onCleared() {
        super.onCleared()
        ttsManager.shutdown()
    }
}
