package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RoleVerificationScreen() {
    Box(Modifier.fillMaxSize()) {
        ElevatedCard(Modifier.align(Alignment.Center)) {
            Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Validación del suceso", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = { /* TODO subir foto */ }) { Text("Subir una foto") }
                    OutlinedButton(onClick = { /* TODO tomar foto */ }) { Text("Tomar una foto") }
                }
            }
        }
    }
}
