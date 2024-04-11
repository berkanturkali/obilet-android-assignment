package com.obilet.android.assignment.core.network.utils

object UrlConstants {

    private const val CLIENT_PATH = "/client"
    private const val LOCATION_PATH = "/location"

    //region endpoints
    const val GET_SESSION_ENDPOINT = "$CLIENT_PATH/getsession"
    const val GET_BUS_LOCATIONS_ENDPOINT = "$LOCATION_PATH/getbuslocations"

    //endregion


    //region responses
    internal val GET_SESSION_SUCCESS_RESPONSE = getSuccessResponsePath("get_session")
    internal val GET_BUS_LOCATIONS_SUCCESS_RESPONSE = getSuccessResponsePath("get_bus_locations")

    //endregion
    private fun getSuccessResponsePath(name: String) = "responses/${name}_success_response.json"
}