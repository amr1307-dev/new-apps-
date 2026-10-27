package com.example.util

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class TtsManager(context: Context) {
    private var tts: TextToSpeech? = null
    private var isInitialized = false

    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking: StateFlow<Boolean> = _isSpeaking.asStateFlow()

    private val _currentlySpeakingText = MutableStateFlow<String?>(null)
    val currentlySpeakingText: StateFlow<String?> = _currentlySpeakingText.asStateFlow()

    init {
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                isInitialized = true
                tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(utteranceId: String?) {
                        _isSpeaking.value = true
                    }

                    override fun onDone(utteranceId: String?) {
                        _isSpeaking.value = false
                        _currentlySpeakingText.value = null
                    }

                    @Deprecated("Deprecated in Java")
                    override fun onError(utteranceId: String?) {
                        _isSpeaking.value = false
                        _currentlySpeakingText.value = null
                    }
                })
            } else {
                Log.e("TtsManager", "TextToSpeech initialization failed with status $status")
            }
        }
    }

    fun speak(text: String, languageCode: String, isSlow: Boolean = false) {
        if (!isInitialized || tts == null) {
            Log.w("TtsManager", "TTS not initialized yet")
            return
        }

        val locale = when (languageCode.lowercase()) {
            "de" -> Locale.GERMAN
            "it" -> Locale.ITALIAN
            "ru" -> Locale("ru", "RU")
            "en" -> Locale.ENGLISH
            "fr" -> Locale.FRENCH
            "es" -> Locale("es", "ES")
            else -> Locale.ENGLISH
        }

        val result = tts?.setLanguage(locale)
        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            Log.w("TtsManager", "Language $languageCode is not supported or missing data on this device")
            // Try fallback to US English or standard locale
            tts?.language = Locale.ENGLISH
        }

        // Set speech rate: 0.72f for learning / slow, 1.0f for normal
        tts?.setSpeechRate(if (isSlow) 0.72f else 1.0f)
        tts?.setPitch(1.0f)

        _currentlySpeakingText.value = text
        _isSpeaking.value = true

        val utteranceId = "utterance_${System.currentTimeMillis()}"
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }

    fun stop() {
        tts?.stop()
        _isSpeaking.value = false
        _currentlySpeakingText.value = null
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}
