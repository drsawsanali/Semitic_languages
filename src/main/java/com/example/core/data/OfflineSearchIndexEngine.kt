package com.example.core.data

import com.example.core.model.*

enum class SearchCategory(val titleAr: String, val titleEn: String) {
    ALL("الكل", "All"),
    LANGUAGES("اللغات", "Languages"),
    INSCRIPTIONS("النقوش والمسلات", "Inscriptions"),
    CHAPTERS("فصول الموسوعة", "Chapters"),
    ROOTS("المعجم والجذور", "Roots & Lexicon"),
    PHONETICS("قوانين الأصوات", "Sound Laws"),
    SITES("المواقع الأثرية", "Archaeological Sites"),
    FLASHCARDS("قواعد وبطاقات", "Grammar Rules")
}

data class OfflineSearchResult(
    val id: String,
    val title: String,
    val subtitle: String,
    val snippet: String,
    val category: SearchCategory,
    val targetTab: com.example.ui.AppTab,
    val targetItemId: String,
    val matchScore: Int = 0
)

object OfflineSearchIndexEngine {

    /**
     * Normalize string for robust Arabic and multi-script query matching:
     * - Removes Arabic diacritics / Harakat
     * - Normalizes Alif forms (أ, إ, آ, ٱ -> ا)
     * - Normalizes Taa Marbuta and Haa (ة -> ه)
     * - Normalizes Yaa and Alif Maqsura (ى -> ي)
     * - Lowercases and trims
     */
    fun normalize(input: String): String {
        return input.trim()
            .lowercase()
            .replace(Regex("[\u064B-\u065F\u0670]"), "") // Arabic Tashkeel
            .replace(Regex("[أإآٱ]"), "ا")
            .replace('ة', 'ه')
            .replace('ى', 'ي')
            .replace(Regex("[̀-ͯ]"), "") // Latin diacritics
    }

