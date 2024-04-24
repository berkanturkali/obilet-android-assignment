package com.obilet.android.assignment.core.data.repository.journey.abstraction

import com.obilet.android.assignment.core.model.BusJourney
import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.network.model.request.get_bus_journeys.GetBusJourneysRequestModel
import com.obilet.android.assignment.core.network.model.response.base.BaseResponseDTO
import kotlinx.coroutines.flow.Flow

interface JourneyRepository {

    suspend fun getBusJourneys(body: BaseResponseDTO<GetBusJourneysRequestModel>): Flow<Resource<BusJourney>>


}