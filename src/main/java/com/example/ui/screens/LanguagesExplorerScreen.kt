package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.core.data.SemiticLanguagesData
import com.example.core.model.CivilizationMetadata
import com.example.core.model.InscriptionArtifact
import com.example.core.model.LanguageBranch
import com.example.core.model.SemiticLanguage
import com.example.ui.AppTab
import com.example.ui.MainViewModel
import com.example.ui.theme.RoyalGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguagesExplorerScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val persistedCivilizations by viewModel.persistedCivilizations.collectAsState()
    val persistedInscriptions by viewModel.persistedInscriptions.collectAsState()
    var isCompareMode by remember { mutableStateOf(false) }
    var expandedLangId by remember { mutableStateOf<String?>("akkadian") }

    val filteredLanguages = remember(uiState.selectedLanguageBranch, uiState.searchQuery) {
        SemiticLanguagesData.ALL_LANGUAGES.filter { lang ->
            val matchesBranch = uiState.selectedLanguageBranch == null || lang.branch == uiState.selectedLanguageBranch
            val matchesSearch = uiState.searchQuery.isEmpty() ||
                    lang.nameAr.contains(uiState.searchQuery, ignoreCase = true) ||
                    lang.nameEn.contains(uiState.searchQuery, ignoreCase = true) ||
                    lang.geographicalRegion.contains(uiState.searchQuery, ignoreCase = true)
            matchesBranch && matchesSearch
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("languages_explorer_screen")
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Search and Compare Toggle Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { viewModel.setSearchQuery(it) },
                placeholder = { Text("ابحث في الـ 35+ لغة سامية...", fontSize = 13.sp) },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                trailingIcon = {
                    if (uiState.searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.setSearchQuery("") }) {
                            Icon(Icons.Filled.Clear, contentDescription = "مسح")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .testTag("language_search_field")
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = { isCompareMode = !isCompareMode },
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isCompareMode) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Icon(
                    imageVector = Icons.Filled.CompareArrows,
                    contentDescription = "مقارنة ثنائية",
                    tint = if (isCompareMode) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Language Branches Filter Chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                FilterChip(
                    selected = uiState.selectedLanguageBranch == null,
                    onClick = { viewModel.setLanguageBranchFilter(null) },
                    label = { Text("جميع الفروع (35+)", fontSize = 12.sp) },
                    leadingIcon = {
                        if (uiState.selectedLanguageBranch == null) {
                            Icon(Icons.Filled.Check, contentDescription = null, modifier = Modifier.size(14.dp))
                        }
                    }
                )
            }
            items(LanguageBranch.values()) { branch ->
                val isSelected = uiState.selectedLanguageBranch == branch
                FilterChip(
                    selected = isSelected,
                    onClick = { viewModel.setLanguageBranchFilter(if (isSelected) null else branch) },
                    label = { Text(branch.titleAr, fontSize = 12.sp) },
                    leadingIcon = {
                        if (isSelected) {
                            Icon(Icons.Filled.Check, contentDescription = null, modifier = Modifier.size(14.dp))
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Main List of Languages or Comparison View
        if (isCompareMode) {
            ComparativeMatrixView(languages = SemiticLanguagesData.ALL_LANGUAGES.take(3))
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredLanguages) { lang ->
                    val isExpanded = expandedLangId == lang.id
                    val matchedCiv = persistedCivilizations.find { it.associatedLanguageId == lang.id }
                    val linkedInscriptions = persistedInscriptions.filter { it.languageId == lang.id }

                    LanguageCard(
                        language = lang,
                        civilization = matchedCiv,
                        linkedInscriptions = linkedInscriptions,
                        isExpanded = isExpanded,
                        onToggleExpand = {
                            expandedLangId = if (isExpanded) null else lang.id
                        },
                        onPlayAudio = {
                            viewModel.ttsManager.speakArabic(
                                "${lang.nameAr}. ${lang.sampleTextTranslationAr}"
                            )
                        },
                        onAskAi = {
                            viewModel.explainLanguageWithGemini(lang)
                        },
                        onSelectInscription = { inscriptionId ->
                            viewModel.selectInscription(inscriptionId)
                            viewModel.selectTab(AppTab.INSCRIPTIONS)
                        }
                    )
                }
                item { Spacer(modifier = Modifier.height(40.dp)) }
            }
        }
    }
}

@Composable
fun LanguageCard(
    language: SemiticLanguage,
    civilization: CivilizationMetadata?,
    linkedInscriptions: List<InscriptionArtifact>,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    onPlayAudio: () -> Unit,
    onAskAi: () -> Unit,
    onSelectInscription: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleExpand() }
            .testTag("lang_card_${language.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = language.nameAr,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "(${language.nameEn})",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        text = "${language.branch.titleAr} • ${language.period}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                IconButton(onClick = onToggleExpand) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = "تفاصيل"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Sample Original Script Inscription Box
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = language.sampleTextOriginal,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = language.sampleTextTransliteration,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = FontFamily.Serif,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = language.sampleTextTranslationAr,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(
                        onClick = onPlayAudio,
                        modifier = Modifier.testTag("play_lang_audio_${language.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.VolumeUp,
                            contentDescription = "نطق صوتي",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            // Expanded Comprehensive Philological Dossier
            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Divider(color = MaterialTheme.colorScheme.outlineVariant)
                    Spacer(modifier = Modifier.height(10.dp))

                    // Geographical & Historical Info
                    InfoSectionRow(title = "النطاق الجغرافي:", value = language.geographicalRegion)
                    InfoSectionRow(title = "الممالك والحواضر:", value = language.historicalKingdoms.joinToString("، "))
                    InfoSectionRow(title = "عدد الصوامت الأبجدية:", value = "${language.consonantCount} صامتاً")
                    InfoSectionRow(title = "نظام الكتابة والخط:", value = language.scriptType.titleAr)

                    Spacer(modifier = Modifier.height(10.dp))

                    // Phonological Rules
                    Text(
                        text = "الخصائص الفونولوجية والصوتية:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    language.phonologicalKeyFeatures.forEach { feature ->
                        Text(
                            text = "• $feature",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Morphological & Syntactic Features
                    Text(
                        text = "الخصائص الصرفية والنحوية:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    language.morphologicalFeatures.forEach { feature ->
                        Text(
                            text = "• $feature",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Pantheon
                    if (language.primaryDeities.isNotEmpty()) {
                        Text(
                            text = "البانثيون ومجمع الآلهة القديم:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            text = language.primaryDeities.joinToString(" • "),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    // Historical Civilization Metadata from Room Database
                    civilization?.let { civ ->
                        Spacer(modifier = Modifier.height(10.dp))
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Filled.AccountBalance,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "الحضارة والبيانات التاريخية: ${civ.nameAr}",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                InfoSectionRow("العاصمة التاريخية:", civ.capitalCityAr)
                                InfoSectionRow("النواة الجغرافية:", civ.geographicCoreAr)
                                InfoSectionRow("الفترة والازدهار:", civ.flourishedPeriod)
                                if (civ.majorRulers.isNotEmpty()) {
                                    InfoSectionRow("أشهر الحكام والملوك:", civ.majorRulers.joinToString("، "))
                                }
                                if (civ.tradeRoutesAr.isNotEmpty()) {
                                    InfoSectionRow("المسالك والشبكات التجارية:", civ.tradeRoutesAr)
                                }
                                if (civ.notableAchievementsAr.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    InfoSectionRow("الإنجازات الحضارية والتشريعية:", civ.notableAchievementsAr)
                                }
                            }
                        }
                    }

                    // Linked Inscriptions & Artifacts from Room Database
                    if (linkedInscriptions.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Filled.HistoryEdu,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "النقوش واللقى الأثرية المسجلة (${linkedInscriptions.size}):",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                linkedInscriptions.forEach { insc ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp)
                                            .clickable { onSelectInscription(insc.id) },
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = insc.titleAr,
                                                style = MaterialTheme.typography.bodyMedium,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                            Text(
                                                text = "${insc.dateCentury} • ${insc.discoveryLocation}",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        FilledTonalButton(
                                            onClick = { onSelectInscription(insc.id) },
                                            shape = RoundedCornerShape(8.dp),
                                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                                        ) {
                                            Text("عرض النقش", style = MaterialTheme.typography.labelSmall)
                                        }
                                    }
                                    HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp), color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = onAskAi,
                        colors = ButtonDefaults.buttonColors(containerColor = RoyalGold),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Psychology,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "دراسة فيلولوجية أكاديمية لـ ${language.nameAr} بـ Gemini AI",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InfoSectionRow(title: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(
            text = "$title ",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun ComparativeMatrixView(languages: List<SemiticLanguage>) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "المصفوفة الفيلولوجية المقارنة الثلاثية",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Comparative Table
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Text(text = "المعيار", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                languages.forEach { lang ->
                    Text(text = lang.nameAr, modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            MatrixRow(
                label = "نظام الخط",
                values = languages.map { it.scriptType.titleAr.take(12) }
            )
            MatrixRow(
                label = "العصر",
                values = languages.map { it.period.take(14) }
            )
            MatrixRow(
                label = "عدد الصوامت",
                values = languages.map { "${it.consonantCount} صامتاً" }
            )
        }
    }
}

@Composable
fun MatrixRow(label: String, values: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(text = label, modifier = Modifier.weight(1f), fontWeight = FontWeight.SemiBold, fontSize = 11.sp)
        values.forEach { v ->
            Text(text = v, modifier = Modifier.weight(1f), fontSize = 11.sp)
        }
    }
    Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
}
