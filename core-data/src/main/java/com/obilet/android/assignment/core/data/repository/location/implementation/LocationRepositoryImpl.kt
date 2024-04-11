package com.obilet.android.assignment.core.data.repository.location.implementation

import com.obilet.android.assignment.core.data.ApiManager
import com.obilet.android.assignment.core.data.mapper.bus_location.BusLocationRemoteResponseMapper
import com.obilet.android.assignment.core.data.repository.location.abstraction.LocationRepository
import com.obilet.android.assignment.core.data.usecase.GenerateBaseRequestModelUseCase
import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import com.obilet.android.assignment.core.network.datasource.abstraction.LocationRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationRemoteDataSource: LocationRemoteDataSource,
    private val busLocationRemoteResponseMapper: BusLocationRemoteResponseMapper,
    private val generateBaseRequestModelUseCase: GenerateBaseRequestModelUseCase,
) : LocationRepository {
    override suspend fun getBusLocations(query: String?): Flow<Resource<List<BusLocation>>> {
        return ApiManager.safeApiCall(
            mapFromModel = busLocationRemoteResponseMapper::mapModelList
        ) {
            val requestBody = generateBaseRequestModelUseCase.invoke(query)
            locationRemoteDataSource.getBusLocations(requestBody)
        }
    }
}