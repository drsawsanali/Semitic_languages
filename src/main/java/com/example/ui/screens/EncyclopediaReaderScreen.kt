package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.data.ChaptersData
import com.example.core.export.CitationGenerator
import com.example.core.model.ChapterContent
import com.example.core.model.CitationFormat
import com.example.ui.MainViewModel
import com.example.ui.components.ClickableLinguisticText
import com.example.ui.components.InteractiveWordLookupModal
import com.example.ui.components.SemiticGlossaryComponent
import com.example.ui.theme.RoyalGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EncyclopediaReaderScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val bookmarks by viewModel.bookmarks.collectAsState()
    val clipboardManager = LocalClipboardManager.current
    var isCopiedSnackbarVisible by remember { mutableStateOf(false) }
    var isInteractiveGlossaryMode by remember { mutableStateOf(true) }

    val currentChapter = ChaptersData.ENCYCLOPEDIA_CHAPTERS.find { it.id == uiState.selectedChapterId }
        ?: ChaptersData.ENCYCLOPEDIA_CHAPTERS.first()

    val isBookmarked = bookmarks.any { it.id == currentChapter.id }

    Scaffold(
        modifier = modifier.testTag("encyclopedia_reader_screen"),
        bottomBar = {
            // Audio TTS Floating Control Bar
            Surface(
                tonalElevation = 8.dp,
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        IconButton(
                            onClick = {
                                viewModel.toggleTts(currentChapter.titleAr + ". " + currentChapter.summaryAr)
                            },
                            modifier = Modifier
                                .testTag("tts_play_button")
                                .background(MaterialTheme.colorScheme.primary, CircleShape)
                        ) {
                            Icon(
                                imageVector = if (uiState.isPlayingTts) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                contentDescription = "قراءة صوتية",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = if (uiState.isEnglishUi) "Audio Lecture Player" else "المشغل الصوتي الأكاديمي",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = if (uiState.isPlayingTts) "جاري القراءة الفيلولوجية..." else "قراءة صوتية لنص الفصل",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    // Speed selector
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        listOf(0.75f, 1.0f, 1.25f).forEach { speed ->
                            FilterChip(
                                selected = uiState.ttsSpeed == speed,
                                onClick = { viewModel.setTtsSpeed(speed) },
                                label = { Text("${speed}x", fontSize = 11.sp) },
                                modifier = Modifier.padding(horizontal = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // University Academic Header Card
            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.School,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "جامعة صنعاء • كلية الآداب • قسم الآثار واللغات القديمة",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "الموسوعة الشاملة في فيلولوجيا وآثار اللغات السامية",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "إعداد الباحثة: سوسن علي الحضوري | إشراف: أ.د. أحمد فقعس",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Chapter Selector Horizontal Strip
            item {
                Text(
                    text = if (uiState.isEnglishUi) "Select Chapter" else "فصول الموسوعة الأكاديمية (50 فصلاً):",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(ChaptersData.ENCYCLOPEDIA_CHAPTERS) { chap ->
                        val isSelected = chap.id == uiState.selectedChapterId
                        ElevatedFilterChip(
                            selected = isSelected,
                            onClick = { viewModel.selectChapter(chap.id) },
                            label = {
                                Text(
                                    text = "فصل ${chap.chapterNumber}: ${chap.titleAr.take(20)}...",
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

            // Active Chapter Display Card
            item {
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Badge(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ) {
                                Text(
                                    text = "الوحدة ${currentChapter.unitNumber} • الفصل ${currentChapter.chapterNumber}",
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                    fontSize = 12.sp
                                )
                            }

                            Row {
                                IconButton(
                                    onClick = {
                                        viewModel.toggleBookmark(
                                            id = currentChapter.id,
                                            type = "chapter",
                                            title = currentChapter.titleAr,
                                            subtitle = "الفصل ${currentChapter.chapterNumber}"
                                        )
                                    },
                                    modifier = Modifier.testTag("bookmark_chapter_btn")
                                ) {
                                    Icon(
                                        imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                                        contentDescription = "حفظ علامة",
                                        tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                IconButton(
                                    onClick = { viewModel.toggleCitationModal(true) },
                                    modifier = Modifier.testTag("citation_btn")
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.FormatQuote,
                                        contentDescription = "توثيق واقتباس",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }

                                IconButton(
                                    onClick = { viewModel.toggleGlossaryScreen(true) },
                                    modifier = Modifier.testTag("open_glossary_screen_btn")
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.AutoStories,
                                        contentDescription = "معجم المصطلحات اللغوية السامية",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = currentChapter.titleAr,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = currentChapter.titleEn,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = FontFamily.Serif
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        Spacer(modifier = Modifier.height(12.dp))

                        // Interactive Philological Mode Banner
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isInteractiveGlossaryMode) RoyalGold.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isInteractiveGlossaryMode) RoyalGold.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outlineVariant
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Psychology,
                                        contentDescription = null,
                                        tint = if (isInteractiveGlossaryMode) RoyalGold else MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = "المعجم الفيلولوجي التفاعلي (Gemini AI)",
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = if (isInteractiveGlossaryMode) "انقر على أي كلمة في النص للحصول على تعريفها الأكاديمي وتأصيلها الفيلولوجي" else "انقر لتفعيل التعرف والتفاعل اللغوي الفوري",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                Switch(
                                    checked = isInteractiveGlossaryMode,
                                    onCheckedChange = { isInteractiveGlossaryMode = it },
                                    modifier = Modifier.testTag("toggle_interactive_glossary_mode_switch")
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Executive Summary Box
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "الملخص الأكاديمي:",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                if (isInteractiveGlossaryMode) {
                                    ClickableLinguisticText(
                                        text = currentChapter.summaryAr,
                                        fontSize = 15.sp,
                                        lineHeight = 23.sp,
                                        onWordClick = { word, context ->
                                            viewModel.openWordLookup(word, context, autoTriggerGemini = true)
                                        }
                                    )
                                } else {
                                    Text(
                                        text = currentChapter.summaryAr,
                                        style = MaterialTheme.typography.bodyMedium,
                                        lineHeight = 22.sp
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Full Chapter LaTeX Body
                        Text(
                            text = "المتن الأكاديمي والتحقيق الفيلولوجي:",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        if (isInteractiveGlossaryMode) {
                            ClickableLinguisticText(
                                text = currentChapter.fullLatexContent,
                                fontSize = 16.sp,
                                lineHeight = 26.sp,
                                onWordClick = { word, context ->
                                    viewModel.openWordLookup(word, context, autoTriggerGemini = true)
                                }
                            )
                        } else {
                            Text(
                                text = currentChapter.fullLatexContent,
                                style = MaterialTheme.typography.bodyLarge,
                                lineHeight = 26.sp,
                                fontFamily = FontFamily.Serif
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Footnotes & Academic References
                        if (currentChapter.footnotes.isNotEmpty()) {
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "الهوامش والمصادر المعتمدة:",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            currentChapter.footnotes.forEachIndexed { idx, fn ->
                                Text(
                                    text = "[${idx + 1}] $fn",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(40.dp)) }
        }
    }

    // Citation Modal Dialog
    if (uiState.isCitationModalOpen) {
        AlertDialog(
            onDismissRequest = { viewModel.toggleCitationModal(false) },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.FormatQuote, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("مولد الاقتباس الأكاديمي التلقائي")
                }
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "اختر صيغة التوثيق المعتمدة لنسخ الاستشهاد:",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(CitationFormat.values()) { fmt ->
                            FilterChip(
                                selected = uiState.selectedCitationFormat == fmt,
                                onClick = { viewModel.setCitationFormat(fmt) },
                                label = { Text(fmt.name.replace("_", " "), fontSize = 11.sp) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    val citationText = CitationGenerator.generateChapterCitation(
                        chapter = currentChapter,
                        format = uiState.selectedCitationFormat
                    )

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = citationText,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val citationText = CitationGenerator.generateChapterCitation(
                            chapter = currentChapter,
                            format = uiState.selectedCitationFormat
                        )
                        clipboardManager.setText(AnnotatedString(citationText))
                        viewModel.toggleCitationModal(false)
                    },
                    modifier = Modifier.testTag("copy_citation_confirm_btn")
                ) {
                    Icon(Icons.Filled.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("نسخ الاستشهاد")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.toggleCitationModal(false) }) {
                    Text("إغلاق")
                }
            }
        )
    }
}

