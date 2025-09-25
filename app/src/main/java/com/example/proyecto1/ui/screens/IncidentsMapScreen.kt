package com.example.proyecto1.ui.screens

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.Menu
import androidx.compose.material3.icons.filled.Settings
import androidx.compose.material3.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proyecto1.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun IncidentsMapScreen(
    onOpenChat: () -> Unit,
    onOpenReports: () -> Unit,
    onOpenProfile: () -> Unit,
    onOpenAdmin: () -> Unit,
    onOpenRoleVerification: () -> Unit,
    onSilentAlertConfirmed: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Permiso de ubicación (opcional, por si luego centras el mapa en el usuario)
    val fineLocation = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    // Estado del mapa (posición inicial Bogotá / ajusta a tu zona)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(4.6482837, -74.247894), 12f)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp)
            ) {
                // Encabezado del drawer con el LOGO
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.zonapp_logo),
                        contentDescription = "Logo Zonapp",
                        modifier = Modifier
                            .size(128.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = "Zonapp",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }

                NavigationDrawerItem(
                    label = { Text("Nombre") },
                    selected = false,
                    onClick = { /* abre pantalla de perfil/ajustes si quieres */ },
                    icon = { Icon(Icons.Default.Person, contentDescription = null) }
                )
                NavigationDrawerItem(
                    label = { Text("Ajustes") },
                    selected = false,
                    onClick = { /* Abre ajustes */ },
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) }
                )
                Spacer(Modifier.height(16.dp))
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Zonapp") },
                    navigationIcon = {
                        IconButton(onClick = { if (drawerState.isClosed) scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
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
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Viewport del mapa (Google Maps Compose)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    // Si no has agregado la dependencia de Maps Compose, comenta este bloque.
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        uiSettings = MapUiSettings(zoomControlsEnabled = false),
                        properties = MapProperties(isMyLocationEnabled = fineLocation.status.isGranted)
                    ) {
                        Marker(
                            state = rememberMarkerState(position = cameraPositionState.position.target),
                            title = "Mi zona",
                            snippet = "Marca de ejemplo"
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Botones principales
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onOpenChat
                ) { Text("Chat vecinal") }

                Spacer(Modifier.height(12.dp))

                // Alerta silenciosa
                var showConfirm by remember { mutableStateOf(false) }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    onClick = { showConfirm = true }
                ) { Text("Alerta silenciosa") }

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
                        dismissButton = {
                            TextButton(onClick = { showConfirm = false }) { Text("Cancelar") }
                        }
                    )
                }

                Spacer(Modifier.height(12.dp))

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onOpenReports
                ) { Text("Reportes") }

                Spacer(Modifier.height(12.dp))

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onOpenRoleVerification
                ) { Text("Verificación de rol") }
            }
        }
    }
}
