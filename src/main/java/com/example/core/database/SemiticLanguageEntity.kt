package com.example.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.model.LanguageBranch
import com.example.core.model.ScriptType
import com.example.core.model.SemiticLanguage

/**
 * Room Entity representing a Semitic language definition stored locally.
 */
@Entity(tableName = "semitic_languages")
data class SemiticLanguageEntity(
    @PrimaryKey val id: String,
    val nameAr: String,
    val nameEn: String,
    val branchId: String,
    val scriptTypeId: String,
    val period: String,
    val geographicalRegion: String,
    val historicalKingdomsJson: String, // Stored as comma-separated or serialized
    val consonantCount: Int,
    val sampleTextOriginal: String,
    val sampleTextTransliteration: String,
    val sampleTextTranslationAr: String,
    val prominentInscriptionsJson: String,
    val phonologicalKeyFeaturesJson: String,
    val morphologicalFeaturesJson: String,
    val syntacticFeaturesJson: String,
    val primaryDeitiesJson: String,
    val totalChapters: Int = 50,
    val isUserFavorite: Boolean = false,
    val customNotes: String = ""
) {
    fun toDomainModel(): SemiticLanguage {
        val matchedBranch = LanguageBranch.values().find { it.id == branchId } ?: LanguageBranch.EAST_SEMITIC
        val matchedScript = ScriptType.values().find { it.id == scriptTypeId } ?: ScriptType.PHOENICIAN_LINEAR

        return SemiticLanguage(
            id = id,
            nameAr = nameAr,
            nameEn = nameEn,
            branch = matchedBranch,
            scriptType = matchedScript,
            period = period,
            geographicalRegion = geographicalRegion,
            historicalKingdoms = parseDelimitedList(historicalKingdomsJson),
            consonantCount = consonantCount,
            sampleTextOriginal = sampleTextOriginal,
            sampleTextTransliteration = sampleTextTransliteration,
            sampleTextTranslationAr = sampleTextTranslationAr,
            prominentInscriptions = parseDelimitedList(prominentInscriptionsJson),
            phonologicalKeyFeatures = parseDelimitedList(phonologicalKeyFeaturesJson),
            morphologicalFeatures = parseDelimitedList(morphologicalFeaturesJson),
            syntacticFeatures = parseDelimitedList(syntacticFeaturesJson),
            primaryDeities = parseDelimitedList(primaryDeitiesJson),
            totalChapters = totalChapters
        )
    }

    companion object {
        private const val DELIMITER = "|||"

        fun fromDomainModel(model: SemiticLanguage, isFavorite: Boolean = false, notes: String = ""): SemiticLanguageEntity {
            return SemiticLanguageEntity(
                id = model.id,
                nameAr = model.nameAr,
                nameEn = model.nameEn,
                branchId = model.branch.id,
                scriptTypeId = model.scriptType.id,
                period = model.period,
                geographicalRegion = model.geographicalRegion,
                historicalKingdomsJson = model.historicalKingdoms.joinToString(DELIMITER),
                consonantCount = model.consonantCount,
                sampleTextOriginal = model.sampleTextOriginal,
                sampleTextTransliteration = model.sampleTextTransliteration,
                sampleTextTranslationAr = model.sampleTextTranslationAr,
                prominentInscriptionsJson = model.prominentInscriptions.joinToString(DELIMITER),
                phonologicalKeyFeaturesJson = model.phonologicalKeyFeatures.joinToString(DELIMITER),
                morphologicalFeaturesJson = model.morphologicalFeatures.joinToString(DELIMITER),
                syntacticFeaturesJson = model.syntacticFeatures.joinToString(DELIMITER),
                primaryDeitiesJson = model.primaryDeities.joinToString(DELIMITER),
                totalChapters = model.totalChapters,
                isUserFavorite = isFavorite,
                customNotes = notes
            )
        }

        private fun parseDelimitedList(raw: String): List<String> {
            if (raw.isBlank()) return emptyList()
            return raw.split(DELIMITER).map { it.trim() }.filter { it.isNotEmpty() }
        }
    }
}
