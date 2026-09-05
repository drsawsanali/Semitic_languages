package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.AppTab
import com.example.ui.MainViewModel

/**
 * Material 3 Navigation Drawer organizing the Semitic Encyclopedia
 * into main sections: 'اللغات', 'النقوش', 'الحضارات', 'المفضلة',
 * alongside academic tools and specialized philological modules.
 */
@Composable
fun SemiticNavigationDrawer(
    viewModel: MainViewModel,
    onTabSelected: (AppTab) -> Unit,
    onOpenSearch: () -> Unit,
    onOpenSettings: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val isEnglish = uiState.isEnglishUi
    val persistedLanguages by viewModel.persistedLanguages.collectAsState(initial = emptyList())
    val persistedInscriptions by viewModel.persistedInscriptions.collectAsState(initial = emptyList())
    val persistedCivilizations by viewModel.persistedCivilizations.collectAsState(initial = emptyList())
    val bookmarks by viewModel.bookmarks.collectAsState(initial = emptyList())

    ModalDrawerSheet(
        modifier = Modifier
            .widthIn(max = 340.dp)
            .testTag("semitic_navigation_drawer")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "𐤀", // Phoenician Aleph
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Column {
                            Text(
                                text = if (isEnglish) "Digital Semitic Atlas" else "أطلس وموسوعة اللغات السامية",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = if (isEnglish) "Sana'a University • Dept. of Archaeology" else "جامعة صنعاء • كلية الآداب • قسم الآثار",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Stats row in header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        HeaderCounterBadge(
                            label = if (isEnglish) "Languages" else "اللغات",
                            count = "${persistedLanguages.size}",
                            modifier = Modifier.weight(1f)
                        )
                        HeaderCounterBadge(
                            label = if (isEnglish) "Inscriptions" else "النقوش",
                            count = "${persistedInscriptions.size}",
                            modifier = Modifier.weight(1f)
                        )
                        HeaderCounterBadge(
                            label = if (isEnglish) "Civilizations" else "الحضارات",
                            count = "${persistedCivilizations.size}",
                            modifier = Modifier.weight(1f)
                        )
                        HeaderCounterBadge(
                            label = if (isEnglish) "Favorites" else "المفضلة",
                            count = "${bookmarks.size}",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ==========================================
            // Category 1: الأقسام الرئيسية (Core Sections)
            // ==========================================
            SectionHeader(title = if (isEnglish) "MAIN SECTIONS" else "الأقسام الرئيسية")

            DrawerTabItem(
                tab = AppTab.LANGUAGES,
                label = if (isEnglish) "Languages" else "اللغات",
                sublabel = if (isEnglish) "35+ Semitic Languages & Dialects" else "٣٥+ لغة ولهجة سامية موثقة",
                icon = Icons.Filled.Language,
                isSelected = uiState.currentTab == AppTab.LANGUAGES,
                badgeCount = persistedLanguages.size.takeIf { it > 0 },
                onClick = { onTabSelected(AppTab.LANGUAGES) },
                testTag = "drawer_item_languages"
            )

            DrawerTabItem(
                tab = AppTab.INSCRIPTIONS,
                label = if (isEnglish) "Inscriptions" else "النقوش",
                sublabel = if (isEnglish) "Royal Stelae & Clay Tablets" else "المسلات والرقيمات والتوابيت الملكية",
                icon = Icons.Filled.HistoryEdu,
                isSelected = uiState.currentTab == AppTab.INSCRIPTIONS,
                badgeCount = persistedInscriptions.size.takeIf { it > 0 },
                onClick = { onTabSelected(AppTab.INSCRIPTIONS) },
                testTag = "drawer_item_inscriptions"
            )

            DrawerTabItem(
                tab = AppTab.CIVILIZATIONS,
                label = if (isEnglish) "Civilizations" else "الحضارات",
                sublabel = if (isEnglish) "Ancient Kingdoms, Deities & Rulers" else "الممالك، الحواضر، والبانثيون القديم",
                icon = Icons.Filled.AccountBalance,
                isSelected = uiState.currentTab == AppTab.CIVILIZATIONS,
                badgeCount = persistedCivilizations.size.takeIf { it > 0 },
                onClick = { onTabSelected(AppTab.CIVILIZATIONS) },
                testTag = "drawer_item_civilizations"
            )

            DrawerTabItem(
                tab = AppTab.FAVORITES,
                label = if (isEnglish) "Favorites" else "المفضلة",
                sublabel = if (isEnglish) "Bookmarked Chapters & Artifacts" else "المحفوظات، الفصول، والشواهد المفضلة",
                icon = Icons.Filled.Bookmark,
                isSelected = uiState.currentTab == AppTab.FAVORITES,
                badgeCount = bookmarks.size.takeIf { it > 0 },
                onClick = { onTabSelected(AppTab.FAVORITES) },
                testTag = "drawer_item_favorites"
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))

            // ==========================================
            // Category 2: الموسوعة والدراسات التخصصية
            // ==========================================
            SectionHeader(title = if (isEnglish) "ENCYCLOPEDIA & TOOLS" else "الموسوعة والدراسات الأكاديمية")

            DrawerTabItem(
                tab = AppTab.READER,
                label = if (isEnglish) "Encyclopedia Reader" else "القارئ الموسوعي",
                sublabel = if (isEnglish) "Academic LaTeX Chapters & Studies" else "الفصول والأبحاث والتوثيق الأكاديمي",
                icon = Icons.Filled.MenuBook,
                isSelected = uiState.currentTab == AppTab.READER,
                onClick = { onTabSelected(AppTab.READER) },
                testTag = "drawer_item_reader"
            )

            DrawerTabItem(
                tab = AppTab.PHONETICS,
                label = if (isEnglish) "Phonetics Simulator" else "محاكي علم الأصوات",
                sublabel = if (isEnglish) "Sound Shifts & Audio Coach" else "قوانين التحول الصوتي ومخارج الحروف",
                icon = Icons.Filled.GraphicEq,
                isSelected = uiState.currentTab == AppTab.PHONETICS,
                onClick = { onTabSelected(AppTab.PHONETICS) },
                testTag = "drawer_item_phonetics"
            )

            DrawerTabItem(
                tab = AppTab.LEXICON,
                label = if (isEnglish) "Comparative Lexicon" else "المعجم المقارن",
                sublabel = if (isEnglish) "Proto-Semitic Etymology Matrix" else "تأصيل الجذور السامية المقارنة",
                icon = Icons.Filled.Translate,
                isSelected = uiState.currentTab == AppTab.LEXICON,
                onClick = { onTabSelected(AppTab.LEXICON) },
                testTag = "drawer_item_lexicon"
            )

            DrawerTabItem(
                tab = AppTab.MAP,
                label = if (isEnglish) "Historical Map Atlas" else "الأطلس والخريطة",
                sublabel = if (isEnglish) "Sites, Trade Routes & Heatmap" else "مواقع الاكتشاف وخريطة الانتشار",
                icon = Icons.Filled.Map,
                isSelected = uiState.currentTab == AppTab.MAP,
                onClick = { onTabSelected(AppTab.MAP) },
                testTag = "drawer_item_map"
            )

            DrawerTabItem(
                tab = AppTab.LAB,
                label = if (isEnglish) "Research Lab & Keyboard" else "مختبر الأبحاث والكتابة",
                sublabel = if (isEnglish) "Virtual Ancient Scripts & AI" else "لوحة مفاتيح الخطوط السامية القديمة",
                icon = Icons.Filled.Keyboard,
                isSelected = uiState.currentTab == AppTab.LAB,
                onClick = { onTabSelected(AppTab.LAB) },
                testTag = "drawer_item_lab"
            )

            DrawerTabItem(
                tab = AppTab.LEARNING,
                label = if (isEnglish) "Learning & Quiz Center" else "مركز الاختبارات والتعليم",
                sublabel = if (isEnglish) "Grammar Flashcards & Quizzes" else "البطاقات التعليمية والتحديات اليومية",
                icon = Icons.Filled.School,
                isSelected = uiState.currentTab == AppTab.LEARNING,
                onClick = { onTabSelected(AppTab.LEARNING) },
                testTag = "drawer_item_learning"
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))

            // Quick Actions & Settings
            SectionHeader(title = if (isEnglish) "QUICK ACTIONS" else "أدوات وتخصيص")

            NavigationDrawerItem(
                icon = { Icon(Icons.Filled.AutoStories, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
                label = { Text(if (isEnglish) "Linguistic Terms Glossary" else "معجم المصطلحات اللغوية السامية") },
                selected = false,
                onClick = { viewModel.toggleGlossaryScreen(true) },
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
                    .testTag("drawer_item_glossary")
            )

            NavigationDrawerItem(
                icon = { Icon(Icons.Filled.Psychology, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
                label = { Text(if (isEnglish) "Gemini AI Philological Assistant" else "مساعد Gemini الفيلولوجي") },
                selected = false,
                onClick = { viewModel.openAiAnalysisDialog() },
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
                    .testTag("drawer_item_gemini_ai")
            )

            NavigationDrawerItem(
                icon = { Icon(Icons.Filled.Search, contentDescription = null) },
                label = { Text(if (isEnglish) "Search Index & Concordance" else "محرك البحث والفهرس الشامل") },
                selected = false,
                onClick = onOpenSearch,
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
                    .testTag("drawer_item_search")
            )

            NavigationDrawerItem(
                icon = { Icon(Icons.Filled.Settings, contentDescription = null) },
                label = { Text(if (isEnglish) "Display Themes & Credits" else "السمات وإعدادات القراءة") },
                selected = false,
                onClick = onOpenSettings,
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
                    .testTag("drawer_item_settings")
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
    )
}

@Composable
private fun DrawerTabItem(
    tab: AppTab,
    label: String,
    sublabel: String,
    icon: ImageVector,
    isSelected: Boolean,
    badgeCount: Int? = null,
    onClick: () -> Unit,
    testTag: String
) {
    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        label = {
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )
                Text(
                    text = sublabel,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp
                )
            }
        },
        badge = {
            if (badgeCount != null && badgeCount > 0) {
                Surface(
                    shape = CircleShape,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                ) {
                    Text(
                        text = "$badgeCount",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }
        },
        selected = isSelected,
        onClick = onClick,
        modifier = Modifier
            .padding(NavigationDrawerItemDefaults.ItemPadding)
            .testTag(testTag)
    )
}

@Composable
private fun HeaderCounterBadge(
    label: String,
    count: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 4.dp)
        ) {
            Text(
                text = count,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
