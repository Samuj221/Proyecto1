package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleVerificationScreen(onBack: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Verificación de rol") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text("Flujo de validación (UI)")
            Spacer(Modifier.height(16.dp))
            Button(onClick = { showDialog = true }) { Text("Validar") }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Validación del suceso") },
            text = { Text("Elige una opción") },
            confirmButton = {
                TextButton(onClick = { showDialog = false /* subir foto */ }) { Text("Subir una foto") }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false /* tomar foto */ }) { Text("Tomar una foto") }
            }
        )
    }
}
