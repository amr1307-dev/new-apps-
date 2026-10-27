package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Language
import com.example.data.model.Lesson
import com.example.data.model.VocabWord
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.NavyBorder
import com.example.ui.theme.NavyCard
import com.example.ui.theme.NavyCardElevated
import com.example.ui.theme.NavyDark
import com.example.ui.theme.PurpleAccent
import com.example.ui.viewmodel.LessonTab

@Composable
fun LessonPlayerScreen(
    lesson: Lesson,
    activeTab: LessonTab,
    selectedTrack: Language,
    isSpeaking: Boolean,
    quizAnswers: Map<String, Int>,
    isQuizSubmitted: Boolean,
    quizScorePercent: Int,
    onBack: () -> Unit,
    onTabSelected: (LessonTab) -> Unit,
    onSpeakNormal: (String) -> Unit,
    onSpeakSlow: (String) -> Unit,
    onSaveWord: (VocabWord) -> Unit,
    onSelectQuizAnswer: (String, Int) -> Unit,
    onSubmitQuiz: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Lesson Header Card
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = NavyCard,
            border = BorderStroke(1.dp, NavyBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(NavyCardElevated)
                            .testTag("lesson_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "رجوع للمنهج",
                            tint = Color.White
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = GoldPrimary.copy(alpha = 0.2f),
                        border = BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.5f))
                    ) {
                        Text(
                            text = "${lesson.level.nameAr} (${lesson.level.code})",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = lesson.titleAr,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = lesson.titleForeign,
                    fontSize = 13.sp,
                    color = CyanAccent
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⏱️ ${lesson.estimatedMinutes} دقيقة مذاكرة",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "•",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${selectedTrack.flag} ${selectedTrack.nameAr}",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // 4 Lecture Tabs Selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            LessonTab.entries.forEach { tab ->
                val isSelected = activeTab == tab
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) GoldPrimary else NavyCard,
                    border = BorderStroke(1.dp, if (isSelected) GoldPrimary else NavyBorder),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("lesson_tab_${tab.name.lowercase()}")
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onTabSelected(tab) }
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = tab.emoji, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = tab.titleAr,
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.Black else Color.White
                        )
                    }
                }
            }
        }

        // Tab Content
        when (activeTab) {
            LessonTab.CONCEPTS -> {
                LessonConceptsTab(
                    lesson = lesson,
                    onSpeakNormal = onSpeakNormal,
                    onSpeakSlow = onSpeakSlow
                )
            }
            LessonTab.VOCABULARY -> {
                LessonVocabularyTab(
                    lesson = lesson,
                    onSpeakNormal = onSpeakNormal,
                    onSpeakSlow = onSpeakSlow,
                    onSaveWord = onSaveWord
                )
            }
            LessonTab.DIALOGUE -> {
                LessonDialogueTab(
                    lesson = lesson,
                    onSpeak = onSpeakNormal
                )
            }
            LessonTab.QUIZ -> {
                LessonQuizTab(
                    lesson = lesson,
                    quizAnswers = quizAnswers,
                    isQuizSubmitted = isQuizSubmitted,
                    quizScorePercent = quizScorePercent,
                    onSelectAnswer = onSelectQuizAnswer,
                    onSubmitQuiz = onSubmitQuiz
                )
            }
        }
    }
}

