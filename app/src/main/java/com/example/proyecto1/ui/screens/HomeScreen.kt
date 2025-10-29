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
import com.example.proyecto1.util.rememberMockLocation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun HomeScreen(onGoToChat: () -> Unit = {}) {
    val context = LocalContext.current
    // ✅ Usamos una función SIN ambigüedad
    val myLoc: LatLng? = rememberMockLocation()

    val center = myLoc ?: LatLng(4.6539, -74.0580)
    val camera = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 14.5f)
    }
    LaunchedEffect(myLoc) {
        myLoc?.let { camera.animate(CameraUpdateFactory.newLatLngZoom(it, 15f), 800) }
    }

    var showConfirm by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(8.dp))
        Text(
            "Alertas en\nTu zona",
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(12.dp))

        // MAPA dentro de Card
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
                Box(Modifier.size(280.dp).clip(CircleShape)) {
                    GoogleMap(
                        modifier = Modifier.matchParentSize(),
                        cameraPositionState = camera,
                        properties = MapProperties(isMyLocationEnabled = false),
                        uiSettings = MapUiSettings(
                            zoomControlsEnabled = false,
                            myLocationButtonEnabled = false
                        )
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
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            shape = MaterialTheme.shapes.medium
        ) { Text("Chat vecinal") }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { showConfirm = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(64.dp)
                .shadow(12.dp, RoundedCornerShape(14.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(14.dp)
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(listOf(ZonRed, ZonRedDark)),
                        RoundedCornerShape(14.dp)
                    ),
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

    if (showConfirm) {
        AlertDialog(
            onDismissRequest = { showConfirm = false },
            text = { Text("¿Desea enviar una alerta silenciosa a las autoridades?") },
            confirmButton = {
                FilledTonalButton(onClick = {
                    showConfirm = false
                    Toast.makeText(context, "Alerta silenciosa enviada", Toast.LENGTH_SHORT).show()
                }) { Text("Confirmar") }
            },
            dismissButton = { OutlinedButton({ showConfirm = false }) { Text("Cancelar") } }
        )
    }
}
