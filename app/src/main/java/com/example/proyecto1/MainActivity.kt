package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.rememberNavController
import com.example.proyecto1.ui.components.AppDrawer
import com.example.proyecto1.ui.components.DrawerItem
import com.example.proyecto1.ui.theme.Proyecto1Theme
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto1Theme {
                val nav = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        AppDrawer { item ->
                            scope.launch { drawerState.close() }
                            when (item) {
                                DrawerItem.Profile -> nav.navigate("profile")
                                DrawerItem.Settings -> nav.navigate("settings")
                                DrawerItem.Admin -> nav.navigate("admin")
                                DrawerItem.Reports -> nav.navigate("reports")
                                DrawerItem.Incidents -> nav.navigate("incidents")
                                DrawerItem.Chat -> nav.navigate("chat")
                                DrawerItem.RoleVerification -> nav.navigate("roleVerification")
                            }
                        }
                    }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        // 👇 Aquí va el contenido de navegación
                        androidx.navigation.compose.NavHost(
                            navController = nav,
                            startDestination = "profile"
                        ) {
                            composable("profile") { ProfileScreen() }
                            composable("settings") { SettingsScreen() }
                            composable("admin") { AdminPanelScreen() }
                            composable("reports") { ReportsScreen() }
                            composable("incidents") { IncidentsMapScreen() }
                            composable("chat") { ChatScreen() }
                            composable("roleVerification") { RoleVerificationScreen() }
                        }
                    }
                }

            }
            }
        }
    }
}
