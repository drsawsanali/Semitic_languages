#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Semitic Languages Digital Atlas - Master Build Tool
Prepared by: Sousan Ali Al-Hadouri
Supervised by: Prof. Dr. Ahmed Faqas
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
    print(f"Generated: {rel_path}")

def write_file(rel_path, content):
    full_path = os.path.join(BASE_DIR, rel_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"Generated: {rel_path}")

print("Master script ready.")
