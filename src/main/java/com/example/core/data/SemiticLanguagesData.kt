package com.example.core.data

import com.example.core.model.*

object SemiticLanguagesData {
    val ALL_LANGUAGES: List<SemiticLanguage> = listOf(
        // === EAST SEMITIC ===
        SemiticLanguage(
            id = "akkadian",
            nameAr = "الأكادية",
            nameEn = "Akkadian",
            branch = LanguageBranch.EAST_SEMITIC,
            scriptType = ScriptType.CUNEIFORM,
            period = "2600 ق.م – 500 ق.م",
            geographicalRegion = "بلاد الرافدين (العراق وسوريا)",
            historicalKingdoms = listOf("الإمبراطورية الأكادية", "مملكة سرجون الأكادي", "نارام سين"),
            consonantCount = 20,
            sampleTextOriginal = "𒀭 𒈗 𒁺 𒈗 𒆧 𒆠",
            sampleTextTransliteration = "Šar-ru-kīn šar kiš-šat",
            sampleTextTranslationAr = "سرجون، ملك الكون والعالم الأكادي",
            prominentInscriptions = listOf("مسلة سرجون الأكادي", "مسلة نارام سين الصخرية", "أسطوانة مانيشتوشو"),
            phonologicalKeyFeatures = listOf(
                "فقدان معظم الصوامت الحلقية والحنجرية السامية (*h, *ḥ, *ʿ, *ġ) وتعويضها بصوائت ممدودة (e / ē)",
                "الحفاظ على الصوامت المطبقة الثلاثية: (q, ṣ, ṭ)",
                "حفظ التنوين والميمية الاسمية القديمة (-um, -am, -im)"
            ),
            morphologicalFeatures = listOf(
                "نظام الجذوع اللفظية الرباعي: G (المجرد), D (المكثف), Š (التعدية), N (المطاوع)",
                "صيغة الحاضر المستمر (iparras) والماضي التام (iprus) والماضي التتابعي (iptaras)",
                "الضمائر المتصلة المنفصلة وتطابق إعراب المضاف إليه"
            ),
            syntacticFeatures = listOf(
                "ترتيب الجملة SOV (فاعل - مفعول - فعل) بسبب التأثير الطبقي السومري العريق",
                "استخدام حرف الجر (ina, ana, ištu) وبناء الجمل المركبة بـ (ša)"
            ),
            primaryDeities = listOf("عشتار (Ištar)", "شمش (Šamaš)", "إنليل (Enlil)", "مردوخ (Marduk)")
        ),
        SemiticLanguage(
            id = "babylonian",
            nameAr = "البابلية",
            nameEn = "Babylonian",
            branch = LanguageBranch.EAST_SEMITIC,
            scriptType = ScriptType.CUNEIFORM,
            period = "1950 ق.م – 100 م",
            geographicalRegion = "جنوب ووسط بلاد الرافدين (بابل، كيش، أور، نيبور)",
            historicalKingdoms = listOf("السلالة البابلية الأولى", "الدولة الكاشية", "الإمبراطورية البابلية الحديثة (الكلدانية)"),
            consonantCount = 20,
            sampleTextOriginal = "𒄿 𒉡 𒈠 𒀭 𒉡 𒌝 𒍢 𒊒 𒌝",
            sampleTextTransliteration = "Inūma Anum ṣīrum šar Anunnaki",
            sampleTextTranslationAr = "حينما أقر آنو العليّ، ملك الأنوناكي، المجد لبابل",
            prominentInscriptions = listOf("مسلة شريعة حمورابي (اللوفر)", "أسطوانة قورش البابلية", "ألواح خلق العالم (إنوما إيليش)"),
            phonologicalKeyFeatures = listOf(
                "تحول (w) الابتدائية إلى فراغ أو إدغام في البابلية الوسيطة والحديثة",
                "استقرار المصوتات (a, i, u, e) الطويلة والقصيرة",
                "تماثل النون مع الصامت التالي (in-dīnum > iddīnum)"
            ),
            morphologicalFeatures = listOf(
                "استقرار صيغ التوكيد والاستفهام",
                "تطور صيغة الجمع المؤنث (-ātum) والجمع المذكر (-ū)",
                "استخدام اسم الفاعل (pārisum) والمصدر (parāsum)"
            ),
            syntacticFeatures = listOf(
                "بناء الجمل الشرطية القانونية (šumma awīlum... innamer: إذا قام رجل... يُعاقب)",
                "استخدام الجمل الوصفية الموصولة بأداة ša"
            ),
            primaryDeities = listOf("مردوخ (Bel-Marduk)", "نبو (Nabû)", "عشتار", "سين (Sin)")
        ),
        SemiticLanguage(
            id = "assyrian",
            nameAr = "الآشورية",
            nameEn = "Assyrian",
            branch = LanguageBranch.EAST_SEMITIC,
            scriptType = ScriptType.CUNEIFORM,
            period = "2000 ق.م – 609 ق.م",
            geographicalRegion = "شمال بلاد الرافدين (آشور، نينوى، كالح، نمرود)",
            historicalKingdoms = listOf("المملكة الآشورية القديمة", "المملكة الآشورية الوسيطة", "الإمبراطورية الآشورية الحديثة"),
            consonantCount = 20,
            sampleTextOriginal = "𒀭 𒁹 𒀸 𒋩 𒆕 𒌉 𒍑 𒈗 𒃲",
            sampleTextTransliteration = "Aššur-bāni-apli šarru rabû šarru dannu",
            sampleTextTranslationAr = "آشوربانيبال، الملك العظيم، الملك القوي، ملك بلاد آشور",
            prominentInscriptions = listOf("مكتبة نينوى الملكية الرقيمية", "مسلة شلمنصر الثالث السوداء", "نقوش قصر سنحاريب"),
            phonologicalKeyFeatures = listOf(
                "قانون الانسجام الصوتي الآشوري (Assyrian Vowel Harmony): تأثر حركة المقطع القصير بحركة المقطع التالي",
                "المحافظة على الواو الابتدائية (wa- / warhum) خلافاً للبابلية",
                "تبسيط المجموعات الصوتية المركبة"
            ),
            morphologicalFeatures = listOf(
                "ثبات الميمية في العصر القديم وتلاشيها لاحقاً",
                "أدوات الإشارة الآشورية الخاصة (anniu, ammīu)",
                "اشتقاقات صيغة التوكيد التناظرية"
            ),
            syntacticFeatures = listOf(
                "أسلوب الحوليات والتقارير العسكرية الحربية المباشرة",
                "أدوات الربط السردية الإخبارية"
            ),
            primaryDeities = listOf("آشور (Aššur)", "عشتار نينوى", "أداد (Adad)", "نيرجال")
        ),
        SemiticLanguage(
            id = "eblaite",
            nameAr = "الإيبلاوية",
            nameEn = "Eblaite",
            branch = LanguageBranch.EAST_SEMITIC,
            scriptType = ScriptType.CUNEIFORM,
            period = "2400 ق.م – 2250 ق.م",
            geographicalRegion = "تل مرديخ (شمال غرب سوريا)",
            historicalKingdoms = listOf("مملكة إيبلا الملكية الأولى والثانية"),
            consonantCount = 22,
            sampleTextOriginal = "𒉌 𒅖 𒀭 𒁕 𒃶 𒂗 𒈠 𒌈",
            sampleTextTransliteration = "ni-iš da-gan en ma-tim",
            sampleTextTranslationAr = "باسم الإله دجن، رب الأرض وسيد إيبلا",
            prominentInscriptions = listOf("أرشيف ألواح تل مرديخ (17,000 لوح طيني)", "المعاهدة الإيبلاوية مع أبرسال", "المعاجم الثنائية السومرية-الإيبلاوية"),
            phonologicalKeyFeatures = listOf(
                "حلقة وصل بين السامية الشرقية والشمالية الغربية",
                "الاحتفاظ ببعض الحروف الحلقية في الرسم المسماري الصوتي",
                "نظام صوائت ثلاثي كلاسيكي"
            ),
            morphologicalFeatures = listOf(
                "استخدام البادئة (ti-) للغائب المؤنث والمخاطب",
                "صيغ الضمائر المتصلة المشابهة للأكادية القديمة والعمورية",
                "أدوات النسب والأسماء المركبة مع الإله إيل ودجن"
            ),
            syntacticFeatures = listOf(
                "مزيج من الترتيب الفعلي VSO و SOV في السجلات الإدارية والاقتصادية",
                "استخدام الرموز السومرية الفكرية (Sumerograms) مع لواحق إيبلاوية صرفية"
            ),
            primaryDeities = listOf("دجن (Dagan)", "كورا (Kura)", "حدد (Haddad)", "إيل (El)")
        ),

        // === NORTHWEST SEMITIC ===
        SemiticLanguage(
            id = "amorite",
            nameAr = "الأمورية",
            nameEn = "Amorite",
            branch = LanguageBranch.NORTHWEST_SEMITIC,
            scriptType = ScriptType.CUNEIFORM,
            period = "2500 ق.م – 1200 ق.م",
            geographicalRegion = "بلاد الشام وبادية سوريا وبلاد الرافدين (ماري، يمحاض، بابل القديمة)",
            historicalKingdoms = listOf("مملكة ماري العمورية", "سلالة بابل الأولى (حمورابي)", "مملكة يمحاض (حلب)"),
            consonantCount = 28,
            sampleTextOriginal = "𒀭 𒈠 𒌨 𒀀 𒁍 𒌝",
            sampleTextTransliteration = "Yantin-ʾEl šamê u erṣetim, Hammu-rapiʾ",
            sampleTextTranslationAr = "الإله يمنح، وحمورابي العظيم، والآلهة حامية العهود",
            prominentInscriptions = listOf("أرشيف قصر ماري الملكي الرقيمية", "نصوص الأعلام العمورية في إيبلا وأور الثالثة", "لوامس رسائل العمارنة القديمة"),
            phonologicalKeyFeatures = listOf(
                "الاحتفاظ بالواو الابتدائية السامية الأصلية (*w- > w-) دون تحولها إلى ياء كالكنعانية اللاحقة",
                "الحفاظ على كامل الصوامت الحلقية والحنجرية الخمسة (*h, *ḥ, *ʿ, *ġ, *ʾ)",
                "عدم حدوث التحول الكنعاني (*ā > ō) مما يثبت موقعها كأقدم لسان شمالي غربي موثق"
            ),
            morphologicalFeatures = listOf(
                "صيغة الفعل المضارع يفعل (yaqțul) مع الاحتفاظ بالحركات الإعرابية الأصلية",
                "عدم وجود أداة تعريف مخصصة (كالسامية الأم)",
                "حفظ التنوين والميمية في أسماء الأعلام والصفات"
            ),
            syntacticFeatures = listOf(
                "غلبة الأسماء الثيوفورية المركبة الإسنادية (فعل + اسم إله)",
                "جمل فعلية تتقدم فيها الأفعال مع أسماء الآلهة الرعوية"
            ),
            primaryDeities = listOf("إيل (ʾEl)", "دجن (Dagan)", "هدد (Hadad)", "عناة (ʿAnat)")
        ),
        SemiticLanguage(
            id = "canaanite",
            nameAr = "الكنعانية القديمة (كنعانية العمارنة)",
            nameEn = "Old Canaanite (Amarna Canaanite)",
            branch = LanguageBranch.NORTHWEST_SEMITIC,
            scriptType = ScriptType.CUNEIFORM,
            period = "1800 ق.م – 1000 ق.م",
            geographicalRegion = "أرض كنعان وسواحل بلاد الشام وفلسطين والأردن (شكيم، مجدو، أورشليم، حاصور)",
            historicalKingdoms = listOf("ممالك المدن الكنعانية", "مملكة حاصور الكبرى", "أورشليم اليبوسية الكنعانية", "مجدو وجازر"),
            consonantCount = 22,
            sampleTextOriginal = "𒀀 𒉡 𒈠 𒅗 𒆷 𒄠 𒈠 𒋫 𒉌",
            sampleTextTransliteration = "anūma ba-ti-ti ša-me-ma u er-ṣe-tum",
            sampleTextTranslationAr = "ها أنا ذا عبدك الأمين تحت قدمي الملك سيدي، شمس السماوات والأرض",
            prominentInscriptions = listOf("نصوص اللعن الكنعانية المصرية (Execration Texts)", "رسائل تل العمارنة الكنعانية اللوامس (EA 286, 287)", "أختام تل المتسلم الكنعانية بمجدو"),
            phonologicalKeyFeatures = listOf(
                "الظهور التاريخي الأول للتحول الكنعاني الحاسم (*ā > ō) كما وثقته رسائل العمارنة (anūki, zūru)",
                "تحول الواو الابتدائية السامية إلى ياء (*warḫu > yarḫu)",
                "سقوط النون الساكنة بالتماثل مع الصامت التالي (yantin > yattin)"
            ),
            morphologicalFeatures = listOf(
                "ظهور أداة التعريف بالهاء المشددة (ha-)",
                "لواحق ضمائر المتكلم والمخاطب الكنعانية (-ī, -kā, -hū)",
                "علامة التأنيث بالتاء الصريحة (-at)"
            ),
            syntacticFeatures = listOf(
                "ترتيب الجملة VSO مع الربط التتابعي بالواو",
                "صيغ النذور والشرط الكنعاني الملكي"
            ),
            primaryDeities = listOf("إيل (ʾEl)", "بعل (Baʿal)", "عشتاروت (ʿAštart)", "موت (Mōt)")
        ),
        SemiticLanguage(
            id = "ancient-hebrew",
            nameAr = "العبرية القديمة (النقشية والتوراتية)",
            nameEn = "Ancient / Biblical Hebrew",
            branch = LanguageBranch.NORTHWEST_SEMITIC,
            scriptType = ScriptType.PHOENICIAN_LINEAR,
            period = "1200 ق.م – 200 م",
            geographicalRegion = "فلسطين ومرتفعات يهوذا والسامرة وحوض نهر الأردن",
            historicalKingdoms = listOf("مملكة إسرائيل الشمالية (السامرة)", "مملكة يهوذا (أورشليم القدس)"),
            consonantCount = 22,
            sampleTextOriginal = "𐤔𐤌𐤏 𐤉𐤔𐤓𐤀𐤋 𐤉𐤄𐤅𐤄 𐤀𐤋𐤄𐤉𐤍𐤅 𐤉𐤄𐤅𐤄 𐤀𐤇𐤃",
            sampleTextTransliteration = "Šəmaʿ Yiśrāʾēl YHWH ʾĚlōhēnū YHWH ʾeḥād",
            sampleTextTranslationAr = "اسمع يا إسرائيل: الرب إلهنا رب واحد، أحبب إلهك بكل قلبك وفكرك",
            prominentInscriptions = listOf("تقويم جازر الزراعي القديم (Gezer Calendar)", "نقش نفق سلوان بأورشليم (Siloam Inscription)", "شقفات لخيش العسكرية (Lachish Ostraca)", "مخطوطات البحر الميت بقمران"),
            phonologicalKeyFeatures = listOf(
                "تطبيق التحول الكنعاني الكامل (*ā > ō) مثل šālōm < *šalām-",
                "قانون بجد كفت (Begadkefat) المزدوج للنطق الانفجاري والاحتكاكي للحروف الستة",
                "الاحتفاظ بالشين المزدوجة (š و ś) الفونيمية"
            ),
            morphologicalFeatures = listOf(
                "واو العطف التتابعية السردية الناسخة للزمن (Waw-Consecutive: wayyōmer)",
                "منظومة الأوزان السبعة الكلاسيكية: (Qal, Nif'al, Pi'el, Pu'al, Hif'il, Huf'al, Hitpa'el)",
                "حذف نون الجمع في حالة الإضافة البنائية (bānīm > bənē)"
            ),
            syntacticFeatures = listOf(
                "السرد النثري التاريخي المتعاقب بصيغ الماضي المعطوف",
                "التوازي الشعري المترادف والتركيبي والطباقي"
            ),
            primaryDeities = listOf("إيل (ʾEl)", "يهوه (YHWH)")
        ),
        SemiticLanguage(
            id = "ugaritic",
            nameAr = "الأوغاريتية",
            nameEn = "Ugaritic",
            branch = LanguageBranch.NORTHWEST_SEMITIC,
            scriptType = ScriptType.UGARITIC_CUNEIFORM,
            period = "1400 ق.م – 1185 ق.م",
            geographicalRegion = "رأس الشمرا (الساحل السوري الشمالي، اللاذقية)",
            historicalKingdoms = listOf("مملكة أوغاريت البحرية", "سلالة نقماد وأمورابي"),
            consonantCount = 30,
            sampleTextOriginal = "𐎍𐎁𐎓𐎍 𐎊𐎈𐎔𐎍 𐎋𐎐𐎔 𐎐𐎌𐎗",
            sampleTextTransliteration = "l-ba'li yaḥpulu kanapa našri",
            sampleTextTranslationAr = "لبعل يكسر جناح النسر المعتدي",
            prominentInscriptions = listOf("لوح ملحمة بعل وموت", "ملحمة كرت (Keret)", "ملحمة أقهات بن دانيال", "اللوح الموسيقي الحوري-الأوغاريتي"),
            phonologicalKeyFeatures = listOf(
                "الاحتفاظ بـ 28 صامتاً سامياً أصيلاً بالإضافة إلى علامات الهمزة الثلاث (ʾa, ʾi, ʾu)",
                "عدم خضوعها للتحول الكنعاني (*ā لم تتحول إلى ō بل بقيت ā)",
                "الاحتفاظ بالحلقيات الكاملة (ح، خ، ع، غ) والأسنانيات (ذ، ث، ظ)"
            ),
            morphologicalFeatures = listOf(
                "نظام إعرابي ثلاثي كامل (-u, -a, -i) والميمية والحرية الصرفية",
                "الأوزان الفعلية السبعة بما فيها أوزان L-stem و Š-stem",
                "ازدواجية الضمائر والمثنى في الأفعال والأسماء"
            ),
            syntacticFeatures = listOf(
                "التوازي الشعري السامي الكنعاني الكلاسيكي (Parallelism)",
                "استخدام أدوات الربط (w-, p-, l-) والترتيب الفعلي VSO"
            ),
            primaryDeities = listOf("إيل (ʾIl)", "بعل (Baʿal)", "عناة (ʿAnat)", "أشيرة (ʾAṯirat)", "يم وموت")
        ),
        SemiticLanguage(
            id = "phoenician",
            nameAr = "الفينيقية",
            nameEn = "Phoenician",
            branch = LanguageBranch.NORTHWEST_SEMITIC,
            scriptType = ScriptType.PHOENICIAN_LINEAR,
            period = "1200 ق.م – 300 م",
            geographicalRegion = "الساحل اللبناني والسوري والفلسطيني (جبيل، صور، صيدا، أرواد)",
            historicalKingdoms = listOf("مملكة جبيل", "مملكة صور البحرية", "مملكة صيدا"),
            consonantCount = 22,
            sampleTextOriginal = "𐤀𐤍𐤊 𐤀𐤇𐤓𐤌 𐤁𐤍 𐤀𐤕𐤁𐤏𐤋 𐤌𐤋𐤊 𐤂𐤁𐤋",
            sampleTextTransliteration = "ʾnk ʾḥrm bn ʾtbʿl mlk gbl",
            sampleTextTranslationAr = "أنا أحيرام بن إيتوبعل، ملك جبيل، صانع هذا التابوت لأبيه",
            prominentInscriptions = listOf("نقش تابوت أحيرام الملكي بجبيل", "نقش كاراتبه ثنائي اللغة (أزيتوادا)", "نقش تابوت تبنيت وإشمونعزر بصيدا"),
            phonologicalKeyFeatures = listOf(
                "خضوع تام للتحول الكنعاني الصوتي (*ā > ō ثم إلى ū في الفينيقية المتأخرة)",
                "اندماج الصوامت السامية الـ 29 في 22 حرفاً أبجدياً خطياً",
                "سقوط النون الساكنة بالإدغام التام (*bint > bitt)"
            ),
            morphologicalFeatures = listOf(
                "ضمير المتكلم المنفصل (𐤀𐤍𐤊 ʾanōkī)",
                "أداة التعريف الهاء (𐤄ـ) مع تضعيف الحرف التالي",
                "وزن التعدية بالياء (Yiphil 𐤉𐤐𐤏𐤋) في فينيقية جبيل وصور"
            ),
            syntacticFeatures = listOf(
                "استخدام واو العطف التتابعية المباشرة (waw consecutive)",
                "أداة المفعولية الصريحة (𐤀𐤉𐤕 ʾiyyāt / 𐤀𐤕 ʾēt)"
            ),
            primaryDeities = listOf("ملقارت (Melqart)", "عشتروت (Astarte)", "بعل شميم", "إشمون (Eshmun)")
        ),
        SemiticLanguage(
            id = "punic",
            nameAr = "البونيقية (الفينيقية الغربية)",
            nameEn = "Punic & Neo-Punic",
            branch = LanguageBranch.NORTHWEST_SEMITIC,
            scriptType = ScriptType.PHOENICIAN_LINEAR,
            period = "814 ق.م – 500 م",
            geographicalRegion = "قرطاج، تونس، الجزائر، المغرب، صقلية، الأندلس",
            historicalKingdoms = listOf("جمهورية قرطاج التجارية", "إمبراطورية حنبعل البرقاوي"),
            consonantCount = 22,
            sampleTextOriginal = "𐤋𐤓𐤁𐤕 𐤋𐤕𐤍𐤕 𐤐𐤍 𐤁𐤏𐤋 𐤅𐤋𐤀𐤃𐤍 𐤋𐤁𐤏𐤋 𐤇𐤌𐤍",
            sampleTextTransliteration = "l-rbt l-tnt pn bʿl w-l-ʾdn l-bʿl ḥmn",
            sampleTextTranslationAr = "لسيدتنا تنيت وجه بعل، ولسيدنا بعل حمون، نذر نذره فلان",
            prominentInscriptions = listOf("شواهد نذور التوفيت بقرطاج", "نقش مسينيسا النوميدي-البونيقي", "شواهد تريبوليتانيا وليبيا النيوبونيقية"),
            phonologicalKeyFeatures = listOf(
                "ضعف نطق الحروف الحلقية وتداخل العين والهمزة والحاء في البونيقية المتأخرة",
                "انتقال الصائت الممدود (ō) إلى (ū) بشكل كامل",
                "ظهور التدوين بالأحرف اللاتينية واليونانية (Poenulus لبلاوتوس)"
            ),
            morphologicalFeatures = listOf(
                "استخدام أداة الموصول (𐤔ـ š-) كصيغة مخففة من (ʾašer)",
                "تطور ضمائر الملكية اللاحقة (-im, -om)",
                "تنوع أوزان اسم المفعول واسم الفاعل"
            ),
            syntacticFeatures = listOf(
                "صيغ النذور والتكريس الديني والتشريعات الجنائزية",
                "استخدام التراكيب الإضافية التتابعية"
            ),
            primaryDeities = listOf("تنيت (Tanit)", "بعل حمون (Baal Hammon)", "شدرفا", "ملقارت")
        ),
        SemiticLanguage(
            id = "moabite",
            nameAr = "المؤابية",
            nameEn = "Moabite",
            branch = LanguageBranch.NORTHWEST_SEMITIC,
            scriptType = ScriptType.PHOENICIAN_LINEAR,
            period = "900 ق.م – 500 ق.م",
            geographicalRegion = "شرق البحر الميت (الأردن: ذيبان، الكرك، مادبا)",
            historicalKingdoms = listOf("مملكة مؤاب", "عهد الملك ميشع المؤابي"),
            consonantCount = 22,
            sampleTextOriginal = "𐤀𐤍𐤊 𐤌𐤔𐤏 𐤁𐤍 𐤊𐤌𐤔𐤉𐤕 𐤌𐤋𐤊 𐤌𐤀𐤁",
            sampleTextTransliteration = "ʾnk mšʿ bn kmšyt mlk mʾb",
            sampleTextTranslationAr = "أنا ميشع بن كموشيت، ملك مؤاب الديباني، صانع هذا النصب لكموش",
            prominentInscriptions = listOf("مسلة ميشع الشهيرة (حجر ذيبان - اللوفر)", "نقش الكرك المؤابي", "شقفة مادبا الفخارية"),
            phonologicalKeyFeatures = listOf(
                "التحول الكنعاني (*ā > ō) في أسماء الأعلام والمفردات",
                "جمع المذكر السالم بالنون (-īn) مثل العربية والآرامية خلافاً للعبرية والفينيقية (-īm)",
                "الاحتفاظ بتاء التأنيث المفتوحة (-at) في الأسماء المفردة"
            ),
            morphologicalFeatures = listOf(
                "استخدام صيغة الفعل المطاوع بالتاء (Yiptaʿal / w-ʾltḥm: وقاتلتُ)",
                "ضمير الغائب المفرد المذكر الملحق بالهاء (-h / -hu)",
                "استخدام واو العطف التتابعية السردية في الأفعال الماضية"
            ),
            syntacticFeatures = listOf(
                "السرد التاريخي الحربي التتابعي بأسلوب رفيع",
                "التطابق الصارم بين الصفة والموصوف والمعطوفات"
            ),
            primaryDeities = listOf("كموش (Chemosh)", "عشتار-كموش", "بعل فغور")
        ),
        SemiticLanguage(
            id = "ammonite",
            nameAr = "العمونية",
            nameEn = "Ammonite",
            branch = LanguageBranch.NORTHWEST_SEMITIC,
            scriptType = ScriptType.PHOENICIAN_LINEAR,
            period = "900 ق.م – 500 ق.م",
            geographicalRegion = "عمان والبلقاء (الأردن: ربّة عمون، تل سيران)",
            historicalKingdoms = listOf("مملكة بني عمون", "الملك عميناداب"),
            consonantCount = 22,
            sampleTextOriginal = "𐤏𐤁𐤃 𐤏𐤌𐤍𐤃𐤁 𐤌𐤋𐤊 𐤁𐤍 𐤏𐤌𐤍",
            sampleTextTransliteration = "ʿbd ʿmndb mlk bn ʿmn",
            sampleTextTranslationAr = "منجزات عميناداب ملك بني عمون بن هصل إيل",
            prominentInscriptions = listOf("نقش قارورة تل سيران البرونزية", "نقش قلعة عمان الحجري", "نقش مسرح عمان الأثري"),
            phonologicalKeyFeatures = listOf(
                "وسيط لغوي بين الكنعانية الشمالية والآرامية الجنوبية",
                "المحافظة على الحروف الحلقية",
                "التحول الكنعاني الكامل للصوائت الممدودة"
            ),
            morphologicalFeatures = listOf(
                "صيغ أسماء الملوك المركبة مع أسماء الآلهة (إيل، ملكوم)",
                "استخدام أدوات التعريف الكنعانية"
            ),
            syntacticFeatures = listOf(
                "صيغ الإهداء الملكي والتوثيق البنائي للقصور والجنان"
            ),
            primaryDeities = listOf("ملكوم (Milkom)", "إيل (El)")
        ),
        SemiticLanguage(
            id = "edomite",
            nameAr = "الأدومية",
            nameEn = "Edomite",
            branch = LanguageBranch.NORTHWEST_SEMITIC,
            scriptType = ScriptType.PHOENICIAN_LINEAR,
            period = "900 ق.م – 400 ق.م",
            geographicalRegion = "جنوب الأردن وشمال النقب (بصيرا، وادي رم، إيلات)",
            historicalKingdoms = listOf("مملكة أدوم الجبلية", "بصيرا عاصمة أدوم"),
            consonantCount = 22,
            sampleTextOriginal = "𐤋𐤒𐤅𐤎 𐤏𐤁𐤃 𐤄𐤌𐤋𐤊",
            sampleTextTransliteration = "l-qws ʿbd h-mlk",
            sampleTextTranslationAr = "لقوس، خادم الملك، نذر سلامة",
            prominentInscriptions = listOf("أوستراكا حورفات عوزا الأدومية", "شقفات بصيرا الفخارية", "أختام أم قيس ووادي رم"),
            phonologicalKeyFeatures = listOf(
                "خصائص صوتية كنعانية جنوبية مع تأثر بالبادية العربية المبكرة",
                "حفظ الصامت الحلقي القوسي المنسوب للإله قوس"
            ),
            morphologicalFeatures = listOf(
                "استخدام اسم الإله الوطني (قوس Qaws) في معظم الأسماء الشخصية والمركبة",
                "بناء الإضافة الاسمي الكنعاني"
            ),
            syntacticFeatures = listOf(
                "رسائل عسكرية وإدارية مختصرة على الشقفات الفخارية"
            ),
            primaryDeities = listOf("قوس (Qaws)", "إيل", "عشتروت")
        ),

        // === ARAMAIC COMPLEX ===
        SemiticLanguage(
            id = "old-aramaic",
            nameAr = "الآرامية القديمة والإمبراطورية",
            nameEn = "Old & Imperial Aramaic",
            branch = LanguageBranch.ARAMAIC,
            scriptType = ScriptType.IMPERIAL_ARAMAIC,
            period = "1000 ق.م – 200 ق.م",
            geographicalRegion = "دمشق، حماة، شمال سوريا، والإمبراطورية الأخمينية الفارسية",
            historicalKingdoms = listOf("مملكة آرام دمشق", "مملكة سمأل (زنجرلي)", "المملكة الأخمينية"),
            consonantCount = 22,
            sampleTextOriginal = "𐡍𐡑𐡁𐡀 𐡆𐡉 𐡔𐡌 𐡁𐡓𐡄𐡃𐡃 𐡁𐡓 𐡈𐡁𐡓𐡌𐡍",
            sampleTextTransliteration = "nṣbʾ zy śm br-hdd br ṭb-rmn",
            sampleTextTranslationAr = "النصب الذي أقامه بر هدد بن طاب ريمون لسيده ملقارت",
            prominentInscriptions = listOf("مسلة بر هدد النذرية بدمشق", "نقوش السفيرة المعاهدية", "مسلة تل دان الحجرية", "برديات إلفنتين بمصر"),
            phonologicalKeyFeatures = listOf(
                "تحول الصوامت بين الأسنانية إلى صوامت أسنانية انفجارية (*ḏ > d, *ṯ > t, *ṱ > ṭ)",
                "عدم خضوعها للتحول الكنعاني (بقاء *ā كما هي)",
                "تطور أداة التعريف اللاحقة (-ā) المسماة بالحالة المؤكدة"
            ),
            morphologicalFeatures = listOf(
                "كلمة (𐡁𐡓 bar) للدلالة على الابن خلافاً للكنعانية (𐤁𐤍 ben/bin)",
                "استخدام أداة الموصول والإضافة (𐡆𐡉 zī / 𐡃𐡉 dī)",
                "الجذوع الفعلية: Peal (المجرد), Pael (المكثف), Haphel/Aphel (السببي), Ithpeel (المطاوع)"
            ),
            syntacticFeatures = listOf(
                "أصبحت لغة الدبلوماسية والتجارة العالمية الأولى (Lingua Franca) في الشرق الأدنى القديم",
                "مرونة عالية في الترتيب بين VSO و SVO"
            ),
            primaryDeities = listOf("حدد (Hadad)", "سين حران", "عترسمين", "شمس")
        ),
        SemiticLanguage(
            id = "nabataean",
            nameAr = "النبطية",
            nameEn = "Nabataean Aramaic",
            branch = LanguageBranch.ARAMAIC,
            scriptType = ScriptType.NABATAEAN,
            period = "300 ق.م – 400 م",
            geographicalRegion = "البتراء، الحجر (مدائن صالح)، النقب، حوران، سيناء",
            historicalKingdoms = listOf("مملكة الأنباط العربية", "عهد الملك الحارث الرابع وعبادة"),
            consonantCount = 22,
            sampleTextOriginal = "𐢅𐢼𐢠 𐢍𐢁𐢇 𐢕𐢍𐢝 𐢙𐢞𐢈 𐢁𐢄 𐢑𐢞𐢖𐢄",
            sampleTextTransliteration = "dnh qbrʾ dy ʿbd ḥryt mlk nbtw",
            sampleTextTranslationAr = "هذا القبر الذي صنعه الحارث ملك الأنباط لنفسه ولأولاده",
            prominentInscriptions = listOf("نقش مدائن صالح الجنائزي", "نقش النمارة لامرئ القيس (328م)", "نقش عين عبدات النبطي", "نقش حران اللجأ"),
            phonologicalKeyFeatures = listOf(
                "لغة آرامية كتابية مع طبقة تحتية عربية واضحة ونظام صوتي عربي متداخل",
                "تطور الخط النبطي المتصل الذي انبثق منه الخط العربي الكلاسيكي مباشرة",
                "استخدام الواو النبطية في نهاية الأسماء (Al-Waw al-Nabatiyya)"
            ),
            morphologicalFeatures = listOf(
                "الجمع بين المفردات الآرامية والأسماء والأفعال العربية الصميمة",
                "صيغ الدعاء النبطية (شلم، دكير، ليعن)"
            ),
            syntacticFeatures = listOf(
                "نقوش جنائزية وقانونية وصخرية على واجهات المقابر الصخرية في البتراء ومدائن صالح"
            ),
            primaryDeities = listOf("ذو الشرى (Dushara)", "اللات (Allat)", "العزى (Al-Uzza)", "مناة")
        ),
        SemiticLanguage(
            id = "palmyrene",
            nameAr = "التدمرية",
            nameEn = "Palmyrene Aramaic",
            branch = LanguageBranch.ARAMAIC,
            scriptType = ScriptType.IMPERIAL_ARAMAIC,
            period = "100 ق.م – 273 م",
            geographicalRegion = "واحة تدمر (بادية الشام، سوريا)",
            historicalKingdoms = listOf("مملكة تدمر", "الملكة زنوبيا (بات زباي) وأذينة"),
            consonantCount = 22,
            sampleTextOriginal = "𐡑𐡋𐡌𐡀 𐡃𐡍𐡄 𐡃𐡉 𐡁𐡕 𐡆𐡁𐡉 𐡌𐡋𐡊𐡕𐡀",
            sampleTextTransliteration = "ṣlmʾ dnh dy bt-zby mlktʾ",
            sampleTextTranslationAr = "هذا التمثال لزنوبيا الملكة الجليلة المعظمة المعظمة في تدمر",
            prominentInscriptions = listOf("التعريفة الجمركية التدمرية الكبرى", "نقوش الشواهد الجنائزية التدمرية", "شواهد معبد بل وتدمر"),
            phonologicalKeyFeatures = listOf(
                "آرامية غربية متطورة ذات نبر وخصائص صوتية مميزة",
                "خط تدمري زخرفي فخم بنوعين: خط أثري بارز وخط مائل سريع"
            ),
            morphologicalFeatures = listOf(
                "أدوات الإشارة التدمرية (دنه، دكن)",
                "صيغ الرتب التجارية والمناصب القوافلية (رئيس القافلة: ريش شيرتا)"
            ),
            syntacticFeatures = listOf(
                "نقوش تجارية وتنظيمية ثنائية اللغة (تدمرية - يونانية)"
            ),
            primaryDeities = listOf("بل (Bel)", "يرحبول", "عجلبول", "ملكبل")
        ),
        SemiticLanguage(
            id = "syriac",
            nameAr = "السريانية الكلاسيكية",
            nameEn = "Classical Syriac",
            branch = LanguageBranch.ARAMAIC,
            scriptType = ScriptType.SYRIAC_ESTRANGELO,
            period = "100 م – مستمرة",
            geographicalRegion = "الرها (أورفا)، ماردين، الجزيرة الفراتية، دمشق، العراق، مالابار (الهند)",
            historicalKingdoms = listOf("مملكة الرها (أوسروين)", "مملكة الحضر"),
            consonantCount = 22,
            sampleTextOriginal = "ܒܪܫܝܬ ܐܝܬܘܗܝ ܗܘܐ ܡܠܬܐ",
            sampleTextTransliteration = "B-rešīt ʾītwawī-wā melṯā",
            sampleTextTranslationAr = "في البدء كان الكلمة، والكلمة كان عند الله",
            prominentInscriptions = listOf("ترجمة العهدين (البشيطتا)", "مخطوطات دير السريان ودير الزعفران", "نقوش طور عابدين وماردين"),
            phonologicalKeyFeatures = listOf(
                "قانون بجد كفت (Begadkefat): ترقيق وتفخيم الصوامت الستة (b, g, d, k, p, t) عند وقوعها بعد صائت",
                "حركات الصوائت الخمس بنظام التنقيط الماروني أو اليوناني اليعقوبي",
                "تحول الواو الساكنة إلى صائت مركب (aw > au / ō)"
            ),
            morphologicalFeatures = listOf(
                "التفريق الدقيق بين الخطوط الثلاثة: الأسطرنجيلي، الغربي (السرطا)، والشرقي (النسطوري)",
                "اشتقاقات الأفعال المطواعة والسببية الدقيقة",
                "تصريف أداة الوجود الكينونية (ʾīt / layt)"
            ),
            syntacticFeatures = listOf(
                "تراث أدبي وترجمي وفلسفي ضخم كان الجسر الأكبر لنقل العلوم إلى الحضارة العربية الإسلامية",
                "استخدام حرف اللام (l-) لتحديد المفعول به الصريح"
            ),
            primaryDeities = listOf("التراث المسيحي التوحيدي اللاهوتي")
        ),
        SemiticLanguage(
            id = "mandaic",
            nameAr = "المندائية",
            nameEn = "Classical Mandaic",
            branch = LanguageBranch.ARAMAIC,
            scriptType = ScriptType.IMPERIAL_ARAMAIC,
            period = "200 م – مستمرة",
            geographicalRegion = "أهوار جنوب العراق، خوزستان (الأهواز)",
            historicalKingdoms = listOf("مجتمعات الصابئة المندائيين في ميسان وواسط"),
            consonantCount = 23,
            sampleTextOriginal = "ࡁࡔࡅࡌࡀࡉࡄࡅࡍ ࡖࡄࡉࡉࡀ ࡓࡁࡉࡀ",
            sampleTextTransliteration = "b-šumaihun d-hiia rbia",
            sampleTextTranslationAr = "باسم الحي العظيم، القدوس الأزلي البهي",
            prominentInscriptions = listOf("كتاب كنزا ربا (الكنز العظيم)", "ديوان دراشة د-يهيا", "أحراز ورُقى المندائيين الرصاصية والخزفية"),
            phonologicalKeyFeatures = listOf(
                "الكتابة الصوتية الحركية الكاملة (Vocalic Script) حيث تمثل حروف العلة صوائت حقيقية داخل المتن",
                "تآكل نطق الحروف الحلقية الشديد في بيئة الأهواز والرافدين الجنوبية",
                "الحفاظ على خصوصية الخط المندائي المقدس المنفصل"
            ),
            morphologicalFeatures = listOf(
                "أدوات الوصل والإضافة المندائية الخاصة (d-)",
                "صيغ صلوات التعميد (المصبتا) والارتقاء الروحي (المسقثا)"
            ),
            syntacticFeatures = listOf(
                "نصوص طقسية دينية شعرية محفوظة شفهياً وكتابياً لآلاف السنين"
            ),
            primaryDeities = listOf("هيّا ربي (الحي العظيم)", "ملكا د-نورا")
        ),

        // === ARABIC & ANCIENT NORTH ARABIAN ===
        SemiticLanguage(
            id = "classical-arabic",
            nameAr = "العربية الفصحى القديمة",
            nameEn = "Classical Arabic",
            branch = LanguageBranch.ARABIC_ANA,
            scriptType = ScriptType.ARABIC_KUFIC,
            period = "500 م – مستمرة",
            geographicalRegion = "شبه الجزيرة العربية، الشرق الأوسط، شمال أفريقيا، العالم الإسلامي",
            historicalKingdoms = listOf("مملكة كندة", "مملكة الغساسنة والمناذرة", "الحضارة العربية الإسلامية"),
            consonantCount = 28,
            sampleTextOriginal = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
            sampleTextTransliteration = "Bi-smi l-lāhi r-raḥmāni r-raḥīm",
            sampleTextTranslationAr = "بسم الله الرحمن الرحيم - الحمد لله رب العالمين",
            prominentInscriptions = listOf("المعلقات السبع الجاهلية", "نقش قبة الصخرة المشرفة (72هـ)", "مصحف سمرقند وطشقند الكوفي", "نقش النمارة وحران وزبد"),
            phonologicalKeyFeatures = listOf(
                "أكثر اللغات السامية حفاظاً على الفونيمات الصامتة للسامية الأم (28 صامتاً من أصل 29)",
                "حفظ الصوامت المطبقة الأربعة: (ص، ض، ط، ظ) والحلقيات والحنجريات كاملة",
                "نظام الإعراب بالحركات الثلاث (ضمة، فتحة، كسرة) والتنوين الصوتي المكتمل"
            ),
            morphologicalFeatures = listOf(
                "أوزان الأفعال القياسية العشرة وما فوقها (فَعَل، فَعَّل، فَاعَل، أَفْعَل، تَفَعَّل، تَفَاعَل، انْفَعَل، افْتَعَل، افْعَلَّ، اسْتَفْعَل)",
                "جموع التكسير المعقدة ذات الدلالات المادية والمعنوية الدقيقة",
                "نظام الاشتقاق الجذري الثلاثي والرباعي الأوسع عالمياً"
            ),
            syntacticFeatures = listOf(
                "ترتيب الجملة المرن (VSO و SVO) مع تمييز الفاعل والمفعول بالعلامة الإعرابية لا بالرتبة",
                "البلاغة الإيجازية، الوصل والفصل، والبيان الشعري الفذ"
            ),
            primaryDeities = listOf("التوحيد الخالص لله رب العالمين")
        ),
        SemiticLanguage(
            id = "safaitic",
            nameAr = "الصفائية (الصفوية)",
            nameEn = "Safaitic",
            branch = LanguageBranch.ARABIC_ANA,
            scriptType = ScriptType.SAFAITIC_ANA,
            period = "300 ق.م – 400 م",
            geographicalRegion = "حرة الشام البازلتية (جنوب سوريا، شمال الأردن، شمال السعودية)",
            historicalKingdoms = listOf("قبائل البادية الصفا ورعاة الإبل والخيل في البادية"),
            consonantCount = 28,
            sampleTextOriginal = "𐪀 𐪁 𐪂 𐪃 𐪄 𐪅 𐪆 𐪇",
            sampleTextTransliteration = "l-ġṯ bn s²mt bn whb-ʾl",
            sampleTextTranslationAr = "لغوث بن شمات بن وهب إيل، ورعى الإبل ووجَم على أخيه",
            prominentInscriptions = listOf("أكثر من 40,000 نقش بازلتي في حرة الصفا ورم وقاع العاقر", "نقوش صيد الأسود ورعي الإبل"),
            phonologicalKeyFeatures = listOf(
                "الاحتفاظ بجميع الصوامت السامية الـ 28 مع خط أبجدي صخري سريع الخطوط",
                "استخدام حرف اللام (l-) في بداية كل نقش كعلامة تأليف وملكية",
                "غياب أداة التعريف في كثير من السياقات أو استخدام (h-/hn-)"
            ),
            morphologicalFeatures = listOf(
                "صيغة الفعل الماضي (فعل fʿl) وضمائر النسب القبلي (بن، بنة، آل)",
                "أدوات التوجع والوجد (وجم wjm, تندم tndm)"
            ),
            syntacticFeatures = listOf(
                "صيغ الدعاء بالسلامة والغنيمة (ف هـ بعل سمين سلم وغنمت)"
            ),
            primaryDeities = listOf("الرجا (Ruda)", "اللات", "شيع القوم", "بعل سمين")
        ),
        SemiticLanguage(
            id = "thamudic",
            nameAr = "الثمودية",
            nameEn = "Thamudic (B, C, D)",
            branch = LanguageBranch.ARABIC_ANA,
            scriptType = ScriptType.SAFAITIC_ANA,
            period = "600 ق.م – 300 م",
            geographicalRegion = "شمال ووسط وجنوب شبه الجزيرة العربية (حائل، تيماء، تبوك، نجران)",
            historicalKingdoms = listOf("مملكة ثمود الأثرية وقبائل البادية العربية القديمة"),
            consonantCount = 28,
            sampleTextOriginal = "𐪀 𐪔 𐪓 𐪑 𐪗 𐪘",
            sampleTextTransliteration = "l-zmrt bn ʿbd w-wdd-h",
            sampleTextTranslationAr = "لزمرت بن عبد، وله المحبة والسلام وحفظ الإله",
            prominentInscriptions = listOf("نقوش جبل أم سنمان بحائل", "نقوش وادي رم والديسة", "نقوش جبة وجبل عكمة"),
            phonologicalKeyFeatures = listOf(
                "تنوع أساليب الكتابة من اليمين إلى اليسار، من اليسار إلى اليمين، ورأسياً ومحراثياً (Boustrophedon)",
                "حفظ الصوامت السامية كاملة ونظام رمزي هندسي بديع"
            ),
            morphologicalFeatures = listOf(
                "صيغ المحبة والتحيات (ودّ wdd)",
                "الأسماء الثيوفورية المقترنة بالآلهة القديمة"
            ),
            syntacticFeatures = listOf(
                "تسجيل المذكرات اليومية، ومشاعر العشق والشوق، ورسوم الإبل والوعول"
            ),
            primaryDeities = listOf("رضو (Ruda)", "نهي (Nuhay)", "عترسمين")
        ),
        SemiticLanguage(
            id = "dadanitic",
            nameAr = "الديدانية واللحيانية",
            nameEn = "Dadanitic & Lihyanite",
            branch = LanguageBranch.ARABIC_ANA,
            scriptType = ScriptType.MUSNAD,
            period = "600 ق.م – 100 ق.م",
            geographicalRegion = "واحة دادان (العلا، شمال غرب السعودية)",
            historicalKingdoms = listOf("مملكة دادان", "مملكة لحيان العظيمة"),
            consonantCount = 28,
            sampleTextOriginal = "𐩱 𐩩 𐩡 𐩥 𐩠 𐩬 𐩽 𐩹 𐩡 𐩡 𐩠",
            sampleTextTransliteration = "ʾtlw hn-ẓllh l-ḏġbt",
            sampleTextTranslationAr = "أدوا طقس التظليل والتكريس للإله ذو غابة ونذروا النذور",
            prominentInscriptions = listOf("نقوش جبل عكمة (المكتبة الصخرية المفتوحة بالعلا)", "مقابر الخريبة الديدانية", "تماثيل ملوك لحيان الضخمة"),
            phonologicalKeyFeatures = listOf(
                "استخدام أداة التعريف (هنـ hn-) أو (هـ h-)",
                "خط ضخم منحوت بدقة هندسية بارزة على صخور الجبال الشاهقة",
                "تطابق كبير مع الصوامت العربية الفصحى"
            ),
            morphologicalFeatures = listOf(
                "أوزان التعدية بالهاء (Hiphil/hafʿala) والهمزة",
                "صيغ القرابين والنذور الدورية (Zillal)"
            ),
            syntacticFeatures = listOf(
                "توثيق المعاملات القانونية، ملكيات الآبار والمزارع، والتبرعات لمعبد ذو غابة"
            ),
            primaryDeities = listOf("ذو غابة (Ḏū-Ġābat)", "هن-عزى", "سلمان")
        ),
        SemiticLanguage(
            id = "lihyanite",
            nameAr = "اللحيانية",
            nameEn = "Lihyanite",
            branch = LanguageBranch.ARABIC_ANA,
            scriptType = ScriptType.MUSNAD,
            period = "القرن السادس ق.م – القرن الثاني ق.م",
            geographicalRegion = "مملكة لحيان بواحة العلا والخريبة وجبل عكمة (شمال غرب شبه الجزيرة العربية)",
            historicalKingdoms = listOf("مملكة لحيان القديمة والوسيطة", "ملوك لحيان (هنأس وتلمي وشهر)"),
            consonantCount = 28,
            sampleTextOriginal = "𐪆𐪈𐪖 𐪉𐪚𐪈 𐪑𐪍 𐪃𐪋𐪈 𐪚𐪆𐪒𐪎 𐪕𐪍𐪘𐪑",
            sampleTextTransliteration = "hn-ṣlm dnh bny hn-qyn l-ḏġbt ʾlh-h",
            sampleTextTranslationAr = "هذا الصنم أقامه ونحته القين للإله ذي غابة إلهه ونذر له القرابين",
            prominentInscriptions = listOf("نقوش جبل عكمة اللحيانية الكبرى", "تماثيل الحكام والملوك بمقابر الخريبة", "نقوش صهاريج وقنوات العلا"),
            phonologicalKeyFeatures = listOf(
                "أداة التعريف النموذجية بالهاء والنون (hn-) أو الهاء الصريحة (h-)",
                "الاحتفاظ الكامل بالصوامت السامية الـ 28 مع رسم حروف هندسي عريض",
                "صلة فيلولوجية وثيقة بين اللسان اللحياني وأصول اللهجات العربية الشمالية"
            ),
            morphologicalFeatures = listOf(
                "أوزان الأفعال التعدية (hafʿala) والمطاوعة والتكثير",
                "أدوات الإشارة اللحيانية (ḏn, ḏt) وأداة الصلة (ḏ-)",
                "إعراب الأسماء وتخصيص أسماء المهن والوظائف الدينية"
            ),
            syntacticFeatures = listOf(
                "صيغ التكريس الديني لنذور التمور والماشية (Zillal) للإله ذو غابة",
                "سجلات القوافل التجارية ومحطات استراحة تجار اللبان والمر"
            ),
            primaryDeities = listOf("ذو غابة (Ḏū-Ġaybat)", "اللات (Allāt)", "هن-عزى", "ود (Wadd)")
        ),
        SemiticLanguage(
            id = "hismaic",
            nameAr = "الحسمائية",
            nameEn = "Hismaic",
            branch = LanguageBranch.ARABIC_ANA,
            scriptType = ScriptType.SAFAITIC_ANA,
            period = "القرن الأول ق.م – القرن الرابع الميلادي",
            geographicalRegion = "صحراء حسمى ووادي رم وجبال الديسة (جنوب الأردن وشمال تبوك)",
            historicalKingdoms = listOf("قبائل البادية الحسمائية", "أحلاف القوافل النبطية والبدوية"),
            consonantCount = 28,
            sampleTextOriginal = "𐪀𐪁 𐪂𐪃 𐪄𐪅 𐪆𐪇𐪈 𐪉𐪊 𐪋𐪌",
            sampleTextTransliteration = "l-wdd bn ʿbd l-lt dkr-t ʾl-lt w-s¹lm",
            sampleTextTranslationAr = "لود بن عبد، للات: ذكرت اللات هذا العبد بالخير والسلام والأمان لركبه",
            prominentInscriptions = listOf("نقوش جبال رم وجبال الديسة الصخرية", "رسوم الصيد والجمال بحسمى", "نقوش وادي رم الحسمائية"),
            phonologicalKeyFeatures = listOf(
                "حفظ الصوامت الحلقية والصفيرية والأسنانية كاملة",
                "استخدام أداة التعريف (h-) أو بدونه مع الأسماء المعرفة بالسياق",
                "صلة وثيقة ومباشرة بالأصول اللهجية للعربية التراثية"
            ),
            morphologicalFeatures = listOf(
                "صيغ الدعاء المأثورة (dkrt lt / s¹lm)",
                "ضمائر المتصلة وأسماء الأعلام البدوية المركبة",
                "أفعال الرعي والترحال (ngʿ, wgm)"
            ),
            syntacticFeatures = listOf(
                "عبارات تذكارية سريعة محفورة على واجهات الحجر الرملي الوردي",
                "نصوص مصحوبة برسوم تصويرية للفرسان والوعول والنعام"
            ),
            primaryDeities = listOf("اللات (Allāt)", "رضو (Ruḍaw)", "ذو الشرى (Dushara)")
        ),
        SemiticLanguage(
            id = "taymanitic",
            nameAr = "التيمائية",
            nameEn = "Taymanitic",
            branch = LanguageBranch.ARABIC_ANA,
            scriptType = ScriptType.SAFAITIC_ANA,
            period = "القرن السادس ق.م – القرن الرابع ق.م",
            geographicalRegion = "واحة تيماء التاريخية وبئر هداج (شمال غرب الجزيرة العربية)",
            historicalKingdoms = listOf("مملكة تيماء المستقلة", "إمارة تيماء في عهد الملك البابلي نبونيد"),
            consonantCount = 28,
            sampleTextOriginal = "𐪀 𐪔 𐪚 𐪖 𐪐 𐪑 𐪈 𐪛 𐪜",
            sampleTextTransliteration = "l-ṣlm-šzb mlk tymʾ s¹lm w-ḥyt",
            sampleTextTranslationAr = "لصلم-شزب ملك تيماء، السلام والحياة والأمان لقصر الإمارة وسكان الواحة",
            prominentInscriptions = listOf("مسلة تيماء البازلتية الشهيرة (متحف اللوفر)", "نقوش بئر هداج العتيق وقصر الحمراء", "نقوش جبل غنيم الرقيمية"),
            phonologicalKeyFeatures = listOf(
                "خط تيمائي محلي متميز مشتق من خط المسند الشمالي الأصيل",
                "احتفاظ بالصوامت الأسنانية والاحتكاكية والشفوية",
                "تأثيرات صوتية متبادلة مع البابلية والآرامية بحكم إقامة نبونيد الطويلة بتيماء"
            ),
            morphologicalFeatures = listOf(
                "أسماء أعلام ثنائية اللغة تجمع الجذور السامية الشمالية والبابلية",
                "صيغ تقديم الهدايا والأنصبة الحجرية لآلهة تيماء الكبرى"
            ),
            syntacticFeatures = listOf(
                "نصوص نذرية وتشريعية تنظم مياه بئر هداج والضرائب التجارية",
                "توثيق المعاهدات الدبلوماسية مع الممالك المجاورة"
            ),
            primaryDeities = listOf("صلم (Ṣalm)", "شنقلا (Šengalla)", "أشيما (ʾAšīmā)")
        ),
        SemiticLanguage(
            id = "maltese",
            nameAr = "المالطية",
            nameEn = "Maltese",
            branch = LanguageBranch.ARABIC_ANA,
            scriptType = ScriptType.ARABIC_KUFIC,
            period = "القرن الحادي عشر الميلادي – مستمرة كلغة رسمية أوروبية",
            geographicalRegion = "أرخبيل جزر مالطة وجوزو وكومينو (وسط البحر الأبيض المتوسط)",
            historicalKingdoms = listOf("الإمارة العربية الصقلية", "كونتية وتاج صقلية", "فرسان القديس يوحنا (فرسان مالطا)", "جمهورية مالطا"),
            consonantCount = 25,
            sampleTextOriginal = "Il-bnedmin kollha jitwieldu ħielsa u ugwali fid-dinjità u d-drittijiet",
            sampleTextTransliteration = "Il-bnedmin kollha jitwieldu ħielsa u ugwali fid-dinjità u d-drittijiet",
            sampleTextTranslationAr = "يولد جميع البشر أحراراً ومتساوين في الكرامة والحقوق، وُهبوا عقلاً وضميراً",
            prominentInscriptions = listOf("قصيدة كانتيلنا لميخائيل فيبوس (Il-Kantilena نحو 1470م - أقدم نص مالطي)", "معاجم ميكيل أنطون فازالي التأسيسية", "شواهد قبور الرباط والمدينة بمالطة"),
            phonologicalKeyFeatures = listOf(
                "انحدار فيلولوجي مباشر من العربية الصقلية المغاربية الوسيطة (Siculo-Arabic)",
                "تحول القاف السامية إلى همزة لسانية مزمارية (/q/ > [ʔ]) مكتوبة بالحرف (Q)",
                "حفظ الصوامت الحلقية في أصوات خاصة مثل الحاء (Ħ) والعين التي تحولت إلى إشباع صائتي (Għ)"
            ),
            morphologicalFeatures = listOf(
                "أداة التعريف السامية الموصولة المماثلة للحروف الشمسية (il- / l- / id- / it-)",
                "تصريف أفعال سامي متطابق بالكامل مع العربية: (kiteb / jikteb / ktibna / kitbu)",
                "الجموع التكسيرية السامية العريقة: (ktieb > kotba, triq > toroq, dar > djar)"
            ),
            syntacticFeatures = listOf(
                "بنية جملة سامية أصيلة ممتزجة بمصطلحات قانونية وتقنية لاتينية وإيطالية",
                "اللغة السامية الوحيدة المكتوبة رسمياً بالحرف اللاتيني والمعتمدة كلغة رسمية في الاتحاد الأوروبي"
            ),
            primaryDeities = listOf("التراث التوحيدي (Alla: الله)")
        ),

        // === ANCIENT SOUTH ARABIAN (SAYHADIC) ===
        SemiticLanguage(
            id = "sabaic",
            nameAr = "السبئية",
            nameEn = "Sabaic",
            branch = LanguageBranch.ANCIENT_SOUTH_ARABIAN,
            scriptType = ScriptType.MUSNAD,
            period = "1000 ق.م – 554 م",
            geographicalRegion = "مملكة سبأ (مأرب، صرواح، الجوف، صنعاء - اليمن)",
            historicalKingdoms = listOf("مملكة سبأ الكبرى", "مملكة سبأ وذي ريدان وحضرموت ويمنت"),
            consonantCount = 29,
            sampleTextOriginal = "𐩪 𐩨 𐩱 𐩥 𐩡 𐩠 𐩺 𐩲 𐩻 𐩩 𐩧 𐩥 𐩱 𐩡 𐩣 𐩤 𐩠",
            sampleTextTransliteration = "sbʾ w-l-hyʿṯtr w-ʾlmqh",
            sampleTextTranslationAr = "سبأ، وبعون الإله عثتر والإله إلمقه ثهو، بنوا وشيدوا هذا الصرح العظيم",
            prominentInscriptions = listOf("نقش النصر للمكرب كربئيل وتر بصرواح", "نقوش سد مأرب العظيم وشرحبيل يعفر", "نقوش معبد أوام (محرم بلقيس)", "نقش أبرهة بصرواح ومأرب"),
            phonologicalKeyFeatures = listOf(
                "الاحتفاظ الكامل بجميع صوامت السامية الأم الـ 29 بدون أي اندماج",
                "التفريق الصوتي بين السينات الثلاث: (س1 s¹ = s, س2 s² = ś شين جانبية, س3 s³ = š شين صفيرية)",
                "أداة التعريف اللاحقة بالنون (-ن -n) في نهاية الأسماء (بيت-ن = البيت)"
            ),
            morphologicalFeatures = listOf(
                "بناء الجذع السببي بالهاء (h-fʿl / هفعل) وفي السبئية المتأخرة بالسين (s¹-fʿl)",
                "صيغ الجمع التكسير والجمع السالم بالواو والنون في الرفع (-wn) والياء والنون في النصب والجر (-yn)",
                "خطان: خط المسند التذكاري المنحوت، وخط الزبور السريع على عيدان الخشب"
            ),
            syntacticFeatures = listOf(
                "الترتيب الفعلي الكلاسيكي VSO مع دقة مذهلة في الصياغات القانونية والدبلوماسية والعسكرية",
                "حروف العطف السبئية المتخصصة (و w-, ف f- للتفريع والتعقيب)"
            ),
            primaryDeities = listOf("إلمقه (Almaqah)", "عثتر (Athtar)", "ذات حميم", "شمس", "رحمنان (في الحقبة التوحيدية)")
        ),
        SemiticLanguage(
            id = "minaic",
            nameAr = "المعينية (المدابية)",
            nameEn = "Minaic (Madhhabic)",
            branch = LanguageBranch.ANCIENT_SOUTH_ARABIAN,
            scriptType = ScriptType.MUSNAD,
            period = "800 ق.م – 100 ق.م",
            geographicalRegion = "وادي الجوف (قرناو، يثل/براقش - اليمن)، محطات طريق اللبان (دادان، غزة، مصر، ديلوس باليونان)",
            historicalKingdoms = listOf("مملكة معين التجارية العالمية", "محطات الجالية المعينية في دادان ومصر"),
            consonantCount = 29,
            sampleTextOriginal = "𐩣 𐩲 𐩬 𐩥 𐩺 𐩻 𐩡 𐩥 𐩠 𐩱 𐩡 𐩲 𐩡 𐩺 𐩬",
            sampleTextTransliteration = "mʿn w-yṯl w-h-ʾl ʿlyn",
            sampleTextTranslationAr = "معين ويثل، وأهل وتجار قوافل اللبان المعيني",
            prominentInscriptions = listOf("نقوش أسوار يثل (براقش)", "نقوش الجالية المعينية في العلا (دادان)", "نقش تابوت زيد إيل تاجر البخور بمصر"),
            phonologicalKeyFeatures = listOf(
                "صوتيات معينية متقدمة مع تشديد على مخارج الحروف التجارة والقياس",
                "خط مسند دقيق ومتناسق المقاييس الهندسية"
            ),
            morphologicalFeatures = listOf(
                "الجذع السببي بالسين (s¹-fʿl / سفعل) خلافاً للسبئية التي تستخدم الهاء",
                "صيغ جمع الجمع وألقاب كبار التجار والقضاة المعينيين"
            ),
            syntacticFeatures = listOf(
                "عقود تجارية، لوائح جمركية، وقوانين حماية قوافل طريق البخور والتوابل"
            ),
            primaryDeities = listOf("عثتر ذو قبض", "نكرح (Nikrah)", "ود (Wadd)")
        ),
        SemiticLanguage(
            id = "qatabanic",
            nameAr = "القتبانية",
            nameEn = "Qatabanic",
            branch = LanguageBranch.ANCIENT_SOUTH_ARABIAN,
            scriptType = ScriptType.MUSNAD,
            period = "800 ق.م – 200 م",
            geographicalRegion = "وادي بيحان وحريب (تمنع عاصمة قتبان - اليمن)",
            historicalKingdoms = listOf("مملكة قتبان", "الملك شهر هلال ويدع أب ذبيان"),
            consonantCount = 29,
            sampleTextOriginal = "𐩩 𐩣 𐩬 𐩲 𐩥 𐩲 𐩣 𐩥 𐩱 𐩬 𐩨 𐩺",
            sampleTextTransliteration = "tmnʿ w-ʿm w-ʾnby",
            sampleTextTranslationAr = "تمنع، وبأمر الإله عم والإله أنبي، شرع هذا القانون التجاري العادل",
            prominentInscriptions = listOf("قانون سوق تمنع التجاري والضرائب الشهير", "نقش مسلة هجر بن حميد", "نقوش معبد وادي بيحان"),
            phonologicalKeyFeatures = listOf(
                "استخدام السين السببية والصوتية الدقيقة",
                "تقارب صوتي ومفرداتي وثيق مع الحضرمية والسبئية"
            ),
            morphologicalFeatures = listOf(
                "صيغ الضمائر المتصلة المنتهية بـ (-s¹) للغائب",
                "مصطلحات تنظيم الأسواق، المكاييل، الرسوم، وعقود البيع والإيجار"
            ),
            syntacticFeatures = listOf(
                "أرقى صياغة للنصوص التشريعية والتجارية والدستورية في جنوب الجزيرة العربية"
            ),
            primaryDeities = listOf("عم (ʿAmm)", "أنبي (Anbay)", "عثتر")
        ),
        SemiticLanguage(
            id = "hadramitic",
            nameAr = "الحضرمية",
            nameEn = "Hadramitic",
            branch = LanguageBranch.ANCIENT_SOUTH_ARABIAN,
            scriptType = ScriptType.MUSNAD,
            period = "800 ق.م – 300 م",
            geographicalRegion = "وادي حضرموت، شبوة (العاصمة)، ميناء قنا (بئر علي)، سمهرم (ظفار)",
            historicalKingdoms = listOf("مملكة حضرموت", "ميناء قنا الدولي لتصدير اللبان واللبنى"),
            consonantCount = 29,
            sampleTextOriginal = "𐩦 𐩨 𐩥 𐩩 𐩥 𐩪 𐩺 𐩬 𐩥 𐩤 𐩬 𐩱",
            sampleTextTransliteration = "šbwt w-syn w-qnʾ",
            sampleTextTranslationAr = "شبوة، وحماية الإله سين رب ميناء قنا وسمهرم للبان",
            prominentInscriptions = listOf("نقوش قصر شبوة الملكي (شقير)", "نقوش حصن الغراب بميناء قنا", "نقوش سمهرم وخور روري بظفار"),
            phonologicalKeyFeatures = listOf(
                "ظاهرة الإبدال الصوتي الشبيه بالتلتلة والتحولات بين السين والثاء",
                "نهايات الأسماء المؤنثة المنتهية بـ (-t)"
            ),
            morphologicalFeatures = listOf(
                "صيغة ضمير الغائب بالثاء في بعض اللهجات الحضرمية الصخرية",
                "مصطلحات الملاحة البحرية ومستودعات خزن اللبان الملكي"
            ),
            syntacticFeatures = listOf(
                "نقوش تخليد بناء الحصون الساحلية والموانئ والمشاريع المائية الكبرى"
            ),
            primaryDeities = listOf("سين (Syn / Sayin)", "عثتر", "ذات ظهران")
        ),

        // === MODERN SOUTH ARABIAN ===
        SemiticLanguage(
            id = "mehri",
            nameAr = "المهرية",
            nameEn = "Mehri",
            branch = LanguageBranch.MODERN_SOUTH_ARABIAN,
            scriptType = ScriptType.ARABIC_KUFIC,
            period = "تراث لغوي سامي حي",
            geographicalRegion = "محافظة المهرة (اليمن)، محافظة ظفار (عمان)، جنوب شرق الربع الخالي",
            historicalKingdoms = listOf("قبائل المهرة والتراث اللغوي الشفهي العريق"),
            consonantCount = 30,
            sampleTextOriginal = "هيبو تتعلَم مَهريَت؟ حَه تيت خَير!",
            sampleTextTransliteration = "Hēbō tetʿallam Mahrīyat? Ḥah tīt xayr!",
            sampleTextTranslationAr = "كيف تتعلم اللغة المهرية؟ إنه أمر طيب وممتع للغاية!",
            prominentInscriptions = listOf("الأدب والقصائد المهرية المروية شفهياً", "أمثال وحكم البادية وظفار المهرية"),
            phonologicalKeyFeatures = listOf(
                "الاحتفاظ بالضاد الجانبية الاحتكاكية السامية القديمة (Lateral Fricative ɬ)",
                "وجود الصوامت القذفية الحنجرية المستقلة (Ejectives: k', t', s', š', č')",
                "نظام صوائت مركب يحتوي على 8 صوائت متمايزة بنبر مقطعي صوتي دقيق"
            ),
            morphologicalFeatures = listOf(
                "نظام مزدوج للتصريف (المثنى) في الأفعال والأسماء والضمائر",
                "الأوزان الفعلية الخاصة غير المتأثرة بالتعريب",
                "علامات الجمع الداخلي والخارجي بالمهرية"
            ),
            syntacticFeatures = listOf(
                "ترتيب مرن بين SVO و VSO مع أدوات إشارة ونفي متخصصة (الـ / لا)"
            ),
            primaryDeities = listOf("التراث الإسلامي الشعبي")
        ),
        SemiticLanguage(
            id = "soqotri",
            nameAr = "السقطرية",
            nameEn = "Soqotri",
            branch = LanguageBranch.MODERN_SOUTH_ARABIAN,
            scriptType = ScriptType.ARABIC_KUFIC,
            period = "تراث جزيري سامي حي وفريد",
            geographicalRegion = "أرخبيل جزيرة سقطرى (اليمن: حديبو، قلنسية، عبد الكوري)",
            historicalKingdoms = listOf("مجتمعات سقطرى الجزيرية الفريدة والتراث النباتي والبحري"),
            consonantCount = 31,
            sampleTextOriginal = "شينهو شمهك؟ إيهَن شمهي عيسى!",
            sampleTextTransliteration = "Šīnhū šem-hak? ʾĪhan šem-hī ʿĪsa!",
            sampleTextTranslationAr = "ما اسمك؟ اسمي عيسى السقطري!",
            prominentInscriptions = listOf("نقوش كهف هوك بسقطرى (نقوش هنديّة ومسندية وحبشية وسقطرية)", "الأساطير والشعر السقطري القديم"),
            phonologicalKeyFeatures = listOf(
                "أكثر لغات السامية الجنوبية الحديثة عزلة وأصالة فونولوجية",
                "حفظ الصوامت الجانبية المقذوفة والأصوات الحلقية النادرة",
                "تنوع لهجي بين شرق الجزيرة وغربها وجزر عبد الكوري وسمحة"
            ),
            morphologicalFeatures = listOf(
                "تصريف أفعال فريد للمثنى والمؤنث",
                "معجم نباتي وبيئي استثنائي يصف أشجار دم الأخوين واللبان السقطري"
            ),
            syntacticFeatures = listOf(
                "حوارات شعرية سقطرية شفهية وإيقاعات غنائية بحرية"
            ),
            primaryDeities = listOf("التراث الجزيري الإسلامي")
        ),
        SemiticLanguage(
            id = "shehri-jibbali",
            nameAr = "الشحرية (الجبالية)",
            nameEn = "Shehri / Jibbali",
            branch = LanguageBranch.MODERN_SOUTH_ARABIAN,
            scriptType = ScriptType.ARABIC_KUFIC,
            period = "تراث حي",
            geographicalRegion = "جبال ظفار وساحل صلالة (سلطنة عمان)",
            historicalKingdoms = listOf("سكان جبال ظفار ومجمعي اللبان الحوجري"),
            consonantCount = 30,
            sampleTextOriginal = "عَاقِبَت خَير لِكُل عُمَل",
            sampleTextTransliteration = "ʿĀqibat xayr l-kul ʿumal",
            sampleTextTranslationAr = "العاقبة الخير والبركة لكل عمل وصنعة",
            prominentInscriptions = listOf("أهازيج جني اللبان الظفاري والشعر الجبالي"),
            phonologicalKeyFeatures = listOf(
                "فونيمات جانبية شديدة الوضوح ونظام صوتي انسيابي فريد",
                "تحولات نبرية وحركية تختلف عن المهرية"
            ),
            morphologicalFeatures = listOf(
                "صيغ أفعال متعددة للأحوال والأنشطة الرعوية والجبلية"
            ),
            syntacticFeatures = listOf(
                "سرد قصصي وتراثي شعبي متوارث"
            ),
            primaryDeities = listOf("التراث الإسلامي")
        ),
        SemiticLanguage(
            id = "bathari",
            nameAr = "البطحرية",
            nameEn = "Bathari",
            branch = LanguageBranch.MODERN_SOUTH_ARABIAN,
            scriptType = ScriptType.ARABIC_KUFIC,
            period = "تراث شفهي مهدد بالاندثار",
            geographicalRegion = "ساحل بحر العرب مقابل جزر الحلانيات وشربثات (محافظة ظفار ومحافظة الوسطى - عمان)",
            historicalKingdoms = listOf("مجتمعات صيادي وغواصي اللؤلؤ الباطحرة على بحر العرب"),
            consonantCount = 29,
            sampleTextOriginal = "مِسْيَاك تَبْرُوك وِبْحُور كَفُور",
            sampleTextTransliteration = "Misyāk tabrūk w-bəḥūr kafūr",
            sampleTextTranslationAr = "مساؤك مبارك وبحارنا مليئة بالخير والصيد والبركة",
            prominentInscriptions = listOf("أراجيز الملاحة البحرية وصيد أسماك القرش التراثية"),
            phonologicalKeyFeatures = listOf(
                "حفظ الصوامت الجانبية المقذوفة والأسنانية النادرة",
                "نظام صوتي غني بأصوات البحر والصيد التي تتقاطع مع المهرية والشحرية",
                "تحولات صوتية سريعة بسبب قلة عدد الناطقين المعاصرين"
            ),
            morphologicalFeatures = listOf(
                "معجم متخصص بالمصطلحات البحرية والرياح الموسمية والغوص",
                "أوزان أفعال وحركات إعرابية تماثل السامية الجنوبية القديمة"
            ),
            syntacticFeatures = listOf(
                "حوارات وتراكيب غنائية بحرية شفهية"
            ),
            primaryDeities = listOf("التراث الإسلامي الجزيري")
        ),
        SemiticLanguage(
            id = "harsusi",
            nameAr = "الحرسوسية",
            nameEn = "Harsusi",
            branch = LanguageBranch.MODERN_SOUTH_ARABIAN,
            scriptType = ScriptType.ARABIC_KUFIC,
            period = "تراث حي في بادية جدة الحراسيس",
            geographicalRegion = "صحراء جدة الحراسيس وصحراء المها (محافظة الوسطى - سلطنة عمان)",
            historicalKingdoms = listOf("قبائل الحراسيس ورعاة الإبل وبدو الصحراء العمانية"),
            consonantCount = 29,
            sampleTextOriginal = "شْخَبُورك خَيرْ وِبْعِيرْ تَرْعَى حَيّ",
            sampleTextTransliteration = "Šxabūrk xayr w-bʿīr tarʿā ḥayy",
            sampleTextTranslationAr = "أخبارك خير إن شاء الله، والإبل ترعى في روض العشب الحي",
            prominentInscriptions = listOf("أشعار وقصائد الحداء الصحراوي ورعي الإبل بالحراسيس"),
            phonologicalKeyFeatures = listOf(
                "قرب فيلولوجي معجمي من المهرية مع سمات صوتية مميزة لبيئة البادية",
                "صوامت جانبية واحتكاكية ومقذوفة بالغة الوضوح في مخارج الحروف",
                "حفظ الصوائت المركبة والترقيق البين للأصوات"
            ),
            morphologicalFeatures = listOf(
                "أنساق مفردات دقيقة تصف تضاريس الصحراء والإبل وأنواع النباتات الجافة",
                "تصريف أفعال ثنائي ومثنى دقيق"
            ),
            syntacticFeatures = listOf(
                "سرد بدوي شعري وقصصي محكم الأداء"
            ),
            primaryDeities = listOf("التراث الإسلامي الجزيري")
        ),
        SemiticLanguage(
            id = "hobyot",
            nameAr = "الهوبيوت",
            nameEn = "Hobyot",
            branch = LanguageBranch.MODERN_SOUTH_ARABIAN,
            scriptType = ScriptType.ARABIC_KUFIC,
            period = "تراث شفهي نادر",
            geographicalRegion = "المناطق الحدودية الجبلية بين سلطنة عمان (حوف/ظفار) واليمن (المهرة)",
            historicalKingdoms = listOf("مجتمعات الهوبيوت بين سلاسل جبال حوف وجبال ظفار"),
            consonantCount = 29,
            sampleTextOriginal = "أَمْبُور خَيْر لِكُل أَهْل وِجَار",
            sampleTextTransliteration = "ʾAmbūr xayr l-kul ʾahl w-ǧār",
            sampleTextTranslationAr = "صباح الخير والأمان لكل أهل وديار وجار",
            prominentInscriptions = listOf("المأثورات الشفهية والقصص الحدودية بجبال حوف"),
            phonologicalKeyFeatures = listOf(
                "حلقة وصل لغوية استثنائية بين المهرية والشحرية (الجبالية)",
                "حفظ الصوامت السامية الجنوبية الأصيلة وتأثير متبادل بين اللهجات",
                "صوتيات حلقية ومقذوفة دقيقة للغاية"
            ),
            morphologicalFeatures = listOf(
                "تداخل صيغ الضمائر والأفعال بين المهرية والجبالية",
                "بنية مفردات ترتبط بالغابات الجبلية ومواسم الخريف والضباب"
            ),
            syntacticFeatures = listOf(
                "تراكيب قصيرة مركزة وسرد تراثي شفهي"
            ),
            primaryDeities = listOf("التراث الإسلامي")
        ),

        // === ETHIOSEMITIC ===
        SemiticLanguage(
            id = "geez",
            nameAr = "الجعزية الكلاسيكية (الحبشية)",
            nameEn = "Ge'ez (Classical Ethiopic)",
            branch = LanguageBranch.ETHIOSEMITIC,
            scriptType = ScriptType.GEEZ_FIDEL,
            period = "500 ق.م – مستمرة كلغة طقسية",
            geographicalRegion = "مملكة أكسوم (شمال إثيوبيا وإريتريا)",
            historicalKingdoms = listOf("إمبراطورية أكسوم العظمى", "الملك عيزانا وكالب"),
            consonantCount = 26,
            sampleTextOriginal = "ቀዳሚሁ፡ ቃል፡ ውእቱ፡ ወውእቱ፡ ቃል፡ ኀበ፡ እግዚአብሔር",
            sampleTextTransliteration = "Qadāmīhu Qāl wəʾətu, wawəʾətu Qāl ḫaba ʾƎgzīʾabḥēr",
            sampleTextTranslationAr = "في البدء كان الكلمة، وكان الكلمة عند الرب إله الكون",
            prominentInscriptions = listOf("مسلة الملك عيزانا الحجرية الثلاثية بأكسوم (حجر رشيد الإثيوبي)", "مخطوطة أناجيل أبا غاريما المذهبة (أقدم مخطوطة إنجيلية مصورة)", "كتاب أخنوخ الجعزي ومجد الملوك (كبر نجشت)"),
            phonologicalKeyFeatures = listOf(
                "التحول من الصوامت المطبقة السامية إلى الصوامت القذفية الحنجرية (Ejectives: p', t', k', s', č')",
                "نظام المراتب الصوتية السبع في الخط المقطعي الفيدل (Ge'ez, Ka'eb, Sales, Rabe, Hames, Sades, Sabe)",
                "الحفاظ على الحلقيات والحنجريات السامية الكلاسيكية (ح، ع، خ، هـ، أ)"
            ),
            morphologicalFeatures = listOf(
                "علامة النصب الصريحة بالفتحة في الأسماء (-a) مثل العربية (Nəguša nagast: ملك الملوك)",
                "الأوزان اللفظية الثلاثية: Qatala, Qattala, ʾAqtala, Tawaqātala",
                "نظام الإضافة المتصلة ونظام الجموع التكسيرية الشبيهة بالعربية والسبئية"
            ),
            syntacticFeatures = listOf(
                "ترتيب الجملة VSO في الأصل مع تحول تدريجي نحو SOV بتأثير اللغات الكوشية المجاورة",
                "أدوات الربط (wa-, za-, ʾəm-)"
            ),
            primaryDeities = listOf("مهرم (Mahrem/Ares)", "عثتر", "بحر", "إله السماء التوحيدي")
        ),
        SemiticLanguage(
            id = "amharic",
            nameAr = "الأمهرية",
            nameEn = "Amharic",
            branch = LanguageBranch.ETHIOSEMITIC,
            scriptType = ScriptType.GEEZ_FIDEL,
            period = "1200 م – مستمرة",
            geographicalRegion = "إثيوبيا (أديس أبابا، غوندار، شوا)",
            historicalKingdoms = listOf("الإمبراطورية الإثيوبية السليمانية", "عهد الأباطرة منليك وتيدروس وهيلا سيلاسي"),
            consonantCount = 31,
            sampleTextOriginal = "ሰላም፡ እንደምን፡ ነህ፧ እኔ፡ ደህና፡ ነኝ።",
            sampleTextTransliteration = "Selam, əndəmən näh? Ǝne dähna näñ.",
            sampleTextTranslationAr = "سلام، كيف حالك؟ أنا بخير وعافية ونعمة!",
            prominentInscriptions = listOf("الأغاني الملكية الإمبراطورية (Imperial Royal Songs)", "الدستور الإثيوبي والأدب الأمهري الحديث"),
            phonologicalKeyFeatures = listOf(
                "سقوط معظم الحروف الحلقية تحت تأثير الكوشية (العين والحاء والهمزة والخاء)",
                "إضافة الصوامت الحنكية (Palatals: č, č', š, ž, ñ, ǧ)",
                "استخدام الخط الفيدل بـ 33 صامتاً مع الحركات السبع"
            ),
            morphologicalFeatures = listOf(
                "أداة التعريف اللاحقة (-u للمذكر، -wa للمؤنث)",
                "فعل الكينونة اللاحق (-n / näw)",
                "الاشتقاقات المورفولوجية الدقيقة للأفعال المركبة"
            ),
            syntacticFeatures = listOf(
                "الترتيب الفعلي الصارم SOV (الفاعل - المفعول - الفعل) التزاماً كاملاً",
                "وضع الجمل الوصفية والمضاف إليه قبل الموصوف والمضاف"
            ),
            primaryDeities = listOf("التراث المسيحي والإسلامي الإثيوبي")
        ),
        SemiticLanguage(
            id = "tigrinya",
            nameAr = "التجرينية",
            nameEn = "Tigrinya",
            branch = LanguageBranch.ETHIOSEMITIC,
            scriptType = ScriptType.GEEZ_FIDEL,
            period = "1200 م – مستمرة",
            geographicalRegion = "إريتريا (أسمرة) وإقليم تيغراي (إثيوبيا: مقلي، أكسوم)",
            historicalKingdoms = listOf("مملكة مدري بحري (إريتريا)", "إقليم تيغراي الأكسومي"),
            consonantCount = 29,
            sampleTextOriginal = "ከመይ፡ ኣለኻ፧ ደሓን፡ ዶ፡ ቐኒኻ፧",
            sampleTextTransliteration = "Kemey ʾāleḵā? Deḥān do qänīḵā?",
            sampleTextTranslationAr = "كيف أنت؟ هل قضيت أيامك بالخير والسلام؟",
            prominentInscriptions = listOf("قوانين لغاس هابسل العرفية الإريترية", "مخطوطات أديرة ديبري دامو وديبري بيزن"),
            phonologicalKeyFeatures = listOf(
                "أكثر اللغات الإثيوبية الحديثة محافظة على أصوات الجعزية الحلقية (العين والحاء والخاء والهمزة)",
                "الاحتفاظ بنظام الصوامت القذفية الحنجرية (q', t', č', s', k'x)",
                "ظاهرة التفخيم والترقيق اللولبي للصوامت"
            ),
            morphologicalFeatures = listOf(
                "بنية أفعال تشبه الجعزية والعربية في تصريف الماضي والمضارع",
                "أداة التعريف والضمائر التجرينية المستقلة"
            ),
            syntacticFeatures = listOf(
                "الترتيب SOV مع مرونة نسبية وتأثير أكسومي أصيل"
            ),
            primaryDeities = listOf("التراث المسيحي والإسلامي الإريتري والتيغراوي")
        ),
        SemiticLanguage(
            id = "tigre",
            nameAr = "التجرية",
            nameEn = "Tigre",
            branch = LanguageBranch.ETHIOSEMITIC,
            scriptType = ScriptType.GEEZ_FIDEL,
            period = "القرن العاشر الميلادي – مستمرة",
            geographicalRegion = "شمال وغرب إريتريا (قاش بركة، عنسبا، الساحل الشمالي) ومحاذاة شرق السودان",
            historicalKingdoms = listOf("مملكة البجة التاريخية", "سلطنة د Dahlak وجزر دهلك", "قبائل بني عامر والحباب"),
            consonantCount = 27,
            sampleTextOriginal = "ሕና፡ ሕዝብ፡ ብሩክ፡ ወሰላም፡ ነሀሌ",
            sampleTextTransliteration = "Ḥəna ḥəzb bərūk wa-salām nahalē",
            sampleTextTranslationAr = "نحن شعب مبارك، نعيش في سلام وأمان ومودة وخير",
            prominentInscriptions = listOf("شواهد قبور وأضرحة جزر دهلك بالخط الكوفي والتجري", "أشعار ملحمة الحباب الشعبية العريقة"),
            phonologicalKeyFeatures = listOf(
                "احتفاظ مدهش بالصوامت الحلقية السامية (العين والحاء والخاء والهمزة)",
                "حفظ الصوامت السامية المطبقة والقذفية",
                "أقرب اللغات الإثيوبية الحية شبهاً بالجعزية الكلاسيكية في بنيتها الصوتية"
            ),
            morphologicalFeatures = listOf(
                "أداة التعريف باللام المشددة (la-) الشبيهة بالعربية والسامية القديمة",
                "تصريف أفعال سامي متقن في الماضي والمضارع والأمر",
                "جموع تكسير سامية عريقة (أفعل، فُعول، فِعال)"
            ),
            syntacticFeatures = listOf(
                "ترتيب جملة SOV مع استخدام أدوات ربط عريقة",
                "تراث شعري وأدبي شفهي غني جداً بالأمثال والحكم"
            ),
            primaryDeities = listOf("التراث الإسلامي الإريتري")
        ),
        SemiticLanguage(
            id = "harari",
            nameAr = "الهررية (لسان قِدر / جيسينان)",
            nameEn = "Harari (Gēy Sinān)",
            branch = LanguageBranch.ETHIOSEMITIC,
            scriptType = ScriptType.ARABIC_KUFIC,
            period = "القرن العاشر الميلادي – مستمرة",
            geographicalRegion = "مدينة هرر المسورة التاريخية (يوغول) - شرق إثيوبيا",
            historicalKingdoms = listOf("سلطنة عفت التاريخية", "سلطنة عدل الإسلامية (أحمد جران)", "إمارة هرر المستقلة"),
            consonantCount = 26,
            sampleTextOriginal = "أَمَانْ بَشِرْ أَمَانْ أَمَانْ كُوتْ أَمَانْ حَالْشُو؟",
            sampleTextTransliteration = "ʾAmān bašir, ʾamān ʾamān, kūt ʾamān ḥālšu?",
            sampleTextTranslationAr = "السلام عليكم، بالخير واليمن والبركة، كيف حالكم وأحوال دياركم؟",
            prominentInscriptions = listOf("مخطوطات كتاب الفرائض وقصائد المولد النبوي بالخط العربي الهرري", "نقوش أبواب وأسوار هرر التاريخية الخمسة"),
            phonologicalKeyFeatures = listOf(
                "جزيرة لغوية سامية متحضرة داخل النطاق الكوشي الأورومي",
                "تاريخ طويل من التدوين بالخط العربي المشتق والمعدل",
                "تحولات صوتية في الصوامت الحلقية مع الحفاظ على القذفية"
            ),
            morphologicalFeatures = listOf(
                "أوزان أفعال حضرية متطورة لتنظيم التجارة والحرف اليدوية",
                "لواحق تصريفية خاصة للاحترام والتبجيل الأسري والمدني",
                "معجم ثري يجمع الجذور السامية مع مصطلحات الفقه والتصوف الإسلامي"
            ),
            syntacticFeatures = listOf(
                "بنية جملة محكمة تناسب التوثيق التجاري والأدب الصوفي المسور",
                "سرد أدبي حضري مميز عن اللهجات الرعوية المحيطة"
            ),
            primaryDeities = listOf("التراث الإسلامي الهرري والتصوف")
        ),
        SemiticLanguage(
            id = "argobba",
            nameAr = "الأرغوبية",
            nameEn = "Argobba",
            branch = LanguageBranch.ETHIOSEMITIC,
            scriptType = ScriptType.GEEZ_FIDEL,
            period = "القرن الثاني عشر الميلادي – مستمرة",
            geographicalRegion = "محافظات شوا وولو وشرق إثيوبيا (أودية الأرغوبا التاريخية)",
            historicalKingdoms = listOf("سلطنة إيفات الإسلامية", "حواضر تجار القوافل المسلمين في جبال إثيوبيا"),
            consonantCount = 27,
            sampleTextOriginal = "ሰላም፡ ሁኑ፡ በቤታችሁ፡ በረከት፡ ይግባ",
            sampleTextTransliteration = "Salām hūnu, ba-bētāčəhu barakat yəgba",
            sampleTextTranslationAr = "كونوا بسلام وأمان، ولتدخل البركة والخير بيوتكم ودياركم",
            prominentInscriptions = listOf("شواهد القبور الأثرية بأودية شوا", "مخطوطات الأرغوبا الدينية والتجارية"),
            phonologicalKeyFeatures = listOf(
                "شقيقة فيلولوجية مباشرة للأمهرية مع احتفاظ أكبر ببعض الصوامت القديمة",
                "حفظ الصوامت الحنجرية والقذفية في بيئة جبلية منعزلة",
                "تأثيرات صوتية ناجمة عن حماية مسارات التجارة القديمة"
            ),
            morphologicalFeatures = listOf(
                "تصريف أفعال يجمع بين البنية الجعزية القديمة والتطورات الأمهرية",
                "مصطلحات فريدة لتجارة التوابل والمنسوجات والبن"
            ),
            syntacticFeatures = listOf(
                "ترتيب SOV كلاسيكي للسامية الإثيوبية الجنوبية"
            ),
            primaryDeities = listOf("التراث الإسلامي الإثيوبي")
        ),
        SemiticLanguage(
            id = "gafat",
            nameAr = "الغافاتية",
            nameEn = "Gafat",
            branch = LanguageBranch.ETHIOSEMITIC,
            scriptType = ScriptType.GEEZ_FIDEL,
            period = "منقرضة (وثقت حتى أواخر القرن التاسع عشر)",
            geographicalRegion = "إقليم النيل الأزرق وجنوب غوجام (إثيوبيا)",
            historicalKingdoms = listOf("مملكة غافات التاريخية", "إمارة غوجام الجنوبية"),
            consonantCount = 26,
            sampleTextOriginal = "እግዚኦ፡ አጽንአነ፡ በማኅደረ፡ ሰላም",
            sampleTextTransliteration = "ʾƎgzīʾo ʾaṣnəʾana ba-māḥdara salām",
            sampleTextTranslationAr = "يا رب ثبتنا في مساكن السلام والأمان والرخاء",
            prominentInscriptions = listOf("مخطوطة ترجمة نشيد الأنشاد بالغافاتية للمستشرق جيمس بروس (1770م)", "أبحاث ولف ليسلاو التوثيقية الميدانية"),
            phonologicalKeyFeatures = listOf(
                "لغة منقرضة ذات أهمية فيلولوجية قصوى لكونها تمثل الفرع الوسيط للسامية الجنوبية الإثيوبية",
                "حفظ الصوامت القذفية الحنجرية وتطور صوامت لثوية احتكاكية",
                "سقوط معظم الصوامت الحلقية نتيجة الجوار الكوشي"
            ),
            morphologicalFeatures = listOf(
                "صيغ أفعال وسيطة بين مجموعات الجوراجي والأمهرية",
                "علامات مفعولية وإضافة فريدة تميز اللسان الغافاتي المندثر"
            ),
            syntacticFeatures = listOf(
                "ترتيب الفاعل فالمفعول فالفعل SOV الصارم"
            ),
            primaryDeities = listOf("التراث التوحيدي الإثيوبي")
        ),
        SemiticLanguage(
            id = "gurage",
            nameAr = "الجوراجية (لغات ومجموعات الجوراجي)",
            nameEn = "Gurage (Sebat Bet & Kistane)",
            branch = LanguageBranch.ETHIOSEMITIC,
            scriptType = ScriptType.GEEZ_FIDEL,
            period = "القرن الرابع عشر الميلادي – مستمرة",
            geographicalRegion = "المرتفعات الجنوبية الغربية لشوا وإقليم الجوراجي (إثيوبيا)",
            historicalKingdoms = listOf("حلف بيوت الجوراجي السبعة (Sebat Bet Gurage)", "إمارة كيستاني وسودو"),
            consonantCount = 28,
            sampleTextOriginal = "ዳን፡ እንኩ፡ በሰላም፡ ተደበርነ",
            sampleTextTransliteration = "Dān ʾənku, ba-salām tadabbarne",
            sampleTextTranslationAr = "نحن بخير وسلام، وقد اجتمعنا بالمحبة والتعاون والبركة",
            prominentInscriptions = listOf("أعراف وسجلات قضاء السبات بيت التقليدية (Yajoka Kicha)", "مأثورات شجرة الإنسيت وشعر الحصاد الجوراجي"),
            phonologicalKeyFeatures = listOf(
                "مجمع لغوي يضم أكثر من 12 لغة ولهجة سامية شديدة التنوع",
                "ظاهرة الإشمام والإطباق الشفوي (Labialization) والحنكي (Palatalization) المعقدة",
                "أغنى اللغات السامية بتنوع الصوامت وتداخلها الصوتي المتفرد"
            ),
            morphologicalFeatures = listOf(
                "تصريف أفعال بالغ التعقيد يفرق بين أنواع الحركة وأزمنة وقوع الحدث",
                "منظومة ضمائر متصلة ومنفصلة دقيقة جداً لكل شخص وحالة",
                "معجم زراعي ضخم مرتبط بزراعة شجرة الموز الكاذب (الإنسيت / Ensete)"
            ),
            syntacticFeatures = listOf(
                "بنية نحوية SOV معقدة مع سلاسل أفعال مدمجة متسلسلة (Serial Verb Constructions)"
            ),
            primaryDeities = listOf("التراث المسيحي والإسلامي الجوراجي المشترك")
        )
    )
}
