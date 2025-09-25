// app/src/main/java/com/example/proyecto1/ui/components/DrawerItem.kt
package com.example.proyecto1.ui.components

import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)
