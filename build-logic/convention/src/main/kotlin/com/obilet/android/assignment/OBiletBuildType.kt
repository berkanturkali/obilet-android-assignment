

import java.util.Locale


interface OBiletBuildType {

    val name: String
    val isMinifyEnabled: Boolean
    val isTestCoverageEnabled: Boolean
    val applicationIdSuffix: String
    val versionNameSuffix: String

    companion object {
        val Debug: OBiletBuildType = BuildTypeDebug
        val Release: OBiletBuildType = BuildTypeRelease
    }
}

private object BuildTypeDebug : OBiletBuildType {
    override val name: String get() = "debug"
    override val isMinifyEnabled: Boolean get() = false
    override val isTestCoverageEnabled: Boolean get() = true
    override val applicationIdSuffix: String get() = ".$name"
    override val versionNameSuffix: String get() = "-${name.uppercase(Locale.getDefault())}"
}

private object BuildTypeRelease : OBiletBuildType {
    override val name: String get() = "release"
    override val isMinifyEnabled: Boolean get() = true
    override val isTestCoverageEnabled: Boolean get() = true
    override val applicationIdSuffix: String get() = ".$name"
    override val versionNameSuffix: String get() = "-${name.uppercase(Locale.getDefault())}"
}