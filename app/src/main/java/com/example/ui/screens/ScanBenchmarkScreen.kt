package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.data.model.ScannedProductInfo
import com.example.data.repository.BenchmarkData
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
fun ScanBenchmarkScreen(
    product: ScannedProductInfo,
    selectedLanguage: Language,
    isScanning: Boolean,
    onSelectProduct: (String) -> Unit,
    onTriggerScan: () -> Unit,
    onNavigateToPitch: () -> Unit,
    modifier: Modifier = Modifier
) {
    val officialPrice = product.officialSitePriceEur.toInt()
    val ourPrice = product.suggestedRetailPriceEur.toInt()
    val savings = officialPrice - ourPrice
    val savingsPercent = ((savings.toFloat() / officialPrice) * 100).toInt()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Product Quick Selector Chips
        item {
            Text(
                text = "المنتج المستهدف للمسح والفحص الميداني:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BenchmarkData.PRODUCTS.forEach { prod ->
                    val isSelected = prod.id == product.id
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) ArtisanGoldLight else LightSurface,
                        border = BorderStroke(
                            1.5.dp,
                            if (isSelected) ArtisanCognac else LightBorder
                        ),
                        modifier = Modifier
                            .testTag("benchmark_product_${prod.id}")
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { onSelectProduct(prod.id) }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = when (prod.id) {
                                    "scan_bag_01" -> "👜"
                                    "scan_jacket_02" -> "🧥"
                                    else -> "✨"
                                },
                                fontSize = 16.sp
                            )
                            Text(
                                text = prod.nameAr.split("(").first(),
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) ArtisanCognac else TextDark
                            )
                        }
                    }
                }
            }
        }

        // Live Scanning / Camera Hero Card
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = LightSurface),
                border = BorderStroke(1.5.dp, if (isScanning) RetailAzure else ArtisanCognac),
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
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(RetailAzureBg),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = null,
                                    tint = RetailAzure,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = "فحص الذكاء الاصطناعي والمطابقة العالمية",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextDark
                                )
                                Text(
                                    text = "مقارنة حية مع المواقع الرسمية والمتاجر الأوروبية",
                                    fontSize = 11.sp,
                                    color = TextMuted
                                )
                            }
                        }

                        if (isScanning) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = RetailAzure,
                                strokeWidth = 2.5.dp
                            )
                        } else {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = DealSuccessBg
                            ) {
                                Text(
                                    text = "تمت المطابقة بنجاح ✓",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DealSuccess,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Product Identification Title
                    Text(
                        text = product.nameAr,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = TextDark
                    )
                    Text(
                        text = "الموديل المطابق: ${product.officialBrandMatch}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ArtisanCognac
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // 3 Price Benchmark Cards (The Core Value Proof!)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Official Site Price
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = LightSurfaceElevated,
                            border = BorderStroke(1.dp, LightBorder),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Public,
                                        contentDescription = null,
                                        tint = TextMuted,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(text = "الموقع الرسمي", fontSize = 10.sp, color = TextMuted)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "€$officialPrice",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = TextDark
                                )
                                Text(text = "سعر المتجر بباريس", fontSize = 9.sp, color = TextMuted)
                            }
                        }

                        // High Grade Online Stores
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = LightSurfaceElevated,
                            border = BorderStroke(1.dp, LightBorder),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.ShoppingBag,
                                        contentDescription = null,
                                        tint = TextMuted,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(text = "المتاجر العالمية", fontSize = 10.sp, color = TextMuted)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "€${product.onlineStorePriceEur.toInt()}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextDark
                                )
                                Text(text = "نسخ عالية الجودة", fontSize = 9.sp, color = TextMuted)
                            }
                        }

                        // Our Workshop Proposal Price
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = ArtisanGoldLight,
                            border = BorderStroke(1.5.dp, ArtisanCognac),
                            modifier = Modifier.weight(1.15f)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "سعرنا المعروض للعميل",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ArtisanCognac
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "€$ourPrice",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = ArtisanCognac
                                )
                                Text(text = "مباشرة من الورشة", fontSize = 9.sp, color = ArtisanCognac)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Savings Proof Banner
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = DealSuccessBg,
                        border = BorderStroke(1.dp, DealSuccess.copy(alpha = 0.5f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.TrendingDown,
                                    contentDescription = null,
                                    tint = DealSuccess,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = "فارق التوفير والأرباح أمام السائح:",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DealSuccess
                                )
                            }
                            Text(
                                text = "توفير €$savings ($savingsPercent%)!",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = DealSuccess
                            )
                        }
                    }
                }
            }
        }

        // Verified Specifications Checklist
        item {
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = LightSurface,
                border = BorderStroke(1.dp, LightBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "المواصفات الحرفية المؤكدة (الحجة القاطعة للإقناع):",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    product.artisanFeaturesAr.forEach { feat ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = DealSuccess,
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(top = 2.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = feat,
                                fontSize = 13.sp,
                                color = TextDark,
                                lineHeight = 19.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Deep rationale
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = LightSurfaceElevated,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = product.luxuryBrandComparisonAr,
                            fontSize = 12.sp,
                            color = TextMuted,
                            lineHeight = 18.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }

        // Main CTA to Open Deal Closer Engine
        item {
            Button(
                onClick = onNavigateToPitch,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ArtisanCognac,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("go_to_closing_engine_button")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "💎 تجهيز خطة إقناع العميل بـ 400€ (صفقة لا تعوض)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}
