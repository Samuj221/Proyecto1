package com.example.proyecto1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ChatMessage(val id: Long, val author: String, val text: String, val isMe: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ChatScreen(onBack: () -> Unit) {
    var input by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            ChatMessage(1, "Ana", "¿Qué pasó esta mañana?", false),
            ChatMessage(2, "Tú", "Hubo un choque", true),
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat vecinal") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        },
        bottomBar = {
            Row(Modifier.fillMaxWidth().padding(8.dp)) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Mensaje…") },
                    singleLine = true
                )
                Spacer(Modifier.width(8.dp))
                IconButton(onClick = {
                    if (input.isNotBlank()) {
                        messages.add(ChatMessage(System.currentTimeMillis(), "Tú", input, true))
                        input = ""
                    }
                }) { Icon(Icons.Filled.Send, contentDescription = "Enviar") }
            }
        }
    ) { padding ->
        LazyColumn(Modifier.padding(padding).fillMaxSize()) {
            items(messages, key = { it.id }) { msg ->
                Row(
                    Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 4.dp),
                    horizontalArrangement = if (msg.isMe) Arrangement.End else Arrangement.Start
                ) {
                    Surface(shape = RoundedCornerShape(12.dp), color =
                        if (msg.isMe) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(msg.text, modifier = Modifier.padding(10.dp),
                            color = if (msg.isMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        }
    }
}
