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
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Sell
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
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.NavyBorder
import com.example.ui.theme.NavyCard
import com.example.ui.theme.NavyCardElevated

@Composable
fun PriceObjectionTabContent(
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
    var expandedObjectionId by remember {
        mutableStateOf(product.objections.firstOrNull()?.id)
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
                imageVector = Icons.Default.Sell,
                contentDescription = null,
                tint = GoldPrimary,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "التعامل مع السعر واعتراضات العميل باحترافية:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        if (product.objections.isEmpty()) {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = NavyCard,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "استخدم حزمة المنتجات أو قدم هدية مجانية لتبرير القيمة دون حرق السعر.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        product.objections.forEach { objection ->
            val isExpanded = expandedObjectionId == objection.id
            val foreignObjection = objection.objectionForeign[selectedLanguage.code]
                ?: objection.objectionForeign["en"] ?: ""
            val rebuttalPhrase = objection.response[selectedLanguage.code]
                ?: objection.response["en"]

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = if (isExpanded) NavyCardElevated else NavyCard,
                border = BorderStroke(
                    1.dp,
                    if (isExpanded) GoldPrimary.copy(alpha = 0.5f) else NavyBorder
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        expandedObjectionId = if (isExpanded) null else objection.id
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
                                text = objection.objectionAr,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if (foreignObjection.isNotBlank()) {
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "“$foreignObjection”",
                                    fontSize = 12.sp,
                                    color = GoldPrimary
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
                        Column(
                            modifier = Modifier.padding(top = 14.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Sales Strategy Advice
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = GoldPrimary.copy(alpha = 0.1f),
                                border = BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.3f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.Top,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Lightbulb,
                                        contentDescription = null,
                                        tint = GoldPrimary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = objection.salesTipAr,
                                        fontSize = 12.sp,
                                        lineHeight = 17.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }

                            if (rebuttalPhrase != null) {
                                PhraseSpeechCard(
                                    phrase = rebuttalPhrase,
                                    language = selectedLanguage,
                                    isSpeaking = isSpeaking,
                                    onSpeakNormal = { onSpeakNormal(rebuttalPhrase.foreignText) },
                                    onSpeakSlow = { onSpeakSlow(rebuttalPhrase.foreignText) },
                                    onStopAudio = onStopAudio,
                                    onShowCustomerCard = onShowCustomerCard,
                                    onLogFeedback = onLogFeedback,
                                    titleLabel = "طريقة الرد وتبرير القيمة"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
