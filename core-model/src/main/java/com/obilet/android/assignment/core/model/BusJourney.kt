package com.obilet.android.assignment.core.model

data class BusJourney(
    val id: Long? = null,
    val partnerId: Long? = null,
    val partnerName: String? = null,
    val routeId: Long? = null,
    val busType: String? = null,
    val totalSeats: Int? = null,
    val availableSeats: Int? = null,
    val journeyOrigin: String? = null,
    val journeyDestination: String? = null,
    val journeyDeparture: String? = null,
    val journeyArrival: String? = null,
    val journeyDuration: String? = null,
    val journeyPrice: String? = null,
    val journeyBusName: String? = null,
    val originLocation: String? = null,
    val destinationLocation: String? = null,
    val originLocationId: Int? = null,
    val destinationLocationId: Int? = null,
    val stops: List<Stop>? = null,
    val features: List<Feature>? = null,
)