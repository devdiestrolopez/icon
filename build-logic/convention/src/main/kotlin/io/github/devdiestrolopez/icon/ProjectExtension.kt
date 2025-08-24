package io.github.devdiestrolopez.icon

import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

@JvmName("implementationLibrary")
internal fun DependencyHandlerScope.implementation(library: Provider<MinimalExternalModuleDependency>) {
    add("implementation", library)
}

@JvmName("implementationBundle")
internal fun DependencyHandlerScope.implementation(bundle: Provider<ExternalModuleDependencyBundle>) {
    add("implementation", bundle)
}

@JvmName("testImplementationLibrary")
internal fun DependencyHandlerScope.testImplementation(library: Provider<MinimalExternalModuleDependency>) {
    add("testImplementation", library)
}
@JvmName("testImplementationBundle")
internal fun DependencyHandlerScope.testImplementation(bundle: Provider<ExternalModuleDependencyBundle>) {
    add("testImplementation", bundle)
}

internal fun DependencyHandlerScope.testRuntimeOnly(bundle: Provider<ExternalModuleDependencyBundle>) {
    add("testRuntimeOnly", bundle)
}

@JvmName("androidTestImplementationLibrary")
internal fun DependencyHandlerScope.androidTestImplementation(library: Provider<MinimalExternalModuleDependency>) {
    add("androidTestImplementation", library)
}

@JvmName("androidTestImplementationBundle")
internal fun DependencyHandlerScope.androidTestImplementation(bundle: Provider<ExternalModuleDependencyBundle>) {
    add("androidTestImplementation", bundle)
}

internal fun DependencyHandlerScope.debugImplementation(library: Provider<MinimalExternalModuleDependency>) {
    add("debugImplementation", library)
}

internal fun DependencyHandlerScope.implementation(project: Project) {
    add("implementation", project)
}
