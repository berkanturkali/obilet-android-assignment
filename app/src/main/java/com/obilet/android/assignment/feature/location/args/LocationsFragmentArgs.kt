package com.obilet.android.assignment.feature.location.args

import android.os.Parcelable
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import com.obilet.android.assignment.feature.location.model.LocationDirection
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationsFragmentArgs(
    val direction: LocationDirection,
    val selectedOrigin: BusLocation,
    val selectedDestination: BusLocation,
    val locationList: List<BusLocation>,
    val previousTabItemLabelId: Int,
) : Parcelable {

    companion object {
        const val KEY = "locationsFragmentArgs"
    }
}