import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp") version "1.7.21-1.0.8"
    id("org.jlleitschuh.gradle.ktlint")
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

    implementation(AndroidX.core.ktx)
    implementation(AndroidX.lifecycle.runtime.ktx)
    implementation(AndroidX.activity.compose)
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.compose.material)

    testImplementation(Testing.junit4)
    androidTestImplementation(AndroidX.test.ext.junit)
    androidTestImplementation(AndroidX.test.espresso.core)
    androidTestImplementation(AndroidX.compose.ui.testJunit4)
    debugImplementation(AndroidX.compose.ui.tooling)
    debugImplementation(AndroidX.compose.ui.testManifest)

    // Coil
    implementation(COIL.compose)

    // Dagger - Hilt
    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.android.compiler)
    kapt(AndroidX.hilt.compiler)
    implementation(AndroidX.hilt.navigationCompose)

    // Retrofit
    implementation(Square.retrofit2)
    implementation(Square.retrofit2.converter.moshi)
    implementation(Square.okHttp3)
    implementation(Square.okHttp3.loggingInterceptor)

    // Moshi Library Dependencies - Core Moshi JSON Library and Moshi"s Kotlin support and converter factory
    implementation(Square.moshi)
    implementation(Square.moshi.kotlinReflect)

    // Room KTX with Kotlin Extensions and Coroutines support for Room
    implementation(AndroidX.room.ktx)
    kapt(AndroidX.room.compiler)

    // room-paging artifact
    implementation(AndroidX.room.paging)

    // Paging 3.0
    implementation(AndroidX.paging.compose)

    // Lottie Animation
    implementation(libs.lottie.compose)

    // Compose Nav Destinations
    implementation(libs.io.github.raamcosta.compose.destinations.core)
    ksp(libs.ksp)

    // Pager - Accompanist
    implementation(Google.accompanist.pager) // Pager
    implementation(Google.accompanist.pager.indicators) // Pager Indicators

    // Swipe to Refresh - Accompanist
    implementation(Google.accompanist.swipeRefresh)

    // System UI Controller - Accompanist
    implementation(Google.accompanist.systemUiController)

    // Timber Logging
    implementation(JakeWharton.timber)
}