    fun search(
        query: String,
        categoryFilter: SearchCategory = SearchCategory.ALL
    ): List<OfflineSearchResult> {
        val normalizedQuery = normalize(query)
        if (normalizedQuery.isBlank()) return emptyList()

        val results = mutableListOf<OfflineSearchResult>()

        // 1. Search Semitic Languages
        if (categoryFilter == SearchCategory.ALL || categoryFilter == SearchCategory.LANGUAGES) {
            SemiticLanguagesData.ALL_LANGUAGES.forEach { lang ->
                val nameNorm = normalize(lang.nameAr + " " + lang.nameEn + " " + lang.sampleTextOriginal)
                val branchNorm = normalize(lang.branch.titleAr + " " + lang.branch.titleEn)
                val regionNorm = normalize(lang.geographicalRegion + " " + lang.period)
                val kingdomsNorm = normalize(lang.historicalKingdoms.joinToString(" ") + " " + lang.prominentInscriptions.joinToString(" "))
                val featuresNorm = normalize(lang.phonologicalKeyFeatures.joinToString(" ") + " " + lang.morphologicalFeatures.joinToString(" "))

                var score = 0
                if (nameNorm.contains(normalizedQuery)) score += 10
                if (branchNorm.contains(normalizedQuery)) score += 6
                if (regionNorm.contains(normalizedQuery)) score += 4
                if (kingdomsNorm.contains(normalizedQuery)) score += 4
                if (featuresNorm.contains(normalizedQuery)) score += 3

                if (score > 0) {
                    results.add(
                        OfflineSearchResult(
                            id = "lang_${lang.id}",
                            title = "${lang.nameAr} (${lang.nameEn})",
                            subtitle = "${lang.branch.titleAr} • ${lang.period}",
                            snippet = "${lang.geographicalRegion} — مثال: ${lang.sampleTextTranslationAr}",
                            category = SearchCategory.LANGUAGES,
                            targetTab = com.example.ui.AppTab.LANGUAGES,
                            targetItemId = lang.id,
                            matchScore = score
                        )
                    )
                }
            }
        }

        // 2. Search Inscriptions and Epigraphic Artifacts
        if (categoryFilter == SearchCategory.ALL || categoryFilter == SearchCategory.INSCRIPTIONS) {
            InscriptionsData.ALL_INSCRIPTIONS.forEach { insc ->
                val titleNorm = normalize(insc.titleAr + " " + insc.titleEn + " " + insc.discoveryLocation)
                val originalTextNorm = normalize(insc.scriptTextOriginal + " " + insc.transliteration)
                val transNorm = normalize(insc.translationAr + " " + insc.philologicalNotes)
                val museumNorm = normalize(insc.currentMuseum + " " + insc.material)

                var score = 0
                if (titleNorm.contains(normalizedQuery)) score += 10
                if (originalTextNorm.contains(normalizedQuery)) score += 8
                if (transNorm.contains(normalizedQuery)) score += 5
                if (museumNorm.contains(normalizedQuery)) score += 4

                if (score > 0) {
                    results.add(
                        OfflineSearchResult(
                            id = "insc_${insc.id}",
                            title = insc.titleAr,
                            subtitle = "${insc.branch.titleAr} • ${insc.dateCentury} (${insc.discoveryLocation})",
                            snippet = insc.translationAr.take(160) + "...",
                            category = SearchCategory.INSCRIPTIONS,
                            targetTab = com.example.ui.AppTab.INSCRIPTIONS,
                            targetItemId = insc.id,
                            matchScore = score
                        )
                    )
                }
            }
        }

        // 3. Search Encyclopedia Chapters
        if (categoryFilter == SearchCategory.ALL || categoryFilter == SearchCategory.CHAPTERS) {
            ChaptersData.ENCYCLOPEDIA_CHAPTERS.forEach { chap ->
                val titleNorm = normalize(chap.titleAr + " " + chap.titleEn)
                val summaryNorm = normalize(chap.summaryAr + " " + chap.keywords.joinToString(" "))
                val contentNorm = normalize(chap.fullLatexContent)

                var score = 0
                if (titleNorm.contains(normalizedQuery)) score += 10
                if (summaryNorm.contains(normalizedQuery)) score += 6
                if (contentNorm.contains(normalizedQuery)) score += 3

                if (score > 0) {
                    results.add(
                        OfflineSearchResult(
                            id = "chap_${chap.id}",
                            title = "الفصل ${chap.chapterNumber}: ${chap.titleAr}",
                            subtitle = "الوحدة ${chap.unitNumber} • ${chap.author}",
                            snippet = (if (chap.summaryAr.isNotBlank()) chap.summaryAr else chap.fullLatexContent).take(160) + "...",
                            category = SearchCategory.CHAPTERS,
                            targetTab = com.example.ui.AppTab.READER,
                            targetItemId = chap.id,
                            matchScore = score
                        )
                    )
                }
            }
        }

        // 4. Search Lexicon Roots and Cognates
        if (categoryFilter == SearchCategory.ALL || categoryFilter == SearchCategory.ROOTS) {
            LexiconData.COMPARATIVE_ROOTS.forEach { lex ->
                val rootNorm = normalize(lex.protoSemiticRoot + " " + lex.arabicMeaning + " " + lex.englishMeaning + " " + lex.reconstructedForm)
                val formsText = lex.formsByLanguage.values.joinToString(" ") { "${it.scriptForm} ${it.transliteration} ${it.notes}" }
                val formsNorm = normalize(formsText)

                var score = 0
                if (rootNorm.contains(normalizedQuery)) score += 10
                if (formsNorm.contains(normalizedQuery)) score += 8

                if (score > 0) {
                    results.add(
                        OfflineSearchResult(
                            id = "lex_${lex.protoSemiticRoot}",
                            title = "الجذر السامي: ${lex.protoSemiticRoot} (${lex.arabicMeaning})",
                            subtitle = "إعادة التركيب: ${lex.reconstructedForm} • الإنجليزية: ${lex.englishMeaning}",
                            snippet = "المقابلات: " + lex.formsByLanguage.entries.take(4).joinToString(" | ") { "${it.key}: ${it.value.scriptForm} (${it.value.transliteration})" },
                            category = SearchCategory.ROOTS,
                            targetTab = com.example.ui.AppTab.LEXICON,
                            targetItemId = lex.protoSemiticRoot,
                            matchScore = score
                        )
                    )
                }
            }
        }

        // 5. Search Phonetics Laws and Sound Shift Rules
        if (categoryFilter == SearchCategory.ALL || categoryFilter == SearchCategory.PHONETICS) {
            PhoneticsData.SOUND_SHIFT_RULES.forEach { law ->
                val nameNorm = normalize(law.ruleNameAr + " " + law.ruleNameEn + " " + law.protoSemiticSound + " " + law.targetSound + " " + law.linguisticExplanationAr)
                val examplesNorm = normalize(law.comparativeExamples.joinToString(" "))

                var score = 0
                if (nameNorm.contains(normalizedQuery)) score += 10
                if (examplesNorm.contains(normalizedQuery)) score += 6

                if (score > 0) {
                    results.add(
                        OfflineSearchResult(
                            id = "law_${law.id}",
                            title = law.ruleNameAr,
                            subtitle = "التحول: ${law.protoSemiticSound} ➔ ${law.targetSound}",
                            snippet = law.linguisticExplanationAr.take(160) + "...",
                            category = SearchCategory.PHONETICS,
                            targetTab = com.example.ui.AppTab.PHONETICS,
                            targetItemId = law.id,
                            matchScore = score
                        )
                    )
                }
            }
        }

        // 6. Search Archaeological Sites
        if (categoryFilter == SearchCategory.ALL || categoryFilter == SearchCategory.SITES) {
            LearningData.ARCHAEOLOGICAL_SITES.forEach { site ->
                val nameNorm = normalize(site.nameAr + " " + site.nameEn + " " + site.region + " " + site.historicalSignificanceAr)
                val inscNorm = normalize(site.inscriptionsFound.joinToString(" ") + " " + site.notableArtifacts.joinToString(" "))

                var score = 0
                if (nameNorm.contains(normalizedQuery)) score += 10
                if (inscNorm.contains(normalizedQuery)) score += 6

                if (score > 0) {
                    results.add(
                        OfflineSearchResult(
                            id = "site_${site.id}",
                            title = site.nameAr,
                            subtitle = "${site.region} • ${site.associatedBranch.titleAr}",
                            snippet = site.historicalSignificanceAr.take(160) + "...",
                            category = SearchCategory.SITES,
                            targetTab = com.example.ui.AppTab.MAP,
                            targetItemId = site.id,
                            matchScore = score
                        )
                    )
                }
            }
        }

        // 7. Search Grammar Flashcards
        if (categoryFilter == SearchCategory.ALL || categoryFilter == SearchCategory.FLASHCARDS) {
            LearningData.GRAMMAR_FLASHCARDS.forEach { card ->
                val titleNorm = normalize(card.titleAr + " " + card.titleEn + " " + card.formula + " " + card.frontPromptAr + " " + card.backExplanationAr)
                var score = 0
                if (titleNorm.contains(normalizedQuery)) score += 8

                if (score > 0) {
                    results.add(
                        OfflineSearchResult(
                            id = "card_${card.id}",
                            title = card.titleAr,
                            subtitle = "${card.categoryAr} • صيغة: ${card.formula}",
                            snippet = card.backExplanationAr.take(160) + "...",
                            category = SearchCategory.FLASHCARDS,
                            targetTab = com.example.ui.AppTab.LEARNING,
                            targetItemId = card.id,
                            matchScore = score
                        )
                    )
                }
            }
        }

        // Sort by match score descending
        return results.sortedByDescending { it.matchScore }
    }
}
