package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_lesson_progress")
data class StudentLessonProgressEntity(
    @PrimaryKey
    val lessonId: String,
    val trackCode: String,
    val isCompleted: Boolean = false,
    val scorePercent: Int = 0,
    val stars: Int = 0,
    val completedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "student_profile")
data class StudentProfileEntity(
    @PrimaryKey
    val id: Int = 1,
    val totalXp: Int = 120,
    val currentStreakDays: Int = 3,
    val lastStudyDateMillis: Long = System.currentTimeMillis(),
    val rankTitleAr: String = "طالب جامعي مجتهد",
    val dailyGoalMinutes: Int = 15,
    val minutesStudiedToday: Int = 8
)

@Entity(tableName = "saved_words_vault")
data class SavedWordEntity(
    @PrimaryKey
    val id: String,
    val trackCode: String,
    val foreignWord: String,
    val arabicMeaning: String,
    val arabicPhonetics: String,
    val reviewCount: Int = 0,
    val isMastered: Boolean = false,
    val savedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "university_certificates")
data class UniversityCertificateEntity(
    @PrimaryKey
    val certificateId: String,
    val trackCode: String,
    val levelCode: String,
    val studentName: String = "طالب الأكاديمية",
    val scoreGrade: String = "امتياز مع مرتبة الشرف (A+)",
    val issuedDateMillis: Long = System.currentTimeMillis()
)
