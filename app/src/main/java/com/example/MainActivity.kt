package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.model.Lesson
import com.example.data.repository.UniversityCurriculumData
import com.example.ui.components.CloserTopBar
import com.example.ui.components.CustomerProofDialog
import com.example.ui.screens.ClosingPitchScreen
import com.example.ui.screens.CurriculumScreen
import com.example.ui.screens.LessonPlayerScreen
import com.example.ui.screens.LiveMicObjectionScreen
import com.example.ui.screens.ManualQuickCatalogScreen
import com.example.ui.screens.ScanBenchmarkScreen
import com.example.ui.screens.SpeakingLabScreen
import com.example.ui.screens.StudentProfileScreen
import com.example.ui.screens.UniversityDashboardScreen
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldDeep
import com.example.ui.theme.EmeraldGradientEnd
import com.example.ui.theme.EmeraldGradientStart
import com.example.ui.theme.EmeraldLightBg
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightSurface
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.NeonLime
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import com.example.ui.viewmodel.CloserTab
import com.example.ui.viewmodel.SalesCloserViewModel
import com.example.ui.viewmodel.UniversityNavTab
import com.example.ui.viewmodel.UniversityViewModel

enum class MainAppTab {
    ACADEMY_HOME,
    CLOSER_DEAL,
    LIVE_VOICE_COACH,
    PRACTICE_LESSONS,
    PROFILE_STATS
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme(darkTheme = false) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    UnifiedSalesAndUniversityApp()
                }
            }
        }
    }
}

