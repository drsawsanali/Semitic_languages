package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.core.data.LearningData
import com.example.core.data.OfflineSearchIndexEngine
import com.example.core.data.SearchCategory
import com.example.core.export.CitationGenerator
import com.example.core.model.CitationFormat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("أطلس اللغات السامية", appName)
  }

  @Test
  fun `offline search index engine finds inscriptions and languages`() {
    // Search with Arabic query
    val meshaResults = OfflineSearchIndexEngine.search("ميشع")
    assertFalse("Results for Mesha should not be empty", meshaResults.isEmpty())
    assertTrue("Should find Mesha inscription", meshaResults.any { it.title.contains("ميشع") })

    // Search with Hammurabi
    val hammurabiResults = OfflineSearchIndexEngine.search("حمورابي")
    assertFalse("Results for Hammurabi should not be empty", hammurabiResults.isEmpty())

    // Category filter test
    val languagesOnly = OfflineSearchIndexEngine.search("كنعان", categoryFilter = SearchCategory.LANGUAGES)
    assertTrue("Filtered search should only return languages", languagesOnly.all { it.category == SearchCategory.LANGUAGES })
  }

  @Test
  fun `offline search normalizer removes tashkeel and harmonizes letters`() {
    val normalized = OfflineSearchIndexEngine.normalize("الْمَلِكُ أُوغَارِيت")
    assertEquals("الملك اوغاريت", normalized)
  }

  @Test
  fun `citation generator produces formatted output in all styles`() {
    val bib = LearningData.BIBLIOGRAPHY_ENTRIES.first()
    
    val apa = CitationGenerator.generateBibliographyCitation(bib, CitationFormat.APA_7)
    assertTrue("APA citation should contain author and year", apa.contains(bib.author) && apa.contains(bib.year.toString()))

    val mla = CitationGenerator.generateBibliographyCitation(bib, CitationFormat.MLA_9)
    assertTrue("MLA citation should contain title in quotes", mla.contains(bib.title))

    val bibtex = CitationGenerator.generateBibliographyCitation(bib, CitationFormat.BIBTEX)
    assertTrue("BibTeX should start with @book or @article", bibtex.startsWith("@book{"))
  }

  @Test
  fun `chronology events and learning data contain expected entries`() {
    assertTrue("Chronology events should have at least 15 entries", LearningData.CHRONOLOGY_EVENTS.size >= 15)
    assertTrue("Quiz questions should not be empty", LearningData.QUIZ_QUESTIONS.isNotEmpty())
    assertTrue("Flashcards should not be empty", LearningData.FLASHCARDS.isNotEmpty())
    assertTrue("Archaeological sites should not be empty", LearningData.ARCHAEOLOGICAL_SITES.isNotEmpty())
  }

  @Test
  fun `inscription linguistic breakdowns are populated with tokens and phonology`() {
    val meshaBreakdown = com.example.core.data.InscriptionLinguisticData.getBreakdown("mesha-stele")
    assertTrue("Mesha Stele breakdown should exist", meshaBreakdown != null)
    assertTrue("Mesha Stele should have linguistic tokens", meshaBreakdown!!.tokens.isNotEmpty())
    assertTrue("Mesha Stele should contain Proto-Semitic roots", meshaBreakdown.tokens.any { it.protoSemiticRoot.isNotBlank() })
    assertTrue("Mesha Stele should have phonological laws", meshaBreakdown.phonologicalLaws.isNotEmpty())
    assertTrue("Mesha Stele should have syntactic features", meshaBreakdown.syntacticFeatures.isNotEmpty())

    val ahiramBreakdown = com.example.core.data.InscriptionLinguisticData.getBreakdown("ahiram-sarcophagus")
    assertTrue("Ahiram Sarcophagus breakdown should exist", ahiramBreakdown != null)
    assertTrue("Ahiram should have tokens", ahiramBreakdown!!.tokens.isNotEmpty())

    val ugaritBreakdown = com.example.core.data.InscriptionLinguisticData.getBreakdown("ugarit_baal_epic")
    assertTrue("Ugarit breakdown should exist", ugaritBreakdown != null)
    assertTrue("Ugarit should have tokens", ugaritBreakdown!!.tokens.isNotEmpty())

    val telDanBreakdown = com.example.core.data.InscriptionLinguisticData.getBreakdown("tel_dan_stele")
    assertTrue("Tel Dan breakdown should exist", telDanBreakdown != null)
    assertTrue("Tel Dan should have tokens", telDanBreakdown!!.tokens.isNotEmpty())

    val sirwahBreakdown = com.example.core.data.InscriptionLinguisticData.getBreakdown("sirwah_inscription")
    assertTrue("Sirwah breakdown should exist", sirwahBreakdown != null)
    assertTrue("Sirwah should have tokens", sirwahBreakdown!!.tokens.isNotEmpty())
  }

  @Test
  fun `inscription comparison workflow updates state properly`() {
    val app = ApplicationProvider.getApplicationContext<android.app.Application>()
    val vm = com.example.ui.MainViewModel(app)

    vm.openInscriptionComparison("mesha_stele", "tel_dan_stele")
    var state = vm.uiState.value
    assertTrue("Comparison should be open", state.isInscriptionComparisonOpen)
    assertEquals("mesha_stele", state.selectedFirstCompareInscriptionId)
    assertEquals("tel_dan_stele", state.selectedSecondCompareInscriptionId)

    vm.swapCompareInscriptions()
    state = vm.uiState.value
    assertEquals("tel_dan_stele", state.selectedFirstCompareInscriptionId)
    assertEquals("mesha_stele", state.selectedSecondCompareInscriptionId)

    vm.setFirstCompareInscription("ahiram_sarcophagus")
    state = vm.uiState.value
    assertEquals("ahiram_sarcophagus", state.selectedFirstCompareInscriptionId)

    vm.closeInscriptionComparison()
    state = vm.uiState.value
    assertFalse("Comparison should be closed", state.isInscriptionComparisonOpen)
  }
}


