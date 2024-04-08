package com.obilet.android.assignment.core.network.dispatcher

import com.obilet.android.assignment.core.network.factory.getJson
import com.obilet.android.assignment.core.network.utils.UrlConstants
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

class ClientDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            UrlConstants.GET_SESSION_ENDPOINT -> {
                MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(UrlConstants.GET_SESSION_SUCCESS_RESPONSE))
            }

            else -> throw Exception("Invalid request path ${request.path}")
        }
    }
}