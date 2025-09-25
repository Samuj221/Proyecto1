package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto1.ui.components.AppDrawer
import com.example.proyecto1.ui.components.DrawerItem
import com.example.proyecto1.ui.navigation.Routes
import com.example.proyecto1.ui.screens.*
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
            DrawerItem("Reportes", Icons.Default.List, Routes.Reports.route),
            DrawerItem("Chat vecinal", Icons.Default.Chat, Routes.Chat.route),
            DrawerItem("Ajustes", Icons.Default.Settings, Routes.Profile.route),
            DrawerItem("Admin", Icons.Default.VerifiedUser, Routes.Admin.route),
        )

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = { AppDrawer(items) { route ->
                scope.launch {
                    drawerState.close()
                    navController.navigate(route) {
                        popUpTo(Routes.Home.route) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            } }
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
                    composable(Routes.Home.route) {
                        HomeScreen(onGoToChat = { navController.navigate(Routes.Chat.route) })
                    }
                    composable(Routes.Reports.route) {
                        ReportsListScreen(onCreate = { navController.navigate(Routes.ReportCreate.route) })
                    }
                    composable(Routes.ReportCreate.route) {
                        ReportCreateScreen(onDone = { navController.popBackStack() })
                    }
                    composable(Routes.Chat.route) { ChatScreen() }
                    composable(Routes.Profile.route) { ProfileScreen() }
                    composable(Routes.Admin.route) { AdminPanelScreen() }
                }
            }
        }
    }
}
