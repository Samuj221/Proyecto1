package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto1.nav.Route
import com.example.proyecto1.ui.components.AppDrawer
import com.example.proyecto1.ui.components.DrawerItem
import com.example.proyecto1.ui.screens.*
import com.example.proyecto1.ui.theme.Proyecto1Theme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto1Theme {
                val nav = rememberNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        AppDrawer { item ->
                            scope.launch { drawerState.close() }
                            when (item) {
                                DrawerItem.Profile -> nav.navigate(Route.Profile.route)
                                DrawerItem.Settings -> nav.navigate(Route.Admin.route)
                            }
                        }
                    }
                ) {
                    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        NavHost(navController = nav, startDestination = Route.Home.route) {
                            composable(Route.Home.route) {
                                IncidentsMapScreen(
                                    onMenuClick = { scope.launch { drawerState.open() } },
                                    onOpenChat = { nav.navigate(Route.Chat.route) },
                                    onOpenReports = { nav.navigate(Route.Reports.route) },
                                    onOpenProfile = { nav.navigate(Route.Profile.route) },
                                    onOpenAdmin = { nav.navigate(Route.Admin.route) },
                                    onOpenRoleVerification = { nav.navigate(Route.RoleVerification.route) },
                                    onSilentAlertConfirmed = { /* TODO */ }
                                )
                            }
                            composable(Route.Chat.route) { ChatScreen(onBack = { nav.popBackStack() }) }
                            composable(Route.Reports.route) { ReportsScreen(onBack = { nav.popBackStack() }) }
                            composable(Route.Profile.route) { ProfileScreen(onBack = { nav.popBackStack() }) }
                            composable(Route.Admin.route) { AdminPanelScreen(onBack = { nav.popBackStack() }) }
                            composable(Route.RoleVerification.route) { RoleVerificationScreen(onBack = { nav.popBackStack() }) }
                        }
                    }
                }
            }
        }
    }
}
