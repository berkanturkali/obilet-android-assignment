package com.obilet.android.assignment.core.network.model.request.get_session

data class GetSessionRequestModel(
    val type: Int? = null,
    val connection: Connection? = null,
    val application: Application? = null,
)