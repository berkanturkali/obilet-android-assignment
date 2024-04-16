package com.obilet.android.assignment.feature.bus_section.usecase

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class FormatDateWithTheGivenPatternUseCase @Inject constructor() {
    companion object {
        private const val DEFAULT_PATTERN = "dd MMMM EEEE"
    }

    operator fun invoke(date: Date, pattern: String = DEFAULT_PATTERN): String {
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return simpleDateFormat.format(date)
    }
}