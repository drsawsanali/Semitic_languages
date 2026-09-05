package com.example.core.data

import com.example.core.model.GlossaryCategory
import com.example.core.model.SemiticGlossaryItem

/**
 * Comprehensive Academic Glossary of Semitic Linguistics, Epigraphy, and Comparative Philology.
 * Contains core philological terms, formulas, epigraphic attestations, and comparative cognates.
 */
object SemiticGlossaryData {

    val GLOSSARY_ITEMS: List<SemiticGlossaryItem> = listOf(
        SemiticGlossaryItem(
            id = "term_canaanite_shift",
            termAr = "التحول الكنعاني (انتقال الصائت)",
            termEn = "Canaanite Vowel Shift",
            category = GlossaryCategory.PHONETICS,
            academicDefinitionAr = "قانون صوتي فيلولوجي حاكم في اللغات الكنعانية (الفينيقية، البونية، العبرية، المؤابية، العمونية) يتمثل في تحول الألف الممدودة المنبورة في السامية الأم (*ā) إلى واو مشبعة طويلة (ō). وهو المعيار الفاصل بين الفرع الكنعاني وسائر الفروع السامية.",
            protoSemiticBasis = "*ā > ō (في المقاطع المنبورة)",
            linguisticFormula = "Proto-Semitic *ā ──[Canaanite Shift]──> Canaanite / Phoenician / Hebrew /ō/",
            relatedLanguages = listOf("الفينيقية", "المؤابية", "العبرية القديمة", "البونية", "الأوغاريتية (استثناء جزئي)"),
            epigraphicAttestations = listOf(
                "مسلة ميشع (سطر 1): 𐤀𐤍𐤊 𐤌𐤉𐤔𐤏 ('anōkī بدلاً من السامية الأم 'anāku)",
                "نقش تابوت أحيرام بجبيل: 𐤔𐤋𐤌 (šālōm من أصل سامي *šalām-)"
            ),
            comparativeCognates = listOf(
                "السامية الأم" to "*šalām- (سلام، أمان)",
                "الفينيقية" to "šālōm / šulūm (𐤔𐤋𐤌)",
                "العربية" to "سَلَام (احتفظت بالألف الأصلية)",
                "الآرامية" to "šəlāmā (احتفظت بالألف)",
                "الأكادية" to "šalāmu (احتفظت بالألف)"
            ),
            searchKeywords = listOf("التحول الكنعاني", "كنعاني", "تحول صوتي", "canaanite shift", "vowel shift", "صوائت", "ألف", "واو"),
            relatedChapterIds = listOf("chap_2", "chap_11", "chap_21")
        ),
        SemiticGlossaryItem(
            id = "term_mimation",
            termAr = "الميمية (التنكير والإعراب بالميم)",
            termEn = "Mimation",
            category = GlossaryCategory.MORPHOLOGY,
            academicDefinitionAr = "ظاهرة صرفية ونحوية سامية قديمة تتمثل في إلحاق صوت الميم (-m) في أواخر الأسماء المعربة للدلالة على الإفراد أو التنكير أو التمام الإعرابي. شائعة في الأكادية (بابلية وآشورية) والسبئية والعمورية المبكرة، وتقابلها ظاهرة النونية في العربية والآرامية.",
            protoSemiticBasis = "*-m (لاحقة إعرابية وتنكيرية)",
            linguisticFormula = "Noun-Stem + Case Vowel (-u/-a/-i) + /m/ = -um (رفع), -am (نصب), -im (جر)",
            relatedLanguages = listOf("الأكادية", "البابلية", "الآشورية", "السبئية", "المعينية", "العمورية"),
            epigraphicAttestations = listOf(
                "مسلة شريعة حمورابي: awīlum (الرجل)، bītum (البيت)، šarrum (الملك)",
                "نقوش مأرب السبئية: 𐩨𐩺𐩩𐩣 (bytm: بيتاً/بيت)"
            ),
            comparativeCognates = listOf(
                "الأكادية" to "šarrum (ملكٌ) / šarram (ملكَ) / šarrim (ملكٍ)",
                "السبئية" to "𐩣𐩡𐩫𐩣 (mlkm: ملكٌ)",
                "العربية" to "مَلِكٌ (نونية: -un بمقابل الميمية -um)"
            ),
            searchKeywords = listOf("الميمية", "ميمية", "mimation", "تنكير", "إعراب", "أكادية", "سبئية"),
            relatedChapterIds = listOf("chap_1", "chap_14", "chap_24")
        ),
        SemiticGlossaryItem(
            id = "term_nunation",
            termAr = "النونية (التنوين والتعريف بالنون)",
            termEn = "Nunation",
            category = GlossaryCategory.MORPHOLOGY,
            academicDefinitionAr = "إلحاق النون (-n) في أواخر الأسماء، وتظهر بوظيفتين مختلفتين في اللغات السامية: في العربية للدلالة على التنكير (التنوين: -un, -an, -in)، بينما في خط المسند باللغات العربية الجنوبية القديمة (السبئية والقتبانية) تؤدي وظيفة أداة التعريف اللاحقة (-n).",
            protoSemiticBasis = "*-n (لاحقة اسمية)",
            linguisticFormula = "العربية: [الاسم + نون التنكير] | السبئية: [الاسم + نون التعريف: mlk-n = الملك]",
            relatedLanguages = listOf("العربية الفصحى", "السبئية", "القتبانية", "الحضرمية", "الآرامية القديمة"),
            epigraphicAttestations = listOf(
                "نقش صرواح الكبير: 𐩣𐩡𐩫𐩬 (mlkn: الملك المعرف)",
                "نقش النمارة: 𐢖𐢍𐢏𐢛𐢝 (امرؤ القيس ملك العرب)"
            ),
            comparativeCognates = listOf(
                "العربية" to "كِتَابٌ (تنكير نوني)",
                "السبئية" to "𐩫𐩩𐩨𐩬 (ktbn: الكتاب المعرف)",
                "الآرامية" to "ktāb-ā (التعريف بالألف اللاحقة)"
            ),
            searchKeywords = listOf("النونية", "نونية", "تنوين", "nunation", "تعريف بالنون", "سبئية"),
            relatedChapterIds = listOf("chap_4", "chap_14", "chap_34")
        ),
        SemiticGlossaryItem(
            id = "term_begadkefat",
            termAr = "قانون بجد كفت (الترقيق والانفجار الصوتي)",
            termEn = "Begadkefat Rule",
            category = GlossaryCategory.PHONETICS,
            academicDefinitionAr = "ظاهرة ألوفونية صوتية دقيقة تختص بها اللغات السامية الشمالية الغربية (خاصة الآرامية والسريانية والعبرية)، حيث تمتلك الحروف الستة (ب، ج، د، ك، ف، ت) نطقين: انفجاري قاسي (Plosive / Occlusive) إذا وقعت في أول الكلام أو بعد ساكن، واحتكاكي رخو (Fricative / Spirantized) إذا وقعت بعد حركة صائتة.",
            protoSemiticBasis = "أصوات انفجارية أصلية تلين بعد الحركات",
            linguisticFormula = "Vowel + /b, g, d, k, p, t/ ──> [β/v, ɣ, ð, x, f, θ]",
            relatedLanguages = listOf("السريانية", "الآرامية الإمبراطورية", "العبرية القديمة", "المندائية"),
            epigraphicAttestations = listOf(
                "المخطوطات السريانية: التنقيط الفوقاني (القوشايا) للتشديد والانفجار، والتنقيط التحتاني (الروكاكا) للترقيق والرخاوة."
            ),
            comparativeCognates = listOf(
                "السريانية" to "ܒܝܬܐ (baytā: الباء انفجارية لأنها أول الكلمة، والتاء احتكاكية θ بعد صائت)",
                "العربية" to "بَيْت (احتفظت بنطق الصوامت الانفجارية في كافة المواضع)"
            ),
            searchKeywords = listOf("بجد كفت", "begadkefat", "ترقيق", "رخاوة", "انفجاري", "احتكاكي", "سريانية", "آرامية"),
            relatedChapterIds = listOf("chap_3", "chap_13", "chap_33")
        ),
        SemiticGlossaryItem(
            id = "term_waw_consecutive",
            termAr = "واو العطف السردية التتابعية (Wayyiqtol)",
            termEn = "Waw Consecutive / Inversive",
            category = GlossaryCategory.SYNTAX,
            academicDefinitionAr = "تركيب نحوي وأسلوبي كنعاني فريد يربط الجمل السردية التاريخية؛ حيث تدخل واو العطف الخاصة على صيغة الفعل الناقص (المضارع: yiqtol) مع تشديد حرف المضارعة، فتقلب زمنه ودلالته إلى الماضي السردي التتابعي (Past Narrative). تعد السمة الأسلوبية الأبرز في مسلة ميشع والكنعانية ونصوص الأوغاريتية.",
            protoSemiticBasis = "*wa + yiqtol (صيغة سردية تتابعية)",
            linguisticFormula = "wa- + yiqtol ──> wayyiqtol (فـَـ فَعَلَ / ومضى يفعل)",
            relatedLanguages = listOf("المؤابية", "الفينيقية", "العبرية القديمة", "الأوغاريتية"),
            epigraphicAttestations = listOf(
                "مسلة ميشع (السطر 5): 𐤅𐤉𐤏𐤍𐤅 𐤏𐤌𐤓𐤉 (w-yʿnw ʿmry: وأذلّ عمري ملك إسرائيل مؤاب)",
                "مسلة ميشع (السطر 9): 𐤅𐤀𐤁𐤍 𐤁𐤏𐤋𐤌𐤏𐤍 (w-ʾbn bʿlmʿn: وبنيتُ بعلمعون)"
            ),
            comparativeCognates = listOf(
                "المؤابية" to "𐤅𐤉𐤀𐤌𐤓 (w-yʾmr: وقالَ)",
                "العربية" to "فقالَ / وقالَ (استعمال الفعل الماضي الصريح بعد العطف)"
            ),
            searchKeywords = listOf("واو العطف السردية", "واو القلب", "wayyiqtol", "waw consecutive", "ميشع", "مؤابية", "كنعانية"),
            relatedChapterIds = listOf("chap_2", "chap_12", "chap_32")
        ),
        SemiticGlossaryItem(
            id = "term_acrophony",
            termAr = "الأكروفونية (المبدأ التصويري لتسمية الحروف)",
            termEn = "Acrophony",
            category = GlossaryCategory.EPIGRAPHY,
            academicDefinitionAr = "المبدأ الباليوغرافي الذي ابتكره الكنعانيون في سيناء (سرابيط الخادم) والشام لصياغة أول أبجدية صوتية في تاريخ البشرية؛ حيث أخذوا رسماً تصويرياً لشيء ما، وجعلوا الرمز يمثل فقط الصوت الأول من اسم ذلك الشيء (مثال: رسم 'البيت' 𐤁 يمثل صوت /b/ لأن كلمة بيت تبدأ بالباء، ورسم 'الرأس' 𐤓 يمثل صوت /r/).",
            protoSemiticBasis = "اختزال الرمز الإيدوغرافي إلى قيمته الصوتية الأولى",
            linguisticFormula = "Pictogram (e.g. *bayt- 'house') ──[Acrophony]──> Letter /b/ (𐤁)",
            relatedLanguages = listOf("الكنعانية المبكرة (السينائية)", "الفينيقية", "الأوغاريتية", "المسند", "السامية الأم"),
            epigraphicAttestations = listOf(
                "نقوش سرابيط الخادم بسيناء (نحو 1800 ق.م): نقش 'لبعلت' (𐤋𐤁𐤏𐤋𐤕)",
                "أبجديات رأس الشمرا المسمارية (نظام الترتيب الأبجدي)"
            ),
            comparativeCognates = listOf(
                "𐤀 (ألف)" to "*ʾalpu- (ثور / رأس ثور)",
                "𐤁 (بيت)" to "*baytu- (منزل / مأوى)",
                "𐤂 (جمل)" to "*gamlu- (عصا معقوفة / جمل)",
                "𐤃 (دالت)" to "*daltu- (دلفة الباب)"
            ),
            searchKeywords = listOf("الأكروفونية", "أكروفونية", "acrophony", "سرابيط الخادم", "نشأة الأبجدية", "فينيقية"),
            relatedChapterIds = listOf("chap_2", "chap_41", "chap_42")
        ),
        SemiticGlossaryItem(
            id = "term_construct_state",
            termAr = "صيغة الإضافة (Status Constructus)",
            termEn = "Construct State",
            category = GlossaryCategory.SYNTAX,
            academicDefinitionAr = "حالة إعرابية وتركيبية سامية أصيلة تقتضي تجريد الاسم الأول (المضاف) من أداة التعريف وعلامات التنوين والميمية أو النونية وحذف نون التثنية والجمع، وربطه صوتياً ومقطعياً بالاسم الثاني (المضاف إليه). ويصاحبها غالباً تقصير في الصوائت الطويلة بسبب انتقال نبرة الصوت إلى المضاف إليه.",
            protoSemiticBasis = "*malku + *qaryatim ──> *malki-qaryati",
            linguisticFormula = "Noun1 (stripped of mimation/nunation/article) + Noun2 (Genitive)",
            relatedLanguages = listOf("كافة اللغات السامية بلا استثناء"),
            epigraphicAttestations = listOf(
                "نقش تابوت أحيرام: 𐤌𐤋𐤊 𐤂𐤁𐤋 (mlk gbl: ملكُ جبيلَ)",
                "مسلة حمورابي: šar māt šumerim u akkadīm (ملكُ بلاد سومر وأكاد)"
            ),
            comparativeCognates = listOf(
                "الفينيقية" to "𐤁𐤕 𐤌𐤋𐤊 (bt mlk: بيت الملك)",
                "العربية" to "بَيْتُ المَلِكِ (إضافة تجرد الأول من التنوين)",
                "السبئية" to "𐩨𐩺𐩩 𐩣𐩡𐩫𐩬 (byt mlkn: بيت الملك)"
            ),
            searchKeywords = listOf("صيغة الإضافة", "إضافة", "construct state", "status constructus", "مضاف", "مضاف إليه"),
            relatedChapterIds = listOf("chap_1", "chap_2", "chap_31")
        ),
        SemiticGlossaryItem(
            id = "term_lateral_fricatives",
            termAr = "الصوامت الجانبية الاحتكاكية (الضاد والشين الجانبية)",
            termEn = "Lateral Fricatives",
            category = GlossaryCategory.PHONETICS,
            academicDefinitionAr = "أصوات صامتة سامية بدائية تنطق بجريان الهواء على جانبي اللسان مع احتكاك. أهمها الضاد السامية الأم (*ḍ / ḍ́) والشين الجانبية (*ś / š́). حفظت هذه الأصوات بدقة متناهية في خط المسند باللغات العربية الجنوبية القديمة (السبئية والحضرمية) واللغات العربية الجنوبية الحديثة الحية (المهرية والسقطرية والشحرية)، بينما اندمجت في الفروع الشمالية والغربية.",
            protoSemiticBasis = "صامتان جانبيان مستقلان: *ḍ (جانبي مطبق) و *ś (جانبي احتكاكي مهموس)",
            linguisticFormula = "Proto-Semitic *ḍ ──> Sabaic 𐩳 / Arabic ض / Aramaic ע (ʿ) / Hebrew צ (ṣ) / Akkadian ṣ",
            relatedLanguages = listOf("السبئية", "المهرية", "السقطرية", "الشحرية", "العربية التراثية"),
            epigraphicAttestations = listOf(
                "نقش سد مأرب: 𐩱𐩧𐩳𐩬 (ʾrḍn: الأرض بصوت الضاد الجانبي المسندي)",
                "نقوش صرواح: 𐩪𐩨𐩱 (sbʾ: سبأ بالسين/الشين الجانبية)"
            ),
            comparativeCognates = listOf(
                "السامية الأم" to "*ʾarṣ́- / *ʾarḍ- (أرض)",
                "السبئية" to "𐩱𐩧𐩳 (ʾrḍ)",
                "العربية" to "أَرْض (ضاد جانبية فصحى قديمة)",
                "الآرامية" to "ʾarʿā (تحولت الضاد إلى عين!)",
                "العبرية" to "ʾereṣ (تحولت إلى صاد)"
            ),
            searchKeywords = listOf("الصوامت الجانبية", "ضاد جانبية", "شين جانبية", "lateral fricative", "سبئية", "مهرية"),
            relatedChapterIds = listOf("chap_4", "chap_5", "chap_14")
        ),
        SemiticGlossaryItem(
            id = "term_ejectives",
            termAr = "الصوامت القذفية الحنجرية (المطبقة بالانحباس)",
            termEn = "Ejective Consonants",
            category = GlossaryCategory.PHONETICS,
            academicDefinitionAr = "طريقة النطق الأصلية للصوامت السامية المفخمة (المطبقة: ط، ق، ص، ظ، ض) في السامية الأم، وتتم عبر قفل الحنجرة وإطلاق دفعة هواء مضغوطة بقذف حنجري (Glottalic ejective: [tʼ, kʼ, sʼ]). تحتفظ بها اللغات السامية الإثيوبية (كالجعزية والأمهرية) حتى اليوم، بينما تحولت في العربية إلى إطباق بلعومي (Pharyngealization).",
            protoSemiticBasis = "صوامت قذفية حنجرية: *ṭ [tʼ], *q [kʼ], *ṣ [sʼ], *ṱ [θʼ]",
            linguisticFormula = "Glottalic Ejective [Cʼ] ──[Pharyngeal Shift]──> Pharyngealized Emphatic [Cˤ]",
            relatedLanguages = listOf("الجعزية", "الأمهرية", "التجرينية", "السامية الأم", "الأكادية"),
            epigraphicAttestations = listOf(
                "مسلة الملك عيزانا بالجعزية: الحروف المقطعية القذفية مثل ጠ (ṭa), ቀ (qa), ጸ (ṣa)",
                "الألواح الأكادية المسمارية (صوامت Emphatic المشددة)"
            ),
            comparativeCognates = listOf(
                "الجعزية" to "ቀተለ (qʼatala: قتل بنطق القاف القذفية [kʼ])",
                "العربية" to "قَتَلَ (قاف لهوية مفخمة)",
                "الأكادية" to "qaqqadu (رأس / جمجمة)"
            ),
            searchKeywords = listOf("الصوامت القذفية", "قذف حنجري", "ejectives", "جعزية", "أمهرية", "إطباق", "تفخيم"),
            relatedChapterIds = listOf("chap_6", "chap_16", "chap_26")
        ),
        SemiticGlossaryItem(
            id = "term_theophoric",
            termAr = "الأسماء الثيوفورية (المركبة بأسماء الآلهة)",
            termEn = "Theophoric Names",
            category = GlossaryCategory.ETYMOLOGY,
            academicDefinitionAr = "أسماء أعلام شخصية أو جغرافية سامية تتألف من مركب لغوي يتضمن اسم إله سامي (مثل: إل، بعل، كموش، حدد، يهو، شمش، إلمقه، عثتر). توفر هذه الأسماء ثروة لغوية وتاريخية هائلة لأنها تحفظ تراكيب جمل اسمية وفعلية كاملة تعود للألفين الثالث والثاني قبل الميلاد.",
            protoSemiticBasis = "Sentence-names: [Verb + Divine Name] or [Servant of + Divine Name]",
            linguisticFormula = "Servant/Gift/Created-by + [Theos: ʾIl, Baʿl, Kamoš, ʾAlmaqah]",
            relatedLanguages = listOf("كافة اللغات السامية (أكادية، كنعانية، أوغاريتية، آرامية، سبئية، عربية)"),
            epigraphicAttestations = listOf(
                "نقش أحيرام: 𐤀𐤕𐤁𐤏𐤋 ('itobaʿl = معه الإله بعل)",
                "مسلة ميشع: 𐤌𐤉𐤔𐤏 𐤁𐤍 𐤊𐤌𐤔 (ميشع بن كموش-يت)",
                "نقش صرواح: 𐩫𐩧𐩨𐩱𐩡 (كربئيل = قرّبَ للإله إل / المبارك من إل)"
            ),
            comparativeCognates = listOf(
                "الأكادية" to "Ḫammurapi (حمورابي = الإله حمو شافٍ وعظيم)",
                "الفينيقية" to "Hannibaʿl (حنبعل = نعمة بعل وحنانه)",
                "السبئية" to "Wahabʾil (وهب إل = هبة الله)",
                "العربية" to "عَبْدُ اللهِ / عَبْدُ شَمْسٍ"
            ),
            searchKeywords = listOf("ثيوفورية", "theophoric", "أسماء أعلام", "بعل", "إل", "كموش", "إلمقه"),
            relatedChapterIds = listOf("chap_1", "chap_2", "chap_4", "chap_41")
        ),
        SemiticGlossaryItem(
            id = "term_proto_semitic",
            termAr = "السامية الأم (اللغة السامية البدائية الافتراضية)",
            termEn = "Proto-Semitic",
            category = GlossaryCategory.COMPARATIVE,
            academicDefinitionAr = "اللغة الافتراضية الموحدة التي أعاد علماء الفيلولوجيا بناءها عبر المقارنة التاريخية بين سائر الفروع السامية القديمة. تمتاز بامتلاكها 29 صامتاً، وثلاث حركات قصيرة وثلاث طويلة (a, i, u, ā, ī, ū)، ونظام إعرابي ثلاثي الحالات بالميمية أو النونية، وجذور ثلاثية صرفية متكاملة.",
            protoSemiticBasis = "نظام الـ 29 صامتاً والاشتقاق الداخلي الثلاثي",
            linguisticFormula = "Proto-Semitic ──> East Semitic (Akkadian) + West Semitic (Central, South, Ethiopic)",
            relatedLanguages = listOf("السامية الشرقية", "الشمالية الغربية", "العربية", "الجنوبية القديمة", "الإثيوبية"),
            epigraphicAttestations = listOf(
                "الأوغاريتية والعربية والسبئية هي أكثر اللغات السامية احتفاظاً بحروف السامية الأم التسعة والعشرين."
            ),
            comparativeCognates = listOf(
                "السامية الأم" to "*malku- (مَلِك)",
                "الأكادية" to "malku / šarru",
                "الأوغاريتية" to "mlk (𐎎𐎍𐎋)",
                "السبئية" to "mlk (𐩣𐩡𐩫)",
                "الجعزية" to "nəguś / malak"
            ),
            searchKeywords = listOf("السامية الأم", "proto semitic", "اللغة السامية الأم", "الأصل السامي", "إعادة البناء"),
            relatedChapterIds = listOf("chap_1", "chap_10", "chap_20")
        ),
        SemiticGlossaryItem(
            id = "term_diphthong_contraction",
            termAr = "انكماش المزدوجات الصوتية (Monophthongization)",
            termEn = "Diphthong Contraction",
            category = GlossaryCategory.PHONETICS,
            academicDefinitionAr = "ظاهرة فونولوجية تاريخية تلاشت فيها حركتا اللين المزدوجتان (*ay و *aw) وتحولتا إلى صائتين طويلين بسيطين (*ay > ē و *aw > ō). وقع هذا الانكماش مبكراً في الفينيقية والأكادية والآرامية، بينما حفظت العربية الفصحى والأوغاريتية المزدوجات الصوتية كاملة (بيت، يوم).",
            protoSemiticBasis = "*ay > ē, *aw > ō",
            linguisticFormula = "Proto-Semitic *bayt- ──> Phoenician /bēt/ (𐤁𐤕) | *yawm- ──> Phoenician /yōm/ (𐤉𐤌)",
            relatedLanguages = listOf("الفينيقية", "البونية", "الأكادية", "السريانية", "العبرية القديمة"),
            epigraphicAttestations = listOf(
                "نقش تابوت أحيرام: 𐤁𐤕 (bēt بدلاً من bayt)",
                "نقش كاراتبه: 𐤉𐤌𐤌 (yōmīm: أيام)"
            ),
            comparativeCognates = listOf(
                "العربية" to "بَيْت (مزدوج صوتي تام /ay/)",
                "الفينيقية" to "bēt (انكماش إلى /ē/)",
                "الأكادية" to "bītu (انكماش إلى صائت طويل)",
                "السريانية" to "baytā / bēṯ"
            ),
            searchKeywords = listOf("انكماش المزدوجات", "مزدوجات صوتية", "monophthongization", "diphthong", "بيت", "يوم"),
            relatedChapterIds = listOf("chap_2", "chap_12", "chap_22")
        ),
        SemiticGlossaryItem(
            id = "term_n_assimilation",
            termAr = "سقوط النون الساكنة بالإدغام (N-Assimilation)",
            termEn = "Assimilation of Nun",
            category = GlossaryCategory.PHONETICS,
            academicDefinitionAr = "قانون صوتي سامي يقضي باندماج النون الساكنة بالصامت الذي يليها مباشرة وتحولها إلى تضعيف وتشديد في الصامت التالي (مثل: *yantin > yattin، *min bayt > mib-bayt). ينتشر هذا القانون بصورة قياسية في الأكادية والكنعانية والفينيقية والعبرية، بينما يقل في العربية الجنوبية والجعزية.",
            protoSemiticBasis = "*n + C ──> CC (حيث C صامت)",
            linguisticFormula = "*-nC- ──> -CC- (*yantin ──> yattin)",
            relatedLanguages = listOf("الفينيقية", "الأكادية", "العبرية القديمة", "المؤابية"),
            epigraphicAttestations = listOf(
                "نقش يحيملك بجبيل: 𐤉𐤕𐤍 (yattin من جذر *ntn 'أعطى')",
                "الألواح البابلية: iddin من جذر nadānum (أعطى)"
            ),
            comparativeCognates = listOf(
                "السامية الأم" to "*yantin- (يُعطي)",
                "الفينيقية" to "yattin (𐤉𐤕𐤍)",
                "الأكادية" to "iddin (سقوط النون وإدغامها)",
                "العربية" to "يَنْتِنُ / مَنَحَ (احتفظت بالنون الساكنة في الغالب)"
            ),
            searchKeywords = listOf("سقوط النون", "إدغام النون", "assimilation of nun", "نون ساكنة", "تضعيف"),
            relatedChapterIds = listOf("chap_1", "chap_2", "chap_11")
        ),
        SemiticGlossaryItem(
            id = "term_epigraphy",
            termAr = "علم الإبيغرافيا (قراءة وتحقيق النقوش الأثرية)",
            termEn = "Epigraphy",
            category = GlossaryCategory.EPIGRAPHY,
            academicDefinitionAr = "العلم الآثاري والفيلولوجي المعني باكتشاف، ونسخ، وتوثيق، وفك رموز، ونقد النصوص القديمة المنقوشة أو المحفورة على الخامات الصلبة المعمرة (كالحجارة، والمسلات، والصخور، والبرونز، والجص، وجدران المعابد والقبور). ويعد المصدر الأول والمباشر غير المحرف لمعرفة لغات الشرق الأدنى القديم.",
            protoSemiticBasis = "المصدر الوثائقي المباشر للنصوص دون وساطة النساخ",
            linguisticFormula = "Inscription Artifact ──> Squeeze / High-Res Multi-Spectral ──> Transliteration ──> Philological Analysis",
            relatedLanguages = listOf("كافة اللغات السامية المدونة بالنقوش الصخرية والمسلات"),
            epigraphicAttestations = listOf(
                "مدونة النقوش السامية (Corpus Inscriptionum Semiticarum - CIS)",
                "مجموعة النقوش الكنعانية والآرامية (KAI - Donner & Röllig)",
                "مدونة النقوش العربية الجنوبية (CIH / DASI)"
            ),
            comparativeCognates = listOf(
                "مسلة ميشع" to "القرن 9 ق.م (مؤاب)",
                "مسلة حمورابي" to "القرن 18 ق.م (بابل)",
                "نقش صرواح" to "القرن 7 ق.م (سبأ)"
            ),
            searchKeywords = listOf("إبيغرافيا", "epigraphy", "نقوش", "مسلات", "تحقيق نصوص", "وثائق حجرية"),
            relatedChapterIds = listOf("chap_41", "chap_42", "chap_43", "chap_44")
        ),
        SemiticGlossaryItem(
            id = "term_matres_lectionis",
            termAr = "حروف اللين وأمهات القراءة (Matres Lectionis)",
            termEn = "Matres Lectionis",
            category = GlossaryCategory.EPIGRAPHY,
            academicDefinitionAr = "استخدام الحروف الصامتة الضعيفة (الواو، الياء، الألف، الهاء) للدلالة على الصوائت الطويلة (ū, ī, ā) في الأبجديات السامية غير المشكولة. ظهرت هذه الظاهرة بالتدريج في الآرامية والعبرية القديمة والمؤابية لتسهيل القراءة وتفادي اللبس، بينما حافظت الفينيقية القديمة على الكتابة الصامتية الصرفة الخالية من أمهات القراءة.",
            protoSemiticBasis = "تطور خطي لتسجيل الصوائت دون نظام حركات كامل",
            linguisticFormula = "Consonantal Sign (ʾ, w, y, h) ──> Vowel Indicator (/ā/, /ū/, /ī/)",
            relatedLanguages = listOf("الآرامية", "العبرية القديمة", "المؤابية", "النبطية", "العربية"),
            epigraphicAttestations = listOf(
                "مسلة ميشع: استخدام الياء والواو كأمهات قراءة في أواخر الكلمات (𐤊𐤉، 𐤁𐤍𐤉)",
                "البرديات الآرامية الفيلية: التدوين المنتظم للصوائت النهائية"
            ),
            comparativeCognates = listOf(
                "الفينيقية" to "كتابة صامتية محضة (𐤌𐤋𐤊 = ملك، مَلَكَ، مُلْك)",
                "الآرامية" to "استخدام الواو والياء كحروف مد مساعدة"
            ),
            searchKeywords = listOf("أمهات القراءة", "حروف اللين", "matres lectionis", "صوائت", "تدوين الحركات"),
            relatedChapterIds = listOf("chap_3", "chap_13", "chap_43")
        ),
        SemiticGlossaryItem(
            id = "term_ostraca",
            termAr = "الأوستراكا (الشقفات الفخارية التدوينية)",
            termEn = "Ostraca",
            category = GlossaryCategory.EPIGRAPHY,
            academicDefinitionAr = "شقفات وكسرات الفخار أو الخزف التي استخدمها الكتبة القدماء كمادة رخيصة ومتاحة لكتابة الرسائل الإدارية العاجلة، وقوائم التموين العسكري، والوصولات التجارية، والمذكرات القضائية اليومية بالحبر الأسود. تمثل شقفات السامرة وشقفات لخيش وشقفات عراد أثمن شواهد النثر الكنعاني والآرامي الحي.",
            protoSemiticBasis = "مادة تدوين سريعة للحياة اليومية بخلاف الحجر التذكاري",
            linguisticFormula = "Pottery Sherd + Carbon Ink + Reed Pen ──> Epigraphic Document",
            relatedLanguages = listOf("الكنعانية", "العبرية القديمة", "الآرامية", "النبطية"),
            epigraphicAttestations = listOf(
                "رسائل شقفات لخيش (Lachish Ostraca - 588 ق.م): رسائل القائد العسكري هوشعيا",
                "شقفات السامرة (Samaria Ostraca - القرن 8 ق.م): وصولات شحنات الزيت والنبيذ"
            ),
            comparativeCognates = listOf(
                "شقفات لخيش" to "وثائق حربية ومراقبة إشارات النار",
                "شقفات السامرة" to "سجلات ضريبية كنعانية"
            ),
            searchKeywords = listOf("أوستراكا", "ostraca", "شقفات فخارية", "لخيش", "السامرة", "رسائل بالحبر"),
            relatedChapterIds = listOf("chap_2", "chap_42")
        ),
        SemiticGlossaryItem(
            id = "term_fidel_abugida",
            termAr = "الفيدل (الكتابة الأبوجيدية المقطعية الحبشية)",
            termEn = "Ge'ez Fidel Abugida",
            category = GlossaryCategory.EPIGRAPHY,
            academicDefinitionAr = "نظام كتابة مقطعي إثيوبي (Abugida) مشتق أصلاً من خط المسند العربي الجنوبي. في هذا النظام، يمثل كل حرف صامتاً متصلاً بحركة علة تتغير نغمياً من خلال إضافة زوائد أو حلقات أو انحناءات على جسم الحرف في سبع رتب صوتية منتظمة (الفتحة، الضمة، الكسرة، الألف، الياء، السكون، الواو).",
            protoSemiticBasis = "تحوير خط المسند إلى نظام علام مقطعي دقيق",
            linguisticFormula = "Consonant Base + Diacritic Modifiers (7 Vocalic Orders: ግዕዝ, ካዕብ, ሣልስ, ራብዕ, ኃምስ, ሳድስ, ሳብዕ)",
            relatedLanguages = listOf("الجعزية", "الأمهرية", "التجرينية", "التجرية"),
            epigraphicAttestations = listOf(
                "نقوش الملك عيزانا الأكسومي (القرن الرابع الميلادي): أول استخدام للنظام المقطعي المشكول",
                "مخطوطة أناجيل أبا غاريما (أقدم مخطوطة إنجيلية مذهبة بإفريقيا)"
            ),
            comparativeCognates = listOf(
                "المسند اليمني" to "𐩠 (حرف الهاء الصامت)",
                "الفيدل الجعزي" to "ሀ (hä), ሁ (hu), ሂ (hi), ሃ (ha), ሄ (he), ህ (hə), ሆ (ho)"
            ),
            searchKeywords = listOf("الفيدل", "fidel", "abugida", "أبوجيدا", "كتابة مقطعية", "جعزية", "أكسوم"),
            relatedChapterIds = listOf("chap_6", "chap_16", "chap_46")
        ),
        SemiticGlossaryItem(
            id = "term_verbal_stems",
            termAr = "الجذوع والأوزان الفعلية (Binyanim / Stems)",
            termEn = "Verbal Stems (Binyanim)",
            category = GlossaryCategory.MORPHOLOGY,
            academicDefinitionAr = "المنظومة الصرفية السامية لاشتقاق معانٍ متعددة من جذر الفعل الثلاثي بواسطة أوزان قياسية: الجذع المجرد (G/Qal)، المكثف بتضعيف عين الفعل (D/Piel/فَعَّلَ)، السببي بالبادئة الشينية أو الهائية أو الهمزية (C/Š/H/ʾ/أَفْعَلَ)، والمطاوع بالنون (N/Niphal/انْفَعَلَ)، والأوزان التائية الانعكاسية (t-stems/تَفَعَّلَ وافْتَعَلَ).",
            protoSemiticBasis = "نظام الأوزان الخمسة الكبرى: G, D, C, N, t",
            linguisticFormula = "Root √CCC ──> G (qatala), D (qattala), C (šaqtala / haqtal / ʾaqtala), N (naqtala)",
            relatedLanguages = listOf("كافة اللغات السامية"),
            epigraphicAttestations = listOf(
                "الأكادية: الجذوع الأربعة الأساسية (G, D, Š, N) مع أوزان tn",
                "الفينيقية والمؤابية: وزن Yiphil / Hiphil السببي بالهاء أو الياء (𐤄𐤐𐤏𐤋)"
            ),
            comparativeCognates = listOf(
                "السببي بالأكادية" to "ušapris (بالشين: Š-stem)",
                "السببي بالعبرية" to "hiqṭīl (بالهاء: H-stem)",
                "السببي بالفينيقية" to "yiqṭīl (بالياء: Y-stem)",
                "السببي بالعربية" to "أَفْعَلَ (بالهمزة: ʾ-stem)",
                "السببي بالآرامية" to "ʾap̄ʿel / hap̄ʿel"
            ),
            searchKeywords = listOf("الجذوع الفعلية", "الأوزان", "binyanim", "verbal stems", "أفعال", "اشتقاق", "صرف"),
            relatedChapterIds = listOf("chap_1", "chap_2", "chap_3", "chap_21", "chap_23")
        )
    )

