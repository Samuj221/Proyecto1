package com.example.proyecto1.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColors = darkColorScheme(
    primary = Color(0xFF1976D2),
    onPrimary = Color.White,
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color(0xFFEDEDED),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFEDEDED)
)

private val LightColors = lightColorScheme(
    primary = Color(0xFF1976D2),
    onPrimary = Color.White,
    secondary = Color(0xFF00BFA5),
    onSecondary = Color.Black,
    background = Color(0xFFF8F8F8),
    onBackground = Color(0xFF111418),
    surface = Color.White,
    onSurface = Color(0xFF111418)
)

@Composable
fun Proyecto1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // déjalo en false si no quieres dinámico
    content: @Composable () -> Unit
) {
    val colors =
        if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val ctx = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
        } else {
            if (darkTheme) DarkColors else LightColors
        }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}
