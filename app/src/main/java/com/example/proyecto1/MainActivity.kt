package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto1.nav.Route
import com.example.proyecto1.ui.screens.*
import com.example.proyecto1.ui.theme.Proyecto1Theme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto1Theme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val nav = rememberNavController()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet(
                            drawerContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = .45f),
                            modifier = Modifier.width(300.dp)
                        ) {
                            Spacer(Modifier.height(24.dp))
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.zonapp_logo // <--- tu logo en res/drawable
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(128.dp)
                                        .clip(CircleShape)
                                )
                                Text(
                                    text = "Zonapp",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(top = 12.dp)
                                )
                            }
                            Spacer(Modifier.height(32.dp))

                            NavigationDrawerItem(
                                icon = { Icon(Icons.Default.Person, null) },
                                label = { Text("Nombre") },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    nav.navigate(Route.Profile.route)
                                },
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                            NavigationDrawerItem(
                                icon = { Icon(Icons.Default.Settings, null) },
                                label = { Text("Ajustes") },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    nav.navigate(Route.RoleVerification.route)
                                },
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                            Spacer(Modifier.height(24.dp))
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            SmallTopAppBar(
                                title = { Text("Zonapp") },
                                navigationIcon = {
                                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                        Icon(
                                            painterResource(id = R.drawable.ic_menu), // si no tienes, usa Icons.Filled.Settings
                                            contentDescription = null
                                        )
                                    }
                                },
                                actions = {
                                    TextButton(onClick = { nav.navigate(Route.Profile.route) }) { Text("Perfil") }
                                    TextButton(onClick = { nav.navigate(Route.Admin.route) }) { Text("Admin") }
                                }
                            )
                        }
                    ) { padding ->
                        NavHost(
                            navController = nav,
                            startDestination = Route.Home.route,
                            modifier = Modifier.padding(padding)
                        ) {
                            composable(Route.Home.route) {
                                IncidentsMapScreen(
                                    onOpenChat = { nav.navigate(Route.Chat.route) },
                                    onOpenReports = { nav.navigate(Route.Reports.route) },
                                    onOpenProfile = { nav.navigate(Route.Profile.route) },
                                    onOpenAdmin = { nav.navigate(Route.Admin.route) },
                                    onOpenRoleVerification = { nav.navigate(Route.RoleVerification.route) },
                                    onSilentAlertConfirmed = { /* TODO lógica de alerta */ }
                                )
                            }
                            composable(Route.Chat.route) {
                                ChatScreen(onBack = { nav.popBackStack() })
                            }
                            composable(Route.Reports.route) {
                                ReportsScreen(onBack = { nav.popBackStack() })
                            }
                            composable(Route.Profile.route) {
                                ProfileScreen(onBack = { nav.popBackStack() })
                            }
                            composable(Route.Admin.route) {
                                AdminPanelScreen(onBack = { nav.popBackStack() })
                            }
                            composable(Route.RoleVerification.route) {
                                RoleVerificationScreen(onBack = { nav.popBackStack() })
                            }
                        }
                    }
                }
            }
        }
    }
}
