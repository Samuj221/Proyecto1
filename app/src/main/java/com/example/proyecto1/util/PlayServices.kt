package com.example.proyecto1.util

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

fun isPlayServicesOk(context: Context): Boolean {
    val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
    return status == ConnectionResult.SUCCESS
}
