package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ai.GeminiAnalysisState
import com.example.core.ai.GeminiPhilologicalApiService
import com.example.core.audio.SemiticTtsManager
import com.example.core.data.*
import com.example.core.database.*
import com.example.core.keyboard.ScriptKeyboardLayout
import com.example.core.keyboard.VirtualKeyboardData
import com.example.core.model.*
import com.example.ui.theme.ReadingThemeMode
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class AppTab(val titleAr: String, val titleEn: String) {
    LANGUAGES("اللغات السامية", "Semitic Languages"),
    INSCRIPTIONS("أرشيف النقوش واللقى", "Inscriptions & Epigraphy"),
    CIVILIZATIONS("الحضارات السامية", "Semitic Civilizations"),
    FAVORITES("المفضلة والمحفوظات", "Favorites & Bookmarks"),
    READER("القارئ الموسوعي", "Encyclopedia Reader"),
    PHONETICS("محاكي الأصوات", "Phonetics Simulator"),
    LEXICON("المعجم المقارن", "Comparative Lexicon"),
    MAP("الأطلس والخريطة", "Historical Map Atlas"),
    LAB("مختبر الأبحاث والكتابة", "Research Lab & Keyboard"),
    LEARNING("مركز الاختبارات والتعليم", "Learning & Quiz Center")
}

