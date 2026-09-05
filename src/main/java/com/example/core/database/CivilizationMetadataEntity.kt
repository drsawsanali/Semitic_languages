package com.example.core.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.core.model.CivilizationMetadata
import com.example.core.model.LanguageBranch

/**
 * Room Entity representing historical civilization and kingdom metadata,
 * linked directly to its primary Semitic language.
 */
@Entity(
    tableName = "civilization_metadata",
    indices = [
        Index(value = ["associatedLanguageId"]),
        Index(value = ["branchId"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = SemiticLanguageEntity::class,
            parentColumns = ["id"],
            childColumns = ["associatedLanguageId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CivilizationMetadataEntity(
    @PrimaryKey val id: String,
    val nameAr: String,
    val nameEn: String,
    val associatedLanguageId: String,
    val branchId: String,
    val capitalCityAr: String,
    val capitalCityEn: String,
    val flourishedPeriod: String,
    val geographicCoreAr: String,
    val pantheonDeitiesJson: String,
    val majorRulersJson: String,
    val socialEconomicStructureAr: String = "",
    val tradeRoutesAr: String = "",
    val epigraphicStyleAr: String = "",
    val notableAchievementsAr: String = "",
    val primaryArchaeologicalSitesJson: String = "",
    val academicSummaryAr: String = ""
) {
    fun toDomainModel(): CivilizationMetadata {
        val branch = LanguageBranch.values().find { it.id == branchId } ?: LanguageBranch.EAST_SEMITIC
        return CivilizationMetadata(
            id = id,
            nameAr = nameAr,
            nameEn = nameEn,
            associatedLanguageId = associatedLanguageId,
            branch = branch,
            capitalCityAr = capitalCityAr,
            capitalCityEn = capitalCityEn,
            flourishedPeriod = flourishedPeriod,
            geographicCoreAr = geographicCoreAr,
            pantheonDeities = parseDelimitedList(pantheonDeitiesJson),
            majorRulers = parseDelimitedList(majorRulersJson),
            socialEconomicStructureAr = socialEconomicStructureAr,
            tradeRoutesAr = tradeRoutesAr,
            epigraphicStyleAr = epigraphicStyleAr,
            notableAchievementsAr = notableAchievementsAr,
            primaryArchaeologicalSites = parseDelimitedList(primaryArchaeologicalSitesJson),
            academicSummaryAr = academicSummaryAr
        )
    }

    companion object {
        private const val DELIMITER = "|||"

        private fun parseDelimitedList(input: String): List<String> {
            if (input.isBlank()) return emptyList()
            return input.split(DELIMITER).map { it.trim() }.filter { it.isNotEmpty() }
        }

        fun fromDomainModel(model: CivilizationMetadata): CivilizationMetadataEntity {
            return CivilizationMetadataEntity(
                id = model.id,
                nameAr = model.nameAr,
                nameEn = model.nameEn,
                associatedLanguageId = model.associatedLanguageId,
                branchId = model.branch.id,
                capitalCityAr = model.capitalCityAr,
                capitalCityEn = model.capitalCityEn,
                flourishedPeriod = model.flourishedPeriod,
                geographicCoreAr = model.geographicCoreAr,
                pantheonDeitiesJson = model.pantheonDeities.joinToString(DELIMITER),
                majorRulersJson = model.majorRulers.joinToString(DELIMITER),
                socialEconomicStructureAr = model.socialEconomicStructureAr,
                tradeRoutesAr = model.tradeRoutesAr,
                epigraphicStyleAr = model.epigraphicStyleAr,
                notableAchievementsAr = model.notableAchievementsAr,
                primaryArchaeologicalSitesJson = model.primaryArchaeologicalSites.joinToString(DELIMITER),
                academicSummaryAr = model.academicSummaryAr
            )
        }
    }
}
