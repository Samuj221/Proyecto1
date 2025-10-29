package com.example.proyecto1.data

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.google.android.gms.maps.model.LatLng

object ReportsRepository {
    private val _items = mutableStateListOf<Report>()
    val items: List<Report> get() = _items

    fun add(context: Context, title: String, description: String, severity: Severity, latLng: LatLng) {
        _items += Report(title, description, severity, latLng)
        // TODO: persistencia (Room/Prefs) si se necesita más adelante
    }
}
