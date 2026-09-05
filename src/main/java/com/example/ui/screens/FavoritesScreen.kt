package com.example.ui.screens

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.database.BookmarkEntity
import com.example.ui.AppTab
import com.example.ui.MainViewModel

/**
 * Unified Favorites & Bookmarks Screen for the Semitic Encyclopedia.
 * Displays user's saved academic chapters, inscriptions, civilizations, and languages.
 */
@Composable
fun FavoritesScreen(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val isEnglish = uiState.isEnglishUi
    val bookmarks by viewModel.bookmarks.collectAsState(initial = emptyList())

    var selectedTypeFilter by remember { mutableStateOf("ALL") }

    val filteredList = remember(bookmarks, selectedTypeFilter) {
        if (selectedTypeFilter == "ALL") bookmarks
        else bookmarks.filter { it.itemType.equals(selectedTypeFilter, ignoreCase = true) }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("favorites_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("favorites_hero_banner"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(44.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Filled.Bookmark, contentDescription = null)
                            }
                        }
                        Column {
                            Text(
                                text = if (isEnglish) "Favorites & Bookmarked Studies" else "المفضلة والمحفوظات الأكاديمية",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = if (isEnglish)
                                    "Your Personal Academic Repository of Semitic Studies"
                                else
                                    "سجلك الأكاديمي الشخصي للفصول، النقوش، والحضارات المحفوظة",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Filter Chips
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            FilterChip(
                                selected = selectedTypeFilter == "ALL",
                                onClick = { selectedTypeFilter = "ALL" },
                                label = { Text("${if (isEnglish) "All Items" else "كافة المحفوظات"} (${bookmarks.size})") },
                                modifier = Modifier.testTag("fav_filter_all")
                            )
                        }
                        item {
                            FilterChip(
                                selected = selectedTypeFilter == "chapter",
                                onClick = { selectedTypeFilter = "chapter" },
                                label = { Text(if (isEnglish) "Chapters" else "الفصول والمباحث") },
                                modifier = Modifier.testTag("fav_filter_chapter")
                            )
                        }
                        item {
                            FilterChip(
                                selected = selectedTypeFilter == "civilization",
                                onClick = { selectedTypeFilter = "civilization" },
                                label = { Text(if (isEnglish) "Civilizations" else "الحضارات والممالك") },
                                modifier = Modifier.testTag("fav_filter_civilization")
                            )
                        }
                        item {
                            FilterChip(
                                selected = selectedTypeFilter == "inscription",
                                onClick = { selectedTypeFilter = "inscription" },
                                label = { Text(if (isEnglish) "Inscriptions" else "النقوش واللقى") },
                                modifier = Modifier.testTag("fav_filter_inscription")
                            )
                        }
                        item {
                            FilterChip(
                                selected = selectedTypeFilter == "language",
                                onClick = { selectedTypeFilter = "language" },
                                label = { Text(if (isEnglish) "Languages" else "اللغات السامية") },
                                modifier = Modifier.testTag("fav_filter_language")
                            )
                        }
                    }
                }
            }
        }

        // Empty state
        if (filteredList.isEmpty()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                        .testTag("favorites_empty_state"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.BookmarkBorder,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(54.dp)
                        )
                        Text(
                            text = if (isEnglish) "No Bookmarks Saved Yet" else "لا توجد عناصر محفوظة في المفضلة حالياً",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (isEnglish)
                                "Explore the Encyclopedia Reader, Inscriptions Archive, or Civilizations to bookmark items."
                            else
                                "تصفح فصول القارئ الموسوعي أو أرشيف النقوش أو الحضارات واضغط على أيقونة النجمة أو المفضلة لحفظها هنا للرجوع السريع.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(
                                onClick = { viewModel.switchTab(AppTab.READER) },
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(Icons.Filled.MenuBook, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(if (isEnglish) "Browse Reader" else "القارئ الموسوعي")
                            }

                            OutlinedButton(
                                onClick = { viewModel.switchTab(AppTab.INSCRIPTIONS) },
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(Icons.Filled.HistoryEdu, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(if (isEnglish) "Inscriptions" else "أرشيف النقوش")
                            }
                        }
                    }
                }
            }
        } else {
            items(filteredList, key = { it.id }) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("fav_item_card_${item.id}"),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = when (item.itemType) {
                                    "chapter" -> MaterialTheme.colorScheme.primaryContainer
                                    "civilization" -> MaterialTheme.colorScheme.tertiaryContainer
                                    "inscription" -> MaterialTheme.colorScheme.secondaryContainer
                                    else -> MaterialTheme.colorScheme.surfaceVariant
                                },
                                modifier = Modifier.size(42.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = when (item.itemType) {
                                            "chapter" -> Icons.Filled.MenuBook
                                            "civilization" -> Icons.Filled.AccountBalance
                                            "inscription" -> Icons.Filled.HistoryEdu
                                            else -> Icons.Filled.Language
                                        },
                                        contentDescription = null,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                            }

                            Column {
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = item.subtitle,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = when (item.itemType) {
                                        "chapter" -> if (isEnglish) "Academic Chapter" else "فصل أكاديمي"
                                        "civilization" -> if (isEnglish) "Ancient Kingdom" else "حضارة ومملكة"
                                        "inscription" -> if (isEnglish) "Royal Inscription" else "نقش ومسلة أثرية"
                                        else -> if (isEnglish) "Language" else "لغة سامية"
                                    },
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            // TTS Audio Listen
                            IconButton(
                                onClick = {
                                    viewModel.toggleTts("${item.title}. ${item.subtitle}")
                                },
                                modifier = Modifier.testTag("fav_tts_btn_${item.id}")
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.VolumeUp,
                                    contentDescription = "TTS",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }

                            // Open/Navigate
                            IconButton(
                                onClick = {
                                    when (item.itemType) {
                                        "chapter" -> {
                                            viewModel.selectChapter(item.id)
                                            viewModel.switchTab(AppTab.READER)
                                        }
                                        "civilization" -> {
                                            viewModel.switchTab(AppTab.CIVILIZATIONS)
                                        }
                                        "inscription" -> {
                                            viewModel.selectInscription(item.id)
                                            viewModel.switchTab(AppTab.INSCRIPTIONS)
                                        }
                                        "language" -> {
                                            viewModel.selectLanguage(item.id)
                                            viewModel.switchTab(AppTab.LANGUAGES)
                                        }
                                    }
                                },
                                modifier = Modifier.testTag("fav_open_btn_${item.id}")
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowForward,
                                    contentDescription = "Open Item",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }

                            // Delete Bookmark
                            IconButton(
                                onClick = {
                                    viewModel.deleteBookmark(item.id)
                                    if (item.itemType == "language") {
                                        viewModel.toggleLanguageFavorite(item.id, false)
                                    } else if (item.itemType == "inscription") {
                                        viewModel.toggleInscriptionFavorite(item.id, false)
                                    }
                                },
                                modifier = Modifier.testTag("fav_delete_btn_${item.id}")
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.DeleteOutline,
                                    contentDescription = "Remove from Favorites",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
