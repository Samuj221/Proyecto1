package com.example.proyecto1.nav

sealed class Route(val route: String) {
    data object Home : Route("home")
    data object Chat : Route("chat")
    data object Reports : Route("reports")
    data object Profile : Route("profile")
    data object Admin : Route("admin")
    data object RoleVerification : Route("role_verification")
    // Si luego necesitas argumentos, añade otra data object con "detail/{id}" y su helper pass(id).
}
