package com.example.core.model

/**
 * Core domain models for the Semitic Languages Encyclopedia & Digital Atlas.
 * Authored under academic supervision at Sana'a University.
 * Student: Sousan Ali Al-Hadouri | Supervisor: Prof. Dr. Ahmed Faqas
 */

enum class LanguageBranch(val id: String, val titleAr: String, val titleEn: String, val glyph: String, val colorHex: Long) {
    EAST_SEMITIC("east-semitic", "السامية الشرقية", "East Semitic", "𒀭", 0xFF9A6C36),
    NORTHWEST_SEMITIC("northwest-semitic", "السامية الشمالية الغربية", "Northwest Semitic", "𐤀", 0xFF176B69),
    ARAMAIC("aramaic", "المجموعة الآرامية", "Aramaic Complex", "𐡀", 0xFF4F668E),
    ARABIC_ANA("arabic-ana", "العربية والشمالية القديمة", "Arabic & Ancient North Arabian", "𐪀", 0xFF8C4D28),
    ANCIENT_SOUTH_ARABIAN("ancient-south-arabian", "السامية الجنوبية القديمة (الصيهدية)", "Ancient South Arabian (Sayhadic)", "𐩱", 0xFF9E7B29),
    MODERN_SOUTH_ARABIAN("modern-south-arabian", "السامية الجنوبية الحديثة", "Modern South Arabian", "𐩥", 0xFF2C6E49),
    ETHIOSEMITIC("ethiosemitic", "السامية الإثيوبية (الحبشية)", "Ethiosemitic", "ሀ", 0xFF6D3B71)
}

enum class ScriptType(val id: String, val titleAr: String, val titleEn: String, val sampleGlyphs: String) {
    CUNEIFORM("cuneiform", "الخط المسماري", "Cuneiform", "𒀭 𒈗 𒂍 𒂗 𒀭"),
    PHOENICIAN_LINEAR("phoenician", "الأبجدية الفينيقية الكنعانية", "Phoenician / Canaanite", "𐤀 𐤁 𐤂 𐤃 𐤄"),
    UGARITIC_CUNEIFORM("ugaritic", "المسمارية الأبجدية الأوغاريتية", "Ugaritic Cuneiform", "𐎀 𐎁 𐎂 𐎃 𐎄"),
    IMPERIAL_ARAMAIC("aramaic", "الخط الآرامي الإمبراطوري", "Imperial Aramaic", "𐡀 𐡁 𐡂 𐡃 𐡄"),
    SYRIAC_ESTRANGELO("syriac", "السرياني الأسطرنجيلي", "Syriac Estrangelo", "ܐ ܒ ܓ ܕ ܗ"),
    MUSNAD("musnad", "خط المسند العربي الجنوبي", "South Arabian Musnad", "𐩠 𐩡 𐩢 𐩣 𐩤"),
    SAFAITIC_ANA("safaitic", "خط البادية الشمالي (الصفائي)", "Safaitic / ANA", "𐪀 𐪁 𐪂 𐪃 𐪄"),
    GEEZ_FIDEL("fidel", "الخط الجعزي المقطعي (الفيدل)", "Ge'ez Fidel Abugida", "ሀ ለ ሐ መ ሠ"),
    NABATAEAN("nabataean", "الخط النبطي", "Nabataean", "𐢀 𐢁 𐢂 𐢃 𐢄"),
    ARABIC_KUFIC("arabic", "الخط العربي (الكوفي/النسخي)", "Arabic Script", "ا ب ت ث ج")
}

data class SemiticLanguage(
    val id: String,
    val nameAr: String,
    val nameEn: String,
    val branch: LanguageBranch,
    val scriptType: ScriptType,
    val period: String,
    val geographicalRegion: String,
    val historicalKingdoms: List<String>,
    val consonantCount: Int,
    val sampleTextOriginal: String,
    val sampleTextTransliteration: String,
    val sampleTextTranslationAr: String,
    val prominentInscriptions: List<String> = emptyList(),
    val phonologicalKeyFeatures: List<String>,
    val morphologicalFeatures: List<String>,
    val syntacticFeatures: List<String>,
    val primaryDeities: List<String> = emptyList(),
    val totalChapters: Int = 50
)

