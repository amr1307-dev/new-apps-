package com.example.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = EmeraldPrimary,
    onPrimary = Color.White,
    primaryContainer = EmeraldLightBg,
    onPrimaryContainer = EmeraldDark,
    secondary = NeonLimeDark,
    onSecondary = Color.White,
    secondaryContainer = NeonLimeLight,
    onSecondaryContainer = TextDark,
    tertiary = BlueAccent,
    onTertiary = Color.White,
    tertiaryContainer = BlueLightBg,
    onTertiaryContainer = BlueAccent,
    background = LightBg,
    onBackground = TextDark,
    surface = LightSurface,
    onSurface = TextDark,
    surfaceVariant = LightSurfaceElevated,
    onSurfaceVariant = TextMuted,
    outline = LightBorder,
    error = AlertRed
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = false, // Exclusively pure light theme as requested
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
