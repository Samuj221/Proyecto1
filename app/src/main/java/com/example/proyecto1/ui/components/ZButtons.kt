package com.samupro.proyecto1.ui.components


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.samupro.proyecto1.ui.theme.ZBlue
import com.samupro.proyecto1.ui.theme.ZOrange


@Composable
fun PrimaryGradientButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, shape: Shape = MaterialTheme.shapes.large) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        contentPadding = ButtonDefaults.ContentPadding,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) { Text(text) }
}