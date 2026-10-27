package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.model.FieldTrialLog
import com.example.data.model.Language
import com.example.data.model.Product
import com.example.data.model.ProductCategory
import com.example.data.model.QuickPhrase
import com.example.data.model.TrainingProgress
import com.example.data.repository.SalesAssistantRepository
import com.example.util.TtsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class SalesActionTab(val titleAr: String, val emoji: String) {
    EXPLAIN("اشرح المنتج", "📢"),
    QUESTIONS("الرد على سؤال", "💬"),
    PRICE("التعامل مع السعر", "🏷️"),
    PRACTICE("علّمني الجملة", "🎓")
}

enum class MainNavigationTab(val titleAr: String, val emoji: String) {
    CATALOG("كتالوج المنتجات", "🛍️"),
    PHRASEBOOK("جمل سريعة", "🗣️"),
    FIELD_TRIAL("نتائج الميدان", "📊"),
    CURRENCY("حاسبة العملة", "💱")
}

data class UiState(
    val selectedLanguage: Language = Language.DEFAULT, // German
    val selectedCategory: ProductCategory? = null,
    val searchQuery: String = "",
    val selectedProduct: Product? = null,
    val activeSalesActionTab: SalesActionTab = SalesActionTab.EXPLAIN,
    val activeNavTab: MainNavigationTab = MainNavigationTab.CATALOG,
    val isSlowAudio: Boolean = false,
    val isCustomerCardOpen: Boolean = false,
    val showAddProductDialog: Boolean = false,
    val showFeedbackDialog: Boolean = false,
    val lastActionUsed: String = "explain",
    val customerUnderstoodRate: Float = 0.92f
)

class SalesAssistantViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SalesAssistantRepository(AppDatabase.getInstance(application))
    val ttsManager = TtsManager(application)

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    val products: StateFlow<List<Product>> = repository.getAllProducts()
        .combine(_uiState) { prodList, state ->
            prodList.filter { product ->
                val matchesCategory = state.selectedCategory == null || product.category == state.selectedCategory
                val matchesSearch = state.searchQuery.isBlank() ||
                        product.nameAr.contains(state.searchQuery, ignoreCase = true) ||
                        product.nameNativeMap.values.any { it.contains(state.searchQuery, ignoreCase = true) }
                matchesCategory && matchesSearch
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val quickPhrases: List<QuickPhrase> = repository.getQuickPhrases()

    val fieldTrialLogs: StateFlow<List<FieldTrialLog>> = repository.getFieldTrialLogs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val trainingProgressList: StateFlow<List<TrainingProgress>> = repository.getAllTrainingProgress()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun selectLanguage(language: Language) {
        _uiState.value = _uiState.value.copy(selectedLanguage = language)
        // Stop any current audio when language changes
        ttsManager.stop()
    }

    fun selectCategory(category: ProductCategory?) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
    }

    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun selectProduct(product: Product?) {
        _uiState.value = _uiState.value.copy(
            selectedProduct = product,
            activeSalesActionTab = SalesActionTab.EXPLAIN
        )
    }

    fun setSalesActionTab(tab: SalesActionTab) {
        _uiState.value = _uiState.value.copy(activeSalesActionTab = tab)
    }

    fun setNavTab(tab: MainNavigationTab) {
        _uiState.value = _uiState.value.copy(activeNavTab = tab)
        if (tab != MainNavigationTab.CATALOG) {
            _uiState.value = _uiState.value.copy(selectedProduct = null)
        }
    }

    fun toggleSlowAudio() {
        _uiState.value = _uiState.value.copy(isSlowAudio = !_uiState.value.isSlowAudio)
    }

    fun setCustomerCardOpen(isOpen: Boolean) {
        _uiState.value = _uiState.value.copy(isCustomerCardOpen = isOpen)
    }

    fun setShowAddProductDialog(show: Boolean) {
        _uiState.value = _uiState.value.copy(showAddProductDialog = show)
    }

    fun setShowFeedbackDialog(show: Boolean, actionUsed: String = "explain") {
        _uiState.value = _uiState.value.copy(
            showFeedbackDialog = show,
            lastActionUsed = actionUsed
        )
    }

    fun speakText(text: String, isSlowOverride: Boolean? = null) {
        val slow = isSlowOverride ?: _uiState.value.isSlowAudio
        ttsManager.speak(
            text = text,
            languageCode = _uiState.value.selectedLanguage.code,
            isSlow = slow
        )
    }

    fun stopAudio() {
        ttsManager.stop()
    }

    fun logInteraction(
        understood: Boolean,
        speedSeconds: Int = 3,
        notes: String = ""
    ) {
        val prod = _uiState.value.selectedProduct
        val currentLang = _uiState.value.selectedLanguage.code
        val cat = prod?.category?.nameAr ?: "عام"
        val prodName = prod?.nameAr ?: "جملة بيع عامة"
        val action = _uiState.value.lastActionUsed

        viewModelScope.launch {
            repository.logFieldInteraction(
                languageCode = currentLang,
                category = cat,
                productName = prodName,
                actionUsed = action,
                customerUnderstood = understood,
                speedSeconds = speedSeconds,
                note = notes
            )
            _uiState.value = _uiState.value.copy(showFeedbackDialog = false)
        }
    }

    fun recordPractice(phraseId: String, markMastered: Boolean = false) {
        viewModelScope.launch {
            repository.recordTrainingPractice(
                phraseId = phraseId,
                languageCode = _uiState.value.selectedLanguage.code,
                markMastered = markMastered
            )
        }
    }

    fun addCustomProduct(
        nameAr: String,
        categoryId: String,
        priceLocal: Double,
        priceEur: Double,
        facts: String,
        pitchGerman: String,
        pitchEnglish: String,
        pitchPhonetics: String
    ) {
        viewModelScope.launch {
            repository.addCustomProduct(
                nameAr = nameAr,
                categoryId = categoryId,
                priceLocal = priceLocal,
                priceEur = priceEur,
                factsRaw = facts,
                pitchGerman = pitchGerman,
                pitchEnglish = pitchEnglish,
                pitchPhonetics = pitchPhonetics
            )
            _uiState.value = _uiState.value.copy(showAddProductDialog = false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        ttsManager.shutdown()
    }
}
