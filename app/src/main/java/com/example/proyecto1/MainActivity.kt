package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto1.ui.components.AppDrawer
import com.example.proyecto1.ui.components.DrawerItem
import com.example.proyecto1.ui.navigation.Routes
import com.example.proyecto1.ui.screens.AdminPanelScreen
import com.example.proyecto1.ui.screens.ChatScreen
import com.example.proyecto1.ui.screens.HomeScreen
import com.example.proyecto1.ui.screens.IncidentsMapScreen
import com.example.proyecto1.ui.screens.ProfileScreen
import com.example.proyecto1.ui.theme.ZonappTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ZonappApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZonappApp() {
    ZonappTheme {
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        val items = listOf(
            DrawerItem("Inicio", Icons.Default.Home, Routes.Home.route),
            DrawerItem("Mapa de incidentes", Icons.Default.Map, Routes.Incidents.route),
            DrawerItem("Chat vecinal", Icons.Default.Chat, Routes.Chat.route),
            DrawerItem("Ajustes de usuario", Icons.Default.Settings, Routes.Profile.route),
            DrawerItem("Panel admin", Icons.Default.VerifiedUser, Routes.Admin.route),
        )

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                AppDrawer(
                    items = items,
                    onItemClick = { route ->
                        scope.launch {
                            drawerState.close()
                            navController.navigate(route) {
                                popUpTo(Routes.Home.route) { inclusive = false }
                                launchSingleTop = true
                            }
                        }
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
                                Icon(Icons.Default.Menu, contentDescription = "Menú")
                            }
                        }
                    )
                }
            ) { inner ->
                NavHost(
                    navController = navController,
                    startDestination = Routes.Home.route,
                    modifier = Modifier.padding(inner)
                ) {
                    composable(Routes.Home.route) { HomeScreen() }
                    composable(Routes.Incidents.route) { IncidentsMapScreen() }
                    composable(Routes.Chat.route) { ChatScreen() }
                    composable(Routes.Profile.route) { ProfileScreen() }
                    composable(Routes.Admin.route) { AdminPanelScreen() }
                }
            }
        }
    }
}