enum class SpectralAnalysisMode(val id: String, val titleAr: String, val titleEn: String, val descriptionAr: String) {
    STANDARD("standard", "العرض الطبيعي الأصلي", "Standard", "الأبعاد والخامات الحجرية/الطينية الأصلية"),
    HIGH_CONTRAST_RELIEF("relief", "حفر التباين العالي", "High Contrast Relief", "إبراز أخاديد وحفر الحروف الغائرة بدقة قصوى"),
    INFRARED_MSI("infrared", "الأشعة تحت الحمراء MSI", "Infrared MSI", "كشف الحبر والمواضع الباهتة والممسوحة زمنياً"),
    LINE_ART_EDGE("line-art", "تحديد الحواف الأبجدية", "Line Art Detection", "عزل الحبيبات وإبراز الهيكل الأبجدي بخطوط ساطعة"),
    HEATMAP("heatmap", "الخريطة الحرارية للعمق", "Depth Heatmap", "توزيع لوني يوضح كثافة الحفر وعمقه الإبيغرافي")
}

data class InscriptionArtifact(
    val id: String,
    val titleAr: String,
    val titleEn: String,
    val languageId: String,
    val branch: LanguageBranch,
    val scriptType: ScriptType = ScriptType.PHOENICIAN_LINEAR,
    val dateCentury: String = "",
    val periodEra: String = dateCentury,
    val period: String = dateCentury,
    val discoveryLocation: String = "",
    val currentMuseum: String = "",
    val material: String = "حجر بازلتي / كلسي",
    val materialType: String = material,
    val dimensions: String = "",
    val scriptTextOriginal: String = "",
    val originalText: String = scriptTextOriginal,
    val transliteration: String = "",
    val transliterationEn: String = transliteration,
    val translationAr: String = "",
    val translationEn: String = "",
    val historicalContext: String = "",
    val philologicalNotes: String = "",
    val spectralModesAvailable: List<SpectralAnalysisMode> = SpectralAnalysisMode.values().toList(),
    val keyVocabulary: List<Pair<String, String>> = emptyList(),
    val relatedChapterIds: List<String> = emptyList(),
    val audioReconstructedPhonetic: String = ""
)

data class LanguageForm(
    val scriptForm: String,
    val transliteration: String,
    val ipaPhonetic: String,
    val notes: String = ""
)

data class LexiconCognate(
    val languageId: String,
    val languageNameAr: String,
    val wordOriginalScript: String,
    val transliteration: String,
    val ipaTranscription: String,
    val meaningAr: String
)

data class LexiconRoot(
    val protoSemiticRoot: String = "",
    val reconstructedForm: String = "",
    val englishMeaning: String = "",
    val arabicMeaning: String = "",
    val formsByLanguage: Map<String, LanguageForm> = emptyMap(),
    val etymologicalDevelopment: String = "",
    val id: String = protoSemiticRoot,
    val rootProtoSemitic: String = protoSemiticRoot,
    val meaningAr: String = arabicMeaning,
    val meaningEn: String = englishMeaning,
    val cognates: List<LexiconCognate> = emptyList()
)

data class SoundShiftRule(
    val id: String,
    val ruleNameAr: String,
    val ruleNameEn: String = "",
    val protoSemiticSound: String,
    val targetSound: String = "",
    val evolvedSound: String = targetSound,
    val appliedLanguages: List<String> = emptyList(),
    val languagesAffected: List<String> = appliedLanguages,
    val exceptionLanguages: List<String> = emptyList(),
    val linguisticExplanationAr: String,
    val descriptionAr: String = linguisticExplanationAr,
    val comparativeExamples: List<String> = emptyList(),
    val examples: List<String> = comparativeExamples
)

data class IpaConsonant(
    val symbol: String,
    val arabicName: String,
    val articulationPlace: String,
    val articulationManner: String,
    val languageExamples: String = "",
    val frequencyHz: Int = 440
)

data class ChapterContent(
    val id: String,
    val chapterNumber: Int,
    val titleAr: String,
    val titleEn: String,
    val unitNumber: Int = 1,
    val author: String = "سوسن علي الحضوري",
    val institution: String = "جامعة صنعاء • كلية الآداب",
    val summaryAr: String = "",
    val fullLatexContent: String = "",
    val footnotes: List<String> = emptyList(),
    val relatedInscriptionIds: List<String> = emptyList(),
    val keywords: List<String> = emptyList()
)