data class AtlasUiState(
    val currentTab: AppTab = AppTab.READER,
    val selectedLanguageBranch: LanguageBranch? = null,
    val searchQuery: String = "",
    val selectedChapterId: String = "chap_1",
    val selectedInscriptionId: String = "mesha_stele",
    val selectedLanguageId: String = "akkadian",
    val spectralMode: SpectralAnalysisMode = SpectralAnalysisMode.STANDARD,
    val spectralSliderPosition: Float = 0.5f,
    val isPlayingTts: Boolean = false,
    val ttsSpeed: Float = 1.0f,
    val activeKeyboardLayout: ScriptKeyboardLayout = VirtualKeyboardData.PHOENICIAN_LAYOUT,
    val keyboardInputText: String = "",
    val activeQuizIndex: Int = 0,
    val quizSelectedOption: Int? = null,
    val quizScore: Int = 0,
    val isQuizFinished: Boolean = false,
    val flashcardFilterLevel: Int? = null,
    val readingTheme: ReadingThemeMode = ReadingThemeMode.DEFAULT_LIGHT,
    val isEnglishUi: Boolean = false,
    val selectedCitationFormat: CitationFormat = CitationFormat.APA_7,
    val isCitationModalOpen: Boolean = false,
    val isSettingsModalOpen: Boolean = false,
    val activeSiteId: String? = null,
    val aiAnalysisState: GeminiAnalysisState = GeminiAnalysisState.Idle,
    val isAiAnalysisDialogOpen: Boolean = false,
    val aiCustomQueryInput: String = "",
    val selectedFirstCompareInscriptionId: String = "mesha_stele",
    val selectedSecondCompareInscriptionId: String = "tel_dan_stele",
    val inscriptionComparisonState: GeminiAnalysisState = GeminiAnalysisState.Idle,
    val isInscriptionComparisonOpen: Boolean = false,
    val selectedGlossaryTerm: SemiticGlossaryItem? = null,
    val activeWordLookup: String? = null,
    val activeWordLookupContext: String? = null,
    val isWordLookupDialogOpen: Boolean = false,
    val isGlossaryScreenOpen: Boolean = false,
    val wordLookupAiState: GeminiAnalysisState = GeminiAnalysisState.Idle,
    val glossarySearchQuery: String = "",
    val selectedGlossaryCategory: GlossaryCategory? = null
)

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val dao = database.atlasDao()
    val encyclopediaRepository = SemiticEncyclopediaRepository(database.encyclopediaDao())
    val ttsManager = SemiticTtsManager(application)

    private val _uiState = MutableStateFlow(AtlasUiState())
    val uiState: StateFlow<AtlasUiState> = _uiState.asStateFlow()

    // Observable Flows from Room Database
    val persistedLanguages: StateFlow<List<SemiticLanguage>> = encyclopediaRepository.allLanguages.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    val persistedInscriptions: StateFlow<List<InscriptionArtifact>> = encyclopediaRepository.allInscriptions.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    val persistedSites: StateFlow<List<ArchaeologicalSite>> = encyclopediaRepository.allSites.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    val persistedChronology: StateFlow<List<ChronologyEvent>> = encyclopediaRepository.allChronologyEvents.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    val persistedCivilizations: StateFlow<List<CivilizationMetadata>> = encyclopediaRepository.allCivilizations.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    val persistedLanguagesWithInscriptions: StateFlow<List<LanguageWithInscriptions>> = encyclopediaRepository.getAllLanguagesWithInscriptions().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    val bookmarks = dao.getAllBookmarks().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    val readingProgressList = dao.getAllReadingProgress().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    val flashcardProgressList = dao.getFlashcardProgressList().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    val recentQuizScores = dao.getRecentQuizScores().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    init {
        // Initialize Room local database with languages, inscriptions, and cultural metadata if first launch
        viewModelScope.launch {
            encyclopediaRepository.initializeDatabaseIfEmpty()
        }
    }

    fun selectTab(tab: AppTab) {
        _uiState.update { it.copy(currentTab = tab) }
    }

    fun switchTab(tab: AppTab) {
        selectTab(tab)
    }

    fun setLanguageBranchFilter(branch: LanguageBranch?) {
        _uiState.update { it.copy(selectedLanguageBranch = branch) }
    }

    fun setSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun selectChapter(chapterId: String) {
        _uiState.update { it.copy(selectedChapterId = chapterId) }
        viewModelScope.launch {
            dao.saveReadingProgress(
                ReadingProgressEntity(
                    chapterId = chapterId,
                    languageId = "general",
                    percentCompleted = 100f
                )
            )
        }
    }

    fun selectInscription(inscriptionId: String) {
        _uiState.update { it.copy(selectedInscriptionId = inscriptionId) }
    }

    fun selectLanguage(langId: String) {
        _uiState.update { it.copy(selectedLanguageId = langId) }
    }

    fun setSpectralMode(mode: SpectralAnalysisMode) {
        _uiState.update { it.copy(spectralMode = mode) }
    }

    fun setSpectralSlider(pos: Float) {
        _uiState.update { it.copy(spectralSliderPosition = pos.coerceIn(0.05f, 0.95f)) }
    }

    fun toggleTts(textToRead: String) {
        val currentState = _uiState.value.isPlayingTts
        if (currentState) {
            ttsManager.stop()
            _uiState.update { it.copy(isPlayingTts = false) }
        } else {
            ttsManager.speakArabic(textToRead, _uiState.value.ttsSpeed)
            _uiState.update { it.copy(isPlayingTts = true) }
        }
    }

    fun setTtsSpeed(speed: Float) {
        _uiState.update { it.copy(ttsSpeed = speed) }
    }

    fun setKeyboardLayout(layout: ScriptKeyboardLayout) {
        _uiState.update { it.copy(activeKeyboardLayout = layout) }
    }

    fun appendKeyboardGlyph(glyph: String) {
        _uiState.update { it.copy(keyboardInputText = it.keyboardInputText + glyph) }
    }

    fun deleteKeyboardLastChar() {
        _uiState.update {
            val text = it.keyboardInputText
            if (text.isNotEmpty()) {
                it.copy(keyboardInputText = text.dropLast(1))
            } else it
        }
    }

    fun clearKeyboardInput() {
        _uiState.update { it.copy(keyboardInputText = "") }
    }

    fun setKeyboardPresetText(preset: String) {
        _uiState.update { it.copy(keyboardInputText = preset) }
    }

    fun answerQuizQuestion(selectedIdx: Int) {
        val currentQ = LearningData.QUIZ_QUESTIONS.getOrNull(_uiState.value.activeQuizIndex) ?: return
        val isCorrect = selectedIdx == currentQ.correctOptionIndex
        val newScore = if (isCorrect) _uiState.value.quizScore + 1 else _uiState.value.quizScore

        _uiState.update {
            it.copy(
                quizSelectedOption = selectedIdx,
                quizScore = newScore
            )
        }
    }

    fun nextQuizQuestion() {
        val nextIdx = _uiState.value.activeQuizIndex + 1
        if (nextIdx < LearningData.QUIZ_QUESTIONS.size) {
            _uiState.update {
                it.copy(
                    activeQuizIndex = nextIdx,
                    quizSelectedOption = null
                )
            }
        } else {
            _uiState.update { it.copy(isQuizFinished = true) }
            viewModelScope.launch {
                dao.insertQuizScore(
                    QuizScoreEntity(
                        category = "الموسوعة العامة",
                        score = _uiState.value.quizScore,
                        totalQuestions = LearningData.QUIZ_QUESTIONS.size
                    )
                )
            }
        }
    }

    fun resetQuiz() {
        _uiState.update {
            it.copy(
                activeQuizIndex = 0,
                quizSelectedOption = null,
                quizScore = 0,
                isQuizFinished = false
            )
        }
    }

    fun updateFlashcardBox(cardId: String, newLevel: Int) {
        viewModelScope.launch {
            dao.updateFlashcardProgress(
                FlashcardProgressEntity(cardId = cardId, boxLevel = newLevel.coerceIn(0, 2))
            )
        }
    }

    fun toggleBookmark(id: String, type: String, title: String, subtitle: String) {
        viewModelScope.launch {
            val isBookmarked = bookmarks.value.any { it.id == id }
            if (isBookmarked) {
                dao.deleteBookmark(id)
            } else {
                dao.insertBookmark(BookmarkEntity(id = id, itemType = type, title = title, subtitle = subtitle))
            }
        }
    }

    fun setReadingTheme(theme: ReadingThemeMode) {
        _uiState.update { it.copy(readingTheme = theme) }
    }

    fun toggleLanguageUi() {
        _uiState.update { it.copy(isEnglishUi = !it.isEnglishUi) }
    }

    fun setCitationFormat(format: CitationFormat) {
        _uiState.update { it.copy(selectedCitationFormat = format) }
    }

    fun toggleCitationModal(open: Boolean) {
        _uiState.update { it.copy(isCitationModalOpen = open) }
    }

    fun toggleSettingsModal(open: Boolean) {
        _uiState.update { it.copy(isSettingsModalOpen = open) }
    }

    fun setActiveSite(siteId: String?) {
        _uiState.update { it.copy(activeSiteId = siteId) }
    }

    fun toggleLanguageFavorite(languageId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            encyclopediaRepository.setLanguageFavorite(languageId, isFavorite)
        }
    }

    fun saveLanguageNotes(languageId: String, notes: String) {
        viewModelScope.launch {
            encyclopediaRepository.updateLanguageNotes(languageId, notes)
        }
    }

    fun toggleInscriptionFavorite(inscriptionId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            encyclopediaRepository.setInscriptionFavorite(inscriptionId, isFavorite)
        }
    }

    fun saveInscriptionNotes(inscriptionId: String, notes: String) {
        viewModelScope.launch {
            encyclopediaRepository.updateInscriptionNotes(inscriptionId, notes)
        }
    }

    fun toggleSiteSaved(siteId: String, isSaved: Boolean) {
        viewModelScope.launch {
            encyclopediaRepository.setSiteSaved(siteId, isSaved)
        }
    }

    fun addBookmark(id: String, itemType: String, title: String, subtitle: String) {
        viewModelScope.launch {
            dao.insertBookmark(
                BookmarkEntity(
                    id = id,
                    itemType = itemType,
                    title = title,
                    subtitle = subtitle
                )
            )
        }
    }

    fun deleteBookmark(id: String) {
        viewModelScope.launch {
            dao.deleteBookmark(id)
        }
    }

    fun isItemBookmarked(id: String): Flow<Boolean> {
        return dao.isBookmarked(id)
    }

    fun explainCivilizationWithGemini(civ: CivilizationMetadata) {
        _uiState.update { 
            it.copy(
                isAiAnalysisDialogOpen = true,
                aiAnalysisState = GeminiAnalysisState.Loading("جاري صياغة الدراسة التاريخية والأثرية لـ ${civ.nameAr} عبر Gemini AI...")
            ) 
        }

        viewModelScope.launch {
            val result = geminiService.explainCivilization(
                civilizationNameAr = civ.nameAr,
                capitalCity = civ.capitalCityAr,
                flourishedPeriod = civ.flourishedPeriod,
                geographicCore = civ.geographicCoreAr,
                rulers = civ.majorRulers,
                deities = civ.pantheonDeities,
                tradeRoutes = civ.tradeRoutesAr,
                achievements = civ.notableAchievementsAr
            )
            _uiState.update { it.copy(aiAnalysisState = result) }
        }
    }

    val geminiService = GeminiPhilologicalApiService()

    fun openAiAnalysisDialog() {
        _uiState.update { it.copy(isAiAnalysisDialogOpen = true) }
    }

    fun dismissAiAnalysisDialog() {
        _uiState.update { it.copy(isAiAnalysisDialogOpen = false) }
    }

    fun setAiCustomQueryInput(query: String) {
        _uiState.update { it.copy(aiCustomQueryInput = query) }
    }

    fun clearAiAnalysis() {
        _uiState.update { it.copy(aiAnalysisState = GeminiAnalysisState.Idle) }
    }

    fun playTts(text: String) {
        ttsManager.speakArabic(text)
    }

    fun analyzeActiveInscriptionWithGemini() {
        val currentInscription = InscriptionsData.ALL_INSCRIPTIONS.find { it.id == _uiState.value.selectedInscriptionId }
            ?: InscriptionsData.ALL_INSCRIPTIONS.first()

        _uiState.update { 
            it.copy(
                isAiAnalysisDialogOpen = true,
                aiAnalysisState = GeminiAnalysisState.Loading("جاري الاتصال بـ Gemini AI لإجراء التحليل الفيلولوجي والإبيغرافي الأكاديمي للنقش...")
            ) 
        }

        viewModelScope.launch {
            val result = geminiService.analyzeInscription(
                titleAr = currentInscription.titleAr,
                originalScript = currentInscription.scriptTextOriginal,
                scriptType = currentInscription.scriptType.titleAr,
                transliteration = currentInscription.transliteration,
                translationAr = currentInscription.translationAr,
                discoverySite = "${currentInscription.discoveryLocation} (${currentInscription.period})"
            )
            _uiState.update { it.copy(aiAnalysisState = result) }
        }
    }

    fun analyzeInscriptionAspectWithGemini(
        inscription: InscriptionArtifact,
        aspectTitle: String,
        specificPrompt: String
    ) {
        _uiState.update { 
            it.copy(
                isAiAnalysisDialogOpen = true,
                aiAnalysisState = GeminiAnalysisState.Loading("جاري دراسة [$aspectTitle] لنقش (${inscription.titleAr}) عبر Gemini AI...")
            ) 
        }

        viewModelScope.launch {
            val result = geminiService.analyzeInscriptionLinguisticAspect(
                titleAr = inscription.titleAr,
                originalScript = inscription.scriptTextOriginal,
                scriptType = inscription.scriptType.titleAr,
                transliteration = inscription.transliteration,
                translationAr = inscription.translationAr,
                aspectTitle = aspectTitle,
                specificPrompt = specificPrompt
            )
            _uiState.update { it.copy(aiAnalysisState = result) }
        }
    }

    fun analyzeInscriptionTokenWithGemini(
        inscription: InscriptionArtifact,
        token: InscriptionTokenAnalysis
    ) {
        _uiState.update { 
            it.copy(
                isAiAnalysisDialogOpen = true,
                aiAnalysisState = GeminiAnalysisState.Loading("جاري تفكيك وتأثيل المفردة (${token.tokenOriginal} / ${token.transliteration}) عبر Gemini AI...")
            ) 
        }

        viewModelScope.launch {
            val result = geminiService.analyzeSpecificToken(
                inscriptionTitle = inscription.titleAr,
                tokenOriginal = token.tokenOriginal,
                transliteration = token.transliteration,
                root = token.root,
                contextSentence = inscription.scriptTextOriginal
            )
            _uiState.update { it.copy(aiAnalysisState = result) }
        }
    }

    fun analyzeKeyboardInputWithGemini() {
        val text = _uiState.value.keyboardInputText.trim()
        val script = _uiState.value.activeKeyboardLayout.titleAr

        if (text.isBlank()) {
            _uiState.update {
                it.copy(
                    isAiAnalysisDialogOpen = true,
                    aiAnalysisState = GeminiAnalysisState.Error("يرجى كتابة أو إدراج نص سامي عبر لوحة المفاتيح أولاً لتحليله.")
                )
            }
            return
        }

        _uiState.update { 
            it.copy(
                isAiAnalysisDialogOpen = true,
                aiAnalysisState = GeminiAnalysisState.Loading("جاري تفكيك النص واستخراج الجذور السامية عبر Gemini AI...")
            ) 
        }

        viewModelScope.launch {
            val result = geminiService.analyzeTranscribedText(text = text, scriptName = script)
            _uiState.update { it.copy(aiAnalysisState = result) }
        }
    }

    fun explainLanguageWithGemini(language: SemiticLanguage) {
        _uiState.update { 
            it.copy(
                isAiAnalysisDialogOpen = true,
                aiAnalysisState = GeminiAnalysisState.Loading("جاري صياغة الدراسة الفيلولوجية لـ ${language.nameAr} عبر Gemini AI...")
            ) 
        }

        viewModelScope.launch {
            val result = geminiService.explainLanguage(
                languageNameAr = language.nameAr,
                branchAr = language.branch.titleAr,
                phonologicalSystem = language.phonologicalKeyFeatures.joinToString("؛ "),
                scriptSystem = language.scriptType.titleAr,
                period = language.period
            )
            _uiState.update { it.copy(aiAnalysisState = result) }
        }
    }

    fun executeCustomAiQuery(query: String) {
        if (query.isBlank()) return

        _uiState.update { 
            it.copy(
                isAiAnalysisDialogOpen = true,
                aiAnalysisState = GeminiAnalysisState.Loading("جاري معالجة الاستفسار الأكاديمي بواسطة Gemini AI...")
            ) 
        }

        viewModelScope.launch {
            val result = geminiService.generateAcademicAnalysis(query)
            _uiState.update { it.copy(aiAnalysisState = result) }
        }
    }

    fun openInscriptionComparison(firstId: String? = null, secondId: String? = null) {
        _uiState.update { current ->
            val first = firstId ?: current.selectedInscriptionId
            // Ensure second is distinct from first
            val second = secondId ?: if (first == "mesha_stele") "tel_dan_stele" else "mesha_stele"
            current.copy(
                isInscriptionComparisonOpen = true,
                selectedFirstCompareInscriptionId = first,
                selectedSecondCompareInscriptionId = second
            )
        }
    }

    fun closeInscriptionComparison() {
        _uiState.update { it.copy(isInscriptionComparisonOpen = false) }
    }

    fun setFirstCompareInscription(id: String) {
        _uiState.update { it.copy(selectedFirstCompareInscriptionId = id) }
    }

    fun setSecondCompareInscription(id: String) {
        _uiState.update { it.copy(selectedSecondCompareInscriptionId = id) }
    }

    fun swapCompareInscriptions() {
        _uiState.update { current ->
            current.copy(
                selectedFirstCompareInscriptionId = current.selectedSecondCompareInscriptionId,
                selectedSecondCompareInscriptionId = current.selectedFirstCompareInscriptionId
            )
        }
    }

    fun clearInscriptionComparison() {
        _uiState.update { it.copy(inscriptionComparisonState = GeminiAnalysisState.Idle) }
    }

    fun runGeminiInscriptionComparison() {
        val firstId = _uiState.value.selectedFirstCompareInscriptionId
        val secondId = _uiState.value.selectedSecondCompareInscriptionId

        if (firstId == secondId) {
            _uiState.update {
                it.copy(
                    inscriptionComparisonState = GeminiAnalysisState.Error("يرجى اختيار نقشين مختلفين لإجراء المقارنة اللغوية والفيلولوجية.")
                )
            }
            return
        }

        val firstArtifact = InscriptionsData.ALL_INSCRIPTIONS.find { it.id == firstId }
            ?: InscriptionsData.ALL_INSCRIPTIONS.first()
        val secondArtifact = InscriptionsData.ALL_INSCRIPTIONS.find { it.id == secondId }
            ?: InscriptionsData.ALL_INSCRIPTIONS.last()

        val firstBreakdown = InscriptionLinguisticData.getBreakdown(firstId)
        val secondBreakdown = InscriptionLinguisticData.getBreakdown(secondId)

        _uiState.update {
            it.copy(
                inscriptionComparisonState = GeminiAnalysisState.Loading(
                    "جاري تحليل الفروق النحوية والدلالية والصوتية بين (${firstArtifact.titleAr}) و (${secondArtifact.titleAr}) عبر Gemini AI..."
                )
            )
        }

        viewModelScope.launch {
            val result = geminiService.compareInscriptionsLinguistically(
                first = firstArtifact,
                firstBreakdown = firstBreakdown,
                second = secondArtifact,
                secondBreakdown = secondBreakdown
            )
            _uiState.update { it.copy(inscriptionComparisonState = result) }
        }
    }

    /**
     * Opens the interactive word lookup dialog for any clicked word in texts.
     */
    fun openWordLookup(word: String, context: String? = null, autoTriggerGemini: Boolean = true) {
        val cleanWord = word.trim().replace(Regex("[.,:;!؟()\"'«»\\[\\]{}]"), "")
        if (cleanWord.isBlank()) return

        val matchedTerm = SemiticGlossaryData.findTerm(cleanWord)

        _uiState.update {
            it.copy(
                activeWordLookup = cleanWord,
                activeWordLookupContext = context,
                selectedGlossaryTerm = matchedTerm,
                isWordLookupDialogOpen = true,
                wordLookupAiState = if (autoTriggerGemini) GeminiAnalysisState.Idle else it.wordLookupAiState
            )
        }

        if (autoTriggerGemini) {
            requestGeminiWordDefinition(cleanWord, context)
        }
    }

    fun closeWordLookup() {
        _uiState.update {
            it.copy(
                isWordLookupDialogOpen = false,
                activeWordLookup = null,
                activeWordLookupContext = null,
                wordLookupAiState = GeminiAnalysisState.Idle
            )
        }
    }

    fun requestGeminiWordDefinition(word: String, context: String? = null) {
        _uiState.update {
            it.copy(
                wordLookupAiState = GeminiAnalysisState.Loading("جاري استخلاص التعريف الأكاديمي والتأصيل الفيلولوجي لـ «$word» عبر Gemini AI...")
            )
        }

        viewModelScope.launch {
            val result = geminiService.defineSemiticTerm(
                term = word,
                contextSentence = context,
                languageOrBranch = _uiState.value.selectedLanguageBranch?.titleAr
            )
            _uiState.update { it.copy(wordLookupAiState = result) }
        }
    }

    fun toggleGlossaryScreen(isOpen: Boolean) {
        _uiState.update { it.copy(isGlossaryScreenOpen = isOpen) }
    }

    fun selectGlossaryTerm(term: SemiticGlossaryItem) {
        _uiState.update {
            it.copy(
                selectedGlossaryTerm = term,
                activeWordLookup = term.termAr,
                activeWordLookupContext = term.academicDefinitionAr,
                isWordLookupDialogOpen = true
            )
        }
        requestGeminiWordDefinition(term.termAr, term.academicDefinitionAr)
    }

    fun updateGlossarySearch(query: String) {
        _uiState.update { it.copy(glossarySearchQuery = query) }
    }

    fun selectGlossaryCategory(category: GlossaryCategory?) {
        _uiState.update { it.copy(selectedGlossaryCategory = category) }
    }

    override fun onCleared() {
        super.onCleared()
        ttsManager.shutdown()
    }
}
