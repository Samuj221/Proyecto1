package com.example.proyecto1.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.tasks.await

fun isPlayServicesOk(context: Context): Boolean =
    GoogleApiAvailability.getInstance()
        .isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS

fun hasLocationPermission(context: Context): Boolean =
    ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

suspend fun getCurrentLatLng(context: Context): LatLng? {
    val client = LocationServices.getFusedLocationProviderClient(context)
    return try {
        val last = client.lastLocation.await()
        if (last != null) LatLng(last.latitude, last.longitude)
        else {
            val cur = client.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, null).await()
            cur?.let { LatLng(it.latitude, it.longitude) }
        }
    } catch (_: SecurityException) {
        null
    } catch (_: Exception) {
        null
    }
}

@Composable
fun rememberMyLocation(): State<LatLng?> {
    val ctx = androidx.compose.ui.platform.LocalContext.current
    val state = remember { mutableStateOf<LatLng?>(null) }
    LaunchedEffect(Unit) {
        if (hasLocationPermission(ctx)) {
            state.value = getCurrentLatLng(ctx)
        }
    }
    return state
}