@Composable
private fun LessonConceptsTab(
    lesson: Lesson,
    onSpeakNormal: (String) -> Unit,
    onSpeakSlow: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        // Concept Overview
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = NavyCard,
            border = BorderStroke(1.dp, NavyBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = null,
                        tint = GoldPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "الهدف الأكاديمي للمحاضرة:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = lesson.lectureConceptAr,
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Grammar Formula & Deep Explanation
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = NavyCard,
            border = BorderStroke(1.5.dp, CyanAccent.copy(alpha = 0.5f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = CyanAccent,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = lesson.grammarRule.titleAr,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = CyanAccent
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = lesson.grammarRule.explanationAr,
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Formula Box
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = CyanAccent.copy(alpha = 0.15f),
                    border = BorderStroke(1.dp, CyanAccent.copy(alpha = 0.3f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "القاعدة التركيبية:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = CyanAccent
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = lesson.grammarRule.formulaAr,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Practical Example with Audio
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = NavyCardElevated,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "مثال تطبيقي عملي:",
                                fontSize = 11.sp,
                                color = GoldPrimary,
                                fontWeight = FontWeight.Bold
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                IconButton(
                                    onClick = { onSpeakSlow(lesson.grammarRule.practicalExampleForeign) },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Hearing,
                                        contentDescription = "بطيء",
                                        tint = PurpleAccent,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                IconButton(
                                    onClick = { onSpeakNormal(lesson.grammarRule.practicalExampleForeign) },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.VolumeUp,
                                        contentDescription = "نطق عادي",
                                        tint = CyanAccent,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                        Text(
                            text = lesson.grammarRule.practicalExampleForeign,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = lesson.grammarRule.practicalExampleArabic,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LessonVocabularyTab(
    lesson: Lesson,
    onSpeakNormal: (String) -> Unit,
    onSpeakSlow: (String) -> Unit,
    onSaveWord: (VocabWord) -> Unit
) {
    var savedWordsSet by remember { mutableStateOf(setOf<String>()) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "المفردات الأساسية للمحاضرة (${lesson.vocabulary.size} كلمات):",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        lesson.vocabulary.forEach { word ->
            val isSaved = savedWordsSet.contains(word.id)
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = NavyCard,
                border = BorderStroke(1.dp, NavyBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = word.foreignWord,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "🗣️ النطق: ${word.arabicPhonetics}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = GoldPrimary
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            IconButton(
                                onClick = { onSpeakSlow(word.foreignWord) },
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(NavyCardElevated)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Hearing,
                                    contentDescription = "نطق بطيء",
                                    tint = PurpleAccent,
                                    modifier = Modifier.size(16.dp)
                                )
                            }

                            IconButton(
                                onClick = { onSpeakNormal(word.foreignWord) },
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(NavyCardElevated)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.VolumeUp,
                                    contentDescription = "نطق عادي",
                                    tint = CyanAccent,
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            IconButton(
                                onClick = {
                                    savedWordsSet = savedWordsSet + word.id
                                    onSaveWord(word)
                                },
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(if (isSaved) EmeraldAccent.copy(alpha = 0.2f) else NavyCardElevated)
                            ) {
                                Icon(
                                    imageVector = if (isSaved) Icons.Default.Check else Icons.Default.BookmarkAdd,
                                    contentDescription = "حفظ الكلمة",
                                    tint = if (isSaved) EmeraldAccent else GoldPrimary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "المعنى: ${word.arabicMeaning}",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Example Sentence
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = NavyCardElevated,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "“${word.exampleSentence}”",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                            Text(
                                text = word.exampleTranslation,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LessonDialogueTab(
    lesson: Lesson,
    onSpeak: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = "المحادثة الحية في الموقف العملي:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "استمع لكل سطر، وكرره بصوتك لتكتسب الإيقاع الطبيعي للحديث:",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        lesson.conversationDialogue.forEach { turn ->
            val isUser = turn.isUser
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = if (isUser) NavyCardElevated else NavyCard,
                border = BorderStroke(
                    1.dp,
                    if (isUser) GoldPrimary.copy(alpha = 0.5f) else CyanAccent.copy(alpha = 0.3f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(text = if (isUser) "👤" else "🎓", fontSize = 16.sp)
                            Text(
                                text = turn.speakerName,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isUser) GoldPrimary else CyanAccent
                            )
                        }

                        IconButton(
                            onClick = { onSpeak(turn.foreignText) },
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.08f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = "استماع",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = turn.foreignText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "النطق: ${turn.arabicPhonetics}",
                        fontSize = 12.sp,
                        color = GoldPrimary,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = turn.arabicTranslation,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun LessonQuizTab(
    lesson: Lesson,
    quizAnswers: Map<String, Int>,
    isQuizSubmitted: Boolean,
    quizScorePercent: Int,
    onSelectAnswer: (String, Int) -> Unit,
    onSubmitQuiz: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        // Quiz Header / Result
        if (isQuizSubmitted) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = if (quizScorePercent >= 70) EmeraldAccent.copy(alpha = 0.2f) else GoldPrimary.copy(alpha = 0.2f),
                border = BorderStroke(
                    1.5.dp,
                    if (quizScorePercent >= 70) EmeraldAccent else GoldPrimary
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (quizScorePercent >= 70) "🎉 تهانينا! اجتزت اختبار المحاضرة بنجاح" else "⚡ نتيجة الاختبار - حاول مرة أخرى لرفع درجتك",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "$quizScorePercent%",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = if (quizScorePercent >= 70) EmeraldAccent else GoldPrimary
                    )
                    Text(
                        text = "تمت إضافة نقاط الخبرة XP وحفظ التقدم في سجلك الجامعي",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        lesson.quizQuestions.forEachIndexed { index, question ->
            val selectedOption = quizAnswers[question.id]
            val isAnswered = selectedOption != null

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = NavyCard,
                border = BorderStroke(1.dp, NavyBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "سؤال ${index + 1}: ${question.promptAr}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    if (question.promptForeign.isNotBlank()) {
                        Text(
                            text = question.promptForeign,
                            fontSize = 12.sp,
                            color = CyanAccent
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    question.options.forEachIndexed { optIndex, optionText ->
                        val isSelected = selectedOption == optIndex
                        val isCorrect = question.correctIndex == optIndex

                        val bgColor = when {
                            !isQuizSubmitted && isSelected -> GoldPrimary.copy(alpha = 0.2f)
                            isQuizSubmitted && isCorrect -> EmeraldAccent.copy(alpha = 0.25f)
                            isQuizSubmitted && isSelected && !isCorrect -> MaterialTheme.colorScheme.error.copy(alpha = 0.25f)
                            else -> NavyCardElevated
                        }

                        val borderColor = when {
                            !isQuizSubmitted && isSelected -> GoldPrimary
                            isQuizSubmitted && isCorrect -> EmeraldAccent
                            isQuizSubmitted && isSelected && !isCorrect -> MaterialTheme.colorScheme.error
                            else -> NavyBorder
                        }

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = bgColor,
                            border = BorderStroke(1.dp, borderColor),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable(enabled = !isQuizSubmitted) {
                                    onSelectAnswer(question.id, optIndex)
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = optionText,
                                    fontSize = 13.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = Color.White
                                )
                                if (isQuizSubmitted) {
                                    if (isCorrect) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = "إجابة صحيحة",
                                            tint = EmeraldAccent,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    } else if (isSelected) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "إجابة خاطئة",
                                            tint = MaterialTheme.colorScheme.error,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    if (isQuizSubmitted) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = NavyCardElevated,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "💡 الشرح الأكاديمي: ${question.explanationAr}",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }

        if (!isQuizSubmitted) {
            Button(
                onClick = onSubmitQuiz,
                enabled = quizAnswers.size == lesson.quizQuestions.size,
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoldPrimary,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("submit_quiz_button")
            ) {
                Text(
                    text = "تسليم الإجابات والتقييم الأكاديمي",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
