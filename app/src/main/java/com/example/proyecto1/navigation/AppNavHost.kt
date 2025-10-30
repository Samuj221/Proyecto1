package com.example.proyecto1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyecto1.ui.screens.*

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = Routes.Home.route
) {
    NavHost(navController, startDestination) {
        composable(Routes.Home.route) {
            HomeScreen(onGoToChat = { navController.navigate(Routes.Chat.route) })
        }
        composable(Routes.Reports.route) {
            ReportsListScreen(onCreate = { navController.navigate(Routes.ReportCreate.route) })
        }
        composable(Routes.ReportCreate.route) {
            ReportCreateScreen(onDone = { navController.popBackStack() })
        }
        composable(Routes.Chat.route)    { ChatScreen() }
        composable(Routes.Profile.route) { ProfileScreen() }
        composable(Routes.Admin.route)   { AdminPanelScreen() }
    }
}
