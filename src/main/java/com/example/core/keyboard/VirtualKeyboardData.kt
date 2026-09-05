package com.example.core.keyboard

import com.example.core.model.ScriptType

data class VirtualKey(
    val glyph: String,
    val arabicEquivalent: String,
    val ipaSymbol: String,
    val name: String,
    val approximateFrequencyHz: Int = 440
) {
    val ipa: String get() = ipaSymbol
}

typealias ScriptGlyph = VirtualKey

data class ScriptKeyboardLayout(
    val scriptType: ScriptType,
    val titleAr: String,
    val titleEn: String,
    val keys: List<VirtualKey>,
    val presets: List<PresetInscriptionSnippet>
) {
    val scriptNameAr: String get() = titleAr
    val glyphs: List<VirtualKey> get() = keys
}

data class PresetInscriptionSnippet(
    val labelAr: String,
    val originalText: String,
    val transliteration: String,
    val translationAr: String
)

object VirtualKeyboardData {
    val PHOENICIAN_LAYOUT = ScriptKeyboardLayout(
        scriptType = ScriptType.PHOENICIAN_LINEAR,
        titleAr = "الأبجدية الفينيقية والكنعانية القديمة (22 حرفاً)",
        titleEn = "Phoenician & Old Canaanite Linear Script",
        keys = listOf(
            VirtualKey("𐤀", "أ", "ʔ", "ألف (ʾalp)", 440),
            VirtualKey("𐤁", "ب", "b", "بيت (bēt)", 466),
            VirtualKey("𐤂", "ج", "g", "جمل (gaml)", 493),
            VirtualKey("𐤃", "د", "d", "دلت (dalt)", 523),
            VirtualKey("𐤄", "هـ", "h", "هي (hē)", 554),
            VirtualKey("𐤅", "و", "w", "واو (wāw)", 587),
            VirtualKey("𐤆", "ز", "z", "زين (zēn)", 622),
            VirtualKey("𐤇", "ح", "ħ", "حيت (ḥēt)", 659),
            VirtualKey("𐤈", "ط", "tˤ", "طيت (ṭēt)", 698),
            VirtualKey("𐤉", "ي", "j", "يود (yōd)", 740),
            VirtualKey("𐤊", "ك", "k", "كف (kap)", 784),
            VirtualKey("𐤋", "ل", "l", "لمد (lamd)", 830),
            VirtualKey("𐤌", "م", "m", "ميم (mēm)", 880),
            VirtualKey("𐤍", "ن", "n", "نون (nūn)", 932),
            VirtualKey("𐤎", "س", "s", "سمك (samk)", 987),
            VirtualKey("𐤏", "ع", "ʕ", "عين (ʿēn)", 1046),
            VirtualKey("𐤐", "ف", "p", "بي (pē)", 1108),
            VirtualKey("𐤑", "ص", "sˤ", "صادي (ṣādē)", 1174),
            VirtualKey("𐤒", "ق", "q", "قوف (qōp)", 1244),
            VirtualKey("𐤓", "ر", "r", "روش (rōš)", 1318),
            VirtualKey("𐤔", "ش", "ʃ", "شين (šīn)", 1396),
            VirtualKey("𐤕", "ت", "t", "تاو (tāw)", 1480),
            VirtualKey("𐤟", "•", "-", "فاصل نقش كنعاني", 440)
        ),
        presets = listOf(
            PresetInscriptionSnippet(
                labelAr = "فاتحة تابوت أحيرام (جبيل)",
                originalText = "𐤀𐤓𐤍 𐤆 𐤐𐤏𐤋 𐤀𐤕𐤁𐤏𐤋 𐤁𐤍 𐤀𐤇𐤓𐤌 𐤌𐤋𐤊 𐤂𐤁𐤋",
                transliteration = "ʾrn z pʿl ʾtbʿl bn ʾḥrm mlk gbl",
                translationAr = "هذا التابوت صنعه إيتوبعل بن أحيرام ملك جبيل"
            ),
            PresetInscriptionSnippet(
                labelAr = "فاتحة مسلة ميشع (مؤاب)",
                originalText = "𐤀𐤍𐤊 𐤌𐤔𐤏 𐤁𐤍 𐤊𐤌𐤔𐤉𐤕 𐤌𐤋𐤊 𐤌𐤀𐤁",
                transliteration = "ʾnk mšʿ bn kmšyt mlk mʾb",
                translationAr = "أنا ميشع بن كموشيت ملك مؤاب الديباني"
            )
        )
    )

