package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {
    var nombre by remember { mutableStateOf("Juan Salazar") }
    var correo by remember { mutableStateOf("persona@gmail.com") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Ajustes de usuario", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))
        Button(onClick = { /* guardar */ }) { Text("Guardar cambios") }
    }
}
