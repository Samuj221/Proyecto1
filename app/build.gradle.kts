plugins {
    id("com.android.application")
    kotlin("android")
}


android {
    namespace = "com.samupro.proyecto1"
    compileSdk = 35


    defaultConfig {
        applicationId = "com.samupro.proyecto1"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
    }


    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.15" }


    packaging { resources.excludes += "/META-INF/{AL2.0,LGPL2.1}" }
}


dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2025.02.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)


    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")


// Material 3
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")


// Navigation + (opcional) animaciones
    implementation("androidx.navigation:navigation-compose:2.8.3")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.36.0")


// Imágenes
    implementation("io.coil-kt:coil-compose:2.7.0")


// Splash Screen Android 12+
    implementation("androidx.core:core-splashscreen:1.1.0-rc01")


// System UI Controller (status/nav bar)
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.36.0")


    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-util")
}