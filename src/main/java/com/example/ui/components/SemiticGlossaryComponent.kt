package com.example.ui.components

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
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.data.SemiticGlossaryData
import com.example.core.model.GlossaryCategory
import com.example.core.model.SemiticGlossaryItem
import com.example.ui.MainViewModel
import com.example.ui.theme.RoyalGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SemiticGlossaryComponent(
    viewModel: MainViewModel,
    onClose: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf(uiState.glossarySearchQuery) }
    var selectedCategory by remember { mutableStateOf<GlossaryCategory?>(uiState.selectedGlossaryCategory) }

    val filteredTerms = remember(searchQuery, selectedCategory) {
        SemiticGlossaryData.searchTerms(searchQuery, selectedCategory)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("semitic_glossary_screen")
    ) {
        // Header Bar
        Surface(
            tonalElevation = 4.dp,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MenuBook,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "معجم المصطلحات اللغوية السامية",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "دليل المصطلحات الفيلولوجية، الصوتية، والنحوية المقارنة",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    if (onClose != null) {
                        IconButton(
                            onClick = onClose,
                            modifier = Modifier
                                .size(48.dp)
                                .testTag("close_glossary_component_btn")
                        ) {
                            Icon(imageVector = Icons.Filled.Close, contentDescription = "إغلاق")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Search Box
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        viewModel.updateGlossarySearch(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("glossary_search_textfield"),
                    placeholder = { Text("ابحث في المصطلحات اللغوية، القوانين الصوتية، أو الجذور...") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "بحث")
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = {
                                searchQuery = ""
                                viewModel.updateGlossarySearch("")
                            }) {
                                Icon(imageVector = Icons.Filled.Clear, contentDescription = "مسح البحث")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Category Filter Chips
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        FilterChip(
                            selected = selectedCategory == null,
                            onClick = {
                                selectedCategory = null
                                viewModel.selectGlossaryCategory(null)
                            },
                            label = { Text("كافة المجالات (${SemiticGlossaryData.GLOSSARY_ITEMS.size})") },
                            modifier = Modifier.heightIn(min = 48.dp)
                        )
                    }

                    items(GlossaryCategory.values()) { cat ->
                        FilterChip(
                            selected = selectedCategory == cat,
                            onClick = {
                                selectedCategory = if (selectedCategory == cat) null else cat
                                viewModel.selectGlossaryCategory(selectedCategory)
                            },
                            label = { Text(cat.titleAr) },
                            modifier = Modifier.heightIn(min = 48.dp)
                        )
                    }
                }
            }
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        // Results List
        if (filteredTerms.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Filled.SearchOff,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(56.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "لم يتم العثور على مصطلح يطابق «$searchQuery»",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            viewModel.openWordLookup(searchQuery, autoTriggerGemini = true)
                        },
                        modifier = Modifier.heightIn(min = 48.dp)
                    ) {
                        Icon(imageVector = Icons.Filled.Psychology, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("طلب تحقيق أكاديمي لـ «$searchQuery» عبر Gemini AI")
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "عدد المصطلحات المتوفرة: ${filteredTerms.size}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }

                items(filteredTerms, key = { it.id }) { term ->
                    GlossaryTermCard(
                        term = term,
                        onWordClick = {
                            viewModel.selectGlossaryTerm(term)
                        },
                        onPlayTts = {
                            viewModel.toggleTts("${term.termAr}. ${term.academicDefinitionAr}")
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

@Composable
fun GlossaryTermCard(
    term: SemiticGlossaryItem,
    onWordClick: () -> Unit,
    onPlayTts: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onWordClick)
            .testTag("glossary_term_card_${term.id}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header Row: Category Badge & English Name
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = term.category.titleAr,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                Text(
                    text = term.termEn,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Arabic Term Title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = term.termAr,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                IconButton(
                    onClick = onPlayTts,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "استماع صوتي",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Academic Definition Preview
            Text(
                text = term.academicDefinitionAr,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 22.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3
            )

            // Formula rule badge if available
            if (term.linguisticFormula.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = term.linguisticFormula,
                        style = MaterialTheme.typography.labelSmall,
                        color = RoyalGold,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Action Row: Click for Gemini Deep Dive
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "اللغات: ${term.relatedLanguages.take(3).joinToString("، ")}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )

                OutlinedButton(
                    onClick = onWordClick,
                    modifier = Modifier
                        .heightIn(min = 40.dp)
                        .testTag("gemini_deep_dive_btn_${term.id}"),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Psychology,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("تعريف وتحقيق Gemini", style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}
