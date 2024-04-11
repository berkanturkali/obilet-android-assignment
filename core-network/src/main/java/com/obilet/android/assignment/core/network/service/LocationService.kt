package com.obilet.android.assignment.core.network.service

import com.obilet.android.assignment.core.network.BuildConfig.BASE_URL
import com.obilet.android.assignment.core.network.endpoint.LocationServiceEndpoints
import com.obilet.android.assignment.core.network.model.request.base.BaseRequestModelDTO
import com.obilet.android.assignment.core.network.model.response.base.BaseResponseDTO
import com.obilet.android.assignment.core.network.model.response.get_bus_locations.BusLocationDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Service class that contains functions for
 * sub-routes of the /location/ route.
 *
 * For example the [getBusLocations]
 * fetches the data from
 * [BASE_URL]location/getbuslocations
 */
interface LocationService {
    @POST(LocationServiceEndpoints.GET_BUS_LOCATIONS_ENDPOINT)
    suspend fun getBusLocations(@Body body: BaseRequestModelDTO<String?>): Response<BaseResponseDTO<List<BusLocationDTO>>>
}