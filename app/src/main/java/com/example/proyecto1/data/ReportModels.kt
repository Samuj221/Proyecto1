package com.example.proyecto1.data

import com.google.firebase.Timestamp

data class Report(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val severity: Severity = Severity.LOW,
    val lat: Double? = null,
    val lng: Double? = null,
    val uid: String? = null,
    val ts: Timestamp? = null
)
