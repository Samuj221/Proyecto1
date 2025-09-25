package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IncidentsMapScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Mapa de incidentes")
        Spacer(Modifier.height(8.dp))
        Button(onClick = { /* TODO: mostrar reporte */ }) {
            Text("Reportar incidente")
        }
    }
}
