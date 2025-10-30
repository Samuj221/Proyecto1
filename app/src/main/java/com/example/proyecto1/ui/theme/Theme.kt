package com.example.proyecto1.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColors: ColorScheme = lightColorScheme(
    primary = ZonBlue,
    onPrimary = ZonSurfaceLight,
    secondary = ZonOrange,
    onSecondary = ZonSurfaceLight,
    tertiary = ZonCyan,
    background = ZonBackgroundLight,
    onBackground = ZonBlueDark,
    surface = ZonSurfaceLight,
    onSurface = ZonBlueDark,
    error = ZonRed,
    onError = ZonSurfaceLight
)

private val DarkColors: ColorScheme = darkColorScheme(
    primary = ZonBlueLight,
    onPrimary = ZonBackgroundDark,
    secondary = ZonOrangeLight,
    onSecondary = ZonBackgroundDark,
    tertiary = ZonCyanLight,
    background = ZonBackgroundDark,
    onBackground = ZonSurfaceLight,
    surface = ZonSurfaceDark,
    onSurface = ZonSurfaceLight,
    error = ZonRedDark,
    onError = ZonBackgroundDark
)

@Composable
fun ZonappTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // mantenlo false si quieres colores de marca
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
        typography = AppTypography,
        shapes = ZonappShapes,
        content = content
    )
}
