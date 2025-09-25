plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") // requerido en Kotlin 2.x
}

android {
    namespace = "com.example.proyecto1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.proyecto1"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables { useSupportLibrary = true }
    }

    buildFeatures { compose = true }

    // NO usar composeOptions con Kotlin 2.x (se maneja por el plugin):
    // composeOptions { kotlinCompilerExtensionVersion = "..." }

    // Alinear Java/Javac a 17
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        // isCoreLibraryDesugaringEnabled = true // (opcional)
    }

    // Alinear Kotlin a 17
    kotlinOptions {
        jvmTarget = "17"
    }

    // Opcional: flags del compilador de Compose
    composeCompiler {
        enableStrongSkippingMode.set(true)
    }

    packaging {
        resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" }
    }
}

// Fuerza toolchain de Kotlin a JDK 17
kotlin {
    jvmToolchain(17)
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.09.02")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Material 3
    implementation("androidx.compose.material3:material3")

    // Navegación
    implementation("androidx.navigation:navigation-compose:2.8.0")

    // Activity + Lifecycle
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.4")

    // Icons (hamburguesa, etc.)
    implementation("androidx.compose.material:material-icons-extended:1.7.2")

    // Coil (si usas imágenes remotas en otras pantallas)
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Google Maps Compose
    implementation("com.google.maps.android:maps-compose:4.4.1")
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // Desugaring si activaste arriba:
    // coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
}
