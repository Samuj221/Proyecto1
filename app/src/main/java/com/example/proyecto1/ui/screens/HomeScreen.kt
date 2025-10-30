package com.example.proyecto1.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NotificationsOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto1.ui.theme.ZonRed
import com.example.proyecto1.ui.theme.ZonRedDark
import com.example.proyecto1.util.isPlayServicesOk
import com.example.proyecto1.util.rememberAccelerometerMagnitude
import com.example.proyecto1.util.rememberLightLux
import com.example.proyecto1.util.rememberLocationState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun HomeScreen(onGoToChat: () -> Unit = {}) {
    val context = LocalContext.current
    val hasGms = remember { isPlayServicesOk(context) }
    val loc = rememberLocationState()

    val center = loc.lastKnown ?: LatLng(4.6539, -74.0580)
    val camera = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 14.5f)
    }
    LaunchedEffect(loc.lastKnown) {
        loc.lastKnown?.let { camera.animate(CameraUpdateFactory.newLatLngZoom(it, 15f), 800) }
    }

    // Sensores
    val accel = rememberAccelerometerMagnitude()
    val light = rememberLightLux()

    // “Shake to SOS”
    LaunchedEffect(accel) {
        if (accel > 25f) { // sacudida fuerte
            Toast.makeText(context, "Sacudida detectada (SOS)", Toast.LENGTH_SHORT).show()
        }
    }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(8.dp))
        Text("Alertas en\nTu zona", style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp), textAlign = TextAlign.Center)
        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.elevatedCardElevation(6.dp)
        ) {
            Box(Modifier.fillMaxWidth().height(300.dp), contentAlignment = Alignment.Center) {
                if (hasGms) {
                    Box(Modifier.size(280.dp).clip(CircleShape)) {
                        GoogleMap(
                            modifier = Modifier.matchParentSize(),
                            cameraPositionState = camera,
                            properties = MapProperties(isMyLocationEnabled = loc.hasPermission),
                            uiSettings = MapUiSettings(zoomControlsEnabled = false, myLocationButtonEnabled = false)
                        )
                    }
                } else {
                    Text("Mapa no disponible (sin Google Play Services)", textAlign = TextAlign.Center, modifier = Modifier.padding(16.dp))
                }
            }
        }

        Spacer(Modifier.height(12.dp))
        // Info de sensores mínima para sustentar “2 sensores”
        Text("Acelerómetro: ${"%.1f".format(accel)} m/s² · Luz: ${light?.let { "%.0f lx".format(it) } ?: "--"}")

        Spacer(Modifier.height(16.dp))
        OutlinedButton(
            onClick = onGoToChat,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(52.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            shape = MaterialTheme.shapes.medium
        ) { Text("Chat vecinal") }

        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { Toast.makeText(context, "Alerta silenciosa enviada", Toast.LENGTH_SHORT).show() },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(64.dp).shadow(12.dp, RoundedCornerShape(14.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(14.dp)
        ) {
            Box(
                Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(ZonRed, ZonRedDark)), RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.NotificationsOff, contentDescription = null, tint = Color.White)
                    Spacer(Modifier.width(8.dp))
                    Text("Alerta silenciosa", color = Color.White)
                }
            }
        }
    }
}
