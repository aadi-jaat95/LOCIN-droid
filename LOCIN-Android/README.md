# LOCIN Android - Study Focus Companion

Complete Android version of LOCIN with all PC features plus Android-specific enhancements.

## Features

### Core Features (from PC version)
- ⏱️ Focus timer with customizable voice reminders
- 🔔 Pomodoro mode with break timers
- 📊 Stats, streaks, and progress tracking
- 🎯 Attention exercises
- 🚫 App blocking with time limits
- 🌐 Website blocking (via companion browser)
- 👤 User profiles

### Android-Specific Features
- 📱 Phone number & Google authentication
- 📅 Schedule creator with notifications
- 🔒 Uninstall protection during focus sessions
- 🛡️ Force-stop protection with background service
- 🏆 Global leaderboard across all users
- 👥 Real-time "users currently focusing" counter
- 📺 Smart YouTube filtering:
  - Whitelist educational channels (pre-populated + custom)
  - Block non-whitelisted videos (blur thumbnails)
  - In-app shorts/reels blocking
- 📱 In-app blocking for Instagram Reels, YouTube Shorts
- 🎨 Collapsible sidebar navigation

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose + Material3
- **Architecture**: MVVM with Repository pattern
- **Backend**: Firebase (Auth, Firestore, Realtime Database)
- **Services**: 
  - AccessibilityService (app/content blocking)
  - ForegroundService (background operation)
  - DeviceAdminReceiver (uninstall protection)
  - WorkManager (scheduled reminders)

## Project Structure

```
LOCIN-Android/
├── app/
│   ├── build.gradle.kts
│   ├── google-services.json (add your own)
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/locin/app/
│       │   ├── MainActivity.kt
│       │   ├── ui/
│       │   │   ├── theme/
│       │   │   ├── screens/
│       │   │   └── components/
│       │   ├── services/
│       │   │   ├── FocusMonitorService.kt
│       │   │   ├── AppBlockingAccessibilityService.kt
│       │   │   └── DeviceAdminReceiver.kt
│       │   ├── data/
│       │   │   ├── repository/
│       │   │   └── models/
│       │   └── viewmodels/
│       └── res/
│           ├── values/
│           ├── drawable/
│           └── xml/
├── build.gradle.kts
└── settings.gradle.kts
```

## Setup Instructions

### 1. Prerequisites
- Android Studio Hedgehog or later
- JDK 17+
- Firebase project

### 2. Firebase Setup
1. Create a Firebase project at https://console.firebase.google.com/
2. Add an Android app with package name: `com.locin.app`
3. Download `google-services.json` and place in `app/` folder
4. Enable Authentication (Phone & Google)
5. Create Firestore database
6. Create Realtime Database for live user count

### 3. Build & Run
```bash
# Open in Android Studio
# Sync Gradle files
# Run on device/emulator
```

### 4. GitHub Actions for APK Build
The project includes `.github/workflows/build.yml` for automated APK builds.

Push to GitHub and APK will be generated on each release.

## Permissions

The app requests permissions only when features are used:

- **PACKAGE_USAGE_STATS**: App usage tracking
- **BIND_ACCESSIBILITY_SERVICE**: App/content blocking
- **SYSTEM_ALERT_WINDOW**: Overlay blocking screens
- **FOREGROUND_SERVICE**: Background operation
- **REQUEST_IGNORE_BATTERY_OPTIMIZATIONS**: Prevent force-stop
- **BIND_DEVICE_ADMIN**: Uninstall protection (optional)
- **INTERNET**: Sync with Firebase
- **POST_NOTIFICATIONS**: Reminders and alerts

## Play Store Compliance

✅ All permissions clearly explained
✅ AccessibilityService declared as primary feature
✅ No YouTube app modification (uses companion browser)
✅ Device Admin optional and clearly explained

## Building APK

### Via Android Studio
1. Build → Generate Signed Bundle/APK
2. Choose APK
3. Create/select keystore
4. Build release APK

### Via Command Line
```bash
./gradlew assembleRelease
```

APK location: `app/build/outputs/apk/release/app-release.apk`

## Educational Channels (Pre-populated)

The app comes with popular educational channels pre-whitelisted:

**Indian:**
- Physics Wallah
- Unacademy
- Khan Academy India
- Vedantu
- BYJU'S

**International:**
- Khan Academy
- Crash Course
- 3Blue1Brown
- MIT OpenCourseWare
- Kurzgesagt

Users can add their own channels via username or URL.

## License

© 2024 LOCIN. All rights reserved.

## Support

For issues, contact: support@locin.app
