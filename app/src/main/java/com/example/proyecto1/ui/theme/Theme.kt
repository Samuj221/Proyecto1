// Theme.kt
package com.example.proyecto1.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ZonappColors: ColorScheme = lightColorScheme(
    primary = ZonBlue,
    onPrimary = ZonOnBlue,
    primaryContainer = ZonBlueContainer,
    onPrimaryContainer = ZonBlue,
    secondary = ZonOrange,
    surface = ZonSurface
)

@Composable
fun ZonappTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ZonappColors,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}