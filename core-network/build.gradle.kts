import OBiletBuildType.Companion.Debug
import com.obilet.android.assignment.Modules
import com.obilet.android.assignment.implementAll
import com.obilet.android.assignment.implementAllModules
import com.obilet.android.assignment.implementAllUnitTests

plugins {
    alias(libs.plugins.obilet.android.library)
    alias(libs.plugins.obilet.android.hilt)
}

android {
    namespace = "com.obilet.android.assignment.core.network"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "OUTBOUND_IP_URL", "\"https://api.ipify.org/\"")
        buildConfigField("String", "FEATURE_IMAGE_BASE_URL", "\"https://s3.eu-central-1.amazonaws.com/static.obilet.com/images/feature/\"")
    }
    buildTypes.getByName(Debug.name)
        .buildConfigField("String", "BASE_URL", "\"https://v2-api.obilet.com/api/\"")
}

dependencies {

    implementAllModules(
        Modules.CORE_MODEL,
        Modules.CORE_STORAGE
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