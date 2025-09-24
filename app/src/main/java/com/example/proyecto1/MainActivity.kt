package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto1.nav.Route
import com.example.proyecto1.ui.screens.AdminPanelScreen
import com.example.proyecto1.ui.screens.ChatScreen
import com.example.proyecto1.ui.screens.IncidentsMapScreen
import com.example.proyecto1.ui.screens.ProfileScreen
import com.example.proyecto1.ui.screens.ReportsScreen
import com.example.proyecto1.ui.screens.RoleVerificationScreen
import com.example.proyecto1.ui.theme.Proyecto1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Proyecto1Theme {
                AppNav()
            }
        }
    }
}

@Composable
private fun AppNav() {
    val nav = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(navController = nav, startDestination = Route.Home.route) {

            composable(Route.Home.route) {
                // Pantalla principal tipo "Inicio" (mapa + botones)
                IncidentsMapScreen(
                    onOpenChat = { nav.navigate(Route.Chat.route) },
                    onOpenReports = { nav.navigate(Route.Reports.route) },
                    onOpenProfile = { nav.navigate(Route.Profile.route) },
                    onOpenAdmin = { nav.navigate(Route.Admin.route) },
                    onOpenRoleVerification = { nav.navigate(Route.RoleVerification.route) },
                    onSilentAlertConfirmed = { /* aquí podrías disparar la lógica real */ }
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
