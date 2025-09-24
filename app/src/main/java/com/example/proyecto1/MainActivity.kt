package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.fillMaxSize
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto1.nav.Route
import com.example.proyecto1.ui.theme.Proyecto1Theme
import com.example.proyecto1.ui.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val nav = rememberNavController()

                    NavHost(
                        navController = nav,
                        startDestination = Route.Home.route
                    ) {
                        composable(Route.Home.route) {
                            // Pantalla inicial (usa la que prefieras)
                            IncidentsMapScreen(
                                onOpenChat = { nav.navigate(Route.Chat.route) },
                                onOpenReports = { nav.navigate(Route.Reports.route) },
                                onOpenProfile = { nav.navigate(Route.Profile.route) }
                            )
                        }
                        composable(Route.Chat.route) { ChatScreen() }
                        composable(Route.Reports.route) { ReportsScreen() }
                        composable(Route.Profile.route) { ProfileScreen() }
                        composable(Route.Admin.route) { AdminPanelScreen() }
                        composable(Route.RoleVerification.route) { RoleVerificationScreen() }
                    }
                }
            }
        }
    }
}
