package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.NavyBorder
import com.example.ui.theme.NavyCard
import com.example.ui.theme.NavyCardElevated

@Composable
fun CurrencyConverterScreen(
    modifier: Modifier = Modifier
) {
    var localAmountText by remember { mutableStateOf("2500") }
    var eurRate by remember { mutableDoubleStateOf(50.0) } // 1 EUR = ~50 Local Units
    var usdRate by remember { mutableDoubleStateOf(48.0) } // 1 USD = ~48 Local Units
    var discountPercent by remember { mutableStateOf(0) }

    val baseAmount = localAmountText.toDoubleOrNull() ?: 0.0
    val discountedAmount = if (discountPercent > 0) baseAmount * (1.0 - discountPercent / 100.0) else baseAmount
    val eurValue = if (eurRate > 0) discountedAmount / eurRate else 0.0
    val usdValue = if (usdRate > 0) discountedAmount / usdRate else 0.0
    val gbpValue = if (eurRate > 0) discountedAmount / (eurRate * 1.15) else 0.0

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CurrencyExchange,
                contentDescription = null,
                tint = GoldPrimary,
                modifier = Modifier.size(24.dp)
            )
            Column {
                Text(
                    text = "حاسبة العملات والخصم الفوري",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "تحويل سريع للسعر وعرض القيمة للسائح باليورو والدولار",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Amount Input Field
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = NavyCard,
            border = BorderStroke(1.dp, NavyBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "المبلغ بالعملة المحلية (ج.م):",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = localAmountText,
                    onValueChange = { localAmountText = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = NavyCardElevated,
                        unfocusedContainerColor = NavyCardElevated,
                        focusedBorderColor = GoldPrimary,
                        unfocusedBorderColor = NavyBorder
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("currency_input_field")
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Discount Quick Chips
                Text(
                    text = "تطبيق خصم تفاوضي:",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf(0 to "بدون خصم", 10 to "خصم 10%", 15 to "خصم 15%", 20 to "خصم 20%").forEach { (pct, label) ->
                        val isSelected = discountPercent == pct
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isSelected) EmeraldAccent else NavyCardElevated,
                            border = BorderStroke(1.dp, if (isSelected) EmeraldAccent else NavyBorder),
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { discountPercent = pct }
                        ) {
                            Text(
                                text = label,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) Color.Black else Color.White,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }

        // Live Tourist Currency Results Card
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = NavyCard,
            border = BorderStroke(2.dp, GoldPrimary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "القيمة المستحقة للدفع أمام السائح",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = GoldPrimary
                )

                if (discountPercent > 0) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "بعد خصم $discountPercent%: ${discountedAmount.toInt()} ج.م (بدلاً من ${baseAmount.toInt()})",
                        fontSize = 12.sp,
                        color = EmeraldAccent
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // EUR
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🇪🇺 اليورو", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(
                            text = "€${"%.1f".format(eurValue)}",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = CyanAccent
                        )
                    }

                    // USD
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🇺🇸 الدولار", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(
                            text = "$${"%.1f".format(usdValue)}",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = EmeraldAccent
                        )
                    }

                    // GBP
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🇬🇧 الإسترليني", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(
                            text = "£${"%.1f".format(gbpValue)}",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
