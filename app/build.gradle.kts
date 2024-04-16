import OBiletBuildType.Companion.Release
import com.obilet.android.assignment.Modules
import com.obilet.android.assignment.implementAll
import com.obilet.android.assignment.implementAllModules
import com.obilet.android.assignment.implementAllUnitTests

plugins {
    alias(libs.plugins.obilet.android.application)
    alias(libs.plugins.obilet.android.hilt)
    alias(libs.plugins.obilet.android.application.compose)
}

android {
    namespace = "com.obilet.android.assignment"

    defaultConfig {
        applicationId = "com.obilet.android.assignment"
        versionCode = 1
        versionName = "1.0.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named(Release.name) {
            isMinifyEnabled = Release.isMinifyEnabled
            versionNameSuffix = Release.versionNameSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        viewBinding = true
    }
}
hilt {
    enableAggregatingTask = true
}

dependencies {
    implementAllModules(
        Modules.CORE_MODEL,
        Modules.CORE_NETWORK,
        Modules.CORE_DATA,
        Modules.CORE_STORAGE,
        Modules.CORE_CACHE,
    )
    implementAll(
        libs.androidx.core.splashScreen,
        libs.androidx.lifecycle.viewmodel,
        libs.androidx.fragment.ktx,
        libs.androidx.navigation.fragment.ktx,
        libs.androidx.navigation.ui.ktx,
        libs.androidx.compose.ui,
        libs.androidx.compose.material,
        libs.androidx.compose.constraint.layout,
        libs.androidx.compose.runtime.livedata,
        libs.jakewharton.timber
    )

    implementAllUnitTests(
        libs.google.truth
    )
}