    /**
     * Finds a glossary item matching a word or query (exact or partial keyword match).
     */
    fun findTerm(query: String): SemiticGlossaryItem? {
        val clean = query.trim().lowercase()
        if (clean.isBlank()) return null

        // 1. Direct ID match
        GLOSSARY_ITEMS.find { it.id.equals(clean, ignoreCase = true) }?.let { return it }

        // 2. Direct Arabic or English term match
        GLOSSARY_ITEMS.find {
            it.termAr.contains(clean, ignoreCase = true) ||
            it.termEn.contains(clean, ignoreCase = true)
        }?.let { return it }

        // 3. Keyword match
        GLOSSARY_ITEMS.find { item ->
            item.searchKeywords.any { kw ->
                clean.contains(kw, ignoreCase = true) || kw.contains(clean, ignoreCase = true)
            }
        }?.let { return it }

        // 4. In-formula or in-definition match
        GLOSSARY_ITEMS.find {
            it.linguisticFormula.contains(clean, ignoreCase = true) ||
            it.protoSemiticBasis.contains(clean, ignoreCase = true)
        }?.let { return it }

        return null
    }

    /**
     * Search and filter glossary items by query and category.
     */
    fun searchTerms(query: String, category: GlossaryCategory? = null): List<SemiticGlossaryItem> {
        val clean = query.trim().lowercase()
        return GLOSSARY_ITEMS.filter { item ->
            val matchesCategory = category == null || item.category == category
            val matchesQuery = clean.isBlank() ||
                    item.termAr.contains(clean, ignoreCase = true) ||
                    item.termEn.contains(clean, ignoreCase = true) ||
                    item.academicDefinitionAr.contains(clean, ignoreCase = true) ||
                    item.searchKeywords.any { it.contains(clean, ignoreCase = true) } ||
                    item.relatedLanguages.any { it.contains(clean, ignoreCase = true) }
            matchesCategory && matchesQuery
        }
    }
}
