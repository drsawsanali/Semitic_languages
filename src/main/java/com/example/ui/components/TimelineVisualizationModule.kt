package com.example.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.data.LearningData
import com.example.core.model.ChronologyEvent
import com.example.core.model.LanguageBranch
import com.example.ui.AppTab
import com.example.ui.MainViewModel
import kotlinx.coroutines.delay

enum class TimelineViewMode(val titleAr: String, val titleEn: String) {
    VERTICAL_STEPPER("المسار الزمني الرأسي", "Vertical Stepper"),
    EPOCH_MATRIX("مصفوفة التزامن التاريخي", "Epoch Matrix"),
    SCRIPT_EVOLUTION("شجرة تطور الأبجديات", "Script Evolution")
}

@Composable
fun TimelineVisualizationModule(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedViewMode by remember { mutableStateOf(TimelineViewMode.VERTICAL_STEPPER) }
    var selectedBranchFilter by remember { mutableStateOf<LanguageBranch?>(null) }
    var selectedEraFilter by remember { mutableStateOf<String?>(null) }
    var expandedEventId by remember { mutableStateOf<String?>("ev_7") }
    var isAutoPlaying by remember { mutableStateOf(false) }
    var activeAutoPlayIndex by remember { mutableStateOf(0) }

    val allEvents = remember { LearningData.TIMELINE_EVENTS.sortedBy { it.yearBceOrCe } }

    val filteredEvents = remember(allEvents, selectedBranchFilter, selectedEraFilter) {
        allEvents.filter { event ->
            (selectedBranchFilter == null || event.languageBranch == selectedBranchFilter) &&
            (selectedEraFilter == null || event.eraAr == selectedEraFilter)
        }
    }

    val availableEras = remember(allEvents) {
        allEvents.map { it.eraAr }.distinct().filter { it.isNotBlank() }
    }

    // Auto-play effect
    LaunchedEffect(isAutoPlaying) {
        if (isAutoPlaying) {
            while (isAutoPlaying && filteredEvents.isNotEmpty()) {
                activeAutoPlayIndex = (activeAutoPlayIndex + 1) % filteredEvents.size
                expandedEventId = filteredEvents[activeAutoPlayIndex].id
                delay(3500)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("timeline_visualization_module")
    ) {
        // Top Control Header: View Mode Switcher
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                // View Selector Row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TimelineViewMode.values().forEach { mode ->
                        val isSelected = selectedViewMode == mode
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedViewMode = mode },
                            label = {
                                Text(
                                    text = if (uiState.isEnglishUi) mode.titleEn else mode.titleAr,
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = when (mode) {
                                        TimelineViewMode.VERTICAL_STEPPER -> Icons.Filled.Timeline
                                        TimelineViewMode.EPOCH_MATRIX -> Icons.Filled.GridView
                                        TimelineViewMode.SCRIPT_EVOLUTION -> Icons.Filled.AccountTree
                                    },
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                                selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Auto-Play & Filter summary bar
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Auto play button
                    Button(
                        onClick = { isAutoPlaying = !isAutoPlaying },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isAutoPlaying) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primaryContainer,
                            contentColor = if (isAutoPlaying) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = if (isAutoPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isAutoPlaying) (if (uiState.isEnglishUi) "Pause Journey" else "إيقاف الرحلة")
                            else (if (uiState.isEnglishUi) "Auto-Play Timeline" else "تشغيل الرحلة الزمنية"),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Milestone counter badge
                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = if (uiState.isEnglishUi) "${filteredEvents.size} Milestones" else "${filteredEvents.size} محطة تاريخية",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Filters LazyRow (Branches & Eras)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                FilterChip(
                    selected = selectedBranchFilter == null && selectedEraFilter == null,
                    onClick = {
                        selectedBranchFilter = null
                        selectedEraFilter = null
                    },
                    label = { Text(if (uiState.isEnglishUi) "All Eras & Branches" else "كافة العصور والفروع", fontSize = 11.sp) }
                )
            }

            items(LanguageBranch.values()) { branch ->
                FilterChip(
                    selected = selectedBranchFilter == branch,
                    onClick = {
                        selectedBranchFilter = if (selectedBranchFilter == branch) null else branch
                    },
                    label = { Text(if (uiState.isEnglishUi) branch.titleEn else branch.titleAr, fontSize = 11.sp) }
                )
            }

            items(availableEras) { era ->
                FilterChip(
                    selected = selectedEraFilter == era,
                    onClick = {
                        selectedEraFilter = if (selectedEraFilter == era) null else era
                    },
                    label = { Text(era, fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Content Area based on View Mode
        when (selectedViewMode) {
            TimelineViewMode.VERTICAL_STEPPER -> {
                VerticalTimelineStepper(
                    events = filteredEvents,
                    expandedEventId = expandedEventId,
                    onToggleExpand = { id ->
                        expandedEventId = if (expandedEventId == id) null else id
                    },
                    viewModel = viewModel,
                    isEnglish = uiState.isEnglishUi
                )
            }
            TimelineViewMode.EPOCH_MATRIX -> {
                EpochComparisonMatrix(
                    events = allEvents,
                    viewModel = viewModel,
                    isEnglish = uiState.isEnglishUi
                )
            }
            TimelineViewMode.SCRIPT_EVOLUTION -> {
                ScriptEvolutionFlowView(
                    viewModel = viewModel,
                    isEnglish = uiState.isEnglishUi
                )
            }
        }
    }
}

@Composable
fun VerticalTimelineStepper(
    events: List<ChronologyEvent>,
    expandedEventId: String?,
    onToggleExpand: (String) -> Unit,
    viewModel: MainViewModel,
    isEnglish: Boolean
) {
    if (events.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isEnglish) "No milestones match the selected filters." else "لا توجد محطات تاريخية مطابقة للمرشحات المختارة.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        return
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(0.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(events) { index, event ->
            val isExpanded = expandedEventId == event.id
            val isLast = index == events.lastIndex

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                // Left Column: Connecting Vertical Line and Glowing Node
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(52.dp)
                ) {
                    // Node Circle
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(42.dp)
                            .shadow(if (isExpanded) 8.dp else 2.dp, CircleShape)
                            .clip(CircleShape)
                            .background(
                                if (isExpanded) {
                                    Brush.linearGradient(
                                        listOf(
                                            MaterialTheme.colorScheme.primary,
                                            MaterialTheme.colorScheme.tertiary
                                        )
                                    )
                                } else {
                                    Brush.linearGradient(
                                        listOf(
                                            MaterialTheme.colorScheme.surfaceVariant,
                                            MaterialTheme.colorScheme.surface
                                        )
                                    )
                                }
                            )
                            .border(
                                width = if (isExpanded) 2.dp else 1.dp,
                                color = if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                                shape = CircleShape
                            )
                            .clickable { onToggleExpand(event.id) }
                    ) {
                        if (event.scriptGlyphSymbol.isNotBlank()) {
                            Text(
                                text = event.scriptGlyphSymbol,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isExpanded) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.HistoryEdu,
                                contentDescription = null,
                                tint = if (isExpanded) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    // Vertical connecting line
                    if (!isLast) {
                        Box(
                            modifier = Modifier
                                .width(3.dp)
                                .height(if (isExpanded) 190.dp else 70.dp)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                                            MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                                        )
                                    )
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Right Column: Milestone Content Card
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isExpanded) {
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
                        } else {
                            MaterialTheme.colorScheme.surface
                        }
                    ),
                    border = CardDefaults.outlinedCardBorder(
                        enabled = isExpanded
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 14.dp)
                        .clickable { onToggleExpand(event.id) }
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        // Date & Era Row
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Surface(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = if (isEnglish && event.displayDateEn.isNotBlank()) event.displayDateEn else event.displayDateAr,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }

                            if (event.eraAr.isNotBlank()) {
                                Text(
                                    text = if (isEnglish && event.eraEn.isNotBlank()) event.eraEn else event.eraAr,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        // Title
                        Text(
                            text = if (isEnglish && event.titleEn.isNotBlank()) event.titleEn else event.titleAr,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Description preview or full
                        Text(
                            text = if (isEnglish && event.descriptionEn.isNotBlank()) event.descriptionEn else event.descriptionAr,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        // Expanded Section Details
                        AnimatedVisibility(visible = isExpanded) {
                            Column(modifier = Modifier.padding(top = 10.dp)) {
                                Divider(
                                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                                    thickness = 1.dp
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                // Script Type & Language Branch Badge
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    if (event.scriptType.isNotBlank()) {
                                        Surface(
                                            color = MaterialTheme.colorScheme.surfaceVariant,
                                            shape = RoundedCornerShape(6.dp)
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Filled.Edit,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.primary,
                                                    modifier = Modifier.size(12.dp)
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text(
                                                    text = event.scriptType,
                                                    style = MaterialTheme.typography.labelSmall,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }

                                    Surface(
                                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                                        shape = RoundedCornerShape(6.dp)
                                    ) {
                                        Text(
                                            text = if (isEnglish) event.languageBranch.titleEn else event.languageBranch.titleAr,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                        )
                                    }
                                }

                                if (event.significanceNotesAr.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Card(
                                        shape = RoundedCornerShape(8.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                        )
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(8.dp),
                                            verticalAlignment = Alignment.Top
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Lightbulb,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.tertiary,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = event.significanceNotesAr,
                                                style = MaterialTheme.typography.bodySmall,
                                                fontSize = 11.sp,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                // Action Buttons Row
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    // Audio TTS button
                                    OutlinedButton(
                                        onClick = {
                                            viewModel.toggleTts("${event.titleAr}. ${event.descriptionAr}. ${event.significanceNotesAr}")
                                        },
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.VolumeUp,
                                            contentDescription = null,
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = if (isEnglish) "Listen" else "استماع",
                                            fontSize = 10.sp
                                        )
                                    }

                                    // Deep-link to Inscription if exists
                                    if (event.keyInscriptionId.isNotBlank()) {
                                        Button(
                                            onClick = {
                                                viewModel.selectInscription(event.keyInscriptionId)
                                                viewModel.selectTab(AppTab.INSCRIPTIONS)
                                            },
                                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Description,
                                                contentDescription = null,
                                                modifier = Modifier.size(14.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = if (isEnglish) "View Inscription" else "عرض النقش",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }

                                    // Deep-link to Language matrix
                                    if (event.associatedLanguageId.isNotBlank()) {
                                        FilledTonalButton(
                                            onClick = {
                                                viewModel.selectLanguage(event.associatedLanguageId)
                                                viewModel.selectTab(AppTab.LANGUAGES)
                                            },
                                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Language,
                                                contentDescription = null,
                                                modifier = Modifier.size(14.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = if (isEnglish) "Language" else "اللغة",
                                                fontSize = 10.sp
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
        item { Spacer(modifier = Modifier.height(48.dp)) }
    }
}

@Composable
fun EpochComparisonMatrix(
    events: List<ChronologyEvent>,
    viewModel: MainViewModel,
    isEnglish: Boolean
) {
    val epochs = listOf(
        "العصر البرونزي المبكر" to "Early Bronze Age (3000-2000 BCE)",
        "العصر البرونزي الوسيط" to "Middle Bronze Age (2000-1550 BCE)",
        "العصر البرونزي المتأخر" to "Late Bronze Age (1550-1200 BCE)",
        "العصر الحديدي الأول" to "Iron Age I (1200-900 BCE)",
        "العصر الحديدي الثاني" to "Iron Age II (900-550 BCE)",
        "العصر الفارسي والأخميني" to "Persian & Achaemenid (550-330 BCE)",
        "العصر الهلنستي والروماني" to "Hellenistic & Nabataean (330 BCE - 100 CE)",
        "العصر الروماني والبادية" to "Roman & North Arabian (100-300 CE)",
        "العصر الروماني المتأخر" to "Late Roman & Early Arabic (300-500 CE)",
        "العصر البيزنطي والجاهلي" to "Late Antique & Pre-Islamic (500-650 CE)"
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(epochs) { (eraKeyAr, eraTitleEn) ->
            val eraEvents = events.filter { it.eraAr == eraKeyAr }

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    // Header of the Epoch
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isEnglish) eraTitleEn else eraKeyAr,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Surface(
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "${eraEvents.size} " + (if (isEnglish) "Events" else "أحداث"),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (eraEvents.isEmpty()) {
                        Text(
                            text = if (isEnglish) "Developmental period with indirect epigraphic records." else "فترة تطور حضاري وسيطة متصلة بالنقوش والشواهد المحيطة.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            eraEvents.forEach { ev ->
                                Surface(
                                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(10.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        if (ev.scriptGlyphSymbol.isNotBlank()) {
                                            Text(
                                                text = ev.scriptGlyphSymbol,
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.padding(end = 10.dp)
                                            )
                                        }

                                        Column(modifier = Modifier.weight(1f)) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                Text(
                                                    text = if (isEnglish && ev.titleEn.isNotBlank()) ev.titleEn else ev.titleAr,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Text(
                                                    text = if (isEnglish && ev.displayDateEn.isNotBlank()) ev.displayDateEn else ev.displayDateAr,
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = MaterialTheme.colorScheme.secondary,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }

                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = if (isEnglish && ev.descriptionEn.isNotBlank()) ev.descriptionEn else ev.descriptionAr,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
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
        item { Spacer(modifier = Modifier.height(48.dp)) }
    }
}

@Composable
fun ScriptEvolutionFlowView(
    viewModel: MainViewModel,
    isEnglish: Boolean
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = if (isEnglish) "🌳 Genealogy of Semitic Scripts & Alphabets" else "🌳 شجرة الأنساب التطورية للخطوط والأبجديات السامية",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (isEnglish) "How cuneiform, linear alphabets, and South Semitic scripts branched from the 3rd millennium BCE to classical antiquity."
                    else "كيف تفرعت الأبجديات المسمارية والخطية من السينائية الأولية حتى انبثاق الخطوط العربية والمسندية والجعزية.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Evolution Trees
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // Branch 1: The Linear Western Alphabet Line
            item {
                EvolutionBranchCard(
                    titleAr = "1. الفرع الأبجدي الخطي الشمالي الغربي (السينائي ➔ الفينيقي ➔ العربي واليوناني)",
                    titleEn = "1. Northwest Linear Alphabet Line (Proto-Sinaitic ➔ Phoenician ➔ Aramaic/Arabic)",
                    steps = listOf(
                        EvolutionStep(
                            stageNameAr = "السينائي الأولي (1850 ق.م)",
                            stageNameEn = "Proto-Sinaitic (1850 BCE)",
                            sampleGlyph = "𐤀",
                            descriptionAr = "ابتكار الأبجدية الأكروفونية من الهيروغليفية",
                            descriptionEn = "Acrophonic invention of the alphabet derived from Egyptian hieroglyphs"
                        ),
                        EvolutionStep(
                            stageNameAr = "الفينيقي الكنعاني (1050 ق.م)",
                            stageNameEn = "Phoenician / Canaanite (1050 BCE)",
                            sampleGlyph = "𐤁",
                            descriptionAr = "تقنين الأبجدية الخطية المعيارية (22 حرفاً)",
                            descriptionEn = "Standardization of the 22-consonant linear alphabet across the Mediterranean"
                        ),
                        EvolutionStep(
                            stageNameAr = "الآرامي الإمبراطوري (500 ق.م)",
                            stageNameEn = "Imperial Aramaic (500 BCE)",
                            sampleGlyph = "𐡀",
                            descriptionAr = "تبسيط الحروف والانتشار الدبلوماسي العالمي",
                            descriptionEn = "Simplification of cursive letterforms and adoption as lingua franca of the Achaemenid Empire"
                        ),
                        EvolutionStep(
                            stageNameAr = "النبطي الجريان (100 ق.م)",
                            stageNameEn = "Nabataean Cursive (100 BCE)",
                            sampleGlyph = "𐡍",
                            descriptionAr = "ظهور الحروف المتصلة والروابط الصخرية بالبتراء",
                            descriptionEn = "Evolution of ligatures and connected letterforms in Petra and Hegra rock inscriptions"
                        ),
                        EvolutionStep(
                            stageNameAr = "الخط العربي المبكر (328 م - النمارة)",
                            stageNameEn = "Early Arabic Script (328 CE - Namara)",
                            sampleGlyph = "ا",
                            descriptionAr = "انبثاق الخط العربي المتصل المستمر حتى اليوم",
                            descriptionEn = "Emergence of the cursive Arabic script from late Nabataean as seen in the Namara inscription"
                        )
                    ),
                    badgeColor = MaterialTheme.colorScheme.primary,
                    isEnglish = isEnglish
                )
            }

            // Branch 2: The South Semitic & Musnad Line
            item {
                EvolutionBranchCard(
                    titleAr = "2. الفرع الأبجدي الجنوبي (السامية الجنوبية ➔ المسند ➔ الفيدل الجعزي)",
                    titleEn = "2. South Semitic Script Line (Proto-Arabic ➔ Musnad ➔ Ge'ez Fidel)",
                    steps = listOf(
                        EvolutionStep(
                            stageNameAr = "السامي الجنوبي الأولي (1500 ق.م)",
                            stageNameEn = "Proto-South Semitic (1500 BCE)",
                            sampleGlyph = "𐩠",
                            descriptionAr = "انفصال نظام الكتابة الجنوبي ذي الـ 29 صامتاً",
                            descriptionEn = "Divergence of the 29-consonant South Semitic phonetic writing system"
                        ),
                        EvolutionStep(
                            stageNameAr = "خط المسند الصخري (800 ق.م)",
                            stageNameEn = "Monumental Musnad Script (800 BCE)",
                            sampleGlyph = "𐩡",
                            descriptionAr = "الخط المعماري الهندسي الصارم في سبأ ومعين",
                            descriptionEn = "Rigid geometric architectural monumental script in Saba, Ma'in, and Qataban"
                        ),
                        EvolutionStep(
                            stageNameAr = "خط الزبور الشعبي (500 ق.م)",
                            stageNameEn = "Zabur Cursive Script (500 BCE)",
                            sampleGlyph = "𐩢",
                            descriptionAr = "الكتابة اللينة المتصلة على عيدان وسعف النخل",
                            descriptionEn = "Cursive minuscule script incised on wooden sticks and palm fronds for daily correspondence"
                        ),
                        EvolutionStep(
                            stageNameAr = "الجعزي الأبجدي (100 م)",
                            stageNameEn = "Consonantal Ge'ez (100 CE)",
                            sampleGlyph = "ሀ",
                            descriptionAr = "انتقال المسند إلى القرن الأفريقي ومملكة أكسوم",
                            descriptionEn = "Transmission of South Arabian script to the Horn of Africa and Kingdom of Aksum"
                        ),
                        EvolutionStep(
                            stageNameAr = "الجعزي المقطعي - الفيدل (350 م)",
                            stageNameEn = "Ge'ez Fidel Syllabary (350 CE)",
                            sampleGlyph = "ሁ",
                            descriptionAr = "تزويد الحروف بحركات الصوائت السبع في عهد عيزانا",
                            descriptionEn = "Vocalization into seven vowel orders during King Ezana's reign (Fidel)"
                        )
                    ),
                    badgeColor = MaterialTheme.colorScheme.tertiary,
                    isEnglish = isEnglish
                )
            }

            // Branch 3: The Cuneiform Logographic/Syllabic Line
            item {
                EvolutionBranchCard(
                    titleAr = "3. خطوط بلاد الرافدين المسمارية (السومري ➔ الأكادي ➔ الأوغاريتي)",
                    titleEn = "3. Mesopotamian Cuneiform Line (Sumerian ➔ Akkadian ➔ Ugaritic)",
                    steps = listOf(
                        EvolutionStep(
                            stageNameAr = "المسماري الأكادي القديم (2600 ق.م)",
                            stageNameEn = "Old Akkadian Cuneiform (2600 BCE)",
                            sampleGlyph = "𒀭",
                            descriptionAr = "تكييف الرموز السومرية للغة سامية مقطعية",
                            descriptionEn = "Adaptation of Sumerian logograms into a syllabic script for an East Semitic language"
                        ),
                        EvolutionStep(
                            stageNameAr = "المسماري البابلي والآشوري (1750 ق.م)",
                            stageNameEn = "Babylonian & Assyrian Cuneiform (1750 BCE)",
                            sampleGlyph = "𒈗",
                            descriptionAr = "تقنين شريعة حمورابي والنصوص الأدبية",
                            descriptionEn = "Standardization of the Code of Hammurabi, Epic of Gilgamesh, and royal annals"
                        ),
                        EvolutionStep(
                            stageNameAr = "المسماري الأوغاريتي (1400 ق.م)",
                            stageNameEn = "Ugaritic Alphabetic Cuneiform (1400 BCE)",
                            sampleGlyph = "𐎀",
                            descriptionAr = "ثورة اختزال المسماريات إلى 30 صامتاً أبجدياً برأس الشمرا",
                            descriptionEn = "Groundbreaking reduction of hundreds of cuneiform signs into 30 alphabetic letters at Ras Shamra"
                        )
                    ),
                    badgeColor = MaterialTheme.colorScheme.secondary,
                    isEnglish = isEnglish
                )
            }

            item { Spacer(modifier = Modifier.height(48.dp)) }
        }
    }
}

data class EvolutionStep(
    val stageNameAr: String,
    val stageNameEn: String,
    val sampleGlyph: String,
    val descriptionAr: String,
    val descriptionEn: String,
    val stageName: String = stageNameAr,
    val description: String = descriptionAr
) {
    constructor(stageName: String, sampleGlyph: String, description: String) : this(
        stageNameAr = stageName,
        stageNameEn = stageName,
        sampleGlyph = sampleGlyph,
        descriptionAr = description,
        descriptionEn = description,
        stageName = stageName,
        description = description
    )
}

@Composable
fun EvolutionBranchCard(
    titleAr: String,
    titleEn: String,
    steps: List<EvolutionStep>,
    badgeColor: Color,
    isEnglish: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = if (isEnglish) titleEn else titleAr,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = badgeColor
            )

            Spacer(modifier = Modifier.height(12.dp))

            steps.forEachIndexed { index, step ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Glyph Box
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(badgeColor.copy(alpha = 0.15f))
                            .border(1.dp, badgeColor, CircleShape)
                    ) {
                        Text(
                            text = step.sampleGlyph,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = badgeColor
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isEnglish) step.stageNameEn else step.stageNameAr,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (isEnglish) step.descriptionEn else step.descriptionAr,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp
                        )
                    }
                }

                if (index < steps.lastIndex) {
                    Box(
                        modifier = Modifier
                            .padding(start = 17.dp, top = 2.dp, bottom = 2.dp)
                            .width(2.dp)
                            .height(14.dp)
                            .background(badgeColor.copy(alpha = 0.4f))
                    )
                }
            }
        }
    }
}
