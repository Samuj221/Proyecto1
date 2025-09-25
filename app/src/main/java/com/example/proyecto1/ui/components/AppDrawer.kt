package com.example.proyecto1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proyecto1.R

sealed interface DrawerItem {
    data object Profile : DrawerItem
    data object Settings : DrawerItem
}

@Composable
fun AppDrawer(
    onItemClick: (DrawerItem) -> Unit
) {
    ModalDrawerSheet {
        // Cabecera con logo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.zonapp_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(120.dp).clip(CircleShape)
            )
            Text(
                "Zonapp",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(top = 12.dp)
            )
        }

        NavigationDrawerItem(
            icon = { Icon(Icons.Outlined.Person, null) },
            label = { Text("Perfil") },
            selected = false,
            onClick = { onItemClick(DrawerItem.Profile) },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            icon = { Icon(Icons.Outlined.Settings, null) },
            label = { Text("Ajustes") },
            selected = false,
            onClick = { onItemClick(DrawerItem.Settings) },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}
