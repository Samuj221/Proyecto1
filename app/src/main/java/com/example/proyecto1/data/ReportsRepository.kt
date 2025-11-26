package com.example.proyecto1.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object ReportsRepository {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private const val COLLECTION = "reports"

    fun currentUid(): String? = auth.currentUser?.uid

    suspend fun createReport(
        title: String,
        description: String,
        severity: Severity,
        lat: Double?,
        lng: Double?
    ) {
        val uid = currentUid()

        val data = hashMapOf(
            "title" to title,
            "description" to description,
            "severity" to severity.name,
            "lat" to lat,
            "lng" to lng,
            "uid" to uid,
            "ts" to FieldValue.serverTimestamp()
        )

        db.collection(COLLECTION).add(data)
    }

    fun reportsFlow(): Flow<List<Report>> = callbackFlow {
        val reg = db.collection(COLLECTION)
            .orderBy("ts")
            .addSnapshotListener { snap, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val list = snap?.documents?.map { doc ->
                    Report(
                        id = doc.id,
                        title = doc.getString("title") ?: "",
                        description = doc.getString("description") ?: "",
                        severity = Severity.valueOf(
                            doc.getString("severity") ?: Severity.LOW.name
                        ),
                        lat = doc.getDouble("lat"),
                        lng = doc.getDouble("lng"),
                        uid = doc.getString("uid"),
                        ts = doc.getTimestamp("ts")
                    )
                } ?: emptyList()

                trySend(list)
            }

        awaitClose { reg.remove() }
    }
}
