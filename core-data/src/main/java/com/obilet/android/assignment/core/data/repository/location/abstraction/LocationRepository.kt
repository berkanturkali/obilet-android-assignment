package com.obilet.android.assignment.core.data.repository.location.abstraction

import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getBusLocations(query: String?): Flow<Resource<List<BusLocation>>>
}