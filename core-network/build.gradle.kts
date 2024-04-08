import OBiletBuildType.Companion.Debug
import com.obilet.android.assignment.Modules
import com.obilet.android.assignment.implementAll
import com.obilet.android.assignment.implementAllProjects
import com.obilet.android.assignment.implementAllUnitTests

plugins {
    alias(libs.plugins.obilet.android.library)
    alias(libs.plugins.obilet.android.hilt)
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

    implementAllProjects(
        Modules.CORE_MODEL,
    )

    implementAll(
        libs.retrofit.core,
        libs.okhttp.logging,
        libs.okhttp,
        libs.moshi,
        libs.moshi.adapters,
        libs.retrofit.moshi.converter
    )

    implementAllUnitTests(
        libs.google.truth,
        libs.okhttp.mock.web.server
    )
}