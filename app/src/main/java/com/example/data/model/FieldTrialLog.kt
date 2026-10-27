package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "field_trial_logs")
data class FieldTrialLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val languageCode: String,
    val productCategory: String,
    val productName: String,
    val actionUsed: String, // "explain", "question", "price", "practice"
    val customerUnderstood: Boolean = true,
    val speedSeconds: Int = 3, // Seconds taken to access phrase
    val workerNote: String = ""
)

@Entity(tableName = "training_progress")
data class TrainingProgress(
    @PrimaryKey
    val phraseId: String,
    val languageCode: String,
    val timesPracticed: Int = 0,
    val isMastered: Boolean = false,
    val lastPracticedTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "custom_products")
data class CustomProductEntity(
    @PrimaryKey
    val id: String,
    val nameAr: String,
    val categoryId: String,
    val priceLocal: Double,
    val priceEur: Double,
    val verifiedFactsRaw: String, // newline separated
    val pitchGerman: String,
    val pitchEnglish: String,
    val pitchArabicPhonetics: String,
    val createdAt: Long = System.currentTimeMillis()
)