@Composable
fun UnifiedSalesAndUniversityApp(
    closerVm: SalesCloserViewModel = viewModel(),
    universityVm: UniversityViewModel = viewModel()
) {
    val closerState by closerVm.uiState.collectAsStateWithLifecycle()
    val universityState by universityVm.uiState.collectAsStateWithLifecycle()
    val studentProfileState by universityVm.studentProfile.collectAsStateWithLifecycle()
    val certificatesState by universityVm.certificates.collectAsStateWithLifecycle()
    val isSpeaking by closerVm.ttsManager.isSpeaking.collectAsStateWithLifecycle()

    var activeMainTab by remember { mutableStateOf(MainAppTab.ACADEMY_HOME) }

    if (closerState.showCustomerDialog) {
        CustomerProofDialog(
            product = closerState.currentProduct,
            selectedLanguage = closerState.selectedLanguage,
            askingPrice = closerState.userAskingPriceEur,
            onDismiss = { closerVm.setShowCustomerDialog(false) },
            onPlayAudio = {
                val pitch = closerState.currentProduct.pitchesByLang[closerState.selectedLanguage.code]
                    ?: closerState.currentProduct.pitchesByLang["en"]
                    ?: closerState.currentProduct.pitchesByLang.values.first()
                closerVm.playNaturalVoice(pitch.foreignText)
            }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CloserTopBar(
                selectedLanguage = closerState.selectedLanguage,
                onLanguageSelected = {
                    closerVm.selectLanguage(it)
                    universityVm.selectTrack(it)
                },
                onScanProductClicked = {
                    activeMainTab = MainAppTab.CLOSER_DEAL
                    closerVm.simulateCameraCapture()
                }
            )
        },
        bottomBar = {
            FloatingDockBottomNav(
                currentTab = activeMainTab,
                onSelectTab = { activeMainTab = it }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (activeMainTab) {
                MainAppTab.ACADEMY_HOME -> {
                    if (universityState.activeLesson != null) {
                        LessonPlayerScreen(
                            lesson = universityState.activeLesson!!,
                            activeTab = universityState.activeLessonTab,
                            selectedTrack = universityState.selectedTrack,
                            isSpeaking = isSpeaking,
                            quizAnswers = universityState.quizAnswers,
                            isQuizSubmitted = universityState.isQuizSubmitted,
                            quizScorePercent = universityState.quizScorePercent,
                            onBack = { universityVm.closeLesson() },
                            onTabSelected = { universityVm.setLessonTab(it) },
                            onSpeakNormal = { closerVm.playNaturalVoice(it, isSlow = false) },
                            onSpeakSlow = { closerVm.playNaturalVoice(it, isSlow = true) },
                            onSaveWord = { universityVm.saveWord(it) },
                            onSelectQuizAnswer = { qId, opt -> universityVm.selectQuizAnswer(qId, opt) },
                            onSubmitQuiz = { universityVm.submitQuiz() }
                        )
                    } else {
                        val recommendedLesson = UniversityCurriculumData.LESSONS.firstOrNull {
                            it.trackCode == universityState.selectedTrack.code
                        }
                        UniversityDashboardScreen(
                            studentProfile = studentProfileState,
                            selectedTrack = universityState.selectedTrack,
                            nextLesson = recommendedLesson,
                            onNavigate = { navTab ->
                                when (navTab) {
                                    UniversityNavTab.CURRICULUM -> activeMainTab = MainAppTab.PRACTICE_LESSONS
                                    UniversityNavTab.SPEAKING_LAB -> activeMainTab = MainAppTab.LIVE_VOICE_COACH
                                    UniversityNavTab.PROFILE -> activeMainTab = MainAppTab.PROFILE_STATS
                                    else -> {}
                                }
                            },
                            onStartLesson = { lesson -> universityVm.openLesson(lesson) },
                            onSpeak = { text -> closerVm.playNaturalVoice(text, isSlow = false) },
                            onOpenVoiceCoach = { activeMainTab = MainAppTab.LIVE_VOICE_COACH },
                            onOpenCloser = { activeMainTab = MainAppTab.CLOSER_DEAL }
                        )
                    }
                }

                MainAppTab.CLOSER_DEAL -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        // Sub-header for Closer tabs
                        CloserSubNav(
                            currentSubTab = closerState.activeTab,
                            onSelectSubTab = { closerVm.setCloserTab(it) }
                        )

                        when (closerState.activeTab) {
                            CloserTab.SCAN_BENCHMARK -> {
                                ScanBenchmarkScreen(
                                    product = closerState.currentProduct,
                                    selectedLanguage = closerState.selectedLanguage,
                                    isScanning = closerState.isScanningImage,
                                    onSelectProduct = { closerVm.scanProduct(it) },
                                    onTriggerScan = { closerVm.simulateCameraCapture() },
                                    onNavigateToPitch = { closerVm.setCloserTab(CloserTab.CLOSING_PITCH) }
                                )
                            }
                            CloserTab.CLOSING_PITCH -> {
                                ClosingPitchScreen(
                                    product = closerState.currentProduct,
                                    selectedLanguage = closerState.selectedLanguage,
                                    askingPrice = closerState.userAskingPriceEur,
                                    isSpeaking = isSpeaking,
                                    onPriceChanged = { closerVm.updateAskingPrice(it) },
                                    onPlayNaturalVoice = { closerVm.playNaturalVoice(it, isSlow = false) },
                                    onPlaySlowVoice = { closerVm.playNaturalVoice(it, isSlow = true) },
                                    onStopAudio = { closerVm.stopAudio() },
                                    onShowCustomerProof = { closerVm.setShowCustomerDialog(true) },
                                    onGoToMic = { activeMainTab = MainAppTab.LIVE_VOICE_COACH }
                                )
                            }
                            CloserTab.LIVE_MIC -> {
                                LiveMicObjectionScreen(
                                    selectedLanguage = closerState.selectedLanguage,
                                    isMicActive = closerState.isMicActive,
                                    transcriptForeign = closerState.micTranscriptForeign,
                                    transcriptArabic = closerState.micTranscriptArabic,
                                    detectedObjection = closerState.detectedObjection,
                                    selectedTactic = closerState.selectedCounterTactic,
                                    isSpeaking = isSpeaking,
                                    onToggleMic = { closerVm.toggleMicrophone() },
                                    onSimulateObjection = { phrase -> closerVm.toggleMicrophone(phrase) },
                                    onSelectTactic = { closerVm.selectCounterTactic(it) },
                                    onPlayNaturalVoice = { closerVm.playNaturalVoice(it, isSlow = false) },
                                    onPlaySlowVoice = { closerVm.playNaturalVoice(it, isSlow = true) },
                                    onStopAudio = { closerVm.stopAudio() }
                                )
                            }
                            CloserTab.MANUAL_CATALOG -> {
                                ManualQuickCatalogScreen(
                                    selectedLanguage = closerState.selectedLanguage,
                                    onSpeak = { closerVm.playNaturalVoice(it, isSlow = false) }
                                )
                            }
                        }
                    }
                }

                MainAppTab.LIVE_VOICE_COACH -> {
                    LiveMicObjectionScreen(
                        selectedLanguage = closerState.selectedLanguage,
                        isMicActive = closerState.isMicActive,
                        transcriptForeign = closerState.micTranscriptForeign,
                        transcriptArabic = closerState.micTranscriptArabic,
                        detectedObjection = closerState.detectedObjection,
                        selectedTactic = closerState.selectedCounterTactic,
                        isSpeaking = isSpeaking,
                        onToggleMic = { closerVm.toggleMicrophone() },
                        onSimulateObjection = { phrase -> closerVm.toggleMicrophone(phrase) },
                        onSelectTactic = { closerVm.selectCounterTactic(it) },
                        onPlayNaturalVoice = { closerVm.playNaturalVoice(it, isSlow = false) },
                        onPlaySlowVoice = { closerVm.playNaturalVoice(it, isSlow = true) },
                        onStopAudio = { closerVm.stopAudio() }
                    )
                }

                MainAppTab.PRACTICE_LESSONS -> {
                    val currentLessons = universityVm.getTrackLessons()
                    CurriculumScreen(
                        selectedLevel = universityState.selectedLevel,
                        selectedTrack = universityState.selectedTrack,
                        lessons = currentLessons,
                        onSelectLevel = { universityVm.selectLevel(it) },
                        onStartLesson = { lesson ->
                            universityVm.openLesson(lesson)
                            activeMainTab = MainAppTab.ACADEMY_HOME
                        },
                        onClaimCertificate = { universityVm.claimUniversityCertificate() }
                    )
                }

                MainAppTab.PROFILE_STATS -> {
                    StudentProfileScreen(
                        studentProfile = studentProfileState,
                        certificates = certificatesState
                    )
                }
            }
        }
    }
}

