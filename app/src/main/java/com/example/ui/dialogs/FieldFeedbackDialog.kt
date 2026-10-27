package com.example.ui.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.NavyBorder
import com.example.ui.theme.NavyCard
import com.example.ui.theme.NavyDark

@Composable
fun FieldFeedbackDialog(
    onDismiss: () -> Unit,
    onSubmitFeedback: (understood: Boolean, speedSeconds: Int, notes: String) -> Unit
) {
    var understood by remember { mutableStateOf(true) }
    var speedSeconds by remember { mutableIntStateOf(3) }
    var note by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = NavyDark),
            border = BorderStroke(1.5.dp, EmeraldAccent),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("field_feedback_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "تسجيل نتيجة التفاعل الميداني مع السائح",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "هل فهم السائح الشرح ورد الفعل بوضوح؟",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { understood = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (understood) EmeraldAccent else NavyCard,
                            contentColor = if (understood) Color.Black else Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "👍 نعم، فهم تماماً", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }

                    Button(
                        onClick = { understood = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (!understood) MaterialTheme.colorScheme.error else NavyCard,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "👎 جزئي / صعوبة", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }

                // Speed
                Text(
                    text = "سرعة الوصول للجملة: $speedSeconds ثوانٍ",
                    fontSize = 12.sp,
                    color = GoldPrimary
                )

                // Notes
                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    placeholder = { Text("ملاحظة سريعة (مثال: اشترى قطعتين، أبدى إعجابه بالنطق الألماني)") },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = NavyCard,
                        unfocusedContainerColor = NavyCard,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, NavyBorder),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "إلغاء", color = Color.White)
                    }

                    Button(
                        onClick = { onSubmitFeedback(understood, speedSeconds, note) },
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldAccent, contentColor = Color.Black),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1.3f)
                    ) {
                        Text(text = "حفظ التفاعل", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
