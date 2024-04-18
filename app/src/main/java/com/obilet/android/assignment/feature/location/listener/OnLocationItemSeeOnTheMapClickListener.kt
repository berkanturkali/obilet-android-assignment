package com.obilet.android.assignment.feature.location.listener

import com.obilet.android.assignment.core.model.bus_location.BusLocation

interface OnLocationItemSeeOnTheMapClickListener {

    fun onSeeOnTheMapClicked(location: BusLocation)
}