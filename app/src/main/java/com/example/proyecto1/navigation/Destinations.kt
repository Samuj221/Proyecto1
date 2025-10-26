package com.samupro.proyecto1.navigation


sealed class Dest(val route: String, val label: String) {
    data object Home : Dest("home", "Inicio")
    data object Explore : Dest("explore", "Explorar")
    data object Profile : Dest("profile", "Perfil")
    data object Settings : Dest("settings", "Ajustes")
    companion object { val bottom = listOf(Home, Explore, Profile, Settings) }
}