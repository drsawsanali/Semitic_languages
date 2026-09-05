package com.example.core.data

import com.example.core.model.*

object PhoneticsData {
    val SOUND_SHIFT_RULES: List<SoundShiftRule> = listOf(
        SoundShiftRule(
            id = "canaanite_shift",
            ruleNameAr = "التحول الكنعاني للصوائت (*ā > ō)",
            ruleNameEn = "Canaanite Vowel Shift (*ā > ō)",
            protoSemiticSound = "*ā",
            targetSound = "ō / ū",
            appliedLanguages = listOf("الفينيقية", "البونيقية", "المؤابية", "العبرية القديمة"),
            exceptionLanguages = listOf("الأوغاريتية", "الآرامية", "العربية", "السبئية", "الأكادية"),
            linguisticExplanationAr = "قانون فونولوجي تاريخي حاسم يحدد انتماء اللغة إلى الفرع الكنعاني: تحولت حركة الفتحة الطويلة الممدودة (*ā) في السامية الأم إلى ضمة طويلة مشبعة (ō)، وتحولت لاحقاً في الفينيقية المتأخرة والبونيقية إلى (ū).",
            comparativeExamples = listOf(
                "السامية الأم: *šalāmu- > الفينيقية: šalōm (سلام)",
                "السامية الأم: *ṭābu- > الفينيقية: ṭōb (طيّب/خير)",
                "السامية الأم: *ʾanāku- > الفينيقية: ʾanōkī (أنا)",
                "السامية الأم: *raʾšu- (*rāšu) > الفينيقية: rōš (رأس)"
            )
        ),
        SoundShiftRule(
            id = "interdental_shifts",
            ruleNameAr = "صيرورة تحول الصوامت بين الأسنانية (*ḏ, *ṯ, *ṱ)",
            ruleNameEn = "Proto-Semitic Interdental Sound Shifts",
            protoSemiticSound = "*ḏ, *ṯ, *ṱ (ذ، ث، ظ)",
            targetSound = "d/z, t/š, ṭ/ṣ",
            appliedLanguages = listOf("الآرامية", "الأكادية", "الفينيقية", "العبرية"),
            exceptionLanguages = listOf("العربية الفصحى", "الأوغاريتية", "السبئية", "المهرية"),
            linguisticExplanationAr = "حافظت العربية والسبئية والأوغاريتية على الصوامت بين الأسنانية الثلاثة (ذ، ث، ظ)، بينما تحولت في الآرامية إلى أسنانية انفجارية (ذ > د، ث > ت، ظ > ط)، وفي الكنعانية والعبرية إلى صفيرية (ذ > ز، ث > ش، ظ > ص)، وفي الأكادية (ذ > ز، ث > ش، ظ > ص).",
            comparativeExamples = listOf(
                "السامية الأم: *ḏahabu- (ذهب) > الآرامية: dehav (دَهَب) > العبرية: zahav (زَهَب) > الأكادية: ḫurāṣu",
                "السامية الأم: *ṯawru- (ثور) > الآرامية: tōrā (تورا) > العبرية: šōr (شور)",
                "السامية الأم: *ṱillu- (ظل) > الآرامية: ṭillā (طِلّا) > العبرية: ṣēl (صِل)"
            )
        ),
        SoundShiftRule(
            id = "begadkefat_rule",
            ruleNameAr = "قانون الإرخاء والتشديد (بجد كفت Begadkefat)",
            ruleNameEn = "Begadkefat Spirantization Rule",
            protoSemiticSound = "b, g, d, k, p, t (ب، ج، د، ك، ف/پ، ت)",
            targetSound = "v, ɣ, ð, x, f, θ (ف، غ، ذ، خ، ف، ث)",
            appliedLanguages = listOf("السريانية الكلاسيكية", "الآرامية التلمودية", "العبرية الطبرية"),
            exceptionLanguages = listOf("العربية", "الأكادية", "الأوغاريتية", "السبئية"),
            linguisticExplanationAr = "ظاهرة فونولوجية تنص على أن الصوامت الانفجارية الستة (ب، ج، د، ك، ف، ت) ترقق وتتحول إلى صوامت احتكاكية لينة إذا سبقتها حركة صائتية غير متبوعة بتشديد.",
            comparativeExamples = listOf(
                "السريانية: malḵā (مَلخا) حيث رققت الكاف إلى خاء لوقوعها بعد فتحة",
                "السريانية: kaṯḇā (كثوا) حيث رققت التاء إلى ثاء والباء إلى واو/فاء رخوة"
            )
        ),
        SoundShiftRule(
            id = "diphthong_contraction",
            ruleNameAr = "انكماش المزدوجات الصوتية (*ay > ē, *aw > ō)",
            ruleNameEn = "Monophthongization of Diphthongs",
            protoSemiticSound = "*ay, *aw (أي، أو)",
            targetSound = "ē, ō",
            appliedLanguages = listOf("الأكادية", "الفينيقية", "السريانية", "الجعزية"),
            exceptionLanguages = listOf("العربية الفصحى", "الأوغاريتية (جزئياً)", "السبئية"),
            linguisticExplanationAr = "انكماش المزدوج الصائتي المكون من فتحة وياء أو فتحة وواو إلى حركة طويلة ممدودة بسيطة ومتجانسة.",
            comparativeExamples = listOf(
                "السامية الأم: *baytu- (بيت) > الفينيقية: bēt (بِيت) > الأكادية: bītum",
                "السامية الأم: *yawmu- (يوم) > الفينيقية: yōm (يُوم) > السريانية: yawmā / yōmā"
            )
        )
    )

