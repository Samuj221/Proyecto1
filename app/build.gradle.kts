plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    // No apliques aquí el plugin de Google Services; lo aplicamos condicionalmente al final
}

android {
    namespace = "com.example.proyecto1"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.proyecto1"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true

        // Flag para habilitar/deshabilitar Firebase según exista google-services.json
        val hasGoogleJson = project.file("google-services.json").exists()
        buildConfigField("boolean", "FIREBASE_ENABLED", hasGoogleJson.toString())
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }
    kotlin { jvmToolchain(17) }

    buildFeatures {
        compose = true
        buildConfig = true       // ✅ necesario para usar buildConfigField
    }

    packaging { resources.excludes += "/META-INF/{AL2.0,LGPL2.1}" }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2025.02.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")

    // Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.3")

    // Material XML + Splash
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Maps + Localización
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.google.maps.android:maps-compose:4.3.3")

    // Firebase (chat en vivo)
    implementation(platform("com.google.firebase:firebase-bom:33.4.0"))
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.1") // Task.await()
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.6")

    // Media (micrófono / reproducción)
    implementation("androidx.media3:media3-exoplayer:1.4.1")
    implementation("androidx.compose.runtime:runtime-saveable")


    // Desugaring
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
}

// ✅ Aplica Google Services SOLO si existe app/google-services.json
if (project.file("google-services.json").exists()) {
    apply(plugin = "com.google.gms.google-services")
    println("✅ google-services.json encontrado: se aplica com.google.gms.google-services")
} else {
    println("⚠️ google-services.json NO encontrado: se omite el plugin. El chat funcionará en modo local/offline.")
}
