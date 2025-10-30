package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.proyecto1.navigation.Routes
import com.example.proyecto1.navigation.AppNavHost
import com.example.proyecto1.ui.components.AppDrawer
import com.example.proyecto1.ui.components.DrawerItem
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
        val nav     = rememberNavController()
        val drawer  = rememberDrawerState(DrawerValue.Closed)
        val scope   = rememberCoroutineScope()

        val items = listOf(
            DrawerItem("Inicio",     Icons.Rounded.Home,      Routes.Home.route),
            DrawerItem("Reportes",   Icons.Rounded.ListAlt,   Routes.Reports.route),
            DrawerItem("Chat vecinal", Icons.Rounded.Chat,    Routes.Chat.route),
            DrawerItem("Ajustes",    Icons.Rounded.Settings,  Routes.Profile.route),
            DrawerItem("Admin",      Icons.Rounded.Verified,  Routes.Admin.route),
        )

        ModalNavigationDrawer(
            drawerState = drawer,
            drawerContent = {
                AppDrawer(items) { route ->
                    scope.launch {
                        drawer.close()
                        nav.navigate(route) {
                            popUpTo(Routes.Home.route) { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                }
            }
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("Zonapp") },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawer.open() } }) {
                                Icon(Icons.Rounded.Menu, contentDescription = "Menú")
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* TODO: notificaciones */ }) {
                                Icon(Icons.Rounded.Notifications, contentDescription = "Notificaciones")
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
                    )
                }
            ) { inner ->
                // host centralizado
                AppNavHost(
                    navController = nav,
                    startDestination = Routes.Home.route
                )
            }
        }
    }
}
