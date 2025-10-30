package com.example.proyecto1.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NotificationsOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto1.util.isPlayServicesOk
import com.example.proyecto1.util.rememberMyLocation
import com.google.android.gms.maps.CameraUpdateFactory as GmsCameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun HomeScreen(onGoToChat: () -> Unit = {}, onGoToReports: () -> Unit, onGoToMap: () -> Unit) {
    val ctx = LocalContext.current

    // Play Services ok?
    val hasGms = remember(ctx) { isPlayServicesOk(ctx) }

    // Ubicación observada como State<LatLng?>
    val myLoc by rememberMyLocation()

    val center = myLoc ?: LatLng(4.6539, -74.0580) // Bogotá
    val camera = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 14.5f)
    }

    // Animar cuando llega la ubicación
    LaunchedEffect(myLoc) {
        myLoc?.let { camera.animate(GmsCameraUpdateFactory.newLatLngZoom(it, 15f), 800) }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(8.dp))
        Text(
            "Alertas en\nTu zona",
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.elevatedCardElevation(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                if (hasGms) {
                    GoogleMap(
                        modifier = Modifier.matchParentSize(),
                        cameraPositionState = camera,
                        properties = MapProperties(isMyLocationEnabled = myLoc != null),
                        uiSettings = MapUiSettings(
                            zoomControlsEnabled = false,
                            myLocationButtonEnabled = false
                        )
                    )
                } else {
                    Text(
                        "Mapa no disponible (Google Play Services)",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        OutlinedButton(
            onClick = onGoToChat,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(52.dp),
            shape = MaterialTheme.shapes.medium
        ) { Text("Chat vecinal") }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { Toast.makeText(ctx, "Alerta silenciosa enviada", Toast.LENGTH_SHORT).show() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.NotificationsOff, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Alerta silenciosa")
            }
        }
    }
}
