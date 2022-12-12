buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }

}

plugins{

    id ("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id ("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}


