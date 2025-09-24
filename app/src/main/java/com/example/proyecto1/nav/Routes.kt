package com.example.proyecto1.nav

sealed class Route(val route: String) {
    data object Map : Route("map")
    data object Chat : Route("chat")
    data object Reports : Route("reports")
    data object Profile : Route("profile")
    data object RoleVerify : Route("role_verify")
    data object AdminPanel : Route("admin_panel")
}
