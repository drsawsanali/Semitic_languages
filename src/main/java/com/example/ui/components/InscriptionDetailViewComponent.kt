@file:OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)

package com.example.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.ai.GeminiAnalysisState
import com.example.core.data.InscriptionLinguisticData
import com.example.core.model.*
import com.example.ui.MainViewModel

enum class InscriptionLinguisticTab(val titleAr: String, val iconName: String) {
    TOKENS("التفكيك الصرفي والمعجمي", "tokens"),
    PHONOLOGY("القوانين والتحولات الصوتية", "phonology"),
    SYNTAX("النحو والأسلوبية التركيبية", "syntax"),
    EPIGRAPHY("الإبيغرافيا والباليوغرافيا", "epigraphy"),
    GEMINI_STUDY("مختبر التحليل الأكاديمي Gemini", "gemini")
}

enum class InscriptionViewLayoutMode {
    STACKED,
    SIDE_BY_SIDE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InscriptionDetailViewComponent(
    inscription: InscriptionArtifact,
    viewModel: MainViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val clipboardManager = LocalClipboardManager.current
    val uiState by viewModel.uiState.collectAsState()
    val isBookmarked by viewModel.isItemBookmarked(inscription.id).collectAsState(initial = false)

    val linguisticBreakdown = remember(inscription.id) {
        InscriptionLinguisticData.getBreakdown(inscription.id)
    }

    var selectedTab by remember { mutableStateOf(InscriptionLinguisticTab.TOKENS) }
    var selectedTokenIndex by remember { mutableStateOf<Int?>(null) }
    var currentSpectralMode by remember { mutableStateOf(inscription.spectralModesAvailable.firstOrNull() ?: SpectralAnalysisMode.STANDARD) }
    var layoutMode by remember { mutableStateOf(InscriptionViewLayoutMode.STACKED) }
    var customQueryText by remember { mutableStateOf("") }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Top App Bar
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = inscription.titleAr,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                        Text(
                            text = "${inscription.scriptType.titleAr} • ${inscription.dateCentury}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("inscription_detail_back_btn")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "رجوع"
                        )
                    }
                },
                actions = {
                    // Audio playback button
                    IconButton(
                        onClick = {
                            val speechText = inscription.audioReconstructedPhonetic.ifBlank {
                                inscription.translationAr
                            }
                            viewModel.playTts(speechText)
                        },
                        modifier = Modifier.testTag("inscription_detail_tts_btn")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = "استماع للنطق المقدر",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Bookmark button
                    IconButton(
                        onClick = {
                            viewModel.toggleBookmark(
                                id = inscription.id,
                                type = "نقش أثري",
                                title = inscription.titleAr,
                                subtitle = "${inscription.scriptType.titleAr} (${inscription.dateCentury})"
                            )
                        },
                        modifier = Modifier.testTag("inscription_detail_bookmark_btn")
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                            contentDescription = "حفظ في المفضلة",
                            tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Layout Mode Switcher (Side-by-Side vs Stacked)
                    IconButton(
                        onClick = {
                            layoutMode = if (layoutMode == InscriptionViewLayoutMode.STACKED) {
                                InscriptionViewLayoutMode.SIDE_BY_SIDE
                            } else {
                                InscriptionViewLayoutMode.STACKED
                            }
                        },
                        modifier = Modifier.testTag("inscription_detail_layout_mode_btn")
                    ) {
                        Icon(
                            imageVector = if (layoutMode == InscriptionViewLayoutMode.SIDE_BY_SIDE) {
                                Icons.Filled.Splitscreen
                            } else {
                                Icons.Filled.ViewAgenda
                            },
                            contentDescription = "تبديل نمط العرض (مزدوج / تتابعي)",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Compare with another inscription button
                    IconButton(
                        onClick = {
                            viewModel.openInscriptionComparison(firstId = inscription.id)
                        },
                        modifier = Modifier.testTag("inscription_detail_compare_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CompareArrows,
                            contentDescription = "مقارنة لغوية بنقش آخر",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Gemini Quick Analysis Trigger
                    FilledTonalButton(
                        onClick = {
                            viewModel.analyzeActiveInscriptionWithGemini()
                        },
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .testTag("inscription_detail_gemini_header_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "تحليل Gemini",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )

            HorizontalDivider()

            // Content Area: Either Side-by-Side or Stacked
            BoxWithConstraints(modifier = Modifier.weight(1f)) {
                val availableWidth = maxWidth
                val isWideScreen = availableWidth > 720.dp
                val activeLayout = if (isWideScreen || layoutMode == InscriptionViewLayoutMode.SIDE_BY_SIDE) {
                    InscriptionViewLayoutMode.SIDE_BY_SIDE
                } else {
                    InscriptionViewLayoutMode.STACKED
                }

                if (activeLayout == InscriptionViewLayoutMode.SIDE_BY_SIDE) {
                    // Side-by-Side Dual Pane View
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Left Pane: Inscription Text & Paleographic Viewer
                        Card(
                            modifier = Modifier
                                .weight(0.48f)
                                .fillMaxHeight(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            border = CardDefaults.outlinedCardBorder()
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                item {
                                    InscriptionTextDisplaySection(
                                        inscription = inscription,
                                        selectedTokenIndex = selectedTokenIndex,
                                        onTokenClick = { index ->
                                            selectedTokenIndex = index
                                            selectedTab = InscriptionLinguisticTab.TOKENS
                                        },
                                        currentSpectralMode = currentSpectralMode,
                                        onSpectralModeChange = { currentSpectralMode = it },
                                        onCopyText = { text, label ->
                                            clipboardManager.setText(AnnotatedString(text))
                                            snackbarMessage = "تم نسخ $label"
                                        }
                                    )
                                }
                            }
                        }

                        // Right Pane: Linguistic Features Breakdown & Gemini AI
                        Card(
                            modifier = Modifier
                                .weight(0.52f)
                                .fillMaxHeight(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            shape = RoundedCornerShape(16.dp),
                            border = CardDefaults.outlinedCardBorder()
                        ) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                // Linguistic Tab Bar
                                InscriptionLinguisticTabBar(
                                    selectedTab = selectedTab,
                                    onTabSelected = { selectedTab = it }
                                )

                                HorizontalDivider()

                                // Tab Content
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(14.dp)
                                ) {
                                    InscriptionLinguisticTabContent(
                                        inscription = inscription,
                                        linguisticBreakdown = linguisticBreakdown,
                                        selectedTab = selectedTab,
                                        selectedTokenIndex = selectedTokenIndex,
                                        onTokenSelect = { selectedTokenIndex = it },
                                        viewModel = viewModel,
                                        customQueryText = customQueryText,
                                        onCustomQueryChange = { customQueryText = it }
                                    )
                                }
                            }
                        }
                    }
                } else {
                    // Stacked Vertical Layout
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            InscriptionTextDisplaySection(
                                inscription = inscription,
                                selectedTokenIndex = selectedTokenIndex,
                                onTokenClick = { index ->
                                    selectedTokenIndex = index
                                    selectedTab = InscriptionLinguisticTab.TOKENS
                                },
                                currentSpectralMode = currentSpectralMode,
                                onSpectralModeChange = { currentSpectralMode = it },
                                onCopyText = { text, label ->
                                    clipboardManager.setText(AnnotatedString(text))
                                    snackbarMessage = "تم نسخ $label"
                                }
                            )
                        }

                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                ),
                                shape = RoundedCornerShape(16.dp),
                                border = CardDefaults.outlinedCardBorder()
                            ) {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    InscriptionLinguisticTabBar(
                                        selectedTab = selectedTab,
                                        onTabSelected = { selectedTab = it }
                                    )

                                    HorizontalDivider()

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp)
                                    ) {
                                        InscriptionLinguisticTabContent(
                                            inscription = inscription,
                                            linguisticBreakdown = linguisticBreakdown,
                                            selectedTab = selectedTab,
                                            selectedTokenIndex = selectedTokenIndex,
                                            onTokenSelect = { selectedTokenIndex = it },
                                            viewModel = viewModel,
                                            customQueryText = customQueryText,
                                            onCustomQueryChange = { customQueryText = it }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Snackbar feedback
        snackbarMessage?.let { msg ->
            Snackbar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                action = {
                    TextButton(onClick = { snackbarMessage = null }) {
                        Text("حسناً")
                    }
                }
            ) {
                Text(text = msg)
            }
        }
    }
}

/**
 * Renders the ancient Semitic text, academic transliteration, and translations.
 */
@Composable
fun InscriptionTextDisplaySection(
    inscription: InscriptionArtifact,
    selectedTokenIndex: Int?,
    onTokenClick: (Int) -> Unit,
    currentSpectralMode: SpectralAnalysisMode,
    onSpectralModeChange: (SpectralAnalysisMode) -> Unit,
    onCopyText: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val linguisticBreakdown = remember(inscription.id) {
        InscriptionLinguisticData.getBreakdown(inscription.id)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Meta chips
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            AssistChip(
                onClick = {},
                label = { Text(inscription.branch.titleAr, style = MaterialTheme.typography.labelSmall) },
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            )
            AssistChip(
                onClick = {},
                label = { Text(inscription.discoveryLocation, style = MaterialTheme.typography.labelSmall) },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Place, contentDescription = null, modifier = Modifier.size(14.dp))
                }
            )
            AssistChip(
                onClick = {},
                label = { Text(inscription.currentMuseum, style = MaterialTheme.typography.labelSmall) },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.AccountBalance, contentDescription = null, modifier = Modifier.size(14.dp))
                }
            )
            AssistChip(
                onClick = {},
                label = { Text(inscription.material, style = MaterialTheme.typography.labelSmall) },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Layers, contentDescription = null, modifier = Modifier.size(14.dp))
                }
            )
        }

        // Spectral Analysis Visual Selector
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            border = CardDefaults.outlinedCardBorder()
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "نمط الفحص الطيفي للمادة الأثرية",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = currentSpectralMode.titleAr,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(inscription.spectralModesAvailable) { mode ->
                        FilterChip(
                            selected = mode == currentSpectralMode,
                            onClick = { onSpectralModeChange(mode) },
                            label = { Text(mode.titleAr, style = MaterialTheme.typography.labelSmall) },
                            modifier = Modifier.testTag("spectral_mode_${mode.name}")
                        )
                    }
                }
            }
        }

        // Ancient Script Display Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = when (currentSpectralMode) {
                    SpectralAnalysisMode.STANDARD -> MaterialTheme.colorScheme.surface
                    SpectralAnalysisMode.HIGH_CONTRAST_RELIEF -> Color(0xFF1E1E1E)
                    SpectralAnalysisMode.INFRARED_MSI -> Color(0xFF2A1B18)
                    SpectralAnalysisMode.LINE_ART_EDGE -> Color(0xFF0F172A)
                    SpectralAnalysisMode.HEATMAP -> Color(0xFF1A1A2E)
                }
            ),
            shape = RoundedCornerShape(14.dp),
            border = CardDefaults.outlinedCardBorder()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.MenuBook,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "النص الأصلي بالخط السامي الأصيل",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(
                        onClick = { onCopyText(inscription.scriptTextOriginal, "النص الأصلي") },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ContentCopy,
                            contentDescription = "نسخ النص الأصلي",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Interactive Words / Glyphs Flow
                if (linguisticBreakdown != null && linguisticBreakdown.tokens.isNotEmpty()) {
                    Text(
                        text = "اضغط على أي كلمة لتسليط الضوء على تفكيكها الصرفي ومعجمها المقارن:",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        linguisticBreakdown.tokens.forEachIndexed { idx, token ->
                            val isSelected = selectedTokenIndex == idx
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelected) {
                                    MaterialTheme.colorScheme.primaryContainer
                                } else {
                                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                                },
                                border = if (isSelected) {
                                    BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                                } else {
                                    BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant)
                                },
                                modifier = Modifier
                                    .clickable { onTokenClick(idx) }
                                    .testTag("inscription_token_btn_$idx")
                            ) {
                                Column(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = token.tokenOriginal,
                                        style = MaterialTheme.typography.headlineSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = token.transliteration,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                } else {
                    // Fallback full text
                    Text(
                        text = inscription.scriptTextOriginal,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 22.sp,
                            lineHeight = 34.sp
                        ),
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // Academic Latin Transliteration Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
            ),
            shape = RoundedCornerShape(14.dp),
            border = CardDefaults.outlinedCardBorder()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "النقحرة الصوتية الأكاديمية (Transliteration)",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    IconButton(
                        onClick = { onCopyText(inscription.transliteration, "النقحرة الصوتية") },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ContentCopy,
                            contentDescription = "نسخ النقحرة",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = inscription.transliteration,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 0.5.sp,
                        lineHeight = 26.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Modern Standard Arabic Translation
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
            ),
            shape = RoundedCornerShape(14.dp),
            border = CardDefaults.outlinedCardBorder()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "الترجمة العربية الفصحى الشارحة",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    IconButton(
                        onClick = { onCopyText(inscription.translationAr, "الترجمة العربية") },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ContentCopy,
                            contentDescription = "نسخ الترجمة",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = inscription.translationAr,
                    style = MaterialTheme.typography.bodyLarge.copy(lineHeight = 28.sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // English Translation (if available)
        if (inscription.translationEn.isNotBlank()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f)
                ),
                shape = RoundedCornerShape(14.dp),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                ) {
                    Text(
                        text = "English Philological Translation",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = inscription.translationEn,
                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

/**
 * Tab bar for navigating linguistic breakdown features.
 */
@Composable
fun InscriptionLinguisticTabBar(
    selectedTab: InscriptionLinguisticTab,
    onTabSelected: (InscriptionLinguisticTab) -> Unit,
    modifier: Modifier = Modifier
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTab.ordinal,
        edgePadding = 8.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        InscriptionLinguisticTab.values().forEach { tab ->
            Tab(
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                text = {
                    Text(
                        text = tab.titleAr,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (selectedTab == tab) FontWeight.Bold else FontWeight.Normal
                    )
                },
                icon = {
                    when (tab) {
                        InscriptionLinguisticTab.TOKENS -> Icon(Icons.Filled.Spellcheck, contentDescription = null, modifier = Modifier.size(18.dp))
                        InscriptionLinguisticTab.PHONOLOGY -> Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null, modifier = Modifier.size(18.dp))
                        InscriptionLinguisticTab.SYNTAX -> Icon(Icons.Filled.AccountTree, contentDescription = null, modifier = Modifier.size(18.dp))
                        InscriptionLinguisticTab.EPIGRAPHY -> Icon(Icons.Filled.HistoryEdu, contentDescription = null, modifier = Modifier.size(18.dp))
                        InscriptionLinguisticTab.GEMINI_STUDY -> Icon(Icons.Filled.AutoAwesome, contentDescription = null, modifier = Modifier.size(18.dp), tint = MaterialTheme.colorScheme.primary)
                    }
                },
                modifier = Modifier.testTag("inscription_linguistic_tab_${tab.name}")
            )
        }
    }
}

/**
 * Content switch for each linguistic analysis tab.
 */
@Composable
fun InscriptionLinguisticTabContent(
    inscription: InscriptionArtifact,
    linguisticBreakdown: InscriptionLinguisticBreakdown?,
    selectedTab: InscriptionLinguisticTab,
    selectedTokenIndex: Int?,
    onTokenSelect: (Int) -> Unit,
    viewModel: MainViewModel,
    customQueryText: String,
    onCustomQueryChange: (String) -> Unit
) {
    when (selectedTab) {
        InscriptionLinguisticTab.TOKENS -> {
            InscriptionTokensTabContent(
                inscription = inscription,
                breakdown = linguisticBreakdown,
                selectedTokenIndex = selectedTokenIndex,
                onTokenSelect = onTokenSelect,
                onAnalyzeTokenWithGemini = { token ->
                    viewModel.analyzeInscriptionTokenWithGemini(inscription, token)
                }
            )
        }
        InscriptionLinguisticTab.PHONOLOGY -> {
            InscriptionPhonologyTabContent(
                breakdown = linguisticBreakdown,
                onAskGemini = {
                    viewModel.analyzeInscriptionAspectWithGemini(
                        inscription = inscription,
                        aspectTitle = "علم الأصوات والقوانين الفونولوجية",
                        specificPrompt = "اشرح القوانين الفونولوجية والتحولات الصوتية (مثل التحول الكنعاني، سقوط النون، الميمية/النونية) المتبدية في هذا النقش بالتفصيل وقارنها بالسامية الأم."
                    )
                }
            )
        }
        InscriptionLinguisticTab.SYNTAX -> {
            InscriptionSyntaxTabContent(
                breakdown = linguisticBreakdown,
                onAskGemini = {
                    viewModel.analyzeInscriptionAspectWithGemini(
                        inscription = inscription,
                        aspectTitle = "النحو والتراكيب الأسلوبية",
                        specificPrompt = "حلل التركيب النحوي والإعرابي لنصوص هذا النقش، مبيناً بنية الجملة (واو العطف السردية، حالة الإضافة، الضمائر المتصلة والمنفصلة، وأوزان الأفعال)."
                    )
                }
            )
        }
        InscriptionLinguisticTab.EPIGRAPHY -> {
            InscriptionEpigraphyTabContent(
                inscription = inscription,
                breakdown = linguisticBreakdown,
                onAskGemini = {
                    viewModel.analyzeInscriptionAspectWithGemini(
                        inscription = inscription,
                        aspectTitle = "الدراسة الإبيغرافية والباليوغرافيا",
                        specificPrompt = "قدم دراسة باليوغرافية دقيقة لشكل الخط الأبجدي، وتطور رسم الحروف، واتجاه التدوين، ومادة النقش، والسياق الأثري والتاريخي لظهوره."
                    )
                }
            )
        }
        InscriptionLinguisticTab.GEMINI_STUDY -> {
            InscriptionGeminiAssistantTabContent(
                inscription = inscription,
                viewModel = viewModel,
                customQueryText = customQueryText,
                onCustomQueryChange = onCustomQueryChange
            )
        }
    }
}

/**
 * 1. Tokens and Morphology Tab
 */
@Composable
fun InscriptionTokensTabContent(
    inscription: InscriptionArtifact,
    breakdown: InscriptionLinguisticBreakdown?,
    selectedTokenIndex: Int?,
    onTokenSelect: (Int) -> Unit,
    onAnalyzeTokenWithGemini: (InscriptionTokenAnalysis) -> Unit
) {
    val tokens = breakdown?.tokens ?: emptyList()

    if (tokens.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "جاري تجميع التفكيك المعجمي والصرفي لهذا النقش...",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
        return
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "قائمة المفردات المحللة صرفياً ونحوياً (${tokens.size} مفردات أساسية):",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        items(tokens.size) { idx ->
            val token = tokens[idx]
            val isSelected = selectedTokenIndex == idx

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onTokenSelect(idx) },
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) {
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                    }
                ),
                shape = RoundedCornerShape(12.dp),
                border = if (isSelected) {
                    BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                } else {
                    CardDefaults.outlinedCardBorder()
                }
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    // Header row: Token + Transliteration + Role
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = token.tokenOriginal,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = token.transliteration,
                                style = MaterialTheme.typography.titleSmall.copy(fontFamily = FontFamily.Monospace),
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = token.ipa,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        AssistChip(
                            onClick = {},
                            label = { Text(token.meaningAr, style = MaterialTheme.typography.labelSmall) }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                    // Grammatical Role
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "الوظيفة النحوية: ",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = token.grammaticalRoleAr,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Root & Pattern
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "الجذر والوزن: ",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${token.root} • (${token.morphologicalPattern})",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Semitic Cognates
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = "المقارنة السامية المشتركة:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = token.cognatesComparison,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Gemini AI Word Deep-dive Action
                    OutlinedButton(
                        onClick = { onAnalyzeTokenWithGemini(token) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("gemini_analyze_token_btn_$idx"),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "تأثيل المفردة ومقارنتها عبر Gemini AI",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

/**
 * 2. Phonology and Sound Shifts Tab
 */
@Composable
fun InscriptionPhonologyTabContent(
    breakdown: InscriptionLinguisticBreakdown?,
    onAskGemini: () -> Unit
) {
    val laws = breakdown?.phonologicalFeatures ?: emptyList()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                ),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "علم الأصوات والقوانين الفونولوجية للنقش",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "تتبع القوانين الصوتية والتحولات من السامية الأم",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    FilledTonalButton(
                        onClick = onAskGemini,
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Icon(Icons.Filled.AutoAwesome, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("دراسة صوتية بـ Gemini", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }

        if (laws.isEmpty()) {
            item {
                Text(
                    text = "تتوفر دراسة صوتية شاملة لهذا النقش عبر مختبر Gemini الفيلولوجي.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            items(laws.size) { idx ->
                val law = laws[idx]
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = law.ruleTitleAr,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.surface,
                            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant)
                        ) {
                            Text(
                                text = law.formula,
                                style = MaterialTheme.typography.labelMedium.copy(fontFamily = FontFamily.Monospace),
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = law.explanationAr,
                            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (law.inTextExamples.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "الشواهد من متن النقش:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                law.inTextExamples.forEach { ex ->
                                    SuggestionChip(
                                        onClick = {},
                                        label = { Text(ex, style = MaterialTheme.typography.labelSmall) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * 3. Syntax and Stylistics Tab
 */
@Composable
fun InscriptionSyntaxTabContent(
    breakdown: InscriptionLinguisticBreakdown?,
    onAskGemini: () -> Unit
) {
    val morphologicalFeatures = breakdown?.morphologicalFeatures ?: emptyList()
    val syntacticFeatures = breakdown?.syntacticFeatures ?: emptyList()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.35f)
                ),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "النحو والتراكيب الأسلوبية وبناء الجمل",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "حالات الإضافة، واو العطف السردية، وصيغ الأفعال",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    FilledTonalButton(
                        onClick = onAskGemini,
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Icon(Icons.Filled.AutoAwesome, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("إعراب بـ Gemini", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }

        // Morphological Features
        if (morphologicalFeatures.isNotEmpty()) {
            item {
                Text(
                    text = "الخصائص المورفولوجية والصرفية:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            items(morphologicalFeatures.size) { idx ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(16.dp)
                                .padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = morphologicalFeatures[idx],
                            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        // Syntactic Features
        if (syntacticFeatures.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "الخصائص النحوية وبناء الجمل:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            items(syntacticFeatures.size) { idx ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AccountTree,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(16.dp)
                                .padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = syntacticFeatures[idx],
                            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

/**
 * 4. Epigraphy and Paleography Tab
 */
@Composable
fun InscriptionEpigraphyTabContent(
    inscription: InscriptionArtifact,
    breakdown: InscriptionLinguisticBreakdown?,
    onAskGemini: () -> Unit
) {
    val epigraphicNotes = breakdown?.epigraphicPaleographicNotes ?: emptyList()
    val insights = breakdown?.comparativeSemiticInsights ?: emptyList()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.35f)
                ),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "الإبيغرافيا وتطور الخط والتدوين",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            text = "اتجاه الكتابة، الفواصل النقطية، وتقنيات الحفر",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    FilledTonalButton(
                        onClick = onAskGemini,
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Icon(Icons.Filled.AutoAwesome, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("دراسة باليوغرافية بـ Gemini", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }

        // Writing Direction & Divider metadata
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                ),
                shape = RoundedCornerShape(12.dp),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("اتجاه الكتابة:", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text(breakdown?.writingDirectionAr ?: "من اليمين إلى اليسار", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                        }
                        Column {
                            Text("فواصل الكلمات والجمل:", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text(breakdown?.wordDividerAr ?: "فواصل تقليدية", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }

        // Epigraphic Notes
        if (epigraphicNotes.isNotEmpty()) {
            item {
                Text(
                    text = "الملاحظات الإبيغرافية والباليوغرافية:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            items(epigraphicNotes.size) { idx ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Filled.HistoryEdu,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier
                                .size(16.dp)
                                .padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = epigraphicNotes[idx],
                            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        // Historical Significance
        breakdown?.historicalSignificanceAr?.takeIf { it.isNotBlank() }?.let { significance ->
            item {
                Spacer(modifier = Modifier.height(4.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "الأهمية التاريخية والأثرية:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = significance,
                            style = MaterialTheme.typography.bodySmall.copy(lineHeight = 22.sp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

/**
 * 5. Interactive Gemini Academic Assistant Tab
 */
@Composable
fun InscriptionGeminiAssistantTabContent(
    inscription: InscriptionArtifact,
    viewModel: MainViewModel,
    customQueryText: String,
    onCustomQueryChange: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val clipboardManager = LocalClipboardManager.current
    var copiedFeedback by remember { mutableStateOf(false) }

    val preloadedPrompts = listOf(
        "تحليل فيلولوجي شامل" to "قدم تحليلاً فيلولوجياً أكاديمياً متكاملاً لنقش (${inscription.titleAr})، مبيناً خصائص اللغة، والتأثيل المقارن للجذور، والصلة بالسامية الأم.",
        "إعراب وبناء الجمل" to "أعرب الجمل الرئيسية الواردة في هذا النقش إعراباً مقارناً، مع بيان واو العطف التتابعية، وحالة الإضافة، واللواحق الضمائرية.",
        "دراسة التحول الكنعاني والصوتيات" to "تتبع القوانين الصوتية والحركات ونظام الصوائت (Vowel System) والقوانين التاريخية البادية في رسم هذا النقش.",
        "المقارنة اللغوية السامية" to "قارن معجم ومفردات هذا النقش مع الأكادية المسمارية، الفينيقية، السريانية، والعربية الفصحى في جدول تحليلي.",
        "السياق التاريخي والديني" to "اشرح السياق الحضاري والسياسي والبانثيون الديني المذكور في النقش وصلته بالاكتشافات الأثرية المعاصرة له."
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        // Quick Academic Prompts Section
        item {
            Text(
                text = "محاور التحقيق الأكاديمي السريع عبر Gemini AI:",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                preloadedPrompts.forEach { (title, prompt) ->
                    ActionChip(
                        onClick = {
                            viewModel.analyzeInscriptionAspectWithGemini(
                                inscription = inscription,
                                aspectTitle = title,
                                specificPrompt = prompt
                            )
                        },
                        label = { Text(title, style = MaterialTheme.typography.labelSmall) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.AutoAwesome,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    )
                }
            }
        }

        // Custom Query Field
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                ),
                shape = RoundedCornerShape(12.dp),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "اطرح استفساراً فيلولوجياً مخصصاً عن هذا النقش:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = customQueryText,
                        onValueChange = onCustomQueryChange,
                        placeholder = {
                            Text("مثال: ما دلالة واو العطف التتابعية في السطر الأول؟ أو قارن مفردة...")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("inscription_gemini_custom_query_input"),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    if (customQueryText.isNotBlank()) {
                                        viewModel.analyzeInscriptionAspectWithGemini(
                                            inscription = inscription,
                                            aspectTitle = "استفسار بحثي مخصص",
                                            specificPrompt = customQueryText.trim()
                                        )
                                    }
                                },
                                enabled = customQueryText.isNotBlank()
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Send,
                                    contentDescription = "إرسال لـ Gemini",
                                    tint = if (customQueryText.isNotBlank()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                                )
                            }
                        },
                        singleLine = false,
                        maxLines = 3
                    )
                }
            }
        }

        // Current Gemini Analysis State Card (Embedded Output)
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.AutoAwesome,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "مخرجات التحليل الأكاديمي (Gemini AI)",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        if (uiState.aiAnalysisState is GeminiAnalysisState.Success) {
                            val successResult = (uiState.aiAnalysisState as GeminiAnalysisState.Success).responseText
                            Row {
                                IconButton(
                                    onClick = {
                                        viewModel.playTts(successResult.take(300))
                                    },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                        contentDescription = "استماع",
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        clipboardManager.setText(AnnotatedString(successResult))
                                        copiedFeedback = true
                                    },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.ContentCopy,
                                        contentDescription = "نسخ",
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

                    when (val state = uiState.aiAnalysisState) {
                        is GeminiAnalysisState.Idle -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Filled.Psychology,
                                        contentDescription = null,
                                        modifier = Modifier.size(40.dp),
                                        tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "اختر أحد محاور التحقيق أعلاه أو اكتب استفسارك لتوليد دراسة فيلولوجية متخصصة بنموذج Gemini 3.5 Flash.",
                                        style = MaterialTheme.typography.bodySmall,
                                        textAlign = TextAlign.Center,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }

                        is GeminiAnalysisState.Loading -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 28.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(36.dp),
                                        strokeWidth = 3.dp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.height(14.dp))
                                    Text(
                                        text = state.messageAr,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }

                        is GeminiAnalysisState.Success -> {
                            Column {
                                Text(
                                    text = state.responseText,
                                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 24.sp),
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                if (copiedFeedback) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "✓ تم نسخ نص التحليل إلى الحافظة بنجاح",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                        is GeminiAnalysisState.Error -> {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.6f),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.error)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = "تنبيه التحليل الأكاديمي:",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = state.errorAr,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onErrorContainer
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ActionChip(
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier.clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon?.invoke()
            if (leadingIcon != null) {
                Spacer(modifier = Modifier.width(6.dp))
            }
            label()
        }
    }
}
