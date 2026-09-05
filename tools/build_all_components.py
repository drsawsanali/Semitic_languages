#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Semitic Languages Digital Atlas - Master Script
Prepared by: Sousan Ali Al-Hadouri | Supervised by: Prof. Dr. Ahmed Faqas
Faculty of Arts, Department of Archaeology & Semitics, Sana'a University
"""

import os
import sys
import json
import zipfile
import shutil

BASE_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))

def write_json(rel_path, data):
    full_path = os.path.join(BASE_DIR, rel_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)
    print(f"[JSON] {rel_path} ({len(data) if isinstance(data, list) else (len(data.keys()) if isinstance(data, dict) else 1)} entries)")

def write_file(rel_path, content):
    full_path = os.path.join(BASE_DIR, rel_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"[FILE] {rel_path} ({len(content)} chars)")

print("Building complete Semitic Atlas application...")

# ==========================================
# 1. APP CONFIG
# ==========================================
app_config = {
  "appId": "semitic-atlas",
  "appName": { "ar": "أطلس اللغات السامية", "en": "Semitic Languages Digital Atlas" },
  "tagline": { "ar": "الموسوعة الرقمية الأكاديمية الشاملة للغات السامية ونقوشها وحضاراتها وفهارسها", "en": "Comprehensive Academic Digital Encyclopedia & Atlas of Semitic Languages" },
  "version": "1.0.0",
  "authorship": {
    "preparedBy": { "ar": "سوسن علي الحضوري", "en": "Sousan Ali Al-Hadouri" },
    "supervisedBy": { "ar": "أ.د/ أحمد فقعس", "en": "Prof. Dr. Ahmed Faqas" },
    "institution": { "ar": "جامعة صنعاء — كلية الآداب والعلوم الإنسانية — قسم الآثار واللغات السامية", "en": "Sana'a University — Faculty of Arts and Humanities — Department of Archaeology & Semitics" },
    "level": { "ar": "مستوى ثالث: قديم", "en": "Third Level: Ancient" },
    "academicYear": "2025/2026"
  },
  "defaultLanguage": "ar",
  "supportedLanguages": ["ar", "en"],
  "theme": "dark-gold",
  "offlineFirst": True,
  "storagePrefix": "semitic_atlas_"
}
write_json("data/app-config.json", app_config)

# AI Config Example
ai_config_example = {
  "provider": "local-mock",
  "endpoint": "",
  "model": "semitic-philologist-v1",
  "apiKey": "",
  "customPrompt": "You are an expert academic philologist specializing in comparative Semitic linguistics, epigraphy, and historical phonology. Cite primary inscriptions and standard academic references.",
  "enabled": False,
  "offlineFallbackEnabled": True
}
write_json("data/ai-config.example.json", ai_config_example)

# ==========================================
# 2. LANGUAGE FAMILIES
# ==========================================
families = [
  {
    "id": "east-semitic",
    "name": { "ar": "السامية الشرقية", "en": "East Semitic" },
    "glyph": "𒀭",
    "color": "#9a6c36",
    "description": {
      "ar": "الفرع السامي الرافدي المكتوب بالخط المسماري، ويشمل الأكادية (بفرعيها البابلي والآشوري) والإيبلاوية.",
      "en": "The Mesopotamian cuneiform branch of Semitic, including Akkadian (Babylonian and Assyrian dialects) and Eblaite."
    },
    "geography": { "ar": "بلاد الرافدين وسورية الداخلية", "en": "Mesopotamia and inland Syria" },
    "period": { "start": "-2600", "end": "0100", "display": { "ar": "2600 ق.م – 100 م", "en": "2600 BCE – 100 CE" } },
    "linguisticFeatures": [
      { "title": { "ar": "الترتيب الفعلي SOV", "en": "SOV Word Order" }, "description": { "ar": "تأثرت بالسومرية في وضع الفعل في نهاية الجملة.", "en": "Influenced by Sumerian substrate placing the verb at the end of clauses." } },
      { "title": { "ar": "النون الوقائية والميمية", "en": "Mimation and Case System" }, "description": { "ar": "نظام حالات إعرابية ثلاثي (رفع -um، نصب -am، جر -im) وميمية اسمية.", "en": "Tripartite case system (-um, -am, -im) with nominal mimation." } }
    ]
  },
  {
    "id": "northwest-semitic",
    "name": { "ar": "السامية الشمالية الغربية", "en": "Northwest Semitic" },
    "glyph": "𐤀",
    "color": "#176b69",
    "description": {
      "ar": "يشمل اللغات الكنعانية (الفينيقية، البونيقية، العبرية، المؤابية، العمونية، الأدومية)، والأوغاريتية، والعمورية.",
      "en": "Includes the Canaanite group (Phoenician, Punic, Hebrew, Moabite, Ammonite, Edomite), Ugaritic, and Amorite."
    },
    "geography": { "ar": "بلاد الشام والساحل الفينيقي وغرب المتوسط", "en": "Levant, Phoenician coast, and Western Mediterranean" },
    "period": { "start": "-2000", "end": "0500", "display": { "ar": "2000 ق.م – العصر الحديث", "en": "2000 BCE – Present" } },
    "linguisticFeatures": [
      { "title": { "ar": "التحول الكنعاني", "en": "Canaanite Vowel Shift" }, "description": { "ar": "تحول الصائت الطويل الممدود (*ā > ō) في الكنعانية.", "en": "The regular shift of Proto-Semitic long *ā to ō." } },
      { "title": { "ar": "ابتكار الأبجدية الخطية", "en": "Linear Alphabet Genesis" }, "description": { "ar": "تطور الأبجدية الفينيقية الكنعانية المكونة من 22 حرفاً صامتاً.", "en": "Development of the 22-letter linear consonantal alphabet." } }
    ]
  },
  {
    "id": "aramaic",
    "name": { "ar": "المجموعة الآرامية", "en": "Aramaic Complex" },
    "glyph": "𐡀",
    "color": "#4f668e",
    "description": {
      "ar": "شجرة آرامية متصلة من الآرامية القديمة إلى الإمبراطورية والسريانية والنبطية والتدمرية والمندائية واللهجات الحديثة.",
      "en": "Continuous Aramaic tradition spanning Old, Imperial, Syriac, Nabataean, Palmyrene, Mandaic, and Neo-Aramaic dialects."
    },
    "geography": { "ar": "الشرق الأدنى القديم، بلاد الشام، بلاد الرافدين", "en": "Ancient Near East, Levant, Mesopotamia" },
    "period": { "start": "-1000", "end": None, "display": { "ar": "1000 ق.م – مستمرة", "en": "1000 BCE – Present" } },
    "linguisticFeatures": [
      { "title": { "ar": "أداة التعريف اللاحقة (-ā)", "en": "Postpositive Definite Article" }, "description": { "ar": "إلحاق الألف في نهاية الاسم كأداة تعريف وتأكيد.", "en": "Suffixed aleph (-ā) marking the emphatic/determinate state." } },
      { "title": { "ar": "تبسيط الصوامت بين الأسنانية", "en": "Interdental Shift to Dentals" }, "description": { "ar": "تحول الذال والثاء والظاء إلى دال وتاء وطاء.", "en": "Shift of Proto-Semitic interdentals to dental stops (d, t, ṭ)." } }
    ]
  },
  {
    "id": "arabic-ana",
    "name": { "ar": "العربية والعربية الشمالية القديمة", "en": "Arabic & Ancient North Arabian" },
    "glyph": "𐪀",
    "color": "#8c4d28",
    "description": {
      "ar": "يشمل العربية الفصحى ولهجاتها القديمة، بالإضافة إلى نقوش البادية الشمالية (الثمودية، الصفائية، الديدانية، اللحيانية، الحسمائية، التيمائية).",
      "en": "Covers Classical Arabic and its ancient dialects, along with Ancient North Arabian epigraphic varieties (Thamudic, Safaitic, Dadanitic, Lihyanite, Hismaic, Taymanitic)."
    },
    "geography": { "ar": "شبه الجزيرة العربية، بادية الشام، شمال أفريقيا", "en": "Arabian Peninsula, Syrian Desert, North Africa" },
    "period": { "start": "-0800", "end": None, "display": { "ar": "800 ق.م – مستمرة", "en": "800 BCE – Present" } },
    "linguisticFeatures": [
      { "title": { "ar": "الاحتفاظ بالنظام الصوتي الكامل (28 صامتاً)", "en": "Phonological Conservatism" }, "description": { "ar": "المحافظة على مخارج الحروف السامية القديمة كاملة بما فيها الضاد والظاء والعين والحاء.", "en": "Retention of 28 consonants preserving Proto-Semitic distinctions." } },
      { "title": { "ar": "أداة التعريف (الـ / هـ)", "en": "Definite Article Variants" }, "description": { "ar": "استخدام (الـ) في الفصحى و(هـ/هن) في النقوش الشمالية.", "en": "Use of 'al-' in Arabic proper vs. 'h-/hn-' in ANA inscriptions." } }
    ]
  },
  {
    "id": "ancient-south-arabian",
    "name": { "ar": "السامية الجنوبية القديمة (الصيهدية)", "en": "Ancient South Arabian (Sayhadic)" },
    "glyph": "𐩱",
    "color": "#9e7b29",
    "description": {
      "ar": "لغات ممالك اليمن القديم المكتوبة بخط المسند والزبور: السبئية، المعينية، القتبانية، الحضرمية، والأوسانية.",
      "en": "Languages of ancient South Arabian kingdoms written in Musnad and Zabur scripts: Sabaic, Minaic, Qatabanic, Hadramitic, and Awsanian."
    },
    "geography": { "ar": "جنوب شبه الجزيرة العربية (اليمن، ظفار، نجران)", "en": "Southern Arabia (Yemen, Dhofar, Najran)" },
    "period": { "start": "-1000", "end": "0600", "display": { "ar": "1000 ق.م – 600 م", "en": "1000 BCE – 600 CE" } },
    "linguisticFeatures": [
      { "title": { "ar": "أداة التعريف اللاحقة بالنون (-ن)", "en": "Suffixed Definite Article (-n)" }, "description": { "ar": "إلحاق النون بآخر الاسم للتعريف (بيت-ن = البيت).", "en": "Definiteness marked by suffixed -n (e.g., bayt-n = the house)." } },
      { "title": { "ar": "الخط المسند الأبجدي الكامل (29 حرفاً)", "en": "29-Consonant Musnad Script" }, "description": { "ar": "تمثيل دقيق لجميع الصوامت السامية بدون لبس.", "en": "Monolithic geometric script representing all 29 consonants." } }
    ]
  },
  {
    "id": "modern-south-arabian",
    "name": { "ar": "السامية الجنوبية الحديثة", "en": "Modern South Arabian" },
    "glyph": "𐩥",
    "color": "#2c6e49",
    "description": {
      "ar": "لغات شفهية حية مستقلة منطوقة في جنوب الجزيرة العربية وسقطرى: المهرية، السقطرية، الشحرية (الجبالية)، الحرسوسية، البطحرية، والهوبيوت.",
      "en": "Independent living oral languages of southern Arabia and Socotra: Mehri, Soqotri, Shehri/Jibbali, Harsusi, Bathari, and Hobyot."
    },
    "geography": { "ar": "محافظة المهرة وسقطرى وظفار والوسطى العمانية", "en": "Al-Mahrah, Socotra, Dhofar, and central Oman" },
    "period": { "start": "-0500", "end": None, "display": { "ar": "تراث لغوي حي ومستمر", "en": "Living oral traditions" } },
    "linguisticFeatures": [
      { "title": { "ar": "الصوامت الجانبية المقذوفة", "en": "Lateral Ejectives" }, "description": { "ar": "الاحتفاظ بالضاد الجانبية القديمة والأصوات المقذوفة غير المجهورة.", "en": "Preservation of lateral fricatives and ejective consonants." } },
      { "title": { "ar": "صيغ الأفعال المزدوجة والمطاوعة", "en": "Complex Dual and Aspect Morphology" }, "description": { "ar": "أنظمة تصريف دقيقة للمثنى والجماعة.", "en": "Elaborate dual marking in verbs, pronouns, and nouns." } }
    ]
  },
  {
    "id": "ethiosemitic",
    "name": { "ar": "السامية الإثيوبية (الحبشية)", "en": "Ethiosemitic" },
    "glyph": "ሀ",
    "color": "#6d3b71",
    "description": {
      "ar": "لغات القرن الأفريقي السامية المتفرعة من الجعزية الكلاسيكية: الأمهرية، التجرينية، التجرية، الهررية، الأرغوبية، لغات الجوراجي، والغافاتية.",
      "en": "Semitic languages of the Horn of Africa derived from or parallel to Ge'ez: Amharic, Tigrinya, Tigre, Harari, Argobba, Gurage group, and Gafat."
    },
    "geography": { "ar": "إثيوبيا وإريتريا والقرن الأفريقي", "en": "Ethiopia, Eritrea, and Horn of Africa" },
    "period": { "start": "-0500", "end": None, "display": { "ar": "500 ق.م – مستمرة", "en": "500 BCE – Present" } },
    "linguisticFeatures": [
      { "title": { "ar": "الخط المقطعي (الفيدل)", "en": "Fidel Abugida Script" }, "description": { "ar": "كتابة تجمع الصامت مع الصائت في علامة مقطعية واحدة تتغير حركاتها بـ 7 مراتب.", "en": "Abugida script where base consonants change form for 7 vowel orders." } },
      { "title": { "ar": "الصوامت المقذوفة الحنجرية", "en": "Ejective Consonants" }, "description": { "ar": "تحول الصوامت المطبقة السامية إلى أصوات قذفية حنجرية (p', t', k', s', č').", "en": "Glottalic ejective articulation replacing Proto-Semitic velarized stops." } }
    ]
  }
]
write_json("data/language-families.json", families)

print("Writing datasets generator script...")
