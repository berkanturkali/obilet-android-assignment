package com.obilet.android.assignment.core.network.service

import com.obilet.android.assignment.core.network.BuildConfig.BASE_URL
import com.obilet.android.assignment.core.network.endpoint.JourneyServiceEndpoints
import com.obilet.android.assignment.core.network.model.request.get_bus_journeys.GetBusJourneysRequestModel
import com.obilet.android.assignment.core.network.model.response.base.BaseResponseDTO
import com.obilet.android.assignment.core.network.model.response.get_bus_journeys.BusJourneyDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Service class that contains functions for
 * sub-routes of the /journey/ route.
 *
 * For example the [getBusJourneys]
 * fetches the data from
 * [BASE_URL]journey/getbusjourneys
 */
interface JourneyService {
    @POST(JourneyServiceEndpoints.GET_BUS_JOURNEYS_ENDPOINT)
    suspend fun getBusJourneys(@Body body: BaseResponseDTO<GetBusJourneysRequestModel>): Response<BaseResponseDTO<BusJourneyDTO>>
}