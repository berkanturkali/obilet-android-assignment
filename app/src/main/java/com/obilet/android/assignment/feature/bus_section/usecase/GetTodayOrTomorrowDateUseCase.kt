package com.obilet.android.assignment.feature.bus_section.usecase

import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class GetTodayOrTomorrowDateUseCase @Inject constructor() {

    operator fun invoke(isTomorrow: Boolean): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_WEEK, if (isTomorrow) 1 else 0)
        return calendar.time
    }
}