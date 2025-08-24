plugins {
    alias(libs.plugins.icon.android.library)
}

android {
    namespace = "io.github.devdiestrolopez.tests.icon.core.android"

    testOptions {
        unitTests.all { it.useJUnitPlatform() }
    }
}

dependencies {
    testImplementation(projects.iconCoreAndroid)
    testImplementation(libs.bundles.junit.jupiter)
    testImplementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.androidx.compose.ui)
    testRuntimeOnly(libs.bundles.junit.jupiter.runtime)
}