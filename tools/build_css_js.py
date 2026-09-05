#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Generate complete CSS and JS modules for Semitic Languages Digital Atlas
Prepared by: Sousan Ali Al-Hadouri | Supervised by: Prof. Dr. Ahmed Faqas
"""

import os

BASE_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))

def write_file(rel_path, content):
    full_path = os.path.join(BASE_DIR, rel_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"Generated: {rel_path}")

print("Generating CSS & JS modules...")
