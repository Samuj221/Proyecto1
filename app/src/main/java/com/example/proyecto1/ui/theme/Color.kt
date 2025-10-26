package com.samupro.proyecto1.ui.theme


import androidx.compose.ui.graphics.Color


// Paleta tomada del logo (aprox)
val ZBlue = Color(0xFF33B5F6)
val ZBlueDeep = Color(0xFF1592D1)
val ZOrange = Color(0xFFFF8A3D)
val ZOrangeDeep = Color(0xFFF26419)


// Tesla‑like neutrales
val Graphite = Color(0xFF0B0D0E)
val Ink = Color(0xFF121416)
val Silver = Color(0xFF8E99A3)
val Mist = Color(0xFFE8ECEF)


// Material 3 schemes
val lightScheme = androidx.compose.material3.lightColorScheme(
    primary = ZBlueDeep,
    onPrimary = Color.White,
    primaryContainer = ZBlue,
    onPrimaryContainer = Color.Black,
    secondary = ZOrangeDeep,
    onSecondary = Color.White,
    secondaryContainer = ZOrange,
    onSecondaryContainer = Color.Black,
    background = Color.White,
    onBackground = Ink,
    surface = Color.White,
    onSurface = Ink,
    outline = Silver
)


val darkScheme = androidx.compose.material3.darkColorScheme(
    primary = ZBlue,
    onPrimary = Color.Black,
    primaryContainer = ZBlueDeep,
    onPrimaryContainer = Color.White,
    secondary = ZOrange,
    onSecondary = Color.Black,
    secondaryContainer = ZOrangeDeep,
    onSecondaryContainer = Color.White,
    background = Ink,
    onBackground = Color.White,
    surface = Graphite,
    onSurface = Color.White,
)