package com.example.proyecto1.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.proyecto1.data.FirebaseAuthManager
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen() {
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var loadingInitial by remember { mutableStateOf(true) }
    var saving by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val profile = FirebaseAuthManager.loadProfile()
        name = profile?.name ?: ""
        email = profile?.email ?: ""
        loadingInitial = false
    }

    if (loadingInitial) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            CircularProgressIndicator()
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Text(
            text = "Ajustes de usuario",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                val trimmedName = name.trim()
                val trimmedEmail = email.trim()
                if (trimmedName.isEmpty() || trimmedEmail.isEmpty()) {
                    Toast.makeText(ctx, "Completa nombre y correo", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                scope.launch {
                    saving = true
                    val result = FirebaseAuthManager.updateProfile(trimmedName, trimmedEmail)
                    saving = false
                    if (result.isSuccess) {
                        Toast.makeText(ctx, "Datos actualizados", Toast.LENGTH_SHORT).show()
                    } else {
                        val message = result.exceptionOrNull()?.localizedMessage ?: "Error al guardar"
                        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show()
                    }
                }
            },
            enabled = !saving,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            if (saving) {
                CircularProgressIndicator(
                    modifier = Modifier.height(24.dp)
                )
            } else {
                Text("Guardar cambios")
            }
        }
    }
}
