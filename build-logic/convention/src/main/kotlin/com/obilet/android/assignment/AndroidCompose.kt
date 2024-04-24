package com.obilet.android.assignment

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion =
                libs.findVersion("androidXComposeCompiler").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
        }

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + (
                        "-Xopt-in=kotlin.Experimental," +
                                "androidx.compose.ui.ExperimentalComposeUiApi," +
                                "androidx.compose.foundation.ExperimentalFoundationApi," +
                                "androidx.compose.ui.ExperimentalComposeUiApi," +
                                "androidx.compose.material.ExperimentalMaterialApi," +
                                "androidx.compose.animation.ExperimentalAnimationApi," +
                                "androidx.compose.foundation.layout.ExperimentalLayoutApi,"
                        )
            }
        }


    }

}