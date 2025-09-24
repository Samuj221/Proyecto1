package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onBack: () -> Unit) {
    var name by remember { mutableStateOf("Juan Salazar") }
    var email by remember { mutableStateOf("persona@gmail.com") }
    val role = "Moderador"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Zonapp") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Correo") }, modifier = Modifier.fillMaxWidth())

            Text("Rol")
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) { Text(role, modifier = Modifier.padding(12.dp)) }

            Text("Tu Zona")
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().height(150.dp)
            ) { Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Mapa de tu zona") } }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { /* guardar */ }, modifier = Modifier.weight(1f)) { Text("Guardar cambios") }
                OutlinedButton(onClick = { /* logout */ }, modifier = Modifier.weight(1f)) { Text("Cerrar sesión") }
            }
        }
    }
}
