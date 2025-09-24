package com.example.proyecto1.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun IncidentsMapScreen(
    onOpenChat: () -> Unit = {},
    onOpenReports: () -> Unit = {},
    onOpenProfile: () -> Unit = {}
) {
    // Placeholder para que compile – reemplaza por tu UI
    Button(onClick = onOpenChat) { Text("Ir a Chat") }
}
