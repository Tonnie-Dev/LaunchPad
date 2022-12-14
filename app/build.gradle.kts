import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp") version "1.7.21-1.0.8"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("com.diffplug.spotless")
}

android {
    namespace = "com.uxstate.launchpad"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.uxstate.launchpad"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    /* Kotlin Block - makes sure that the KSP Plugin looks at
     the right paths when it comes to generated classes*/
    kotlin {
        sourceSets {
            debug {
                kotlin.srcDir("build/generated/ksp/debug/kotlin")
            }
            release {
                kotlin.srcDir("build/generated/ksp/release/kotlin")
            }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

// ktlintFormat task will need to run before preBuild
tasks.getByPath("preBuild")
    .dependsOn("ktlintFormat")

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {

    android.set(true)
    ignoreFailures.set(false)
    disabledRules.set(setOf("final-newline", "no-wildcard-imports"))
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.CHECKSTYLE)
        reporter(ReporterType.SARIF)
    }
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
        // version, setUseExperimental, userData and editorConfigOverride are all optional
        ktlint("0.45.2")
            .setUseExperimental(true)
            .userData(mapOf("android" to "true"))
            .editorConfigOverride(mapOf("indent_size" to 2))
    }
    kotlinGradle {
        target("*.gradle.kts") // default target for kotlinGradle
        ktlint() // or ktfmt() or prettier()
    }
}
dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.ui:ui:1.3.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.3.1")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.3.1")

    // Coil
    implementation("io.coil-kt:coil-compose:2.2.2")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.7")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.7")

    // Moshi Library Dependencies - Core Moshi JSON Library and Moshi"s Kotlin support and converter factory
    implementation("com.squareup.moshi:moshi:1.12.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")

    // Room KTX with Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")

    // room-paging artifact
    implementation("androidx.room:room-paging:2.4.3")

    // Paging 3.0
    implementation("androidx.paging:paging-compose:1.0.0-alpha17")

    // Lottie Animation
    implementation("com.airbnb.android:lottie-compose:5.0.1")

    // Compose Nav Destinations
    implementation("io.github.raamcosta.compose-destinations:core:1.6.23-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.6.23-beta")

    // Pager - Accompanist
    implementation("com.google.accompanist:accompanist-pager:0.25.0") // Pager
    implementation("com.google.accompanist:accompanist-pager-indicators:0.25.0") // Pager Indicators

    // Swipe to Refresh - Accompanist
    implementation("com.google.accompanist:accompanist-swiperefresh:0.28.0")

    // System UI Controller - Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    // Timber Logging
    implementation("com.jakewharton.timber:timber:5.0.1")
}
