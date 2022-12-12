buildscript {
    ext {
        compose_ui_version = '1.3.1'
        paging_compose = "1.0.0-alpha16"
        swipe_refresh = "0.24.7-alpha"
        shimmer = "0.5.0"
        accompanist_version = '0.28.0'
    }

    dependencies {
        classpath "com.google.dagger:hilt-android-gradle-plugin:2.44"
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id 'com.android.application' version '7.3.1' apply false
    id 'com.android.library' version '7.3.1' apply false
    id 'org.jetbrains.kotlin.android' version '1.7.20' apply false
}