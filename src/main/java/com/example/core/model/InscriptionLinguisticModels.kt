package com.example.core.model

/**
 * Detailed linguistic token breakdown for ancient Semitic inscriptions.
 */
data class InscriptionTokenAnalysis(
    val tokenOriginal: String,
    val transliteration: String,
    val ipa: String,
    val meaningAr: String,
    val meaningEn: String = "",
    val grammaticalRoleAr: String,
    val root: String,
    val morphologicalPattern: String,
    val cognatesComparison: String,
    val protoSemiticRoot: String = root
)

/**
 * Phonological sound law manifested in the inscription.
 */
data class InscriptionPhonologicalLaw(
    val ruleTitleAr: String,
    val formula: String,
    val explanationAr: String,
    val inTextExamples: List<String>
)

/**
 * Comprehensive linguistic and philological breakdown of an inscription artifact.
 */
data class InscriptionLinguisticBreakdown(
    val inscriptionId: String,
    val titleAr: String,
    val scriptNameAr: String,
    val writingDirectionAr: String = "من اليمين إلى اليسار (RTL)",
    val wordDividerAr: String = "فواصل نقطية عمودية",
    val tokens: List<InscriptionTokenAnalysis>,
    val phonologicalFeatures: List<InscriptionPhonologicalLaw>,
    val morphologicalFeatures: List<String>,
    val syntacticFeatures: List<String>,
    val epigraphicPaleographicNotes: List<String>,
    val comparativeSemiticInsights: List<String>,
    val historicalSignificanceAr: String,
    val phonologicalLaws: List<InscriptionPhonologicalLaw> = phonologicalFeatures
)
