const fs = require('fs');
const path = require('path');
const { execSync } = require('child_process');

function ensureDir(dir) {
  if (!fs.existsSync(dir)) {
    fs.mkdirSync(dir, { recursive: true });
  }
}

console.log("Generating Semitic Atlas complete package...");
