plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Hilt dependency
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)

    // firebase
    id("com.google.gms.google-services")
}

android {
    namespace = "com.sanvi.ecommercetarget"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.sanvi.ecommercetarget"
        minSdk = 27
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

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
    }

    buildFeatures {
        compose = true
    }

    // Configure Kotlin options
    kotlin {
        jvmToolchain(17)
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // for img upload
    implementation("io.coil-kt.coil3:coil-compose:3.4.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.4.0")

    // navigation
    val nav_version = "2.9.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:34.13.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")

    // Hilt For DI with KSP
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler.ksp)
    implementation(libs.androidx.hilt.navigation.compose)

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // For logging (optional but recommended)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coroutine
    implementation(libs.kotlinx.coroutines.android)


    // Shimmer loading effect (modern loader)
    implementation("com.valentinilk.shimmer:compose-shimmer:1.3.0")

    // Or use Accompanist Placeholder (alternative)
    implementation("com.google.accompanist:accompanist-placeholder-material3:0.35.0-alpha")

    // DataStore Preferences (modern replacement for SharedPreferences)
    implementation("androidx.datastore:datastore-preferences:1.1.0")

    // Material Icons Extended (for visibility icons)
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    // Room
//    val room_version = "2.9.7"
    val room_version = "2.8.4"

    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

}

ksp {
    arg("dagger.hilt.disableModulesHaveInstallInCheck", "true")
}