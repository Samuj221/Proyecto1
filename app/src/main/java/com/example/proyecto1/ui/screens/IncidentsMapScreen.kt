@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Pantalla "Inicio": mapa + acciones.
 * Expone los callbacks que estás usando desde MainActivity/NavHost.
 */
@Composable
fun IncidentsMapScreen(
    onOpenChat: () -> Unit,
    onOpenReports: () -> Unit,
    onOpenProfile: () -> Unit,
    onOpenAdmin: () -> Unit,
    onOpenRoleVerification: () -> Unit,
    onSilentAlertConfirmed: () -> Unit
) {
    var showSilentAlertDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Zonapp") },
                actions = {
                    // Acciones rápidas en la app bar (opcional)
                    TextButton(onClick = onOpenProfile) { Text("Perfil") }
                    TextButton(onClick = onOpenAdmin) { Text("Admin") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Placeholder del "mapa"
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Aquí iría el mapa/viewport")
                }
            }

            Spacer(Modifier.height(16.dp))

            // Botón principal: Chat vecinal
            Button(
                onClick = onOpenChat,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Chat vecinal")
            }

            Spacer(Modifier.height(12.dp))

            // Botón de alerta silenciosa (abre diálogo de confirmación)
            Button(
                onClick = { showSilentAlertDialog = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Alerta silenciosa")
            }

            Spacer(Modifier.height(24.dp))

            // Acciones extra sugeridas por tu flujo
            OutlinedButton(
                onClick = onOpenReports,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Reportes") }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = onOpenRoleVerification,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Verificación de rol") }
        }
    }

    if (showSilentAlertDialog) {
        AlertDialog(
            onDismissRequest = { showSilentAlertDialog = false },
            title = { Text("¿Desea enviar una alerta silenciosa a las autoridades?") },
            confirmButton = {
                TextButton(onClick = {
                    showSilentAlertDialog = false
                    onSilentAlertConfirmed()
                }) { Text("Confirmar") }
            },
            dismissButton = {
                TextButton(onClick = { showSilentAlertDialog = false }) { Text("Cancelar") }
            }
        )
    }
}
