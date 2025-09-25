package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncidentsMapScreen(
    onOpenChat: () -> Unit,
    onOpenReports: () -> Unit,
    onOpenProfile: () -> Unit,
    onOpenAdmin: () -> Unit,
    onOpenRoleVerification: () -> Unit,
    onSilentAlertConfirmed: () -> Unit,
) {
    var showConfirm by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Map container
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            // Google Map
            val cameraPositionState = rememberCameraPositionState()
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            )
        }

        Button(
            onClick = onOpenChat,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp)
        ) { Text("Chat vecinal") }

        Button(
            onClick = { showConfirm = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) { Text("Alerta silenciosa") }

        OutlinedButton(
            onClick = onOpenReports,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp)
        ) { Text("Reportes") }

        OutlinedButton(
            onClick = onOpenRoleVerification,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp)
        ) { Text("Verificación de rol") }
    }

    if (showConfirm) {
        AlertDialog(
            onDismissRequest = { showConfirm = false },
            confirmButton = {
                TextButton(onClick = {
                    showConfirm = false
                    onSilentAlertConfirmed()
                }) { Text("Confirmar") }
            },
            dismissButton = { TextButton(onClick = { showConfirm = false }) { Text("Cancelar") } },
            title = { Text("¿Desea enviar una alerta silenciosa?") },
            text = { Text("Se notificará a las autoridades.") }
        )
    }
}
