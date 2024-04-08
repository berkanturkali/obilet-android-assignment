import com.obilet.android.assignment.Modules
import com.obilet.android.assignment.implementAll
import com.obilet.android.assignment.implementAllProjects
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

    implementAllProjects(
        Modules.CORE_NETWORK,
        Modules.CORE_MODEL,
    )

    implementAll(
        libs.retrofit.core,
    )

    implementAllUnitTests(
        libs.google.truth,
    )
}