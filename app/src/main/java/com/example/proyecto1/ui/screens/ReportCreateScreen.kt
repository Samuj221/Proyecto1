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
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
fun ReportCreateScreen(
    onDone: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var severity by remember { mutableStateOf(Severity.LOW) }

    val bogota = LatLng(4.60971, -74.08175)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(bogota, 12f)
    }

    var selectedPosition by remember { mutableStateOf<LatLng?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {

        Text(
            text = "Crear reporte",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = { Text("Título") },
            singleLine = true
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = { Text("Descripción") },
            minLines = 2
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Severidad",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(4.dp))

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

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Ubicación (toca el mapa)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .padding(horizontal = 16.dp)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapClick = { latLng ->
                    selectedPosition = latLng
                }
            ) {
                selectedPosition?.let { p ->
                    Marker(
                        state = MarkerState(position = p),
                        title = "Ubicación del reporte"
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                val t = title.text.trim()
                val d = description.text.trim()
                val p = selectedPosition

                if (t.isEmpty() || d.isEmpty() || p == null) {
                    Toast.makeText(
                        context,
                        "Completa título, descripción y selecciona la ubicación",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    scope.launch {
                        try {
                            ReportsRepository.createReport(
                                title = t,
                                description = d,
                                severity = severity,
                                lat = p.latitude,
                                lng = p.longitude
                            )
                            Toast.makeText(
                                context,
                                "Reporte enviado",
                                Toast.LENGTH_SHORT
                            ).show()
                            onDone()
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                e.localizedMessage ?: "Error al guardar el reporte",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
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
