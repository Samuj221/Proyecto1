package com.example.proyecto1.navigation

sealed class Routes(val route: String) {
    data object Home         : Routes("home")
    data object ReportsList  : Routes("reports_list")
    data object ReportCreate : Routes("report_create")
    data object Chat         : Routes("chat")
    data object Profile      : Routes("profile")
    data object Admin        : Routes("admin")
    data object IncidentsMap : Routes("incidents_map")
}
