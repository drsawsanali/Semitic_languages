package com.example.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.core.data.OfflineSearchIndexEngine
import com.example.core.data.OfflineSearchResult
import com.example.core.data.SearchCategory
import com.example.ui.AppTab
import com.example.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfflineSearchDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(SearchCategory.ALL) }

    val searchResults = remember(searchQuery, selectedCategory) {
        if (searchQuery.isBlank()) {
            emptyList()
        } else {
            OfflineSearchIndexEngine.search(searchQuery, selectedCategory)
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.90f)
                .clip(RoundedCornerShape(24.dp)),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                // Header with Title and Close Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = if (uiState.isEnglishUi) "Offline Search Index" else "محرك وفهرس البحث المحلي",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = if (uiState.isEnglishUi) "Search across all Semitic languages, roots, inscriptions & chapters" else "بحث شامل وسريع دون الحاجة للإنترنت في كافة الأقسام",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    IconButton(onClick = onDismiss, modifier = Modifier.testTag("close_search_btn")) {
                        Icon(Icons.Filled.Close, contentDescription = "Close")
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Search Input Field
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            if (uiState.isEnglishUi) "Type keyword, root, inscription or sound law..." else "ابحث عن كلمة، جذر، نقش، قانون صوتي، أو موقع أثري...",
                            fontSize = 13.sp
                        )
                    },
                    leadingIcon = {
                        Icon(Icons.Filled.ManageSearch, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Filled.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("offline_search_input")
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Category Filter Chips
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(SearchCategory.values()) { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = {
                                Text(
                                    text = if (uiState.isEnglishUi) category.titleEn else category.titleAr,
                                    fontSize = 12.sp
                                )
                            },
                            leadingIcon = {
                                val icon = getCategoryIcon(category)
                                Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(8.dp))

                // Search Results or Empty/Intro State
                if (searchQuery.isBlank()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(24.dp)
                        ) {
                            Icon(
                                Icons.Outlined.TravelExplore,
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = if (uiState.isEnglishUi) "Instant Offline Semitic Corpus Search" else "فهرس البحث الشامل للغات والنقوش السامية",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = if (uiState.isEnglishUi) "Try searching: \"ميشع\", \"حمورابي\", \"التحول الكنعاني\", \"أوغاريت\", \"šalāmu\", \"أحيرام\"" else "جرّب البحث عن: «ميشع»، «حمورابي»، «التحول الكنعاني»، «صرواح»، «سلام»، «أحيرام»",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                } else if (searchResults.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                Icons.Outlined.SearchOff,
                                contentDescription = null,
                                modifier = Modifier.size(56.dp),
                                tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = if (uiState.isEnglishUi) "No matching records found" else "لم يتم العثور على نتائج مطابقة",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = if (uiState.isEnglishUi) "Try a different keyword or broaden the category filter" else "جرّب كتابة كلمة أخرى أو تغيير تصنيف البحث أعلاه",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (uiState.isEnglishUi) "Found ${searchResults.size} results" else "تم العثور على ${searchResults.size} نتيجة",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(searchResults, key = { it.id }) { item ->
                            SearchResultCard(
                                item = item,
                                isEnglishUi = uiState.isEnglishUi,
                                onClick = {
                                    viewModel.selectTab(item.targetTab)
                                    when (item.targetTab) {
                                        AppTab.READER -> viewModel.selectChapter(item.targetItemId)
                                        AppTab.INSCRIPTIONS -> viewModel.selectInscription(item.targetItemId)
                                        AppTab.LANGUAGES -> viewModel.selectLanguage(item.targetItemId)
                                        AppTab.MAP -> viewModel.setActiveSite(item.targetItemId)
                                        else -> {}
                                    }
                                    onDismiss()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchResultCard(
    item: OfflineSearchResult,
    isEnglishUi: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("search_result_${item.id}"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = getCategoryIcon(item.category),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isEnglishUi) item.category.titleEn else item.category.titleAr,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f)
                ) {
                    Text(
                        text = if (isEnglishUi) item.targetTab.titleEn.split(" ").first() else item.targetTab.titleAr.split(" ").first(),
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = item.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = item.subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = item.snippet,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

private fun getCategoryIcon(category: SearchCategory): ImageVector {
    return when (category) {
        SearchCategory.ALL -> Icons.Filled.GridView
        SearchCategory.LANGUAGES -> Icons.Filled.Language
        SearchCategory.INSCRIPTIONS -> Icons.Filled.HistoryEdu
        SearchCategory.CHAPTERS -> Icons.Filled.MenuBook
        SearchCategory.ROOTS -> Icons.Filled.Translate
        SearchCategory.PHONETICS -> Icons.Filled.GraphicEq
        SearchCategory.SITES -> Icons.Filled.Place
        SearchCategory.FLASHCARDS -> Icons.Filled.School
    }
}
