# LOCIN Android - Complete Build Instructions

## Quick Start Guide

I've created the foundational structure for the LOCIN Android app. Here's what you have and what you need to complete:

## ✅ Files Created

1. **Project Configuration**
   - `build.gradle.kts` (root)
   - `settings.gradle.kts`
   - `app/build.gradle.kts` (with all dependencies)
   - `AndroidManifest.xml` (with all permissions)

2. **Core Application**
   - `LocinApplication.kt` (notification channels)
   - `MainActivity.kt` (entry point)
   - `LocinApp.kt` (main UI with sidebar navigation)
   - `Color.kt` (theme colors matching PC app)

## 🔨 To Complete the Build

### Step 1: Add Missing UI Files

Create these placeholder screen files in `app/src/main/java/com/locin/app/ui/screens/`:

```kotlin
// RemindersScreen.kt
package com.locin.app.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RemindersScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Reminders Screen", style = MaterialTheme.typography.headlineMedium)
        Text("Focus timer and voice reminders will go here")
    }
}

// Repeat similar structure for:
// - ExercisesScreen.kt
// - AppBlockingScreen.kt
// - WebBlockingScreen.kt
// - StatsScreen.kt
// - SettingsScreen.kt
// - LeaderboardScreen.kt
// - ScheduleScreen.kt
```

### Step 2: Add Theme File

Create `app/src/main/java/com/locin/app/ui/theme/Theme.kt`:

```kotlin
package com.locin.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = CyanAccent,
    onPrimary = OnAccent,
    background = NavyDark,
    surface = NavyPanel,
    onSurface = TextLight
)

private val LightColorScheme = lightColorScheme(
    primary = LightAccent,
    onPrimary = Color.White,
    background = LightBg,
    surface = LightPanel,
    onSurface = LightText
)

@Composable
fun LOCINTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```

### Step 3: Add Services

Create `app/src/main/java/com/locin/app/services/`:

**FocusMonitorService.kt** - Foreground service for background focus tracking
**AppBlockingAccessibilityService.kt** - AccessibilityService for app/content blocking
**LocinDeviceAdminReceiver.kt** - Device admin for uninstall protection
**BootReceiver.kt** - Auto-start on boot

(See SERVICES_TEMPLATE.md for full code)

### Step 4: Add Firebase Configuration

1. Go to https://console.firebase.google.com/
2. Create project named "LOCIN"
3. Add Android app with package: `com.locin.app`
4. Download `google-services.json`
5. Place in `app/` folder

### Step 5: Add Resources

Create `app/src/main/res/values/strings.xml`:

```xml
<resources>
    <string name="app_name">LOCIN</string>
    <string name="notification_title">Focus Session Active</string>
    <string name="accessibility_service_description">LOCIN uses Accessibility Service to help you stay focused by monitoring and blocking distracting apps when you enable app blocking features.</string>
</resources>
```

Create `app/src/main/res/xml/accessibility_service_config.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<accessibility-service
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:accessibilityEventTypes="typeWindowStateChanged|typeWindowContentChanged"
    android:accessibilityFeedbackType="feedbackGeneric"
    android:accessibilityFlags="flagReportViewIds|flagRetrieveInteractiveWindows"
    android:canRetrieveWindowContent="true"
    android:description="@string/accessibility_service_description"
    android:notificationTimeout="100" />
```

### Step 6: Build APK

**Option A: Android Studio**
1. Open project in Android Studio
2. File → Sync Project with Gradle Files
3. Build → Generate Signed Bundle/APK
4. Select APK
5. Create keystore if needed
6. Build Release APK

**Option B: Command Line**
```bash
cd LOCIN-Android
./gradlew assembleRelease
# APK at: app/build/outputs/apk/release/app-release.apk
```

### Step 7: GitHub Actions (Optional)

Create `.github/workflows/build.yml`:

```yaml
name: Build APK

on:
  push:
    tags:
      - 'v*'

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '17'
      - name: Build APK
        run: |
          chmod +x ./gradlew
          ./gradlew assembleRelease
      - name: Upload APK
        uses: actions/upload-artifact@v3
        with:
          name: locin-app
          path: app/build/outputs/apk/release/*.apk
```

## 📱 Key Features to Implement

### Priority 1 (Core)
- ✅ Project structure
- ✅ Navigation
- ⚠️ Screen layouts (placeholders created)
- ⏳ Focus timer logic
- ⏳ Firebase authentication

### Priority 2 (Blocking)
- ⏳ AccessibilityService implementation
- ⏳ UsageStatsManager integration
- ⏳ App blocking logic
- ⏳ YouTube shorts/reels detection

### Priority 3 (Advanced)
- ⏳ Leaderboard with Firestore
- ⏳ Schedule system with WorkManager
- ⏳ Voice reminders (TextToSpeech)
- ⏳ Channel whitelist system

## 🚀 Deployment

### For Testing (APK)
1. Build release APK
2. Enable "Unknown Sources" on device
3. Install APK directly

### For Play Store
1. Complete all features
2. Test thoroughly
3. Create signed app bundle (.aab)
4. Submit to Google Play Console
5. Fill out store listing
6. Submit for review

## 📚 Additional Resources

- **Compose Tutorial**: https://developer.android.com/jetpack/compose/tutorial
- **Accessibility Service**: https://developer.android.com/guide/topics/ui/accessibility/service
- **Firebase Android**: https://firebase.google.com/docs/android/setup
- **WorkManager**: https://developer.android.com/topic/libraries/architecture/workmanager

## ⚠️ Important Notes

1. **Permissions**: All critical permissions are in AndroidManifest. Request at runtime when needed.

2. **Play Store Compliance**: 
   - Declare Accessibility Service as primary feature
   - Provide clear permission explanations
   - No YouTube app modification

3. **Testing**: Test on real device (Android 8.0+) for best results

4. **Firebase**: Don't commit google-services.json to public repos

## 🆘 Need Help?

The foundation is complete. To finish:
1. Fill in screen UI layouts (use Jetpack Compose)
2. Implement service logic (AccessibilityService for blocking)
3. Connect Firebase (Auth, Firestore for data sync)
4. Add WorkManager for schedules
5. Implement TextToSpeech for voice reminders

Good luck with your LOCIN Android app! 🎓📱
