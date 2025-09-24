package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class Incident(
    val title: String,
    val time: String,
    val neighborhood: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(onBack: () -> Unit) {
    val items = listOf(
        Incident("Choque de vehículo", "7:47 a. m.", "Comunidad Nueva Granada"),
        Incident("Robo a vivienda", "8:15 a. m.", "Comunidad Nueva Granada"),
        Incident("Riña", "9:40 a. m.", "Parque Central"),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reportes") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            items.forEach { inc ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        // Header como Figma (banda azul)
                        Surface(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = .15f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = inc.neighborhood,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        Spacer(Modifier.height(8.dp))

                        // Título + hora (banda naranja)
                        Surface(
                            color = MaterialTheme.colorScheme.tertiary.copy(alpha = .15f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(Modifier.fillMaxWidth().padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(inc.title, fontWeight = FontWeight.Medium)
                                Text(inc.time)
                            }
                        }

                        Spacer(Modifier.height(12.dp))

                        // Mini “mapa” (placeholder visual)
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                        ) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text("Mapa de la zona (miniatura)")
                            }
                        }

                        Spacer(Modifier.height(12.dp))

                        // Lista de involucrados (como en Figma)
                        Surface(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = .08f),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                Text("Vecino involucrado              Estado de salud")
                                Spacer(Modifier.height(8.dp))
                                Text("Pacho Gutierrez                 Bien")
                                Text("Jose Fernandez                  Bien")
                                Text("Martin Martinez                  Fallecido")
                            }
                        }
                    }
                }
            }
        }
    }
}
