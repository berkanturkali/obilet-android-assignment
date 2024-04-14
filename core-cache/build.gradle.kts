import com.obilet.android.assignment.Modules
import com.obilet.android.assignment.implementAll
import com.obilet.android.assignment.implementAllModules
import com.obilet.android.assignment.implementAllUnitTests

plugins {
    alias(libs.plugins.obilet.android.library)
    alias(libs.plugins.obilet.android.hilt)
}

android {
    namespace = "com.obilet.android.assignment.core.cache"

    defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    defaultConfig.buildConfigField("int", "passengerFiltersDatabaseVersion", 1.toString())
    defaultConfig.buildConfigField(
        "String",
        "passengerFiltersDatabaseName",
        "\"passenger_filters_db\""
    )
}

dependencies {
    implementAllModules(
        Modules.CORE_MODEL,
    )
    implementAll(
        libs.androidx.room.ktx,
        libs.androidx.room.runtime
    )

    kapt(libs.androidx.room.compiler)

    implementAllUnitTests(
        libs.google.truth
    )
}