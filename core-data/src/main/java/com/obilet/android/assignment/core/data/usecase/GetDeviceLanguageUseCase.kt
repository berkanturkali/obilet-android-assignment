package com.obilet.android.assignment.core.data.usecase

import java.util.Locale
import javax.inject.Inject

class GetDeviceLanguageUseCase @Inject constructor() {
    operator fun invoke(): String {
        val defaultLocale = Locale.getDefault()
        return "${defaultLocale.language}-${defaultLocale.language.uppercase()}"
    }
}