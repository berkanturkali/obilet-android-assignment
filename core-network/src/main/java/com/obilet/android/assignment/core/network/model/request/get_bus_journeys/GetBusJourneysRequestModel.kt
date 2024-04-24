package com.obilet.android.assignment.core.network.model.request.get_bus_journeys

import com.squareup.moshi.Json

data class GetBusJourneysRequestModel(
    @Json(name = "origin-id")
    val originId: Int,
    @Json(name = "destination-id")
    val destinationId: Int,
    @Json(name = "departure-date")
    val departureDate: String
)