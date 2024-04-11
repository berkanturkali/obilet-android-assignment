package com.obilet.android.assignment.core.network.model.request.base

import com.obilet.android.assignment.core.network.model.request.device_session.DeviceSessionDTO
import com.squareup.moshi.Json

data class BaseRequestModelDTO<T>(
    @Json(name = "device-session")
    val deviceSessionDTO: DeviceSessionDTO? = null,
    @Json(name = "date")
    var date: String? = null,
    @Json(name = "language")
    var language: String? = null,
    @Json(name = "data")
    var data: T? = null,
)