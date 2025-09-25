package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdminPanelScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Panel de administración", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text("Validación/Verificación de reportes (pendiente de implementar).")
    }
}
