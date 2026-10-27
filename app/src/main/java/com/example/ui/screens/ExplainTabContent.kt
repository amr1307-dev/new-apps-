package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Language
import com.example.data.model.Product
import com.example.ui.components.PhraseSpeechCard
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.NavyBorder
import com.example.ui.theme.NavyCard

@Composable
fun ExplainTabContent(
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
    val pitch = product.pitch[selectedLanguage.code] ?: product.pitch["en"]

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (pitch != null) {
            PhraseSpeechCard(
                phrase = pitch,
                language = selectedLanguage,
                isSpeaking = isSpeaking,
                onSpeakNormal = { onSpeakNormal(pitch.foreignText) },
                onSpeakSlow = { onSpeakSlow(pitch.foreignText) },
                onStopAudio = onStopAudio,
                onShowCustomerCard = onShowCustomerCard,
                onLogFeedback = onLogFeedback,
                titleLabel = "جملة تقديم وشرح المنتج"
            )
        }

        // Verified Product Facts (حقائق مؤكدة تمنع اختراع المواصفات)
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = NavyCard,
            border = BorderStroke(1.dp, NavyBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "🛡️", fontSize = 18.sp)
                    Text(
                        text = "المعلومات المؤكدة للمنتج (غير قابلة للاختلاق):",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                product.verifiedFacts.forEach { fact ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = EmeraldAccent,
                            modifier = Modifier
                                .size(16.dp)
                                .padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = fact,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 19.sp
                        )
                    }
                }
            }
        }
    }
}
