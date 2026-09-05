package com.example.core.database

import com.example.core.data.ArchaeologicalSitesData
import com.example.core.data.CivilizationsData
import com.example.core.data.InscriptionsData
import com.example.core.data.LearningData
import com.example.core.data.SemiticLanguagesData
import com.example.core.model.ArchaeologicalSite
import com.example.core.model.ChronologyEvent
import com.example.core.model.CivilizationMetadata
import com.example.core.model.InscriptionArtifact
import com.example.core.model.SemiticLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Clean Architecture Repository providing local-first Room access for
 * Semitic languages, civilizations metadata, inscriptions, and cultural/geographical metadata.
 */
class SemiticEncyclopediaRepository(
    private val encyclopediaDao: SemiticEncyclopediaDao
) {

    // === OBSERVABLE FLOWS ===
    val allLanguages: Flow<List<SemiticLanguage>> = encyclopediaDao.getAllLanguages().map { list ->
        list.map { it.toDomainModel() }
    }

    val favoriteLanguages: Flow<List<SemiticLanguage>> = encyclopediaDao.getFavoriteLanguages().map { list ->
        list.map { it.toDomainModel() }
    }

    val allInscriptions: Flow<List<InscriptionArtifact>> = encyclopediaDao.getAllInscriptions().map { list ->
        list.map { it.toDomainModel() }
    }

    val favoriteInscriptions: Flow<List<InscriptionArtifact>> = encyclopediaDao.getFavoriteInscriptions().map { list ->
        list.map { it.toDomainModel() }
    }

    val allSites: Flow<List<ArchaeologicalSite>> = encyclopediaDao.getAllArchaeologicalSites().map { list ->
        list.map { it.toDomainModel() }
    }

    val allChronologyEvents: Flow<List<ChronologyEvent>> = encyclopediaDao.getAllChronologyEvents().map { list ->
        list.map { it.toDomainModel() }
    }

    val allCivilizations: Flow<List<CivilizationMetadata>> = encyclopediaDao.getAllCivilizations().map { list ->
        list.map { it.toDomainModel() }
    }

    fun getLanguagesByBranch(branchId: String): Flow<List<SemiticLanguage>> =
        encyclopediaDao.getLanguagesByBranch(branchId).map { list -> list.map { it.toDomainModel() } }

    fun getLanguageById(id: String): Flow<SemiticLanguage?> =
        encyclopediaDao.getLanguageById(id).map { it?.toDomainModel() }

    fun getInscriptionsByLanguage(languageId: String): Flow<List<InscriptionArtifact>> =
        encyclopediaDao.getInscriptionsByLanguage(languageId).map { list -> list.map { it.toDomainModel() } }

    fun getInscriptionsByBranch(branchId: String): Flow<List<InscriptionArtifact>> =
        encyclopediaDao.getInscriptionsByBranch(branchId).map { list -> list.map { it.toDomainModel() } }

    fun getInscriptionById(id: String): Flow<InscriptionArtifact?> =
        encyclopediaDao.getInscriptionById(id).map { it?.toDomainModel() }

    fun getCivilizationById(id: String): Flow<CivilizationMetadata?> =
        encyclopediaDao.getCivilizationById(id).map { it?.toDomainModel() }

    fun getCivilizationByLanguageId(languageId: String): Flow<CivilizationMetadata?> =
        encyclopediaDao.getCivilizationByLanguageId(languageId).map { it?.toDomainModel() }

    fun getLanguageWithInscriptions(languageId: String): Flow<LanguageWithInscriptions?> =
        encyclopediaDao.getLanguageWithInscriptions(languageId)

    fun getAllLanguagesWithInscriptions(): Flow<List<LanguageWithInscriptions>> =
        encyclopediaDao.getAllLanguagesWithInscriptions()

    fun getLanguageWithCivilizationAndInscriptions(languageId: String): Flow<LanguageWithCivilizationMetadata?> =
        encyclopediaDao.getLanguageWithCivilizationAndInscriptions(languageId)

    fun getAllLanguagesWithCivilizationAndInscriptions(): Flow<List<LanguageWithCivilizationMetadata>> =
        encyclopediaDao.getAllLanguagesWithCivilizationAndInscriptions()

    fun getCivilizationWithInscriptions(civilizationId: String): Flow<CivilizationWithInscriptions?> =
        encyclopediaDao.getCivilizationWithInscriptions(civilizationId)

    fun searchLanguages(query: String): Flow<List<SemiticLanguage>> =
        encyclopediaDao.searchLanguages(query).map { list -> list.map { it.toDomainModel() } }

    fun searchInscriptions(query: String): Flow<List<InscriptionArtifact>> =
        encyclopediaDao.searchInscriptions(query).map { list -> list.map { it.toDomainModel() } }

    fun searchCivilizations(query: String): Flow<List<CivilizationMetadata>> =
        encyclopediaDao.searchCivilizations(query).map { list -> list.map { it.toDomainModel() } }

    // === USER MUTATIONS ===
    suspend fun setLanguageFavorite(id: String, isFavorite: Boolean) {
        encyclopediaDao.setLanguageFavorite(id, isFavorite)
    }

    suspend fun updateLanguageNotes(id: String, notes: String) {
        encyclopediaDao.updateLanguageNotes(id, notes)
    }

    suspend fun setInscriptionFavorite(id: String, isFavorite: Boolean) {
        encyclopediaDao.setInscriptionFavorite(id, isFavorite)
    }

    suspend fun updateInscriptionNotes(id: String, notes: String) {
        encyclopediaDao.updateInscriptionUserNotes(id, notes)
    }

    suspend fun setSiteSaved(id: String, saved: Boolean) {
        encyclopediaDao.setSiteSaved(id, saved)
    }

    // === PRE-POPULATION / LOCAL SEEDING ===
    suspend fun initializeDatabaseIfEmpty() {
        if (encyclopediaDao.getLanguagesCount() == 0) {
            val languageEntities = SemiticLanguagesData.ALL_LANGUAGES.map {
                SemiticLanguageEntity.fromDomainModel(it)
            }
            encyclopediaDao.insertLanguages(languageEntities)
        }

        if (encyclopediaDao.getCivilizationsCount() == 0) {
            val civEntities = CivilizationsData.ALL_CIVILIZATIONS.map {
                CivilizationMetadataEntity.fromDomainModel(it)
            }
            encyclopediaDao.insertCivilizations(civEntities)
        }

        if (encyclopediaDao.getInscriptionsCount() == 0) {
            val inscriptionEntities = InscriptionsData.ALL_INSCRIPTIONS.map {
                InscriptionEntity.fromDomainModel(it)
            }
            encyclopediaDao.insertInscriptions(inscriptionEntities)
        }

        if (encyclopediaDao.getSitesCount() == 0) {
            val siteEntities = ArchaeologicalSitesData.ALL_SITES.map {
                ArchaeologicalSiteEntity.fromDomainModel(it)
            }
            encyclopediaDao.insertSites(siteEntities)
        }

        if (encyclopediaDao.getChronologyCount() == 0) {
            val eventEntities = LearningData.TIMELINE_EVENTS.map {
                ChronologyEventEntity.fromDomainModel(it)
            }
            encyclopediaDao.insertChronologyEvents(eventEntities)
        }
    }
}
