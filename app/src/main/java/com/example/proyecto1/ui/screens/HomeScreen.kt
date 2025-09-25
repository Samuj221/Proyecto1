package com.example.proyecto1.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyecto1.ui.theme.ZonRed
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun HomeScreen(
    onGoToChat: () -> Unit = {} // opcional: para navegar al chat
) {
    val zonaCentro = LatLng(4.6539, -74.0580) // Bogotá aprox (céntrico)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(zonaCentro, 14.5f)
    }
    var showConfirm by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(8.dp))
        Text(
            text = "Alertas en\nTu zona",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(12.dp))

        MapaCircular(cameraPositionState = cameraPositionState)

        Spacer(Modifier.height(24.dp))

        // Botón "Chat vecinal" estilo mockup (azul claro con borde)
        OutlinedButton(
            onClick = onGoToChat,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(52.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Chat vecinal")
        }

        Spacer(Modifier.height(16.dp))

        // CTA "Alerta silenciosa" grande y llamativo
        ElevatedButton(
            onClick = { showConfirm = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(64.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = ZonRed,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 6.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Alerta silenciosa")
        }
    }

    if (showConfirm) {
        AlertDialog(
            onDismissRequest = { showConfirm = false },
            text = { Text("¿Desea enviar una alerta silenciosa a las autoridades?") },
            confirmButton = {
                FilledTonalButton(onClick = { showConfirm = false /* TODO: enviar alerta */ }) {
                    Icon(Icons.Default.Check, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Confirmar")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showConfirm = false }) {
                    Icon(Icons.Default.Close, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
private fun MapaCircular(
    cameraPositionState: CameraPositionState
) {
    // Polígono simple para simular el perímetro del barrio (como en el mock)
    val poly = listOf(
        LatLng(4.6560, -74.0632),
        LatLng(4.6564, -74.0535),
        LatLng(4.6507, -74.0538),
        LatLng(4.6502, -74.0627),
    )

    Box(
        modifier = Modifier
            .size(280.dp)
            .clip(CircleShape)
    ) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = false,
            ),
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                myLocationButtonEnabled = false,
                compassEnabled = false,
                mapToolbarEnabled = false
            )
        ) {
            Polygon(
                points = poly,
                strokeColor = Color(0xFF1E88E5),
                strokeWidth = 6f,
                fillColor = Color(0x332196F3)
            )
        }
    }
}
