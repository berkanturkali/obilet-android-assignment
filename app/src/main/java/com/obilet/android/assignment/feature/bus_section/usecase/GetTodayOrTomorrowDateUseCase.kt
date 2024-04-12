package com.obilet.android.assignment.feature.bus_section.usecase

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetTodayOrTomorrowDateUseCase @Inject constructor() {

    companion object {
        private const val PATTERN = "dd MMMM EEEE"
    }

    operator fun invoke(isTomorrow: Boolean): String {
        val dateFormat = SimpleDateFormat(PATTERN, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_WEEK, if (isTomorrow) 1 else 0)
        val date = calendar.time
        return dateFormat.format(date)
    }
}