package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.proyecto1.nav.Route
import com.example.proyecto1.ui.screens.*
import com.example.proyecto1.ui.theme.ZoneAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ZoneAppTheme {
                val drawer = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val nav = rememberNavController()
                var showSilentAlert by remember { mutableStateOf(false) }

                ModalNavigationDrawer(
                    drawerState = drawer,
                    drawerContent = {
                        ModalDrawerSheet {
                            // Encabezado del Drawer
                            NavigationDrawerItem(
                                label = { Text("Cuenta") },
                                selected = false,
                                onClick = {
                                    nav.navigate(Route.Profile.route)
                                }
                            )
                            NavigationDrawerItem(
                                label = { Text("Ajustes") },
                                selected = false,
                                onClick = {
                                    nav.navigate(Route.Profile.route)
                                }
                            )
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("Zonapp") },
                                navigationIcon = {
                                    IconButton(onClick = { scope.launch { drawer.open() } }) {
                                        Icon(Icons.Default.Menu, contentDescription = "Menú")
                                    }
                                }
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = { showSilentAlert = true },
                                containerColor = MaterialTheme.colorScheme.tertiary
                            ) {
                                Icon(Icons.Default.Notifications, contentDescription = "Alerta silenciosa")
                            }
                        }
                    ) { pad ->
                        NavHost(
                            navController = nav,
                            startDestination = Route.Map.route,
                            modifier = Modifier.padding(pad)
                        ) {
                            composable(Route.Map.route) {
                                IncidentsMapScreen(
                                    onOpenChat = { nav.navigate(Route.Chat.route) },
                                    onReportIncident = { nav.navigate(Route.Reports.route) },
                                    onSilentAlert = { showSilentAlert = true }
                                )
                            }
                            composable(Route.Chat.route) { ChatScreen() }
                            composable(Route.Reports.route) { ReportsScreen() }
                            composable(Route.Profile.route) { ProfileScreen(
                                onGoRoleVerify = { nav.navigate(Route.RoleVerify.route) },
                                onGoAdmin = { nav.navigate(Route.AdminPanel.route) }
                            ) }
                            composable(Route.RoleVerify.route) { RoleVerificationScreen() }
                            composable(Route.AdminPanel.route) { AdminPanelScreen() }
                        }
                    }

                    if (showSilentAlert) {
                        AlertDialog(
                            onDismissRequest = { showSilentAlert = false },
                            title = { Text("¿Enviar alerta silenciosa a las autoridades?") },
                            confirmButton = {
                                TextButton(onClick = { showSilentAlert = false /* TODO: acción real */ }) {
                                    Text("Confirmar")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showSilentAlert = false }) {
                                    Text("Cancelar")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
