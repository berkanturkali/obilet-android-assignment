package com.obilet.android.assignment.core.cache.entity.mapper

import com.obilet.android.assignment.core.cache.entity.PassengerFilterEntity
import com.obilet.android.assignment.core.cache.entity.mapper.base.ModelToEntityModel
import com.obilet.android.assignment.core.model.flight_section.PassengerFilter
import javax.inject.Inject

class PassengerFilterToPassengerFilterEntityMapper @Inject constructor() :
    ModelToEntityModel<PassengerFilter, PassengerFilterEntity> {
    override fun mapFromModel(model: PassengerFilter): PassengerFilterEntity {
        return PassengerFilterEntity(
            passengerFilter = model.name,
            count = model.count,
            canIncrease = model.canIncrease,
            canDecrease = model.canDecrease
        )
    }

}