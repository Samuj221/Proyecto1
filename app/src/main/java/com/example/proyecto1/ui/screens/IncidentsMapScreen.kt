package com.example.proyecto1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proyecto1.R

@Composable
fun IncidentsMapScreen(
    onOpenChat: () -> Unit,
    onReportIncident: () -> Unit,
    onSilentAlert: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Tarjeta encabezado azul
        ElevatedCard(
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(12.dp)) {
                Text("Comunidad Nueva Granada", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
                Text("Lunes 22 de septiembre de 2025 • 4:30 p.m.", color = MaterialTheme.colorScheme.onPrimary)
            }
        }

        Spacer(Modifier.height(12.dp))

        // Mapa (placeholder imagen en drawable)
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.map_placeholder), // coloca un PNG en res/drawable
                contentDescription = "Mapa",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(Modifier.height(12.dp))
        Text("Incidentes", style = MaterialTheme.typography.titleMedium)
        Text("Hoy", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = .6f))

        Spacer(Modifier.height(8.dp))

        // Botón naranja: "Mis reportes"
        Button(
            onClick = onReportIncident,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) { Text("Mis reportes") }

        Spacer(Modifier.height(8.dp))

        // Botón rojo: "Reportar incidente…"
        Button(
            onClick = onReportIncident,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) { Text("Reportar incidente…") }

        Spacer(Modifier.height(16.dp))

        // Botón azul: Chat Vecinal (como en la primera imagen)
        Button(
            onClick = onOpenChat,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) { Text("Chat Vecinal") }

        Spacer(Modifier.height(8.dp))

        // Botón rojo grande: Alerta silenciosa (también tienes el FAB)
        Button(
            onClick = onSilentAlert,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) { Text("Alerta silenciosa") }
    }
}
