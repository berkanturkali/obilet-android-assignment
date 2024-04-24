package com.obilet.android.assignment.core.network.datasource.abstraction

import com.obilet.android.assignment.core.network.model.request.get_bus_journeys.GetBusJourneysRequestModel
import com.obilet.android.assignment.core.network.model.response.base.BaseResponseDTO
import com.obilet.android.assignment.core.network.model.response.get_bus_journeys.BusJourneyDTO
import retrofit2.Response

interface JourneyRemoteDataSource {

    suspend fun getBusJourneys(body: BaseResponseDTO<GetBusJourneysRequestModel>): Response<BaseResponseDTO<BusJourneyDTO>>
}