package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.data.SemiticGlossaryData
import com.example.ui.theme.RoyalGold

/**
 * Renders Arabic or academic text with interactive clickable words.
 * Semitic linguistic glossary terms are automatically highlighted with gold accents,
 * and clicking on any word triggers the interactive philological lookup via Gemini.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ClickableLinguisticText(
    text: String,
    onWordClick: (word: String, surroundingContext: String) -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    lineHeight: TextUnit = 26.sp,
    color: Color = MaterialTheme.colorScheme.onSurface,
    fontFamily: FontFamily = FontFamily.Serif,
    highlightRecognizedTerms: Boolean = true
) {
    // Split into paragraphs to preserve reading flow
    val paragraphs = remember(text) {
        text.split("\n\n", "\n").filter { it.isNotBlank() }
    }

    Column(modifier = modifier.testTag("clickable_linguistic_text_container")) {
        paragraphs.forEachIndexed { pIdx, paragraph ->
            // Split paragraph into tokens while keeping punctuation attached
            val tokens = remember(paragraph) {
                paragraph.trim().split(Regex("\\s+")).filter { it.isNotBlank() }
            }

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                tokens.forEachIndexed { wIdx, rawToken ->
                    // Strip punctuation for matching and lookup
                    val cleanWord = rawToken.replace(Regex("[.,:;!؟()\"'«»\\[\\]{}،]"), "")
                    val isGlossaryTerm = remember(cleanWord) {
                        highlightRecognizedTerms && cleanWord.length >= 2 && SemiticGlossaryData.findTerm(cleanWord) != null
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (isGlossaryTerm) RoyalGold.copy(alpha = 0.18f) else Color.Transparent
                            )
                            .clickable {
                                if (cleanWord.isNotBlank()) {
                                    // Surrounding context sentence: approximate 7-10 words
                                    val start = maxOf(0, wIdx - 4)
                                    val end = minOf(tokens.size, wIdx + 5)
                                    val contextSnippet = tokens.subList(start, end).joinToString(" ")
                                    onWordClick(cleanWord, contextSnippet)
                                }
                            }
                            .padding(horizontal = if (isGlossaryTerm) 3.dp else 1.dp, vertical = 1.dp)
                    ) {
                        Text(
                            text = rawToken,
                            fontSize = fontSize,
                            lineHeight = lineHeight,
                            fontFamily = fontFamily,
                            color = if (isGlossaryTerm) MaterialTheme.colorScheme.primary else color,
                            fontWeight = if (isGlossaryTerm) FontWeight.Bold else FontWeight.Normal,
                            textDecoration = if (isGlossaryTerm) TextDecoration.Underline else TextDecoration.None
                        )
                    }
                }
            }

            if (pIdx < paragraphs.size - 1) {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
