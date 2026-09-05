const fs = require('fs');
const path = require('path');
const { execSync } = require('child_process');

const ROOT_DIR = path.resolve(__dirname, '..');

function writeJson(relPath, data) {
  const fullPath = path.join(ROOT_DIR, relPath);
  fs.mkdirSync(path.dirname(fullPath), { recursive: true });
  fs.writeFileSync(fullPath, JSON.stringify(data, null, 2), 'utf-8');
  console.log(`[JSON] ${relPath} written.`);
}

function writeFile(relPath, content) {
  const fullPath = path.join(ROOT_DIR, relPath);
  fs.mkdirSync(path.dirname(fullPath), { recursive: true });
  fs.writeFileSync(fullPath, content, 'utf-8');
  console.log(`[FILE] ${relPath} written.`);
}

const fs = require('fs');
const path = require('path');
const { execSync } = require('child_process');

const ROOT_DIR = path.resolve(__dirname, '..');

function writeJson(relPath, data) {
  const fullPath = path.join(ROOT_DIR, relPath);
  fs.mkdirSync(path.dirname(fullPath), { recursive: true });
  fs.writeFileSync(fullPath, JSON.stringify(data, null, 2), 'utf-8');
  console.log(`[JSON] ${relPath} (${Array.isArray(data) ? data.length : 'obj'})`);
}

function writeFile(relPath, content) {
  const fullPath = path.join(ROOT_DIR, relPath);
  fs.mkdirSync(path.dirname(fullPath), { recursive: true });
  fs.writeFileSync(fullPath, content, 'utf-8');
  console.log(`[FILE] ${relPath}`);
}

console.log("Master Node.js builder ready.");