    val SOUND_SHIFTS: List<SoundShiftRule> = SOUND_SHIFT_RULES

    val CONSONANT_IPA_CHART: List<IpaConsonant> = listOf(
        IpaConsonant("ʔ", "همزة (أ)", "حنجري انفجاري مهموس", "Glottal Stop", "أكادية، أوغاريتية، عربية، عبرية، جعزية", 440),
        IpaConsonant("b", "باء (ب)", "شفوي انفجاري مجهور", "Voiced Bilabial Plosive", "كافة اللغات السامية", 466),
        IpaConsonant("g", "جيم غير معطشة (گ)", "طبقي انفجاري مجهور", "Voiced Velar Plosive", "أكادية، عبرية، سريانية، يمنية قديمة", 493),
        IpaConsonant("d", "دال (د)", "أسناني انفجاري مجهور", "Voiced Dental Plosive", "كافة اللغات السامية", 523),
        IpaConsonant("h", "هاء (هـ)", "حنجري احتكاكي مهموس", "Voiceless Glottal Fricative", "سامية غربية وجنوبية", 554),
        IpaConsonant("w", "واو (و)", "شفوي طبقي شبه صامت", "Labial-velar Approximant", "كافة اللغات السامية", 587),
        IpaConsonant("z", "زاي (ز)", "لثوي صفيري مجهور", "Voiced Alveolar Fricative", "كافة اللغات السامية", 622),
        IpaConsonant("ħ", "حاء (ح)", "حلقي احتكاكي مهموس", "Voiceless Pharyngeal Fricative", "أوغاريتية، عربية، سبئية، جعزية", 659),
        IpaConsonant("tˤ", "طاء (ط)", "أسناني انفجاري مطبق/مقذوف", "Pharyngealized/Ejective Dental", "كافة اللغات السامية", 698),
        IpaConsonant("j", "ياء (ي)", "حنكي شبه صامت", "Palatal Approximant", "كافة اللغات السامية", 740),
        IpaConsonant("k", "كاف (ك)", "طبقي انفجاري مهموس", "Voiceless Velar Plosive", "كافة اللغات السامية", 784),
        IpaConsonant("l", "لام (ل)", "لثوي جانبي مجهور", "Alveolar Lateral Approximant", "كافة اللغات السامية", 830),
        IpaConsonant("m", "ميم (م)", "شفوي أنفي مجهور", "Bilabial Nasal", "كافة اللغات السامية", 880),
        IpaConsonant("n", "نون (ن)", "لثوي أنفي مجهور", "Alveolar Nasal", "كافة اللغات السامية", 932),
        IpaConsonant("s", "سين (س)", "لثوي صفيري مهموس", "Voiceless Alveolar Sibilant", "كافة اللغات السامية", 987),
        IpaConsonant("ʕ", "عين (ع)", "حلقي احتكاكي مجهور", "Voiced Pharyngeal Fricative", "أوغاريتية، عربية، سبئية، جعزية", 1046),
        IpaConsonant("p / f", "فاء/پاء (ف)", "شفوي احتكاكي/انفجاري", "Voiceless Labial Plosive/Fricative", "كافة اللغات السامية", 1108),
        IpaConsonant("sˤ", "صاد (ص)", "لثوي صفيري مطبق/مقذوف", "Pharyngealized Alveolar Sibilant", "كافة اللغات السامية", 1174),
        IpaConsonant("q / kʼ", "قاف (ق)", "لهوي انفجاري مطبق/مقذوف", "Voiceless Uvular Plosive", "كافة اللغات السامية", 1244),
        IpaConsonant("r", "راء (ر)", "لثوي تكراري مجهور", "Alveolar Trill", "كافة اللغات السامية", 1318),
        IpaConsonant("ʃ", "شين (ش)", "لثوي غاري احتكاكي", "Voiceless Postalveolar Fricative", "كافة اللغات السامية", 1396),
        IpaConsonant("t", "تاء (ت)", "أسناني انفجاري مهموس", "Voiceless Dental Plosive", "كافة اللغات السامية", 1480),
        IpaConsonant("θ", "ثاء (ث)", "بين أسناني احتكاكي مهموس", "Voiceless Dental Fricative", "عربية، أوغاريتية، سبئية، مهرية", 1567),
        IpaConsonant("ð", "ذال (ذ)", "بين أسناني احتكاكي مجهور", "Voiced Dental Fricative", "عربية، أوغاريتية، سبئية، مهرية", 1661),
        IpaConsonant("ðˤ", "ظاء (ظ)", "بين أسناني مطبق مجهور", "Pharyngealized Voiced Dental Fricative", "عربية، أوغاريتية، سبئية", 1760),
        IpaConsonant("dˤ / ɬˤ", "ضاد (ض)", "جانبي مطبق مجهور (قديم)", "Voiced Alveolar Lateral Fricative", "عربية قديمة، سبئية، مهرية", 1864),
        IpaConsonant("x", "خاء (خ)", "طبقي احتكاكي مهموس", "Voiceless Velar Fricative", "أوغاريتية، عربية، سبئية، جعزية", 1975),
        IpaConsonant("ɣ", "غين (غ)", "طبقي احتكاكي مجهور", "Voiced Velar Fricative", "أوغاريتية، عربية، سبئية", 2093)
    )

    val IPA_CONSONANTS: List<IpaConsonant> = CONSONANT_IPA_CHART
}
