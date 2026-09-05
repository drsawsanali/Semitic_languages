package com.example.core.data

import com.example.core.model.ArchaeologicalSite
import com.example.core.model.ChronologyEvent
import com.example.core.model.LanguageBranch

object ArchaeologicalSitesData {
    val ALL_SITES: List<ArchaeologicalSite> = listOf(
        ArchaeologicalSite(
            id = "byblos",
            nameAr = "جبيل (جُبل / Byblos)",
            nameEn = "Byblos",
            latitude = 34.12,
            longitude = 35.65,
            region = "لبنان / ساحل بلاد الشام",
            associatedBranch = LanguageBranch.NORTHWEST_SEMITIC,
            associatedLanguages = listOf("فينيقية", "كنعانية قديمة"),
            historicalSignificanceAr = "أقدم ميناء فينيقي كنعاني ومنشأ الأبجدية الخطية الفينيقية ذات الـ 22 حرفاً.",
            inscriptionsFound = listOf("تابوت أحيرام الملكي (KAI 1)", "نقش يحيملك (KAI 4)", "نقش أبي بعل (KAI 5)"),
            notableArtifacts = listOf("تابوت أحيرام المنحوت من الحجر الجيري", "أختام أسطوانية برونزية"),
            periodDisplay = "الألفية الثالثة ق.م - العصر الروماني"
        ),
        ArchaeologicalSite(
            id = "ugarit",
            nameAr = "رأس الشمرا (أوغاريت / Ugarit)",
            nameEn = "Ugarit",
            latitude = 35.60,
            longitude = 35.78,
            region = "سوريا / الساحل السوري",
            associatedBranch = LanguageBranch.NORTHWEST_SEMITIC,
            associatedLanguages = listOf("أوغاريتية", "أكادية"),
            historicalSignificanceAr = "حاضرة كنعانية بحرية أنتجت أول أبجدية مسمارية سامية تضم 30 صامتاً متطابقاً مع مخارج السامية الأم.",
            inscriptionsFound = listOf("ألواح ملحمة بعل وموت (KTU 1.1-6)", "لوح أبجدية أوجاريت (KTU 5.6)", "ملحمة كرت الأسطورية"),
            notableArtifacts = listOf("المكتبة الملكية للألواح الطينية", "تمثال بعل ذو الصاعقة البرونزي"),
            periodDisplay = "القرن 14 - 12 ق.م"
        ),
        ArchaeologicalSite(
            id = "marib",
            nameAr = "مأرب وصرواح (مملكة سبأ)",
            nameEn = "Marib & Sirwah",
            latitude = 15.46,
            longitude = 45.32,
            region = "اليمن / وادي أذنة وجوف اليمن",
            associatedBranch = LanguageBranch.ANCIENT_SOUTH_ARABIAN,
            associatedLanguages = listOf("سبئية", "معينية"),
            historicalSignificanceAr = "عاصمة مملكة سبأ وحاضرة خط المسند العربي الجنوبي البارز وسد مأرب التاريخي العظيم.",
            inscriptionsFound = listOf("نقش صرواح الكبير للمكرب كربئيل وتر (RES 3945)", "نقش ترميم سد مأرب لشرحبيل يعفر (CIH 540)", "نقوش معبد أوام (محرم بلقيس)"),
            notableArtifacts = listOf("أعمدة معبد برّان (عرش بلقيس)", "تمثال معديكرب البرونزي المصمت"),
            periodDisplay = "القرن العاشر ق.م - القرن السادس الميلادي"
        ),
        ArchaeologicalSite(
            id = "babylon",
            nameAr = "بابل (Babylon)",
            nameEn = "Babylon",
            latitude = 32.54,
            longitude = 44.42,
            region = "العراق / بلاد ما بين النهرين",
            associatedBranch = LanguageBranch.EAST_SEMITIC,
            associatedLanguages = listOf("أكادية بابلية"),
            historicalSignificanceAr = "حاضرة السامية الشرقية والبابلية الكلاسيكية وعاصمة الإمبراطورية البابلية ومصدر القوانين الإنسانية الأولى.",
            inscriptionsFound = listOf("مسلة شريعة حمورابي المصنوعة من حجر الديوريت الأسود", "ألواح ملحمة إينوما إيليش", "نقوش نبوخذ نصر الثاني لبوابة عشتار"),
            notableArtifacts = listOf("بوابة عشتار الزرقاء المطعمة بالحيوانات الأسطورية", "برج بابل وزقورة إيتيمينانكي"),
            periodDisplay = "الألفية الثانية ق.م - 539 ق.م"
        ),
        ArchaeologicalSite(
            id = "nineveh",
            nameAr = "نينوى (Nineveh / كويونجق)",
            nameEn = "Nineveh",
            latitude = 36.36,
            longitude = 43.15,
            region = "شمال العراق / الموصل",
            associatedBranch = LanguageBranch.EAST_SEMITIC,
            associatedLanguages = listOf("أكادية آشورية"),
            historicalSignificanceAr = "عاصمة الإمبراطورية الآشورية ومستودع أضخم مكتبة رُقيمات مسمارية للسامية الشرقية أنشأها آشوربانيبال.",
            inscriptionsFound = listOf("مكتبة آشوربانيبال الطينية (أكثر من 30 ألف رقيم)", "ألواح ملحمة جلجامش ورواية الطوفان السامية", "مسلة شلمنصر الثالث السوداء"),
            notableArtifacts = listOf("ثيران لاماسو المجنحة ذات الوجوه البشرية", "نقوش الصيد الملكي البارزة بالمرمر"),
            periodDisplay = "الألفية الأولى ق.م حتى 612 ق.م"
        ),
        ArchaeologicalSite(
            id = "petra",
            nameAr = "البتراء (رقمو / Petra)",
            nameEn = "Petra",
            latitude = 30.32,
            longitude = 35.44,
            region = "الأردن / وادي موسى",
            associatedBranch = LanguageBranch.NORTHWEST_SEMITIC,
            associatedLanguages = listOf("آرامية نبطية", "عربية شمالية قديمة"),
            historicalSignificanceAr = "عاصمة مملكة الأنباط المنحوتة في الصخر الوردي ومهد التحول الخطي من القلم النبطي إلى الخط العربي الكوفي.",
            inscriptionsFound = listOf("نقش قبر عرتاس الرابع", "نقوش قبر التوركمانية القانونية", "نقش النمارة الأثري المؤرخ 328م لامريء القيس"),
            notableArtifacts = listOf("واجهة الخزنة (البنك الصخري النبطي)", "الدير الصخري ونظام القنوات والجرار المائية"),
            periodDisplay = "القرن الرابع ق.م - 106 م"
        ),
        ArchaeologicalSite(
            id = "axum",
            nameAr = "أكسوم (Axum)",
            nameEn = "Aksum",
            latitude = 14.13,
            longitude = 38.72,
            region = "إثيوبيا / إقليم تيغراي",
            associatedBranch = LanguageBranch.ETHIOSEMITIC,
            associatedLanguages = listOf("جعزية", "مسندية إثيوبية"),
            historicalSignificanceAr = "عاصمة إمبراطورية أكسوم ومهد اللغات السامية الإثيوبية والتحول من الأبجدية الصامتة إلى مقطعية الفيدل المصلوبة.",
            inscriptionsFound = listOf("مسلة الملك عيزانا الثلاثية اللغات (RIE 185)", "نقوش المسند الجعزي العتيق", "نقش كالب ملك حمير وأكسوم"),
            notableArtifacts = listOf("مسلات أكسوم الجرانيتية العملاقة الشاهقة", "المسكوكات الذهبية للأباطرة الأكسوميين"),
            periodDisplay = "القرن الأول ق.م - القرن العاشر الميلادي"
        ),
        ArchaeologicalSite(
            id = "carthage",
            nameAr = "قرطاج (قَرْت حَدَشْت / Carthage)",
            nameEn = "Carthage",
            latitude = 36.85,
            longitude = 10.32,
            region = "تونس / خليج تونس",
            associatedBranch = LanguageBranch.NORTHWEST_SEMITIC,
            associatedLanguages = listOf("بونيقية", "فينيقية جديدة"),
            historicalSignificanceAr = "حاضرة الفينيقية الغربية والبونية وأعظم إمبراطورية بحرية وتجارية سامية في غرب حوض البحر الأبيض المتوسط.",
            inscriptionsFound = listOf("تعرفة قرطاج الدينية ونظام الأضاحي (CIS I 165)", "نقوش توفت قرطاج النذرية (KAI 61-96)", "نقوش حنبعل البحرية"),
            notableArtifacts = listOf("توابيت الرخام البونيقية المنحوتة لرجال الدين والبحارة", "مرفأ قرطاج الحربي الدائري الفريد"),
            periodDisplay = "814 ق.م - 146 ق.م"
        ),
        ArchaeologicalSite(
            id = "ebla",
            nameAr = "تل مرديخ (إيبلا / Ebla)",
            nameEn = "Ebla",
            latitude = 35.80,
            longitude = 36.80,
            region = "شمال غرب سوريا / إدلب",
            associatedBranch = LanguageBranch.EAST_SEMITIC,
            associatedLanguages = listOf("إيبلاوية", "أكادية مبكرة"),
            historicalSignificanceAr = "مملكة سامية كبرى من الألف الثالث ق.م كشفت عن أقدم معاجم ثنائية اللغة سومرية-إيبلاوية في التاريخ البشري.",
            inscriptionsFound = listOf("ألواح الأرشيف الملكي الإيبلاوي (أكثر من 17 ألف لوح مسماري)", "المعجم اللغوي المقارن إيبلاوي-سومري"),
            notableArtifacts = listOf("ألواح الطين الإدارية للملك إبريوم", "القصر الملكي G وأواني اللازورد والذهب"),
            periodDisplay = "2500 ق.م - 1600 ق.م"
        ),
        ArchaeologicalSite(
            id = "palmyra",
            nameAr = "تدمر (تدمور / Palmyra)",
            nameEn = "Palmyra",
            latitude = 34.56,
            longitude = 38.27,
            region = "سوريا / بادية الشام",
            associatedBranch = LanguageBranch.NORTHWEST_SEMITIC,
            associatedLanguages = listOf("آرامية تدمرية", "عربية شمالية"),
            historicalSignificanceAr = "لؤلؤة البادية وملتقى طريق الحرير التجاري ومقر حكم الملكة زنوبيا بالخط التدمري المميز.",
            inscriptionsFound = listOf("قانون تعرفة تدمر الجمركية الضخمة (CIS II 3913)", "نقوش مدافن تدمر البرجية والمنحوتات الجنائزية"),
            notableArtifacts = listOf("معبد بل والشارع المستقيم المعمد", "تماثيل نصفية تدمرية من الحجر الكلسي بالزي الشرقي"),
            periodDisplay = "القرن الثاني ق.م - 273 م"
        )
    )
}
