package com.example.proyecto1.data

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

data class AppUserProfile(
    val name: String,
    val email: String
)

object FirebaseAuthManager {

    private val auth = Firebase.auth
    private val db = Firebase.firestore

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    suspend fun register(name: String, email: String, password: String): Result<FirebaseUser?> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user != null) {
                val profileUpdate = userProfileChangeRequest {
                    displayName = name
                }
                user.updateProfile(profileUpdate).await()
                db.collection("users").document(user.uid).set(
                    mapOf(
                        "name" to name,
                        "email" to (user.email ?: email)
                    )
                ).await()
            }
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            ensureUserDocument()
            Result.success(result.user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun ensureUserDocument() {
        val user = auth.currentUser ?: return
        val docRef = db.collection("users").document(user.uid)
        val snap = docRef.get().await()
        if (!snap.exists()) {
            docRef.set(
                mapOf(
                    "name" to (user.displayName ?: ""),
                    "email" to (user.email ?: "")
                )
            ).await()
        }
    }

    suspend fun loadProfile(): AppUserProfile? {
        val user = auth.currentUser ?: return null
        val doc = db.collection("users").document(user.uid).get().await()
        return if (doc.exists()) {
            AppUserProfile(
                name = doc.getString("name") ?: (user.displayName ?: ""),
                email = doc.getString("email") ?: (user.email ?: "")
            )
        } else {
            AppUserProfile(
                name = user.displayName ?: "",
                email = user.email ?: ""
            )
        }
    }

    suspend fun updateProfile(name: String, email: String): Result<Unit> {
        val user = auth.currentUser ?: return Result.failure(IllegalStateException("No user"))
        return try {
            val profileUpdate = userProfileChangeRequest {
                displayName = name
            }
            user.updateProfile(profileUpdate).await()
            if (email.isNotBlank() && email != user.email) {
                user.updateEmail(email).await()
            }
            db.collection("users").document(user.uid).set(
                mapOf(
                    "name" to name,
                    "email" to (user.email ?: email)
                )
            ).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun signOut() {
        auth.signOut()
    }
}
