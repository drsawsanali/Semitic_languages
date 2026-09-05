package com.example.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.ai.GeminiAnalysisState
import com.example.core.data.InscriptionLinguisticData
import com.example.core.data.InscriptionsData
import com.example.core.model.InscriptionArtifact
import com.example.core.model.InscriptionLinguisticBreakdown
import com.example.ui.MainViewModel
import com.example.ui.theme.DeepGold
import com.example.ui.theme.RoyalGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InscriptionSideBySideComparisonComponent(
    viewModel: MainViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val clipboardManager = LocalClipboardManager.current
    var copiedNotice by remember { mutableStateOf(false) }

    val allInscriptions = InscriptionsData.ALL_INSCRIPTIONS

    val firstInscription = allInscriptions.find { it.id == uiState.selectedFirstCompareInscriptionId }
        ?: allInscriptions.first()
    val secondInscription = allInscriptions.find { it.id == uiState.selectedSecondCompareInscriptionId }
        ?: allInscriptions.getOrElse(1) { allInscriptions.first() }

    val firstBreakdown = remember(firstInscription.id) {
        InscriptionLinguisticData.getBreakdown(firstInscription.id)
    }
    val secondBreakdown = remember(secondInscription.id) {
        InscriptionLinguisticData.getBreakdown(secondInscription.id)
    }

    var isSideBySideMode by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("inscription_comparison_screen")
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = if (uiState.isEnglishUi) "Linguistic Comparison of Inscriptions" else "المقارنة اللغوية المزدوجة بين النقوش",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Text(
                        text = if (uiState.isEnglishUi) "Side-by-side grammatical & semantic analysis with Gemini AI" else "تحليل الفروق النحوية والدلالية جنباً إلى جنب عبر Gemini AI",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            navigationIcon = {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.testTag("close_comparison_btn")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "الرجوع"
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = { viewModel.swapCompareInscriptions() },
                    modifier = Modifier.testTag("swap_inscriptions_btn")
                ) {
                    Icon(
                        imageVector = Icons.Filled.SwapHoriz,
                        contentDescription = "تبديل موضع النقشين",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(
                    onClick = { isSideBySideMode = !isSideBySideMode },
                    modifier = Modifier.testTag("toggle_layout_mode_btn")
                ) {
                    Icon(
                        imageVector = if (isSideBySideMode) Icons.Filled.ViewAgenda else Icons.Filled.ViewColumn,
                        contentDescription = "تبديل طريقة العرض"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }

            // Preset Quick Comparison Pairs
            item {
                Column {
                    Text(
                        text = if (uiState.isEnglishUi) "Suggested Comparison Pairs:" else "أزواج مقارنة مقترحة لدراسة الفروق اللغوية:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            ComparisonPresetChip(
                                title = "مسلة ميشع ⟷ تل دان",
                                subtitle = "مؤابية ⟷ آرامية قديمة",
                                isSelected = firstInscription.id == "mesha_stele" && secondInscription.id == "tel_dan_stele",
                                onClick = {
                                    viewModel.setFirstCompareInscription("mesha_stele")
                                    viewModel.setSecondCompareInscription("tel_dan_stele")
                                }
                            )
                        }
                        item {
                            ComparisonPresetChip(
                                title = "تابوت أحيرام ⟷ حمورابي",
                                subtitle = "فينيقية ⟷ بابلية مسمارية",
                                isSelected = firstInscription.id == "ahiram_sarcophagus" && secondInscription.id == "hammurabi_stele",
                                onClick = {
                                    viewModel.setFirstCompareInscription("ahiram_sarcophagus")
                                    viewModel.setSecondCompareInscription("hammurabi_stele")
                                }
                            )
                        }
                        item {
                            ComparisonPresetChip(
                                title = "ملحمة أوجاريت ⟷ مسلة ميشع",
                                subtitle = "أوغاريتية مسمارية ⟷ مؤابية كنعانية",
                                isSelected = firstInscription.id == "ugarit_baal_epic" && secondInscription.id == "mesha_stele",
                                onClick = {
                                    viewModel.setFirstCompareInscription("ugarit_baal_epic")
                                    viewModel.setSecondCompareInscription("mesha_stele")
                                }
                            )
                        }
                        item {
                            ComparisonPresetChip(
                                title = "نقش صرواح ⟷ تل دان",
                                subtitle = "سبئية مسندية ⟷ آرامية قديمة",
                                isSelected = firstInscription.id == "sirwah_inscription" && secondInscription.id == "tel_dan_stele",
                                onClick = {
                                    viewModel.setFirstCompareInscription("sirwah_inscription")
                                    viewModel.setSecondCompareInscription("tel_dan_stele")
                                }
                            )
                        }
                    }
                }
            }

            // Inscription Selection Cards
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = if (uiState.isEnglishUi) "Select Inscriptions to Compare:" else "اختيار النقشين للمقارنة الفيلولوجية:",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // Inscription 1 Selector
                        Text(
                            text = if (uiState.isEnglishUi) "First Inscription (A):" else "النقش الأول (أ):",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(allInscriptions) { insc ->
                                val isSelected = insc.id == firstInscription.id
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { viewModel.setFirstCompareInscription(insc.id) },
                                    label = { Text(insc.titleAr.take(20), fontSize = 11.sp) },
                                    leadingIcon = if (isSelected) {
                                        { Icon(Icons.Filled.Check, contentDescription = null, modifier = Modifier.size(14.dp)) }
                                    } else null
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Inscription 2 Selector
                        Text(
                            text = if (uiState.isEnglishUi) "Second Inscription (B):" else "النقش الثاني (ب):",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(allInscriptions) { insc ->
                                val isSelected = insc.id == secondInscription.id
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { viewModel.setSecondCompareInscription(insc.id) },
                                    label = { Text(insc.titleAr.take(20), fontSize = 11.sp) },
                                    leadingIcon = if (isSelected) {
                                        { Icon(Icons.Filled.Check, contentDescription = null, modifier = Modifier.size(14.dp)) }
                                    } else null
                                )
                            }
                        }

                        if (firstInscription.id == secondInscription.id) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.errorContainer
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        Icons.Filled.Warning,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.error,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "تنبيه: لقد اخترت نفس النقش. يُرجى اختيار نقشين مختلفين لإجراء المقارنة.",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onErrorContainer
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Gemini Comparison Action Button
            item {
                Button(
                    onClick = { viewModel.runGeminiInscriptionComparison() },
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalGold),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("run_gemini_comparison_btn"),
                    enabled = firstInscription.id != secondInscription.id
                ) {
                    Icon(
                        imageVector = Icons.Filled.AutoAwesome,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (uiState.isEnglishUi) "Generate Linguistic Comparison with Gemini AI" else "تحليل الفروق النحوية والدلالية بواسطة Gemini AI",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            }

            // Gemini AI Analysis Result Section (if active)
            item {
                GeminiComparisonResultSection(
                    state = uiState.inscriptionComparisonState,
                    onRetry = { viewModel.runGeminiInscriptionComparison() },
                    onCopy = { text ->
                        clipboardManager.setText(AnnotatedString(text))
                        copiedNotice = true
                    },
                    onSpeak = { text -> viewModel.playTts(text) }
                )
            }

            // Side-by-Side Inscriptions Data Comparison View
            item {
                Text(
                    text = if (uiState.isEnglishUi) "Side-by-Side Linguistic Breakdown:" else "عرض البيانات اللغوية والتفكيك الصرفي جنباً إلى جنب:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (isSideBySideMode) {
                // Horizontal Side-by-Side Layout
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // First Inscription Card
                        Box(modifier = Modifier.width(320.dp)) {
                            SingleInscriptionColumnCard(
                                label = "النقش الأول (أ)",
                                inscription = firstInscription,
                                breakdown = firstBreakdown,
                                accentColor = MaterialTheme.colorScheme.primary,
                                onPlayTts = { viewModel.playTts(firstInscription.translationAr) }
                            )
                        }

                        // Second Inscription Card
                        Box(modifier = Modifier.width(320.dp)) {
                            SingleInscriptionColumnCard(
                                label = "النقش الثاني (ب)",
                                inscription = secondInscription,
                                breakdown = secondBreakdown,
                                accentColor = MaterialTheme.colorScheme.secondary,
                                onPlayTts = { viewModel.playTts(secondInscription.translationAr) }
                            )
                        }
                    }
                }
            } else {
                // Stacked Layout
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        SingleInscriptionColumnCard(
                            label = "النقش الأول (أ)",
                            inscription = firstInscription,
                            breakdown = firstBreakdown,
                            accentColor = MaterialTheme.colorScheme.primary,
                            onPlayTts = { viewModel.playTts(firstInscription.translationAr) }
                        )

                        SingleInscriptionColumnCard(
                            label = "النقش الثاني (ب)",
                            inscription = secondInscription,
                            breakdown = secondBreakdown,
                            accentColor = MaterialTheme.colorScheme.secondary,
                            onPlayTts = { viewModel.playTts(secondInscription.translationAr) }
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(48.dp)) }
        }
    }
}

@Composable
private fun ComparisonPresetChip(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            1.dp,
            if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
        ),
        modifier = Modifier.clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 10.sp,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f) else MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Composable
