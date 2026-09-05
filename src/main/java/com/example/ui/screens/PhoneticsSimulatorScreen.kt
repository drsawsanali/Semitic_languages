package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.data.PhoneticsData
import com.example.core.model.IpaConsonant
import com.example.core.model.SoundShiftRule
import com.example.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneticsSimulatorScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    var activeTab by remember { mutableStateOf(0) } // 0: Sound Shifts, 1: IPA Matrix, 2: Formant Synthesizer
    var selectedConsonant by remember { mutableStateOf<IpaConsonant?>(PhoneticsData.IPA_CONSONANTS.first()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("phonetics_simulator_screen")
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Tab Selector
        TabRow(
            selectedTabIndex = activeTab,
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            modifier = Modifier.clip(RoundedCornerShape(12.dp))
        ) {
            Tab(
                selected = activeTab == 0,
                onClick = { activeTab = 0 },
                text = { Text("قوانين التحول الصوتي", fontSize = 12.sp, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = activeTab == 1,
                onClick = { activeTab = 1 },
                text = { Text("مصفوفة IPA للصوامت", fontSize = 12.sp, fontWeight = FontWeight.Bold) }
            )
            Tab(
                selected = activeTab == 2,
                onClick = { activeTab = 2 },
                text = { Text("محاكي الفورمانت", fontSize = 12.sp, fontWeight = FontWeight.Bold) }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        when (activeTab) {
            0 -> SoundShiftsList(viewModel = viewModel)
            1 -> IpaMatrixView(
                selectedConsonant = selectedConsonant,
                onSelectConsonant = { cons ->
                    selectedConsonant = cons
                    viewModel.ttsManager.playChime(cons.frequencyHz)
                }
            )
            2 -> FormantSynthesizerView(viewModel = viewModel)
        }
    }
}

@Composable
fun SoundShiftsList(viewModel: MainViewModel) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(PhoneticsData.SOUND_SHIFTS) { shift ->
            Card(
                shape = RoundedCornerShape(14.dp),
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
                            text = shift.ruleNameAr,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        IconButton(
                            onClick = {
                                viewModel.ttsManager.speakArabic("${shift.ruleNameAr}. ${shift.descriptionAr}")
                            }
                        ) {
                            Icon(Icons.Filled.VolumeUp, contentDescription = "استماع", tint = MaterialTheme.colorScheme.primary)
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "الصيغة: ${shift.protoSemiticSound} ➔ ${shift.evolvedSound}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = shift.descriptionAr, style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "أمثلة مقارنة: ${shift.examples.joinToString(" • ")}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
        item { Spacer(modifier = Modifier.height(40.dp)) }
    }
}

@Composable
fun IpaMatrixView(
    selectedConsonant: IpaConsonant?,
    onSelectConsonant: (IpaConsonant) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "انقر على أي صامت للاستماع للتردد ومخرجه اللساني:",
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(PhoneticsData.IPA_CONSONANTS) { cons ->
                val isSelected = selectedConsonant?.symbol == cons.symbol
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier
                        .clickable { onSelectConsonant(cons) }
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = cons.symbol,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = cons.arabicName,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${cons.frequencyHz} Hz",
                            fontSize = 9.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        selectedConsonant?.let { cons ->
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "تفاصيل الصامت: ${cons.symbol} (${cons.arabicName})",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "مخرج النطق: ${cons.articulationPlace}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "طريقة النطق: ${cons.articulationManner}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "الرمز الصوتي الدولي (IPA): /${cons.symbol}/", style = MaterialTheme.typography.bodySmall, fontFamily = FontFamily.Monospace)
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun FormantSynthesizerView(viewModel: MainViewModel) {
    var f1Freq by remember { mutableStateOf(500f) }
    var f2Freq by remember { mutableStateOf(1500f) }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "محاكي ترددات الفورمانت والمصوتات السامية",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "توليد رنين المصوتات السامية (/a/, /i/, /u/, /ā/, /ō/, /ē/) بواسطة ترددات F1 و F2:",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "تردد الرنين الأول F1: ${f1Freq.toInt()} Hz", style = MaterialTheme.typography.labelSmall)
            Slider(
                value = f1Freq,
                onValueChange = { f1Freq = it },
                valueRange = 200f..1000f,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "تردد الرنين الثاني F2: ${f2Freq.toInt()} Hz", style = MaterialTheme.typography.labelSmall)
            Slider(
                value = f2Freq,
                onValueChange = { f2Freq = it },
                valueRange = 800f..3000f,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.ttsManager.playChime(f1Freq.toInt())
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.GraphicEq, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("توليد النغمة الصوتية")
            }
        }
    }
}
