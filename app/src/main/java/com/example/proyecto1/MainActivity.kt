@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class, // p/ SearchBar, DatePicker, ModalBottomSheet, etc.
    androidx.compose.foundation.ExperimentalFoundationApi::class // si usas stickyHeader en esta pantalla
)

package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

// Screens de tu proyecto (ajusta los nombres si difieren)
import com.example.proyecto1.ui.theme.Proyecto1Theme
import com.example.proyecto1.ui.theme.ChatScreen
import com.example.proyecto1.ui.theme.IncidentsMapScreen
import com.example.proyecto1.ui.theme.ReportsScreen
import com.example.proyecto1.ui.theme.ProfileScreen
import com.example.proyecto1.ui.theme.AdminPanelScreen
import com.example.proyecto1.ui.theme.RoleVerificationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScaffold()
                }
            }
        }
    }
}

@Composable
private fun AppScaffold() {
    val nav = rememberNavController()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selected by remember { mutableStateOf("incidents") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawerItem(
                label = { Text("Incidentes") },
                selected = selected == "incidents",
                onClick = {
                    selected = "incidents"
                    scope.launch { drawerState.close() }
                    nav.navigate("incidents") { launchSingleTop = true }
                },
                colors = NavigationDrawerItemDefaults.colors()
            )
            NavigationDrawerItem(
                label = { Text("Reportes") },
                selected = selected == "reports",
                onClick = {
                    selected = "reports"
                    scope.launch { drawerState.close() }
                    nav.navigate("reports") { launchSingleTop = true }
                }
            )
            NavigationDrawerItem(
                label = { Text("Chat vecinal") },
                selected = selected == "chat",
                onClick = {
                    selected = "chat"
                    scope.launch { drawerState.close() }
                    nav.navigate("chat") { launchSingleTop = true }
                }
            )
            NavigationDrawerItem(
                label = { Text("Perfil") },
                selected = selected == "profile",
                onClick = {
                    selected = "profile"
                    scope.launch { drawerState.close() }
                    nav.navigate("profile") { launchSingleTop = true }
                }
            )
            NavigationDrawerItem(
                label = { Text("Verificación de rol") },
                selected = selected == "role",
                onClick = {
                    selected = "role"
                    scope.launch { drawerState.close() }
                    nav.navigate("role") { launchSingleTop = true }
                }
            )
            NavigationDrawerItem(
                label = { Text("Panel admin") },
                selected = selected == "admin",
                onClick = {
                    selected = "admin"
                    scope.launch { drawerState.close() }
                    nav.navigate("admin") { launchSingleTop = true }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Zonapp") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { padding ->
            // NavHost
            NavHost(
                navController = nav,
                startDestination = "incidents"
            ) {
                composable("incidents") {
                    IncidentsMapScreen(padding = padding)
                }
                composable("reports") {
                    ReportsScreen(padding = padding)
                }
                composable("chat") {
                    ChatScreen(padding = padding)
                }
                composable("profile") {
                    ProfileScreen(padding = padding)
                }
                composable("role") {
                    RoleVerificationScreen(padding = padding)
                }
                composable("admin") {
                    AdminPanelScreen(padding = padding)
                }

                // Ejemplo de pantalla con argumento (si luego lo necesitas)
                composable(
                    route = "reportDetail?id={id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType })
                ) { backStack ->
                    val id = backStack.arguments?.getString("id").orEmpty()
                    ReportsScreen(padding = padding, selectedId = id)
                }
            }
        }
    }
}
