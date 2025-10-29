package com.example.proyecto1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyecto1.ui.screens.AdminPanelScreen
import com.example.proyecto1.ui.screens.ChatScreen
import com.example.proyecto1.ui.screens.HomeScreen
import com.example.proyecto1.ui.screens.ProfileScreen
import com.example.proyecto1.ui.screens.ReportCreateScreen
import com.example.proyecto1.ui.screens.ReportsListScreen

/**
 * Versión simple, sin Accompanist ni animaciones.
 * Si ya usas NavHost directamente en MainActivity, puedes dejar este igual o
 * no usarlo; pero debe compilar si está en el proyecto.
 */
@Composable
fun AppNavGraph(nav: NavHostController) {
    NavHost(
        navController = nav,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) {
            HomeScreen(onGoToChat = { nav.navigate(Routes.Chat.route) })
        }
        composable(Routes.Reports.route) {
            ReportsListScreen(onCreate = { nav.navigate(Routes.ReportCreate.route) })
        }
        composable(Routes.ReportCreate.route) {
            ReportCreateScreen(onDone = { nav.popBackStack() })
        }
        composable(Routes.Chat.route) { ChatScreen() }
        composable(Routes.Profile.route) { ProfileScreen() }
        composable(Routes.Admin.route) { AdminPanelScreen() }
    }
}
