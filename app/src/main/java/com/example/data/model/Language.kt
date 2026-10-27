package com.example.data.model

data class Language(
    val code: String,
    val nameAr: String,
    val nameNative: String,
    val flag: String,
    val localeTag: String
) {
    companion object {
        val ALL = listOf(
            Language("de", "الألمانية", "Deutsch", "🇩🇪", "de-DE"),
            Language("it", "الإيطالية", "Italiano", "🇮🇹", "it-IT"),
            Language("ru", "الروسية", "Русский", "🇷🇺", "ru-RU"),
            Language("en", "الإنجليزية", "English", "🇬🇧", "en-US"),
            Language("fr", "الفرنسية", "Français", "🇫🇷", "fr-FR"),
            Language("es", "الإسبانية", "Español", "🇪🇸", "es-ES")
        )

        val DEFAULT = ALL[0] // German, as specifically highlighted in user request

        fun fromCode(code: String): Language {
            return ALL.find { it.code == code } ?: DEFAULT
        }
    }
}
