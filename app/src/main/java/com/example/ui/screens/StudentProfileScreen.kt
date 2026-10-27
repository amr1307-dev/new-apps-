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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
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
import com.example.data.model.StudentProfileEntity
import com.example.data.model.UniversityCertificateEntity
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.NavyBorder
import com.example.ui.theme.NavyCard
import com.example.ui.theme.NavyCardElevated
import com.example.ui.theme.PurpleAccent
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun StudentProfileScreen(
    studentProfile: StudentProfileEntity?,
    certificates: List<UniversityCertificateEntity>,
    modifier: Modifier = Modifier
) {
    val totalXp = studentProfile?.totalXp ?: 240
    val streakDays = studentProfile?.currentStreakDays ?: 5
    val rank = studentProfile?.rankTitleAr ?: "طالب جامعي مجتهد"

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // University Student ID Card
        item {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = NavyCard,
                border = BorderStroke(2.dp, GoldPrimary),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("student_id_card")
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(54.dp)
                                    .clip(CircleShape)
                                    .background(GoldPrimary.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "👨‍🎓", fontSize = 28.sp)
                            }
                            Column {
                                Text(
                                    text = "بطاقة الطالب الجامعي",
                                    fontSize = 11.sp,
                                    color = GoldPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "طالب أكاديمية اللغات",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "الرتبة: $rank",
                                    fontSize = 12.sp,
                                    color = EmeraldAccent
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = "موثق",
                            tint = EmeraldAccent,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Academic Stats Grid
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ProfileStatItem(
                            title = "نقاط الخبرة",
                            value = "$totalXp XP",
                            icon = Icons.Default.Star,
                            iconColor = GoldPrimary
                        )
                        ProfileStatItem(
                            title = "أيام الحماسة",
                            value = "$streakDays أيام 🔥",
                            icon = Icons.Default.LocalFireDepartment,
                            iconColor = Color(0xFFEF4444)
                        )
                        ProfileStatItem(
                            title = "الشهادات",
                            value = "${certificates.size.coerceAtLeast(1)}",
                            icon = Icons.Default.CardMembership,
                            iconColor = CyanAccent
                        )
                    }
                }
            }
        }

        // Earned University Certificates
        item {
            Text(
                text = "الشهادات والدرجات الأكاديمية الصادرة:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Display sample default cert if empty
        val displayCerts = if (certificates.isNotEmpty()) {
            certificates
        } else {
            listOf(
                UniversityCertificateEntity(
                    certificateId = "CERT-DE-A1-2026-8941",
                    trackCode = "de",
                    levelCode = "A1",
                    studentName = "طالب أكاديمية اللغات المتفوق",
                    scoreGrade = "امتياز مع مرتبة الشرف (A+)",
                    issuedDateMillis = System.currentTimeMillis()
                )
            )
        }

        items(displayCerts) { cert ->
            val dateStr = SimpleDateFormat("dd MMMM yyyy", Locale("ar")).format(Date(cert.issuedDateMillis))
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = NavyCard,
                border = BorderStroke(1.5.dp, GoldPrimary),
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
                            Text(text = "🏛️", fontSize = 20.sp)
                            Text(
                                text = "جامعة اللغات في جيبك",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldPrimary
                            )
                        }
                        Text(
                            text = cert.levelCode,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = CyanAccent
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "شهادة إتمام المستوى التأسيسي ${cert.levelCode}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "تشهد الأكاديمية باجتياز الطالب كافة متطلبات المحاضرات والاختبارات التفاعلية بنجاح.",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 17.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "التقدير: ${cert.scoreGrade}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldAccent
                        )
                        Text(
                            text = dateStr,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        text = "كود التوثيق: ${cert.certificateId}",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileStatItem(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = NavyCardElevated,
        modifier = Modifier.padding(horizontal = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = title,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
