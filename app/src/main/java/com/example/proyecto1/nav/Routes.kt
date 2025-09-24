package com.example.proyecto1.nav

sealed class Route(val route: String) {
    object Home : Route("home")
    object Chat : Route("chat")
    object Reports : Route("reports")
    object Profile : Route("profile")
    object Admin : Route("admin")
    object RoleVerification : Route("role_verification")
}
