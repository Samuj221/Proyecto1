package com.example.proyecto1.data

import android.content.Context
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONArray
import org.json.JSONObject

object ReportsRepository {
    private const val PREFS = "reports_repo"
    private const val KEY = "reports_json"

    private val _items = MutableStateFlow<List<Report>>(emptyList())
    val items: StateFlow<List<Report>> = _items

    fun load(context: Context) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val raw = prefs.getString(KEY, null) ?: return
        runCatching {
            val arr = JSONArray(raw)
            val list = mutableListOf<Report>()
            for (i in 0 until arr.length()) {
                val o = arr.getJSONObject(i)
                list.add(
                    Report(
                        id = o.getString("id"),
                        title = o.getString("title"),
                        description = o.optString("description"),
                        severity = Severity.valueOf(o.getString("severity")),
                        lat = o.getDouble("lat"),
                        lng = o.getDouble("lng"),
                        timestamp = o.getLong("timestamp")
                    )
                )
            }
            _items.value = list.sortedByDescending { it.timestamp }
        }.onFailure { Log.e("ReportsRepository", "load", it) }
    }

    fun add(context: Context, title: String, description: String, severity: Severity, where: LatLng) {
        val newItem = Report(
            title = title,
            description = description,
            severity = severity,
            lat = where.latitude,
            lng = where.longitude
        )
        val list = _items.value.toMutableList()
        list.add(0, newItem)
        _items.value = list
        persist(context)
    }

    private fun persist(context: Context) {
        val arr = JSONArray()
        _items.value.forEach { r ->
            val o = JSONObject()
            o.put("id", r.id)
            o.put("title", r.title)
            o.put("description", r.description)
            o.put("severity", r.severity.name)
            o.put("lat", r.lat)
            o.put("lng", r.lng)
            o.put("timestamp", r.timestamp)
            arr.put(o)
        }
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit().putString(KEY, arr.toString()).apply()
    }
}
