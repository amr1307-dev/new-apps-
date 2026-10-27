package com.example.data.model

enum class AcademicLevel(
    val code: String,
    val nameAr: String,
    val subtitleAr: String,
    val badgeColorHex: Long
) {
    A1("A1", "المستوى التأسيسي", "الحروف، النطق، التقديم، وبناء الجملة البسيطة", 0xFF0284C7),
    A2("A2", "المستوى المبتدئ المتقدم", "المواقف اليومية، السؤال والتوجيه، وأزمنة الماضي والمضارع", 0xFF059669),
    B1("B1", "المستوى المتوسط", "المحادثات المتصلة، السفر، إبداء الرأي، ومواقف العمل", 0xFFD97706),
    B2("B2", "مستوى الطلاقة والاحتراف", "التفاوض، النقاشات المعقدة، والطلاقة دون تردد", 0xFF8B5CF6)
}

data class VocabWord(
    val id: String,
    val foreignWord: String,
    val arabicMeaning: String,
    val arabicPhonetics: String,
    val exampleSentence: String,
    val exampleTranslation: String,
    val partOfSpeech: String = "اسم"
)

data class GrammarRule(
    val titleAr: String,
    val explanationAr: String,
    val formulaAr: String,
    val practicalExampleForeign: String,
    val practicalExampleArabic: String
)

data class DialogueLine(
    val speakerName: String,
    val isUser: Boolean,
    val foreignText: String,
    val arabicTranslation: String,
    val arabicPhonetics: String
)

enum class QuestionType {
    TRANSLATION,
    LISTENING,
    SENTENCE_ORDER
}

data class QuizQuestion(
    val id: String,
    val promptAr: String,
    val promptForeign: String = "",
    val type: QuestionType = QuestionType.TRANSLATION,
    val options: List<String>,
    val correctIndex: Int,
    val explanationAr: String
)

data class Lesson(
    val id: String,
    val level: AcademicLevel,
    val trackCode: String,
    val orderNumber: Int,
    val titleAr: String,
    val titleForeign: String,
    val estimatedMinutes: Int = 12,
    val lectureConceptAr: String,
    val grammarRule: GrammarRule,
    val vocabulary: List<VocabWord>,
    val conversationDialogue: List<DialogueLine>,
    val quizQuestions: List<QuizQuestion>
)

data class InteractiveLabScenario(
    val id: String,
    val titleAr: String,
    val descriptionAr: String,
    val emoji: String,
    val contextEnvironment: String,
    val dialogueRounds: List<DialogueLine>,
    val keyUsefulExpressions: List<VocabWord>
)
