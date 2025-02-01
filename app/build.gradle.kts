plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt") // For Room annotation processing
}

android {
  namespace = "com.swe.businesscard"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.swe.businesscard"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }

  buildFeatures {
    compose = true // Enable Compose
  }

  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.3" // Compose compiler version
  }
}

dependencies {
  // ==================== Core Dependencies ====================
  implementation("androidx.core:core-ktx:1.13.1") // Core Kotlin extensions
  implementation("androidx.appcompat:appcompat:1.7.0") // AppCompat for backward compatibility
  implementation("com.google.android.material:material:1.12.0") // Material Design components
  implementation("androidx.constraintlayout:constraintlayout:2.2.0") // ConstraintLayout
  implementation("androidx.activity:activity-ktx:1.8.0") // Activity KTX for better Activity APIs


  // ==================== Navigation ====================
  implementation("androidx.navigation:navigation-compose:2.6.0")

  // ==================== Room Database Dependencies ====================
  implementation("androidx.room:room-runtime:2.6.1") // Room runtime
  implementation("androidx.room:room-ktx:2.6.1") // Room Kotlin extensions (coroutine support)
  kapt("androidx.room:room-compiler:2.6.1") // Room annotation processor (KAPT)

  // ==================== Compose Dependencies ====================
  implementation("androidx.compose.ui:ui:1.7.7") // Core Compose UI
  implementation("androidx.compose.material3:material3:1.3.1") // Material 3 for Compose
  implementation("androidx.compose.ui:ui-tooling-preview:1.7.7") // Preview support
  implementation("androidx.activity:activity-compose:1.8.0") // Compose integration with Activity
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7") // Compose integration with ViewModel
  implementation("androidx.compose.runtime:runtime-livedata:1.5.0")

  // ==================== Lifecycle Dependencies ====================
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7") // Lifecycle runtime
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7") // ViewModel KTX

  // ==================== Kotlin Standard Library ====================
  implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22") // Match the Kotlin version

  // ==================== Testing Dependencies ====================
  testImplementation("junit:junit:4.13.2") // Unit testing
  androidTestImplementation("androidx.test.ext:junit:1.2.1") // Android JUnit testing
  androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1") // Espresso testing
  androidTestImplementation("androidx.room:room-testing:2.6.1") // Room database testing
}