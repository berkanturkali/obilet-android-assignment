package com.obilet.android.assignment.core.network.utils

object UrlConstants {

    private const val CLIENT_PATH = "/client"

    //region endpoints

    const val GET_SESSION_ENDPOINT = "$CLIENT_PATH/getsession"

    //endregion


    //region responses
    internal
    val GET_SESSION_SUCCESS_RESPONSE = getSuccessResponsePath("get_session")

    //endregion
    private fun getSuccessResponsePath(name: String) = "responses/${name}_success_response.json"
}