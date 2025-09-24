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

// Paleta fija sin depender de Color.kt
private val DarkColors = darkColorScheme(
    primary   = Color(0xFF9CCAFF),   // azul claro
    secondary = Color(0xFFBBC6D2),   // gris azulado
    tertiary  = Color(0xFFEFB8C8),   // rosado
    background = Color(0xFF121212),
    surface    = Color(0xFF1E1E1E),
    onPrimary   = Color(0xFF002A4D),
    onSecondary = Color(0xFF25303A),
    onTertiary  = Color(0xFF442832),
    onBackground = Color(0xFFE6E1E5),
    onSurface    = Color(0xFFE6E1E5)
)

private val LightColors = lightColorScheme(
    primary   = Color(0xFF0061A4),   // azul
    secondary = Color(0xFF526070),   // gris azulado
    tertiary  = Color(0xFF7D5260),   // rosado oscuro
    background = Color(0xFFFDFBFF),
    surface    = Color(0xFFFFFFFF),
    onPrimary   = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFFFFFFF),
    onTertiary  = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1C1B1F),
    onSurface    = Color(0xFF1C1B1F)
)

@Composable
fun Proyecto1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true, // si no quieres dinámicos pon false
    content: @Composable () -> Unit
) {
    val colors =
        if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val ctx = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
        } else {
            if (darkTheme) DarkColors else LightColors
        }

    // Tipografía y shapes se dejan por defecto para simplificar
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
