#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Semitic Languages Digital Atlas - Complete Academic Engine Generator
Generates all datasets, CSS, JS, HTML, PWA, Standalone, Docs, and ZIP package.
Authorship:
  Prepared by: Sousan Ali Al-Hadouri
  Supervised by: Prof. Dr. Ahmed Faqas
  Sana'a University, Faculty of Arts, Department of Archaeology & Semitic Languages
"""

import os
import json
import zipfile
import shutil

BASE_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))

def write_json(rel_path, data):
    full_path = os.path.join(BASE_DIR, rel_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)

def write_file(rel_path, content):
    full_path = os.path.join(BASE_DIR, rel_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w", encoding="utf-8") as f:
        f.write(content)

print("Starting generation...")
