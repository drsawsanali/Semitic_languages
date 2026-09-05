#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Semitic Languages Digital Atlas - Complete Academic Engine Builder
Generates all datasets, modules, CSS, PWA, standalone HTML, docs, ZIP, and Android assets.
Prepared by: Sousan Ali Al-Hadouri | Supervised by: Prof. Dr. Ahmed Faqas
"""

import os
import json
import zipfile
import shutil
import html

BASE_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))

def write_json(rel_path, data):
    full_path = os.path.join(BASE_DIR, rel_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)
    print(f"[OK] JSON: {rel_path}")

def write_file(rel_path, content):
    full_path = os.path.join(BASE_DIR, rel_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"[OK] File: {rel_path}")

# Import dataset definitions
from dataset_data import FAMILIES

# =========================================================================
# 1. GENERATE APP CONFIG
# =========================================================================
app_config = {
  "appId": "semitic-atlas",
  "name": "أطلس اللغات السامية",
  "appName": {
    "ar": "أطلس اللغات السامية",
    "en": "Semitic Languages Digital Atlas"
  },
  "tagline": {
    "ar": "الموسوعة الرقمية الأكاديمية الشاملة للغات السامية ونقوشها وحضاراتها وفهارسها",
    "en": "Comprehensive Academic Digital Encyclopedia & Atlas of Semitic Languages"
  },
  "version": "1.0.0",
  "ownership": {
    "preparedBy": {
      "ar": "سوسن علي الحضوري",
      "en": "Sousan Ali Al-Hadouri"
    },
    "supervisedBy": {
      "ar": "أ.د/ أحمد فقعس",
      "en": "Prof. Dr. Ahmed Faqas"
    },
    "institution": {
      "ar": "جامعة صنعاء — كلية الآداب والعلوم الإنسانية — قسم الآثار واللغات السامية",
      "en": "Sana'a University — Faculty of Arts and Humanities — Department of Archaeology & Semitics"
    },
    "level": {
      "ar": "مستوى ثالث: قديم",
      "en": "Third Level: Ancient"
    },
    "year": "2026"
  },
  "defaultLanguage": "ar",
  "supportedLanguages": ["ar", "en"],
  "theme": "dark-gold",
  "offlineFirst": True
}
write_json("data/app-config.json", app_config)
write_json("data/language-families.json", FAMILIES)

print("Config & Families written.")
