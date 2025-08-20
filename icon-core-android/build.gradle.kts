plugins {
    alias(libs.plugins.icon.android.library.compose)
    alias(libs.plugins.icon.android.library)
}

android {
    namespace = "io.github.devdiestrolopez.icon.core.android"

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            consumerProguardFiles("consumer-rules.pro")
        }
    }
}

dependencies {
    implementation(libs.androidx.annotation)
}
