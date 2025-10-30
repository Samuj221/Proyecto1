package com.example.proyecto1.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Colores de marca (logo)
val ZonBlue        = Color(0xFF1E88E5)
val ZonBlueLight   = Color(0xFF64B5F6)
val ZonBlueDark    = Color(0xFF1565C0)

val ZonOrange      = Color(0xFFFB8C00)
val ZonOrangeLight = Color(0xFFFFB74D)
val ZonOrangeDark  = Color(0xFFEF6C00)

val ZonCyan        = Color(0xFF26C6DA)
val ZonCyanLight   = Color(0xFF80DEEA)
val ZonCyanDark    = Color(0xFF0097A7)

// Fondos / superficies
val ZonBackgroundLight = Color(0xFFF8F9FC)
val ZonSurfaceLight    = Color(0xFFFFFFFF)

val ZonBackgroundDark  = Color(0xFF101318)
val ZonSurfaceDark     = Color(0xFF1A1D22)

// Error
val ZonRed     = Color(0xFFE53935)
val ZonRedDark = Color(0xFFC62828)

// Esquemas Material 3
val LightColors = lightColorScheme(
    primary      = ZonBlue,       onPrimary      = Color.White,
    secondary    = ZonOrange,     onSecondary    = Color.White,
    tertiary     = ZonCyan,       onTertiary     = Color.White,
    background   = ZonBackgroundLight, onBackground = Color(0xFF111418),
    surface      = ZonSurfaceLight,    onSurface    = Color(0xFF111418),
    error        = ZonRed,        onError        = Color.White
)

val DarkColors = darkColorScheme(
    primary      = ZonBlueLight,  onPrimary      = Color.Black,
    secondary    = ZonOrangeLight,onSecondary    = Color.Black,
    tertiary     = ZonCyanLight,  onTertiary     = Color.Black,
    background   = ZonBackgroundDark, onBackground = Color(0xFFE9ECF1),
    surface      = ZonSurfaceDark,    onSurface    = Color(0xFFE9ECF1),
    error        = ZonRedDark,    onError        = Color.White
)
// --- Aliases para compatibilidad con nombres antiguos (no toques tus pantallas) ---
val ZBlue         = ZonBlue
val ZBlueLight    = ZonBlueLight
val ZBlueDark     = ZonBlueDark

val ZOrange       = ZonOrange
val ZOrangeLight  = ZonOrangeLight
val ZOrangeDark   = ZonOrangeDark

val ZCyan         = ZonCyan
val ZCyanLight    = ZonCyanLight
val ZCyanDark     = ZonCyanDark

val ZRed          = ZonRed
val ZRedDark      = ZonRedDark

val ZBackgroundLight = ZonBackgroundLight
val ZBackgroundDark  = ZonBackgroundDark
val ZSurfaceLight    = ZonSurfaceLight
val ZSurfaceDark     = ZonSurfaceDark
