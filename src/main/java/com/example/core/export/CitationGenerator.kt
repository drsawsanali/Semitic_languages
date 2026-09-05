package com.example.core.export

import com.example.core.model.AcademicBibliography
import com.example.core.model.ChapterContent
import com.example.core.model.CitationFormat

object CitationGenerator {
    fun generateChapterCitation(
        chapter: ChapterContent,
        format: CitationFormat,
        retrievalDate: String = "2026"
    ): String {
        return when (format) {
            CitationFormat.APA_7 -> {
                "Al-Hadouri, S. A. (${retrievalDate}). Chapter ${chapter.chapterNumber}: ${chapter.titleEn} [Doctoral Dissertation Encyclopedia, Sana'a University]. Digital Semitic Atlas."
            }
            CitationFormat.MLA_9 -> {
                "Al-Hadouri, Sousan Ali. \"Chapter ${chapter.chapterNumber}: ${chapter.titleAr}.\" Encyclopedia of Semitic Languages, supervised by Prof. Dr. Ahmed Faqas, Sana'a University, ${retrievalDate}."
            }
            CitationFormat.CHICAGO_17 -> {
                "Al-Hadouri, Sousan Ali. \"${chapter.titleAr}.\" In Encyclopedia of Comparative Semitic Languages and Epigraphy. Sana'a: Sana'a University Press, ${retrievalDate}."
            }
            CitationFormat.BIBTEX -> {
                """
@incollection{alhadouri${chapter.chapterNumber},
  author    = {Al-Hadouri, Sousan Ali},
  title     = {${chapter.titleEn}},
  booktitle = {Comprehensive Digital Atlas of Semitic Languages},
  editor    = {Faqas, Ahmed},
  publisher = {Sana'a University Faculty of Arts},
  year      = {2026},
  chapter   = {${chapter.chapterNumber}},
  address   = {Sana'a, Yemen}
}
                """.trimIndent()
            }
            CitationFormat.ARABIC_UNIFIED -> {
                "الحضوري، سوسن علي. (${retrievalDate}). \"الفصل ${chapter.chapterNumber}: ${chapter.titleAr}\". إشراف: أ.د. أحمد فقعس، كلية الآداب، جامعة صنعاء."
            }
            CitationFormat.RIS -> {
                """
TY  - CHAP
AU  - Al-Hadouri, Sousan Ali
TI  - ${chapter.titleEn}
T2  - Digital Encyclopedia of Semitic Languages
ED  - Faqas, Ahmed
PB  - Sana'a University
PY  - 2026
ER  - 
                """.trimIndent()
            }
        }
    }

    fun generateBibliographyCitation(
        bib: AcademicBibliography,
        format: CitationFormat
    ): String {
        return when (format) {
            CitationFormat.APA_7 -> {
                "${bib.authors} (${bib.year}). ${bib.title}. ${bib.publisher}."
            }
            CitationFormat.MLA_9 -> {
                "${bib.authors}. *${bib.title}*. ${bib.publisher}, ${bib.year}."
            }
            CitationFormat.CHICAGO_17 -> {
                "${bib.authors}. ${bib.year}. *${bib.title}*. ${bib.city}: ${bib.publisher}."
            }
            CitationFormat.BIBTEX -> {
                """
@book{${bib.citeKey},
  author    = {${bib.authors}},
  title     = {${bib.title}},
  publisher = {${bib.publisher}},
  address   = {${bib.city}},
  year      = {${bib.year}}
}
                """.trimIndent()
            }
            CitationFormat.ARABIC_UNIFIED -> {
                "${bib.authors}. (${bib.year}). ${bib.title}. ${bib.publisher}، ${bib.city}."
            }
            CitationFormat.RIS -> {
                """
TY  - BOOK
AU  - ${bib.authors}
TI  - ${bib.title}
PB  - ${bib.publisher}
CY  - ${bib.city}
PY  - ${bib.year}
ER  - 
                """.trimIndent()
            }
        }
    }
}
