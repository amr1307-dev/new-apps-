package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.model.AcademicLevel
import com.example.data.model.InteractiveLabScenario
import com.example.data.model.Language
import com.example.data.model.Lesson
import com.example.data.model.SavedWordEntity
import com.example.data.model.StudentLessonProgressEntity
import com.example.data.model.StudentProfileEntity
import com.example.data.model.UniversityCertificateEntity
import com.example.data.model.VocabWord
import com.example.data.repository.UniversityCurriculumData
import com.example.data.repository.UniversityRepository
import com.example.util.TtsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class UniversityNavTab(val titleAr: String, val emoji: String) {
    DASHBOARD("الرئيسية", "🏛️"),
    CURRICULUM("المناهج", "📚"),
    SPEAKING_LAB("مختبر المحادثة", "🎙️"),
    VOCAB_VAULT("حقيبة الكلمات", "🧠"),
    PROFILE("السجل الجامعي", "🎓")
}

enum class LessonTab(val titleAr: String, val emoji: String) {
    CONCEPTS("الشرح والقواعد", "📖"),
    VOCABULARY("المفردات والنطق", "🗣️"),
    DIALOGUE("المحادثة الواقعية", "💬"),
    QUIZ("اختبار المحاضرة", "✍️")
}

data class UniversityUiState(
    val selectedTrack: Language = Language.DEFAULT, // German as requested
    val selectedLevel: AcademicLevel = AcademicLevel.A1,
    val activeNavTab: UniversityNavTab = UniversityNavTab.DASHBOARD,
    val activeLesson: Lesson? = null,
    val activeLessonTab: LessonTab = LessonTab.CONCEPTS,
    val activeLabScenario: InteractiveLabScenario? = null,
    val activeScenarioStep: Int = 0,
    val isSlowAudio: Boolean = false,
    val quizAnswers: Map<String, Int> = emptyMap(),
    val isQuizSubmitted: Boolean = false,
    val quizScorePercent: Int = 0,
    val celebrationDialogOpen: Boolean = false,
    val searchFilter: String = ""
)

class UniversityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UniversityRepository(AppDatabase.getInstance(application))
    val ttsManager = TtsManager(application)

    private val _uiState = MutableStateFlow(UniversityUiState())
    val uiState: StateFlow<UniversityUiState> = _uiState.asStateFlow()

    val studentProfile: StateFlow<StudentProfileEntity?> = repository.getStudentProfile()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val savedWords: StateFlow<List<SavedWordEntity>> = repository.getSavedWords()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val certificates: StateFlow<List<UniversityCertificateEntity>> = repository.getCertificates()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            repository.initDefaultProfileIfNeeded()
        }
    }

    fun selectTrack(language: Language) {
        _uiState.value = _uiState.value.copy(
            selectedTrack = language,
            activeLesson = null,
            activeLabScenario = null
        )
        ttsManager.stop()
    }

    fun selectLevel(level: AcademicLevel) {
        _uiState.value = _uiState.value.copy(selectedLevel = level)
    }

    fun setNavTab(tab: UniversityNavTab) {
        _uiState.value = _uiState.value.copy(
            activeNavTab = tab,
            activeLesson = null,
            activeLabScenario = null
        )
    }

    fun openLesson(lesson: Lesson) {
        _uiState.value = _uiState.value.copy(
            activeLesson = lesson,
            activeLessonTab = LessonTab.CONCEPTS,
            quizAnswers = emptyMap(),
            isQuizSubmitted = false,
            quizScorePercent = 0
        )
    }

    fun closeLesson() {
        _uiState.value = _uiState.value.copy(activeLesson = null)
        ttsManager.stop()
    }

    fun setLessonTab(tab: LessonTab) {
        _uiState.value = _uiState.value.copy(activeLessonTab = tab)
    }

    fun openLabScenario(scenario: InteractiveLabScenario) {
        _uiState.value = _uiState.value.copy(
            activeLabScenario = scenario,
            activeScenarioStep = 0
        )
    }

    fun closeLabScenario() {
        _uiState.value = _uiState.value.copy(activeLabScenario = null)
        ttsManager.stop()
    }

    fun nextScenarioStep() {
        val scenario = _uiState.value.activeLabScenario ?: return
        if (_uiState.value.activeScenarioStep < scenario.dialogueRounds.size - 1) {
            _uiState.value = _uiState.value.copy(
                activeScenarioStep = _uiState.value.activeScenarioStep + 1
            )
        }
    }

    fun prevScenarioStep() {
        if (_uiState.value.activeScenarioStep > 0) {
            _uiState.value = _uiState.value.copy(
                activeScenarioStep = _uiState.value.activeScenarioStep - 1
            )
        }
    }

    fun selectQuizAnswer(questionId: String, optionIndex: Int) {
        val currentAnswers = _uiState.value.quizAnswers.toMutableMap()
        currentAnswers[questionId] = optionIndex
        _uiState.value = _uiState.value.copy(quizAnswers = currentAnswers)
    }

    fun submitQuiz() {
        val lesson = _uiState.value.activeLesson ?: return
        val questions = lesson.quizQuestions
        if (questions.isEmpty()) return

        var correctCount = 0
        questions.forEach { q ->
            val userSelected = _uiState.value.quizAnswers[q.id]
            if (userSelected == q.correctIndex) {
                correctCount++
            }
        }

        val scorePct = (correctCount.toFloat() / questions.size * 100).toInt()
        val stars = when {
            scorePct >= 90 -> 3
            scorePct >= 70 -> 2
            scorePct >= 50 -> 1
            else -> 0
        }

        _uiState.value = _uiState.value.copy(
            isQuizSubmitted = true,
            quizScorePercent = scorePct,
            celebrationDialogOpen = stars >= 2
        )

        viewModelScope.launch {
            repository.saveLessonCompletion(
                lessonId = lesson.id,
                trackCode = lesson.trackCode,
                scorePercent = scorePct,
                stars = stars
            )
        }
    }

    fun dismissCelebration() {
        _uiState.value = _uiState.value.copy(celebrationDialogOpen = false)
    }

    fun toggleSlowAudio() {
        _uiState.value = _uiState.value.copy(isSlowAudio = !_uiState.value.isSlowAudio)
    }

    fun speak(text: String, isSlowOverride: Boolean? = null) {
        val slow = isSlowOverride ?: _uiState.value.isSlowAudio
        ttsManager.speak(
            text = text,
            languageCode = _uiState.value.selectedTrack.code,
            isSlow = slow
        )
    }

    fun stopAudio() {
        ttsManager.stop()
    }

    fun saveWord(word: VocabWord) {
        viewModelScope.launch {
            repository.saveWordToVault(word, _uiState.value.selectedTrack.code)
        }
    }

    fun deleteWord(id: String) {
        viewModelScope.launch {
            repository.deleteSavedWord(id)
        }
    }

    fun markWordMastered(id: String) {
        viewModelScope.launch {
            repository.markWordMastered(id)
        }
    }

    fun getTrackLessons(): List<Lesson> {
        val all = repository.getLessonsForTrack(_uiState.value.selectedTrack.code)
        return all.filter { it.level == _uiState.value.selectedLevel }
    }

    fun getAllLabScenarios(): List<InteractiveLabScenario> {
        return repository.getLabScenarios()
    }

    fun claimUniversityCertificate() {
        viewModelScope.launch {
            repository.awardCertificate(
                levelCode = _uiState.value.selectedLevel.code,
                trackCode = _uiState.value.selectedTrack.code
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        ttsManager.shutdown()
    }
}
