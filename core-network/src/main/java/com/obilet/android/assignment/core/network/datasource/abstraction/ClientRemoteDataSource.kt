package com.obilet.android.assignment.core.network.datasource.abstraction

import com.obilet.android.assignment.core.network.model.request.get_session.GetSessionRequestModel
import com.obilet.android.assignment.core.network.model.response.get_session.GetSessionResponseDTO
import retrofit2.Response
import retrofit2.http.Body

interface ClientRemoteDataSource {
    suspend fun getSession(@Body body: GetSessionRequestModel): Response<GetSessionResponseDTO>
}