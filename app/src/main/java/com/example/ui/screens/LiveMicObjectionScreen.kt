package com.example.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CounterTactic
import com.example.data.model.Language
import com.example.data.model.ObjectionCase
import com.example.ui.theme.AlertRed
import com.example.ui.theme.AlertRedBg
import com.example.ui.theme.BlueAccent
import com.example.ui.theme.BlueLightBg
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldLightBg
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightSurface
import com.example.ui.theme.LightSurfaceElevated
import com.example.ui.theme.NeonLime
import com.example.ui.theme.StarGold
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import kotlin.math.sin

@Composable
fun LiveMicObjectionScreen(
    selectedLanguage: Language,
    isMicActive: Boolean,
    transcriptForeign: String?,
    transcriptArabic: String?,
    detectedObjection: ObjectionCase?,
    selectedTactic: CounterTactic?,
    isSpeaking: Boolean,
    onToggleMic: () -> Unit,
    onSimulateObjection: (String) -> Unit,
    onSelectTactic: (CounterTactic) -> Unit,
    onPlayNaturalVoice: (String) -> Unit,
    onPlaySlowVoice: (String) -> Unit,
    onStopAudio: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Top Screen Header: "AI Voice Coach • Online" (Matching Image 3 Screen 3)
        item {
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = LightSurface,
                border = BorderStroke(1.dp, LightBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(EmeraldLightBg),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "🤖", fontSize = 18.sp)
                        }
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "AI Voice Coach",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextDark
                                )
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(EmeraldPrimary)
                                )
                                Text(
                                    text = "online",
                                    fontSize = 10.sp,
                                    color = EmeraldPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = "مترجم الاعتراضات المباشر (${selectedLanguage.nameNative} ${selectedLanguage.flag})",
                                fontSize = 11.sp,
                                color = TextMuted
                            )
                        }
                    }

                    Text(text = selectedLanguage.flag, fontSize = 24.sp)
                }
            }
        }

        // Conversational Bubble 1: AI Coach Welcome
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(EmeraldPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🤖", fontSize = 16.sp)
                }

                Surface(
                    shape = RoundedCornerShape(topStart = 4.dp, topEnd = 16.dp, bottomEnd = 16.dp, bottomStart = 16.dp),
                    color = LightSurface,
                    border = BorderStroke(1.dp, LightBorder),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "AI Coach 🎙️",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldPrimary
                            )
                            IconButton(
                                onClick = {
                                    onPlayNaturalVoice("Let's handle your customer objections. Speak into the microphone or pick a common tourist phrase.")
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.VolumeUp,
                                    contentDescription = "استمع للترحيب",
                                    tint = EmeraldPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "أهلاً بك! شغّل الميكروفون عند اعتراض الزبون على سعر الـ 400€ أو قل لي ماذا قال لأجهز لك أقوى رد بالصوت الطبيعي.",
                            fontSize = 12.sp,
                            color = TextDark,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        // Quick Common Objection Simulation Chips
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "أو اختر اعتراضاً شائعاً للتجربة الفورية:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextMuted
                )
                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf(
                        "السعر 400€ غالي جداً" to "Das ist mir zu teuer, 400 Euro ist weit über meinem Budget.",
                        "في المحل الآخر أرخص" to "Im anderen Geschäft habe ich es für die Hälfte gesehen.",
                        "سأفكر وأعود لاحقاً" to "Ich muss noch überlegen und komme vielleicht später wieder.",
                        "هل يمكنك عمل خصم؟" to "Können Sie mir einen besseren Preis machen?"
                    ).forEach { (label, foreignPhrase) ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = LightSurface,
                            border = BorderStroke(1.dp, LightBorder),
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { onSimulateObjection(foreignPhrase) }
                        ) {
                            Text(
                                text = label,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextDark,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                            )
                        }
                    }
                }
            }
        }

        // Conversational Bubble 2: Transcribed Tourist Speech (User side bubble with gradient)
        if (transcriptForeign != null) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Surface(
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 4.dp, bottomEnd = 16.dp, bottomStart = 16.dp),
                        color = Color.Transparent,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(EmeraldPrimary, EmeraldDark)
                                    )
                                )
                                .padding(14.dp)
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "السائح قال (${selectedLanguage.nameNative}):",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White.copy(alpha = 0.9f)
                                    )
                                    Text(text = selectedLanguage.flag, fontSize = 16.sp)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "“$transcriptForeign”",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color.Black.copy(alpha = 0.2f),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "الترجمة للبائع: $transcriptArabic",
                                        fontSize = 11.sp,
                                        color = Color.White.copy(alpha = 0.95f),
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(BlueLightBg),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "👤", fontSize = 16.sp)
                    }
                }
            }
        }

        // Tactical Counter-Options
        if (detectedObjection != null) {
            item {
                Text(
                    text = "خطة الرد لإغلاق صفقة الـ 400€:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
            }

            items(detectedObjection.tactics.size) { index ->
                val tactic = detectedObjection.tactics[index]
                val isSelected = selectedTactic?.id == tactic.id

                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = if (isSelected) EmeraldLightBg else LightSurface,
                    border = BorderStroke(1.5.dp, if (isSelected) EmeraldPrimary else LightBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .clickable { onSelectTactic(tactic) }
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = tactic.titleAr,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) EmeraldDark else TextDark
                            )
                            if (isSelected) {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = EmeraldPrimary
                                ) {
                                    Text(
                                        text = "المعتمد ✓",
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = tactic.strategyExplanationAr,
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    }
                }
            }
        }

        // 4. THE MASTERPIECE: Dark Emerald Audio Waveform Container (Matches Image 3 Screen 3)
        item {
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = EmeraldDeep,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("dark_emerald_waveform_container")
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Status text with glowing dot
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(if (isSpeaking || isMicActive) NeonLime else EmeraldPrimary)
                        )
                        Text(
                            text = when {
                                isSpeaking -> "AI is speaking to customer... • الرد الصوتي الطبيعي يعمل"
                                isMicActive -> "Listening to customer... • أستمع لاعتراض السائح"
                                else -> "AI Voice Ready • جاهز للاستماع أو الرد الصوتي"
                            },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Audio Waveform Visualizer Canvas
                    AnimatedAudioWaveform(
                        isActive = isSpeaking || isMicActive,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Action Pill Buttons Row: Lime "Stop response / Start Speaking" + Slow replay
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (isSpeaking) {
                            Surface(
                                shape = RoundedCornerShape(24.dp),
                                color = NeonLime,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(24.dp))
                                    .clickable { onStopAudio() }
                                    .testTag("stop_response_pill_button")
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Stop",
                                        tint = EmeraldDeep,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "Stop response",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = EmeraldDeep
                                    )
                                }
                            }
                        } else {
                            Surface(
                                shape = RoundedCornerShape(24.dp),
                                color = if (isMicActive) AlertRed else NeonLime,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(24.dp))
                                    .clickable {
                                        if (selectedTactic != null) {
                                            onPlayNaturalVoice(selectedTactic.foreignSpeech)
                                        } else {
                                            onToggleMic()
                                        }
                                    }
                                    .testTag("toggle_voice_action_button")
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 22.dp, vertical = 11.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = if (selectedTactic != null) Icons.Default.VolumeUp else Icons.Default.Mic,
                                        contentDescription = null,
                                        tint = if (isMicActive) Color.White else EmeraldDeep,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = if (selectedTactic != null) "تشغيل الرد الصوتي الطبيعي للعميل 🔊"
                                        else if (isMicActive) "إيقاف الاستماع"
                                        else "اضغط للتحدث أو الاستماع",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = if (isMicActive) Color.White else EmeraldDeep
                                    )
                                }
                            }
                        }
                    }

                    if (selectedTactic != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "🐢 قراءة بطيئة للمبتدئ",
                                fontSize = 11.sp,
                                color = NeonLime,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { onPlaySlowVoice(selectedTactic.foreignSpeech) }
                                    .padding(6.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimatedAudioWaveform(
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition(label = "waveform_anim")
    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = 6.28318f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "phase"
    )

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val centerY = height / 2f
        val points = 80
        val stepX = width / points

        // Draw multiple layered waves with Emerald and Neon Lime glow
        val amplitudes = if (isActive) listOf(24f, 16f, 8f) else listOf(8f, 5f, 2f)
        val colors = listOf(
            NeonLime.copy(alpha = 0.85f),
            EmeraldPrimary.copy(alpha = 0.70f),
            Color.White.copy(alpha = 0.40f)
        )

        for (layer in 0..2) {
            val amp = amplitudes[layer]
            val path = Path()
            path.moveTo(0f, centerY)

            for (i in 0..points) {
                val x = i * stepX
                // Envelope window function so edges taper off
                val window = sin((i.toFloat() / points) * Math.PI.toFloat())
                val y = centerY + sin((i * 0.25f) + phase + (layer * 1.5f)) * amp * window
                path.lineTo(x, y)
            }

            drawPath(
                path = path,
                color = colors[layer],
                style = Stroke(
                    width = if (layer == 0) 3.5.dp.toPx() else 2.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    }
}

