package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyecto1.data.Report
import com.example.proyecto1.data.ReportsRepository
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ReportsListScreen(onCreate: () -> Unit) {
    val reportsState = remember { mutableStateOf<List<Report>>(emptyList()) }

    LaunchedEffect(Unit) {
        ReportsRepository.reportsFlow().collectLatest { list ->
            reportsState.value = list
        }
    }

    val reports = reportsState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Reportes recientes",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = onCreate) {
            Text("Crear reporte")
        }

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(reports, key = { it.id }) { r ->
                ElevatedCard {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(
                            r.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            r.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            "Severidad: ${r.severity}  •  (${r.lat.format(5)}, ${r.lng.format(5)})",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

private fun Double?.format(n: Int): String =
    this?.let { "%.${n}f".format(it) } ?: "-"
