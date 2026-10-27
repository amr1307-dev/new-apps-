package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.CustomProductEntity
import com.example.data.model.FieldTrialLog
import com.example.data.model.SavedWordEntity
import com.example.data.model.StudentLessonProgressEntity
import com.example.data.model.StudentProfileEntity
import com.example.data.model.TrainingProgress
import com.example.data.model.UniversityCertificateEntity

@Database(
    entities = [
        StudentLessonProgressEntity::class,
        StudentProfileEntity::class,
        SavedWordEntity::class,
        UniversityCertificateEntity::class,
        FieldTrialLog::class,
        TrainingProgress::class,
        CustomProductEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentProgressDao(): StudentProgressDao
    abstract fun studentProfileDao(): StudentProfileDao
    abstract fun savedVocabDao(): SavedVocabDao
    abstract fun certificateDao(): CertificateDao

    abstract fun fieldTrialDao(): FieldTrialDao
    abstract fun trainingDao(): TrainingDao
    abstract fun customProductDao(): CustomProductDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "language_university_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
