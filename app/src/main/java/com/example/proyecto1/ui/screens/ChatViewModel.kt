package com.example.proyecto1.ui.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto1.data.ChatRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class ChatUiMessage(
    val id: String,
    val text: String,
    val fromMe: Boolean,
    val timestamp: Long
)

class ChatViewModel : ViewModel() {

    private val _messages = mutableStateListOf<ChatUiMessage>()
    val messages: List<ChatUiMessage> get() = _messages

    init {
        viewModelScope.launch {
            ChatRepository.ensureSignedIn()
            ChatRepository.messagesFlow().collectLatest { list ->
                val myUid = ChatRepository.currentUid
                val mapped = list.map { msg ->
                    ChatUiMessage(
                        id = msg.id,
                        text = msg.text,
                        fromMe = myUid != null && myUid == msg.uid,
                        timestamp = msg.ts?.toDate()?.time ?: 0L
                    )
                }.sortedBy { it.timestamp }
                _messages.clear()
                _messages.addAll(mapped)
            }
        }
    }

    fun send(text: String) {
        val clean = text.trim()
        if (clean.isEmpty()) return
        viewModelScope.launch {
            ChatRepository.send(clean)
        }
    }
}
