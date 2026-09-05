package com.example.core.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.core.model.InscriptionArtifact
import com.example.core.model.LanguageBranch
import com.example.core.model.ScriptType
import com.example.core.model.SpectralAnalysisMode

/**
 * Room Entity for caching and persisting Semitic inscriptions and epigraphic artifacts locally.
 * Linked via languageId to semitic_languages.
 */
@Entity(
    tableName = "inscriptions",
    indices = [
        Index(value = ["languageId"]),
        Index(value = ["branchId"]),
        Index(value = ["scriptTypeId"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = SemiticLanguageEntity::class,
            parentColumns = ["id"],
            childColumns = ["languageId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class InscriptionEntity(
    @PrimaryKey val id: String,
    val titleAr: String,
    val titleEn: String,
    val languageId: String,
    val branchId: String,
    val scriptTypeId: String,
    val dateCentury: String,
    val discoveryLocation: String,
    val currentMuseum: String,
    val material: String,
    val dimensions: String,
    val scriptTextOriginal: String,
    val transliteration: String,
    val translationAr: String,
    val translationEn: String,
    val historicalContext: String,
    val philologicalNotes: String,
    val spectralModesAvailableJson: String,
    val relatedChapterIdsJson: String,
    val audioReconstructedPhonetic: String,
    val isUserFavorite: Boolean = false,
    val userTranscriptionNotes: String = ""
) {
    fun toDomainModel(): InscriptionArtifact {
        val matchedBranch = LanguageBranch.values().find { it.id == branchId } ?: LanguageBranch.NORTHWEST_SEMITIC
        val matchedScript = ScriptType.values().find { it.id == scriptTypeId } ?: ScriptType.PHOENICIAN_LINEAR
        val modes = spectralModesAvailableJson.split(DELIMITER)
            .mapNotNull { modeId -> SpectralAnalysisMode.values().find { it.id == modeId } }
            .ifEmpty { SpectralAnalysisMode.values().toList() }

        return InscriptionArtifact(
            id = id,
            titleAr = titleAr,
            titleEn = titleEn,
            languageId = languageId,
            branch = matchedBranch,
            scriptType = matchedScript,
            dateCentury = dateCentury,
            discoveryLocation = discoveryLocation,
            currentMuseum = currentMuseum,
            material = material,
            dimensions = dimensions,
            scriptTextOriginal = scriptTextOriginal,
            transliteration = transliteration,
            translationAr = translationAr,
            translationEn = translationEn,
            historicalContext = historicalContext,
            philologicalNotes = philologicalNotes,
            spectralModesAvailable = modes,
            relatedChapterIds = relatedChapterIdsJson.split(DELIMITER).filter { it.isNotBlank() },
            audioReconstructedPhonetic = audioReconstructedPhonetic
        )
    }

    companion object {
        private const val DELIMITER = "|||"

        fun fromDomainModel(model: InscriptionArtifact, isFavorite: Boolean = false, userNotes: String = ""): InscriptionEntity {
            return InscriptionEntity(
                id = model.id,
                titleAr = model.titleAr,
                titleEn = model.titleEn,
                languageId = model.languageId,
                branchId = model.branch.id,
                scriptTypeId = model.scriptType.id,
                dateCentury = model.dateCentury,
                discoveryLocation = model.discoveryLocation,
                currentMuseum = model.currentMuseum,
                material = model.material,
                dimensions = model.dimensions,
                scriptTextOriginal = model.scriptTextOriginal,
                transliteration = model.transliteration,
                translationAr = model.translationAr,
                translationEn = model.translationEn,
                historicalContext = model.historicalContext,
                philologicalNotes = model.philologicalNotes,
                spectralModesAvailableJson = model.spectralModesAvailable.joinToString(DELIMITER) { it.id },
                relatedChapterIdsJson = model.relatedChapterIds.joinToString(DELIMITER),
                audioReconstructedPhonetic = model.audioReconstructedPhonetic,
                isUserFavorite = isFavorite,
                userTranscriptionNotes = userNotes
            )
        }
    }
}
