package com.example.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Semitic Languages, Inscriptions, and Cultural Metadata (Sites, Chronology).
 */
@Dao
interface SemiticEncyclopediaDao {

    // === LANGUAGES ===
    @Query("SELECT * FROM semitic_languages ORDER BY branchId ASC, id ASC")
    fun getAllLanguages(): Flow<List<SemiticLanguageEntity>>

    @Query("SELECT * FROM semitic_languages WHERE branchId = :branchId")
    fun getLanguagesByBranch(branchId: String): Flow<List<SemiticLanguageEntity>>

    @Query("SELECT * FROM semitic_languages WHERE id = :id LIMIT 1")
    fun getLanguageById(id: String): Flow<SemiticLanguageEntity?>

    @Query("SELECT * FROM semitic_languages WHERE id = :id LIMIT 1")
    suspend fun getLanguageByIdSync(id: String): SemiticLanguageEntity?

    @Query("SELECT * FROM semitic_languages WHERE isUserFavorite = 1")
    fun getFavoriteLanguages(): Flow<List<SemiticLanguageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLanguages(languages: List<SemiticLanguageEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLanguage(language: SemiticLanguageEntity)

    @Update
    suspend fun updateLanguage(language: SemiticLanguageEntity)

    @Query("UPDATE semitic_languages SET isUserFavorite = :isFavorite WHERE id = :id")
    suspend fun setLanguageFavorite(id: String, isFavorite: Boolean)

    @Query("UPDATE semitic_languages SET customNotes = :notes WHERE id = :id")
    suspend fun updateLanguageNotes(id: String, notes: String)

    @Query("SELECT COUNT(*) FROM semitic_languages")
    suspend fun getLanguagesCount(): Int

    // === INSCRIPTIONS ===
    @Query("SELECT * FROM inscriptions ORDER BY branchId ASC, id ASC")
    fun getAllInscriptions(): Flow<List<InscriptionEntity>>

    @Query("SELECT * FROM inscriptions WHERE languageId = :languageId")
    fun getInscriptionsByLanguage(languageId: String): Flow<List<InscriptionEntity>>

    @Query("SELECT * FROM inscriptions WHERE branchId = :branchId")
    fun getInscriptionsByBranch(branchId: String): Flow<List<InscriptionEntity>>

    @Query("SELECT * FROM inscriptions WHERE id = :id LIMIT 1")
    fun getInscriptionById(id: String): Flow<InscriptionEntity?>

    @Query("SELECT * FROM inscriptions WHERE id = :id LIMIT 1")
    suspend fun getInscriptionByIdSync(id: String): InscriptionEntity?

    @Query("SELECT * FROM inscriptions WHERE isUserFavorite = 1")
    fun getFavoriteInscriptions(): Flow<List<InscriptionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInscriptions(inscriptions: List<InscriptionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInscription(inscription: InscriptionEntity)

    @Query("UPDATE inscriptions SET isUserFavorite = :isFavorite WHERE id = :id")
    suspend fun setInscriptionFavorite(id: String, isFavorite: Boolean)

    @Query("UPDATE inscriptions SET userTranscriptionNotes = :notes WHERE id = :id")
    suspend fun updateInscriptionUserNotes(id: String, notes: String)

    @Query("SELECT COUNT(*) FROM inscriptions")
    suspend fun getInscriptionsCount(): Int

    // === CULTURAL METADATA: ARCHAEOLOGICAL SITES ===
    @Query("SELECT * FROM archaeological_sites ORDER BY region ASC, nameAr ASC")
    fun getAllArchaeologicalSites(): Flow<List<ArchaeologicalSiteEntity>>

    @Query("SELECT * FROM archaeological_sites WHERE id = :id LIMIT 1")
    fun getSiteById(id: String): Flow<ArchaeologicalSiteEntity?>

    @Query("SELECT * FROM archaeological_sites WHERE associatedBranchId = :branchId")
    fun getSitesByBranch(branchId: String): Flow<List<ArchaeologicalSiteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSites(sites: List<ArchaeologicalSiteEntity>)

    @Query("UPDATE archaeological_sites SET isVisitedOrSaved = :saved WHERE id = :id")
    suspend fun setSiteSaved(id: String, saved: Boolean)

    @Query("SELECT COUNT(*) FROM archaeological_sites")
    suspend fun getSitesCount(): Int

    // === CULTURAL METADATA: CHRONOLOGY EVENTS ===
    @Query("SELECT * FROM chronology_events ORDER BY yearBceOrCe ASC")
    fun getAllChronologyEvents(): Flow<List<ChronologyEventEntity>>

    @Query("SELECT * FROM chronology_events WHERE branchId = :branchId ORDER BY yearBceOrCe ASC")
    fun getChronologyEventsByBranch(branchId: String): Flow<List<ChronologyEventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChronologyEvents(events: List<ChronologyEventEntity>)

    @Query("SELECT COUNT(*) FROM chronology_events")
    suspend fun getChronologyCount(): Int

    // === SEARCH ACROSS TABLES ===
    @Query("""
        SELECT * FROM semitic_languages 
        WHERE nameAr LIKE '%' || :query || '%' 
           OR nameEn LIKE '%' || :query || '%' 
           OR geographicalRegion LIKE '%' || :query || '%'
           OR sampleTextOriginal LIKE '%' || :query || '%'
    """)
    fun searchLanguages(query: String): Flow<List<SemiticLanguageEntity>>

    @Query("""
        SELECT * FROM inscriptions 
        WHERE titleAr LIKE '%' || :query || '%' 
           OR titleEn LIKE '%' || :query || '%' 
           OR scriptTextOriginal LIKE '%' || :query || '%'
           OR transliteration LIKE '%' || :query || '%'
           OR translationAr LIKE '%' || :query || '%'
    """)
    fun searchInscriptions(query: String): Flow<List<InscriptionEntity>>

    // === CIVILIZATIONS & KINGDOMS METADATA ===
    @Query("SELECT * FROM civilization_metadata ORDER BY branchId ASC, id ASC")
    fun getAllCivilizations(): Flow<List<CivilizationMetadataEntity>>

    @Query("SELECT * FROM civilization_metadata WHERE id = :id LIMIT 1")
    fun getCivilizationById(id: String): Flow<CivilizationMetadataEntity?>

    @Query("SELECT * FROM civilization_metadata WHERE associatedLanguageId = :languageId LIMIT 1")
    fun getCivilizationByLanguageId(languageId: String): Flow<CivilizationMetadataEntity?>

    @Query("SELECT * FROM civilization_metadata WHERE branchId = :branchId")
    fun getCivilizationsByBranch(branchId: String): Flow<List<CivilizationMetadataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCivilizations(civilizations: List<CivilizationMetadataEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCivilization(civilization: CivilizationMetadataEntity)

    @Query("SELECT COUNT(*) FROM civilization_metadata")
    suspend fun getCivilizationsCount(): Int

    @Query("""
        SELECT * FROM civilization_metadata 
        WHERE nameAr LIKE '%' || :query || '%' 
           OR capitalCityAr LIKE '%' || :query || '%' 
           OR geographicCoreAr LIKE '%' || :query || '%'
           OR notableAchievementsAr LIKE '%' || :query || '%'
    """)
    fun searchCivilizations(query: String): Flow<List<CivilizationMetadataEntity>>

    // === ROOM RELATIONS (TRANSACTIONAL JOINS) ===
    @androidx.room.Transaction
    @Query("SELECT * FROM semitic_languages WHERE id = :languageId LIMIT 1")
    fun getLanguageWithInscriptions(languageId: String): Flow<LanguageWithInscriptions?>

    @androidx.room.Transaction
    @Query("SELECT * FROM semitic_languages ORDER BY branchId ASC, id ASC")
    fun getAllLanguagesWithInscriptions(): Flow<List<LanguageWithInscriptions>>

    @androidx.room.Transaction
    @Query("SELECT * FROM semitic_languages WHERE id = :languageId LIMIT 1")
    fun getLanguageWithCivilizationAndInscriptions(languageId: String): Flow<LanguageWithCivilizationMetadata?>

    @androidx.room.Transaction
    @Query("SELECT * FROM semitic_languages ORDER BY branchId ASC, id ASC")
    fun getAllLanguagesWithCivilizationAndInscriptions(): Flow<List<LanguageWithCivilizationMetadata>>

    @androidx.room.Transaction
    @Query("SELECT * FROM civilization_metadata WHERE id = :civilizationId LIMIT 1")
    fun getCivilizationWithInscriptions(civilizationId: String): Flow<CivilizationWithInscriptions?>
}
