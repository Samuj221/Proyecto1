// app/src/main/java/com/example/proyecto1/ui/components/AppDrawer.kt
package com.example.proyecto1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proyecto1.R

@Composable
fun AppDrawer(
    items: List<DrawerItem>,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        // Header con logo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(MaterialTheme.colorScheme.primaryContainer),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.`zonapp_logo`),
                contentDescription = "Zonapp logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .height(96.dp)
            )
            Text(
                "Zonapp",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )
        }

        Divider()

        LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
            items(items) { item ->
                NavigationDrawerItem(
                    label = { Text(item.title) },
                    icon = { androidx.compose.material3.Icon(imageVector = item.icon, contentDescription = item.title) },
                    selected = false,
                    onClick = { onItemClick(item.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}