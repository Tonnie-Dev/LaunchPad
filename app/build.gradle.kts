plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.parcelize.plugin)
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Coil
    implementation(COIL.compose)

    // Dagger Hilt
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
    implementation(libs.compose.destinations.core.one)
    ksp(libs.compose.destinations.ksp.one)

    // Timber Logging
    implementation(JakeWharton.timber)

    /*
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
    implementation("com.google.accompanist:accompanist-systemuicontroller:_")*/



}
