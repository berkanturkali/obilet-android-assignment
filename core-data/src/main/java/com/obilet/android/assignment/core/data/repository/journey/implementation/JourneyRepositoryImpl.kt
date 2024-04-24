package com.obilet.android.assignment.core.data.repository.journey.implementation

import com.obilet.android.assignment.core.data.ApiManager
import com.obilet.android.assignment.core.data.repository.journey.abstraction.JourneyRepository
import com.obilet.android.assignment.core.model.BusJourney
import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.network.datasource.abstraction.JourneyRemoteDataSource
import com.obilet.android.assignment.core.network.mapper.bus_journeys.BusJourneyRemoteResponseMapper
import com.obilet.android.assignment.core.network.model.request.get_bus_journeys.GetBusJourneysRequestModel
import com.obilet.android.assignment.core.network.model.response.base.BaseResponseDTO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JourneyRepositoryImpl @Inject constructor(
    private val journeyRemoteDataSource: JourneyRemoteDataSource,
    private val busJourneyMapper: BusJourneyRemoteResponseMapper,
) : JourneyRepository {
    override suspend fun getBusJourneys(body: BaseResponseDTO<GetBusJourneysRequestModel>): Flow<Resource<BusJourney>> {
        return ApiManager.safeApiCall(mapFromModel = busJourneyMapper::mapFromModel) {
            journeyRemoteDataSource.getBusJourneys(body)
        }
    }
}