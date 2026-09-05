#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Generate all academic datasets for the Semitic Languages Digital Atlas.
Prepared by: Sousan Ali Al-Hadouri | Supervised by: Prof. Dr. Ahmed Faqas
"""

import os
import json

BASE_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))

def write_json(rel_path, data):
    full_path = os.path.join(BASE_DIR, rel_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)
    print(f"[JSON] {rel_path} generated successfully.")

# APP CONFIG
app_config = {
  "appId": "semitic-atlas",
  "appName": { "ar": "أطلس اللغات السامية", "en": "Semitic Languages Digital Atlas" },
  "tagline": { "ar": "الموسوعة الرقمية الأكاديمية الشاملة للغات السامية ونقوشها وحضاراتها وفهارسها", "en": "Comprehensive Academic Digital Encyclopedia & Atlas of Semitic Languages" },
  "version": "1.0.0",
  "authorship": {
    "preparedBy": { "ar": "سوسن علي الحضوري", "en": "Sousan Ali Al-Hadouri" },
    "supervisedBy": { "ar": "أ.د/ أحمد فقعس", "en": "Prof. Dr. Ahmed Faqas" },
    "institution": { "ar": "جامعة صنعاء — كلية الآداب والعلوم الإنسانية — قسم الآثار واللغات السامية", "en": "Sana'a University — Faculty of Arts and Humanities — Department of Archaeology & Semitics" },
    "degreeLevel": { "ar": "مستوى ثالث: قديم", "en": "Third Level: Ancient" },
    "date": "2026"
  },
  "defaultLanguage": "ar",
  "supportedLanguages": ["ar", "en"],
  "theme": "dark-gold",
  "offlineFirst": True,
  "storagePrefix": "semitic_atlas_"
}
write_json("data/app-config.json", app_config)

# AI CONFIG EXAMPLE
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

print("Config created.")
