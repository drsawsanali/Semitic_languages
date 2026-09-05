package com.example.core.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val id: String, // e.g., "chapter_1", "inscription_mesha"
    val itemType: String, // "chapter", "inscription", "lexicon", "site"
    val title: String,
    val subtitle: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "reading_progress")
data class ReadingProgressEntity(
    @PrimaryKey val chapterId: String,
    val languageId: String,
    val percentCompleted: Float,
    val lastReadTimestamp: Long = System.currentTimeMillis(),
    val notesText: String = ""
)

@Entity(tableName = "quiz_records")
data class QuizScoreEntity(
    @PrimaryKey(autoGenerate = true) val recordId: Long = 0,
    val category: String,
    val score: Int,
    val totalQuestions: Int,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "flashcard_progress")
data class FlashcardProgressEntity(
    @PrimaryKey val cardId: String,
    val boxLevel: Int, // 0=New, 1=Review, 2=Mastered
    val lastReviewedTimestamp: Long = System.currentTimeMillis()
)

@Dao
interface AtlasDao {
    @Query("SELECT * FROM bookmarks ORDER BY timestamp DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun deleteBookmark(id: String)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE id = :id)")
    fun isBookmarked(id: String): Flow<Boolean>

    @Query("SELECT * FROM reading_progress")
    fun getAllReadingProgress(): Flow<List<ReadingProgressEntity>>

    @Query("SELECT * FROM reading_progress WHERE chapterId = :chapterId")
    suspend fun getProgressForChapter(chapterId: String): ReadingProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReadingProgress(progress: ReadingProgressEntity)

    @Query("SELECT * FROM quiz_records ORDER BY timestamp DESC LIMIT 20")
    fun getRecentQuizScores(): Flow<List<QuizScoreEntity>>

    @Insert
    suspend fun insertQuizScore(score: QuizScoreEntity)

    @Query("SELECT * FROM flashcard_progress")
    fun getFlashcardProgressList(): Flow<List<FlashcardProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFlashcardProgress(progress: FlashcardProgressEntity)
}

@Database(
    entities = [
        BookmarkEntity::class,
        ReadingProgressEntity::class,
        QuizScoreEntity::class,
        FlashcardProgressEntity::class,
        SemiticLanguageEntity::class,
        CivilizationMetadataEntity::class,
        InscriptionEntity::class,
        ArchaeologicalSiteEntity::class,
        ChronologyEventEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun atlasDao(): AtlasDao
    abstract fun encyclopediaDao(): SemiticEncyclopediaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "semitic_atlas_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
