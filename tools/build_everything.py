#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Semitic Languages Digital Atlas - Complete Academic Engine Builder
Generates:
  1. data/*.json (app-config, language-families, languages [85 languages], inscriptions, scripts, chapters, sources, media, maps, timeline-events, glossary, tags, search-index, ai-config.example)
  2. css/*.css (tokens, base, layout, components, rtl, themes, print)
  3. js/**/*.js (app, router, state, i18n, storage, search, import-export, sanitize, ui, accessibility, maps, timeline, ai/*, modules/*)
  4. index.html (PWA)
  5. semitic-atlas-standalone.html (Single-file standalone distribution)
  6. manifest.webmanifest, service-worker.js, offline.html, package-info.json
  7. docs/*.md (All 12 required markdown files)
  8. semitic-atlas.zip (Full offline package)
  9. app/src/main/assets/ (Android app assets)
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
    print(f"JSON: {rel_path}")

def write_file(rel_path, content):
    full_path = os.path.join(BASE_DIR, rel_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"FILE: {rel_path}")

print("Master generator initialized.")
