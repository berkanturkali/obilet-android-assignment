import com.obilet.android.assignment.Modules
import com.obilet.android.assignment.implementAll
import com.obilet.android.assignment.implementAllModules
import com.obilet.android.assignment.implementAllUnitTests

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

    implementAllModules(
        Modules.CORE_NETWORK,
        Modules.CORE_MODEL,
        Modules.CORE_STORAGE,
        Modules.CORE_CACHE,
    )

    implementAll(
        libs.retrofit.core,
    )

    implementAllUnitTests(
        libs.google.truth,
    )
}