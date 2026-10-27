package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Language
import com.example.data.model.Product
import com.example.ui.components.PhraseSpeechCard
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.NavyBorder
import com.example.ui.theme.NavyCard
import com.example.ui.theme.NavyCardElevated

@Composable
fun QuestionsTabContent(
    product: Product,
    selectedLanguage: Language,
    isSpeaking: Boolean,
    onSpeakNormal: (String) -> Unit,
    onSpeakSlow: (String) -> Unit,
    onStopAudio: () -> Unit,
    onShowCustomerCard: () -> Unit,
    onLogFeedback: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var expandedQuestionId by remember {
        mutableStateOf(product.questions.firstOrNull()?.id)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.HelpOutline,
                contentDescription = null,
                tint = EmeraldAccent,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "الأسئلة الشائعة من السياح حول هذا المنتج:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        if (product.questions.isEmpty()) {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = NavyCard,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "لا توجد أسئلة مسجلة مسبقاً لهذا المنتج المخصص.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        product.questions.forEach { question ->
            val isExpanded = expandedQuestionId == question.id
            val foreignQuestion = question.questionForeign[selectedLanguage.code]
                ?: question.questionForeign["en"] ?: ""
            val answerPhrase = question.answers[selectedLanguage.code]
                ?: question.answers["en"]

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = if (isExpanded) NavyCardElevated else NavyCard,
                border = BorderStroke(
                    1.dp,
                    if (isExpanded) EmeraldAccent.copy(alpha = 0.5f) else NavyBorder
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        expandedQuestionId = if (isExpanded) null else question.id
                    }
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = question.questionAr,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if (foreignQuestion.isNotBlank()) {
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = foreignQuestion,
                                    fontSize = 12.sp,
                                    color = EmeraldAccent
                                )
                            }
                        }
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = if (isExpanded) "Collapse" else "Expand",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    AnimatedVisibility(visible = isExpanded) {
                        Column(modifier = Modifier.padding(top = 14.dp)) {
                            if (answerPhrase != null) {
                                PhraseSpeechCard(
                                    phrase = answerPhrase,
                                    language = selectedLanguage,
                                    isSpeaking = isSpeaking,
                                    onSpeakNormal = { onSpeakNormal(answerPhrase.foreignText) },
                                    onSpeakSlow = { onSpeakSlow(answerPhrase.foreignText) },
                                    onStopAudio = onStopAudio,
                                    onShowCustomerCard = onShowCustomerCard,
                                    onLogFeedback = onLogFeedback,
                                    titleLabel = "الإجابة النموذجية المعتمدة"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
