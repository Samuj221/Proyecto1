package com.example.proyecto1.data

import java.util.UUID

enum class Severity { LOW, MEDIUM, HIGH }

data class Report(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val severity: Severity,
    val lat: Double,
    val lng: Double,
    val timestamp: Long = System.currentTimeMillis()
)
