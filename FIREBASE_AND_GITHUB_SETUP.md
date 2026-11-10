# Complete Step-by-Step Guide: Firebase Setup & GitHub APK Build

## Part 1: Firebase Setup (15 minutes)

### Step 1: Create Firebase Project

1. **Go to Firebase Console**
   - Open browser: https://console.firebase.google.com/
   - Sign in with your Google account

2. **Create New Project**
   - Click "Add project" or "Create a project"
   - Project name: `LOCIN` (or any name you want)
   - Click "Continue"

3. **Google Analytics (Optional)**
   - Toggle OFF if you don't need analytics (recommended for now)
   - Or toggle ON and select account
   - Click "Create project"
   - Wait 30-60 seconds for project creation
   - Click "Continue"

### Step 2: Add Android App to Firebase

1. **Add Android App**
   - On Firebase project homepage, click the **Android icon** (robot symbol)
   - Or click the gear icon (Settings) → Project settings → Scroll down → "Add app" → Android

2. **Register App**
   - **Android package name**: `com.locin.app` (IMPORTANT: Must match exactly!)
   - **App nickname**: LOCIN Android (optional)
   - **Debug signing certificate SHA-1**: Leave empty for now (optional)
   - Click "Register app"

3. **Download google-services.json**
   - Click "Download google-services.json"
   - **SAVE THIS FILE** - you'll need it in the next part
   - Click "Next" → "Next" → "Continue to console"

### Step 3: Enable Firebase Authentication

1. **Go to Authentication**
   - In Firebase Console left sidebar, click "Authentication"
   - Click "Get started"

2. **Enable Phone Authentication**
   - Click "Sign-in method" tab
   - Click "Phone" in the providers list
   - Toggle "Enable"
   - Click "Save"

3. **Enable Google Sign-In**
   - Still in "Sign-in method" tab
   - Click "Google" in the providers list
   - Toggle "Enable"
   - Select your support email
   - Click "Save"

### Step 4: Create Firestore Database

1. **Go to Firestore Database**
   - In Firebase Console left sidebar, click "Firestore Database"
   - Click "Create database"

