package com.example.proyecto1.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val ZonappColors = lightColorScheme(
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
        shapes = Shapes(
            extraSmall = RoundedCornerShape(8.dp),
            small = RoundedCornerShape(10.dp),
            medium = RoundedCornerShape(12.dp),
            large = RoundedCornerShape(16.dp),
            extraLarge = RoundedCornerShape(24.dp)
        ),
        content = content
    )
}
