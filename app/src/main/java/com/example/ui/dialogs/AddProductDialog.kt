package com.example.ui.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.ProductCategory
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.NavyBorder
import com.example.ui.theme.NavyCard
import com.example.ui.theme.NavyDark

@Composable
fun AddProductDialog(
    onDismiss: () -> Unit,
    onSaveProduct: (
        nameAr: String,
        categoryId: String,
        priceLocal: Double,
        priceEur: Double,
        facts: String,
        pitchGerman: String,
        pitchEnglish: String,
        pitchPhonetics: String
    ) -> Unit
) {
    var nameAr by remember { mutableStateOf("") }
    var selectedCategoryId by remember { mutableStateOf(ProductCategory.LEATHER.id) }
    var priceLocalText by remember { mutableStateOf("1500") }
    var priceEurText by remember { mutableStateOf("30") }
    var factsRaw by remember { mutableStateOf("صناعة يدوية محلية أصيلة\nخامات طبيعية 100%\nضمان استبدال معتمد") }
    var pitchGerman by remember { mutableStateOf("Dieses Produkt ist handgefertigt und von höchster Qualität.") }
    var pitchEnglish by remember { mutableStateOf("This authentic item is handcrafted with the highest quality.") }
    var pitchPhonetics by remember { mutableStateOf("ديزِس برودوكت إست هاند-غِفرتيكت أوند فون هوخستر كفاليتيت.") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = NavyDark),
            border = BorderStroke(1.5.dp, GoldPrimary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("add_product_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "إضافة منتج جديد لكتالوج المحل",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                // Name AR
                OutlinedTextField(
                    value = nameAr,
                    onValueChange = { nameAr = it },
                    label = { Text("اسم المنتج بالعربية (مثال: محفظة جلد جمل)") },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = NavyCard,
                        unfocusedContainerColor = NavyCard,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // Category selector
                Text(text = "القسم:", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    ProductCategory.entries.forEach { cat ->
                        val isSelected = selectedCategoryId == cat.id
                        Button(
                            onClick = { selectedCategoryId = cat.id },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSelected) GoldPrimary else NavyCard,
                                contentColor = if (isSelected) Color.Black else Color.White
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = cat.iconEmoji, fontSize = 14.sp)
                        }
                    }
                }

                // Prices
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = priceLocalText,
                        onValueChange = { priceLocalText = it },
                        label = { Text("السعر المحلي (ج.م)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = NavyCard,
                            unfocusedContainerColor = NavyCard,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    OutlinedTextField(
                        value = priceEurText,
                        onValueChange = { priceEurText = it },
                        label = { Text("السعر باليورو (€)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = NavyCard,
                            unfocusedContainerColor = NavyCard,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }

                // Verified facts
                OutlinedTextField(
                    value = factsRaw,
                    onValueChange = { factsRaw = it },
                    label = { Text("المعلومات المؤكدة (سطر لكل معلومة)") },
                    minLines = 3,
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = NavyCard,
                        unfocusedContainerColor = NavyCard,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // German pitch
                OutlinedTextField(
                    value = pitchGerman,
                    onValueChange = { pitchGerman = it },
                    label = { Text("جملة الشرح بالألمانية") },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = NavyCard,
                        unfocusedContainerColor = NavyCard,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // Phonetics
                OutlinedTextField(
                    value = pitchPhonetics,
                    onValueChange = { pitchPhonetics = it },
                    label = { Text("النطق الصوتي بالعربي") },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = NavyCard,
                        unfocusedContainerColor = NavyCard,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Actions
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
                        onClick = {
                            if (nameAr.isNotBlank()) {
                                onSaveProduct(
                                    nameAr,
                                    selectedCategoryId,
                                    priceLocalText.toDoubleOrNull() ?: 0.0,
                                    priceEurText.toDoubleOrNull() ?: 0.0,
                                    factsRaw,
                                    pitchGerman,
                                    pitchEnglish,
                                    pitchPhonetics
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GoldPrimary,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1.4f)
                    ) {
                        Text(text = "حفظ في الكتالوج", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
