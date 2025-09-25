
package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Chat vecinal (mock)", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text("Aquí iría la lista de mensajes y la caja de texto.")
    }
}
