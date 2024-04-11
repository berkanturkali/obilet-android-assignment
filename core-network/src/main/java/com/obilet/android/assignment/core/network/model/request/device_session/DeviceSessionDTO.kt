package com.obilet.android.assignment.core.network.model.request.device_session

import com.squareup.moshi.Json


data class DeviceSessionDTO(
    @Json(name = "session-id")
    val sessionId: String? = null,
    @Json(name = "device-id")
    val deviceId: String? = null,
) 