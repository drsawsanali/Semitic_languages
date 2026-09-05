package com.example.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.AppTab
import com.example.ui.MainViewModel
import com.example.ui.theme.ReadingThemeMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtlasTopBar(
    viewModel: MainViewModel,
    onOpenDrawer: () -> Unit,
    onOpenSearch: () -> Unit,
    onOpenSettings: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onOpenDrawer,
                modifier = Modifier.testTag("open_navigation_drawer_btn")
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = if (uiState.isEnglishUi) "Open Navigation Menu" else "فتح قائمة التنقل الرئيسية"
                )
            }
        },
        title = {
            Column {
                Text(
                    text = if (uiState.isEnglishUi) "Digital Semitic Atlas" else "أطلس وموسوعة اللغات السامية",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (uiState.isEnglishUi) uiState.currentTab.titleEn else uiState.currentTab.titleAr,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            // Gemini AI Philological Assistant
            IconButton(
                onClick = { viewModel.openAiAnalysisDialog() },
                modifier = Modifier.testTag("gemini_ai_top_bar_btn")
            ) {
                Icon(
                    imageVector = Icons.Filled.Psychology,
                    contentDescription = if (uiState.isEnglishUi) "Gemini AI Philological Assistant" else "مساعد Gemini الفيلولوجي الأكاديمي",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            // Offline Search Action Button
            IconButton(
                onClick = onOpenSearch,
                modifier = Modifier.testTag("search_top_bar_btn")
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = if (uiState.isEnglishUi) "Offline Search Index" else "محرك وفهرس البحث المحلي"
                )
            }

            // Language Switcher (Ar / En)
            TextButton(
                onClick = { viewModel.toggleLanguageUi() },
                modifier = Modifier.testTag("lang_toggle_btn")
            ) {
                Icon(Icons.Filled.Translate, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(if (uiState.isEnglishUi) "العربية" else "English", fontSize = 12.sp)
            }

            // Settings Action
            IconButton(
                onClick = onOpenSettings,
                modifier = Modifier.testTag("settings_btn")
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "الإعدادات والسمات"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun SettingsModalDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val isEnglish = uiState.isEnglishUi

    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.testTag("settings_modal_dialog"),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Palette, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (isEnglish) "Academic Reading Themes & Settings" else "إعدادات القراءة والسمات الأكاديمية")
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = if (isEnglish) "Reading Theme Mode:" else "نمط العرض والسمة (Reading Theme Mode):",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                listOf(
                    ReadingThemeMode.DEFAULT_LIGHT to (if (isEnglish) "Classic Light Mode" else "النمط الكلاسيكي الفاتح"),
                    ReadingThemeMode.SEPIA_ANTIQUE to (if (isEnglish) "Antique Sepia Mode" else "النمط السيبيائي العتيق (Sepia / Antique)"),
                    ReadingThemeMode.DARK to (if (isEnglish) "Night Dark Mode" else "النمط الليلي الداكن (Dark Mode)"),
                    ReadingThemeMode.SLATE to (if (isEnglish) "Basalt Slate Contrast Mode" else "نمط الحجر البازلتي عالي التباين (Slate)")
                ).forEach { (mode, label) ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.setReadingTheme(mode) }
                            .padding(vertical = 4.dp)
                            .testTag("theme_option_${mode.name.lowercase()}")
                    ) {
                        RadioButton(
                            selected = uiState.readingTheme == mode,
                            onClick = { viewModel.setReadingTheme(mode) }
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = label, style = MaterialTheme.typography.bodyMedium)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Divider()
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = if (isEnglish) "Academic Affiliation & Thesis Credits:" else "بيانات التوثيق والبحث:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (isEnglish) {
                        "Sana'a University • Faculty of Arts • Dept. of Archaeology\nResearcher: Sawsan Ali Al-Hudhouri\nSupervised by: Prof. Dr. Ahmad Faq'as"
                    } else {
                        "جامعة صنعاء • كلية الآداب • قسم الآثار\nالباحثة: سوسن علي الحضوري\nإشراف: أ.د. أحمد فقعس"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.testTag("settings_confirm_btn")
            ) {
                Text(if (isEnglish) "Done" else "تم")
            }
        }
    )
}
