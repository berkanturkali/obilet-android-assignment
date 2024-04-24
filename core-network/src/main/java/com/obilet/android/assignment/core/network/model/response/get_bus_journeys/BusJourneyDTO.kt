package com.obilet.android.assignment.core.network.model.response.get_bus_journeys

import com.squareup.moshi.Json

data class BusJourneyDTO(
    val id: Long? = null,
    @Json(name = "partner-id")
    val partnerId: Long? = null,
    @Json(name = "partner-name")
    val partnerName: String? = null,
    @Json(name = "route-id")
    val routeId: Long? = null,
    @Json(name = "bus-type")
    val busType: String? = null,
    @Json(name = "bus-type-name")
    val busTypeName: String? = null,
    @Json(name = "total-seats")
    val totalSeats: Int? = null,
    @Json(name = "available-seats")
    val availableSeats: Int? = null,
    @Json(name = "journey")
    val journey: JourneyDTO? = null,
    @Json(name = "features")
    val features: List<FeatureDTO>? = null,
    @Json(name = "origin-location")
    val originLocation: String? = null,
    @Json(name = "destination-location")
    val destinationLocation: String? = null,
    @Json(name = "origin-location-id")
    val originLocationId: Int? = null,
    @Json(name = "destination-location-id")
    val destinationLocationId: Int? = null,
)