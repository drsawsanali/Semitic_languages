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
import com.example.core.data.LearningData
import com.example.core.export.CitationGenerator
import com.example.core.model.CitationFormat
import com.example.core.model.FlashcardItem
import com.example.core.model.QuizQuestion
import com.example.ui.MainViewModel
import com.example.ui.components.TimelineVisualizationModule

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningCenterScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedLearningTab by remember { mutableStateOf(0) } // 0: Quiz, 1: Flashcards, 2: Timeline, 3: Bibliography

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("learning_center_screen")
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Navigation Tab Row
        TabRow(
            selectedTabIndex = selectedLearningTab,
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .testTag("learning_center_tabs")
        ) {
            Tab(
                selected = selectedLearningTab == 0,
                onClick = { selectedLearningTab = 0 },
                text = { Text(if (uiState.isEnglishUi) "Quizzes" else "الاختبارات", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                modifier = Modifier.testTag("tab_quizzes")
            )
            Tab(
                selected = selectedLearningTab == 1,
                onClick = { selectedLearningTab = 1 },
                text = { Text(if (uiState.isEnglishUi) "Grammar Cards" else "بطاقات القواعد", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                modifier = Modifier.testTag("tab_grammar_cards")
            )
            Tab(
                selected = selectedLearningTab == 2,
                onClick = { selectedLearningTab = 2 },
                text = { Text(if (uiState.isEnglishUi) "Timeline" else "الخط الزمني", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                modifier = Modifier.testTag("tab_timeline")
            )
            Tab(
                selected = selectedLearningTab == 3,
                onClick = { selectedLearningTab = 3 },
                text = { Text(if (uiState.isEnglishUi) "Bibliography" else "المراجع", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                modifier = Modifier.testTag("tab_bibliography")
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        when (selectedLearningTab) {
            0 -> PhilologicalQuizModule(viewModel = viewModel)
            1 -> LeitnerFlashcardsModule(viewModel = viewModel)
            2 -> TimelineVisualizationModule(viewModel = viewModel)
            3 -> InteractiveBibliographyModule(viewModel = viewModel)
        }
    }
}

@Composable
fun PhilologicalQuizModule(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val questions = LearningData.QUIZ_QUESTIONS
    val currentQ = questions.getOrNull(uiState.activeQuizIndex)
    val isEnglish = uiState.isEnglishUi

    if (uiState.isQuizFinished) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("quiz_finished_card")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Filled.EmojiEvents,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = if (isEnglish) "Academic Quiz Completed Successfully!" else "اكتمل الاختبار الأكاديمي بنجاح!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = if (isEnglish) "Final Score: ${uiState.quizScore} of ${questions.size}" else "درجتك النهائية: ${uiState.quizScore} من أصل ${questions.size}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { viewModel.resetQuiz() },
                    modifier = Modifier.testTag("quiz_restart_btn")
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(if (isEnglish) "Retake Quiz" else "إعادة الاختبار")
                }
            }
        }
    } else if (currentQ != null) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("quiz_question_card")
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Badge(containerColor = MaterialTheme.colorScheme.primary) {
                        Text(
                            text = if (isEnglish) "Question ${uiState.activeQuizIndex + 1} of ${questions.size}" else "السؤال ${uiState.activeQuizIndex + 1} من ${questions.size}",
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            fontSize = 11.sp
                        )
                    }
                    Text(
                        text = if (isEnglish) "Score: ${uiState.quizScore}" else "النقاط: ${uiState.quizScore}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = if (isEnglish && currentQ.questionEn.isNotBlank()) currentQ.questionEn else currentQ.questionAr,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Options List
                currentQ.optionsAr.forEachIndexed { idx, opt ->
                    val isSelected = uiState.quizSelectedOption == idx
                    val isCorrect = idx == currentQ.correctOptionIndex
                    val hasAnswered = uiState.quizSelectedOption != null

                    val backgroundColor = when {
                        !hasAnswered -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        isSelected && isCorrect -> Color(0xFF2E7D32).copy(alpha = 0.2f)
                        isSelected && !isCorrect -> MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f)
                        isCorrect -> Color(0xFF2E7D32).copy(alpha = 0.2f)
                        else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                    }

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = backgroundColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable(enabled = !hasAnswered) {
                                viewModel.answerQuizQuestion(idx)
                            }
                            .border(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .testTag("quiz_option_$idx")
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${idx + 1}.",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.width(24.dp)
                            )
                            Text(
                                text = opt,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // Explanation & Next Button
                if (uiState.quizSelectedOption != null) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = if (isEnglish) "Philological Explanation & Analysis:" else "الشرح والتحليل الفيلولوجي:",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = currentQ.explanationAr, style = MaterialTheme.typography.bodySmall)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = { viewModel.nextQuizQuestion() },
                        modifier = Modifier
                            .align(Alignment.End)
                            .testTag("quiz_next_btn")
                    ) {
                        Text(
                            if (isEnglish) {
                                if (uiState.activeQuizIndex + 1 < questions.size) "Next Question" else "Finish & Save Score"
                            } else {
                                if (uiState.activeQuizIndex + 1 < questions.size) "السؤال التالي" else "إنهاء وحفظ النتيجة"
                            }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(Icons.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun LeitnerFlashcardsModule(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val flashcardProgress by viewModel.flashcardProgressList.collectAsState()
    val isEnglish = uiState.isEnglishUi

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .testTag("leitner_flashcards_list")
    ) {
        items(LearningData.FLASHCARDS) { card ->
            val progress = flashcardProgress.find { it.cardId == card.id }
            val currentBox = progress?.boxLevel ?: 0
            var isFlipped by remember { mutableStateOf(false) }

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isFlipped = !isFlipped }
                    .testTag("flashcard_${card.id}")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Badge(containerColor = MaterialTheme.colorScheme.secondary) {
                            Text(
                                text = card.category,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                                fontSize = 10.sp
                            )
                        }

                        Text(
                            text = if (isEnglish) {
                                when (currentBox) {
                                    0 -> "Box 1 (New)"
                                    1 -> "Box 2 (Review)"
                                    else -> "Box 3 (Mastered ✅)"
                                }
                            } else {
                                when (currentBox) {
                                    0 -> "صندوق 1 (جديدة)"
                                    1 -> "صندوق 2 (مراجعة)"
                                    else -> "صندوق 3 (متقنة ✅)"
                                }
                            },
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (isEnglish && card.titleEn.isNotBlank()) card.titleEn else card.ruleTitleAr,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    if (!isFlipped) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = if (isEnglish) "Formula: ${card.formula}" else "الصيغة: ${card.formula}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isEnglish) "Click to flip card & reveal evidence ↻" else "انقر لقلب البطاقة وكشف الشرح والشواهد المنقوشة ↻",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        Text(text = card.explanationAr, style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isEnglish) "Inscribed Evidence: ${card.inscribedEvidence}" else "الشواهد الأثرية: ${card.inscribedEvidence}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            fontFamily = FontFamily.Serif
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Box Upgrade Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { viewModel.updateFlashcardBox(card.id, 0) },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(if (isEnglish) "Review (Box 1)" else "إعادة مراجعة", fontSize = 10.sp)
                            }
                            Button(
                                onClick = { viewModel.updateFlashcardBox(card.id, currentBox + 1) },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(if (isEnglish) "Mastered (+1)" else "أتقنتها (+1)", fontSize = 10.sp)
                            }
                        }
                    }
                }
            }
        }
        item { Spacer(modifier = Modifier.height(40.dp)) }
    }
}

@Composable
fun ChronologyTimelineModule(viewModel: MainViewModel? = null) {
    if (viewModel != null) {
        TimelineVisualizationModule(viewModel = viewModel)
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(LearningData.CHRONOLOGY_EVENTS) { event ->
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.width(60.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AccessTime,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = if (event.displayDateAr.isNotBlank()) event.displayDateAr else "${event.yearBce} ق.م",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = event.titleAr,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = event.descriptionAr, style = MaterialTheme.typography.bodySmall)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "اللغة والفرع: ${event.languageAssociated}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(40.dp)) }
        }
    }
}

@Composable
fun InteractiveBibliographyModule(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val clipboardManager = LocalClipboardManager.current
    val isEnglish = uiState.isEnglishUi
    var selectedFormat by remember { mutableStateOf(CitationFormat.APA_7) }
    var copiedBibId by remember { mutableStateOf<String?>("") }

    Column(modifier = Modifier.fillMaxSize()) {
        // Citation Format Selector Bar
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isEnglish) "Format:" else "صيغة التوثيق:",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 4.dp)
                )

                listOf(
                    CitationFormat.APA_7 to "APA 7",
                    CitationFormat.MLA_9 to "MLA 9",
                    CitationFormat.CHICAGO_17 to "Chicago",
                    CitationFormat.BIBTEX to "BibTeX"
                ).forEach { (format, label) ->
                    FilterChip(
                        selected = selectedFormat == format,
                        onClick = { selectedFormat = format },
                        label = { Text(label, fontSize = 11.sp) },
                        modifier = Modifier.testTag("citation_format_${label.lowercase()}")
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .testTag("interactive_bibliography_list")
        ) {
            items(LearningData.BIBLIOGRAPHY_ENTRIES) { bib ->
                val citationText = remember(bib, selectedFormat) {
                    CitationGenerator.generateBibliographyCitation(bib, selectedFormat)
                }

                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("bibliography_card_${bib.id}")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = bib.title,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = {
                                    clipboardManager.setText(AnnotatedString(citationText))
                                    copiedBibId = bib.id
                                },
                                modifier = Modifier.testTag("copy_citation_${bib.id}")
                            ) {
                                Icon(
                                    imageVector = if (copiedBibId == bib.id) Icons.Filled.Check else Icons.Filled.ContentCopy,
                                    contentDescription = if (isEnglish) "Copy Citation" else "نسخ المرجع",
                                    tint = if (copiedBibId == bib.id) Color(0xFF2E7D32) else MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${if (isEnglish) "Author" else "المؤلف"}: ${bib.author} (${bib.year}) • ${bib.publisher}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = bib.summaryAr, style = MaterialTheme.typography.bodySmall)

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "${if (isEnglish) "Field" else "مجال التحقيق"}: ${bib.topics.joinToString(" • ")}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )

                        Spacer(modifier = Modifier.height(6.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = citationText,
                                style = MaterialTheme.typography.bodySmall,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 10.sp,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(40.dp)) }
        }
    }
}
