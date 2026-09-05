package com.example.core.data

import com.example.core.model.InscriptionLinguisticBreakdown
import com.example.core.model.InscriptionPhonologicalLaw
import com.example.core.model.InscriptionTokenAnalysis

object InscriptionLinguisticData {

    val BREAKDOWNS_BY_ID: Map<String, InscriptionLinguisticBreakdown> = mapOf(
        "mesha_stele" to InscriptionLinguisticBreakdown(
            inscriptionId = "mesha_stele",
            titleAr = "مسلة الملك ميشع المؤابي",
            scriptNameAr = "الخط الفينيقي/الكنعاني القديم (Moabite Alphabet)",
            writingDirectionAr = "من اليمين إلى اليسار (RTL)",
            wordDividerAr = "نقاط فاصلة بين الكلمات (Dots 𐤟) وخطوط بين الجمل",
            tokens = listOf(
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤀𐤍𐤊",
                    transliteration = "ʾnk",
                    ipa = "/ʔaˈnoːkiː/",
                    meaningAr = "أنا",
                    meaningEn = "I am",
                    grammaticalRoleAr = "ضمير منفصل للمتكلم المفرد في صدر الجملة الاسمية",
                    root = "أ-ن-ك (Proto-Semitic: *ʾanāku)",
                    morphologicalPattern = "صيغة الضمير السامي القديم غير المختزل",
                    cognatesComparison = "الأكادية: anāku | العبرية: ʾānōḵī (אָנֹכִי) | الفينيقية: ʾnk | السبئية: ʾn | العربية: أنا"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤌𐤔𐤏",
                    transliteration = "mšʿ",
                    ipa = "/ˈmeːʃaʕ/",
                    meaningAr = "ميشع (اسم علم للملك المؤابي)",
                    meaningEn = "Mesha (King of Moab)",
                    grammaticalRoleAr = "خبر المبتدأ (اسم علم مذكر مرفوع)",
                    root = "م-ش-ع / ي-ش-ع (الخلاص والإنجاء)",
                    morphologicalPattern = "اسم فاعل أو صفة مشتقة",
                    cognatesComparison = "العبرية: מֵישַׁע (ميشع) وموشيع (المخلّص) | العربية: مسع / يسع (الواسع المنقذ)"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤁𐤍",
                    transliteration = "bn",
                    ipa = "/bin/",
                    meaningAr = "ابن",
                    meaningEn = "son of",
                    grammaticalRoleAr = "بدل أو نعت مضاف في حالة إضافة (Construct State)",
                    root = "ب-ن-ي (Proto-Semitic: *bin-)",
                    morphologicalPattern = "اسم ثلاثي بحذف لامه في الإضافة",
                    cognatesComparison = "العربية: ابن | الفينيقية: بن 𐤁𐤍 | الآرامية: بر 𐡁𐡓 | الأكادية: bīnu / māru"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤊𐤌𐤔𐤉𐤕",
                    transliteration = "kmšyt",
                    ipa = "/kamoːʃˈjat/",
                    meaningAr = "كموشيت (عطية الإله كموش - والد ميشع)",
                    meaningEn = "Chemosh-yat (gift of Chemosh)",
                    grammaticalRoleAr = "مضاف إليه مجرور (علم مركب إضافي ثيوفوري)",
                    root = "ك-م-ش + ي-ت (هبة الإله القومي)",
                    morphologicalPattern = "مركب إضافي ديني ثيوفوري",
                    cognatesComparison = "الأكادية: Kammušu | العبرية: كَموش (כְּמוֹשׁ) إله مؤاب"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤌𐤋𐤊",
                    transliteration = "mlk",
                    ipa = "/ˈmalik/",
                    meaningAr = "ملك",
                    meaningEn = "king of",
                    grammaticalRoleAr = "نعت أو بدل مضاف للمملكة التالية",
                    root = "م-ل-ك (Proto-Semitic: *malku-)",
                    morphologicalPattern = "فَعْل > فَعِل (اسم جنس في حالة بناء)",
                    cognatesComparison = "السامية المشتركة: الأكادية malku | الفينيقية mlk | السبئية mlk | العربية مَلِك"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤌𐤀𐤁",
                    transliteration = "mʾb",
                    ipa = "/moːˈʔaːb/",
                    meaningAr = "مؤاب",
                    meaningEn = "Moab",
                    grammaticalRoleAr = "مضاف إليه ممنوع من الصرف للعلمية والتأنيث المكاني",
                    root = "م-أ-ب (أرض مؤاب شرق البحر الميت)",
                    morphologicalPattern = "اسم إقليم جغرافي سياسي",
                    cognatesComparison = "العبرية: מוֹאָב (مؤاب) | الآشورية: Mu'aba / Ma'aba"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤄𐤃𐤉𐤁𐤍𐤉",
                    transliteration = "h-dybny",
                    ipa = "/had-diːboːˈniː/",
                    meaningAr = "الديباني (نسبة إلى عاصمة ذيبان)",
                    meaningEn = "the Dibonite",
                    grammaticalRoleAr = "نعت معرف بأداة التعريف الهاء (h-)",
                    root = "د-ي-ب-ن + ياء النسبة",
                    morphologicalPattern = "اسم منسوب معرف بالهاء مع تشديد الدال (إدغام تماثلي)",
                    cognatesComparison = "العربية: الذيباني / الديباني | العبرية: הַדִּיבֹנִי"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤔𐤋𐤔𐤍",
                    transliteration = "šlšn",
                    ipa = "/ʃaˈlaːʃiːn/",
                    meaningAr = "ثلاثين",
                    meaningEn = "thirty",
                    grammaticalRoleAr = "تمييز عدد / ظرف زمان منصوب بالنون",
                    root = "ث-ل-ث (Proto-Semitic: *ṯalāṯ-)",
                    morphologicalPattern = "عقد عددي ينتهي بلاحقة الجمع المؤابية (-īn بالنون)",
                    cognatesComparison = "المؤابية والآرامية: تنتهي بـ (-īn) | الفينيقية والعبرية: تنتهي بـ (-īm بالميم) | العربية: ثلاثين"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤔𐤕",
                    transliteration = "št",
                    ipa = "/ʃat/",
                    meaningAr = "سنة / عام",
                    meaningEn = "year",
                    grammaticalRoleAr = "تمييز منصوب مفرد مؤنث",
                    root = "ش-ن-ت (Proto-Semitic: *šanat- مع سقوط النون وإدغامها)",
                    morphologicalPattern = "فَعْلَة مع سقوط النون: *šant- > št",
                    cognatesComparison = "الفينيقية: št | العبرية: שָׁנָה (شاناه) | الأكادية: šattu | العربية: سَنَة"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤅𐤀𐤏𐤫",
                    transliteration = "w-ʾʿś",
                    ipa = "/wa-ˈʔaʕaś/",
                    meaningAr = "وصنعتُ / وعملتُ",
                    meaningEn = "and I made",
                    grammaticalRoleAr = "واو العطف السردية التتابعية (Waw Consecutive) + فعل ناقص مجزوم",
                    root = "ع-ش-ي (صنع / عمل)",
                    morphologicalPattern = "فعل مضارع معطوف بواو التتابع السردي المنقلب للمضي",
                    cognatesComparison = "العبرية القديمة: וָאַעַשׂ (فاأعَس) | العربية: عسى / صنع"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤄𐤁𐤌𐤕",
                    transliteration = "h-bmt",
                    ipa = "/hab-baːˈmoːt/",
                    meaningAr = "المرتفعة / المذبح الشعائري العالي",
                    meaningEn = "the high place (sanctuary)",
                    grammaticalRoleAr = "مفعول به منصوب معرف بأداة التعريف الهاء",
                    root = "ب-م-ت (Proto-Semitic: *bamat- المرتفع)",
                    morphologicalPattern = "اسم مؤنث بالتاء الصريحة مع تحول الصائت ā > ō",
                    cognatesComparison = "العبرية: בָּמָה (باماه) | الأكادية: bamtu (الظهر/المرتفع) | الأوغاريتية: bmt"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤆𐤀𐤕",
                    transliteration = "zʾt",
                    ipa = "/zoːʔt/",
                    meaningAr = "هذه",
                    meaningEn = "this (fem.)",
                    grammaticalRoleAr = "اسم إشارة للمؤنث القريب نعت للمرتفعة",
                    root = "ذ-هـ / ز-أ (Proto-Semitic demonstrative)",
                    morphologicalPattern = "مؤنث اسم الإشارة الكنعاني القديم",
                    cognatesComparison = "الفينيقية: zʾt / zt | العبرية: זֹאת (زوت) | العربية: ذات / ذي"
                )
            ),
            phonologicalFeatures = listOf(
                InscriptionPhonologicalLaw(
                    ruleTitleAr = "التحول الكنعاني للصوائت (Canaanite Shift)",
                    formula = "Proto-Semitic *ā > Canaanite /ō/",
                    explanationAr = "تحول الصائت الممدود المفتوح النبري (*ā) إلى صائت خلفي مضموم طويل (/ō/)، مثل: *ʾanāku > ʾanōkī (𐤀𐤍𐤊)، و *bāmat > bōmat (𐤄𐤁𐤌𐤕).",
                    inTextExamples = listOf("𐤀𐤍𐤊 (ʾnk = ʾanōkī)", "𐤄𐤁𐤌𐤕 (h-bmt = hab-bōmat)", "𐤆𐤀𐤕 (zʾt = zōʾt)")
                ),
                InscriptionPhonologicalLaw(
                    ruleTitleAr = "سقوط النون الساكنة والإدغام التماثلي",
                    formula = "*nC > CC (Assimilation of Nun)",
                    explanationAr = "سقوط النون الساكنة عندما تليها صامتة أخرى وإدغامها تماثلياً مع تشديد الحرف اللاحق، كما في كلمة سنة (*šanat- > št 𐤔𐤕).",
                    inTextExamples = listOf("𐤔𐤕 (št من أصل *šanat-)", "𐤄𐤃𐤉𐤁𐤍𐤉 (had-d- من أصل *han-)")
                ),
                InscriptionPhonologicalLaw(
                    ruleTitleAr = "لاحقة جمع المذكر السالم بالنون (Nunation Plural)",
                    formula = "Plural Marker: -īn (not -īm)",
                    explanationAr = "تتميز المؤابية بالاحتفاظ بجمع المذكر السالم المنتهي بالنون (-īn) مثل الآرامية والعربية، مخالفةً للفينيقية والعبرية التي تنتهي بالميم (-īm).",
                    inTextExamples = listOf("𐤔𐤋𐤔𐤍 (šlšn = šalāšīn)", "𐤌𐤋𐤊𐤍 (mlkn = malkīn)")
                )
            ),
            morphologicalFeatures = listOf(
                "أداة التعريف بالهاء (h-): تلحق بالأسماء وتُدغم في الصامت التالي مع تشديده (h-dybny، h-bmt).",
                "صيغة الفعل المعتل الناقص: استخدام صيغة المتبوع بالواو السردية (w-ʾʿś) بحذف حرف العلة في حالة الجزم.",
                "بناء المطاوعة بالتاء المقحمة (t-stem): ورود صيغة (w-ʾltḥm = وحاربتُ) المقابلة لـ (افتعل) العربية.",
                "ضمير الغائب المتصل بالهاء (-h): ينطق صائتاً طويلاً /ōh/ (مثل qrḥh = قرحته)."
            ),
            syntacticFeatures = listOf(
                "واو العطف السردية التتابعية (Waw Consecutive / Inverted): تعطف المضارع على الماضي لتقلب دلالته الزمنية إلى سرد ماضٍ مستمر (w-ʾʿś = وصنعتُ، w-ʾmrx = وقلتُ).",
                "الجملة الاسمية الصدرية المؤكدة: افتتاح السجلات الملكية بالضمير المنفصل المتبوع بالاسم الملكي واللقب (ʾnk mšʿ... mlk mʾb).",
                "الإضافة الصريحة والإسناد المركب: توالي الإضافات (ʾnk mšʿ bn kmšyt mlk mʾb h-dybny)."
            ),
            epigraphicPaleographicNotes = listOf(
                "نوع الخط: الأبجدية الفينيقية الكنعانية في مرحلتها الانتقالية المبكرة للقرن التاسع قبل الميلاد.",
                "اتجاه الكتابة: من اليمين إلى اليسار أفقياً دون انقطاع، باستثناء الفواصل النقطية.",
                "علامات الترقيم: استخدام النقاط الدائرية الصغيرة (𐤟) للفصل بين الكلمات الفردية، واستخدام خطوط عمودية قصيرة للفصل بين العبارات التامة.",
                "الخامة الحجرية: نقش غائر عالي الإتقان على حجر البازلت البركاني الأسود المصقول بأبعاد 124 سم × 71 سم."
            ),
            comparativeSemiticInsights = listOf(
                "المؤابية تشكل حلقة وصل فيلولوجية نادرة تجمع بين معجم كنعاني صرف ونظام صوائت غربي، مع منظومة جمع آرامية-عربية (-īn).",
                "تطابق شبه تام مع تراكيب العبرية الإبيغرافية المبكرة (نقش سلوان ونقوش عراد).",
                "ورود أسماء آلهة مشتركة وذكر 'يهوه' كأقدم شاهد أثري خارجي غير توراتي."
            ),
            historicalSignificanceAr = "تعد مسلة ميشع أهم وثيقة إبيغرافية وتاريخية عُثر عليها في بلاد الشام لتاريخ القرن التاسع ق.م؛ إذ تروي انتفاضة الملك ميشع المؤابي ضد أسرة عمري ملك إسرائيل واستعادة استقلال مؤاب وبناء المدن والتحصينات وخزانات المياه."
        ),

