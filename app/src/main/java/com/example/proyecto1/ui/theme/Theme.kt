package com.example.proyecto1.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Marca (logo: azul ↔ naranja) + rojos de alerta (usados en HomeScreen)
val ZonBlue       = Color(0xFF33B5F6)
val ZonBlueDeep   = Color(0xFF1592D1)
val ZonOrange     = Color(0xFFFF8A3D)
val ZonOrangeDeep = Color(0xFFF26419)
val ZonRed        = Color(0xFFFF6B6B)
val ZonRedDark    = Color(0xFFC62828)

private val Ink      = Color(0xFF121416)
private val Graphite = Color(0xFF0B0D0E)

private val Light = lightColorScheme(
    primary = ZonBlueDeep,
    onPrimary = Color.White,
    secondary = ZonOrangeDeep,
    onSecondary = Color.White,
    background = Color.White,
    onBackground = Ink,
    surface = Color.White,
    onSurface = Ink
)

private val Dark = darkColorScheme(
    primary = ZonBlue,
    onPrimary = Color.Black,
    secondary = ZonOrange,
    onSecondary = Color.Black,
    background = Ink,
    onBackground = Color.White,
    surface = Graphite,
    onSurface = Color.White
)

/**
 * Versión sin accompanist (no toca barras del sistema).
 * Si luego quieres, añadimos systemuicontroller otra vez.
 */
@Composable
fun ZonappTheme(darkTheme: Boolean = true, content: @Composable () -> Unit) {
    val colors = if (darkTheme) Dark else Light
    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}
