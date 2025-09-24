package com.example.proyecto1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyecto1.R

@Composable
fun ProfileScreen(onGoRoleVerify: () -> Unit, onGoAdmin: () -> Unit) {
    var nombre by remember { mutableStateOf("Juan Salazar") }
    var correo by remember { mutableStateOf("persona@gmail.com") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.size(72.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(onClick = onGoRoleVerify, modifier = Modifier.weight(1f)) { Text("Verificación de rol") }
            OutlinedButton(onClick = onGoAdmin, modifier = Modifier.weight(1f)) { Text("Panel Admin.") }
        }
        Spacer(Modifier.weight(1f))
        Button(
            onClick = { /* TODO logout */ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Cerrar sesión") }
    }
}
