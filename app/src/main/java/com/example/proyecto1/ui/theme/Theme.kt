package com.example.proyecto1.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = ZonBlue,       onPrimary = Color.White,
    secondary = ZonOrange,   onSecondary = Color.White,
    tertiary = ZonCyan,
    background = ZonBackgroundLight, onBackground = Color(0xFF0F1115),
    surface = ZonSurfaceLight,        onSurface = Color(0xFF0F1115),
    error = ZonRed,                   onError = Color.White
)

private val DarkColors = darkColorScheme(
    primary = ZonBlueLight,  onPrimary = Color.Black,
    secondary = ZonOrangeLight, onSecondary = Color.Black,
    tertiary = ZonCyanLight,
    background = ZonBackgroundDark, onBackground = Color(0xFFE8EAEE),
    surface = ZonSurfaceDark,        onSurface = Color(0xFFE8EAEE),
    error = ZonRedDark,              onError = Color.White
)

@Composable
fun ZonappTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val ctx = LocalContext.current
    val colorScheme =
        if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (useDarkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
        } else {
            if (useDarkTheme) DarkColors else LightColors
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ZonappTypography,
        shapes = ZonappShapes,
        content = content
    )
}
