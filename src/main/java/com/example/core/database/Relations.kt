package com.example.core.database

import androidx.room.Embedded
import androidx.room.Relation

/**
 * 1-to-many relationship linking a Semitic Language to all its documented Inscriptions.
 */
data class LanguageWithInscriptions(
    @Embedded val language: SemiticLanguageEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "languageId"
    )
    val inscriptions: List<InscriptionEntity>
)

/**
 * Comprehensive relational model linking a Semitic Language with its Civilization Metadata
 * and its Inscriptions.
 */
data class LanguageWithCivilizationMetadata(
    @Embedded val language: SemiticLanguageEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "associatedLanguageId"
    )
    val civilization: CivilizationMetadataEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "languageId"
    )
    val inscriptions: List<InscriptionEntity>
)

/**
 * Relational model linking a Civilization with all its epigraphic Inscriptions
 * through the common language ID.
 */
data class CivilizationWithInscriptions(
    @Embedded val civilization: CivilizationMetadataEntity,
    @Relation(
        parentColumn = "associatedLanguageId",
        entityColumn = "languageId"
    )
    val inscriptions: List<InscriptionEntity>
)
