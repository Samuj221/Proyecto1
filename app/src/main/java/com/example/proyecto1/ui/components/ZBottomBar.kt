package com.samupro.proyecto1.ui.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.samupro.proyecto1.navigation.Dest
import com.samupro.proyecto1.ui.theme.ZBlue
import com.samupro.proyecto1.ui.theme.ZOrange


@Composable
fun ZBottomBar(nav: NavHostController) {
    val items = Dest.bottom
    val entry by nav.currentBackStackEntryAsState()
    val current = entry?.destination


    NavigationBar {
        items.forEach { dest ->
            val selected = current.isTopLevel(dest)
            NavigationBarItem(
                selected = selected,
                onClick = { if (!selected) nav.navigate(dest.route) { launchSingleTop = true; popUpTo(Dest.Home.route) { saveState = true }; restoreState = true } },
                icon = {
                    val icon = when (dest) {
                        Dest.Home -> Icons.Outlined.Home
                        Dest.Explore -> Icons.Outlined.Explore
                        Dest.Profile -> Icons.Outlined.Person
                        Dest.Settings -> Icons.Outlined.Settings
                    }
                    Icon(icon, contentDescription = dest.label)
                },
                label = { Text(dest.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}


private fun NavDestination?.isTopLevel(dest: Dest) = this?.hierarchy?.any { it.route == dest.route } == true