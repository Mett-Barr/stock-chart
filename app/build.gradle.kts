plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")

    id("com.google.dagger.hilt.android") //version "2.47" apply false

    kotlin("plugin.serialization") version "1.9.0"
}

android {
    namespace = "com.example.stockchart"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.stockchart"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        /** Dependency 'androidx.emoji2:emoji2:1.4.0' requires libraries and applications that
        depend on it to compile against version 34 or later of the
        Android APIs. */
        compileSdkPreview = "UpsideDownCake"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")

    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")



    // ConstrantLayout
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.0-alpha04")
    implementation("androidx.datastore:datastore-core:1.1.0-alpha04")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.47")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    // Navigation
    val navVersion = "2.7.1"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    // Moshi
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.0")

    // Gson
    implementation("com.google.code.gson:gson:2.9.0")

    // Retrofit
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion") // For Moshi converter
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion") // For Gson converter


    // Kotlinx-serialization
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Ktor
    val ktorVersion = "2.3.3"
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    // MPChart
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
}

kapt {
    correctErrorTypes = true
}