package com.obilet.android.assignment.feature.search.usecase

import com.obilet.android.assignment.core.model.bus_location.BusLocation
import javax.inject.Inject

class FindDefaultLocationsByCityIdUseCase @Inject constructor() {
    operator fun invoke(locationList: List<BusLocation>): Pair<BusLocation?, BusLocation?> {
        val origin = locationList.first()
        val destination = locationList.firstOrNull { origin.cityId != it.cityId }
        return if (destination != null) Pair(origin, destination) else Pair(null, null)
    }
}