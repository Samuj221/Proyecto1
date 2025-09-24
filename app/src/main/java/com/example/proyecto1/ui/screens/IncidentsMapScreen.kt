package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IncidentsMapScreen(
    onOpenChat: () -> Unit,
    onOpenReports: () -> Unit,
    onOpenProfile: () -> Unit,
    onOpenAdmin: () -> Unit,
    onOpenRoleVerification: () -> Unit,
    onSilentAlertConfirmed: () -> Unit
) {
    var showConfirm by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Zonapp") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // ----- Mapa (placeholder) -----
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                tonalElevation = 2.dp,
                shape = MaterialTheme.shapes.medium
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Mapa (placeholder)")
                }
            }

            // ----- Acciones principales -----
            Button(
                onClick = onOpenChat,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Chat vecinal") }

            Button(
                onClick = { showConfirm = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) { Text("Alerta silenciosa") }

            // ----- Accesos auxiliares (según tus mockups) -----
            OutlinedButton(
                onClick = onOpenReports,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Reportes de incidentes") }

            OutlinedButton(
                onClick = onOpenProfile,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Perfil / Ajustes") }

            OutlinedButton(
                onClick = onOpenAdmin,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Panel Admin") }

            OutlinedButton(
                onClick = onOpenRoleVerification,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Verificación de rol") }
        }
    }

    if (showConfirm) {
        AlertDialog(
            onDismissRequest = { showConfirm = false },
            title = { Text("¿Enviar alerta silenciosa a las autoridades?") },
            confirmButton = {
                TextButton(onClick = {
                    showConfirm = false
                    onSilentAlertConfirmed()
                }) { Text("Confirmar") }
            },
            dismissButton = {
                TextButton(onClick = { showConfirm = false }) { Text("Cancelar") }
            }
        )
    }
}
