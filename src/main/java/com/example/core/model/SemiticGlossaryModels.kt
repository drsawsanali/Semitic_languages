package com.example.core.model

/**
 * Domain models for the Semitic Linguistic Glossary & Interactive Word Lookup Engine.
 * Authored under academic supervision at Sana'a University.
 */

enum class GlossaryCategory(val id: String, val titleAr: String, val titleEn: String) {
    PHONETICS("phonetics", "علم الأصوات والفونولوجيا", "Phonetics & Phonology"),
    MORPHOLOGY("morphology", "الصرف وبنية الكلمة", "Morphology & Word Formation"),
    SYNTAX("syntax", "النحو وبناء الجملة", "Syntax & Sentence Structure"),
    EPIGRAPHY("epigraphy", "الإبيغرافيا والخطوط والنقوش", "Epigraphy & Paleography"),
    ETYMOLOGY("etymology", "المعاجم والتأصيل الفيلولوجي", "Lexicology & Etymology"),
    COMPARATIVE("comparative", "فقه اللغات السامية المقارن", "Comparative Semitics")
}

data class SemiticGlossaryItem(
    val id: String,
    val termAr: String,
    val termEn: String,
    val category: GlossaryCategory,
    val academicDefinitionAr: String,
    val protoSemiticBasis: String = "",
    val linguisticFormula: String = "",
    val relatedLanguages: List<String> = emptyList(),
    val epigraphicAttestations: List<String> = emptyList(),
    val comparativeCognates: List<Pair<String, String>> = emptyList(),
    val searchKeywords: List<String> = emptyList(),
    val relatedChapterIds: List<String> = emptyList()
)
