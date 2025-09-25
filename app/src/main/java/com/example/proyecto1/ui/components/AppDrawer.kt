package com.example.proyecto1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proyecto1.R
import com.example.proyecto1.ui.theme.ZonBlue
import com.example.proyecto1.ui.theme.ZonBlueContainer

@Composable
fun AppDrawer(items: List<DrawerItem>, onItemClick: (String) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        // Header moderno con gradiente y logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(
                    Brush.verticalGradient(listOf(ZonBlue, ZonBlueContainer))
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(R.drawable.zonapp_logo),
                    contentDescription = "Zonapp",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(96.dp)
                )
                Text("Zonapp", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
            }
        }

        LazyColumn {
            items(items) { item ->
                NavigationDrawerItem(
                    label = { Text(item.title) },
                    icon = { Icon(item.icon, null) },
                    selected = false,
                    onClick = { onItemClick(item.route) },
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}
