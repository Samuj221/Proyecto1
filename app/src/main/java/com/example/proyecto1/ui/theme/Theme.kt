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
    primary = ZonBlue,
    onPrimary = Color.White,
    primaryContainer = ZonBlueLight,
    onPrimaryContainer = Color(0xFF07233F),

    secondary = ZonOrange,
    onSecondary = Color.Black,
    secondaryContainer = ZonOrangeLight,
    onSecondaryContainer = Color(0xFF3A2100),

    tertiary = ZonCyan,
    onTertiary = Color.Black,
    tertiaryContainer = ZonCyanLight,
    onTertiaryContainer = Color(0xFF00363B),

    background = ZonBackgroundLight,
    onBackground = Color(0xFF0F172A),
    surface = ZonSurfaceLight,
    onSurface = Color(0xFF0F172A),
    surfaceVariant = Color(0xFFE6ECF5),
    onSurfaceVariant = Color(0xFF394657),
    outline = Color(0xFF6B7A8B)
)

private val DarkColors = darkColorScheme(
    // En oscuro usamos tonos más claros como “primarios” para mantener contraste
    primary = ZonBlueLight,
    onPrimary = Color.Black,
    primaryContainer = ZonBlueDark,
    onPrimaryContainer = Color(0xFFE3F2FD),

    secondary = ZonOrangeLight,
    onSecondary = Color.Black,
    secondaryContainer = ZonOrangeDark,
    onSecondaryContainer = Color(0xFFFFF3E0),

    tertiary = ZonCyanLight,
    onTertiary = Color.Black,
    tertiaryContainer = ZonCyanDark,
    onTertiaryContainer = Color(0xFFE0F7FA),

    background = ZonBackgroundDark,
    onBackground = Color(0xFFE6EAF2),
    surface = ZonSurfaceDark,
    onSurface = Color(0xFFE6EAF2),
    surfaceVariant = Color(0xFF2A3038),
    onSurfaceVariant = Color(0xFFC0C7D0),
    outline = Color(0xFF8C95A1)
)

/**
 * Tema principal de la app.
 *
 * - Cambia automáticamente claro/oscuro con el sistema.
 * - Si quisieras respetar colores dinámicos de Android 12+ (Material You),
 *   pon `dynamicColor = true`; por defecto mantenemos la marca del logo.
 */
@Composable
fun ZonappTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val ctx = LocalContext.current
            if (useDarkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
        } else {
            if (useDarkTheme) DarkColors else LightColors
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