    val UGARITIC_LAYOUT = ScriptKeyboardLayout(
        scriptType = ScriptType.UGARITIC_CUNEIFORM,
        titleAr = "المسمارية الأوغاريتية الأبجدية (30 حرفاً)",
        titleEn = "Ugaritic Cuneiform Alphabet",
        keys = listOf(
            VirtualKey("𐎀", "أَ", "ʔa", "ألف بالفتحة", 440),
            VirtualKey("𐎁", "ب", "b", "بيتا", 466),
            VirtualKey("𐎂", "ج", "g", "جامل", 493),
            VirtualKey("𐎃", "خ", "x", "خا", 523),
            VirtualKey("𐎄", "د", "d", "ديلت", 554),
            VirtualKey("𐎅", "هـ", "h", "هو", 587),
            VirtualKey("𐎆", "و", "w", "واو", 622),
            VirtualKey("𐎇", "ز", "z", "زين", 659),
            VirtualKey("𐎈", "ح", "ħ", "حوتا", 698),
            VirtualKey("𐎉", "ط", "tˤ", "طيت", 740),
            VirtualKey("𐎊", "ي", "j", "يود", 784),
            VirtualKey("𐎋", "ك", "k", "كاف", 830),
            VirtualKey("𐎌", "ش", "ʃ", "شين", 880),
            VirtualKey("𐎍", "ل", "l", "لابدا", 932),
            VirtualKey("𐎎", "م", "m", "ميم", 987),
            VirtualKey("𐎏", "ذ", "ð", "ذال", 1046),
            VirtualKey("𐎐", "ن", "n", "نون", 1108),
            VirtualKey("𐎑", "ظ", "ðˤ", "ظاء", 1174),
            VirtualKey("𐎒", "س", "s", "سامكا", 1244),
            VirtualKey("𐎓", "ع", "ʕ", "عين", 1318),
            VirtualKey("𐎔", "ف", "p", "بو", 1396),
            VirtualKey("𐎕", "ص", "sˤ", "صاد", 1480),
            VirtualKey("𐎖", "ق", "q", "قوف", 1567),
            VirtualKey("𐎗", "ر", "r", "راشا", 1661),
            VirtualKey("𐎘", "ث", "θ", "ثانا", 1760),
            VirtualKey("𐎙", "غ", "ɣ", "غاين", 1864),
            VirtualKey("𐎚", "ت", "t", "تو", 1975),
            VirtualKey("𐎛", "إِ", "ʔi", "ألف بالكسرة", 2093),
            VirtualKey("𐎜", "أُ", "ʔu", "ألف بالضمة", 2217),
            VirtualKey("𐎝", "س2", "s", "سين ثانوية", 2349),
            VirtualKey("𐎟", "•", "-", "فاصل مسماري", 440)
        ),
        presets = listOf(
            PresetInscriptionSnippet(
                labelAr = "افتتاحية ملحمة بعل برأس الشمرا",
                originalText = "𐎍𐎁𐎓𐎍 𐎊𐎈𐎔𐎍 𐎋𐎐𐎔 𐎐𐎌𐎗 𐎋𐎐𐎔 𐎐𐎌𐎗 𐎁𐎓𐎍 𐎊𐎉𐎁𐎗",
                transliteration = "l-ba'li yaḥpulu kanapa našri",
                translationAr = "لبعل يكسر جناح النسر المعتدي"
            )
        )
    )

