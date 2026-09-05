package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.data.InscriptionsData
import com.example.core.model.InscriptionArtifact
import com.example.core.model.SpectralAnalysisMode
import com.example.ui.MainViewModel
import com.example.ui.theme.RoyalGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InscriptionsArtifactsScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val bookmarks by viewModel.bookmarks.collectAsState()
    var isSplitSliderActive by remember { mutableStateOf(false) }

    val currentInscription = InscriptionsData.ALL_INSCRIPTIONS.find { it.id == uiState.selectedInscriptionId }
        ?: InscriptionsData.ALL_INSCRIPTIONS.first()

    val isBookmarked = bookmarks.any { it.id == currentInscription.id }
    var showDetailedLinguisticBreakdown by remember { mutableStateOf(false) }

    if (uiState.isInscriptionComparisonOpen) {
        com.example.ui.components.InscriptionSideBySideComparisonComponent(
            viewModel = viewModel,
            onBack = { viewModel.closeInscriptionComparison() },
            modifier = modifier
        )
        return
    }

    if (showDetailedLinguisticBreakdown) {
        com.example.ui.components.InscriptionDetailViewComponent(
            inscription = currentInscription,
            viewModel = viewModel,
            onBack = { showDetailedLinguisticBreakdown = false },
            modifier = modifier
        )
        return
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("inscriptions_artifacts_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { Spacer(modifier = Modifier.height(8.dp)) }

        // Inscription Selector Carousel
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (uiState.isEnglishUi) "Royal Monuments & Inscriptions:" else "المسلات والنقوش الأثرية الملكية:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )

                TextButton(
                    onClick = { viewModel.openInscriptionComparison(firstId = currentInscription.id) },
                    modifier = Modifier.testTag("open_inscription_comparison_top_btn")
                ) {
                    Icon(
                        imageVector = Icons.Filled.CompareArrows,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (uiState.isEnglishUi) "Compare Inscriptions" else "مقارنة نقشين",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(InscriptionsData.ALL_INSCRIPTIONS) { insc ->
                    val isSelected = insc.id == uiState.selectedInscriptionId
                    ElevatedFilterChip(
                        selected = isSelected,
                        onClick = { viewModel.selectInscription(insc.id) },
                        label = {
                            Text(
                                text = insc.titleAr.take(22),
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        leadingIcon = {
                            if (isSelected) {
                                Icon(Icons.Filled.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        }
                    )
                }
            }
        }

        // Spectral Analysis Mode Selector & Split Slider Controller
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "المعالجة البصرية الطيفية (Multi-Spectral MSI):",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("شريط مقارنة", fontSize = 11.sp)
                            Switch(
                                checked = isSplitSliderActive,
                                onCheckedChange = { isSplitSliderActive = it },
                                modifier = Modifier.scale(0.8f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(SpectralAnalysisMode.values()) { mode ->
                            val isSelected = uiState.spectralMode == mode
                            FilterChip(
                                selected = isSelected,
                                onClick = { viewModel.setSpectralMode(mode) },
                                label = { Text(mode.titleAr, fontSize = 11.sp) }
                            )
                        }
                    }

                    if (isSplitSliderActive) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "موضع مقبض المقارنة: ${(uiState.spectralSliderPosition * 100).toInt()}%",
                            style = MaterialTheme.typography.labelSmall
                        )
                        Slider(
                            value = uiState.spectralSliderPosition,
                            onValueChange = { viewModel.setSpectralSlider(it) },
                            valueRange = 0.05f..0.95f,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        // Artifact Lightbox Canvas
        item {
            ArtifactVisualBox(
                inscription = currentInscription,
                spectralMode = uiState.spectralMode,
                splitSliderPos = if (isSplitSliderActive) uiState.spectralSliderPosition else null
            )
        }

        // Inscription Details Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = currentInscription.titleAr,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = currentInscription.titleEn,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        IconButton(
                            onClick = {
                                viewModel.toggleBookmark(
                                    id = currentInscription.id,
                                    type = "inscription",
                                    title = currentInscription.titleAr,
                                    subtitle = currentInscription.discoveryLocation
                                )
                            },
                            modifier = Modifier.testTag("bookmark_inscription_btn")
                        ) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                                contentDescription = "حفظ",
                                tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(color = MaterialTheme.colorScheme.outlineVariant)
                    Spacer(modifier = Modifier.height(10.dp))

                    // Metadata Badges
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        AssistChip(
                            onClick = {},
                            label = { Text(currentInscription.discoveryLocation, fontSize = 11.sp) },
                            leadingIcon = { Icon(Icons.Filled.Place, contentDescription = null, modifier = Modifier.size(14.dp)) }
                        )
                        AssistChip(
                            onClick = {},
                            label = { Text(currentInscription.currentMuseum, fontSize = 11.sp) },
                            leadingIcon = { Icon(Icons.Filled.Museum, contentDescription = null, modifier = Modifier.size(14.dp)) }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Original Semitic Script
                    Text(
                        text = "النص الأصلي بالخط ${currentInscription.scriptType.titleAr}:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = currentInscription.scriptTextOriginal,
                                fontSize = 18.sp,
                                lineHeight = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "النقحرة اللاتينية (Transliteration):",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = currentInscription.transliteration,
                                style = MaterialTheme.typography.bodySmall,
                                fontFamily = FontFamily.Serif
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Arabic Translation
                    Text(
                        text = "الترجمة العربية الفصحى الشارحة:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = currentInscription.translationAr,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Philological Notes
                    Text(
                        text = "التحقيق اللغوي والفيلولوجي للنقش:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = currentInscription.philologicalNotes,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    FilledTonalButton(
                        onClick = { showDetailedLinguisticBreakdown = true },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("open_inscription_linguistic_detail_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AutoStories,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (uiState.isEnglishUi) "Open Detailed Linguistic Breakdown (Tokens & Roots)" else "عرض التفكيك الصرفي والمعجمي المفصل للنقش",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { viewModel.analyzeActiveInscriptionWithGemini() },
                        colors = ButtonDefaults.buttonColors(containerColor = RoyalGold),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("gemini_analyze_inscription_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Psychology,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "تحليل فيلولوجي وإبيغرافي للنقش عبر Gemini AI",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = { viewModel.openInscriptionComparison(firstId = currentInscription.id) },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("open_inscription_comparison_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CompareArrows,
                            contentDescription = null,
                            tint = RoyalGold,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (uiState.isEnglishUi) "Compare with another inscription (Gemini AI)" else "مقارنة هذا النقش بنقش آخر (Gemini AI)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(40.dp)) }
    }
}

@Composable
fun ArtifactVisualBox(
    inscription: InscriptionArtifact,
    spectralMode: SpectralAnalysisMode,
    splitSliderPos: Float?
) {
    val backgroundBrush = when (spectralMode) {
        SpectralAnalysisMode.STANDARD -> Brush.verticalGradient(listOf(Color(0xFF3E2723), Color(0xFF1E1712)))
        SpectralAnalysisMode.HIGH_CONTRAST_RELIEF -> Brush.verticalGradient(listOf(Color(0xFF0D1B2A), Color(0xFF1B263B)))
        SpectralAnalysisMode.INFRARED_MSI -> Brush.verticalGradient(listOf(Color(0xFF4A148C), Color(0xFF1A237E)))
        SpectralAnalysisMode.LINE_ART_EDGE -> Brush.verticalGradient(listOf(Color(0xFF004D40), Color(0xFF000000)))
        SpectralAnalysisMode.HEATMAP -> Brush.verticalGradient(listOf(Color(0xFFB71C1C), Color(0xFFFF6F00), Color(0xFF212121)))
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundBrush)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.HistoryEdu,
                contentDescription = null,
                tint = when (spectralMode) {
                    SpectralAnalysisMode.LINE_ART_EDGE -> Color(0xFF00FFCC)
                    SpectralAnalysisMode.INFRARED_MSI -> Color(0xFFFF80AB)
                    SpectralAnalysisMode.HEATMAP -> Color(0xFFFFD54F)
                    else -> RoyalGold
                },
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = inscription.titleAr,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "وضع العرض: ${spectralMode.titleAr}",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp
            )
            if (splitSliderPos != null) {
                Text(
                    text = "شريط المقارنة المزدوج مفعل: ${(splitSliderPos * 100).toInt()}%",
                    color = RoyalGold,
                    fontSize = 11.sp
                )
            }
        }
    }
}

fun Modifier.scale(scale: Float): Modifier = this
