import com.android.build.gradle.LibraryExtension
import com.obilet.android.assignment.configureKotlinAndroid
import com.obilet.android.assignment.implementAll
import com.obilet.android.assignment.implementAllAndroidTests
import com.obilet.android.assignment.implementAllUnitTests
import com.obilet.android.assignment.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
            }

            dependencies {
                implementAll(
                    libs.findLibrary("androidx-core").get(),
                    libs.findLibrary("androidx-appcompat").get(),
                    libs.findLibrary("google-material").get(),
                )

                implementAllUnitTests(
                    libs.findLibrary("junit").get()
                )

                implementAllAndroidTests(
                    libs.findLibrary("androidx-test-espresso-core").get(),
                    libs.findLibrary("androidx-test-junit").get(),
                )
            }
        }
    }
}