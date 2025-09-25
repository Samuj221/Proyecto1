package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.proyecto1.nav.Route
import com.example.proyecto1.ui.screens.*
import com.example.proyecto1.ui.theme.Proyecto1Theme
import com.example.proyecto1.ui.components.AppDrawer
import com.example.proyecto1.ui.components.DrawerItem
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto1Theme {
                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    val nav = rememberNavController()
                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    val backStackEntry by nav.currentBackStackEntryAsState()

                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            AppDrawer(
                                currentRoute = backStackEntry?.destination?.route
                            ) { item ->
                                scope.launch { drawerState.close() }
                                when (item) {
                                    DrawerItem.Profile -> nav.navigate(Route.Profile.route)
                                    DrawerItem.Settings -> nav.navigate(Route.Admin.route) // o tu pantalla de ajustes
                                }
                            }
                        }
                    ) {
                        AppNav(
                            onOpenDrawer = { scope.launch { drawerState.open() } },
                            navigate = { route -> nav.navigate(route) },
                            popBack = { nav.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

/** Tu NavHost central */
@Composable
private fun AppNav(
    onOpenDrawer: () -> Unit,
    navigate: (String) -> Unit,
    popBack: () -> Unit
) {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Route.Home.route) {

        composable(Route.Home.route) {
            // Tu pantalla principal (mapa + botones)
            IncidentsMapScreen(
                onMenuClick = onOpenDrawer,       // <<< abre el drawer
                onOpenChat = { navigate(Route.Chat.route) },
                onOpenReports = { navigate(Route.Reports.route) },
                onOpenProfile = { navigate(Route.Profile.route) },
                onOpenAdmin = { navigate(Route.Admin.route) },
                onOpenRoleVerification = { navigate(Route.RoleVerification.route) },
                onSilentAlertConfirmed = { /* lógica del alerta */ }
            )
        }

        composable(Route.Chat.route)    { ChatScreen   (onBack = popBack) }
        composable(Route.Reports.route) { ReportsScreen(onBack = popBack) }
        composable(Route.Profile.route) { ProfileScreen(onBack = popBack) }
        composable(Route.Admin.route)   { AdminPanelScreen(onBack = popBack) }
        composable(Route.RoleVerification.route) { RoleVerificationScreen(onBack = popBack) }
    }
}
