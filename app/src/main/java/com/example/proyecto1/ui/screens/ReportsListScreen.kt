package com.example.proyecto1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import com.example.proyecto1.data.*
import com.example.proyecto1.util.rememberMyLocation
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun ReportsListScreen(onCreate: () -> Unit) {
    val context = LocalContext.current
    val (myLoc, _) = rememberMyLocation()
    val center = myLoc ?: LatLng(4.6539, -74.0580)

    val reports = remember(myLoc) {
        mutableStateOf(
            if (myLoc != null) ReportsRepository.near(context, myLoc, 3000f)
            else ReportsRepository.all(context)
        )
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 13.5f)
    }

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {

            GoogleMap(
                modifier = Modifier.fillMaxWidth().height(240.dp),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = myLoc != null),
                uiSettings = MapUiSettings(zoomControlsEnabled = false, myLocationButtonEnabled = false)
            ) {
                reports.value.forEach { r ->
                    Marker(
                        state = MarkerState(position = r.latLng),
                        title = r.title,
                        snippet = r.description,
                        icon = BitmapDescriptorFactory.defaultMarker(
                            when (r.severity) {
                                Severity.HIGH -> BitmapDescriptorFactory.HUE_RED
                                Severity.MEDIUM -> BitmapDescriptorFactory.HUE_YELLOW
                                Severity.LOW -> BitmapDescriptorFactory.HUE_GREEN
                            }
                        )
                    )
                }
            }

            Spacer(Modifier.height(8.dp))
            Text(
                "Reportes en tu zona",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )

            LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 100.dp)) {
                items(reports.value) { r -> ReportRow(r) }
            }
        }

        FloatingActionButton(
            onClick = onCreate,
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
        ) { Icon(Icons.Default.Add, contentDescription = "Crear reporte") }
    }
}

@Composable
private fun ReportRow(r: Report) {
    val (color, label) = when (r.severity) {
        Severity.HIGH -> Color(0xFFE53935) to "Alto"
        Severity.MEDIUM -> Color(0xFFFFB300) to "Medio"
        Severity.LOW -> Color(0xFF43A047) to "Bajo"
    }

    ElevatedCard(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(14.dp).background(color, shape = MaterialTheme.shapes.small))
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text(r.title, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(2.dp))
                Text(r.description, color = Color.Gray, fontSize = 12.sp, maxLines = 2)
            }
            Spacer(Modifier.width(10.dp))
            Text(label, color = color, fontWeight = FontWeight.Bold)
        }
    }
}
