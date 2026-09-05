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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.data.LexiconData
import com.example.core.model.LexiconCognate
import com.example.core.model.LexiconRoot
import com.example.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComparativeLexiconScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedRootId by remember { mutableStateOf("root_mlk") }

    val filteredRoots = remember(searchQuery) {
        LexiconData.COMPARATIVE_ROOTS.filter { root ->
            searchQuery.isEmpty() ||
                    root.rootProtoSemitic.contains(searchQuery, ignoreCase = true) ||
                    root.meaningAr.contains(searchQuery, ignoreCase = true) ||
                    root.meaningEn.contains(searchQuery, ignoreCase = true)
        }
    }

    val currentRoot = LexiconData.COMPARATIVE_ROOTS.find { it.id == selectedRootId }
        ?: LexiconData.COMPARATIVE_ROOTS.first()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("comparative_lexicon_screen")
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("ابحث في الجذور السامية المقارنة (*m-l-k, *b-y-t...)", fontSize = 13.sp) },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("lexicon_search_input")
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Roots Horizontal Selector
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(filteredRoots) { root ->
                val isSelected = root.id == selectedRootId
                ElevatedFilterChip(
                    selected = isSelected,
                    onClick = { selectedRootId = root.id },
                    label = {
                        Text(
                            text = "${root.rootProtoSemitic} (${root.meaningAr})",
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    leadingIcon = {
                        if (isSelected) {
                            Icon(Icons.Filled.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Active Root Comparative Card
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "الجذر السامي الأم: ${currentRoot.rootProtoSemitic}",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "الصيغة المعاد تركيبها: ${currentRoot.reconstructedForm}",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontFamily = FontFamily.Serif
                                )
                            }

                            IconButton(
                                onClick = {
                                    viewModel.ttsManager.speakArabic(
                                        "الجذر ${currentRoot.rootProtoSemitic}. المعنى: ${currentRoot.meaningAr}"
                                    )
                                }
                            ) {
                                Icon(Icons.Filled.VolumeUp, contentDescription = "استماع", tint = MaterialTheme.colorScheme.primary)
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "الدلالة المعجمية: ${currentRoot.meaningAr} (${currentRoot.meaningEn})",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            item {
                Text(
                    text = "التطور الاشتقاقي والمقابلات في اللغات السامية:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            items(currentRoot.cognates) { cognate ->
                CognateItemRow(cognate = cognate, viewModel = viewModel)
            }

            item { Spacer(modifier = Modifier.height(40.dp)) }
        }
    }
}

@Composable
fun CognateItemRow(cognate: LexiconCognate, viewModel: MainViewModel) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = cognate.languageNameAr,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = cognate.wordOriginalScript,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "النقحرة: ${cognate.transliteration} | IPA: /${cognate.ipaTranscription}/",
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = FontFamily.Serif,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "المعنى: ${cognate.meaningAr}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            IconButton(
                onClick = {
                    viewModel.ttsManager.speakArabic(
                        "${cognate.languageNameAr}: ${cognate.meaningAr}"
                    )
                }
            ) {
                Icon(Icons.Filled.VolumeUp, contentDescription = "استماع", tint = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
