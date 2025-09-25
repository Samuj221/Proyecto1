package com.example.proyecto1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyecto1.R
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(
    onProfile: () -> Unit,
    onSettings: () -> Unit,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(300.dp)) {

                Spacer(Modifier.height(24.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // ⬇️ Asegúrate de tener drawable/zonapp_logo.png (o .webp/.jpg)
                    Image(
                        painter = painterResource(id = R.drawable.zonapp_logo),
                        // Si aún no tienes el archivo, usa temporalmente:
                        // painter = painterResource(id = R.mipmap.ic_launcher),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    )

                    Text(
                        text = "Zonapp",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }

                NavigationDrawerItem(
                    icon = { androidx.compose.material3.Icon(Icons.Outlined.Person, contentDescription = null) },
                    label = { Text("Perfil") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        onProfile()
                    }
                )

                NavigationDrawerItem(
                    icon = { androidx.compose.material3.Icon(Icons.Outlined.Settings, contentDescription = null) },
                    label = { Text("Ajustes") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        onSettings()
                    }
                )
            }
        },
        content = content
    )
}
