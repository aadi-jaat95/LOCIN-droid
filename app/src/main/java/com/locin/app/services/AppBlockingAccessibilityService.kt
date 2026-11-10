package com.locin.app.services

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.content.Intent
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import android.graphics.PixelFormat

class AppBlockingAccessibilityService : AccessibilityService() {

    private var blockedApps = mutableSetOf<String>()
    private var blockOverlay: FrameLayout? = null

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString() ?: return

            // Check if app is blocked
            if (isAppBlocked(packageName)) {
                showBlockOverlay(packageName)
                // Return to home screen
                performGlobalAction(GLOBAL_ACTION_HOME)
            }

            // Check for YouTube Shorts/Instagram Reels
            if (packageName.contains("youtube") || packageName.contains("instagram")) {
                detectAndBlockShorts(event)
            }
        }
    }

    override fun onInterrupt() {
        removeBlockOverlay()
    }

    private fun isAppBlocked(packageName: String): Boolean {
        // TODO: Check against blocked apps from preferences/Firebase
        return blockedApps.contains(packageName)
    }

    private fun detectAndBlockShorts(event: AccessibilityEvent) {
        // TODO: Detect shorts/reels using view hierarchy analysis
        // Look for specific view IDs or text patterns
        val source = event.source ?: return

        // Example: Check for "shorts" in URL or view IDs
        val nodeInfo = source.toString()
        if (nodeInfo.contains("shorts", ignoreCase = true) ||
            nodeInfo.contains("reels", ignoreCase = true)) {
            // Block or blur content
            performGlobalAction(GLOBAL_ACTION_BACK)
        }
    }

    private fun showBlockOverlay(packageName: String) {
        if (blockOverlay != null) return

        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        )

        blockOverlay = FrameLayout(this).apply {
            setBackgroundColor(0xDD000000.toInt())
            addView(TextView(this@AppBlockingAccessibilityService).apply {
                text = "App Blocked by LOCIN\n\n$packageName\n\nStay focused on your goals!"
                textSize = 20f
                setTextColor(0xFFFFFFFF.toInt())
                gravity = android.view.Gravity.CENTER
            })
        }

        windowManager.addView(blockOverlay, params)

        // Auto-remove after 2 seconds
        android.os.Handler(mainLooper).postDelayed({
            removeBlockOverlay()
        }, 2000)
    }

    private fun removeBlockOverlay() {
        blockOverlay?.let {
            val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
            windowManager.removeView(it)
            blockOverlay = null
        }
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        // TODO: Load blocked apps list from preferences/Firebase
    }
}
