package com.example.data.model

data class PhraseItem(
    val foreignText: String,
    val arabicTranslation: String,
    val arabicPhonetics: String,
    val breakdownWords: List<Pair<String, String>> = emptyList() // Word in foreign lang -> Arabic pronunciation
)
