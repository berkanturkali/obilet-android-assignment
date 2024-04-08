package com.obilet.android.assignment.core.network.model.response.base

import com.obilet.android.assignment.core.network.model.response.Status
import com.squareup.moshi.Json

data class BaseResponseDTO<T>(
    val status: Status? = null,
    val data: T? = null,
    val message: String? = null,
    @Json(name = "user-message")
    val userMessage: String? = null,
    @Json(name = "api-request-id")
    val apiRequestId: String? = null,
    val controller: String? = null,
)