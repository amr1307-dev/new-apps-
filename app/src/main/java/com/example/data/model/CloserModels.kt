package com.example.data.model

data class CloserPitch(
    val foreignText: String,
    val arabicTranslation: String,
    val arabicPhonetics: String,
    val valueProofStatement: String
)

data class ScannedProductInfo(
    val id: String,
    val nameAr: String,
    val categoryName: String,
    val officialBrandMatch: String,
    val officialSitePriceEur: Double,
    val onlineStorePriceEur: Double,
    val suggestedRetailPriceEur: Double,
    val artisanFeaturesAr: List<String>,
    val luxuryBrandComparisonAr: String,
    val pitchesByLang: Map<String, CloserPitch>
)

data class CounterTactic(
    val id: String,
    val titleAr: String,
    val strategyExplanationAr: String,
    val foreignSpeech: String,
    val arabicPhonetics: String,
    val arabicTranslation: String
)

data class ObjectionCase(
    val id: String,
    val touristSpokenForeign: String,
    val touristSpokenArabic: String,
    val languageCode: String,
    val detectedObjectionType: String, // "price_high", "thinking", "competitor_cheaper", "discount"
    val tactics: List<CounterTactic>
)