    val MUSNAD_LAYOUT = ScriptKeyboardLayout(
        scriptType = ScriptType.MUSNAD,
        titleAr = "خط المسند العربي الجنوبي الأصيل (29 حرفاً)",
        titleEn = "Ancient South Arabian Musnad Script",
        keys = listOf(
            VirtualKey("𐩠", "هـ", "h", "هاء", 440),
            VirtualKey("𐩡", "ل", "l", "لام", 466),
            VirtualKey("𐩢", "ح", "ħ", "حاء", 493),
            VirtualKey("𐩣", "م", "m", "ميم", 523),
            VirtualKey("𐩤", "ق", "q", "قاف", 554),
            VirtualKey("𐩥", "و", "w", "واو", 587),
            VirtualKey("𐩦", "ش", "ɬ", "شين (س2)", 622),
            VirtualKey("𐩧", "ر", "r", "راء", 659),
            VirtualKey("𐩨", "ب", "b", "باء", 698),
            VirtualKey("𐩩", "ت", "t", "تاء", 740),
            VirtualKey("𐩪", "س", "s", "سين (س1)", 784),
            VirtualKey("𐩫", "ك", "k", "كاف", 830),
            VirtualKey("𐩬", "ن", "n", "نون", 880),
            VirtualKey("𐩭", "خ", "x", "خاء", 932),
            VirtualKey("𐩮", "ص", "sˤ", "صاد", 987),
            VirtualKey("𐩯", "س3", "s", "سامك (س3)", 1046),
            VirtualKey("𐩰", "ف", "f", "فاء", 1108),
            VirtualKey("𐩱", "أ", "ʔ", "ألف", 1174),
            VirtualKey("𐩲", "ع", "ʕ", "عين", 1244),
            VirtualKey("𐩳", "ض", "ɮˤ", "ضاد مسندية", 1318),
            VirtualKey("𐩴", "ج", "g", "جيم", 1396),
            VirtualKey("𐩵", "د", "d", "دال", 1480),
            VirtualKey("𐩶", "غ", "ɣ", "غين", 1567),
            VirtualKey("𐩷", "ط", "tˤ", "طاء", 1661),
            VirtualKey("𐩸", "ز", "z", "زاي", 1760),
            VirtualKey("𐩹", "ذ", "ð", "ذال", 1864),
            VirtualKey("𐩺", "ي", "j", "ياء", 1975),
            VirtualKey("𐩻", "ث", "θ", "ثاء", 2093),
            VirtualKey("𐩼", "ظ", "ðˤ", "ظاء", 2217),
            VirtualKey("𐩽", "|", "-", "فاصل مسند عمودي", 440)
        ),
        presets = listOf(
            PresetInscriptionSnippet(
                labelAr = "دعاء نذر سبئي (مأرب)",
                originalText = "𐩪𐩨𐩱 𐩥𐩡𐩠𐩺𐩲𐩻𐩩𐩧 𐩥𐩱𐩡𐩣𐩤𐩠 𐩥𐩹𐩩 𐩢𐩣𐩺𐩣",
                transliteration = "sbʾ w-l-hyʿṯtr w-ʾlmqh w-ḏt ḥmym",
                translationAr = "سبأ، وبعون عثتر وإلمقه وذات حميم"
            )
        )
    )

