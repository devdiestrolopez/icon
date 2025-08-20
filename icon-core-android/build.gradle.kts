plugins {
    alias(libs.plugins.icon.android.library.compose)
    alias(libs.plugins.icon.android.library)
    id("maven-publish")
    signing
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
            groupId = "io.github.devdiestrolopez.icon.core"
            artifactId = "icon-core-android"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name = "Icon Core Android"
                description = "Foundational library that provides a type-safe, unified API for handling drawable vectors and vector images resources."
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

            repositories {
                maven {
                    name = "ossrh-staging-api"
                    url = uri("https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2/")

                    credentials {
                        username = project.properties["sonatypeUsername"] as String
                        password = project.properties["sonatypePassword"] as String
                    }
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
    implementation(libs.androidx.annotation)
}
