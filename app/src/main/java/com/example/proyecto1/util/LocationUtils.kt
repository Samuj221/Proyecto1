package com.example.proyecto1.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.android.gms.maps.model.LatLng

/**
 * Stub sin permisos para evitar ambigüedad.
 * Si ya tienes otra implementación de ubicación con permisos/intent,
 * mantenla en otro nombre. Esta función solo devuelve null para compilar.
 */
@Composable
fun rememberMockLocation(): LatLng? = remember { null }
