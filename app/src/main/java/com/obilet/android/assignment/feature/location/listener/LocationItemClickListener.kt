package com.obilet.android.assignment.feature.location.listener

import com.obilet.android.assignment.core.model.bus_location.BusLocation
import com.obilet.android.assignment.feature.location.model.LocationDirection

interface LocationItemClickListener {
    fun onLocationItemClick(direction: LocationDirection, busLocation: BusLocation)
}