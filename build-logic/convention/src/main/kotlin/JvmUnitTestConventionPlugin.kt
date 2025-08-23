
import com.android.build.gradle.LibraryExtension
import io.github.devdiestrolopez.icon.implementation
import io.github.devdiestrolopez.icon.libs
import io.github.devdiestrolopez.icon.testRuntimeOnly
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class JvmUnitTestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val extension = extensions.getByType<LibraryExtension>()
            extension.apply {
                testOptions {
                    unitTests.all { it.useJUnitPlatform() }
                }

                dependencies {
                    implementation(libs.findBundle("junit-jupiter").get())
                    testRuntimeOnly(libs.findBundle("junit-jupiter-runtime").get())
                }
            }
        }
    }
}