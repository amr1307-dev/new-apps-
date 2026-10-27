package com.example.data.repository

import com.example.data.db.AppDatabase
import com.example.data.model.CustomProductEntity
import com.example.data.model.FieldTrialLog
import com.example.data.model.PhraseItem
import com.example.data.model.Product
import com.example.data.model.ProductCategory
import com.example.data.model.QuickPhrase
import com.example.data.model.TrainingProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SalesAssistantRepository(private val db: AppDatabase) {

    fun getAllProducts(): Flow<List<Product>> {
        return db.customProductDao().getAllCustomProducts().map { customEntities ->
            val customProducts = customEntities.map { entity ->
                val category = ProductCategory.entries.find { it.id == entity.categoryId } ?: ProductCategory.LEATHER
                val factsList = entity.verifiedFactsRaw.split("\n").filter { it.isNotBlank() }
                Product(
                    id = entity.id,
                    nameAr = entity.nameAr,
                    nameNativeMap = mapOf(
                        "de" to entity.pitchGerman,
                        "en" to entity.pitchEnglish
                    ),
                    category = category,
                    iconEmoji = category.iconEmoji,
                    priceLocal = entity.priceLocal,
                    priceEur = entity.priceEur,
                    priceUsd = entity.priceEur * 1.08,
                    verifiedFacts = factsList,
                    pitch = mapOf(
                        "de" to PhraseItem(
                            foreignText = entity.pitchGerman.ifBlank { "Hochwertiges Produkt aus unserem Laden." },
                            arabicTranslation = entity.nameAr,
                            arabicPhonetics = entity.pitchArabicPhonetics.ifBlank { "هوخ-فيرتيغِس برودوكت" }
                        ),
                        "en" to PhraseItem(
                            foreignText = entity.pitchEnglish.ifBlank { "Premium quality authentic handcrafted item." },
                            arabicTranslation = entity.nameAr,
                            arabicPhonetics = "بريميوم كواليتي أثنتيك آيتم"
                        )
                    ),
                    questions = emptyList(),
                    objections = emptyList(),
                    isCustom = true
                )
            }
            DefaultCatalog.PRODUCTS + customProducts
        }
    }

    fun getQuickPhrases(): List<QuickPhrase> {
        return DefaultCatalog.QUICK_PHRASES
    }

    suspend fun addCustomProduct(
        nameAr: String,
        categoryId: String,
        priceLocal: Double,
        priceEur: Double,
        factsRaw: String,
        pitchGerman: String,
        pitchEnglish: String,
        pitchPhonetics: String
    ) {
        val entity = CustomProductEntity(
            id = "custom_${System.currentTimeMillis()}",
            nameAr = nameAr,
            categoryId = categoryId,
            priceLocal = priceLocal,
            priceEur = priceEur,
            verifiedFactsRaw = factsRaw,
            pitchGerman = pitchGerman,
            pitchEnglish = pitchEnglish,
            pitchArabicPhonetics = pitchPhonetics
        )
        db.customProductDao().insertCustomProduct(entity)
    }

    suspend fun deleteCustomProduct(id: String) {
        db.customProductDao().deleteCustomProduct(id)
    }

    suspend fun logFieldInteraction(
        languageCode: String,
        category: String,
        productName: String,
        actionUsed: String,
        customerUnderstood: Boolean,
        speedSeconds: Int,
        note: String
    ) {
        val log = FieldTrialLog(
            languageCode = languageCode,
            productCategory = category,
            productName = productName,
            actionUsed = actionUsed,
            customerUnderstood = customerUnderstood,
            speedSeconds = speedSeconds,
            workerNote = note
        )
        db.fieldTrialDao().insertLog(log)
    }

    fun getFieldTrialLogs(): Flow<List<FieldTrialLog>> {
        return db.fieldTrialDao().getAllLogs()
    }

    suspend fun recordTrainingPractice(phraseId: String, languageCode: String, markMastered: Boolean = false) {
        val existing = db.trainingDao().getProgress(phraseId, languageCode)
        val newProgress = if (existing != null) {
            existing.copy(
                timesPracticed = existing.timesPracticed + 1,
                isMastered = markMastered || existing.isMastered,
                lastPracticedTimestamp = System.currentTimeMillis()
            )
        } else {
            TrainingProgress(
                phraseId = phraseId,
                languageCode = languageCode,
                timesPracticed = 1,
                isMastered = markMastered,
                lastPracticedTimestamp = System.currentTimeMillis()
            )
        }
        db.trainingDao().saveProgress(newProgress)
    }

    fun getAllTrainingProgress(): Flow<List<TrainingProgress>> {
        return db.trainingDao().getAllProgress()
    }
}
