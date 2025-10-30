package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class ChatMsg(val id: String, val text: String, val ts: Long)

@Composable
fun ChatScreen() {
    val ctx = androidx.compose.ui.platform.LocalContext.current
    val useFirebase = remember { FirebaseApp.getApps(ctx).isNotEmpty() } // evita crash si no hay google-services
    val mensajes = remember { mutableStateListOf<ChatMsg>() }
    var input by remember { mutableStateOf(TextFieldValue("")) }
    var error by remember { mutableStateOf<String?>(null) }

    // Suscripción “segura”
    LaunchedEffect(useFirebase) {
        if (!useFirebase) return@LaunchedEffect
        val db = Firebase.firestore
        val ref = db.collection("zonapp-chat").orderBy("ts")
        ref.addSnapshotListener { snap, e ->
            if (e != null) { error = e.localizedMessage; return@addSnapshotListener }
            mensajes.clear()
            snap?.forEach { d ->
                mensajes.add(
                    ChatMsg(
                        id = d.id,
                        text = d.getString("text").orEmpty(),
                        ts = d.getLong("ts") ?: 0L
                    )
                )
            }
        }
    }

    Column(Modifier.fillMaxSize().padding(12.dp)) {
        Text("Chat vecinal (beta)", style = MaterialTheme.typography.titleLarge)
        if (!useFirebase) {
            Spacer(Modifier.height(8.dp))
            Text("El chat en vivo requiere configurar Firebase (google-services.json y Firestore). La app seguirá funcionando sin cerrar.")
        }
        error?.let { Text("Error: $it", color = MaterialTheme.colorScheme.error) }
        Spacer(Modifier.height(8.dp))

        LazyColumn(Modifier.weight(1f), reverseLayout = false) {
            items(mensajes, key = { it.id }) { m ->
                ElevatedCard(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Text(m.text, Modifier.padding(12.dp))
                }
            }
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = input, onValueChange = { input = it },
                modifier = Modifier.weight(1f), placeholder = { Text("Escribe un mensaje…") }
            )
            Button(
                onClick = {
                    val txt = input.text.trim()
                    if (txt.isNotEmpty() && useFirebase) {
                        Firebase.firestore.collection("zonapp-chat").add(
                            mapOf("text" to txt, "ts" to System.currentTimeMillis())
                        )
                        input = TextFieldValue("")
                    }
                }
            ) { Text("Enviar") }
        }
    }
}
