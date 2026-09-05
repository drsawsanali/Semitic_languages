#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Semitic Atlas - Complete Academic Engine & Deliverables Generator
Prepared by: Sousan Ali Al-Hadouri
Supervised by: Prof. Dr. Ahmed Faqas
Academic Institution: Sana'a University, Faculty of Arts, Department of Archaeology & Semitics
"""

import os
import json
import zipfile
import shutil

ROOT_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))
DATA_DIR = os.path.join(ROOT_DIR, "data")
DOCS_DIR = os.path.join(ROOT_DIR, "docs")
CSS_DIR = os.path.join(ROOT_DIR, "css")
JS_DIR = os.path.join(ROOT_DIR, "js")
JS_MODULES_DIR = os.path.join(JS_DIR, "modules")
JS_AI_DIR = os.path.join(JS_DIR, "ai")
ASSETS_DIR = os.path.join(ROOT_DIR, "assets")
ASSETS_ICONS_DIR = os.path.join(ASSETS_DIR, "icons")
ASSETS_MAPS_DIR = os.path.join(ASSETS_DIR, "maps")
ANDROID_ASSETS_DIR = os.path.join(ROOT_DIR, "app", "src", "main", "assets")

for d in [DATA_DIR, DOCS_DIR, CSS_DIR, JS_DIR, JS_MODULES_DIR, JS_AI_DIR, ASSETS_DIR, ASSETS_ICONS_DIR, ASSETS_MAPS_DIR, ANDROID_ASSETS_DIR]:
    os.makedirs(d, exist_ok=True)

print("Directories verified.")
