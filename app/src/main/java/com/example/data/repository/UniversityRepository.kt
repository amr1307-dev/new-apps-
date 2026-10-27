package com.example.data.repository

import com.example.data.db.AppDatabase
import com.example.data.model.InteractiveLabScenario
import com.example.data.model.Lesson
import com.example.data.model.SavedWordEntity
import com.example.data.model.StudentLessonProgressEntity
import com.example.data.model.StudentProfileEntity
import com.example.data.model.UniversityCertificateEntity
import com.example.data.model.VocabWord
import kotlinx.coroutines.flow.Flow

class UniversityRepository(private val db: AppDatabase) {

    fun getLessonsForTrack(trackCode: String): List<Lesson> {
        val allLessons = UniversityCurriculumData.LESSONS
        val filtered = allLessons.filter { it.trackCode == trackCode }
        return if (filtered.isNotEmpty()) filtered else allLessons.filter { it.trackCode == "de" }
    }

    fun getLessonById(lessonId: String): Lesson? {
        return UniversityCurriculumData.LESSONS.find { it.id == lessonId }
            ?: UniversityCurriculumData.LESSONS.firstOrNull()
    }

    fun getLabScenarios(): List<InteractiveLabScenario> {
        return UniversityCurriculumData.LAB_SCENARIOS
    }

    fun getTrackProgress(trackCode: String): Flow<List<StudentLessonProgressEntity>> {
        return db.studentProgressDao().getTrackProgress(trackCode)
    }

    suspend fun saveLessonCompletion(lessonId: String, trackCode: String, scorePercent: Int, stars: Int) {
        val progress = StudentLessonProgressEntity(
            lessonId = lessonId,
            trackCode = trackCode,
            isCompleted = true,
            scorePercent = scorePercent,
            stars = stars,
            completedAt = System.currentTimeMillis()
        )
        db.studentProgressDao().saveLessonProgress(progress)

        // Award XP and study time
        val xpBonus = 50 + (stars * 20)
        db.studentProfileDao().addXp(xpBonus)
        db.studentProfileDao().addStudyMinutes(12)
    }

    fun getStudentProfile(): Flow<StudentProfileEntity?> {
        return db.studentProfileDao().getProfile()
    }

    suspend fun initDefaultProfileIfNeeded() {
        // Will populate if empty
        val defaultProfile = StudentProfileEntity(
            id = 1,
            totalXp = 240,
            currentStreakDays = 5,
            rankTitleAr = "طالب جامعي متميز",
            dailyGoalMinutes = 15,
            minutesStudiedToday = 10
        )
        db.studentProfileDao().updateProfile(defaultProfile)
    }

    fun getSavedWords(): Flow<List<SavedWordEntity>> {
        return db.savedVocabDao().getAllSavedWords()
    }

    suspend fun saveWordToVault(word: VocabWord, trackCode: String) {
        val entity = SavedWordEntity(
            id = "${word.id}_${System.currentTimeMillis()}",
            trackCode = trackCode,
            foreignWord = word.foreignWord,
            arabicMeaning = word.arabicMeaning,
            arabicPhonetics = word.arabicPhonetics,
            reviewCount = 1,
            isMastered = false
        )
        db.savedVocabDao().saveWord(entity)
    }

    suspend fun deleteSavedWord(id: String) {
        db.savedVocabDao().deleteWord(id)
    }

    suspend fun markWordMastered(id: String) {
        db.savedVocabDao().markWordMastered(id)
        db.studentProfileDao().addXp(15)
    }

    suspend fun awardCertificate(levelCode: String, trackCode: String) {
        val cert = UniversityCertificateEntity(
            certificateId = "cert_${trackCode}_${levelCode}_${System.currentTimeMillis()}",
            trackCode = trackCode,
            levelCode = levelCode,
            studentName = "طالب أكاديمية اللغات المتفوق",
            scoreGrade = "امتياز مع مرتبة الشرف (A+)"
        )
        db.certificateDao().awardCertificate(cert)
        db.studentProfileDao().addXp(200)
    }

    fun getCertificates(): Flow<List<UniversityCertificateEntity>> {
        return db.certificateDao().getAllCertificates()
    }
}
