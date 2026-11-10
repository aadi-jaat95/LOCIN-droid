package com.locin.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = CyanAccent,
    onPrimary = OnAccent,
    primaryContainer = NavyPanel2,
    onPrimaryContainer = CyanAccent,
    background = NavyDark,
    onBackground = TextLight,
    surface = NavyPanel,
    onSurface = TextLight,
    surfaceVariant = NavyPanel2,
    onSurfaceVariant = TextMuted,
    error = RedError,
    onError = Color.White,
    outline = NavyBorder
)

private val LightColorScheme = lightColorScheme(
    primary = LightAccent,
    onPrimary = Color.White,
    primaryContainer = LightPanel2,
    onPrimaryContainer = LightAccent,
    background = LightBg,
    onBackground = LightText,
    surface = LightPanel,
    onSurface = LightText,
    surfaceVariant = LightPanel2,
    onSurfaceVariant = LightTextMuted,
    error = LightRed,
    onError = Color.White,
    outline = LightBorder
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
