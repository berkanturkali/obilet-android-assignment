package com.obilet.android.assignment.core.network.datasource.abstraction

import com.obilet.android.assignment.core.network.model.request.base.BaseRequestModelDTO
import com.obilet.android.assignment.core.network.model.response.base.BaseResponseDTO
import com.obilet.android.assignment.core.network.model.response.get_bus_locations.BusLocationDTO
import retrofit2.Response

interface LocationRemoteDataSource {

    suspend fun getBusLocations(body: BaseRequestModelDTO<String?>): Response<BaseResponseDTO<List<BusLocationDTO>>>
}