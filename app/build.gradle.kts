import OBiletBuildType.Companion.Release
import com.obilet.android.assignment.Modules
import com.obilet.android.assignment.implementAll
import com.obilet.android.assignment.implementAllModules

plugins {
    alias(libs.plugins.obilet.android.application)
    alias(libs.plugins.obilet.android.hilt)
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
    )
    implementAll(
        libs.androidx.core.splashScreen,
        libs.androidx.lifecycle.viewmodel,
        libs.androidx.fragment.ktx
    )
}