    val GEEZ_LAYOUT = ScriptKeyboardLayout(
        scriptType = ScriptType.GEEZ_FIDEL,
        titleAr = "الخط الجعزي الإثيوبي (الفيدل)",
        titleEn = "Ge'ez Fidel Ethiopic Script",
        keys = listOf(
            VirtualKey("ሀ", "هـ", "hä", "ሆይ", 440),
            VirtualKey("ለ", "ل", "lä", "ላዊ", 466),
            VirtualKey("ሐ", "ح", "ḥä", "ሐውት", 493),
            VirtualKey("መ", "م", "mä", "ማይ", 523),
            VirtualKey("ሠ", "ش", "śä", "ሠውት", 554),
            VirtualKey("ረ", "ر", "rä", "ርእስ", 587),
            VirtualKey("ሰ", "س", "sä", "ሳት", 622),
            VirtualKey("ቀ", "ق", "qʼä", "ቃፍ", 659),
            VirtualKey("በ", "ب", "bä", "ቤት", 698),
            VirtualKey("ተ", "ت", "tä", "ታው", 740),
            VirtualKey("ኀ", "خ", "ḫä", "ኀርም", 784),
            VirtualKey("ነ", "ن", "nä", "ናስ", 830),
            VirtualKey("አ", "أ", "ʾä", "አልፍ", 880),
            VirtualKey("ከ", "ك", "kä", "ካፍ", 932),
            VirtualKey("ወ", "و", "wä", "ዋዌ", 987),
            VirtualKey("ዐ", "ع", "ʿä", "ዐይን", 1046),
            VirtualKey("ዘ", "ز", "zä", "Protocol", 1108),
            VirtualKey("የ", "ي", "yä", "የመን", 1174),
            VirtualKey("ደ", "د", "dä", "ድንት", 1244),
            VirtualKey("ገ", "ج", "gä", "ገምል", 1318),
            VirtualKey("ጠ", "ط", "tʼä", "ጠይት", 1396),
            VirtualKey("ጰ", "پ", "pʼä", "ጰይት", 1480),
            VirtualKey("ጸ", "ص", "sʼä", "ጸደይ", 1567),
            VirtualKey("ፈ", "ف", "fä", "አፍ", 1661),
            VirtualKey("ፐ", "پ", "pä", "ፔ", 1760),
            VirtualKey("፡", ":", "-", "فاصل جعزي بنقطتين", 440),
            VirtualKey("።", "::", "-", "نقطة ختام جعزية بأربع نقاط", 440)
        ),
        presets = listOf(
            PresetInscriptionSnippet(
                labelAr = "فاتحة إنجيل يوحنا بالجعزية",
                originalText = "ቀዳሚሁ፡ ቃል፡ ውእቱ፡ ወውእቱ፡ ቃል፡ ኀበ፡ እግዚአብሔር",
                transliteration = "Qadāmīhu Qāl wəʾətu...",
                translationAr = "في البدء كان الكلمة، والكلمة كان عند الله"
            )
        )
    )