private fun SingleInscriptionColumnCard(
    label: String,
    inscription: InscriptionArtifact,
    breakdown: InscriptionLinguisticBreakdown?,
    accentColor: Color,
    onPlayTts: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, accentColor.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Card Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = accentColor.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = accentColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                IconButton(
                    onClick = onPlayTts,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "استماع",
                        tint = accentColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = inscription.titleAr,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${inscription.scriptType.titleAr} • ${inscription.dateCentury}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "موقع الاكتشاف: ${inscription.discoveryLocation}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(10.dp))

            // Original Semitic Script Text
            Text(
                text = "النص السامي الأصلي:",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = accentColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = inscription.scriptTextOriginal,
                    fontSize = 17.sp,
                    lineHeight = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Transliteration
            Text(
                text = "النقحرة اللاتينية (Transliteration):",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = inscription.transliteration,
                style = MaterialTheme.typography.bodySmall,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Translation
            Text(
                text = "الترجمة العربية:",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = inscription.translationAr,
                style = MaterialTheme.typography.bodySmall,
                lineHeight = 18.sp
            )

            // Linguistic Tokens Breakdown
            if (breakdown != null && breakdown.tokens.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "تفكيك عينات المفردات والجذور (Tokens):",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = accentColor
                )
                Spacer(modifier = Modifier.height(6.dp))

                breakdown.tokens.take(4).forEach { token ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = token.tokenOriginal,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = accentColor
                                )
                                Text(
                                    text = token.transliteration,
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.Serif
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "الجذر: ${token.root} • الدور: ${token.grammaticalRoleAr}",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "الدلالة: ${token.meaningAr}",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            // Phonological Laws
            if (breakdown != null && breakdown.phonologicalLaws.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "القوانين الصوتية البارزة:",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.outline
                )
                Spacer(modifier = Modifier.height(4.dp))
                breakdown.phonologicalLaws.take(2).forEach { law ->
                    Text(
                        text = "• ${law.ruleTitleAr}: ${law.formula}",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun GeminiComparisonResultSection(
    state: GeminiAnalysisState,
    onRetry: () -> Unit,
    onCopy: (String) -> Unit,
    onSpeak: (String) -> Unit
) {
    when (state) {
        is GeminiAnalysisState.Idle -> {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Psychology,
                        contentDescription = null,
                        tint = DeepGold,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "جاهز للمقارنة اللغوية المعمقة",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "اضغط على الزر الذهبي أعلاه لتوليد تحليل فيلولوجي مقارن يدرس الفروق النحوية، تصريف الأفعال، الجذور المشتركة، والتحولات الصوتية بين النقشين.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        is GeminiAnalysisState.Loading -> {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, RoyalGold),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("gemini_comparison_loading_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = RoyalGold,
                        modifier = Modifier.size(42.dp)
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "جاري الاتصال بـ Gemini AI...",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = state.messageAr,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        is GeminiAnalysisState.Error -> {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.8f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("gemini_comparison_error_card")
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.ErrorOutline,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "تنبيه في التحليل الذكي",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = state.errorAr,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )

                    if (state.isApiKeyMissing) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "خطوات التفعيل:\n1. افتح شريط الأسرار (Secrets) في AI Studio.\n2. أضف المفتاح باسم: GEMINI_API_KEY\n3. أعد المحاولة لتشغيل المقارنة المباشرة.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.9f)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onRetry,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Icon(Icons.Filled.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("إعادة المحاولة")
                    }
                }
            }
        }

        is GeminiAnalysisState.Success -> {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                border = BorderStroke(1.5.dp, RoyalGold),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("gemini_comparison_success_card")
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    // Header of Gemini Response
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                shape = CircleShape,
                                color = RoyalGold.copy(alpha = 0.2f),
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.AutoAwesome,
                                    contentDescription = null,
                                    tint = DeepGold,
                                    modifier = Modifier
                                        .padding(6.dp)
                                        .size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "نتائج المقارنة الفيلولوجية (Gemini AI)",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "النموذج الأكاديمي: ${state.modelUsed}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                        }

                        Row {
                            IconButton(
                                onClick = { onSpeak(state.responseText) },
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = "استماع صوتي",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            IconButton(
                                onClick = { onCopy(state.responseText) },
                                modifier = Modifier
                                    .size(36.dp)
                                    .testTag("copy_comparison_analysis_btn")
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ContentCopy,
                                    contentDescription = "نسخ التحليل",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(color = RoyalGold.copy(alpha = 0.3f))
                    Spacer(modifier = Modifier.height(12.dp))

                    // Formatted Response Text
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = state.responseText,
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 24.sp,
                            modifier = Modifier.padding(14.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = onRetry,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(Icons.Filled.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("إعادة التحليل")
                    }
                }
            }
        }
    }
}
