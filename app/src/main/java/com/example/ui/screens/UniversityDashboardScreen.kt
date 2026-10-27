package com.example.ui.screens

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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Language
import com.example.data.model.Lesson
import com.example.data.model.StudentProfileEntity
import com.example.ui.theme.BlueAccent
import com.example.ui.theme.BlueLightBg
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldGradientEnd
import com.example.ui.theme.EmeraldGradientStart
import com.example.ui.theme.EmeraldLightBg
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.FlameOrange
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightSurface
import com.example.ui.theme.LightSurfaceElevated
import com.example.ui.theme.NeonLime
import com.example.ui.theme.OrangeAccent
import com.example.ui.theme.OrangeLightBg
import com.example.ui.theme.PurpleAccent
import com.example.ui.theme.PurpleLightBg
import com.example.ui.theme.StarGold
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextSubtle
import com.example.ui.viewmodel.UniversityNavTab

@Composable
fun UniversityDashboardScreen(
    studentProfile: StudentProfileEntity?,
    selectedTrack: Language,
    nextLesson: Lesson?,
    onNavigate: (UniversityNavTab) -> Unit,
    onStartLesson: (Lesson) -> Unit,
    onSpeak: (String) -> Unit,
    onOpenVoiceCoach: () -> Unit = {},
    onOpenCloser: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val streakDays = studentProfile?.currentStreakDays ?: 12
    val totalXp = studentProfile?.totalXp ?: 1250
    val level = studentProfile?.rankTitleAr ?: "Level 8"
    val progressFloat = 0.70f

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Top Header Bar: User Profile + Notifications + Streaks & XP (Matching Image 3 Mockup)
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
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
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(EmeraldLightBg)
                                .clickable { onNavigate(UniversityNavTab.PROFILE) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "👑", fontSize = 22.sp)
                        }
                        Column {
                            Text(
                                text = "أهلاً بك يا بطل المبيعات 👋",
                                fontSize = 12.sp,
                                color = TextMuted
                            )
                            Text(
                                text = "Alex Emma",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = TextDark
                            )
                        }
                    }

                    // Notification Bell Button
                    Surface(
                        shape = CircleShape,
                        color = LightSurface,
                        border = BorderStroke(1.dp, LightBorder),
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "الإشعارات",
                                tint = TextDark,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Stats Row: Level 8 (70%) + 12 Day Streak + 1250 Total XP
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = LightSurface,
                    border = BorderStroke(1.dp, LightBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Level & Progress
                        Column(modifier = Modifier.weight(1.2f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = level,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = TextDark
                                )
                                Text(
                                    text = "${(progressFloat * 100).toInt()}%",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldPrimary
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            LinearProgressIndicator(
                                progress = { progressFloat },
                                color = EmeraldPrimary,
                                trackColor = EmeraldLightBg,
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(7.dp)
                                    .clip(RoundedCornerShape(4.dp))
                            )
                        }

                        // Streak
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = "أيام الالتزام",
                                tint = FlameOrange,
                                modifier = Modifier.size(20.dp)
                            )
                            Column {
                                Text(
                                    text = "$streakDays Days",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextDark
                                )
                                Text(
                                    text = "Streak 🔥",
                                    fontSize = 10.sp,
                                    color = FlameOrange,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        // Total XP
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "نقاط الخبرة",
                                tint = StarGold,
                                modifier = Modifier.size(20.dp)
                            )
                            Column {
                                Text(
                                    text = "$totalXp",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextDark
                                )
                                Text(
                                    text = "Total XP ⭐",
                                    fontSize = 10.sp,
                                    color = TextMuted,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }

        // 2. AI Voice Coach Hero Card (Emerald Gradient with 3D Robot & Start Speaking Pill Button)
        item {
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("ai_voice_coach_hero_card")
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(EmeraldGradientStart, EmeraldGradientEnd)
                            )
                        )
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1.3f)) {
                            Text(
                                text = "AI Voice Coach 🎙️",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Speak • get feedback • close deals\nتحدث، صحح نطقك، وتدرّب على إقناع الزبون بـ 400€",
                                fontSize = 12.sp,
                                color = Color.White.copy(alpha = 0.9f),
                                lineHeight = 17.sp
                            )
                            Spacer(modifier = Modifier.height(14.dp))

                            // Start Speaking Pill Button (Neon Lime)
                            Surface(
                                shape = RoundedCornerShape(24.dp),
                                color = NeonLime,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(24.dp))
                                    .clickable { onOpenVoiceCoach() }
                                    .testTag("start_speaking_pill_button")
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 9.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(
                                        text = "Start Speaking",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = EmeraldDeep
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = null,
                                        tint = EmeraldDeep,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }

                        // 3D Robot Mascot Icon / Badge
                        Box(
                            modifier = Modifier
                                .size(88.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color.White.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "🤖✨", fontSize = 44.sp)
                        }
                    }
                }
            }
        }

        // 3. Continue Learning Card (Matching Image 3 "Continue Learning" section)
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Continue Learning • متابعة التدريب",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )
                    Text(
                        text = "See all",
                        fontSize = 12.sp,
                        color = EmeraldPrimary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable { onNavigate(UniversityNavTab.CURRICULUM) }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = LightSurface,
                    border = BorderStroke(1.dp, LightBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .clickable {
                            if (nextLesson != null) onStartLesson(nextLesson)
                            else onOpenCloser()
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(54.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(EmeraldLightBg),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "👜💎", fontSize = 26.sp)
                            }

                            Column {
                                Text(
                                    text = nextLesson?.titleAr ?: "فن إقناع السائح بـ 400€ للشنطة",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextDark
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "5 lessons • 15 Min • نطق بشري فوري",
                                    fontSize = 11.sp,
                                    color = TextMuted
                                )
                            }
                        }

                        // Circular Play Button
                        Surface(
                            shape = CircleShape,
                            color = EmeraldLightBg,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "ابدأ الدرس",
                                    tint = EmeraldPrimary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // 4. Learning Paths (Horizontal Row with Circular Badges: Small Talk, Speaking, Confidence, Retail Closer)
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Learning Paths • مسارات التدريب",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )
                    Text(
                        text = "See all",
                        fontSize = 12.sp,
                        color = EmeraldPrimary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable { onNavigate(UniversityNavTab.SPEAKING_LAB) }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    listOf(
                        PathChipItem("Small Talk", "محادثات", "💬", PurpleAccent, PurpleLightBg) {
                            onNavigate(UniversityNavTab.SPEAKING_LAB)
                        },
                        PathChipItem("Speaking", "نطق سليم", "🎙️", OrangeAccent, OrangeLightBg) {
                            onOpenVoiceCoach()
                        },
                        PathChipItem("Confidence", "ثقة وبراعة", "🛡️", EmeraldPrimary, EmeraldLightBg) {
                            onNavigate(UniversityNavTab.CURRICULUM)
                        },
                        PathChipItem("Closer 400€", "صفقة الـ 400€", "💎", BlueAccent, BlueLightBg) {
                            onOpenCloser()
                        }
                    ).forEach { chip ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { chip.onClick() }
                                .padding(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .background(chip.bgColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = chip.emoji, fontSize = 24.sp)
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = chip.title,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextDark
                            )
                            Text(
                                text = chip.subtitle,
                                fontSize = 9.sp,
                                color = TextMuted
                            )
                        }
                    }
                }
            }
        }

        // 5. Jump-in Lessons & Practice Scenarios (Roleplays from Image 2: Store Shopping, Deal Closer, Cafe)
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Speaking Practice • سيناريوهات حية للمحل والمحادثة",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        items(scenariosList.size) { index ->
            val scenario = scenariosList[index]
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = LightSurface,
                border = BorderStroke(1.dp, LightBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        onSpeak(scenario.foreignGreeting)
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(scenario.accentBg),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = scenario.emoji, fontSize = 22.sp)
                        }

                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = scenario.title,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextDark
                                )
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = EmeraldLightBg
                                ) {
                                    Text(
                                        text = scenario.badge,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = EmeraldPrimary,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = scenario.subtitle,
                                fontSize = 11.sp,
                                color = TextMuted
                            )
                        }
                    }

                    // Voice preview button
                    IconButton(
                        onClick = { onSpeak(scenario.foreignGreeting) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "استمع للنطق",
                            tint = EmeraldPrimary,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        }
    }
}

