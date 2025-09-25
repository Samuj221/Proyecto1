package com.example.proyecto1.ui.navigation

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Incidents : Routes("incidents_map")
    data object Chat : Routes("chat")
    data object Profile : Routes("profile")
    data object Admin : Routes("admin_panel")
}
