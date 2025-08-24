plugins {
    alias(libs.plugins.icon.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "io.github.devdiestrolopez.tests.icon.compose.android"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

//    packaging {
//        resources {
//            pickFirsts += setOf(
//                "META-INF/LICENSE.md",
//                "META-INF/NOTICE.md",
//                "META-INF/LICENSE",
//                "META-INF/NOTICE",
//                "META-INF/DEPENDENCIES",
//                "META-INF/LICENSE-notice.md"
//            )
//            excludes += setOf(
//                "META-INF/licenses/**",
//                "META-INF/notice/**"
//            )
//        }
//    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    androidTestImplementation(projects.iconComposeAndroid)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.compose.material.icons.core)
    androidTestImplementation(libs.bundles.androidx.test)
    androidTestDebugImplementation(libs.androidx.compose.ui.tooling)
    androidTestDebugImplementation(libs.androidx.compose.ui.test.manifest)
}
