package com.example.proyecto1.ui.components

// Ítems del Drawer (menú lateral)
sealed class DrawerItem(val title: String, val route: String) {
    object Profile : DrawerItem("Perfil", "profile")
    object Settings : DrawerItem("Configuración", "settings")
    object Admin : DrawerItem("Administrador", "admin")
    object Reports : DrawerItem("Reportes", "reports")
    object Incidents : DrawerItem("Incidentes", "incidents")
    object Chat : DrawerItem("Chat", "chat")
    object RoleVerification : DrawerItem("Verificación de Rol", "roleVerification")
}
