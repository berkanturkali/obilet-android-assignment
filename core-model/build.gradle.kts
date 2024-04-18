plugins {
    alias(libs.plugins.obilet.android.library)
    alias(libs.plugins.obilet.android.hilt)
    id("kotlin-parcelize")
}

android {
    namespace = "com.obilet.android.assignment.core.model"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }
}

dependencies {
}