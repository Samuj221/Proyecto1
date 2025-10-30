package com.example.proyecto1.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ListAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.proyecto1.navigation.Routes

data class BottomItem(val title: String, val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

private val items = listOf(
    BottomItem("Inicio",   Routes.Home.route,        Icons.Rounded.Home),
    BottomItem("Reportes", Routes.ReportsList.route, Icons.Rounded.ListAlt),
    BottomItem("Chat",     Routes.Chat.route,        Icons.Rounded.Chat),
)

@Composable
fun ZBottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        items.forEach { item ->
            val selected = currentDestination.isTopLevel(item.route)
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(Routes.Home.route) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) }
            )
        }
    }
}

private fun NavDestination?.isTopLevel(route: String): Boolean =
    this?.hierarchy?.any { it.route == route } == true