data class GrammarFlashcard(
    val id: String,
    val titleAr: String,
    val titleEn: String = "",
    val categoryAr: String = "",
    val category: String = categoryAr,
    val formula: String = "",
    val formulaRule: String = formula,
    val frontPromptAr: String = "",
    val backExplanationAr: String = "",
    val explanationAr: String = backExplanationAr,
    val epigraphicExamples: List<String> = emptyList(),
    val inscribedEvidence: String = epigraphicExamples.joinToString(" • "),
    val ruleTitleAr: String = titleAr,
    var boxLevel: Int = 0,
    var masteryLevel: Int = boxLevel
)

typealias FlashcardItem = GrammarFlashcard

data class QuizQuestion(
    val id: String,
    val questionAr: String,
    val questionEn: String = "",
    val optionsAr: List<String>,
    val correctOptionIndex: Int = 0,
    val correctIndex: Int = correctOptionIndex,
    val explanationAr: String = "",
    val category: String = "عام",
    val domain: String = category,
    val referenceCitation: String = ""
)

data class AcademicBibliography(
    val id: String,
    val citeKey: String = "",
    val authors: String = "",
    val author: String = authors,
    val year: Any = "",
    val title: String,
    val publisher: String = "",
    val city: String = "",
    val place: String = city,
    val journalOrBook: String = "",
    val doiOrUrl: String = "",
    val summaryAr: String = "",
    val annotationAr: String = summaryAr,
    val topics: List<String> = emptyList()
)

data class ChronologyEvent(
    val id: String = "",
    val yearBceOrCe: Int = 0,
    val yearBce: Int = yearBceOrCe,
    val displayDateAr: String = "",
    val displayDateEn: String = "",
    val titleAr: String = "",
    val titleEn: String = "",
    val descriptionAr: String = "",
    val descriptionEn: String = "",
    val eraAr: String = "",
    val eraEn: String = "",
    val languageBranch: LanguageBranch = LanguageBranch.NORTHWEST_SEMITIC,
    val languageAssociated: String = languageBranch.titleAr,
    val associatedLanguageId: String = "",
    val scriptType: String = "",
    val scriptGlyphSymbol: String = "",
    val keyInscriptionId: String = "",
    val keySiteId: String = "",
    val significanceNotesAr: String = ""
)

data class ArchaeologicalSite(
    val id: String,
    val nameAr: String,
    val nameEn: String,
    val latitude: Double,
    val longitude: Double,
    val region: String = "",
    val modernCountry: String = region,
    val associatedBranch: LanguageBranch = LanguageBranch.NORTHWEST_SEMITIC,
    val branch: LanguageBranch = associatedBranch,
    val associatedLanguages: List<String> = emptyList(),
    val primaryLanguage: String = associatedLanguages.firstOrNull() ?: "",
    val historicalSignificanceAr: String = "",
    val descriptionAr: String = historicalSignificanceAr,
    val inscriptionsFound: List<String> = emptyList(),
    val majorInscriptions: List<String> = inscriptionsFound,
    val notableArtifacts: List<String> = emptyList(),
    val periodDisplay: String = ""
)

data class ScriptGlyph(
    val glyph: String,
    val name: String,
    val arabicEquivalent: String,
    val ipa: String,
    val approximateFrequencyHz: Int = 440
)

data class VirtualScriptLayout(
    val scriptId: String,
    val scriptNameAr: String,
    val glyphs: List<ScriptGlyph>
)

enum class CitationFormat {
    APA_7,
    MLA_9,
    CHICAGO_17,
    BIBTEX,
    ARABIC_UNIFIED,
    RIS
}

data class CivilizationMetadata(
    val id: String,
    val nameAr: String,
    val nameEn: String,
    val associatedLanguageId: String,
    val branch: LanguageBranch = LanguageBranch.EAST_SEMITIC,
    val capitalCityAr: String,
    val capitalCityEn: String,
    val flourishedPeriod: String,
    val geographicCoreAr: String,
    val pantheonDeities: List<String> = emptyList(),
    val majorRulers: List<String> = emptyList(),
    val socialEconomicStructureAr: String = "",
    val tradeRoutesAr: String = "",
    val epigraphicStyleAr: String = "",
    val notableAchievementsAr: String = "",
    val primaryArchaeologicalSites: List<String> = emptyList(),
    val academicSummaryAr: String = ""
)
