package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.example.ui.theme.ArtisanCognac
import com.example.ui.theme.ArtisanGold
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
fun CloserTopBar(
    selectedLanguage: Language,
    onLanguageSelected: (Language) -> Unit,
    onScanProductClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = LightSurface,
        border = BorderStroke(1.dp, LightBorder),
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // App Branding & Instant Camera Capture Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(ArtisanGoldLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "👜", fontSize = 22.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "مساعد البائع الذكي",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = TextDark
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = DealSuccessBg
                            ) {
                                Text(
                                    text = "مقارنة وإقناع فوري",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DealSuccess,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = "مسح المنتج • مقارنة الماركات • إقناع العميل بصفقة رابحة",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextMuted
                        )
                    }
                }

                // Camera Scan Button
                Button(
                    onClick = onScanProductClicked,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ArtisanCognac,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .height(40.dp)
                        .testTag("top_bar_scan_camera_button")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = "تصوير المنتج",
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "تصوير ومسح",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Tourist Language Selector Chips
            Text(
                text = "اختر لغة السائح المتواجد معك في المحل:",
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
                Language.ALL.forEach { lang ->
                    val isSelected = lang.code == selectedLanguage.code
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) ArtisanCognac else LightSurfaceElevated,
                        border = BorderStroke(
                            1.dp,
                            if (isSelected) ArtisanCognac else LightBorder
                        ),
                        modifier = Modifier
                            .testTag("lang_chip_${lang.code}")
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { onLanguageSelected(lang) }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(text = lang.flag, fontSize = 16.sp)
                            Column {
                                Text(
                                    text = lang.nameAr,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else TextDark
                                )
                                Text(
                                    text = lang.nameNative,
                                    fontSize = 10.sp,
                                    color = if (isSelected) Color.White.copy(alpha = 0.85f) else TextMuted
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
