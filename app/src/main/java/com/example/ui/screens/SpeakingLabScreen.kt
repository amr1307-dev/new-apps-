package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.InteractiveLabScenario
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.NavyBorder
import com.example.ui.theme.NavyCard
import com.example.ui.theme.NavyCardElevated
import com.example.ui.theme.PurpleAccent

@Composable
fun SpeakingLabScreen(
    scenarios: List<InteractiveLabScenario>,
    activeScenario: InteractiveLabScenario?,
    currentStep: Int,
    onOpenScenario: (InteractiveLabScenario) -> Unit,
    onCloseScenario: () -> Unit,
    onNextStep: () -> Unit,
    onPrevStep: () -> Unit,
    onSpeakNormal: (String) -> Unit,
    onSpeakSlow: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (activeScenario != null) {
        ActiveScenarioPlayer(
            scenario = activeScenario,
            currentStep = currentStep,
            onClose = onCloseScenario,
            onNextStep = onNextStep,
            onPrevStep = onPrevStep,
            onSpeakNormal = onSpeakNormal,
            onSpeakSlow = onSpeakSlow,
            modifier = modifier
        )
    } else {
        ScenariosList(
            scenarios = scenarios,
            onSelect = onOpenScenario,
            modifier = modifier
        )
    }
}

@Composable
private fun ScenariosList(
    scenarios: List<InteractiveLabScenario>,
    onSelect: (InteractiveLabScenario) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = PurpleAccent.copy(alpha = 0.15f),
                border = BorderStroke(1.dp, PurpleAccent.copy(alpha = 0.4f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(PurpleAccent.copy(alpha = 0.25f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🎙️", fontSize = 24.sp)
                    }
                    Column {
                        Text(
                            text = "مختبر المحادثة الحية (24/7)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "تغنيك عن البحث عن شريك محادثة! تدرب على مواقف حقيقية خطوة بخطوة في أي وقت طوال اليوم.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = "اختر سيناريو المحادثة العملي:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        items(scenarios) { scenario ->
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = NavyCard,
                border = BorderStroke(1.dp, NavyBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("scenario_card_${scenario.id}")
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onSelect(scenario) }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(NavyCardElevated),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = scenario.emoji, fontSize = 28.sp)
                        }

                        Column {
                            Text(
                                text = scenario.titleAr,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = scenario.descriptionAr,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 17.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "📍 البيئة: ${scenario.contextEnvironment}",
                                fontSize = 10.sp,
                                color = CyanAccent
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "ابدأ",
                        tint = GoldPrimary
                    )
                }
            }
        }
    }
}

@Composable
private fun ActiveScenarioPlayer(
    scenario: InteractiveLabScenario,
    currentStep: Int,
    onClose: () -> Unit,
    onNextStep: () -> Unit,
    onPrevStep: () -> Unit,
    onSpeakNormal: (String) -> Unit,
    onSpeakSlow: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val totalRounds = scenario.dialogueRounds.size
    val activeLine = scenario.dialogueRounds.getOrNull(currentStep) ?: scenario.dialogueRounds.first()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = NavyCard,
            border = BorderStroke(1.dp, NavyBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "رجوع للمختبر",
                        tint = Color.White
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = scenario.titleAr,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "الجولة ${currentStep + 1} من أصل $totalRounds",
                        fontSize = 11.sp,
                        color = GoldPrimary
                    )
                }

                Text(text = scenario.emoji, fontSize = 24.sp)
            }
        }

        // Active Speaker Dialogue Card
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = if (activeLine.isUser) NavyCardElevated else NavyCard,
            border = BorderStroke(
                1.5.dp,
                if (activeLine.isUser) GoldPrimary else CyanAccent
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = if (activeLine.isUser) "👤" else "🎙️", fontSize = 20.sp)
                        Text(
                            text = if (activeLine.isUser) "دورك في التحدث: ${activeLine.speakerName}" else "المتحدث: ${activeLine.speakerName}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (activeLine.isUser) GoldPrimary else CyanAccent
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        IconButton(
                            onClick = { onSpeakSlow(activeLine.foreignText) },
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(NavyCard)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Hearing,
                                contentDescription = "بطيء",
                                tint = PurpleAccent,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        IconButton(
                            onClick = { onSpeakNormal(activeLine.foreignText) },
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(NavyCard)
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

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = activeLine.foreignText,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    lineHeight = 25.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = GoldPrimary.copy(alpha = 0.12f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "النطق الصوتي: ${activeLine.arabicPhonetics}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = GoldPrimary,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "الترجمة: ${activeLine.arabicTranslation}",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Navigation between dialogue rounds
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = onPrevStep,
                enabled = currentStep > 0,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, NavyBorder),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text(text = "الجولة السابقة", color = Color.White)
            }

            Button(
                onClick = {
                    if (currentStep < totalRounds - 1) onNextStep() else onClose()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoldPrimary,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1.3f)
                    .height(48.dp)
            ) {
                Text(
                    text = if (currentStep < totalRounds - 1) "الجولة التالية ➡️" else "إنهاء المحاكاة 🎉",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
