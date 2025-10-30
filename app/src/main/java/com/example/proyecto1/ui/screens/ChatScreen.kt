package com.example.proyecto1.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ChatScreen(
    vm: ChatViewModel = viewModel()
) {
    val ctx = LocalContext.current
    val listState = rememberLazyListState()
    var input by rememberSaveable { mutableStateOf("") }

    // Auto-scroll al último mensaje
    LaunchedEffect(vm.messages.size) {
        if (vm.messages.isNotEmpty()) {
            listState.animateScrollToItem(vm.messages.lastIndex)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)
    ) {
        Text(
            "Chat vecinal (demo local)",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            state = listState
        ) {
            items(vm.messages, key = { it.id }) { msg ->
                MessageBubble(
                    text = msg.text,
                    time = msg.timestamp,
                    fromMe = msg.fromMe
                )
                Spacer(Modifier.height(6.dp))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FilledTonalIconButton(
                onClick = { Toast.makeText(ctx, "Abrir cámara (demo)", Toast.LENGTH_SHORT).show() }
            ) { Icon(Icons.Rounded.CameraAlt, contentDescription = "Cámara") }

            FilledTonalIconButton(
                onClick = { Toast.makeText(ctx, "Abrir galería (demo)", Toast.LENGTH_SHORT).show() }
            ) { Icon(Icons.Rounded.Image, contentDescription = "Galería") }

            FilledTonalIconButton(
                onClick = { Toast.makeText(ctx, "Grabar audio (demo)", Toast.LENGTH_SHORT).show() }
            ) { Icon(Icons.Rounded.Mic, contentDescription = "Micrófono") }
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 56.dp),
                placeholder = { Text("Escribe un mensaje…") },
                maxLines = 4
            )
            Spacer(Modifier.width(10.dp))
            FilledIconButton(
                onClick = {
                    vm.send(input)
                    input = ""
                },
                modifier = Modifier.size(48.dp)
            ) { Icon(Icons.Rounded.Send, contentDescription = "Enviar") }
        }
    }
}

@Composable
private fun MessageBubble(text: String, time: Long, fromMe: Boolean) {
    val bubbleColor =
        if (fromMe) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.surfaceVariant
    val onBubble =
        if (fromMe) MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.onSurfaceVariant

    val shape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomEnd = if (fromMe) 0.dp else 16.dp,
        bottomStart = if (fromMe) 16.dp else 0.dp
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (fromMe) Alignment.End else Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .clip(shape)
                .background(bubbleColor)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Column(horizontalAlignment = if (fromMe) Alignment.End else Alignment.Start) {
                Text(text, color = onBubble)
                Spacer(Modifier.height(4.dp))
                Text(
                    text = time.formatHour(),
                    style = MaterialTheme.typography.labelSmall,
                    color = onBubble.copy(alpha = 0.8f)
                )
            }
        }
    }
}

private fun Long.formatHour(): String {
    return try {
        val fmt = SimpleDateFormat("HH:mm", Locale.getDefault())
        fmt.format(Date(this))
    } catch (_: Throwable) {
        ""
    }
}
