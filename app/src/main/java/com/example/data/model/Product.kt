package com.example.data.model

data class ProductQuestion(
    val id: String,
    val questionAr: String,
    val questionForeign: Map<String, String>, // langCode -> foreign question text
    val answers: Map<String, PhraseItem>       // langCode -> PhraseItem (foreign, ar, phonetics)
)

data class PriceObjection(
    val id: String,
    val objectionAr: String,
    val objectionForeign: Map<String, String>,
    val response: Map<String, PhraseItem>,
    val salesTipAr: String
)

data class Product(
    val id: String,
    val nameAr: String,
    val nameNativeMap: Map<String, String>,
    val category: ProductCategory,
    val iconEmoji: String,
    val priceLocal: Double,
    val currencyLocal: String = "ج.م",
    val priceEur: Double,
    val priceUsd: Double,
    val verifiedFacts: List<String>,
    val pitch: Map<String, PhraseItem>,
    val questions: List<ProductQuestion>,
    val objections: List<PriceObjection>,
    val isCustom: Boolean = false
)
