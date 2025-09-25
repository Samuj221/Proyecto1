package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Zonapp") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Nombre")
            TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Juan Salazar") })

            Text("Correo")
            TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(), placeholder = { Text("persona@gmail.com") })

            Text("Rol")
            Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(12.dp)) {
                Text("Moderador", modifier = Modifier.padding(12.dp))
            }

            Text("Tu Zona")
            Surface(modifier = Modifier.fillMaxWidth().height(150.dp), color = MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(12.dp)) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Mapa de tu zona") }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { }, modifier = Modifier.weight(1f)) { Text("Guardar cambios") }
                OutlinedButton(onClick = { }, modifier = Modifier.weight(1f)) { Text("Cerrar sesión") }
            }
        }
    }
}
