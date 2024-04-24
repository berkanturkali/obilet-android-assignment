package com.obilet.android.assignment.core.network.model.response.get_bus_journeys

import com.squareup.moshi.Json

data class JourneyDTO(
    @Json(name = "kind")
    val kind: String? = null,
    @Json(name = "code")
    val code: String? = null,
    @Json(name = "stops")
    val stops: List<StopDTO>? = null,
    @Json(name = "origin")
    val origin: String? = null,
    @Json(name = "destination")
    val destination: String? = null,
    @Json(name = "departure")
    val departure: String? = null,
    @Json(name = "arrival")
    val arrival: String? = null,
    @Json(name = "currency")
    val currency: String? = null,
    @Json(name = "duration")
    val duration: String? = null,
    @Json(name = "original-price")
    val originalPrice:Double? = null,
    @Json(name = "internet-price")
    val internetPrice:Double? = null,
    @Json(name = "bus-name")
    val busName:String? = null,
    )