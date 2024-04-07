import OBiletBuildType.Companion.Debug
import com.obilet.android.assignment.implementAll

plugins {
    alias(libs.plugins.obilet.android.library)
}

android {
    namespace = "com.obilet.android.assignment.core.network"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes.getByName(Debug.name)
        .buildConfigField("String", "BASE_URL", "\"https://v2-api.obilet.com/api/\"")
}

dependencies {

    implementAll(
        libs.retrofit.core,
        libs.okhttp.logging,
        libs.okhttp,
        libs.moshi,
        libs.moshi.adapters
    )
}