@Composable
fun CloserSubNav(
    currentSubTab: CloserTab,
    onSelectSubTab: (CloserTab) -> Unit
) {
    Surface(
        color = LightSurface,
        border = BorderStroke(1.dp, LightBorder),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            SubTabPill(
                title = "مسح ومقارنة الأسعار",
                icon = Icons.Default.CameraAlt,
                isSelected = currentSubTab == CloserTab.SCAN_BENCHMARK,
                onClick = { onSelectSubTab(CloserTab.SCAN_BENCHMARK) }
            )
            SubTabPill(
                title = "إقناع بـ 400€",
                icon = Icons.Default.Diamond,
                isSelected = currentSubTab == CloserTab.CLOSING_PITCH,
                onClick = { onSelectSubTab(CloserTab.CLOSING_PITCH) }
            )
            SubTabPill(
                title = "جمل المحل",
                icon = Icons.Default.ShoppingBag,
                isSelected = currentSubTab == CloserTab.MANUAL_CATALOG,
                onClick = { onSelectSubTab(CloserTab.MANUAL_CATALOG) }
            )
        }
    }
}

@Composable
private fun SubTabPill(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) EmeraldLightBg else Color.Transparent,
        border = if (isSelected) BorderStroke(1.dp, EmeraldPrimary) else null,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) EmeraldPrimary else TextMuted,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = title,
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) EmeraldDark else TextMuted
            )
        }
    }
}

@Composable
fun FloatingDockBottomNav(
    currentTab: MainAppTab,
    onSelectTab: (MainAppTab) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Floating Pill Dock Container (Matching Image 3 & Image 1 bottom docks)
        Surface(
            shape = RoundedCornerShape(32.dp),
            color = LightSurface,
            border = BorderStroke(1.5.dp, LightBorder),
            shadowElevation = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .testTag("floating_dock_bottom_nav")
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tab 1: Home / Academy
                DockNavItem(
                    title = "الرئيسية",
                    icon = Icons.Default.Home,
                    isSelected = currentTab == MainAppTab.ACADEMY_HOME,
                    onClick = { onSelectTab(MainAppTab.ACADEMY_HOME) }
                )

                // Tab 2: 400€ Closer & Scan
                DockNavItem(
                    title = "صفقة 400€",
                    icon = Icons.Default.Diamond,
                    isSelected = currentTab == MainAppTab.CLOSER_DEAL,
                    onClick = { onSelectTab(MainAppTab.CLOSER_DEAL) }
                )

                // Spacer for Center Floating Mic
                Spacer(modifier = Modifier.size(54.dp))

                // Tab 4: Curriculum / Practice
                DockNavItem(
                    title = "التمارين",
                    icon = Icons.Default.School,
                    isSelected = currentTab == MainAppTab.PRACTICE_LESSONS,
                    onClick = { onSelectTab(MainAppTab.PRACTICE_LESSONS) }
                )

                // Tab 5: Profile / Stats
                DockNavItem(
                    title = "إنجازاتي",
                    icon = Icons.Default.Person,
                    isSelected = currentTab == MainAppTab.PROFILE_STATS,
                    onClick = { onSelectTab(MainAppTab.PROFILE_STATS) }
                )
            }
        }

        // CENTER FLOATING GLOWING MICROPHONE BUTTON (Elevated circle from mockups)
        Box(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .size(58.dp)
                .shadow(elevation = 12.dp, shape = CircleShape)
                .clip(CircleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(EmeraldGradientStart, EmeraldGradientEnd)
                    )
                )
                .clickable { onSelectTab(MainAppTab.LIVE_VOICE_COACH) }
                .testTag("center_floating_mic_button"),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "المدرب الصوتي المباشر",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
private fun DockNavItem(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = if (isSelected) EmeraldPrimary else TextMuted,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = title,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
            color = if (isSelected) EmeraldPrimary else TextMuted
        )
    }
}

