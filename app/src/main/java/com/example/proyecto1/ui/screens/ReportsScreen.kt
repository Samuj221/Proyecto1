package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyecto1.R

@Composable
fun ReportsScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        ElevatedCard(shape = RoundedCornerShape(10.dp), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text("Choque de vehículo", color = MaterialTheme.colorScheme.onSurface)
                Spacer(Modifier.height(8.dp))
                Card(shape = RoundedCornerShape(12.dp)) {
                    Image(
                        painter = painterResource(R.drawable.map_placeholder),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().height(160.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(Modifier.height(12.dp))
                Text("Vecino involucrado — Estado de salud", style = MaterialTheme.typography.titleSmall)
                Spacer(Modifier.height(8.dp))
                // Repite filas según tus datos
                ListItem(headlineContent = { Text("Pacho Gutierrez") }, supportingContent = { Text("Bien") })
                ListItem(headlineContent = { Text("Jose Fernandez") }, supportingContent = { Text("Bien") })
                ListItem(headlineContent = { Text("Martin Martinez") }, supportingContent = { Text("Fallecido") })
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { /* TODO: PDF */ }, modifier = Modifier.weight(1f)) { Text("Exportar PDF") }
                    OutlinedButton(onClick = { /* TODO: CSV */ }, modifier = Modifier.weight(1f)) { Text("Exportar CSV") }
                }
            }
        }
    }
}
