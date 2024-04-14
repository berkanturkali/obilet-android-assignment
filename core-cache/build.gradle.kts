import com.obilet.android.assignment.implementAll

plugins {
    alias(libs.plugins.obilet.android.library)
    alias(libs.plugins.obilet.android.hilt)
}

android {
    namespace = "com.obilet.android.assignment.core.cache"

    defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    buildTypes {

    }
}

dependencies {
    implementAll(
        libs.androidx.room.ktx,
        libs.androidx.room.runtime
    )

    kapt(libs.androidx.room.compiler)
}