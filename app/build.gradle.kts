plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") // <- requerido con Kotlin 2.x
}

android {
    namespace = "com.example.proyecto1"      // <-- usa tu paquete
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.proyecto1"  // <-- idem arriba
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures { compose = true }

    // ⚠️ Importante: elimina cualquier bloque composeOptions { kotlinCompilerExtensionVersion = "..." }
    // Con Kotlin 2.x el plugin maneja la compatibilidad del compilador de Compose.

    packaging { resources.excludes += "/META-INF/{AL2.0,LGPL2.1}" }
}

dependencies {
    // BOM de Compose: alinea versiones entre los artefactos de Compose
    val composeBom = platform("androidx.compose:compose-bom:2025.02.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")

    // UI Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")

    // (Opcional, si usas navegación/animaciones de Compose)
    implementation("androidx.navigation:navigation-compose:2.8.3")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.36.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.36.0")

    // Debug tooling
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
