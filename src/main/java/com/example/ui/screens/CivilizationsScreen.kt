package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.model.CivilizationMetadata
import com.example.core.model.LanguageBranch
import com.example.ui.AppTab
import com.example.ui.MainViewModel

/**
 * Academic Civilizations Screen showcasing the ancient Semitic kingdoms,
 * their historical geography, major rulers, religious pantheons, trade routes,
 * legislative achievements, and epigraphic heritage.
 */
@Composable
fun CivilizationsScreen(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val isEnglish = uiState.isEnglishUi
    val civilizations by viewModel.persistedCivilizations.collectAsState(initial = emptyList())
    val bookmarks by viewModel.bookmarks.collectAsState(initial = emptyList())

    var searchQuery by remember { mutableStateOf("") }
    var selectedBranchFilter by remember { mutableStateOf<LanguageBranch?>(null) }
    var expandedCivilizationId by remember { mutableStateOf<String?>(null) }

    val filteredCivilizations = remember(civilizations, searchQuery, selectedBranchFilter) {
        civilizations.filter { civ ->
            val matchesBranch = selectedBranchFilter == null || civ.branch == selectedBranchFilter
            val matchesSearch = searchQuery.isBlank() ||
                    civ.nameAr.contains(searchQuery, ignoreCase = true) ||
                    civ.nameEn.contains(searchQuery, ignoreCase = true) ||
                    civ.capitalCityAr.contains(searchQuery, ignoreCase = true) ||
                    civ.geographicCoreAr.contains(searchQuery, ignoreCase = true) ||
                    civ.majorRulers.any { it.contains(searchQuery, ignoreCase = true) } ||
                    civ.pantheonDeities.any { it.contains(searchQuery, ignoreCase = true) }
            matchesBranch && matchesSearch
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("civilizations_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("civilizations_hero_banner"),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.size(44.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Filled.AccountBalance, contentDescription = null)
                            }
                        }
                        Column {
                            Text(
                                text = if (isEnglish) "Ancient Semitic Civilizations & Kingdoms" else "الحضارات والممالك السامية الكبرى",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = if (isEnglish)
                                    "Historical Geography, Rulers, Religious Pantheons & Epigraphy"
                                else
                                    "الجغرافيا التاريخية، العواصم، مجمع الآلهة، والمنجزات الإبيغرافية",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Search Field
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("civilizations_search_input"),
                        placeholder = {
                            Text(if (isEnglish) "Search by kingdom, ruler, capital or deity..." else "ابحث باسم المملكة، العاصمة، الحاكم أو الإله...")
                        },
                        leadingIcon = {
                            Icon(Icons.Filled.Search, contentDescription = null)
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { searchQuery = "" }) {
                                    Icon(Icons.Filled.Close, contentDescription = "Clear")
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Branch Filters
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            FilterChip(
                                selected = selectedBranchFilter == null,
                                onClick = { selectedBranchFilter = null },
                                label = { Text(if (isEnglish) "All Branches" else "كافة الفروع") },
                                modifier = Modifier.testTag("civ_filter_all")
                            )
                        }
                        items(LanguageBranch.entries.toTypedArray()) { branch ->
                            FilterChip(
                                selected = selectedBranchFilter == branch,
                                onClick = {
                                    selectedBranchFilter = if (selectedBranchFilter == branch) null else branch
                                },
                                label = { Text(if (isEnglish) branch.titleEn else branch.titleAr) },
                                modifier = Modifier.testTag("civ_filter_${branch.id}")
                            )
                        }
                    }
                }
            }
        }

        // Count indicator
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isEnglish)
                        "${filteredCivilizations.size} Kingdoms & Civilizations"
                    else
                        "${filteredCivilizations.size} حضارة ومملكة موثقة",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (isEnglish) "Tap card to expand details" else "اضغط على البطاقة لتوسيع التفاصيل",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Civilizations List
        items(filteredCivilizations, key = { it.id }) { civ ->
            val isExpanded = expandedCivilizationId == civ.id
            val isBookmarked = bookmarks.any { it.id == civ.id }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expandedCivilizationId = if (isExpanded) null else civ.id
                    }
                    .testTag("civilization_card_${civ.id}"),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Header Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = if (isEnglish) civ.nameEn else civ.nameAr,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                                ) {
                                    Text(
                                        text = civ.flourishedPeriod,
                                        style = MaterialTheme.typography.labelSmall,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    Icons.Filled.LocationCity,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "${if (isEnglish) "Capital: " else "العاصمة: "}${civ.capitalCityAr}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        // Bookmark & TTS buttons
                        Row {
                            IconButton(
                                onClick = {
                                    val summaryText = "${civ.nameAr}. عاصمتها ${civ.capitalCityAr}. ازدهرت في ${civ.flourishedPeriod}. امتدت في ${civ.geographicCoreAr}. ومن أبرز إنجازاتها: ${civ.notableAchievementsAr}"
                                    viewModel.toggleTts(summaryText)
                                },
                                modifier = Modifier.testTag("tts_civ_${civ.id}")
                            ) {
                                Icon(
                                    imageVector = if (uiState.isPlayingTts) Icons.Filled.VolumeOff else Icons.Filled.VolumeUp,
                                    contentDescription = "TTS Read",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }

                            IconButton(
                                onClick = {
                                    viewModel.toggleBookmark(
                                        id = civ.id,
                                        type = "civilization",
                                        title = civ.nameAr,
                                        subtitle = "${civ.capitalCityAr} • ${civ.flourishedPeriod}"
                                    )
                                },
                                modifier = Modifier.testTag("bookmark_civ_${civ.id}")
                            ) {
                                Icon(
                                    imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                                    contentDescription = "Bookmark",
                                    tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Geographic Core
                    Text(
                        text = "${if (isEnglish) "Core Geography: " else "النطاق الجغرافي: "}${civ.geographicCoreAr}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    // Expandable Details Section
                    AnimatedVisibility(visible = isExpanded) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                        ) {
                            Divider(modifier = Modifier.padding(vertical = 8.dp))

                            // Major Rulers
                            if (civ.majorRulers.isNotEmpty()) {
                                Text(
                                    text = if (isEnglish) "Major Rulers & Dynasties:" else "أبرز الملوك والحكام:",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    civ.majorRulers.forEach { ruler ->
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = MaterialTheme.colorScheme.surface,
                                            border = androidx.compose.foundation.BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant)
                                        ) {
                                            Text(
                                                text = ruler,
                                                style = MaterialTheme.typography.labelSmall,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // Religious Pantheon
                            if (civ.pantheonDeities.isNotEmpty()) {
                                Text(
                                    text = if (isEnglish) "Religious Pantheon & Deities:" else "مجمع الآلهة والبانثيون الديني:",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    civ.pantheonDeities.forEach { deity ->
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                                        ) {
                                            Text(
                                                text = deity,
                                                style = MaterialTheme.typography.labelSmall,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // Trade Routes
                            if (civ.tradeRoutesAr.isNotBlank()) {
                                Text(
                                    text = if (isEnglish) "Trade Routes & Networks:" else "المسالك والشبكات التجارية:",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = civ.tradeRoutesAr,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            // Notable Achievements
                            if (civ.notableAchievementsAr.isNotBlank()) {
                                Text(
                                    text = if (isEnglish) "Civilizational & Legislative Achievements:" else "الإنجازات الحضارية والتشريعية:",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = civ.notableAchievementsAr,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                            }

                            // Actions
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                // Gemini Deep Dive Button
                                Button(
                                    onClick = {
                                        viewModel.explainCivilizationWithGemini(civ)
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("gemini_civ_btn_${civ.id}"),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Icon(Icons.Filled.Psychology, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(if (isEnglish) "Gemini Deep Dive" else "دراسة عبر الذكاء الاصطناعي", fontSize = 12.sp)
                                }

                                // View Inscriptions Button
                                OutlinedButton(
                                    onClick = {
                                        viewModel.switchTab(AppTab.INSCRIPTIONS)
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("view_inscriptions_civ_${civ.id}"),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Icon(Icons.Filled.HistoryEdu, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(if (isEnglish) "View Inscriptions" else "أرشيف النقوش", fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
