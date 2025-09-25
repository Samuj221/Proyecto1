package com.example.proyecto1.ui.navigation

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Reports : Routes("reports")
    data object ReportCreate : Routes("report_create")
    data object Chat : Routes("chat")
    data object Profile : Routes("profile")
    data object Admin : Routes("admin_panel")
}
