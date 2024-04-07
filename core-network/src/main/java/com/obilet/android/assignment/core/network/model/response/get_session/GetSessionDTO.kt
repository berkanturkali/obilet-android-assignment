package com.obilet.android.assignment.core.network.model.response.get_session

import com.squareup.moshi.Json

data class GetSessionDTO(
    @Json(name = "session-id")
    val sessionId: String? = null,
    @Json(name = "device-id")
    val deviceId: String? = null,
    val affiliate: String? = null,
    @Json(name = "device-type")
    val deviceType: Int? = null,
    val device: String? = null,
    @Json(name = "ip-country")
    val ipCountry: String? = null,
)