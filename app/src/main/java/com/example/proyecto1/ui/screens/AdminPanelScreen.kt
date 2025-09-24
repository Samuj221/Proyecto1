package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdminPanelScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Notificaciones", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        // Item de ejemplo
        ElevatedCard(Modifier.fillMaxWidth()) {
            ListItem(
                headlineContent = { Text("Pacho Gutierrez — Habitante del sector") },
                supportingContent = { Text("Choque vehicular") },
                trailingContent = {
                    Button(onClick = { /* TODO validar */ }) { Text("Validar") }
                }
            )
        }
    }
}
