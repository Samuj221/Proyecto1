package com.example.proyecto1.data

import com.google.android.gms.maps.model.LatLng

data class Report(
    val title: String,
    val description: String,
    val severity: Severity,
    val location: LatLng
)
