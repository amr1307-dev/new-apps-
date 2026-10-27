package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.PriceCheck
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CloserPitch
import com.example.data.model.Language
import com.example.data.model.ScannedProductInfo
import com.example.ui.theme.ArtisanCognac
import com.example.ui.theme.ArtisanGold
import com.example.ui.theme.ArtisanGoldLight
import com.example.ui.theme.DealSuccess
import com.example.ui.theme.DealSuccessBg
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightBorderStrong
import com.example.ui.theme.LightSurface
import com.example.ui.theme.LightSurfaceElevated
import com.example.ui.theme.RetailAzure
import com.example.ui.theme.RetailAzureBg
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted

@Composable
fun ClosingPitchScreen(
    product: ScannedProductInfo,
    selectedLanguage: Language,
    askingPrice: String,
    isSpeaking: Boolean,
    onPriceChanged: (String) -> Unit,
    onPlayNaturalVoice: (String) -> Unit,
    onPlaySlowVoice: (String) -> Unit,
    onStopAudio: () -> Unit,
    onShowCustomerProof: () -> Unit,
    onGoToMic: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pitch = product.pitchesByLang[selectedLanguage.code]
        ?: product.pitchesByLang["en"]
        ?: product.pitchesByLang.values.first()

    val numericPrice = askingPrice.toDoubleOrNull() ?: 400.0
    val officialPrice = product.officialSitePriceEur
    val diff = (officialPrice - numericPrice).coerceAtLeast(0.0).toInt()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Asking Price Input Box
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = LightSurface,
            border = BorderStroke(1.5.dp, ArtisanCognac),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "سعر العرض الذي قلته للعميل في المحل:",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextDark
                        )
                        Text(
                            text = "اكتب السعر وسيقوم الذكاء الاصطناعي ببناء حجة الإقناع الفورية",
                            fontSize = 10.sp,
                            color = TextMuted
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = ArtisanGoldLight
                    ) {
                        Text(
                            text = "صفقة رابحة 💎",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = ArtisanCognac,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = askingPrice,
                        onValueChange = onPriceChanged,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.PriceCheck,
                                contentDescription = null,
                                tint = ArtisanCognac
                            )
                        },
                        trailingIcon = {
                            Text(
                                text = "يورو (€)",
                                fontWeight = FontWeight.Bold,
                                color = ArtisanCognac,
                                modifier = Modifier.padding(end = 12.dp)
                            )
                        },
                        textStyle = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = TextDark
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = LightSurfaceElevated,
                            unfocusedContainerColor = LightSurfaceElevated,
                            focusedBorderColor = ArtisanCognac,
                            unfocusedBorderColor = LightBorder
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("user_asking_price_input")
                    )

                    // Quick Value Gap Banner
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = DealSuccessBg,
                        border = BorderStroke(1.dp, DealSuccess),
                        modifier = Modifier.padding(vertical = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "توفير العميل", fontSize = 10.sp, color = DealSuccess)
                            Text(
                                text = "€$diff!",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = DealSuccess
                            )
                        }
                    }
                }
            }
        }

        // The Winning Pitch Card in Tourist's Language
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = LightSurface,
            border = BorderStroke(1.5.dp, LightBorderStrong),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                // Header: Language Flag & Badge
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = selectedLanguage.flag, fontSize = 22.sp)
                        Column {
                            Text(
                                text = "صيغة الإقناع المحكمة (${selectedLanguage.nameNative}):",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextDark
                            )
                            Text(
                                text = "إثبات القيمة وإبراز مزايا الحرفية اليدوية وتوفير السعر",
                                fontSize = 10.sp,
                                color = TextMuted
                            )
                        }
                    }

                    // Flip Screen Button
                    IconButton(
                        onClick = onShowCustomerProof,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(ArtisanGoldLight)
                            .testTag("show_customer_proof_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Cast,
                            contentDescription = "اعرض الشاشة للسائح",
                            tint = ArtisanCognac
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // The Foreign Speech Text
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = LightSurfaceElevated,
                    border = BorderStroke(1.dp, LightBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = pitch.foreignText,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextDark,
                        modifier = Modifier.padding(14.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Arabic Phonetics Box (النطق الصوتي بالحروف العربية)
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = ArtisanGoldLight,
                    border = BorderStroke(1.dp, ArtisanGold.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(text = "🗣️", fontSize = 14.sp)
                            Text(
                                text = "النطق الصوتي بالعربي (اقرأها للسائح مباشرة دون تعثر):",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = ArtisanCognac
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = pitch.arabicPhonetics,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 22.sp,
                            color = TextDark
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Arabic Meaning
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = LightSurfaceElevated,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = "المعنى والحجة بالعربية:",
                            fontSize = 11.sp,
                            color = TextMuted,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = pitch.arabicTranslation,
                            fontSize = 13.sp,
                            color = TextDark,
                            lineHeight = 19.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Audio Playback Controls: Natural Voice vs Slow Voice
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Main Natural Voice Button
                    Button(
                        onClick = {
                            if (isSpeaking) onStopAudio() else onPlayNaturalVoice(pitch.foreignText)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSpeaking) MaterialTheme.colorScheme.error else ArtisanCognac,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1.4f)
                            .height(48.dp)
                            .testTag("play_natural_closer_voice_button")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = if (isSpeaking) Icons.Default.Stop else Icons.Default.VolumeUp,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = if (isSpeaking) "إيقاف الصوت" else "استمع بنطق طبيعي دافئ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    }

                    // Slow Mode for Practice
                    OutlinedButton(
                        onClick = { onPlaySlowVoice(pitch.foreignText) },
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, RetailAzure),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = RetailAzure),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Hearing, contentDescription = null, modifier = Modifier.size(16.dp))
                            Text(text = "نطق بطيء 0.7x", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Live Objection Microphone Prompt
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = RetailAzureBg,
            border = BorderStroke(1.dp, RetailAzure.copy(alpha = 0.4f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(RetailAzure),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🎙️", fontSize = 20.sp)
                    }
                    Column {
                        Text(
                            text = "هل لدى العميل اعتراض أو تردد في السعر؟",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextDark
                        )
                        Text(
                            text = "اضغط الميكروفون بالأسفل واستمع لكلامه ليقترح الذكاء الاصطناعي الرد الفوري",
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    }
                }

                Button(
                    onClick = onGoToMic,
                    colors = ButtonDefaults.buttonColors(containerColor = RetailAzure, contentColor = Color.White),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.height(38.dp)
                ) {
                    Text(text = "فتح الميكروفون", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
