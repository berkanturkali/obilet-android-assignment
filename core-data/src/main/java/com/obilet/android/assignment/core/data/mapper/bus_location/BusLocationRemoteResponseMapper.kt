package com.obilet.android.assignment.core.data.mapper.bus_location

import com.obilet.android.assignment.core.data.mapper.base.RemoteResponseModelMapper
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import com.obilet.android.assignment.core.network.model.response.get_bus_locations.BusLocationDTO
import javax.inject.Inject

class BusLocationRemoteResponseMapper @Inject constructor() :
    RemoteResponseModelMapper<BusLocationDTO, BusLocation> {
    override fun mapFromModel(model: BusLocationDTO): BusLocation {
        return BusLocation(
            id = model.id,
            name = model.name,
            latitude = model.geoLocation?.latitude,
            longitude = model.geoLocation?.longitude
        )
    }
}