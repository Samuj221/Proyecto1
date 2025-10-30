package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.example.proyecto1.data.Report
import com.example.proyecto1.data.ReportsRepository

@Composable
fun ReportsListScreen(onCreate: () -> Unit) {
    val ctx = LocalContext.current

    // Carga inicial desde preferencias
    LaunchedEffect(Unit) { ReportsRepository.load(ctx) }

    // Evito collectAsState para no depender de imports: recojo a mano el flujo
    val reportsState = remember { mutableStateOf<List<Report>>(emptyList()) }
    LaunchedEffect(Unit) {
        ReportsRepository.items.collect { reportsState.value = it }
    }
    val reports = reportsState.value

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Reportes recientes", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Button(onClick = onCreate) { Text("Crear reporte") }
        Spacer(Modifier.height(12.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(reports, key = { it.id }) { r ->
                ElevatedCard {
                    Column(Modifier.fillMaxWidth().padding(12.dp)) {
                        Text(r.title, style = MaterialTheme.typography.titleMedium)
                        Text(r.description, style = MaterialTheme.typography.bodyMedium)
                        Text("Severidad: ${r.severity}  •  (${r.lat.format(5)}, ${r.lng.format(5)})",
                            style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

private fun Double.format(n: Int) = "%.${n}f".format(this)