    val ARAMAIC_LAYOUT = ScriptKeyboardLayout(
        scriptType = ScriptType.IMPERIAL_ARAMAIC,
        titleAr = "الخط الآرامي الإمبراطوري (22 حرفاً)",
        titleEn = "Imperial Aramaic Script",
        keys = listOf(
            VirtualKey("𐡀", "أ", "ʔ", "آلف", 440),
            VirtualKey("𐡁", "ب", "b", "بيت", 466),
            VirtualKey("𐡂", "ج", "g", "جيمل", 493),
            VirtualKey("𐡃", "د", "d", "دالت", 523),
            VirtualKey("𐡄", "هـ", "h", "هي", 554),
            VirtualKey("𐡅", "و", "w", "واو", 587),
            VirtualKey("𐡆", "ز", "z", "زاين", 622),
            VirtualKey("𐡇", "ح", "ħ", "حيث", 659),
            VirtualKey("𐡈", "ط", "tˤ", "طيث", 698),
            VirtualKey("𐡉", "ي", "j", "يود", 740),
            VirtualKey("𐡊", "ك", "k", "كاف", 784),
            VirtualKey("𐡋", "ل", "l", "لامد", 830),
            VirtualKey("𐡌", "م", "m", "ميم", 880),
            VirtualKey("𐡍", "ن", "n", "نون", 932),
            VirtualKey("𐡎", "س", "s", "سمخ", 987),
            VirtualKey("𐡏", "ع", "ʕ", "عين", 1046),
            VirtualKey("𐡐", "ف", "p", "في", 1108),
            VirtualKey("𐡑", "ص", "sˤ", "صادي", 1174),
            VirtualKey("𐡒", "ق", "q", "قوف", 1244),
            VirtualKey("𐡓", "ر", "r", "ريش", 1318),
            VirtualKey("𐡔", "ش", "ʃ", "شين", 1396),
            VirtualKey("𐡕", "ت", "t", "تاو", 1480)
        ),
        presets = listOf(
            PresetInscriptionSnippet(
                labelAr = "نقش زكير ملك حماة الآرامي",
                originalText = "𐡀𐡍𐡄 𐡆𐡊𐡓 𐡌𐡋𐡊 𐡇𐡌𐡕 𐡅𐡋𐡏𐡔 𐡀𐡉𐡔 𐡏𐡍𐡄 𐡀𐡍𐡄",
                transliteration = "ʾnh zkr mlk ḥmt w-lʿš ʾyš ʿnh ʾnh",
                translationAr = "أنا زكير ملك حماة ولعش، رجل وضيع كنت فنصرني بعل شمين"
            ),
            PresetInscriptionSnippet(
                labelAr = "برديات إلفنتين الآرامية",
                originalText = "𐡔𐡋𐡌 𐡌𐡓𐡀𐡍 𐡉𐡄𐡅 𐡉𐡔𐡀𐡋 𐡁𐡊𐡋 𐡏𐡃𐡍",
                transliteration = "šlm mrʾn YHW yšʾl b-kl ʿdn",
                translationAr = "سلام سيدنا؛ ليسأل الرب عن سلامك في كل أوان"
            )
        )
    )

    val SYRIAC_LAYOUT = ScriptKeyboardLayout(
        scriptType = ScriptType.SYRIAC_ESTRANGELO,
        titleAr = "الخط السرياني الإسطرنجيلي (22 حرفاً)",
        titleEn = "Syriac Estrangelo Script",
        keys = listOf(
            VirtualKey("ܐ", "أ", "ʔ", "أولف (ʾālap̄)", 440),
            VirtualKey("ܒ", "ب", "b", "بيث (bēṯ)", 466),
            VirtualKey("ܓ", "ج", "g", "گومل (gāmal)", 493),
            VirtualKey("ܕ", "د", "d", "دولد (dālaṯ)", 523),
            VirtualKey("ܗ", "هـ", "h", "هي (hē)", 554),
            VirtualKey("ܘ", "و", "w", "واو (waw)", 587),
            VirtualKey("ܙ", "ز", "z", "زاين (zayn)", 622),
            VirtualKey("ܚ", "ح", "ħ", "حيث (ḥēṯ)", 659),
            VirtualKey("ܛ", "ط", "tˤ", "طيث (ṭēṯ)", 698),
            VirtualKey("ܝ", "ي", "j", "يود (yōḏ)", 740),
            VirtualKey("ܟ", "ك", "k", "كوف (kāp̄)", 784),
            VirtualKey("ܠ", "ل", "l", "لومد (lāmaḏ)", 830),
            VirtualKey("ܡ", "م", "m", "ميم (mīm)", 880),
            VirtualKey("ܢ", "ن", "n", "نون (nūn)", 932),
            VirtualKey("ܣ", "س", "s", "سمكث (semkaṯ)", 987),
            VirtualKey("ܥ", "ع", "ʕ", "عي (ʿē)", 1046),
            VirtualKey("ܦ", "ف", "p", "في (pē)", 1108),
            VirtualKey("ܨ", "ص", "sˤ", "صودي (ṣāḏē)", 1174),
            VirtualKey("ܩ", "ق", "q", "قوف (qōp̄)", 1244),
            VirtualKey("ܪ", "ر", "r", "ريش (rēš)", 1318),
            VirtualKey("ܫ", "ش", "ʃ", "شين (šīn)", 1396),
            VirtualKey("ܬ", "ت", "t", "تو (taw)", 1480),
            VirtualKey("܁", ".", "-", "نقطة سريانية مفردة", 440),
            VirtualKey("܀", ":", "-", "علامة ختام سريانية بأربع نقاط", 440)
        ),
        presets = listOf(
            PresetInscriptionSnippet(
                labelAr = "فاتحة البشيطتا السريانية (الرها)",
                originalText = "ܒܪܫܝܬ ܐܝܬܘܗܝ ܗܘܐ ܡܠܬܐ ܘܗܘ ܡܠܬܐ ܐܝܬܘܗܝ ܗܘܐ ܠܘܬ ܐܠܗܐ",
                transliteration = "B-rīšīṯ ʾīṯaw-hy hwā melṯā...",
                translationAr = "في البدء كان الكلمة، وتلك الكلمة كانت لدى الله"
            )
        )
    )

