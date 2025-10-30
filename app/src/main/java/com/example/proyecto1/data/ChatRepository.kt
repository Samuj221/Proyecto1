package com.example.proyecto1.data

import com.example.proyecto1.BuildConfig
import androidx.annotation.Keep
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await

@Keep
data class ChatMessage(
    val id: String = "",
    val uid: String = "",
    val text: String = "",
    val ts: Timestamp? = null,
    val name: String? = null
)

object ChatRepository {
    // ===== Fallback in-memory cuando no hay Firebase =====
    private val localBus = MutableStateFlow<List<ChatMessage>>(emptyList())

    // ===== Firebase =====
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { Firebase.firestore }

    suspend fun ensureSignedIn() {
        if (!BuildConfig.FIREBASE_ENABLED) return
        if (auth.currentUser == null) auth.signInAnonymously().await()
    }

    fun messagesFlow(): Flow<List<ChatMessage>> {
        if (!BuildConfig.FIREBASE_ENABLED) return localBus
        val q = db.collection("community_messages")
            .orderBy("ts", Query.Direction.DESCENDING)
            .limit(200)
        return callbackFlow<List<ChatMessage>> {
            val reg = q.addSnapshotListener { snap, err ->
                if (err != null) return@addSnapshotListener
                val list = snap?.documents?.mapNotNull { d ->
                    d.toObject(ChatMessage::class.java)?.copy(id = d.id)
                }.orEmpty()
                trySend(list)
            }
            awaitClose { reg.remove() }
        }
    }

    suspend fun send(text: String) {
        if (!BuildConfig.FIREBASE_ENABLED) {
            val msg = ChatMessage(
                id = System.currentTimeMillis().toString(),
                uid = "local",
                text = text,
                ts = null
            )
            localBus.value = listOf(msg) + localBus.value
            return
        }
        ensureSignedIn()
        val uid = auth.currentUser!!.uid
        db.collection("community_messages").add(
            mapOf(
                "uid" to uid,
                "text" to text,
                "ts" to com.google.firebase.firestore.FieldValue.serverTimestamp()
            )
        ).await()
    }
}
