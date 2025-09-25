package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncidentsMapScreen(
    onMenuClick: () -> Unit,
    onOpenChat: () -> Unit,
    onOpenReports: () -> Unit,
    onOpenProfile: () -> Unit,
    onOpenAdmin: () -> Unit,
    onOpenRoleVerification: () -> Unit,
    onSilentAlertConfirmed: () -> Unit,
) {
    var showConfirm by remember { mutableStateOf(false) }
    val center = LatLng(4.6482837, -74.247894) // Bogotá demo
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 12f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Zonapp") },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menú")
                    }
                },
                actions = {
                    TextButton(onClick = onOpenProfile) { Text("Perfil") }
                    TextButton(onClick = onOpenAdmin) { Text("Admin") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Mapa
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxWidth().height(200.dp)
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    uiSettings = MapUiSettings(zoomControlsEnabled = false)
                ) {
                    Marker(state = MarkerState(position = center), title = "Tu zona")
                }
            }

            Button(onClick = onOpenChat, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(28.dp)) {
                Text("Chat vecinal")
            }

            Button(
                onClick = { showConfirm = true },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) { Text("Alerta silenciosa") }

            OutlinedButton(onClick = onOpenReports, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(28.dp)) {
                Text("Reportes")
            }

            OutlinedButton(onClick = onOpenRoleVerification, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(28.dp)) {
                Text("Verificación de rol")
            }
        }

        if (showConfirm) {
            AlertDialog(
                onDismissRequest = { showConfirm = false },
                title = { Text("¿Enviar alerta silenciosa?") },
                text = { Text("Se notificará a las autoridades.") },
                confirmButton = {
                    TextButton(onClick = {
                        showConfirm = false
                        onSilentAlertConfirmed()
                    }) { Text("Confirmar") }
                },
                dismissButton = { TextButton(onClick = { showConfirm = false }) { Text("Cancelar") } }
            )
        }
    }
}
