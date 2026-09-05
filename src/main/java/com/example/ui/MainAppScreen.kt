package com.example.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AtlasTopBar
import com.example.ui.components.OfflineSearchDialog
import com.example.ui.components.SemiticNavigationDrawer
import com.example.ui.components.SettingsModalDialog
import com.example.ui.screens.*
import com.example.ui.theme.SemiticAtlasTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    var isSettingsOpen by remember { mutableStateOf(false) }
    var isSearchOpen by remember { mutableStateOf(false) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    SemiticAtlasTheme(themeMode = uiState.readingTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    SemiticNavigationDrawer(
                        viewModel = viewModel,
                        onTabSelected = { tab ->
                            viewModel.selectTab(tab)
                            coroutineScope.launch { drawerState.close() }
                        },
                        onOpenSearch = {
                            isSearchOpen = true
                            coroutineScope.launch { drawerState.close() }
                        },
                        onOpenSettings = {
                            isSettingsOpen = true
                            coroutineScope.launch { drawerState.close() }
                        }
                    )
                }
            ) {
                Scaffold(
                    topBar = {
                        AtlasTopBar(
                            viewModel = viewModel,
                            onOpenDrawer = {
                                coroutineScope.launch {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            },
                            onOpenSearch = { isSearchOpen = true },
                            onOpenSettings = { isSettingsOpen = true }
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 6.dp,
                            modifier = Modifier.testTag("main_bottom_nav_bar")
                        ) {
                            listOf(
                                Triple(AppTab.LANGUAGES, Icons.Filled.Language, Icons.Outlined.Language),
                                Triple(AppTab.INSCRIPTIONS, Icons.Filled.HistoryEdu, Icons.Outlined.HistoryEdu),
                                Triple(AppTab.CIVILIZATIONS, Icons.Filled.AccountBalance, Icons.Outlined.AccountBalance),
                                Triple(AppTab.FAVORITES, Icons.Filled.Bookmark, Icons.Outlined.BookmarkBorder),
                                Triple(AppTab.READER, Icons.Filled.MenuBook, Icons.Outlined.MenuBook)
                            ).forEach { (tab, filledIcon, outlinedIcon) ->
                                val isSelected = uiState.currentTab == tab
                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = { viewModel.selectTab(tab) },
                                    icon = {
                                        Icon(
                                            imageVector = if (isSelected) filledIcon else outlinedIcon,
                                            contentDescription = tab.titleAr
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = if (uiState.isEnglishUi) tab.titleEn.split(" ").first() else tab.titleAr.split(" ").first(),
                                            fontSize = 10.sp
                                        )
                                    },
                                    modifier = Modifier.testTag("nav_tab_${tab.name.lowercase()}")
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (uiState.currentTab) {
                            AppTab.LANGUAGES -> LanguagesExplorerScreen(viewModel = viewModel)
                            AppTab.INSCRIPTIONS -> InscriptionsArtifactsScreen(viewModel = viewModel)
                            AppTab.CIVILIZATIONS -> CivilizationsScreen(viewModel = viewModel)
                            AppTab.FAVORITES -> FavoritesScreen(viewModel = viewModel)
                            AppTab.READER -> EncyclopediaReaderScreen(viewModel = viewModel)
                            AppTab.PHONETICS -> PhoneticsSimulatorScreen(viewModel = viewModel)
                            AppTab.LEXICON -> ComparativeLexiconScreen(viewModel = viewModel)
                            AppTab.MAP -> InteractiveMapAtlasScreen(viewModel = viewModel)
                            AppTab.LAB -> AiResearchLabScreen(viewModel = viewModel)
                            AppTab.LEARNING -> LearningCenterScreen(viewModel = viewModel)
                        }
                    }
                }
            }

            if (isSettingsOpen) {
                SettingsModalDialog(
                    viewModel = viewModel,
                    onDismiss = { isSettingsOpen = false }
                )
            }

            if (isSearchOpen) {
                OfflineSearchDialog(
                    viewModel = viewModel,
                    onDismiss = { isSearchOpen = false }
                )
            }

            if (uiState.isAiAnalysisDialogOpen) {
                com.example.ui.components.AiPhilologicalAnalysisDialog(
                    state = uiState.aiAnalysisState,
                    onDismiss = { viewModel.dismissAiAnalysisDialog() },
                    onSendCustomQuery = { query -> viewModel.executeCustomAiQuery(query) },
                    onSpeakText = { text -> viewModel.playTts(text) }
                )
            }

            if (uiState.isWordLookupDialogOpen) {
                com.example.ui.components.InteractiveWordLookupModal(
                    viewModel = viewModel,
                    onDismissRequest = { viewModel.closeWordLookup() }
                )
            }

            if (uiState.isGlossaryScreenOpen) {
                androidx.compose.ui.window.Dialog(
                    onDismissRequest = { viewModel.toggleGlossaryScreen(false) },
                    properties = androidx.compose.ui.window.DialogProperties(usePlatformDefaultWidth = false)
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        com.example.ui.components.SemiticGlossaryComponent(
                            viewModel = viewModel,
                            onClose = { viewModel.toggleGlossaryScreen(false) }
                        )
                    }
                }
            }
        }
    }
}
