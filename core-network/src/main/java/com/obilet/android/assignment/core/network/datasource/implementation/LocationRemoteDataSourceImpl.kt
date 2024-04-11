package com.obilet.android.assignment.core.network.datasource.implementation

import com.obilet.android.assignment.core.network.datasource.abstraction.LocationRemoteDataSource
import com.obilet.android.assignment.core.network.model.request.base.BaseRequestModelDTO
import com.obilet.android.assignment.core.network.model.response.base.BaseResponseDTO
import com.obilet.android.assignment.core.network.model.response.get_bus_locations.BusLocationDTO
import com.obilet.android.assignment.core.network.service.LocationService
import retrofit2.Response
import javax.inject.Inject

class LocationRemoteDataSourceImpl @Inject constructor(
    private val locationService: LocationService
) : LocationRemoteDataSource {
    override suspend fun getBusLocations(body: BaseRequestModelDTO<String?>): Response<BaseResponseDTO<List<BusLocationDTO>>> {
        return locationService.getBusLocations(body)
    }
}