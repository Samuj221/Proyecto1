package com.example.proyecto1.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppDrawer(onItemClick: (DrawerItem) -> Unit) {
    Column {
        Text(
            text = "Menú",
            modifier = Modifier.padding(16.dp)
        )

        // Lista de opciones del menú
        listOf(
            DrawerItem.Profile,
            DrawerItem.Settings,
            DrawerItem.Admin,
            DrawerItem.Reports,
            DrawerItem.Incidents,
            DrawerItem.Chat,
            DrawerItem.RoleVerification
        ).forEach { item ->
            Text(
                text = item.title,
                modifier = Modifier
                    .clickable { onItemClick(item) }
                    .padding(16.dp)
            )
        }
    }
}
