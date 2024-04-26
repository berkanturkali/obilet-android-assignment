package com.obilet.android.assignment.core.data.repository.journey.abstraction

import com.obilet.android.assignment.core.model.BusJourney
import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.network.model.request.get_bus_journeys.GetBusJourneysRequestModel
import kotlinx.coroutines.flow.Flow

interface JourneyRepository {

    suspend fun getBusJourneys(body: GetBusJourneysRequestModel): Flow<Resource<List<BusJourney>>>


}