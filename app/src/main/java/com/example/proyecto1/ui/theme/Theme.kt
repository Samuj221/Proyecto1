package com.samupro.proyecto1.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun ZTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    val colors = if (darkTheme) darkScheme else lightScheme
    val sysUi = rememberSystemUiController()


// Barras sistema limpias
    sysUi.setStatusBarColor(colors.background, darkIcons = !darkTheme)
    sysUi.setNavigationBarColor(colors.background, darkIcons = !darkTheme)


    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}