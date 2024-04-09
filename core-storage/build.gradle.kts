import com.obilet.android.assignment.Modules
import com.obilet.android.assignment.implementProject

plugins {
    alias(libs.plugins.obilet.android.library)
    alias(libs.plugins.obilet.android.hilt)
}

android {
    namespace = "com.obilet.android.assignment.core.storage"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }
}

dependencies {
    implementProject(
        Modules.CORE_MODEL
    )
}