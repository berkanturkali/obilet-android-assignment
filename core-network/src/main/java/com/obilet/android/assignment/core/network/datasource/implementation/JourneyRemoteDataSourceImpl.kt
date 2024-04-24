package com.obilet.android.assignment.core.network.datasource.implementation

import com.obilet.android.assignment.core.network.datasource.abstraction.JourneyRemoteDataSource
import com.obilet.android.assignment.core.network.model.request.get_bus_journeys.GetBusJourneysRequestModel
import com.obilet.android.assignment.core.network.model.response.base.BaseResponseDTO
import com.obilet.android.assignment.core.network.model.response.get_bus_journeys.BusJourneyDTO
import com.obilet.android.assignment.core.network.service.JourneyService
import retrofit2.Response
import javax.inject.Inject

class JourneyRemoteDataSourceImpl @Inject constructor(
    private val service: JourneyService
) : JourneyRemoteDataSource {
    override suspend fun getBusJourneys(body: BaseResponseDTO<GetBusJourneysRequestModel>): Response<BaseResponseDTO<BusJourneyDTO>> {
        return service.getBusJourneys(body)
    }
}