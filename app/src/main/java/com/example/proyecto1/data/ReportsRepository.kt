package com.example.proyecto1.data

import android.content.Context
import android.location.Location
import com.google.android.gms.maps.model.LatLng
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID


object ReportsRepository {
    private const val PREF = "reports_repo"
    private const val KEY = "reports_json"

    private val cache = mutableListOf<Report>()
    private var loaded = false

    private fun ensureLoaded(ctx: Context) {
        if (loaded) return
        val sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val raw = sp.getString(KEY, "[]") ?: "[]"
        val arr = JSONArray(raw)
        cache.clear()
        for (i in 0 until arr.length()) {
            val o = arr.getJSONObject(i)
            cache += Report(
                id = o.getString("id"),
                title = o.getString("title"),
                description = o.getString("description"),
                severity = Severity.valueOf(o.getString("severity")),
                latLng = LatLng(o.getDouble("lat"), o.getDouble("lng")),
                timestamp = o.getLong("ts")
            )
        }
        loaded = true
    }

    private fun persist(ctx: Context) {
        val arr = JSONArray()
        cache.forEach { r ->
            val o = JSONObject()
            o.put("id", r.id)
            o.put("title", r.title)
            o.put("description", r.description)
            o.put("severity", r.severity.name)
            o.put("lat", r.latLng.latitude)
            o.put("lng", r.latLng.longitude)
            o.put("ts", r.timestamp)
            arr.put(o)
        }
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit().putString(KEY, arr.toString()).apply()
    }

    fun all(ctx: Context): List<Report> {
        ensureLoaded(ctx)
        return cache.sortedByDescending { it.timestamp }
    }

    fun near(ctx: Context, center: LatLng, radiusMeters: Float = 3000f): List<Report> {
        ensureLoaded(ctx)
        return cache.filter { distanceMeters(center, it.latLng) <= radiusMeters }
            .sortedByDescending { it.timestamp }
    }

    fun add(ctx: Context, title: String, description: String, severity: Severity, latLng: LatLng) {
        ensureLoaded(ctx)
        cache += Report(
            id = UUID.randomUUID().toString(),
            title = title,
            description = description,
            severity = severity,
            latLng = latLng,
            timestamp = System.currentTimeMillis()
        )
        persist(ctx)
    }

    private fun distanceMeters(a: LatLng, b: LatLng): Float {
        val out = FloatArray(1)
        Location.distanceBetween(a.latitude, a.longitude, b.latitude, b.longitude, out)
        return out[0]
    }
}
