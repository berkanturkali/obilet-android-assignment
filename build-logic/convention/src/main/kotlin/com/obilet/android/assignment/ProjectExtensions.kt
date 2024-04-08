package com.obilet.android.assignment

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun DependencyHandler.implementation(dependency: Any) = add(
    "implementation", dependency
)

fun DependencyHandler.implementAll(vararg dependencies: Any) {
    dependencies.forEach(::implementation)
}

fun DependencyHandler.testImplementation(dependency: Any) = add(
    "testImplementation", dependency
)

fun DependencyHandler.androidTestImplementation(dependency: Any) = add(
    "androidTestImplementation", dependency
)

fun DependencyHandler.implementAllUnitTests(vararg dependencies: Any) {
    dependencies.forEach(::testImplementation)
}

fun DependencyHandler.implementAllAndroidTests(vararg dependencies: Any) {
    dependencies.forEach(::androidTestImplementation)
}

fun DependencyHandler.implementProject(projectLib: String) = add(
    "implementation", project(projectLib)
)

fun DependencyHandler.implementAllProjects(vararg projectLibs: String) {
    projectLibs.forEach(::implementProject)
}