private data class PathChipItem(
    val title: String,
    val subtitle: String,
    val emoji: String,
    val accentColor: Color,
    val bgColor: Color,
    val onClick: () -> Unit
)

private data class ScenarioItem(
    val title: String,
    val subtitle: String,
    val emoji: String,
    val badge: String,
    val accentBg: Color,
    val foreignGreeting: String
)

private val scenariosList = listOf(
    ScenarioItem(
        title = "Negotiating a Luxury Bag Deal ⭐",
        subtitle = "إقناع السائح بالسعر 400€ والمقارنة بالأصلي (3,200€)",
        emoji = "👜",
        badge = "صفقة رابحة",
        accentBg = EmeraldLightBg,
        foreignGreeting = "Look at this handmade saddle leather stitching. In Rome, the boutique charges 3,200 euros. Today you take this identical masterpiece for only 400 euros."
    ),
    ScenarioItem(
        title = "Handling Price Objections ⭐",
        subtitle = "الرد على اعتراض 'السعر غالي جداً'",
        emoji = "🛡️",
        badge = "ميداني",
        accentBg = OrangeLightBg,
        foreignGreeting = "I understand price matters to you. But cheap synthetic bags crack in two months. Genuine full-grain leather lasts twenty years."
    ),
    ScenarioItem(
        title = "Shopping at Store & Welcoming",
        subtitle = "الترحيب بالزبون وبناء الألفة بأسلوب ودود",
        emoji = "🛍️",
        badge = "ترحيب",
        accentBg = BlueLightBg,
        foreignGreeting = "Welcome! Take your time to feel the texture of our handmade leather collections. Can I show you our finest piece?"
    ),
    ScenarioItem(
        title = "Small Talk with Foreign Tourists",
        subtitle = "فتح حديث ودي وسؤال السائح عن رحلته",
        emoji = "☕",
        badge = "طلاقة",
        accentBg = PurpleLightBg,
        foreignGreeting = "Are you enjoying your trip in our city? Where are you visiting us from today?"
    )
)


