package com.obilet.android.assignment.feature.location.listener

import com.obilet.android.assignment.core.model.bus_location.BusLocation
import com.obilet.android.assignment.feature.location.model.LocationDirection

interface OriginDestinationSelectListener {
    fun onOriginDestinationSelected(direction: LocationDirection, location: BusLocation)
}