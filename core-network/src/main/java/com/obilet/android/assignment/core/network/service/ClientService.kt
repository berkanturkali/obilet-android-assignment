package com.obilet.android.assignment.core.network.service

import com.obilet.android.assignment.core.network.BuildConfig.BASE_URL
import com.obilet.android.assignment.core.network.endpoint.ClientServiceEndpoints.GET_SESSION_ENDPOINT
import com.obilet.android.assignment.core.network.model.request.get_session.GetSessionRequestModel
import com.obilet.android.assignment.core.network.model.response.get_session.GetSessionResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Service class that contains functions for
 * sub-routes of the /client/ route.
 *
 * For example the [getSession]
 * fetches the data from
 * [BASE_URL]client/getsession
 */
interface ClientService {

    @POST(GET_SESSION_ENDPOINT)
    suspend fun getSession(@Body body: GetSessionRequestModel): Response<GetSessionResponseDTO>
}