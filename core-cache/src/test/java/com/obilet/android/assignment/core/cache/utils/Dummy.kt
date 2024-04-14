package com.obilet.android.assignment.core.cache.utils

import com.obilet.android.assignment.core.cache.entity.PassengerFilterEntity
import com.obilet.android.assignment.core.model.flight_section.PassengerFilter

object Dummy {

    val passengerFilterEntity = PassengerFilterEntity(
        passengerFilter = PassengerFilter.ADULT.toString(),
        count = 1,
        canIncrease = true,
        canDecrease = false
    )
}