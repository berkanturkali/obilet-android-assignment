package com.obilet.android.assignment.feature.journeys.args

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BusJourneysFragmentArgs(
    val originId: Int,
    val destinationId: Int,
    val departureDate: String,
    val originName: String?,
    val destinationName: String?,
) : Parcelable {
    companion object {
        const val BUS_JOURNEYS_FRAGMENT_ARGS_KEY = "busJourneysFragmentArgs"
    }
}