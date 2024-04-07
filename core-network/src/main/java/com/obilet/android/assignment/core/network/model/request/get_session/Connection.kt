package com.obilet.android.assignment.core.network.model.request.get_session

import com.squareup.moshi.Json

data class Connection(
    @Json(name = "ip-address")
    val ipAddress: String? = null,
)