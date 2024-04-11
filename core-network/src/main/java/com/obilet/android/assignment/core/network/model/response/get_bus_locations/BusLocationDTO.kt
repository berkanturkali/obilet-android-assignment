package com.obilet.android.assignment.core.network.model.response.get_bus_locations

import com.squareup.moshi.Json

data class BusLocationDTO(
    val id: Int? = null,
    @Json(name = "parent-id")
    val parentId: Int? = null,
    val type: String? = null,
    val name: String? = null,
    val zoom: Int? = null,
    @Json(name = "tz-code")
    val tzCode: String? = null,
    @Json(name = "weather-code")
    val weatherCode: String? = null,
    val rank: Int? = null,
    @Json(name = "reference-code")
    val referenceCode: String? = null,
    @Json(name = "city-id")
    val cityId: Int? = null,
    @Json(name = "reference-country")
    val referenceCountry: String? = null,
    @Json(name = "country-id")
    val countryId: Int? = null,
    val keywords: String? = null,
    @Json(name = "city-name")
    val cityName: String? = null,
    @Json(name = "country-name")
    val countryName: String? = null,
    @Json(name = "long-name")
    val longName: String? = null,
    @Json(name = "geo-location")
    val geoLocation: GeoLocationDTO? = null
)