package com.obilet.android.assignment.core.network.model.request.get_session

import com.squareup.moshi.Json

data class Application(
    val version: String? = null,
    @Json(name = "equipment-id")
    val equipmentId: String? = null,
)