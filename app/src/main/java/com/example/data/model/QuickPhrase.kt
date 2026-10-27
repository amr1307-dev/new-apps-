package com.example.data.model

enum class PhraseCategory(val id: String, val titleAr: String, val emoji: String) {
    WELCOME("welcome", "الترحيب والاستقبال", "👋"),
    TRY_FIT("try_fit", "المقاس والتجربة والمرآة", "🪞"),
    PAYMENT("payment", "الدفع والعملات والبطاقات", "💳"),
    GUARANTEE("guarantee", "الضمان وتغليف السفر", "🛡️"),
    CLOSING("closing", "إتمام الصفقة والتوديع", "🤝")
}

data class QuickPhrase(
    val id: String,
    val category: PhraseCategory,
    val titleAr: String,
    val translations: Map<String, PhraseItem> // langCode -> PhraseItem
)
