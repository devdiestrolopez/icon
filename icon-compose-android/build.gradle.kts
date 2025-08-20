plugins {
    alias(libs.plugins.icon.android.library.compose)
    alias(libs.plugins.icon.android.library)
    id("maven-publish")
    signing
}

android {
    namespace = "io.github.devdiestrolopez.icon.compose.android"

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            consumerProguardFiles("consumer-rules.pro")
        }
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "io.github.devdiestrolopez.icon.compose"
            artifactId = "icon-compose-android"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name = "Icon Compose Android"
                description = "Jetpack Compose library that provides a single, unified Icon composable that works with both standard drawable " +
                        "resources and Compose ImageVectors by using the IconResource sealed interface from the icon-core-android library."
                inceptionYear = "2025"
                url = "https://github.com/devdiestrolopez/icon"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "devdiestrolopez"
                        name = "Carlos Diestro Lopez"
                        email = "devdiestrolopez@gmail.com"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/devdiestrolopez/icon.git"
                    developerConnection = "scm:git:ssh://github.com/devdiestrolopez/icon.git"
                    url = "https://github.com/devdiestrolopez/icon"
                }
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}

dependencies {
    api(projects.iconCoreAndroid)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation.layout)
}