package com.example.proyecto1.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.proyecto1.data.ReportsRepository
import com.example.proyecto1.data.Severity
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*


@Composable
fun ReportCreateScreen(onDone: () -> Unit) {
    val context = LocalContext.current

    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var severity by remember { mutableStateOf(Severity.MEDIUM) }
    var picked by remember { mutableStateOf<LatLng?>(null) }

    val hasGms by remember {
        mutableStateOf(com.example.proyecto1.util.isPlayServicesOk(context))
    }

    val center = picked ?: LatLng(4.6539, -74.0580)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 14.5f)
    }

    Column(Modifier.fillMaxSize()) {

        // MAPA
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentAlignment = Alignment.Center
            ) {
                if (hasGms) {
                    GoogleMap(
                        modifier = Modifier.matchParentSize(),
                        cameraPositionState = cameraPositionState,
                        onMapClick = { picked = it },
                        properties = MapProperties(),
                        uiSettings = MapUiSettings(zoomControlsEnabled = false)
                    ) {
                        picked?.let { Marker(state = MarkerState(it)) }
                    }
                } else {
                    Text(
                        "Mapa no disponible (Google Play Services)",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        // FORM
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(8.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = severity == Severity.LOW,
                onClick = { severity = Severity.LOW },
                label = { Text("Bajo") }
            )
            FilterChip(
                selected = severity == Severity.MEDIUM,
                onClick = { severity = Severity.MEDIUM },
                label = { Text("Medio") }
            )
            FilterChip(
                selected = severity == Severity.HIGH,
                onClick = { severity = Severity.HIGH },
                label = { Text("Alto") }
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                val p = picked
                if (title.text.isBlank() || p == null) {
                    Toast.makeText(
                        context,
                        "Completa título y toca el mapa para ubicar",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    ReportsRepository.add(
                        context,
                        title.text.trim(),
                        description.text.trim(),
                        severity,
                        p
                    )
                    Toast.makeText(context, "Reporte enviado", Toast.LENGTH_SHORT).show()
                    onDone()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Enviar reporte")
        }

        Spacer(Modifier.height(12.dp))
    }
}
