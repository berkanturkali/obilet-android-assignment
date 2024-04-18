package com.obilet.android.assignment.core.model.bus_location

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BusLocation(
    val id: Int? = null,
    val name: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val cityId: Int? = null,
) : Parcelable