        "ahiram_sarcophagus" to InscriptionLinguisticBreakdown(
            inscriptionId = "ahiram_sarcophagus",
            titleAr = "نقش تابوت الملك أحيرام بجبيل",
            scriptNameAr = "الخط الفينيقي الجبيلي الكلاسيكي (Old Byblian Phoenician)",
            writingDirectionAr = "من اليمين إلى اليسار (RTL)",
            wordDividerAr = "نقاط فاصلة قصيرة تفصل بين الكلمات",
            tokens = listOf(
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤀𐤓𐤍",
                    transliteration = "ʾrn",
                    ipa = "/ʔaˈroːn/",
                    meaningAr = "تابوت / ناووس حجري",
                    meaningEn = "sarcophagus / chest",
                    grammaticalRoleAr = "مبتدأ أو مفعول به مقدم في صدر العبارة",
                    root = "أ-ر-ن (صندوق / تابوت)",
                    morphologicalPattern = "فَعُول / فَعْل مع التحول الكنعاني",
                    cognatesComparison = "العبرية: אָרוֹן (ʾārōn) | الأوغاريتية: ʾrn | العربية: أَرَنَ (الوعاء والحظيرة)"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤆",
                    transliteration = "z",
                    ipa = "/zeː/",
                    meaningAr = "هذا",
                    meaningEn = "this",
                    grammaticalRoleAr = "اسم إشارة للمفرد المذكر نعت لتابوت (فينيقية جبيل)",
                    root = "ذ- / ز- (Proto-Semitic: *ḏū)",
                    morphologicalPattern = "اسم إشارة جبيلي عتيق (z بدلاً من zʾt)",
                    cognatesComparison = "العربية: ذو / ذا | العبرية: זֶה (زِه) | الآرامية: דְּנָה (دنا) | الأكادية: šū"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤐𐤏𐤋",
                    transliteration = "pʿl",
                    ipa = "/paˈʕala/",
                    meaningAr = "صَنَعَ / فَعَلَ",
                    meaningEn = "made / fashioned",
                    grammaticalRoleAr = "فعل ماضٍ ثلاثي مجرد مبني للمعلوم",
                    root = "ف-ع-ل (Proto-Semitic: *paʿala)",
                    morphologicalPattern = "فَعَلَ (الفعل المعياري للصنع في الكنعانية)",
                    cognatesComparison = "العربية: فَعَلَ | العبرية: פָּעַל (بااعل) | الفينيقية: pʿl (تستعمل كمرادف لـ صنع)"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤀𐤕𐤁𐤏𐤋",
                    transliteration = "ʾtbʿl",
                    ipa = "/ʔittoːˈbaʕal/",
                    meaningAr = "إيتوبعل (مع الإله بعل - ملك جبيل)",
                    meaningEn = "Ittobaal (With Baal)",
                    grammaticalRoleAr = "فاعل مرفوع (اسم علم مركب ثيوفوري)",
                    root = "أ-ت-ي (مع) + ب-ع-ل",
                    morphologicalPattern = "مركب ديني ثيوفوري كنعاني شهير",
                    cognatesComparison = "اليونانية: Ἰθώβαλος (إيثوبالوس) | العبرية: אֶתְבַּעַל"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤁𐤍",
                    transliteration = "bn",
                    ipa = "/bin/",
                    meaningAr = "ابن",
                    meaningEn = "son of",
                    grammaticalRoleAr = "بدل مضاف للملك أحيرام",
                    root = "ب-ن-ي",
                    morphologicalPattern = "اسم ثلاثي بحذف اللام",
                    cognatesComparison = "الفينيقية: بن | العربية: ابن | الآرامية: بر"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤀𐤇𐤓𐤌",
                    transliteration = "ʾḥrm",
                    ipa = "/ʔaħiːˈraːm/",
                    meaningAr = "أحيرام (أخي رفيع / سامٍ)",
                    meaningEn = "Ahiram (My brother is exalted)",
                    grammaticalRoleAr = "مضاف إليه مجرور (اسم الملك المتوفى)",
                    root = "أ-خ-و + ر-و-م",
                    morphologicalPattern = "مركب وصفي: أخي + رام (مرتفع)",
                    cognatesComparison = "العبرية: אֲחִירָם (أحيرام) | العربية: أخي رام"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤂𐤁𐤋",
                    transliteration = "gbl",
                    ipa = "/ˈgubal/",
                    meaningAr = "جبيل (بيبلوس)",
                    meaningEn = "Byblos / Gubla",
                    grammaticalRoleAr = "مضاف إليه مجرور لمملكة جبيل",
                    root = "ج-ب-ل (الجبل / التخم / الحصن)",
                    morphologicalPattern = "اسم علم جغرافي للمدينة الفينيقية الأم",
                    cognatesComparison = "الأكادية: Gubla (رسائل تل العمارنة) | العربية: جَبَل / جُبيل"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤀𐤁𐤄",
                    transliteration = "ʾbh",
                    ipa = "/ʔaˈbiːhu/",
                    meaningAr = "أباه",
                    meaningEn = "his father",
                    grammaticalRoleAr = "بدل أو عطف بيان منصوب بفتحة مقدرة وهو مضاف للهاء",
                    root = "أ-ب-و",
                    morphologicalPattern = "اسم من الأسماء الخمسة مع هاء الغائب",
                    cognatesComparison = "العربية: أباه / أبوه | العبرية: אָבִיו | الأكادية: abūšu"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤊𐤔𐤕𐤄",
                    transliteration = "k-šth",
                    ipa = "/kiː-ʃiːˈtoːh/",
                    meaningAr = "حين وضعه / أنزله في القبر",
                    meaningEn = "when he placed him",
                    grammaticalRoleAr = "كاف الظرفية الزمانية + فعل ماضٍ مجرد + هاء الغائب المفعولية",
                    root = "ش-ي-ت (وضع / جعل)",
                    morphologicalPattern = "فعل أجوف ثلاثي كنعاني (šyt = وضع)",
                    cognatesComparison = "العبرية: שָׁת (شات = وضع) | الأوغاريتية: šyt | العربية: شاتَ"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐤁𐤏𐤋𐤌",
                    transliteration = "b-ʿlm",
                    ipa = "/bi-ʕoːˈlaːm/",
                    meaningAr = "في الأبدية / دار الخلود",
                    meaningEn = "in eternity (the everlasting abode)",
                    grammaticalRoleAr = "شبه جملة جار ومجرور في محل نصب حال",
                    root = "ع-ل-م (Proto-Semitic: *ʿalam- الدهر والأبد)",
                    morphologicalPattern = "فَعَال مع التحول الكنعاني للصائت الأول",
                    cognatesComparison = "العربية: في العَالَم / في الأبد | العبرية: עוֹלָם (عولام) | الفينيقية: ʿlm (دار الخلود)"
                )
            ),
            phonologicalFeatures = listOf(
                InscriptionPhonologicalLaw(
                    ruleTitleAr = "الأبجدية الفينيقية القياسية (22 صامتاً)",
                    formula = "29 Proto-Semitic consonants > 22 Phoenician letters",
                    explanationAr = "يمثل نقش أحيرام أقدم نموذج مكتمل للأبجدية الفينيقية البالغة 22 حرفاً؛ حيث اندمجت الصوامت (الثاء مع الشين، الذال مع الزاي، والظاء مع الصاد، والغين مع العين، والخاء مع الحاء).",
                    inTextExamples = listOf("𐤆 (z < *ḏ)", "𐤔𐤕𐤄 (št < *šyt)", "𐤏𐤋𐤌 (ʿlm)")
                ),
                InscriptionPhonologicalLaw(
                    ruleTitleAr = "غياب أداة التعريف بالهاء (Pre-article Stage)",
                    formula = "Zero Article Pattern",
                    explanationAr = "يخلو نص أحيرام من أداة التعريف بالهاء (h-)؛ إذ كُتب (ʾrn z = التابوت هذا) مجرداً، مما يؤكد أصالته في مرحلة سابقة لظهور أداة التعريف اللاحقة في الكنعانية الكلاسيكية.",
                    inTextExamples = listOf("𐤀𐤓𐤍 𐤆 (ʾrn z بدون h-)", "𐤌𐤋𐤊 𐤂𐤁𐤋")
                )
            ),
            morphologicalFeatures = listOf(
                "اسم الإشارة الجبيلي القديم (z): استخدام (z) بدلاً من (zn) أو (zʾt) الشائعة لاحقاً.",
                "هاء الغائب المتصلة: ورود هاء الغائب متصلة بالفعل والاسم (-h) بلفظ /hu/ أو /ōh/ (ʾbh, k-šth).",
                "أفعال الصنع الكنعانية: استخدام الفعل (pʿl = فعل) كفعل دال على الصناعة والبناء الفني."
            ),
            syntacticFeatures = listOf(
                "جملة اللعنة التحذيرية المركبة: تركيب شرطي جزائي شهير (w-ʾl mlk b-mlkm... w-ygl ʾrn zn = وإن أتى ملك وفتح هذا التابوت...) لحماية المقبرة الملكية.",
                "بناء الجملة الافتتاحية: اسمية تقدم المفعول ثم الفعل والفاعل الفينيقي."
            ),
            epigraphicPaleographicNotes = listOf(
                "الموضع الأثري: منقوش على الحافة العليا لحوض التابوت الحجري، وعلى الغطاء المنحوت ببراعة.",
                "الخط: أقدم نص فينيقي خطي كلاسيكي ناضج مؤرخ بنحو 1000 ق.م ومكتشف بالمقبرة الملكية بجبيل.",
                "الزخارف المصاحبة: محاط بنقوش ناعيات وأسود رابضة تمثل قمة النحت الفينيقي."
            ),
            comparativeSemiticInsights = listOf(
                "فينيقية جبيل تحتفظ بخصائص لهجية تميزها عن فينيقية صور وصيدا وعن البونية القرطاجية.",
                "التطابق المباشر بين مصطلحات التابوت ودار الخلود (b-ʿlm) مع النقوش الكنعانية الأخرى في كاراتبه وأوجاريت."
            ),
            historicalSignificanceAr = "يعد تابوت أحيرام حجر الزاوية في دراسة نشأة الأبجدية الخطية وانتشارها في حوض البحر المتوسط، وهو المصدر الأساسي لتأريخ النقوش الكنعانية الملكية في الألف الأول قبل الميلاد."
        ),

        "hammurabi_stele" to InscriptionLinguisticBreakdown(
            inscriptionId = "hammurabi_stele",
            titleAr = "مسلة شريعة حمورابي البابلية",
            scriptNameAr = "الكتابة المسمارية الأكادية البابلية (Old Babylonian Cuneiform)",
            writingDirectionAr = "من اليسار إلى اليمين أفقياً (وفي أعمدة رأسية أصلاً)",
            wordDividerAr = "فواصل مقطعية مسمارية وقيم صوتية مقطعية ولوغوغرامات (Sumerograms)",
            tokens = listOf(
                InscriptionTokenAnalysis(
                    tokenOriginal = "𒄿 𒉡 𒈠",
                    transliteration = "i-nu-ma (inūma)",
                    ipa = "/iˈnuːma/",
                    meaningAr = "حينما / إبّان / عندئذٍ",
                    meaningEn = "when / at that time",
                    grammaticalRoleAr = "أداة ظرفية زمانية رابطة لافتتاح الملاحم والشرائع",
                    root = "أ-ن-م (الظرفية الزمانية الأكادية)",
                    morphologicalPattern = "ظرف زمان مبني مع لاحقة التوكيد -ma",
                    cognatesComparison = "الأكادية: inūma / inu | العربية: حينما / إبان | العبرية: אָז"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𒀭 𒉡 𒌝",
                    transliteration = "Anum (AN-nu-um)",
                    ipa = "/ˈʔanum/",
                    meaningAr = "الإله آنو (إله السماء الأكبر)",
                    meaningEn = "Anu (Sky god)",
                    grammaticalRoleAr = "فاعل مرفوع بضمة الميمية (-um)",
                    root = "سومروغرام AN (السماء / الألوهية)",
                    morphologicalPattern = "اسم علم إلهي معرب بالميمية الأكادية (Mimation)",
                    cognatesComparison = "السومرية: An | الأكادية: Anum / Anī / Anam"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𒍢 𒊒 𒌝",
                    transliteration = "ṣīrum (ṣi-ru-um)",
                    ipa = "/ˈsˤiːrum/",
                    meaningAr = "العليّ / السامي / الرفيع",
                    meaningEn = "the exalted / supreme",
                    grammaticalRoleAr = "نعت مرفوع للإله آنو بالميمية (-um)",
                    root = "ص-ي-ر / ص-و-ر (Proto-Semitic: *ṣyr العلو)",
                    morphologicalPattern = "فَعِيل مع الميمية المرفوعة",
                    cognatesComparison = "الأكادية: ṣīru(m) | العربية: صَيِّر / الصائر | العبرية: צִיר"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𒈗",
                    transliteration = "šar (LUGAL)",
                    ipa = "/ʃar/",
                    meaningAr = "مَلِك",
                    meaningEn = "king of",
                    grammaticalRoleAr = "بدل أو نعت في حالة إضافة مقطوعة (Status Constructus)",
                    root = "ش-ر-ر (السيادة والأمر)",
                    morphologicalPattern = "سومروغرام LUGAL = šarru(m) بحذف الميمية للإضافة",
                    cognatesComparison = "الأكادية: šarru(m) | العبرية: שַׂר (سار = أمير/قائد) | العربية: شَرّفَ / شار"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𒀭 𒀀 𒉣 𒈾 𒆠",
                    transliteration = "Anunnakī (AN.A.NUN.NA.KI)",
                    ipa = "/ʔanunnakkiː/",
                    meaningAr = "الأنوناكي (مجمع آلهة السماء والأرض البابلية)",
                    meaningEn = "the Anunnaki gods",
                    grammaticalRoleAr = "مضاف إليه مجرور",
                    root = "أصل سومري ديني مركب",
                    morphologicalPattern = "جمع أعلام آلهة ميثولوجية",
                    cognatesComparison = "الأساطير البابلية والآشورية والسومرية"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𒂗 𒆤",
                    transliteration = "Enlil (EN.LÍL)",
                    ipa = "/ˈʔenlil/",
                    meaningAr = "الإله إنليل (رب الهواء والرياح وسيد الأرض)",
                    meaningEn = "Enlil (Lord of air/earth)",
                    grammaticalRoleAr = "معطوف مرفوع على آنو",
                    root = "سومروغرام EN (سيد) + LÍL (ريح)",
                    morphologicalPattern = "مركب سومري ديني مقدس",
                    cognatesComparison = "البانثيون الرافديني القديم"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𒁁 𒂖",
                    transliteration = "bēl (be-el)",
                    ipa = "/beːl/",
                    meaningAr = "رَبّ / سَيّد",
                    meaningEn = "lord of",
                    grammaticalRoleAr = "حالة إضافة للقب الإله إنليل",
                    root = "ب-ع-ل (Proto-Semitic: *baʿlu-)",
                    morphologicalPattern = "انكماش الصامت الحلقي: *baʿlu > bēlu(m) وحالة الإضافة bēl",
                    cognatesComparison = "الكنعانية: baʿal (بعل) | العربية: بَعْل | الأكادية: bēlu / bēlet (سيدة)"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𒊭 𒈨 𒂊",
                    transliteration = "šamê (ša-me-e)",
                    ipa = "/ʃaˈmeː/",
                    meaningAr = "السماء / السماوات",
                    meaningEn = "heavens",
                    grammaticalRoleAr = "مضاف إليه مجرور بالياء/الصائت الممال",
                    root = "س-م-و / ش-م-ي (Proto-Semitic: *šamāy-)",
                    morphologicalPattern = "جمع مجرور بانكماش الياء: *šamāyī > šamê",
                    cognatesComparison = "العربية: سماء / سماوات | العبرية: שָׁמַיִם (شمايم) | الأوغاريتية: šmym"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𒅇",
                    transliteration = "u",
                    ipa = "/ʔu/",
                    meaningAr = "وَ (حرف عطف)",
                    meaningEn = "and",
                    grammaticalRoleAr = "حرف عطف بين المعطوفات",
                    root = "حرف العطف السامي الأكادي",
                    morphologicalPattern = "حرف عطف أحادي مقطعي",
                    cognatesComparison = "العربية: و (wa) | العبرية: וְ (və) | الفينيقية: w-"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𒅕 𒍢 𒁴",
                    transliteration = "erṣetim (er-ṣe-tim)",
                    ipa = "/ʔersˤeˈtim/",
                    meaningAr = "الأرض",
                    meaningEn = "earth",
                    grammaticalRoleAr = "معطوف مجرور بكسرة الميمية (-im)",
                    root = "أ-ر-ص (Proto-Semitic: *ʾarṣˤ-)",
                    morphologicalPattern = "مؤنث بالتاء مع الميمية المجرورة (-tim)",
                    cognatesComparison = "العربية: أرض | العبرية: אֶרֶץ (إيرتس) | الآرامية: ʾarʿā | الأوغاريتية: ʾarṣ"
                )
            ),
            phonologicalFeatures = listOf(
                InscriptionPhonologicalLaw(
                    ruleTitleAr = "قانون الميمية الأكادية (Akkadian Mimation)",
                    formula = "Nominative: -um | Accusative: -am | Genitive: -im",
                    explanationAr = "التنوين بالميم في أواخر الأسماء المعربة المفردة (على عكس التنوين بالنون في العربية)، وتسقط الميمية وجوباً في حالة الإضافة (Status Constructus).",
                    inTextExamples = listOf("Anum (-um رفع)", "ṣīrum (-um رفع)", "erṣetim (-im جر)", "šar (سقطت الميمية للإضافة)")
                ),
                InscriptionPhonologicalLaw(
                    ruleTitleAr = "تحول الحلقيات والحنجريات إلى صوائت ممدودة (e/i)",
                    formula = "*a + Guttural > e / ē",
                    explanationAr = "فقدت الأكادية الصوامت الحلقية السامية (ع، ح، هـ، غ) وتحولت الصوائت المجاورة لها من /a/ إلى /e/، كما في: *baʿlu- > bēlu-، و *ʾarṣ- > erṣetu-.",
                    inTextExamples = listOf("bēl (من أصل *baʿl-)", "erṣetim (من أصل *ʾarṣ-)")
                )
            ),
            morphologicalFeatures = listOf(
                "نظام الجذوع والفعل الأكادي: صيغ (Present: iparras)، (Preterite: iprus)، وصيغة التمام (Perfect: iptaras).",
                "صيغة الشرط القانوني الشهيرة: افتتاح المواد القانونية بـ (šumma awīlum... = إذا ارتكب إنسان...)."
            ),
            syntacticFeatures = listOf(
                "ترتيب عناصر الجملة الأكادي (SOV): الفاعل ثم المفعول به، ويأتي الفعل دائماً في نهاية الجملة بتأثير من الطبقة التحتية السومرية.",
                "حالة الإضافة المبتورة: تحذف الميمية والحركات الإعرابية عند الإضافة المباشرة."
            ),
            epigraphicPaleographicNotes = listOf(
                "الرمزية المسمارية: استخدام الخط المسماري الكلاسيكي البابلي القديم المنقوش على حجر الديوريت الأسود المصقول بارتفاع 2.25 م.",
                "اللوحة العلوية: مشهد بارز يصور الملك حمورابي واقفاً بخشوع يتلقى عصا القيادة وميزان العدالة من إله الشمس والعدل شمش."
            ),
            comparativeSemiticInsights = listOf(
                "تمثل المسلة قمة تطور الفرع السامي الشرقي (East Semitic).",
                "التطابق الصوتي المورفولوجي للجذور الرافدينية مع العربية وشقيقاتها في بلاد الشام وجنوب الجزيرة."
            ),
            historicalSignificanceAr = "تعد شريعة حمورابي أقدم وأشمل مدونة تشريعية قانونية متكاملة في التاريخ الإنساني تضم 282 مادة قانونية تعالج قضايا القضاء، الملكية، التجارة، الأسرة، الجنايات، وحقوق العمل."
        ),

        "marib_dam_inscription" to InscriptionLinguisticBreakdown(
            inscriptionId = "marib_dam_inscription",
            titleAr = "نقش سد مأرب العظيم (شرحبيل يعفر)",
            scriptNameAr = "خط المسند العربي الجنوبي البارز (Ancient South Arabian Musnad)",
            writingDirectionAr = "من اليمين إلى اليسار (وفي نصوص أخرى بوستروفيدون المحراثي)",
            wordDividerAr = "خط عمودي فاصل بين الكلمات (Vertical Bar faṣl)",
            tokens = listOf(
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩩𐩨𐩧𐩫",
                    transliteration = "tbrk",
                    ipa = "/tabaːraka/",
                    meaningAr = "تَبَارَكَ",
                    meaningEn = "blessed be",
                    grammaticalRoleAr = "فعل ماضٍ على وزن تفاعل مبني على الفتح للثناء والتمجيد",
                    root = "ب-ر-ك (Proto-Semitic: *baraka)",
                    morphologicalPattern = "تَفَاعَلَ (وزن المطاوعة والتعظيم السامي)",
                    cognatesComparison = "العربية: تَبَارَكَ | العبرية: הִתְבָּרֵךְ | السريانية: etbārak"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩥𐩩𐩲𐩡𐩺",
                    transliteration = "w-tʿly",
                    ipa = "/wa-taʕaːlaː/",
                    meaningAr = "وَتَعَالَى",
                    meaningEn = "and exalted be",
                    grammaticalRoleAr = "واو العطف + فعل ماضٍ معتل اللام على وزن تفاعل",
                    root = "ع-ل-و (Proto-Semitic: *ʿalawa)",
                    morphologicalPattern = "تَفَاعَلَ (معتل الآخر)",
                    cognatesComparison = "العربية: وتعالى | السبئية: tʿly | الجعزية: ተዐለየ"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩪𐩣",
                    transliteration = "sm",
                    ipa = "/sim/",
                    meaningAr = "اسم",
                    meaningEn = "name of",
                    grammaticalRoleAr = "فاعل مرفوع وهو مضاف لرحمنان",
                    root = "س-م-و (Proto-Semitic: *šim- / *sim-)",
                    morphologicalPattern = "اسم ثنائي الأصل في حالة بناء وإضافة",
                    cognatesComparison = "السبئية: sm (بالسين الأولى) | العربية: اسم | الأكادية: šumu | العبرية: שֵׁם (شيم)"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩧𐩢𐩣𐩬𐩬",
                    transliteration = "rḥmn-n",
                    ipa = "/raħmaːˈnaːn/",
                    meaningAr = "الرَّحْمَن (رحمنان مع أداة التعريف بالنون)",
                    meaningEn = "the Merciful (Rahmanan)",
                    grammaticalRoleAr = "مضاف إليه مجرور معرف بنون التعريف اللاحقة المسندية",
                    root = "ر-ح-م (الرحمة والإحسان)",
                    morphologicalPattern = "فَعْلَان مع نون التعريف اللاحقة المسندية (-n)",
                    cognatesComparison = "العربية: الرَّحْمَن | الآرامية: רַחֲמָנָא (رحمانا) | العبرية: هَرَحَمَان"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩧𐩨",
                    transliteration = "rb",
                    ipa = "/rabb/",
                    meaningAr = "رَبّ / سَيّد",
                    meaningEn = "lord of",
                    grammaticalRoleAr = "بدل أو نعت مضاف في حالة إضافة",
                    root = "ر-ب-ب (السيادة والتربية)",
                    morphologicalPattern = "فَعْل مضعف",
                    cognatesComparison = "السامية المشتركة: العربية رَبّ | السبئية rb | السريانية rabō"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩪𐩣𐩺𐩬",
                    transliteration = "smy-n",
                    ipa = "/samaj-aːn/",
                    meaningAr = "السَّمَاء (معرفة بالنون اللاحقة)",
                    meaningEn = "the heaven",
                    grammaticalRoleAr = "مضاف إليه مجرور بنون التعريف اللاحقة",
                    root = "س-م-و",
                    morphologicalPattern = "اسم جمع/مفرد مع نون التعريف",
                    cognatesComparison = "العربية: السماء | السبئية: smyn | الجعزية: samāyāt"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩥𐩱𐩧𐩳𐩬",
                    transliteration = "w-ʾrḍ-n",
                    ipa = "/wa-ʔardˤ-aːn/",
                    meaningAr = "وَالأَرْض (معرفة بالنون اللاحقة والضاد المسندية)",
                    meaningEn = "and the earth",
                    grammaticalRoleAr = "واو العطف + معطوف مجرور بأداة التعريف النون",
                    root = "أ-ر-ض (Proto-Semitic: *ʾarṣˤ- مع صامت الضاد الجانبية)",
                    morphologicalPattern = "فَعْل مع النون اللاحقة والصامت 𐩳",
                    cognatesComparison = "العربية: الأرض | السبئية: ʾrḍn | الأكادية: erṣetu | العبرية: אֶרֶץ"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩥𐩲𐩧𐩣𐩬",
                    transliteration = "w-ʿrm-n",
                    ipa = "/wa-ʕarim-aːn/",
                    meaningAr = "وَالعَرِم (سد مأرب العظيم المذكور في القرآن)",
                    meaningEn = "and the Great Dam (al-Arim)",
                    grammaticalRoleAr = "معطوف منصوب مفعول به للبناء معرف بالنون اللاحقة",
                    root = "ع-ر-م (السد الركامي وحبس المياه)",
                    morphologicalPattern = "فَعِل مع نون التعريف اللاحقة",
                    cognatesComparison = "القرآن الكريم: (فَأَرْسَلْنَا عَلَيْهِمْ سَيْلَ الْعَرِمِ) | السبئية: ʿrmn"
                )
            ),
            phonologicalFeatures = listOf(
                InscriptionPhonologicalLaw(
                    ruleTitleAr = "أداة التعريف المسندية بالنون اللاحقة (Postpositive Nunation)",
                    formula = "Definite Article: -n / -ān appended to noun stem",
                    explanationAr = "تنفرد العربية الجنوبية القديمة (المسندية) بتعريف الأسماء بإلحاق نون مشددة أو ممدودة في آخر الاسم (rḥmnn, smyn, ʾrḍn, ʿrmn)، بخلاف أداة التعريف باللام في الفصحى وبالهاء في الكنعانية.",
                    inTextExamples = listOf("𐩧𐩢𐩣𐩬𐩬 (rḥmn-n)", "𐩪𐩣𐩺𐩬 (smy-n)", "𐩥𐩱𐩧𐩳𐩬 (w-ʾrḍ-n)")
                ),
                InscriptionPhonologicalLaw(
                    ruleTitleAr = "المحافظة الكاملة على الصوامت السامية البدائية الـ 29",
                    formula = "Complete retention of Proto-Semitic Consonants",
                    explanationAr = "احتفظت السبئية والمسندية بكامل أصوات السامية الأم، بما في ذلك السينات الثلاث (s1, s2, s3)، والضاد الجانبية الاحتكاكية المطبقة (𐩳)، والظاء (8)، والغين والخاء.",
                    inTextExamples = listOf("𐩳 (ḍad lateral)", "𐩪 (s1)", "𐩢 (ḥa)")
                )
            ),
            morphologicalFeatures = listOf(
                "ضمير الغائب المنفصل والمتصل بالهاء (hwʾ / -hw).",
                "أوزان الأفعال المسندية: التوسع في صيغ التضعيف والمطاوعة بالألف والتاء (tbrk, tʿly).",
                "حروف العطف المسندية: استخدام الواو (w-) والفاء (f-) الرابطة للتعقيب والسببية."
            ),
            syntacticFeatures = listOf(
                "التراكيب التوحيدية: صيغة 'تبارك وتعالى اسم رحمنان رب السماء والأرض' تؤرخ لدخول اليمن عصر التوحيد الحميري-السبئي قبل الإسلام.",
                "الصيغ التوثيقية الهندسية: ذكر تفاصيل الترميم، أطوال السد، النفقات، ومصارف المياه بالأمتار والمكاييل."
            ),
            epigraphicPaleographicNotes = listOf(
                "نوع الخط: خط المسند السبئي البارز (Monumental Raised Relief Musnad) المنحوت على واجهات أحجار السد الملساء.",
                "الفواصل: استخدام الخط الرأسي الفاصل (faṣl) بين كل كلمة وأخرى بدقة هندسية متناهية."
            ),
            comparativeSemiticInsights = listOf(
                "شاهد تاريخي ولغوي فريد على صحة وإعجاز الوصف القرآني لسد مأرب بلفظ 'العَرِم' (ʿrmn في النقش السبئي).",
                "وثيقة تثبت انتشار عبادة الإله 'الرحمن' في جنوب الجزيرة العربية قبل بعثة النبي محمد صلى الله عليه وسلم بعدة قرون."
            ),
            historicalSignificanceAr = "يوثق هذا النقش الجداري الضخم أعمال الصيانة والترميم الشاملة التي أمر بها الملك الحميري شرحبيل يعفر لسد مأرب بعد تصدعه في منتصف القرن الخامس الميلادي، ويعد درة الإبيغرافيا اليمنية الصيهدية."
        ),
        "ugaritic_baal_epic" to InscriptionLinguisticBreakdown(
            inscriptionId = "ugaritic_baal_epic",
            titleAr = "لوح ملحمة بعل المسماري الأوغاريتي (KTU 1.2)",
            scriptNameAr = "الأبجدية الأوغاريتية المسمارية (30 علامة إسفينية مسمارية)",
            writingDirectionAr = "من اليسار إلى اليمين (LTR)",
            wordDividerAr = "إسفين مسماري رأسي صغير فاصل بين الكلمات (𐎟)",
            tokens = listOf(
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐎎𐎍𐎋",
                    transliteration = "mlk",
                    ipa = "/ˈmalku/",
                    meaningAr = "مَلِك / هو الملك",
                    meaningEn = "king / is king",
                    grammaticalRoleAr = "خبر مقدم أو صفة مشبهة مرفوعة بالضمة المقدرة",
                    root = "م-ل-ك (Proto-Semitic: *malk-)",
                    morphologicalPattern = "وزن فَعْل (faʿl-)",
                    cognatesComparison = "العربية: مَلِك | العبرية: مَلِخ (מֶלֶךְ) | الأكادية: malku / šarru | الفينيقية: 𐤌𐤋𐤊"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐎓𐎍𐎎",
                    transliteration = "ʿlm",
                    ipa = "/ʕaːlaˈmiː/",
                    meaningAr = "الأبد / الأبدي / الدائم",
                    meaningEn = "eternity / everlasting",
                    grammaticalRoleAr = "مضاف إليه مجرور، يفيد تأبيد صفة الملكوت",
                    root = "ع-ل-م (Proto-Semitic: *ʿalam-)",
                    morphologicalPattern = "اسم ثلاثي مفرد مجرور",
                    cognatesComparison = "العربية: عالَم / الأبد | الفينيقية: 𐤏𐤋𐤌 | العبرية: עולם (ʿōlām) | الآرامية: 𐡏𐡋𐡌 (ʿālam)"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐎁𐎓𐎍",
                    transliteration = "bʿl",
                    ipa = "/ˈbaʕlu/",
                    meaningAr = "بعل (سيد المطر والعواصف)",
                    meaningEn = "Baal (Lord)",
                    grammaticalRoleAr = "مبتدأ مؤخر مرفوع بالضمة (اسم علم إلهي)",
                    root = "ب-ع-ل (السيد والمالك والزوج والرب)",
                    morphologicalPattern = "اسم ثلاثي من أصل السامية الأم",
                    cognatesComparison = "العربية: بَعْل (السيد / الزوج / الشجر العذي المستغني بماء السماء) | العبرية: בַּעַל"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐎊𐎎𐎍𐎋",
                    transliteration = "ymlk",
                    ipa = "/jamˈluku/",
                    meaningAr = "يملِك / يعتلي سدة الحكم",
                    meaningEn = "he reigns / rules",
                    grammaticalRoleAr = "فعل مضارع مرفوع، فاعله ضمير مستتر يعود على بعل",
                    root = "م-ل-ك (الحكم والسلطان)",
                    morphologicalPattern = "صيغة يَفْعُل (yaqțul) الاستمرارية",
                    cognatesComparison = "العربية: يَمْلِكُ | الفينيقية: ymlk | العبرية: יִמְלֹךְ (yimloḵ)"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐎍𐎚𐎁𐎚",
                    transliteration = "l-tbt",
                    ipa = "/li-tibti/",
                    meaningAr = "للجلوس / للاعتلاء والقعود",
                    meaningEn = "for sitting / enthronement",
                    grammaticalRoleAr = "جار ومجرور (اللام الجارة + مصدر و-ث-ب)",
                    root = "و-ث-ب / ي-ث-ب (القعود والاستقرار في الكرسي)",
                    morphologicalPattern = "مصدر ميمي/اسم مرة ثلاثي معتل الفاء",
                    cognatesComparison = "السبئية: wṯb (جلس وحكم) | العبرية: שֶׁבֶת (ševet) | العربية: وَثَبَ (بمعنى قفز واستقر)"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐎋𐎅𐎚",
                    transliteration = "kht",
                    ipa = "/kaḥti/",
                    meaningAr = "كرسي / عرش الحكم",
                    meaningEn = "throne / seat of majesty",
                    grammaticalRoleAr = "مضاف إليه مجرور",
                    root = "ك-ح-ث (العرش في الساميات الشمالية القديمة)",
                    morphologicalPattern = "اسم مؤنث بالتاء",
                    cognatesComparison = "الحورية/السامية الشمالية: kuḥtu (عرش المجد)"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐎎𐎍𐎋𐎅",
                    transliteration = "mlk-h",
                    ipa = "/malki-hu/",
                    meaningAr = "مُلْكِهِ / سلطانه",
                    meaningEn = "his kingdom / sovereignty",
                    grammaticalRoleAr = "مضاف إليه مجرور وعلامة جره الكسرة، والهاء ضمير متصل مبني في محل جر",
                    root = "م-ل-ك",
                    morphologicalPattern = "اسم ثلاثي مضاف للضمير المتصل الغائب -h",
                    cognatesComparison = "العربية: مُلْكِهِ | الفينيقية: mlk-h | العبرية: malkō (מַלְכּוֹ)"
                )
            ),
            phonologicalFeatures = listOf(
                InscriptionPhonologicalLaw(
                    ruleTitleAr = "الأبجدية المسمارية الموسعة وحفظ الصوامت السامية الـ 30",
                    formula = "Proto-Semitic 29 Consonants + 3 Distinct Aleph Vowels",
                    explanationAr = "تمثل الأوغاريتية الحصن الفونولوجي الأكثر كمالاً في الفرع الشمالي الغربي، حيث احتفظت بالذال، الثاء، الغين، الخاء، والظاء دون إدغام، وأضافت ثلاث علامات مستقلة لصوت الألف مصحوبة بحركات الفتح والكسر والضم (ʾa, ʾi, ʾu).",
                    inTextExamples = listOf("𐎎𐎍𐎋 (mlk)", "𐎓𐎍𐎎 (ʿlm)", "𐎁𐎓𐎍 (bʿl)")
                ),
                InscriptionPhonologicalLaw(
                    ruleTitleAr = "عدم خضوع الأوغاريتية للتحول الكنعاني (*ā > ō)",
                    formula = "Proto-Semitic *ā remains /aː/ (NOT /oː/)",
                    explanationAr = "تثبت الأوغاريتية أنها لغة شقيقة للكنعانية وليست كنعانية محضة، إذ لم تخضع لقانون التحول الكنعاني للصائت الطويل، فظلت كلمة (ʿālam) تحتفظ بالألف الطويلة المفتوحة دون أن تنقلب إلى واو كما في الفينيقية والعبرية.",
                    inTextExamples = listOf("𐎓𐎍𐎎 (ʿālamu, not ʿōlōm)")
                )
            ),
            morphologicalFeatures = listOf(
                "حفظ نظام الإعراب الثلاثي التام بالحركات القصيرة: الرفع بالضمة (-u)، النصب بالفتحة (-a)، الجر بالكسرة (-i).",
                "لواحق الضمائر الغائبة بالأصل الحنجري الصريح: هاء الغائب المفرد المذكر (-h = -hu) والمؤنث (-h = -ha).",
                "استخدام الأبجدية المسمارية لكتابة أدب شعري ملحمي متطور يعتمد التوازي التركيبي."
            ),
            syntacticFeatures = listOf(
                "التوازي الشعري المترادف (Parallelismus Membrorum): تكرار الفكرة الواحدة بصيغتين متقابلتين في الشطرين.",
                "بنية الجملة الاسمية والفعلية المتشابكة لتعظيم ملكوت الآلهة في قمة جبل صفون."
            ),
            epigraphicPaleographicNotes = listOf(
                "وسيط التدوين: رقيمات الطين المشوي المكتوبة بأقلام القصب ذات الرؤوس المثلثة في مدينة رأس الشمرا الساحلية.",
                "اتجاه الكتابة: تكتب الأوغاريتية المسمارية من اليسار إلى اليمين استجابة لطبيعة الألواح الطينية المسمارية، خلافاً لبقية الخطوط الأبجدية السامية."
            ),
            comparativeSemiticInsights = listOf(
                "الموازاة المعجمية والأسلوبية المباشرة مع مزامير ونصوص العهد القديم، والشعر العربي الجاهلي في وصف هطول الأمطار وحروب الآلهة.",
                "الصلة المعجمية الصريحة مع اللسان العربي في مئات الجذور الصافية (مثل: ملك، علم، جلس، أرض، مطر، سحاب)."
            ),
            historicalSignificanceAr = "تعد ملحمة بعل أعظم وثيقة أدبية ودينية اكتشفت في بلاد الشام في القرن العشرين، وهي توثق صراع بعل (إله المطر والخصوبة) ضد يم (إله البحر والفوضى) وموت (إله الجفاف والموت) لضمان استمرار الحياة."
        ),
        "tel_dan_stele" to InscriptionLinguisticBreakdown(
            inscriptionId = "tel_dan_stele",
            titleAr = "مسلة تل القاضي (دان) الآرامية - نقش بيت داود",
            scriptNameAr = "الخط الآرامي القديم المبكر (Old Aramaic Script)",
            writingDirectionAr = "من اليمين إلى اليسار (RTL)",
            wordDividerAr = "نقاط فاصلة أفقية بين الكلمات",
            tokens = listOf(
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐡅𐡒𐡕𐡋𐡕",
                    transliteration = "w-qtlt",
                    ipa = "/wa-qataltu/",
                    meaningAr = "وقتلتُ / وقضيتُ على",
                    meaningEn = "and I killed",
                    grammaticalRoleAr = "فعل ماضٍ مبني على السكون لاتصاله بتاء الفاعل للمتكلم المفرد مع واو العطف",
                    root = "ق-ت-ل (الفتك والإماتة)",
                    morphologicalPattern = "وزن فَعَلْتُ (faʿaltu)",
                    cognatesComparison = "العربية: قَتَلْتُ | العبرية: qātalətī (קָטַלְתִּי) | السريانية: qəṭlet (ܩܛܠܬ) | الأكادية: qatālu"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐡌𐡋𐡊",
                    transliteration = "mlk",
                    ipa = "/malkaː/",
                    meaningAr = "مَلِك / مَلِكَ",
                    meaningEn = "king of",
                    grammaticalRoleAr = "مفعول به منصوب في حالة إضافة",
                    root = "م-ل-ك",
                    morphologicalPattern = "اسم ثلاثي صحيح في حالة بناء إضافي",
                    cognatesComparison = "العربية: مَلِك | العبرية: melek | الفينيقية: mlk"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐡉𐡔𐡓𐡀𐡋",
                    transliteration = "yšrʾl",
                    ipa = "/jiśraːˈʔeːl/",
                    meaningAr = "إسرائيل (مملكة إسرائيل الشمالية - السامرة)",
                    meaningEn = "Israel (Northern Kingdom)",
                    grammaticalRoleAr = "مضاف إليه مجرور (علم دولة ومملكة)",
                    root = "ي-ش-ر + إ-ل (يجاهد/يصارع مع الله)",
                    morphologicalPattern = "علم مركب مزجي ثيوفوري",
                    cognatesComparison = "العبرية: יִשְׂרָאֵל | الآرامية: yšrʾl | العربية: إسرائيل"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐡁𐡉𐡕",
                    transliteration = "byt",
                    ipa = "/beːt/",
                    meaningAr = "بيت / سلالة وعشيرة وقصر",
                    meaningEn = "house / dynasty of",
                    grammaticalRoleAr = "مضاف في حالة إضافة (Construct State)",
                    root = "ب-ي-ت (المسكن والسلالة الملكية الحاكمة)",
                    morphologicalPattern = "اسم ثلاثي معتل العين بانكماش الياء (*bayt > bēt)",
                    cognatesComparison = "العربية: بَيْت | السريانية: bēt (ܒܝܬ) | العبرية: bēt (בֵּית) | الأكادية: bītu"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐡃𐡅𐡃",
                    transliteration = "dwd",
                    ipa = "/daːˈwiːd/",
                    meaningAr = "داود (الملك والمؤسس لسلالة يهوذا)",
                    meaningEn = "David (King and dynastic founder)",
                    grammaticalRoleAr = "مضاف إليه مجرور (اسم علم مذكر)",
                    root = "د-و-د (المحبوب والودود)",
                    morphologicalPattern = "اسم علم سامي قديم",
                    cognatesComparison = "العبرية: דָּוִד (Dāwīd) | العربية: داود | الأوغاريتية: dwd (الحبيب)"
                )
            ),
            phonologicalFeatures = listOf(
                InscriptionPhonologicalLaw(
                    ruleTitleAr = "انكماش الصوائت المزدوجة في الآرامية القديمة (Diphthong Contraction)",
                    formula = "*ay > /eː/, *aw > /oː/",
                    explanationAr = "يوضح نقش تل دان انكماش الصوت المزدوج (*bayt-) إلى (bēt) قبل المضاف إليه، وهي سمة آرامية وفينيقية بارزة ميزت اللغات الشمالية الغربية عن العربية الفصحى التي احتفظت بالواو والياء الساكنتين المفتوح ما قبلهما.",
                    inTextExamples = listOf("𐡁𐡉𐡕 𐡃𐡅𐡃 (bytdwd = bēt-dawīd)")
                )
            ),
            morphologicalFeatures = listOf(
                "استخدام تاء الفاعل للمتكلم المفرد (-t = -tu) المطابقة تماماً لتصريف الماضي العربي والأكادي.",
                "بناء التركيب الإضافي الملكي المباشر (bytdwd) بدون أداة وصل، وهو أسلوب توثيقي سامي كلاسيكي للممالك والسلالات الإقليمية مثل (Bīt-Humrī / Bīt-Adini)."
            ),
            syntacticFeatures = listOf(
                "سرد عسكري تفاخري بالانتصارات الحربية للملك الآرامي (حزائيل) على جيرانه الملوك.",
                "بناء الجملة الفعلية (فعل + فاعل + مفعول مضاف إليه)."
            ),
            epigraphicPaleographicNotes = listOf(
                "الخط: آرامي قديم مبكر يمثل مرحلة التفرع عن الأبجدية الفينيقية الأم نحو الحروف المنحنية والسيالة.",
                "الموقع الإبيغرافي: نحتت الحروف بحفر غائر حاد على حجر بازلتي قاسي شكّل جزءاً من بوابة مدينة دان القديمة."
            ),
            comparativeSemiticInsights = listOf(
                "أحدث هذا النقش ثورة في علم الآثار التوراتي والسامي عند اكتشافه عام 1993م لدحضه فرضيات المشككين بوجود شخصية تاريخية اسمها 'داود'.",
                "يوثق النقش الاستخدام الإقليمي للآرامية كلغة ديبلوماسية وعسكرية في المشرق العربي القديم."
            ),
            historicalSignificanceAr = "يعد نقش تل دان الوثيقة الإبيغرافية الأهم المعاصرة لملوك آرام دمشق، ويوثق الصراع الإقليمي العنيف بين الممالك الآرامية والممالك العبرية في القرن التاسع قبل الميلاد."
        ),
        "sirwah_inscription" to InscriptionLinguisticBreakdown(
            inscriptionId = "sirwah_inscription",
            titleAr = "نقش النصر العظيم لكربئيل وتر بصرواح (RES 3945)",
            scriptNameAr = "خط المسند السبئي التذكاري البارز (Monumental Sabaean Musnad)",
            writingDirectionAr = "محراثي تبادلي (Boustrophedon) أو من اليمين إلى اليسار",
            wordDividerAr = "خط رأسي عمودي فاصل ومحدد للألفاظ (𐩽)",
            tokens = listOf(
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩫𐩧𐩨𐩱𐩡",
                    transliteration = "krbʾl",
                    ipa = "/karibˈʔiːl/",
                    meaningAr = "كربئيل (المقرّب من الله / نذير الإله)",
                    meaningEn = "Karib'il (Blessed / consecrated to God)",
                    grammaticalRoleAr = "مبتدأ في صدر الجملة التوثيقية (اسم علم مركب إضافي ثيوفوري)",
                    root = "ك-ر-ب + إ-ل (القربان والنذر والمباركة)",
                    morphologicalPattern = "اسم مفعول أو صفة مشبهة + إيل",
                    cognatesComparison = "الأكادية: karābu (يبارك / يصلي) | العبرية: כְּרוּב (كروب / الملاك المقرب) | العربية: قَرُبَ / الكَروبيون"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩥𐩩𐩧",
                    transliteration = "wtr",
                    ipa = "/waˈtaːr/",
                    meaningAr = "وَتَر (العظيم / الفارع / الزائد رفعةً ومجداً)",
                    meaningEn = "Watar (the Great / exalted)",
                    grammaticalRoleAr = "لقب ملكي رسمي (نعت للملك كربئيل)",
                    root = "و-ت-ر (الزيادة والمجد والتفرد)",
                    morphologicalPattern = "صفة مشبهة على وزن فَعَال",
                    cognatesComparison = "العربية: وَتْر / أَوْتَرَ (زاد وتفرد وعظم) | الجعزية: watara (داوم واستمر)"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩨𐩬",
                    transliteration = "bn",
                    ipa = "/bin/",
                    meaningAr = "ابن",
                    meaningEn = "son of",
                    grammaticalRoleAr = "بدل أو نعت في حالة إضافة",
                    root = "ب-ن-ي",
                    morphologicalPattern = "اسم ثلاثي مجرد في حالة إضافة",
                    cognatesComparison = "العربية: ابن | السبئية: 𐩨𐩬 | الفينيقية: 𐤁𐤍 | العبرية: bēn"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩵𐩣𐩧𐩲𐩡𐩺",
                    transliteration = "ḏmrʿly",
                    ipa = "/ḏamarˈʕalaj/",
                    meaningAr = "ذمار علي (حامي الشرف العلي / ذمار العلي)",
                    meaningEn = "Dhamar'ali (Protector of the Exalted)",
                    grammaticalRoleAr = "مضاف إليه مجرور (اسم والد الملك)",
                    root = "ذ-م-ر + ع-ل-ي (الذمار والشرف والمنعة)",
                    morphologicalPattern = "مركب إضافي يمني أصيل",
                    cognatesComparison = "العربية: ذِمار (ما يلزمك حمايته والذود عنه) وعليّ (المرتفع)"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩣𐩫𐩧𐩨",
                    transliteration = "mkrb",
                    ipa = "/mukarrabu/",
                    meaningAr = "مُكَرِّب (الحاكم الاتحادي الجامع الديني والسياسي لسبأ)",
                    meaningEn = "Mukarrib (Federator / Unifier Priest-King)",
                    grammaticalRoleAr = "نعت أو خبر لبيان الصفة الدستورية والسياسية",
                    root = "ك-ر-ب (التقريب والجمع وتوثيق الأحلاف والنذور)",
                    morphologicalPattern = "اسم فاعل من وزن التضعيف الميمى (مُفَعِّل)",
                    cognatesComparison = "الأكادية: mukarribu | الجعزية: makrab (المحفل والجامع) | العربية: قرّب وجمع الأحلاف"
                ),
                InscriptionTokenAnalysis(
                    tokenOriginal = "𐩪𐩨𐩱",
                    transliteration = "sbʾ",
                    ipa = "/sabaʔ/",
                    meaningAr = "سَبَأ (الشعب والمملكة التاريخية العظيمة)",
                    meaningEn = "Saba (Sheba)",
                    grammaticalRoleAr = "مضاف إليه مجرور",
                    root = "س-ب-أ",
                    morphologicalPattern = "اسم علم قومي وجغرافي",
                    cognatesComparison = "العربية: سَبَأ | الأكادية: Saba' | العبرية: שְׁבָא (Šəḇāʾ) | اليونانية: Σαβά"
                )
            ),
            phonologicalFeatures = listOf(
                InscriptionPhonologicalLaw(
                    ruleTitleAr = "الاحتفاظ بأصوات الصفير الثلاثة والضاد الجانبية المطبقة",
                    formula = "Triple Sibilant System (s1, s2, s3) + Lateral Emphatic ḍ",
                    explanationAr = "يمثل نقش صرواح النموذج الأسمى للفونولوجيا السبئية الكلاسيكية، حيث يفرق النقش بدقة بين السين الأولى (s1 𐩪)، والسين الثانية الجانبية (s2 𐩦 الشبيهة بالشين)، والسين الثالثة الأسنانية (s3 𐩯)، مع وضوح تام للضاد الجانبية (𐩳).",
                    inTextExamples = listOf("𐩪𐩨𐩱 (s1bʾ)", "𐩦𐩲𐩨 (s2ʿb = شعب)", "𐩳𐩧𐩨 (ḍrb = ضَرْب)")
                )
            ),
            morphologicalFeatures = listOf(
                "ألقاب الحكام والمكارب على وزن اسم الفاعل المضعف (مُكَرِّب / mukarrib).",
                "أوزان الأفعال الرباعية والتوسيع بالألف والتاء لتوثيق الفتوحات وبناء المدن والأسوار.",
                "استخدام نون التعريف المسندية اللاحقة في أسماء الممالك والحصون والمنشآت المائية."
            ),
            syntacticFeatures = listOf(
                "الأسلوب الحولي التاريخي الإمبراطوري: توثيق الحملات العسكرية سنة بسنة، وتحديد أسماء المدن المفتوحة، والغنائم، والمعاهدات الفيدرالية.",
                "الصيغ الدينية التكريسية: ربط كل نصر حربي ببركة ونذور الإله القومي لسبأ (إلمقه رب أوعال صرواح)."
            ),
            epigraphicPaleographicNotes = listOf(
                "أطول وأكمل نقش مسندي سبئي على الإطلاق، محفور بأناقة هندسية باهرة في أحجار الجير الأبيض الملساء داخل معبد صرواح التاريخي بمأرب.",
                "استخدام الخطوط الرأسية الفاصلة بين كل مفردة، واستقامة الأسطر المنحوتة بنظام الحفر البارز عالي التباين."
            ),
            comparativeSemiticInsights = listOf(
                "الشاهد الأركيولوجي الأهم على انتقال اليمن من عصر 'مكارب سبأ' (حكام الاتحاد الديني والقبلي) إلى عصر 'ملوك سبأ'.",
                "تطابق مدهش في مئات المفردات الإدارية والقبلية مع المعاجم العربية الكبرى للهمداني ونشوان الحميري ولغة القرآن الكريم."
            ),
            historicalSignificanceAr = "يعد نقش صرواح التأسيسي وثيقة توحيد شبه الجزيرة العربية الجنوبية، حيث سجل كربئيل وتر فيه القضاء على نفوذ مملكة أوسان وتوحيد ممالك قتبان وحضرموت وسبأ تحت راية فيدرالية واحدة في مطلع القرن السابع قبل الميلاد."
        )
    )

    fun getBreakdown(inscriptionId: String): InscriptionLinguisticBreakdown? {
        val normalized = inscriptionId.lowercase().trim()
        val direct = BREAKDOWNS_BY_ID[normalized]
            ?: BREAKDOWNS_BY_ID[normalized.replace("-", "_")]
            ?: BREAKDOWNS_BY_ID[normalized.replace("_", "-")]
        if (direct != null) return direct

        if (normalized.contains("ugarit")) {
            return BREAKDOWNS_BY_ID["ugarit_baal_epic"]
                ?: BREAKDOWNS_BY_ID["ugaritic_baal_epic"]
        }
        if (normalized.contains("mesha")) {
            return BREAKDOWNS_BY_ID["mesha_stele"]
        }
        if (normalized.contains("ahiram")) {
            return BREAKDOWNS_BY_ID["ahiram_sarcophagus"]
        }
        if (normalized.contains("hammurabi")) {
            return BREAKDOWNS_BY_ID["hammurabi_stele"]
        }
        if (normalized.contains("marib")) {
            return BREAKDOWNS_BY_ID["marib_dam_inscription"]
        }
        if (normalized.contains("dan")) {
            return BREAKDOWNS_BY_ID["tel_dan_stele"]
        }
        if (normalized.contains("sirwah")) {
            return BREAKDOWNS_BY_ID["sirwah_inscription"]
        }
        return null
    }
}
