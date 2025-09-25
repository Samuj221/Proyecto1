package com.example.proyecto1.data

import com.google.android.gms.maps.model.LatLng

enum class Severity { HIGH, MEDIUM, LOW }

data class Report(
    val id: String,
    val title: String,
    val description: String,
    val severity: Severity,
    val latLng: LatLng,
    val timestamp: Long
)
