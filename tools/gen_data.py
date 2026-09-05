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

print("Writing datasets...")
