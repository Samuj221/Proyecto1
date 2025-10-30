package com.example.proyecto1.util

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng

class LocationState {
    var lastKnown by mutableStateOf<LatLng?>(null)
        internal set
    var hasPermission by mutableStateOf(false)
        internal set
}

@Composable
fun rememberLocationState(): LocationState {
    val context = LocalContext.current
    val state = remember { LocationState() }

    val perms = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { res -> state.hasPermission = res.values.any { it } }

    LaunchedEffect(Unit) {
        state.hasPermission = perms.any {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
        if (!state.hasPermission) launcher.launch(perms)
        else fetchLastLocation(context) { state.lastKnown = it }
    }

    return state
}

@SuppressLint("MissingPermission")
private fun fetchLastLocation(
    context: android.content.Context,
    onLatLng: (LatLng) -> Unit
) {
    val fused = LocationServices.getFusedLocationProviderClient(context)
    fused.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, null)
        .addOnSuccessListener { it?.let { onLatLng(LatLng(it.latitude, it.longitude)) } }
}
