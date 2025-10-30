package com.example.proyecto1.ui.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val fromMe: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

/**
 * Chat local en memoria (sin backend).
 * Persiste mientras la actividad no se destruya (ViewModel).
 */
class ChatViewModel : ViewModel() {

    private val _messages = mutableStateListOf<ChatMessage>()
    val messages: List<ChatMessage> get() = _messages

    init {
        // Mensajes de ejemplo para arrancar la demo
        _messages.add(ChatMessage(text = "Bienvenido al chat vecinal (demo local).", fromMe = false))
        _messages.add(ChatMessage(text = "Escribe algo y pulsa Enviar.", fromMe = false))
    }

    fun send(text: String) {
        val clean = text.trim()
        if (clean.isEmpty()) return

        // Agrega mi mensaje
        _messages.add(ChatMessage(text = clean, fromMe = true))

        // Simula una respuesta del “vecino bot”
        viewModelScope.launch {
            delay(900)
            _messages.add(
                ChatMessage(
                    text = "Vecino bot: recibido “$clean” 👍",
                    fromMe = false
                )
            )
        }
    }
}