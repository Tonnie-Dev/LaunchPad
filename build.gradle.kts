buildscript {

    repositories{
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Android.tools.build.gradlePlugin)
        classpath (libs.kotlin.gradle.plugin)
        classpath(Google.dagger.hilt.android.gradlePlugin)
        classpath(libs.spotless.plugin.gradle)
    }

}


