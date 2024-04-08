package com.obilet.android.assignment.core.network.datasource.implementation

import com.obilet.android.assignment.core.network.datasource.abstraction.ClientRemoteDataSource
import com.obilet.android.assignment.core.network.model.request.get_session.GetSessionRequestModel
import com.obilet.android.assignment.core.network.model.response.get_session.GetSessionResponseDTO
import com.obilet.android.assignment.core.network.service.ClientService
import retrofit2.Response
import javax.inject.Inject

class ClientRemoteDataSourceImpl @Inject constructor(
    private val service: ClientService
) : ClientRemoteDataSource {
    override suspend fun getSession(body: GetSessionRequestModel): Response<GetSessionResponseDTO> {
        return service.getSession(body)
    }
}