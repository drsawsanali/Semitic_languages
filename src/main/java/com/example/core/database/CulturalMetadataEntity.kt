package com.example.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.model.ArchaeologicalSite
import com.example.core.model.ChronologyEvent
import com.example.core.model.LanguageBranch

/**
 * Room Entity representing archaeological sites and geographical coordinates of discovery.
 */
@Entity(tableName = "archaeological_sites")
data class ArchaeologicalSiteEntity(
    @PrimaryKey val id: String,
    val nameAr: String,
    val nameEn: String,
    val latitude: Double,
    val longitude: Double,
    val region: String,
    val associatedBranchId: String,
    val associatedLanguagesJson: String,
    val historicalSignificanceAr: String,
    val inscriptionsFoundJson: String,
    val notableArtifactsJson: String,
    val periodDisplay: String,
    val isVisitedOrSaved: Boolean = false
) {
    fun toDomainModel(): ArchaeologicalSite {
        val branch = LanguageBranch.values().find { it.id == associatedBranchId } ?: LanguageBranch.NORTHWEST_SEMITIC
        return ArchaeologicalSite(
            id = id,
            nameAr = nameAr,
            nameEn = nameEn,
            latitude = latitude,
            longitude = longitude,
            region = region,
            associatedBranch = branch,
            associatedLanguages = associatedLanguagesJson.split(DELIMITER).filter { it.isNotBlank() },
            historicalSignificanceAr = historicalSignificanceAr,
            inscriptionsFound = inscriptionsFoundJson.split(DELIMITER).filter { it.isNotBlank() },
            notableArtifacts = notableArtifactsJson.split(DELIMITER).filter { it.isNotBlank() },
            periodDisplay = periodDisplay
        )
    }

    companion object {
        private const val DELIMITER = "|||"

        fun fromDomainModel(model: ArchaeologicalSite, saved: Boolean = false): ArchaeologicalSiteEntity {
            return ArchaeologicalSiteEntity(
                id = model.id,
                nameAr = model.nameAr,
                nameEn = model.nameEn,
                latitude = model.latitude,
                longitude = model.longitude,
                region = model.region,
                associatedBranchId = model.associatedBranch.id,
                associatedLanguagesJson = model.associatedLanguages.joinToString(DELIMITER),
                historicalSignificanceAr = model.historicalSignificanceAr,
                inscriptionsFoundJson = model.inscriptionsFound.joinToString(DELIMITER),
                notableArtifactsJson = model.notableArtifacts.joinToString(DELIMITER),
                periodDisplay = model.periodDisplay,
                isVisitedOrSaved = saved
            )
        }
    }
}

/**
 * Room Entity representing historical chronology and cultural timeline events.
 */
@Entity(tableName = "chronology_events")
data class ChronologyEventEntity(
    @PrimaryKey val id: String,
    val yearBceOrCe: Int,
    val displayDateAr: String,
    val displayDateEn: String,
    val titleAr: String,
    val titleEn: String,
    val descriptionAr: String,
    val descriptionEn: String,
    val eraAr: String,
    val eraEn: String,
    val branchId: String,
    val languageAssociated: String,
    val associatedLanguageId: String,
    val scriptType: String,
    val scriptGlyphSymbol: String,
    val keyInscriptionId: String,
    val keySiteId: String,
    val significanceNotesAr: String
) {
    fun toDomainModel(): ChronologyEvent {
        val branch = LanguageBranch.values().find { it.id == branchId } ?: LanguageBranch.NORTHWEST_SEMITIC
        return ChronologyEvent(
            id = id,
            yearBceOrCe = yearBceOrCe,
            displayDateAr = displayDateAr,
            displayDateEn = displayDateEn,
            titleAr = titleAr,
            titleEn = titleEn,
            descriptionAr = descriptionAr,
            descriptionEn = descriptionEn,
            eraAr = eraAr,
            eraEn = eraEn,
            languageBranch = branch,
            languageAssociated = languageAssociated,
            associatedLanguageId = associatedLanguageId,
            scriptType = scriptType,
            scriptGlyphSymbol = scriptGlyphSymbol,
            keyInscriptionId = keyInscriptionId,
            keySiteId = keySiteId,
            significanceNotesAr = significanceNotesAr
        )
    }

    companion object {
        fun fromDomainModel(model: ChronologyEvent): ChronologyEventEntity {
            return ChronologyEventEntity(
                id = model.id.ifBlank { "chrono_${model.yearBceOrCe}_${model.associatedLanguageId}" },
                yearBceOrCe = model.yearBceOrCe,
                displayDateAr = model.displayDateAr,
                displayDateEn = model.displayDateEn,
                titleAr = model.titleAr,
                titleEn = model.titleEn,
                descriptionAr = model.descriptionAr,
                descriptionEn = model.descriptionEn,
                eraAr = model.eraAr,
                eraEn = model.eraEn,
                branchId = model.languageBranch.id,
                languageAssociated = model.languageAssociated,
                associatedLanguageId = model.associatedLanguageId,
                scriptType = model.scriptType,
                scriptGlyphSymbol = model.scriptGlyphSymbol,
                keyInscriptionId = model.keyInscriptionId,
                keySiteId = model.keySiteId,
                significanceNotesAr = model.significanceNotesAr
            )
        }
    }
}
