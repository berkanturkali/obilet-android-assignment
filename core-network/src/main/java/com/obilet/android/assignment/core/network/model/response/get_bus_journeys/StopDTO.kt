package com.obilet.android.assignment.core.network.model.response.get_bus_journeys

import com.squareup.moshi.Json

data class StopDTO(
    val id: Int? = null,
    @Json(name = "kolayCarLocationId")
    val carLocationId: Int? = null,
    val name: String? = null,
    val station: String? = null,
    val time: String? = null,
    @Json(name = "is-origin")
    val isOrigin: Boolean? = null,
    @Json(name = "is-destination")
    val isDestination: Boolean? = null,
    @Json(name = "is-segment-stop")
    val isSegmentStop: Boolean? = null,
)