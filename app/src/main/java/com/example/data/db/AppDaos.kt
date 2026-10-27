package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.CustomProductEntity
import com.example.data.model.FieldTrialLog
import com.example.data.model.SavedWordEntity
import com.example.data.model.StudentLessonProgressEntity
import com.example.data.model.StudentProfileEntity
import com.example.data.model.TrainingProgress
import com.example.data.model.UniversityCertificateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLessonProgress(progress: StudentLessonProgressEntity)

    @Query("SELECT * FROM student_lesson_progress WHERE trackCode = :trackCode")
    fun getTrackProgress(trackCode: String): Flow<List<StudentLessonProgressEntity>>

    @Query("SELECT * FROM student_lesson_progress WHERE lessonId = :lessonId")
    suspend fun getLessonProgress(lessonId: String): StudentLessonProgressEntity?

    @Query("SELECT COUNT(*) FROM student_lesson_progress WHERE isCompleted = 1")
    fun getCompletedLessonsCount(): Flow<Int>
}

@Dao
interface StudentProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProfile(profile: StudentProfileEntity)

    @Query("SELECT * FROM student_profile WHERE id = 1")
    fun getProfile(): Flow<StudentProfileEntity?>

    @Query("UPDATE student_profile SET totalXp = totalXp + :xpBonus WHERE id = 1")
    suspend fun addXp(xpBonus: Int)

    @Query("UPDATE student_profile SET minutesStudiedToday = minutesStudiedToday + :minutes WHERE id = 1")
    suspend fun addStudyMinutes(minutes: Int)
}

@Dao
interface SavedVocabDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWord(word: SavedWordEntity)

    @Query("SELECT * FROM saved_words_vault WHERE trackCode = :trackCode ORDER BY savedAt DESC")
    fun getSavedWordsForTrack(trackCode: String): Flow<List<SavedWordEntity>>

    @Query("SELECT * FROM saved_words_vault ORDER BY savedAt DESC")
    fun getAllSavedWords(): Flow<List<SavedWordEntity>>

    @Query("DELETE FROM saved_words_vault WHERE id = :id")
    suspend fun deleteWord(id: String)

    @Query("UPDATE saved_words_vault SET isMastered = 1, reviewCount = reviewCount + 1 WHERE id = :id")
    suspend fun markWordMastered(id: String)
}

@Dao
interface CertificateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun awardCertificate(certificate: UniversityCertificateEntity)

    @Query("SELECT * FROM university_certificates ORDER BY issuedDateMillis DESC")
    fun getAllCertificates(): Flow<List<UniversityCertificateEntity>>
}

// Keep existing field trial and custom products DAOs for compatibility
@Dao
interface FieldTrialDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: FieldTrialLog): Long

    @Query("SELECT * FROM field_trial_logs ORDER BY timestamp DESC")
    fun getAllLogs(): Flow<List<FieldTrialLog>>

    @Query("SELECT COUNT(*) FROM field_trial_logs")
    fun getTotalInteractionsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM field_trial_logs WHERE customerUnderstood = 1")
    fun getSuccessfulInteractionsCount(): Flow<Int>
}

@Dao
interface TrainingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: TrainingProgress)

    @Query("SELECT * FROM training_progress WHERE phraseId = :phraseId AND languageCode = :languageCode")
    suspend fun getProgress(phraseId: String, languageCode: String): TrainingProgress?

    @Query("SELECT * FROM training_progress")
    fun getAllProgress(): Flow<List<TrainingProgress>>
}

@Dao
interface CustomProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomProduct(product: CustomProductEntity)

    @Query("SELECT * FROM custom_products ORDER BY createdAt DESC")
    fun getAllCustomProducts(): Flow<List<CustomProductEntity>>

    @Query("DELETE FROM custom_products WHERE id = :id")
    suspend fun deleteCustomProduct(id: String)
}
