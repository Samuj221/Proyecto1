package com.example.proyecto1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val ZonappTypography = Typography(
    displayMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 44.sp),
    titleLarge    = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium,   fontSize = 22.sp),
    bodyLarge     = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Normal,   fontSize = 16.sp),
    labelLarge    = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium,   fontSize = 14.sp)
)
