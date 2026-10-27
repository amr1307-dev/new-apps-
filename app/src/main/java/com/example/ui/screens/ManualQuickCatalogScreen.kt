package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.data.model.Language
import com.example.data.repository.DefaultCatalog
import com.example.ui.theme.ArtisanCognac
import com.example.ui.theme.ArtisanGoldLight
import com.example.ui.theme.DealSuccess
import com.example.ui.theme.DealSuccessBg
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightSurface
import com.example.ui.theme.LightSurfaceElevated
import com.example.ui.theme.RetailAzure
import com.example.ui.theme.RetailAzureBg
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted

@Composable
fun ManualQuickCatalogScreen(
    selectedLanguage: Language,
    onSpeak: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val phrases = DefaultCatalog.QUICK_PHRASES

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = ArtisanGoldLight,
                border = BorderStroke(1.dp, ArtisanCognac.copy(alpha = 0.4f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = "🛍️", fontSize = 24.sp)
                    Column {
                        Text(
                            text = "جمل البيع السريعة الميدانية بالمحل",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = ArtisanCognac
                        )
                        Text(
                            text = "عبارات جاهزة بلغة السائح (${selectedLanguage.nameNative}) للترحيب والتجربة والدفع",
                            fontSize = 11.sp,
                            color = TextDark
                        )
                    }
                }
            }
        }

        items(phrases) { phrase ->
            val phraseItem = phrase.translations[selectedLanguage.code]
                ?: phrase.translations["en"]

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = LightSurface,
                border = BorderStroke(1.dp, LightBorder),
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
                            Text(text = phrase.category.emoji, fontSize = 16.sp)
                            Text(
                                text = phrase.titleAr,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextDark
                            )
                        }
                        Text(text = selectedLanguage.flag, fontSize = 16.sp)
                    }

                    if (phraseItem != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = phraseItem.foreignText,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextDark,
                            lineHeight = 21.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        // Phonetics Box
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = LightSurfaceElevated,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "🗣️ النطق: ${phraseItem.arabicPhonetics}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = ArtisanCognac,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = phraseItem.arabicTranslation,
                            fontSize = 11.sp,
                            color = TextMuted
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = { onSpeak(phraseItem.foreignText) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ArtisanCognac,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "نطق الجملة بصوت طبيعي",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