    val SAFAITIC_LAYOUT = ScriptKeyboardLayout(
        scriptType = ScriptType.SAFAITIC_ANA,
        titleAr = "خط البادية الشمالي القديم / الصفائي (28 حرفاً)",
        titleEn = "Ancient North Arabian / Safaitic Script",
        keys = listOf(
            VirtualKey("𐪀", "أ", "ʔ", "ألف", 440),
            VirtualKey("𐪁", "ب", "b", "باء", 466),
            VirtualKey("𐪂", "ت", "t", "تاء", 493),
            VirtualKey("𐪃", "ث", "θ", "ثاء", 523),
            VirtualKey("𐪄", "ج", "g", "جيم", 554),
            VirtualKey("𐪅", "ح", "ħ", "حاء", 587),
            VirtualKey("𐪆", "خ", "x", "خاء", 622),
            VirtualKey("𐪇", "د", "d", "دال", 659),
            VirtualKey("𐪈", "ذ", "ð", "ذال", 698),
            VirtualKey("𐪉", "ر", "r", "راء", 740),
            VirtualKey("𐪊", "ز", "z", "زاي", 784),
            VirtualKey("𐪋", "س", "s", "سين (س1)", 830),
            VirtualKey("𐪌", "ش", "ɬ", "شين (س2)", 880),
            VirtualKey("𐪍", "ص", "sˤ", "صاد", 932),
            VirtualKey("𐪎", "ض", "ɮˤ", "ضاد", 987),
            VirtualKey("𐪏", "ط", "tˤ", "طاء", 1046),
            VirtualKey("𐪐", "ظ", "ðˤ", "ظاء", 1108),
            VirtualKey("𐪑", "ع", "ʕ", "عين", 1174),
            VirtualKey("𐪒", "غ", "ɣ", "غين", 1244),
            VirtualKey("𐪓", "ف", "f", "فاء", 1318),
            VirtualKey("𐪔", "ق", "q", "قاف", 1396),
            VirtualKey("𐪕", "ك", "k", "كاف", 1480),
            VirtualKey("𐪖", "ل", "l", "لام", 1567),
            VirtualKey("𐪗", "م", "m", "ميم", 1661),
            VirtualKey("𐪘", "ن", "n", "نون", 1760),
            VirtualKey("𐪙", "هـ", "h", "هاء", 1864),
            VirtualKey("𐪚", "و", "w", "واو", 1975),
            VirtualKey("𐪛", "ي", "j", "ياء", 2093)
        ),
        presets = listOf(
            PresetInscriptionSnippet(
                labelAr = "افتتاحية نقش صفائي بالبادية",
                originalText = "𐪖 𐪎 𐪁 𐪒 𐪖 𐪎 𐪁 𐪉 𐪖 𐪒 𐪋 𐪗 𐪖 𐪉 𐪎 𐪚 𐪖 𐪁 𐪅 𐪉 𐪖 𐪈 𐪋 𐪗 𐪚 𐪒 𐪖 𐪗",
                transliteration = "l-ʿbd bn ʾws w-bny ʿl-ḥbb-h...",
                translationAr = "لـ (عَبْد بن أَوس) وبنى على حبيبه ووجد أثر أهله فندم"
            )
        )
    )

