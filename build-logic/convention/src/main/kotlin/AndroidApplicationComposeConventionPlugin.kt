import com.android.build.api.dsl.ApplicationExtension
import io.github.devdiestrolopez.icon.configureAndroidCompose
import io.github.devdiestrolopez.icon.implementation
import io.github.devdiestrolopez.icon.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            extensions.configure<ApplicationExtension> {
                configureAndroidCompose(this)

                dependencies {
                    implementation(libs.findLibrary("androidx-activity-compose").get())
                }
            }
        }
    }
}
