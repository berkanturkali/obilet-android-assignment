package com.obilet.android.assignment.core.cache.entity.mapper

import com.obilet.android.assignment.core.cache.entity.PassengerFilterEntity
import com.obilet.android.assignment.core.cache.entity.mapper.base.EntityToModelMapper
import com.obilet.android.assignment.core.model.flight_section.PassengerFilter
import javax.inject.Inject

class PassengerFilterEntityToPassengerFilterMapper @Inject constructor() :
    EntityToModelMapper<PassengerFilterEntity, PassengerFilter> {
    override fun mapFromModel(model: PassengerFilterEntity): PassengerFilter {
        val passengerFilter = PassengerFilter.valueOf(model.passengerFilter)
        passengerFilter.apply {
            count = model.count
            canIncrease = model.canIncrease
            canDecrease = model.canDecrease
        }
        return passengerFilter
    }
}