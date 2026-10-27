package com.example.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cast
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
import com.example.data.model.Product
import com.example.ui.components.ActionSelectorRow
import com.example.ui.components.CustomerFacingDialog
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.NavyBorder
import com.example.ui.theme.NavyCard
import com.example.ui.theme.NavyCardElevated
import com.example.ui.viewmodel.SalesActionTab

@Composable
fun ProductSalesDetailScreen(
    product: Product,
    selectedLanguage: Language,
    selectedActionTab: SalesActionTab,
    isSpeaking: Boolean,
    isCustomerCardOpen: Boolean,
    onBack: () -> Unit,
    onTabSelected: (SalesActionTab) -> Unit,
    onSpeakNormal: (String) -> Unit,
    onSpeakSlow: (String) -> Unit,
    onStopAudio: () -> Unit,
    onToggleCustomerCard: (Boolean) -> Unit,
    onLogFeedback: (Boolean) -> Unit,
    onRecordPractice: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    if (isCustomerCardOpen) {
        CustomerFacingDialog(
            product = product,
            language = selectedLanguage,
            onDismiss = { onToggleCustomerCard(false) },
            onPlayAudio = {
                val pitch = product.pitch[selectedLanguage.code] ?: product.pitch["en"]
                if (pitch != null) onSpeakNormal(pitch.foreignText)
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Navigation & Product Quick Header
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = NavyCard,
            border = BorderStroke(1.dp, NavyBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(NavyCardElevated)
                            .testTag("detail_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "رجوع للكتالوج",
                            tint = Color.White
                        )
                    }

                    // Customer Facing Flip Button
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = GoldPrimary.copy(alpha = 0.2f),
                        border = BorderStroke(1.dp, GoldPrimary),
                        modifier = Modifier
                            .testTag("flip_customer_card_button")
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        IconButton(
                            onClick = { onToggleCustomerCard(true) },
                            modifier = Modifier.height(38.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Cast,
                                    contentDescription = null,
                                    tint = GoldPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "اعرض الشاشة للسائح",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = GoldPrimary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(NavyCardElevated),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = product.iconEmoji, fontSize = 32.sp)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = product.nameAr,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        val foreignName = product.nameNativeMap[selectedLanguage.code] ?: ""
                        if (foreignName.isNotBlank()) {
                            Text(
                                text = foreignName,
                                fontSize = 12.sp,
                                color = CyanAccent
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${product.priceLocal.toInt()} ${product.currencyLocal}",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldPrimary
                            )
                            Text(
                                text = "•",
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "€${product.priceEur.toInt()} / $${product.priceUsd.toInt()}",
                                fontSize = 12.sp,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }
        }

        // The 4 Action Buttons as demanded in the brief:
        // 1. اشرح المنتج
        // 2. الرد على سؤال
        // 3. التعامل مع السعر
        // 4. علّمني الجملة
        ActionSelectorRow(
            selectedTab = selectedActionTab,
            onTabSelected = onTabSelected
        )

        // Dynamic Tab Content
        when (selectedActionTab) {
            SalesActionTab.EXPLAIN -> {
                ExplainTabContent(
                    product = product,
                    selectedLanguage = selectedLanguage,
                    isSpeaking = isSpeaking,
                    onSpeakNormal = onSpeakNormal,
                    onSpeakSlow = onSpeakSlow,
                    onStopAudio = onStopAudio,
                    onShowCustomerCard = { onToggleCustomerCard(true) },
                    onLogFeedback = onLogFeedback
                )
            }
            SalesActionTab.QUESTIONS -> {
                QuestionsTabContent(
                    product = product,
                    selectedLanguage = selectedLanguage,
                    isSpeaking = isSpeaking,
                    onSpeakNormal = onSpeakNormal,
                    onSpeakSlow = onSpeakSlow,
                    onStopAudio = onStopAudio,
                    onShowCustomerCard = { onToggleCustomerCard(true) },
                    onLogFeedback = onLogFeedback
                )
            }
            SalesActionTab.PRICE -> {
                PriceObjectionTabContent(
                    product = product,
                    selectedLanguage = selectedLanguage,
                    isSpeaking = isSpeaking,
                    onSpeakNormal = onSpeakNormal,
                    onSpeakSlow = onSpeakSlow,
                    onStopAudio = onStopAudio,
                    onShowCustomerCard = { onToggleCustomerCard(true) },
                    onLogFeedback = onLogFeedback
                )
            }
            SalesActionTab.PRACTICE -> {
                PracticeTrainerTabContent(
                    product = product,
                    selectedLanguage = selectedLanguage,
                    isSpeaking = isSpeaking,
                    onSpeakNormal = onSpeakNormal,
                    onSpeakSlow = onSpeakSlow,
                    onRecordPractice = onRecordPractice
                )
            }
        }
    }
}
