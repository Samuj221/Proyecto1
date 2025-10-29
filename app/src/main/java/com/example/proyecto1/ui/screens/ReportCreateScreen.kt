package com.example.proyecto1.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.proyecto1.data.ReportsRepository
import com.example.proyecto1.data.Severity
import com.example.proyecto1.util.isPlayServicesOk
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun ReportCreateScreen(onDone: () -> Unit) {
    val context = LocalContext.current
    val hasGms = remember { isPlayServicesOk(context) }

    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var severity by remember { mutableStateOf(Severity.MEDIUM) }
    var picked by remember { mutableStateOf<LatLng?>(null) }

    val center = picked ?: LatLng(4.6539, -74.0580)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 14.5f)
    }

    Column(Modifier.fillMaxSize()) {

        // ======= MAPA / FALLBACK =======
        if (hasGms) {
            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                cameraPositionState = cameraPositionState,
                onMapClick = { picked = it },
                properties = MapProperties(),
                uiSettings = MapUiSettings(zoomControlsEnabled = false)
            ) {
                picked?.let { Marker(state = MarkerState(it)) }
            }
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text("Mapa no disponible (sin Google Play Services)")
                }
            }
        }
        // ======= FIN MAPA =======

        Spacer(Modifier.height(8.dp))

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
            SeverityChip("Bajo", Color(0xFF43A047), severity == Severity.LOW) { severity = Severity.LOW }
            SeverityChip("Medio", Color(0xFFFFB300), severity == Severity.MEDIUM) { severity = Severity.MEDIUM }
            SeverityChip("Alto", Color(0xFFE53935), severity == Severity.HIGH) { severity = Severity.HIGH }
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                val p = picked
                if (title.text.isBlank() || p == null) {
                    Toast.makeText(context, "Completa título y toca el mapa para ubicar", Toast.LENGTH_SHORT).show()
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
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(listOf(Color(0xFF42A5F5), Color(0xFF1976D2))),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) { Text("Enviar reporte", color = Color.White) }
        }

        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun SeverityChip(
    text: String,
    color: Color,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = { Text(text) },
        leadingIcon = {
            Box(
                Modifier
                    .size(12.dp)
                    .background(color, shape = MaterialTheme.shapes.small)
            )
        }
    )
}
