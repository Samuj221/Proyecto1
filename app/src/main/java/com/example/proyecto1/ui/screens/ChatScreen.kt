package com.example.proyecto1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachFile
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.proyecto1.BuildConfig
import com.example.proyecto1.data.ChatRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import kotlinx.coroutines.flow.collectAsState


@Composable
fun ChatScreen() {
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("") }
    val me = FirebaseAuth.getInstance().currentUser?.uid

    // ✅ Estado en vivo, seguro al ciclo de vida
    val messages by ChatRepository
        .messagesFlow()
        .collectAsStateWithLifecycle(initialValue = emptyList())

    LaunchedEffect(Unit) {
        if (BuildConfig.FIREBASE_ENABLED) ChatRepository.ensureSignedIn()
    }

    Scaffold(
        bottomBar = {
            ChatInputBar(
                text = text,
                onText = { text = it },
                onSend = {
                    val msg = text.trim()
                    if (msg.isNotEmpty()) scope.launch {
                        ChatRepository.send(msg)
                        text = ""
                    }
                },
                onPick = { /* TODO: galería */ },
                onCamera = { /* TODO: cámara */ },
                onMic = { /* TODO: audio */ }
            )
        }
    ) { inner ->
        if (messages.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(inner), contentAlignment = Alignment.Center) {
                Text("Sé el primero en escribir…", textAlign = TextAlign.Center)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(inner),
                reverseLayout = true,
                verticalArrangement = Arrangement.spacedBy(6.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(messages, key = { it.id }) { m ->
                    val mine = m.uid == me
                    val time = m.ts?.toDate()?.let {
                        SimpleDateFormat("HH:mm", Locale.getDefault()).format(it)
                    } ?: "--:--"
                    ChatBubble(text = m.text, mine = mine, time = time)
                }
            }
        }
    }
}

@Composable
private fun ChatBubble(text: String, mine: Boolean, time: String) {
    val bg = if (mine) MaterialTheme.colorScheme.primaryContainer
    else MaterialTheme.colorScheme.surfaceVariant
    val align = if (mine) Arrangement.End else Arrangement.Start
    Row(Modifier.fillMaxWidth(), horizontalArrangement = align) {
        Column(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .background(bg, RoundedCornerShape(16.dp))
                .padding(start = 12.dp, top = 8.dp, end = 12.dp, bottom = 4.dp)
        ) {
            Text(text = text, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(2.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(time, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
private fun ChatInputBar(
    text: String,
    onText: (String) -> Unit,
    onSend: () -> Unit,
    onPick: () -> Unit,
    onCamera: () -> Unit,
    onMic: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPick, modifier = Modifier.size(36.dp).clip(CircleShape)) {
            Icon(Icons.Rounded.AttachFile, contentDescription = "Adjuntar")
        }
        IconButton(onClick = onCamera, modifier = Modifier.size(36.dp).clip(CircleShape)) {
            Icon(Icons.Rounded.CameraAlt, contentDescription = "Cámara")
        }
        IconButton(onClick = onMic, modifier = Modifier.size(36.dp).clip(CircleShape)) {
            Icon(Icons.Rounded.Mic, contentDescription = "Micrófono")
        }
        OutlinedTextField(
            value = text,
            onValueChange = onText,
            placeholder = { Text("Escribe un mensaje") },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 6.dp),
            singleLine = true
        )
        FilledIconButton(onClick = onSend, modifier = Modifier.size(40.dp)) {
            Icon(Icons.Rounded.Send, contentDescription = "Enviar")
        }
    }
}
