package com.example.proyecto1.data

import java.util.UUID

data class Report(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val severity: Severity = Severity.MEDIUM,
    val lat: Double,
    val lng: Double,
    val timestamp: Long = System.currentTimeMillis()
)
