package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.keyboard.ScriptGlyph
import com.example.core.keyboard.ScriptKeyboardLayout
import com.example.core.keyboard.VirtualKeyboardData
import com.example.ui.MainViewModel
import com.example.ui.theme.RoyalGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiResearchLabScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val clipboardManager = LocalClipboardManager.current
    var selectedGlyphDetail by remember { mutableStateOf<ScriptGlyph?>(null) }

    val layouts = VirtualKeyboardData.ALL_LAYOUTS

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("ai_research_lab_screen")
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Input Buffer Display Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "محرر النقوش والخطوط السامية القديمة:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Row {
                        IconButton(
                            onClick = {
                                if (uiState.keyboardInputText.isNotEmpty()) {
                                    clipboardManager.setText(AnnotatedString(uiState.keyboardInputText))
                                }
                            }
                        ) {
                            Icon(Icons.Filled.ContentCopy, contentDescription = "نسخ النص", tint = MaterialTheme.colorScheme.primary)
                        }
                        IconButton(onClick = { viewModel.clearKeyboardInput() }) {
                            Icon(Icons.Filled.Clear, contentDescription = "مسح الكل")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 70.dp)
                ) {
                    Box(modifier = Modifier.padding(12.dp)) {
                        if (uiState.keyboardInputText.isEmpty()) {
                            Text(
                                text = "انقر على مفاتيح الحروف بالأسفل لكتابة نص سامي أو اختر نموذجاً...",
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                fontSize = 13.sp
                            )
                        } else {
                            Text(
                                text = uiState.keyboardInputText,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = 30.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { viewModel.analyzeKeyboardInputWithGemini() },
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
                        text = "تحليل النص فيلولوجياً بـ Gemini AI",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 13.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Quick Inscription Presets Row
        Text(
            text = "نماذج نقوش تاريخية جاهزة للإدراج:",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Presets for the active layout
            items(uiState.activeKeyboardLayout.presets) { preset ->
                SuggestionChip(
                    onClick = { viewModel.setKeyboardPresetText(preset.originalText) },
                    label = { Text(preset.labelAr, fontSize = 11.sp) }
                )
            }
            item {
                SuggestionChip(
                    onClick = { viewModel.setKeyboardPresetText("𐤀𐤓𐤍 𐤆 𐤐𐤏𐤋 𐤀𐤕𐤁𐤏𐤋 𐤁𐤍 𐤀𐤇𐤓𐤌 𐤌𐤋𐤊 𐤂𐤁𐤋") },
                    label = { Text("سطر أحيرام الملكي", fontSize = 11.sp) }
                )
            }
            item {
                SuggestionChip(
                    onClick = { viewModel.setKeyboardPresetText("𐤀𐤍𐤊 𐤌𐤔𐤏 𐤁𐤍 𐤊𐤌𐤔𐤉𐤕 𐤌𐤋𐤊 𐤌𐤀𐤁") },
                    label = { Text("مقدمة مسلة ميشع", fontSize = 11.sp) }
                )
            }
            item {
                SuggestionChip(
                    onClick = { viewModel.setKeyboardPresetText("𐩱𐩬𐩫 𐩫𐩧𐩨𐩱𐩡 𐩥𐩩𐩧 𐩨𐩬 𐩺𐩻𐩲𐩱𐩡 𐩣𐩫𐩧𐩨 𐩪𐩨𐩱") },
                    label = { Text("مفتتح نقش صرواح السبئي", fontSize = 11.sp) }
                )
            }
            item {
                SuggestionChip(
                    onClick = { viewModel.setKeyboardPresetText("𐎛𐎍 𐎎𐎍𐎋 𐎄𐎊𐎋𐎐𐎐 𐎁𐎓𐎍 𐎏𐎁𐎍") },
                    label = { Text("شاهد مسماري أوجاريتي", fontSize = 11.sp) }
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Keyboard Script Layout Selector Tabs
        ScrollableTabRow(
            selectedTabIndex = layouts.indexOf(uiState.activeKeyboardLayout).coerceAtLeast(0),
            edgePadding = 0.dp,
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
            modifier = Modifier.clip(RoundedCornerShape(12.dp))
        ) {
            layouts.forEach { layout ->
                val isSelected = uiState.activeKeyboardLayout == layout
                Tab(
                    selected = isSelected,
                    onClick = { viewModel.setKeyboardLayout(layout) },
                    text = { Text(layout.scriptNameAr, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) }
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Virtual Keys Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(uiState.activeKeyboardLayout.glyphs) { glyph ->
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 2.dp,
                    modifier = Modifier
                        .height(56.dp)
                        .clickable {
                            viewModel.appendKeyboardGlyph(glyph.glyph)
                            selectedGlyphDetail = glyph
                            viewModel.ttsManager.playChime(glyph.approximateFrequencyHz)
                        }
                        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(8.dp))
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = glyph.glyph,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = glyph.arabicEquivalent,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            // Backspace Key
            item {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.errorContainer,
                    modifier = Modifier
                        .height(56.dp)
                        .clickable { viewModel.deleteKeyboardLastChar() }
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Icon(
                            imageVector = Icons.Filled.Backspace,
                            contentDescription = "حذف",
                            tint = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }
            }

            // Space Key
            item {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier
                        .height(56.dp)
                        .clickable { viewModel.appendKeyboardGlyph(" ") }
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text("مسافة", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Selected Glyph Detail Footer
        selectedGlyphDetail?.let { glyph ->
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = glyph.glyph,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "الحرف: ${glyph.name} | المقابل العربي: ${glyph.arabicEquivalent}",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "النطق الصوتي (IPA): /${glyph.ipa}/",
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // AI Philological Consultation Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Psychology,
                        contentDescription = null,
                        tint = RoyalGold,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "استشارة باحث Gemini الفيلولوجي الأكاديمي",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "اطرح استفسارات علمية معمقة حول النحو السامي المقارن أو قوانين التحولات الصوتية:",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                var queryText by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = queryText,
                    onValueChange = { queryText = it },
                    placeholder = { Text("مثال: قارن بين أوزان الأفعال في الأكادية والسبئية...", fontSize = 12.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (queryText.isNotBlank()) {
                                    viewModel.executeCustomAiQuery(queryText)
                                    queryText = ""
                                }
                            }
                        ) {
                            Icon(Icons.Filled.Send, contentDescription = "إرسال", tint = RoyalGold)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(6.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    val quickQueries = listOf(
                        "التحول الكنعاني ā > ō",
                        "قانون بجد كفت الصوتي",
                        "الميمية والتنوين في الساميات",
                        "نظام الكتابة المسمارية الأوغاريتية"
                    )
                    items(quickQueries) { query ->
                        SuggestionChip(
                            onClick = { viewModel.executeCustomAiQuery("اشرح بالتفصيل الأكاديمي الفيلولوجي: $query") },
                            label = { Text(query, fontSize = 11.sp) }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
