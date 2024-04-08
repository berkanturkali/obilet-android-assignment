import com.obilet.android.assignment.implementAll

plugins {
    alias(libs.plugins.obilet.android.library)
    alias(libs.plugins.obilet.android.hilt)
}

android {
    namespace = "com.obilet.android.assignment.core.data"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementAll(
        libs.retrofit.core,
        )
}