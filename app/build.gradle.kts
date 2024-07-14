



plugins {
    //id("com.android.application")
    //id("org.jetbrains.kotlin.android")
  //  id("kotlin-kapt")

   /* id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")



    id("org.jlleitschuh.gradle.ktlint")
    id("com.diffplug.spotless")*/
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.uxstate.launchpad"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.uxstate.launchpad"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }


   buildTypes {

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"

    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {

            resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            // exclude("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}



dependencies {

    implementation(AndroidX.core.ktx)
    implementation(AndroidX.lifecycle.runtime.ktx)
    implementation(AndroidX.activity.compose)
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.compose.material)
    implementation(AndroidX.navigation.compose)

    testImplementation(Testing.junit4)
    androidTestImplementation(AndroidX.test.ext.junit)
    androidTestImplementation(AndroidX.test.espresso.core)
    androidTestImplementation(AndroidX.compose.ui.testJunit4)
    debugImplementation(AndroidX.compose.ui.tooling)
    debugImplementation(AndroidX.compose.ui.testManifest)

    // Coil
    implementation(COIL.compose)

    // Dagger - Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Retrofit
    implementation(Square.retrofit2)
    implementation(Square.retrofit2.converter.moshi)
    implementation(Square.okHttp3)
    implementation(Square.okHttp3.loggingInterceptor)

    // Moshi Library Dependencies - Core Moshi JSON Library and Moshi"s Kotlin support and converter factory
    implementation(Square.moshi)
    implementation(Square.moshi.kotlinReflect)

    // Room components
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)

    // Paging 3.0
    implementation(AndroidX.paging.compose)

    // Lottie Animation
    implementation(libs.lottie.compose)

    // Compose Nav Destinations
    implementation(libs.io.github.raamcosta.compose.destinations.core)
    ksp(libs.ksp)

    // Pager - Accompanist
    // Deprecated because Pager is now directly into androidx.compose.foundation.
    //FIXME: Migrate using the following guide:
    // https://google.github.io/accompanist/pager/
    implementation("com.google.accompanist:accompanist-pager:_") // Pager
    // Deprecated because Pager is now directly into androidx.compose.foundation.
    //FIXME: Migrate using the following guide:
    // https://google.github.io/accompanist/pager/
    implementation("com.google.accompanist:accompanist-pager-indicators:_") // Pager Indicators

    // Swipe to Refresh - Accompanist
    // Deprecated because Swipe Refresh is now right into androidx.compose.material.
    // Note that it's not in Material3 at the moment.
    //FIXME: Migrate using the following guide:
    // https://google.github.io/accompanist/swiperefresh/
    implementation("com.google.accompanist:accompanist-swiperefresh:_")

    // System UI Controller - Accompanist
    // Deprecated in favor of Activity.enableEdgeToEdge from androidx.activity 1.8+
    //FIXME: See the example PR in the migration guide here:
    // https://google.github.io/accompanist/systemuicontroller/
    implementation("com.google.accompanist:accompanist-systemuicontroller:_")

    // Timber Logging
    implementation(JakeWharton.timber)
}
