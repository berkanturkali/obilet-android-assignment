import OBiletBuildType.Companion.Release

plugins {
    alias(libs.plugins.obilet.android.application)
    alias(libs.plugins.obilet.android.hilt)
}

android {
    namespace = "com.obilet.android.assignment"

    defaultConfig {
        applicationId = "com.obilet.android.assignment"
        versionCode = 1
        versionName = "1.0"

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
}
hilt {
    enableAggregatingTask = true
}

dependencies {}