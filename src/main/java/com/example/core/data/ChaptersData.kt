package com.example.core.data

import com.example.core.model.*

object ChaptersData {
    val ENCYCLOPEDIA_CHAPTERS: List<ChapterContent> = listOf(
        // === UNIT 1: ORIGINS & HISTORICAL GEOGRAPHY (Chapters 1 - 10) ===
        ChapterContent(
            id = "chap_1",
            chapterNumber = 1,
            unitNumber = 1,
            titleAr = "شجرة اللغات السامية: التصنيف الفيلولوجي والموطن الأصلي (Urheimat)",
            titleEn = "Semitic Language Tree: Philological Classification & Urheimat",
            author = "الباحثة: سوسن علي الحضوري (إشراف: أ.د. أحمد فقعس)",
            institution = "جامعة صنعاء - كلية الآداب والعلوم الإنسانية - قسم الآثار واللغات القديمة",
            summaryAr = "دراسة مقارنة في تأصيل الموطن الجغرافي الأول للشعوب السامية وفروعها الكبرى (السامية الشرقية، الشمالية الغربية، العربية، الجنوبية القديمة، والسامية الإثيوبية)، مع تحليل النظريات الأثرية واللسانية الحديثة.",
            fullLatexContent = """
\section*{المقدمة المنهجية والتصنيف الفيلولوجي}
تحتل عائلة اللغات السامية (Semitic Languages) مكانة مركزية في تاريخ اللسانيات المقارنة والحضارات الإنسانية المبكرة؛ إذ تمثل أقدم عائلة لغوية موثقة بسجلات كتابية متصلة تزيد عن خمسة آلاف عام منذ ظهور الرُقيمات المسمارية في الألف الثالثة قبل الميلاد.

\subsection*{شجرة الفروع السامية الستة}
\begin{enumerate}
    \item \textbf{السامية الشرقية (East Semitic):} الأكادية بفرعيها (البابلي والآشوري) والإيبلاوية برأس الشمرا وتل مرديخ.
    \item \textbf{السامية الشمالية الغربية (Northwest Semitic):} الأوغاريتية، الفينيقية، البونيقية، المؤابية، العمونية، الأدومية، والآرامية بفروعها.
    \item \textbf{العربية والشمالية القديمة (Arabic & ANA):} العربية الفصحى، الصفائية، الثمودية، الديدانية، واللحيانية.
    \item \textbf{السامية الجنوبية القديمة (Sayhadic / Musnad):} السبئية، المعينية، القتبانية، والحضرمية.
    \item \textbf{السامية الجنوبية الحديثة (Modern South Arabian):} المهرية، السقطرية، الشحرية (الجبالية)، البطحرية، والحرسوسية.
    \item \textbf{السامية الإثيوبية (Ethiosemitic):} الجعزية الكلاسيكية، الأمهرية، التجرينية، والتجرية.
\end{enumerate}

\subsection*{جدول المقارنة الزمنية والجغرافية للفروع السامية}
\begin{center}
\begin{tabular}{|l|c|r|}
\hline
\textbf{الفرع السامي} & \textbf{تاريخ أول تدوين} & \textbf{المركز الجغرافي} \\
\hline
السامية الشرقية & 2600 ق.م & بلاد الرافدين (العراق وسوريا) \\
السامية الشمالية الغربية & 1400 ق.م & بلاد الشام وحوض المتوسط \\
السامية الجنوبية القديمة & 1000 ق.م & اليمن وجنوب الجزيرة العربية \\
السامية الإثيوبية & 500 ق.م & أكسوم والقرن الإفريقي \\
\hline
\end{tabular}
\end{center}
            """.trimIndent(),
            footnotes = listOf(
                "Hetzron, Robert. The Semitic Languages. Routledge, 1997, pp. 1-15.",
                "Moscati, S. et al. An Introduction to the Comparative Grammar of the Semitic Languages. Harrassowitz, 1980."
            ),
            relatedInscriptionIds = listOf("hammurabi_stele", "mesha_stele", "marib_dam_inscription"),
            keywords = listOf("التصنيف الفيلولوجي", "الموطن الأصلي", "شجرة اللغات", "السامية الأم", "الرافدين", "اليمن")
        ),
        ChapterContent(
            id = "chap_2",
            chapterNumber = 2,
            unitNumber = 1,
            titleAr = "حواضر كنعان وممالك الساحل: نشأة جبيل وصور وصيدا ومملكة أوغاريت",
            titleEn = "Canaanite Urban Centers: Byblos, Tyre, Sidon & Ugarit",
            author = "الباحثة: سوسن علي الحضوري",
            institution = "جامعة صنعاء - قسم الآثار",
            summaryAr = "استعراض تاريخي أثري لمراكز الاستيطان الكنعاني والفينيقي وتطور الموانئ والتجارة البحرية الدولية في بلاد الشام وحوض البحر الأبيض المتوسط.",
            fullLatexContent = """
\section*{الحواضر الكنعانية الكبرى وتطور الملاحة}
شكلت الموانئ الكنعانية الطبيعية في جبيل (بيبلوس) وصور وصيدا وأرواد ورأس الشمرا (أوغاريت) شرايين التجارة العالمية في العصرين البرونزي والحديدي.

\subsection*{مملكة جبيل وأول أبجدية خطية}
تعد جبيل (𐤂𐤁𐤋 Gubla) المركز الروحي الأبرز، ومنها عُثر على تابوت الملك أحيرام (القرن العاشر ق.م) الذي دُشنت به الأبجدية الفينيقية الكلاسيكية المكونة من 22 حرفاً.
            """.trimIndent(),
            footnotes = listOf(
                "Aubet, Maria Eugenia. The Phoenicians and the West. Cambridge University Press, 2001.",
                "Donner & Röllig. Kanaanäische und aramäische Inschriften (KAI 1)."
            ),
            relatedInscriptionIds = listOf("ahiram_sarcophagus", "karatepe_bilingual"),
            keywords = listOf("جبيل", "أوغاريت", "صور", "الفينيقية", "الملاحة البحرية")
        ),
        ChapterContent(
            id = "chap_3",
            chapterNumber = 3,
            unitNumber = 1,
            titleAr = "ممالك اليمن القديم: سبأ ومعين وقتبان وحضرموت وتجارة اللبان الدولية",
            titleEn = "Ancient South Arabian Kingdoms: Saba, Ma'in, Qataban, Hadramawt",
            author = "الباحثة: سوسن علي الحضوري",
            institution = "جامعة صنعاء - قسم الآثار واللغات القديمة",
            summaryAr = "تاريخ ممالك جنوب الجزيرة العربية وحضارة المسند العظيمة، وهندسة السدود وشبكات الري، ودور قوافل طريق البخور في ربط العالم القديم.",
            fullLatexContent = """
\section*{حضارة جنوب الجزيرة العربية وخط المسند}
تألقت ممالك الصيهد في اليمن القديم (سبأ، معين، قتبان، وحضرموت) بفضل منظومات الري العبقرية واحتكار تجارة البخور واللبان والمر من موانئ قنا وظفار إلى أسواق الشام ومصر واليونان.

\subsection*{العمارة والهندسة المائية في مأرب}
يعد سد مأرب العظيم أعظم إنجاز هندسي مائي في الشرق الأدنى القديم، خلدت نقوشه بطولات المكارب والملوك السبئيين بخط المسند الصخري البارز.
            """.trimIndent(),
            footnotes = listOf(
                "Biella, Joan C. Dictionary of Old South Arabic: Sabaean Dialect. Harvard Semitic Studies, 1982.",
                "Robin, Christian. L'Arabie antique de Karib'il à Mahomet. Revue des mondes musulmans, 1991."
            ),
            relatedInscriptionIds = listOf("marib_dam_inscription"),
            keywords = listOf("سبأ", "معين", "قتبان", "حضرموت", "المسند", "سد مأرب", "طريق اللبان")
        ),

        // === UNIT 2: PHONOLOGY & SOUND SHIFTS (Chapters 11 - 20) ===
        ChapterContent(
            id = "chap_11",
            chapterNumber = 11,
            unitNumber = 2,
            titleAr = "النظام الصوتي للسامية الأم (Proto-Semitic Phonology) ومخارج الحروف الدولية (IPA)",
            titleEn = "Proto-Semitic Phonological System & International Phonetic Alphabet (IPA)",
            author = "الباحثة: سوسن علي الحضوري",
            institution = "جامعة صنعاء - قسم الآثار",
            summaryAr = "إعادة تركيب فونولوجية لصوامت السامية الأم الـ 29، وتصنيف الصوامت المطبقة، الحلقية، والقذفية، ورسم المصوتات الثلاثية وحركاتها.",
            fullLatexContent = """
\section*{إعادة تركيب أصوات السامية الأم}
تتميز اللغة السامية الأم بامتلاكها 29 فونيماً صامتاً متمايزاً، تشتمل على نظام ثلاثي للصوامت: صوامت مهموسة، صوامت مجهورة، وصوامت مطبقة (أو مقذوفة Ejectives).

\subsection*{مصفوفة الصوامت المطبقة والحلقية}
\begin{itemize}
    \item \textbf{الصوامت المطبقة:} الصاد (/sˤ/), الطاء (/tˤ/), القاف (/q/ أو /kʼ/), والظاء (/ðˤ/).
    \item \textbf{الصوامت الجانبية:} الضاد القديمة (/ɬˤ/ أو /ɮˤ/) والشين الجانبية (/ɬ/).
    \item \textbf{الحلقيات والحنجريات:} الحاء (/ħ/), العين (/ʕ/), الهمزة (/ʔ/), والهاء (/h/).
\end{itemize}
            """.trimIndent(),
            footnotes = listOf(
                "Brockelmann, Carl. Grundriss der vergleichenden Grammatik der semitischen Sprachen. Berlin, 1908.",
                "Huehnergard, John. Proto-Semitic Language and Culture. The Semitic Languages, 2019."
            ),
            relatedInscriptionIds = listOf("hammurabi_stele", "ahiram_sarcophagus", "marib_dam_inscription"),
            keywords = listOf("الفونولوجيا", "السامية الأم", "IPA", "الصوامت المطبقة", "الحلقيات", "الصوتيات")
        ),
        ChapterContent(
            id = "chap_12",
            chapterNumber = 12,
            unitNumber = 2,
            titleAr = "قانون التحول الكنعاني (*ā > ō) ومقارنته بالأوغاريتية والآرامية والعربية",
            titleEn = "The Canaanite Vowel Shift (*ā > ō) in Comparative Semitic Context",
            author = "الباحثة: سوسن علي الحضوري",
            institution = "جامعة صنعاء - قسم الآثار",
            summaryAr = "تحليل صوتي دقيق لظاهرة التحول الكنعاني، وظروف حدوثها، والأدلة الإبيغرافية من نقوش ميشع وأحيرام ورسائل تل العمارنة.",
            fullLatexContent = """
\section*{ظاهرة التحول الكنعاني للصائت الممدود}
يعد قانون التحول الكنعاني (*ā > ō) أهم معيار صوتي في تصنيف اللغات الشمالية الغربية؛ إذ تحول الصائت الممدود العام في السامية الأم إلى ضمة مشبعة في الفينيقية والمؤابية والعبرية، بينما امتنعت الأوغاريتية والآرامية والعربية والسبئية عن هذا التحول.

\subsection*{شواهد إبيغرافية مقارنة}
\begin{center}
\begin{tabular}{|l|c|c|r|}
\hline
\textbf{الجذر السامي} & \textbf{السامية الأم} & \textbf{الفينيقية/المؤابية} & \textbf{العربية/السبئية} \\
\hline
*š-l-m & *šalāmu- & šalōm & salām \\
*ṭ-y-b & *ṭābu- & ṭōb & ṭayyib / ṭāb \\
*m-l-k & *malkātu- & milkōt & malikāt \\
\hline
\end{tabular}
\end{center}
            """.trimIndent(),
            footnotes = listOf(
                "Krahmalkov, Charles R. A Phoenician-Punic Grammar. Brill, 2001.",
                "Garr, W. Randall. Dialect Geography of Syria-Palestine, 1000-586 B.C.E. Eisenbrauns, 1985."
            ),
            relatedInscriptionIds = listOf("mesha_stele", "ahiram_sarcophagus", "karatepe_bilingual"),
            keywords = listOf("التحول الكنعاني", "الصوائت", "المؤابية", "الفينيقية", "ميشع", "القوانين الصوتية")
        ),

        // === UNIT 3: MORPHOLOGY & VERBS (Chapters 21 - 30) ===
        ChapterContent(
            id = "chap_21",
            chapterNumber = 21,
            unitNumber = 3,
            titleAr = "الجذوع اللفظية السامية (Binyanim): منظومة الأوزان الخمسة وتطورها الصرفي",
            titleEn = "Semitic Verbal Systems (Binyanim): The Five Major Stems (G, D, C, N, t)",
            author = "الباحثة: سوسن علي الحضوري",
            institution = "جامعة صنعاء - قسم الآثار",
            summaryAr = "دراسة مورفولوجية شاملة لأوزان الفعل في الأكادية (G, D, Š, N)، والكنعانية (Qal, Piel, Yiphil, Nifal)، والعربية والسبئية والجعزية.",
            fullLatexContent = """
\section*{المورفولوجيا والجذوع الفعلية المقارنة}
تقوم بنية الأفعال السامية على الجذر الصامت (غالباً ثلاثي) المدمج في قوالب صرفية تسمى الجذوع اللفظية (Verbal Stems / Binyanim) لتوليد الدلالات الدقيقة:
\begin{enumerate}
    \item \textbf{جذع G (Grundstamm / المجرد):} للدلالة على الحدث الأساسي (فَعَل / iprus / qtl).
    \item \textbf{جذع D (Doppelungsstamm / التكثيف):} بتضعيف عين الفعل للتكثير والتعدية (فَعَّل / uparris).
    \item \textbf{جذع C/Š/H (Kausativ / السببية):} بالتعدية بالسين أو الهاء أو الهمزة (أَفْعَل / šuprus / yaphil).
    \item \textbf{جذع N (المطاوعة والانفعال):} بزيادة النون الدالة على الانفعال والمجهول (انْفَعَل / naprus).
    \item \textbf{جذوع t (الانعكاسي والتبادلي):} بزيادة التاء للدلالة على المطاوعة والمشاركة (تَفَعَّل / iptaras).
\end{enumerate}
            """.trimIndent(),
            footnotes = listOf(
                "Kouwenberg, N. J. C. The Akkadian Verb and Its Semitic Background. Eisenbrauns, 2010.",
                "Wright, William. Lectures on the Comparative Grammar of the Semitic Languages. Cambridge, 1890."
            ),
            relatedInscriptionIds = listOf("hammurabi_stele", "mesha_stele", "ahiram_sarcophagus"),
            keywords = listOf("الصرف", "الأوزان الفعلية", "Binyanim", "المجرد", "المكثف", "السببي", "المطاوع")
        ),

        // === UNIT 4: SYNTAX & STYLE (Chapters 31 - 40) ===
        ChapterContent(
            id = "chap_31",
            chapterNumber = 31,
            unitNumber = 4,
            titleAr = "بناء الجملة السامية: رتبة الكلمات والتراكيب التتابعية والإعراب",
            titleEn = "Semitic Syntax: Word Order, Consecutive Structures & Nominal Cases",
            author = "الباحثة: سوسن علي الحضوري",
            institution = "جامعة صنعاء - قسم الآثار",
            summaryAr = "مقارنة نحوية بين الترتيب الفعلي الكلاسيكي VSO (في العربية والكنعانية والسبئية) والترتيب SOV في الأكادية والأمهرية، مع دراسة واو العطف التتابعية.",
            fullLatexContent = """
\section*{النحو السامي وبناء الجملة}
تتوزع اللغات السامية في بنيتها التركيبية بين نموذجين رئيسيين:
\begin{itemize}
    \item \textbf{النموذج الفعلي (VSO):} وهو السائد في السامية الشمالية الغربية (الكنعانية، الفينيقية، الأوغاريتية)، والعربية الفصحى، والسبئية المسندية.
    \item \textbf{النموذج الاسمي النهائي (SOV):} وهو السائد في الأكادية (بتأثير السومرية) واللغات الإثيوبية الحديثة كالأمهرية (بتأثير اللغات الكوشية).
\end{itemize}

\subsection*{واو العطف السردية التتابعية (Waw Consecutive)}
تعد واو العطف التتابعية إحدى أروع الظواهر الأسلوبية في السرد التاريخي الكنعاني والمؤابي؛ حيث تدخل على صيغة الفعل المضارع وتنقله دلالياً إلى ماضٍ سردي متصل.
            """.trimIndent(),
            footnotes = listOf(
                "Waltke, Bruce & O'Connor, M. An Introduction to Biblical Hebrew Syntax. Eisenbrauns, 1990.",
                "Givón, Talmy. Syntax: A Functional-Typological Introduction. John Benjamins, 2001."
            ),
            relatedInscriptionIds = listOf("mesha_stele", "deir_alla_inscription"),
            keywords = listOf("النحو", "بناء الجملة", "VSO", "SOV", "واو العطف السردية", "الإعراب")
        ),

        // === UNIT 5: EPIGRAPHY & MONUMENTS (Chapters 41 - 50) ===
        ChapterContent(
            id = "chap_41",
            chapterNumber = 41,
            unitNumber = 5,
            titleAr = "إبيغرافيا النقوش الملكية الكبرى: مسلة ميشع، تابوت أحيرام، وسد مأرب",
            titleEn = "Royal Epigraphy: Mesha Stele, Ahiram Sarcophagus & Marib Dam",
            author = "الباحثة: سوسن علي الحضوري",
            institution = "جامعة صنعاء - قسم الآثار",
            summaryAr = "قراءة فيلولوجية نقدية لنصوص النقوش الملكية والنذرية الكبرى، مع دراسة مواد التدوين والأحبار وتقنيات الفحص الطيفي الحديثة.",
            fullLatexContent = """
\section*{الإبيغرافيا والتحقيق الفيلولوجي للنقوش}
يمثل النقش الأثري الوثيقة الأولى والشهادة المعاصرة الصادقة على لغة العصر دون وساطة النساخ، ويشمل الفحص الإبيغرافي:
\begin{enumerate}
    \item دراسة مادة الحجر أو الطين أو البرونز وسياق الاكتشاف الأثري.
    \item التفريغ الحرفي الدقيق بالأبجدية الأصلية (Transliteration).
    \item التحليل المعجمي والصرفي والنحوي ومقارنة الجذور اللغوية.
    \item الفحص الطيفي متعدد الأطياف (MSI) والأشعة تحت الحمراء لكشف الكتابات الباهتة.
\end{enumerate}
            """.trimIndent(),
            footnotes = listOf(
                "Donner & Röllig. KAI, Band 1-3, 2002.",
                "Lemaire, André. Nouvelles inscriptions araméennes et phéniciennes. Maisonneuve, 2003."
            ),
            relatedInscriptionIds = listOf("mesha_stele", "ahiram_sarcophagus", "marib_dam_inscription", "namara_inscription"),
            keywords = listOf("الإبيغرافيا", "النقوش الملكية", "ميشع", "أحيرام", "سد مأرب", "النمارة", "الفحص الطيفي")
        )
    )
}
