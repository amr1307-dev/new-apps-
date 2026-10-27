package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.window.Dialog
import com.example.data.model.Language
import com.example.data.model.ScannedProductInfo
import com.example.ui.theme.ArtisanCognac
import com.example.ui.theme.ArtisanGoldLight
import com.example.ui.theme.DealSuccess
import com.example.ui.theme.DealSuccessBg
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightSurface
import com.example.ui.theme.LightSurfaceElevated
import com.example.ui.theme.RetailAzure
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted

@Composable
fun CustomerProofDialog(
    product: ScannedProductInfo,
    selectedLanguage: Language,
    askingPrice: String,
    onDismiss: () -> Unit,
    onPlayAudio: () -> Unit
) {
    val pitch = product.pitchesByLang[selectedLanguage.code]
        ?: product.pitchesByLang["en"]
        ?: product.pitchesByLang.values.first()

    val officialPrice = product.officialSitePriceEur.toInt()
    val numericPrice = askingPrice.toIntOrNull() ?: product.suggestedRetailPriceEur.toInt()

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = LightSurface),
            border = BorderStroke(2.dp, ArtisanCognac),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("customer_proof_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header: Language Flag & Close
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = selectedLanguage.flag, fontSize = 28.sp)
                        Column {
                            Text(
                                text = "EXCLUSIVE DIRECT WORKSHOP OFFER",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = ArtisanCognac
                            )
                            Text(
                                text = "Verified Handcrafted Quality",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextDark
                            )
                        }
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(LightSurfaceElevated)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextDark
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Product Emoji & Matching Brand
                Text(text = "👜", fontSize = 48.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.officialBrandMatch,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Dual Price Comparison Box
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = LightSurfaceElevated,
                    border = BorderStroke(1.dp, LightBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "OFFICIAL BOUTIQUE",
                                fontSize = 10.sp,
                                color = TextMuted,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "€$officialPrice",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextMuted
                            )
                        }

                        Box(
                            modifier = Modifier
                                .height(38.dp)
                                .width(1.dp)
                                .background(LightBorder)
                        )

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "TODAY'S SPECIAL DEAL",
                                fontSize = 10.sp,
                                color = ArtisanCognac,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                text = "€$numericPrice",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = ArtisanCognac
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Guaranteed Authentic Full Grain Badge
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = DealSuccessBg,
                    border = BorderStroke(1.dp, DealSuccess),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = DealSuccess,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "100% Full-Grain Natural Leather Guaranteed",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DealSuccess
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Foreign Pitch Text Quote
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = LightSurfaceElevated,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "“${pitch.foreignText}”",
                        fontSize = 13.sp,
                        lineHeight = 19.sp,
                        color = TextDark,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Listen Audio Button in Foreign Language
                Button(
                    onClick = onPlayAudio,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ArtisanCognac,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Listen in ${selectedLanguage.nameNative}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
