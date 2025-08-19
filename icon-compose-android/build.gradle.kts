plugins {
    alias(libs.plugins.icon.android.library.compose)
    alias(libs.plugins.icon.android.library)
}

android {
    namespace = "io.github.devdiestrolopez.icon.compose.android"

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(projects.iconCoreAndroid)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation.layout)
}