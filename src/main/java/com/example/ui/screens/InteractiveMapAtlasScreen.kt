package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.model.LanguageBranch
import com.example.ui.MainViewModel
import com.example.ui.theme.RoyalGold

data class ArchaeologicalSite(
    val id: String,
    val nameAr: String,
    val nameEn: String,
    val branch: LanguageBranch,
    val latitude: Double,
    val longitude: Double,
    val primaryInscriptions: List<String>,
    val descriptionAr: String
)

object MapSitesData {
    val SITES = listOf(
        ArchaeologicalSite(
            id = "byblos",
            nameAr = "جبيل (جُبل / Byblos)",
            nameEn = "Byblos",
            branch = LanguageBranch.NORTHWEST_SEMITIC,
            latitude = 34.12,
            longitude = 35.65,
            primaryInscriptions = listOf("تابوت أحيرام الملكي (KAI 1)", "نقش يحيملك (KAI 4)", "نقش أبي بعل (KAI 5)"),
            descriptionAr = "أقدم ميناء فينيقي كنعاني ومنشأ الأبجدية الخطية الفينيقية ذات الـ 22 حرفاً."
        ),
        ArchaeologicalSite(
            id = "ugarit",
            nameAr = "رأس الشمرا (أوغاريت / Ugarit)",
            nameEn = "Ugarit",
            branch = LanguageBranch.NORTHWEST_SEMITIC,
            latitude = 35.60,
            longitude = 35.78,
            primaryInscriptions = listOf("ألواح ملحمة بعل وموت (KTU 1.1-6)", "لوح أبجدية أوجاريت (KTU 5.6)", "ملحمة كرت"),
            descriptionAr = "حاضرة كنعانية بحرية أنتجت أول أبجدية مسمارية سامية تضم 30 صامتاً متطابقاً مع السامية الأم."
        ),
        ArchaeologicalSite(
            id = "marib",
            nameAr = "مأرب وصرواح (مملكة سبأ)",
            nameEn = "Marib",
            branch = LanguageBranch.ANCIENT_SOUTH_ARABIAN,
            latitude = 15.46,
            longitude = 45.32,
            primaryInscriptions = listOf("نقش صرواح الكبير (RES 3945)", "نقش ترميم سد مأرب (CIH 540)", "نقوش محرم بلقيس (معبد أوام)"),
            descriptionAr = "عاصمة مملكة سبأ وحاضرة خط المسند العربي الجنوبي البارز وسد مأرب التاريخي."
        ),
        ArchaeologicalSite(
            id = "babylon",
            nameAr = "بابل (Babylon)",
            nameEn = "Babylon",
            branch = LanguageBranch.EAST_SEMITIC,
            latitude = 32.54,
            longitude = 44.42,
            primaryInscriptions = listOf("مسلة شريعة حمورابي", "ألواح ملحمة إينوما إيليش", "نقوش نبوخذ نصر الثاني"),
            descriptionAr = "حاضرة السامية الشرقية والبابلية الكلاسيكية وعاصمة الإمبراطورية البابلية."
        ),
        ArchaeologicalSite(
            id = "nineveh",
            nameAr = "نينوى (Nineveh / كويونجق)",
            nameEn = "Nineveh",
            branch = LanguageBranch.EAST_SEMITIC,
            latitude = 36.36,
            longitude = 43.15,
            primaryInscriptions = listOf("مكتبة آشوربانيبال الطينية", "ألواح ملحمة جلجامش والطوفان", "مسلة شلمنصر الثالث السوداء"),
            descriptionAr = "عاصمة الإمبراطورية الآشورية ومستودع أضخم أرشيف مسماري للسامية الشرقية."
        ),
        ArchaeologicalSite(
            id = "petra",
            nameAr = "البتراء (رقمو / Petra)",
            nameEn = "Petra",
            branch = LanguageBranch.NORTHWEST_SEMITIC,
            latitude = 30.32,
            longitude = 35.44,
            primaryInscriptions = listOf("نقش قبر عرتاس الرابع", "نقوش قبر التوركمانية", "نقش نمارة الأثري المؤرخ 328م"),
            descriptionAr = "عاصمة مملكة الأنباط ومهد تطور الخط النبطي إلى الخط العربي الكوفي."
        ),
        ArchaeologicalSite(
            id = "axum",
            nameAr = "أكسوم (Axum)",
            nameEn = "Aksum",
            branch = LanguageBranch.ETHIOSEMITIC,
            latitude = 14.13,
            longitude = 38.72,
            primaryInscriptions = listOf("مسلة الملك عيزانا الثلاثية (RIE 185)", "نقوش المسند الجعزي القديم"),
            descriptionAr = "عاصمة إمبراطورية أكسوم ومهد اللغات السامية الإثيوبية وخط الفيدل."
        ),
        ArchaeologicalSite(
            id = "carthage",
            nameAr = "قرطاج (قَرْت حَدَشْت / Carthage)",
            nameEn = "Carthage",
            branch = LanguageBranch.NORTHWEST_SEMITIC,
            latitude = 36.85,
            longitude = 10.32,
            primaryInscriptions = listOf("تعرفة قرطاج الدينية (CIS I 165)", "نقوش توفت قرطاج النذرية (KAI 61-96)"),
            descriptionAr = "حاضرة الفينيقية الغربية والبونية وأعظم إمبراطورية بحرية في غرب المتوسط."
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InteractiveMapAtlasScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedBranch by remember { mutableStateOf<LanguageBranch?>(null) }
    var selectedSiteId by remember { mutableStateOf<String>("byblos") }

    val filteredSites = remember(selectedBranch) {
        if (selectedBranch == null) MapSitesData.SITES
        else MapSitesData.SITES.filter { it.branch == selectedBranch }
    }

    val currentSite = MapSitesData.SITES.find { it.id == selectedSiteId } ?: MapSitesData.SITES.first()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("interactive_map_atlas_screen")
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Branch Filters
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                FilterChip(
                    selected = selectedBranch == null,
                    onClick = { selectedBranch = null },
                    label = { Text("جميع الحواضر السامية", fontSize = 12.sp) }
                )
            }
            items(LanguageBranch.values()) { branch ->
                val isSelected = selectedBranch == branch
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedBranch = if (isSelected) null else branch },
                    label = { Text(branch.titleAr, fontSize = 12.sp) }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Visual Map Representation Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)),
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Filled.Map,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "أطلس المراكز الإبيغرافية في الشرق الأدنى القديم",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "الموقع المحدد: ${currentSite.nameAr} (${currentSite.latitude}° N, ${currentSite.longitude}° E)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "المواقع الأثرية ومكتشفات النقوش:",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredSites) { site ->
                val isSelected = site.id == selectedSiteId
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f) else MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedSiteId = site.id }
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = site.nameAr,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Badge(containerColor = MaterialTheme.colorScheme.primary) {
                                Text(
                                    text = site.branch.titleAr,
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                                    fontSize = 10.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = site.descriptionAr, style = MaterialTheme.typography.bodySmall)

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "أبرز النقوش المكتشفة: ${site.primaryInscriptions.joinToString(" • ")}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(40.dp)) }
        }
    }
}
