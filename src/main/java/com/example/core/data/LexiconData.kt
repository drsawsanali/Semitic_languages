package com.example.core.data

import com.example.core.model.*

object LexiconData {
    val COMPARATIVE_ROOTS: List<LexiconRoot> = listOf(
        LexiconRoot(
            protoSemiticRoot = "*m-l-k",
            reconstructedForm = "*malku- / *malik-",
            englishMeaning = "King, Ruler, Sovereignty",
            arabicMeaning = "مَلِك، حُكْم، سِيَادَة",
            formsByLanguage = mapOf(
                "akkadian" to LanguageForm(
                    scriptForm = "𒈗 / 𒈠𒀠𒆪",
                    transliteration = "malku / šarru",
                    ipaPhonetic = "/ˈmalku/",
                    notes = "استخدمت مألكو كمرادف للشريف أو الحاكم الفرعي، والكلمة الأساسية شَرّو"
                ),
                "ugaritic" to LanguageForm(
                    scriptForm = "𐎎𐎍𐎋",
                    transliteration = "malku",
                    ipaPhonetic = "/ˈmalku/",
                    notes = "تأتي بصيغة الفاعل والاسم ملك (جمع: ملوكم m-l-k-m)"
                ),
                "phoenician" to LanguageForm(
                    scriptForm = "𐤌𐤋𐤊",
                    transliteration = "milk / milkot",
                    ipaPhonetic = "/milk/",
                    notes = "تأثرت بالكسر الفينيقي وتحول حركة الفتحة إلى كسرة قصيرة"
                ),
                "moabite" to LanguageForm(
                    scriptForm = "𐤌𐤋𐤊",
                    transliteration = "mālak / melek",
                    ipaPhonetic = "/ˈmɛlɛk/",
                    notes = "وردت في نقش ميشع بصيغة الاسم والفعل: ʾnk mšʿ mlk mʾb"
                ),
                "old-aramaic" to LanguageForm(
                    scriptForm = "𐡌𐡋𐡊𐡀",
                    transliteration = "malkā",
                    ipaPhonetic = "/malˈkaː/",
                    notes = "مع أداة التعريف اللاحقة بالألف الممدودة"
                ),
                "classical-arabic" to LanguageForm(
                    scriptForm = "مَلِك / مَلَكَ",
                    transliteration = "malik / malaka",
                    ipaPhonetic = "/maˈlik/",
                    notes = "حفظ كامل للحركات الثلاث والاشتقاق الواسع (مملكة، ملوك، ملاك)"
                ),
                "sabaic" to LanguageForm(
                    scriptForm = "𐩣𐩡𐩫𐩬",
                    transliteration = "mlk-n (malkān)",
                    ipaPhonetic = "/malˈkaːn/",
                    notes = "مع أداة التعريف المسندية بالنون اللاحقة"
                ),
                "geez" to LanguageForm(
                    scriptForm = "መልከ፡ / ንጉሥ",
                    transliteration = "malkəʾ / nəguś",
                    ipaPhonetic = "/malkəʔ/",
                    notes = "في الجعزية تعني مظهر/هيئة/ملك، واستخدمت نِغوش للدلالة على الإمبراطور"
                ),
                "mehri" to LanguageForm(
                    scriptForm = "مَلِك / حَاكِم",
                    transliteration = "melēk",
                    ipaPhonetic = "/məˈleːk/",
                    notes = "نطق مهرِي بصائت ممدود مميز"
                )
            ),
            etymologicalDevelopment = "الجذر السامي الأم *m-l-k ارتبط منذ الألف الثالثة ق.م بمفهوم المشورة والتملك وإدارة شؤون القبيلة والمدينة، وتفرع في السامية الشمالية الغربية والجنوبية ليدل على أعلى سلطة سياسية (الملك)."
        ),
        LexiconRoot(
            protoSemiticRoot = "*b-y-t",
            reconstructedForm = "*baytu-",
            englishMeaning = "House, Temple, Dynasty",
            arabicMeaning = "بَيْت، دَار، مَعْبَد، سُلَالَة",
            formsByLanguage = mapOf(
                "akkadian" to LanguageForm(
                    scriptForm = "𒂍 / 𒁉𒄿𒌈",
                    transliteration = "bītum",
                    ipaPhonetic = "/ˈbiːtum/",
                    notes = "انكماش المزدوج الصوتي *ay > ī مع الميمية -um"
                ),
                "ugaritic" to LanguageForm(
                    scriptForm = "𐎁𐎊𐎚",
                    transliteration = "bētu / baytu",
                    ipaPhonetic = "/ˈbeːtu/",
                    notes = "بقاء الياء في الرسم مع نطقها صائتاً طويلاً ē"
                ),
                "phoenician" to LanguageForm(
                    scriptForm = "𐤁𐤕",
                    transliteration = "bēt",
                    ipaPhonetic = "/beːt/",
                    notes = "انكماش كامل وسقوط الياء كتابةً ونطقاً (𐤁𐤕 b-t)"
                ),
                "old-aramaic" to LanguageForm(
                    scriptForm = "𐡁𐡉𐡕𐡀",
                    transliteration = "baytā / bētā",
                    ipaPhonetic = "/beːˈtaː/",
                    notes = "استخدمت للبيت والأسرة الحاكمة مثل (بيت دافيد / بيت عمري)"
                ),
                "classical-arabic" to LanguageForm(
                    scriptForm = "بَيْت",
                    transliteration = "bayt",
                    ipaPhonetic = "/bayt/",
                    notes = "المحافظة التامة على المزدوج الصوتي الأصيل (ay)"
                ),
                "sabaic" to LanguageForm(
                    scriptForm = "𐩨𐩺𐩩𐩬",
                    transliteration = "byt-n (baytān)",
                    ipaPhonetic = "/bayˈtaːn/",
                    notes = "استخدمت للقصر والمعبد والدار المشيدة"
                ),
                "geez" to LanguageForm(
                    scriptForm = "ቤት፡",
                    transliteration = "bēt",
                    ipaPhonetic = "/beːt/",
                    notes = "انكماش المزدوج إلى ē (بِيت كرستيان: كنيسة)"
                )
            ),
            etymologicalDevelopment = "جذر سامي كوني أصيل يعبر عن المأوى والاستقرار، أطلقت علامته الهيروغليفية الأولية (مخطط البيت) كرمز أكروفوني لحرف الباء في كافة الأبجديات السامية والعالمية."
        ),
        LexiconRoot(
            protoSemiticRoot = "*š-l-m",
            reconstructedForm = "*śalāmu- / *šalāmu-",
            englishMeaning = "Peace, Wholeness, Well-being",
            arabicMeaning = "سَلَام، صِحَّة، كَمَال، أَمَان",
            formsByLanguage = mapOf(
                "akkadian" to LanguageForm(
                    scriptForm = "𒁲 / 𒊭𒆷𒈬𒌝",
                    transliteration = "šalāmum",
                    ipaPhonetic = "/ʃaˈlaːmum/",
                    notes = "التحول الصوتي للشين الأكادية"
                ),
                "ugaritic" to LanguageForm(
                    scriptForm = "𐎌𐎍𐎎",
                    transliteration = "šalāmu",
                    ipaPhonetic = "/ʃaˈlaːmu/",
                    notes = "صيغة التحية الدبلوماسية في الرسائل الأوغاريتية"
                ),
                "phoenician" to LanguageForm(
                    scriptForm = "𐤔𐤋𐤌",
                    transliteration = "šalōm / šilēm",
                    ipaPhonetic = "/ʃaˈloːm/",
                    notes = "خضوع حركة الألف للتحول الكنعاني (*ā > ō)"
                ),
                "moabite" to LanguageForm(
                    scriptForm = "𐤔𐤋𐤌",
                    transliteration = "šalōm",
                    ipaPhonetic = "/ʃaˈloːm/",
                    notes = "تكررت في صيغ التهنئة والمعاهدات"
                ),
                "old-aramaic" to LanguageForm(
                    scriptForm = "𐡔𐡋𐡌",
                    transliteration = "šəlāmā",
                    ipaPhonetic = "/ʃəlaːˈmaː/",
                    notes = "أصبحت تحية الشرق الأوسط الرسمية في العصر الأخميني"
                ),
                "classical-arabic" to LanguageForm(
                    scriptForm = "سَلَام / سِلْم",
                    transliteration = "salām / silm",
                    ipaPhonetic = "/saˈlaːm/",
                    notes = "حفظ كامل للصوت الصامت والمد الأصلي"
                ),
                "sabaic" to LanguageForm(
                    scriptForm = "𐩪𐩡𐩣",
                    transliteration = "s¹lm (salām)",
                    ipaPhonetic = "/saˈlaːm/",
                    notes = "بالسين الأولى s¹"
                ),
                "geez" to LanguageForm(
                    scriptForm = "ሰላም፡",
                    transliteration = "salām",
                    ipaPhonetic = "/saˈlaːm/",
                    notes = "تحية السلام الكنسية واليومية في إثيوبيا وإريتريا"
                )
            ),
            etymologicalDevelopment = "يمثل هذا الجذر أقدس وأعرق تحية إنسانية مشتركة في كافة فروع العائلة السامية بدون استثناء، ويعبر عن الخلوص من العيوب والوئام الاجتماعي."
        ),
        LexiconRoot(
            protoSemiticRoot = "*ʾ-l-h",
            reconstructedForm = "*ʾilahu- / *ʾilu-",
            englishMeaning = "God, Deity, Divine",
            arabicMeaning = "إِلَه، مَعْبُود، قُوَّة عُلْيَا",
            formsByLanguage = mapOf(
                "akkadian" to LanguageForm(
                    scriptForm = "𒀭 / 𒄿𒈝",
                    transliteration = "ilum",
                    ipaPhonetic = "/ˈʔilum/",
                    notes = "رمز النجمة المسمارية (Dingir) للدلالة على الألوهية"
                ),
                "ugaritic" to LanguageForm(
                    scriptForm = "𐎛𐎍 / 𐎛𐎍𐎅",
                    transliteration = "ʾil / ʾilah",
                    ipaPhonetic = "/ʔil/",
                    notes = "إيل كبير مجمع الآلهة وأبو البشر والآلهة برأس الشمرا"
                ),
                "phoenician" to LanguageForm(
                    scriptForm = "𐤀𐤋 / 𐤀𐤋𐤍𐤌",
                    transliteration = "ʾēl / ʾalōnim",
                    ipaPhonetic = "/ʔeːl/",
                    notes = "إيل والجمع ألونيم (مجمع الآلهة الفينيقية)"
                ),
                "old-aramaic" to LanguageForm(
                    scriptForm = "𐡀𐡋𐡄𐡀",
                    transliteration = "ʾelāhā",
                    ipaPhonetic = "/ʔɛlaːˈhaː/",
                    notes = "أصل التسمية السريانية والآرامية لله تعالى"
                ),
                "classical-arabic" to LanguageForm(
                    scriptForm = "إِلَه / اللَّه",
                    transliteration = "ʾilāh / Allāh",
                    ipaPhonetic = "/ʔiˈlaːh/",
                    notes = "أصلها الإله ثم خففت وحذفت همزتها لكثرة الاستعمال"
                ),
                "sabaic" to LanguageForm(
                    scriptForm = "𐩱𐩡𐩠𐩬 / 𐩱𐩡",
                    transliteration = "ʾlh-n / ʾl",
                    ipaPhonetic = "/ʔilaːˈhaːn/",
                    notes = "وردت في الأسماء المركبة مثل (وهب إيل، كرب إيل)"
                ),
                "geez" to LanguageForm(
                    scriptForm = "አምላክ፡ / እግዚአብሔር",
                    transliteration = "ʾamlāk / ʾƎgzīʾabḥēr",
                    ipaPhonetic = "/ʔamˈlaːk/",
                    notes = "استخدمت صيغة جمع التكسير (أملاك) كاسم مفرد لله تعالى"
                )
            ),
            etymologicalDevelopment = "الجذر المشترك لكل معاني القداسة والتعالي في العالم السامي القديم، وتطور تدريجياً من تسمية كبار الآلهة في الأساطير إلى التوحيد الإبراهيمي الخالص."
        )
    )
}
