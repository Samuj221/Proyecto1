package com.example.proyecto1.ui.screens

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


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
    val center = LatLng(4.7110, -74.0721) // Bogotá demo (cámbialo por tu zona)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 12f)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Zonapp") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- MAPA ---
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    uiSettings = MapUiSettings(zoomControlsEnabled = false),
                    properties = MapProperties(isMyLocationEnabled = false) // pon true cuando gestiones permisos
                ) {
                    Marker(
                        state = MarkerState(position = center),
                        title = "Tu zona",
                        snippet = "Marcador de ejemplo"
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Botones de navegación (como los que ya tienes)
            Button(onClick = onOpenChat, modifier = Modifier.fillMaxWidth()) {
                Text("Chat vecinal")
            }
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = onSilentAlertConfirmed,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Alerta silenciosa")
            }
            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = onOpenReports, modifier = Modifier.fillMaxWidth()) {
                Text("Reportes")
            }
            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = onOpenRoleVerification, modifier = Modifier.fillMaxWidth()) {
                Text("Verificación de rol")
            }
        }
    }
}
