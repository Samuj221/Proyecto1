plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") // Compose + Kotlin 2.x
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

    // ❌ si tienes composeOptions { kotlinCompilerExtensionVersion = "..." } elimínalo en Kotlin 2.x

    // Alinea Java/Javac a 17
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        // Si usas APIs Java 8+ en minSdk bajos, puedes activar desugaring:
        // isCoreLibraryDesugaringEnabled = true
    }

    // Opcional, pero claro: di a Kotlin que apunte a 17
    kotlinOptions {
        jvmTarget = "17"
    }

    // (Opcional) flags del compilador de Compose
    composeCompiler {
        enableStrongSkippingMode.set(true)
    }

    packaging {
        resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" }
    }
}

// Usa el JDK 17 para Kotlin (evita que tome 21 si lo tienes instalado)
kotlin {
    jvmToolchain(17)
}

dependencies {
    // BOM de Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.09.02")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // UI Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Material 3
    implementation("androidx.compose.material3:material3")

    // Navegación
    implementation("androidx.navigation:navigation-compose:2.8.0")

    // Activity + lifecycle compose
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.4")

    // Icons extendidas
    implementation("androidx.compose.material:material-icons-extended:1.7.2")

    // Coil
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Google Maps Compose
    implementation("com.google.maps.android:maps-compose:4.4.1")
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // Si activaste desugaring arriba, añade:
    // coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
}