2. **Setup Firestore**
   - Location: Select closest region (e.g., asia-south1 for India)
   - Click "Next"
   - Start in **Production mode** (we'll add rules later)
   - Click "Create"
   - Wait for database creation

3. **Set Security Rules**
   - Click "Rules" tab
   - Replace with:
   ```javascript
   rules_version = '2';
   service cloud.firestore {
     match /databases/{database}/documents {
       // Users can read/write their own data
       match /users/{userId} {
         allow read, write: if request.auth != null && request.auth.uid == userId;
       }
       
       // Everyone can read leaderboard
       match /leaderboard/{entry} {
         allow read: if true;
         allow write: if request.auth != null;
       }
       
       // Everyone can read active users count
       match /stats/activeUsers {
         allow read: if true;
         allow write: if request.auth != null;
       }
     }
   }
   ```
   - Click "Publish"

### Step 5: Create Realtime Database (for live user count)

1. **Go to Realtime Database**
   - In Firebase Console left sidebar, click "Realtime Database"
   - Click "Create Database"

2. **Setup Realtime Database**
   - Location: Select closest region
   - Start in **Locked mode**
   - Click "Enable"

3. **Set Security Rules**
   - Click "Rules" tab
   - Replace with:
   ```json
   {
     "rules": {
       "activeUsers": {
         ".read": true,
         ".write": "auth != null"
       },
       "users": {
         "$uid": {
           ".read": "auth != null && auth.uid == $uid",
           ".write": "auth != null && auth.uid == $uid"
         }
       }
     }
   }
   ```
   - Click "Publish"

### Step 6: Add google-services.json to Project

1. **Locate your downloaded google-services.json**
   - Should be in your Downloads folder
   - File name: `google-services.json`

2. **Copy to Project**
   - Open File Explorer
   - Navigate to: `C:\Users\chaud\Downloads\LOCIN\LOCIN-Android\app\`
   - **Paste `google-services.json` here** (directly in the `app` folder)

3. **Verify Location**
   ```
   LOCIN-Android/
   └── app/
       ├── build.gradle.kts
       ├── google-services.json  ← Should be here
       └── src/
   ```

✅ **Firebase setup complete!**

---

## Part 2: Create GitHub Repository & Setup (10 minutes)

### Step 1: Create GitHub Repository

1. **Go to GitHub**
   - Open: https://github.com
   - Sign in to your account

2. **Create New Repository**
   - Click the **"+"** icon (top right)
   - Click "New repository"

3. **Repository Settings**
   - **Repository name**: `LOCIN-Android` (or your choice)
   - **Description**: "LOCIN - Study Focus Companion Android App"
   - **Visibility**: 
     - **Private** (recommended if you want to keep code private)
     - Or **Public** (if open source)
   - **DO NOT** check "Add a README file" (we already have one)
   - **DO NOT** add .gitignore (we already have one)
   - Click "Create repository"

4. **Copy Repository URL**
   - You'll see a page with setup instructions
   - Copy the URL shown (looks like: `https://github.com/YOUR_USERNAME/LOCIN-Android.git`)
   - Keep this page open

### Step 2: Install Git (if not installed)

1. **Check if Git is installed**
   - Open Command Prompt (Win + R, type `cmd`, press Enter)
   - Type: `git --version`
   - If you see version number → Skip to Step 3
   - If you see error → Continue below

2. **Download Git** (only if not installed)
   - Go to: https://git-scm.com/download/win
   - Download will start automatically
   - Run installer
   - Click "Next" on all screens (default options are fine)
   - Click "Install" → "Finish"
   - Close and reopen Command Prompt

3. **Configure Git** (first time only)
   ```bash
   git config --global user.name "Your Name"
   git config --global user.email "your.email@example.com"
   ```

---

## Part 3: Push Code to GitHub (5 minutes)

### Step 1: Open Command Prompt

1. **Navigate to Project**
   - Open Command Prompt (Win + R, type `cmd`, press Enter)
   - Type:
   ```bash
   cd C:\Users\chaud\Downloads\LOCIN\LOCIN-Android
   ```
   - Press Enter

### Step 2: Initialize Git and Push

**Copy these commands ONE BY ONE and press Enter after each:**

```bash
# 1. Initialize git repository
git init

# 2. Add all files
git add .

# 3. Create first commit
git commit -m "Initial LOCIN Android app with Firebase"

# 4. Rename branch to main (if needed)
git branch -M main

# 5. Add GitHub repository as remote
# REPLACE with YOUR repository URL from Step 1!
git remote add origin https://github.com/YOUR_USERNAME/LOCIN-Android.git

# 6. Push to GitHub
git push -u origin main
```

**Important:** Replace `YOUR_USERNAME/LOCIN-Android.git` with your actual repository URL!

### Step 3: Verify Upload

1. **Refresh GitHub page**
   - Go back to your GitHub repository page
   - Refresh (F5)
   - You should see all your code files!

✅ **Code uploaded to GitHub!**

---

## Part 4: Enable GitHub Actions & Build APK (5 minutes)

### Step 1: Enable GitHub Actions

1. **Go to Actions Tab**
   - On your GitHub repository page
   - Click "Actions" tab (top menu)

2. **Enable Workflows**
   - If you see "Workflows aren't being run on this repository"
   - Click "I understand my workflows, go ahead and enable them"
   - Or GitHub may automatically run the workflow

3. **Wait for Build**
   - You'll see "Build Android APK" workflow running
   - Click on it to see progress
   - Build takes 5-10 minutes first time
   - Status will show:
     - 🟡 Yellow (running)
     - 🟢 Green (success)
     - 🔴 Red (failed - see troubleshooting below)

### Step 2: Download APK

1. **After Build Completes (Green ✓)**
   - Click on the workflow run
   - Scroll down to "Artifacts" section
   - You'll see:
     - `locin-debug` (for testing)
     - `locin-release` (unsigned, for distribution)

2. **Download Debug APK**
   - Click "locin-debug"
   - ZIP file downloads
   - Extract ZIP
   - Inside: `app-debug.apk`

3. **Install on Android Device**
   - Transfer APK to your phone via USB or cloud
   - On phone: Settings → Security → Enable "Unknown Sources" or "Install Unknown Apps"
   - Tap the APK file
   - Click "Install"

✅ **APK Built and Ready!**

---

## Part 5: Automatic Builds on Every Update

### How it Works

Every time you push code to GitHub, a new APK is automatically built!

```bash
# Make changes to your code
# Then push updates:

cd C:\Users\chaud\Downloads\LOCIN\LOCIN-Android
git add .
git commit -m "Added new feature"
git push

# GitHub automatically builds new APK!
```

### Create Tagged Releases

For version releases (v1.0, v1.1, etc.):

```bash
# Create a version tag
git tag -a v1.0 -m "Version 1.0 - Initial Release"
git push origin v1.0

# GitHub will create a Release with APK attached!
```

Then on GitHub:
- Go to "Releases" (right sidebar)
- You'll see your version with APK files attached
- Anyone can download directly!

---

## Troubleshooting

### ❌ Build Fails: "Plugin with id 'com.google.gms.google-services' not found"

**Solution:** google-services.json is missing or in wrong location

```bash
# Check file location
cd C:\Users\chaud\Downloads\LOCIN\LOCIN-Android\app
dir google-services.json

# If not there, copy it again
# Then push to GitHub:
git add app/google-services.json
git commit -m "Add Firebase config"
git push
```

### ❌ Build Fails: Gradle Errors

**Solution:** Need Gradle wrapper files

```bash
cd C:\Users\chaud\Downloads\LOCIN\LOCIN-Android

# Create Gradle wrapper (if you have Android Studio installed)
# Or download from working Android project and copy gradlew, gradlew.bat, gradle/ folder

# Then push:
git add gradlew gradlew.bat gradle/
git commit -m "Add Gradle wrapper"
git push
```

### ❌ Can't Push to GitHub: "Permission Denied"

**Solution:** Use Personal Access Token

1. GitHub → Settings (your profile) → Developer settings
2. Personal access tokens → Tokens (classic)
3. Generate new token
4. Select: `repo` scope
5. Copy token
6. When pushing, use token as password

Or use GitHub Desktop app (easier):
- Download: https://desktop.github.com/
- Clone your repository
- Make changes
- Commit and push via GUI

### ❌ APK Won't Install: "App Not Installed"

**Solutions:**
1. Enable "Unknown Sources" in Android settings
2. Uninstall any existing LOCIN app first
3. Make sure APK downloaded completely (not corrupted)

---

## Quick Reference Card

### Firebase Setup Checklist
- [ ] Create Firebase project
- [ ] Add Android app (`com.locin.app`)
- [ ] Download google-services.json
- [ ] Enable Phone authentication
- [ ] Enable Google authentication
- [ ] Create Firestore database
- [ ] Create Realtime database
- [ ] Copy google-services.json to `app/` folder

### GitHub Build Checklist
- [ ] Create GitHub repository
- [ ] Install Git on computer
- [ ] Navigate to project folder in CMD
- [ ] Run: `git init`
- [ ] Run: `git add .`
- [ ] Run: `git commit -m "Initial commit"`
- [ ] Run: `git branch -M main`
- [ ] Run: `git remote add origin YOUR_URL`
- [ ] Run: `git push -u origin main`
- [ ] Wait for GitHub Actions to build
- [ ] Download APK from Artifacts
- [ ] Install on Android device

---

## Complete Command Summary

```bash
# One-time setup
cd C:\Users\chaud\Downloads\LOCIN\LOCIN-Android
git init
git add .
git commit -m "Initial LOCIN Android app with Firebase"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/LOCIN-Android.git
git push -u origin main

# Future updates
git add .
git commit -m "Description of changes"
git push

# Create release version
git tag -a v1.0 -m "Version 1.0"
git push origin v1.0
```

---

## What Happens After Push?

1. **GitHub receives your code** ✓
2. **GitHub Actions starts automatically** ✓
3. **Workflow runs** (.github/workflows/build.yml):
   - Sets up Java 17
   - Installs Android SDK
   - Builds debug APK
   - Builds release APK
   - Uploads both as artifacts
4. **You download APK** from Artifacts section ✓
5. **Install on phone** ✓

---

## Next Steps After APK is Built

1. **Test the app** on your phone
2. **Implement backend features**:
   - Timer logic in ViewModels
   - Firebase authentication
   - Focus time tracking
   - AccessibilityService blocking logic
3. **Create signed release APK** for Play Store:
   - Generate keystore
   - Sign APK
   - Submit to Google Play Console

---

## Need Help?

**Common Issues:**
- google-services.json missing → Copy to app/ folder and push again
- GitHub Actions not running → Check Actions tab is enabled
- APK not downloading → Check workflow completed (green checkmark)
- Can't install APK → Enable Unknown Sources on Android

**Resources:**
- Firebase Docs: https://firebase.google.com/docs/android/setup
- GitHub Actions: https://docs.github.com/en/actions
- Git Commands: https://git-scm.com/docs

Good luck! 🚀📱
