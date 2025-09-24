package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen() {
    var text by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(12.dp)) {
        // Mensajes de ejemplo
        AssistChip(onClick = {}, label = { Text("¿Alguien sabe qué pasó esta mañana?") })
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Surface(shape = RoundedCornerShape(12.dp), color = MaterialTheme.colorScheme.secondary) {
                Text("Listo, muchas gracias.", modifier = Modifier.padding(10.dp))
            }
        }

        Spacer(Modifier.weight(1f))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = text, onValueChange = { text = it },
                modifier = Modifier.weight(1f), placeholder = { Text("Mensaje…") }
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = { text = "" }) { Text("Enviar") }
        }
    }
}
