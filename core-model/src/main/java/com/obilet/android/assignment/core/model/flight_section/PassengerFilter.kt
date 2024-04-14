package com.obilet.android.assignment.core.model.flight_section

import androidx.annotation.StringRes
import com.obilet.android.assignment.core.model.R

enum class PassengerFilter(
    @StringRes val title: Int,
    var count: Int = 0,
    val adult: Boolean = false,
    var canIncrease: Boolean = true,
    var canDecrease: Boolean = false,
) {
    ADULT(R.string.adult, count = 1, adult = true),
    STUDENT(R.string.student),
    CHILD(R.string.child),
    BABY(R.string.baby),
    ELDER(R.string.elder, adult = true),
}