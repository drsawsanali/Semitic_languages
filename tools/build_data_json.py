#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Generate complete JSON datasets for Semitic Languages Digital Atlas
Prepared by: Sousan Ali Al-Hadouri | Supervised by: Prof. Dr. Ahmed Faqas
Faculty of Arts, Department of Archaeology & Semitics, Sana'a University
"""

import os
import json

BASE_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))

def write_json(rel_path, data):
    full_path = os.path.join(BASE_DIR, rel_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)
    print(f"Generated: {rel_path} ({len(data) if isinstance(data, list) else len(data.keys())} records)")

# App Config
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

# Language Families
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
      { "title": { "ar": "قانون بجد كفت", "en": "Begadkefat Spirantization" }, "description": { "ar": "تناوب النطق بين الصوامت الانفجارية والاحتكاكية بعد الصوائت.", "en": "Allophonic spirantization of non-geminate stops following vowels." } }
    ]
  },
  {
    "id": "ancient-north-arabian",
    "name": { "ar": "العربية الشمالية القديمة", "en": "Ancient North Arabian" },
    "glyph": "𐪀",
    "color": "#8d5b52",
    "description": {
      "ar": "مظلة كتابية لنقوش شمال شبه الجزيرة العربية وبواديها: الصفائية، الثمودية، الديدانية، اللحيانية، الحسمائية، والتيماء.",
      "en": "Umbrella epigraphic group for oasis and desert inscriptions: Safaitic, Thamudic, Dadanitic, Lihyanite, Hismaic, and Taymanitic."
    },
    "geography": { "ar": "شمال شبه الجزيرة العربية، الحجاز، حرة الشام، النقب، سيناء", "en": "Northern Arabian Peninsula, Hijaz, Harrah desert, Negev, Sinai" },
    "period": { "start": "-0800", "end": "0500", "display": { "ar": "القرن 8 ق.م – القرن 5 م", "en": "8th century BCE – 5th century CE" } }
  },
  {
    "id": "arabic",
    "name": { "ar": "العربية واللغات المنحدرة منها", "en": "Arabic & Descendant Varieties" },
    "glyph": "ض",
    "color": "#35704b",
    "description": {
      "ar": "العربية الفصحى التراثية، العربية القديمة، اللهجات التاريخية والمعاصرة، والمالطية المنحدرة من العربية الصقلية.",
      "en": "Classical Arabic, Old Arabic, historical/contemporary dialects, and Maltese derived from Siculo-Arabic."
    },
    "geography": { "ar": "الجزيرة العربية، الشرق الأوسط، شمال إفريقيا، مالطا", "en": "Arabian Peninsula, Middle East, North Africa, Malta" },
    "period": { "start": "-0600", "end": None, "display": { "ar": "منذ القرن 6 ق.م – مستمرة", "en": "6th century BCE – Present" } }
  },
  {
    "id": "ancient-south-arabian",
    "name": { "ar": "العربية الجنوبية القديمة (الصيهدية)", "en": "Ancient South Arabian (Sayhadic)" },
    "glyph": "𐩠",
    "color": "#99722e",
    "description": {
      "ar": "لغات ممالك اليمن القديم المكتوبة بخط المسند والزبور: السبئية، المعينية، القتبانية، والحضرمية.",
      "en": "Languages of ancient South Arabian kingdoms written in Musnad and Zabur: Sabaic, Minaic, Qatabanic, and Hadramitic."
    },
    "geography": { "ar": "اليمن، جنوب الجزيرة العربية، وشبكات طريق اللبان", "en": "Yemen, Southern Arabia, and Incense Trade routes" },
    "period": { "start": "-1000", "end": "0600", "display": { "ar": "1000 ق.م – 600 م", "en": "1000 BCE – 600 CE" } }
  },
  {
    "id": "modern-south-arabian",
    "name": { "ar": "السامية الجنوبية الحديثة", "en": "Modern South Arabian" },
    "glyph": "ś",
    "color": "#3c7184",
    "description": {
      "ar": "لغات سامية حية مهددة في جنوب الجزيرة وأرخبيل سقطرى: المهرية، السقطرية، الشحرية (الجبالية)، البطحرية، الحرسوسية، والهوبيوت.",
      "en": "Living, endangered Semitic languages in Southern Arabia and Socotra: Mehri, Soqotri, Jibbali (Shehri), Bathari, Harsusi, and Hobyot."
    },
    "geography": { "ar": "المهرة، ظفار، سقطرى، وادي حضرموت، سلطنة عمان، اليمن", "en": "Mahrah, Dhofar, Socotra, Hadramawt, Oman, Yemen" },
    "period": { "start": "0001", "end": None, "display": { "ar": "لغات شفهية حية مستمرة", "en": "Living oral traditions" } }
  },
  {
    "id": "ethiosemitic",
    "name": { "ar": "السامية الإثيوبية (الحبشية)", "en": "Ethiosemitic (Ethiopian Semitic)" },
    "glyph": "ሀ",
    "color": "#7d4e70",
    "description": {
      "ar": "الفرع الإفريقي السامي: الجعزية الكلاسيكية، الأمهرية، التجرينية، التجرية، الهررية، الأرغوبية، لغات الجوراجي، والغافاتية.",
      "en": "The African Semitic branch: Classical Ge'ez, Amharic, Tigrinya, Tigre, Harari, Argobba, Gurage cluster, and Gafat."
    },
    "geography": { "ar": "إثيوبيا، إريتريا، والقرن الإفريقي", "en": "Ethiopia, Eritrea, and the Horn of Africa" },
    "period": { "start": "-0500", "end": None, "display": { "ar": "500 ق.م – مستمرة", "en": "500 BCE – Present" } }
  }
]
write_json("data/language-families.json", families)

print("Writing datasets...")