    val CUNEIFORM_SYLLABIC_LAYOUT = ScriptKeyboardLayout(
        scriptType = ScriptType.CUNEIFORM,
        titleAr = "المقاطع والرموز المسمارية الأكادية والبابلية",
        titleEn = "Akkadian / Babylonian Cuneiform Logograms",
        keys = listOf(
            VirtualKey("𒀭", "DINGIR / إله", "dingir / ilu", "علامة الألوهية الأكادية (إيلو)", 440),
            VirtualKey("𒈗", "LUGAL / مَلِك", "šarru", "رمز الملك الأكادي (شارّو)", 466),
            VirtualKey("𒂗", "EN / سيّد", "bēlu", "رمز السيد والرب (بيلو)", 493),
            VirtualKey("𒂍", "É / بيت ومَعبد", "bītu", "رمز البيت والمعبد (بيتو)", 523),
            VirtualKey("𒆳", "KUR / أرض وجبل", "mātu", "رمز البلاد والإقليم (ماتو)", 554),
            VirtualKey("𒆠", "KI / موضع وأرض", "erṣetu", "رمز الأرض والمكان (إرصيتو)", 587),
            VirtualKey("𒀀", "A / ماء", "mû", "رمز الماء والبنوة (مو)", 622),
            VirtualKey("𒍪", "ZU / حكمة وعلم", "edû", "علامة العلم والمعرفة", 659),
            VirtualKey("GAL", "GAL / عظيم وكبير", "rabû", "رمز العظمة والكبر (رابو)", 698),
            VirtualKey("𒌉", "DUMU / ابن", "māru", "رمز الابن والذرية (مارو)", 740),
            VirtualKey("𒊩", "MUNUS / امرأة", "sinništu", "محددة المؤنث (سينيشتو)", 784),
            VirtualKey("𒄑", "GIŠ / خشب وشجر", "iṣu", "محددة الأخشاب والأشجار (إصو)", 830),
            VirtualKey("𒌨", "UR / بطل وكلب", "kalbu", "رمز البطل والحيوان الضاري", 880),
            VirtualKey("𒌓", "UD / يوم وشمس", "ūmu / Šamaš", "رمز اليوم وشمس (أومو / شماش)", 932),
            VirtualKey("आईटी", "IT / قمر وشهر", "warḫu", "علامة الشهر القمري (وارخو)", 987),
            VirtualKey("𒅗", "KA / فم وكلام", "pû / amātu", "رمز الفم والأمر والكلام (بو)", 1046),
            VirtualKey("𒋗", "ŠU / يد وقوة", "qātu", "رمز اليد والقدرة (قاتو)", 1108),
            VirtualKey("𒄊", "GÌR / قَدَم وسير", "šēpu", "رمز القدم والمشي (شيبو)", 1174)
        ),
        presets = listOf(
            PresetInscriptionSnippet(
                labelAr = "فاتحة شريعة حمورابي البابلية",
                originalText = "𒀭 𒄿 𒉡 𒀭 𒀀 𒉡 𒌝 𒍢 𒊒 𒌝 𒈗 𒀭 𒀀 𒉣 𒈾 𒆠",
                transliteration = "i-nu Anum ṣīrum šar Anunnaki...",
                translationAr = "عندما الإله آنو السامي، ملك الأنوناكي، مع إنليل رب السماء والأرض"
            )
        )
    )

    val ALL_LAYOUTS: List<ScriptKeyboardLayout> = listOf(
        PHOENICIAN_LAYOUT,
        UGARITIC_LAYOUT,
        MUSNAD_LAYOUT,
        GEEZ_LAYOUT,
        ARAMAIC_LAYOUT,
        SYRIAC_LAYOUT,
        SAFAITIC_LAYOUT,
        CUNEIFORM_SYLLABIC_LAYOUT
    )
}
