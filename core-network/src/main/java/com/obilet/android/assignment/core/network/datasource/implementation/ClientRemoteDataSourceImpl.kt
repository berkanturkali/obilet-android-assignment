package com.obilet.android.assignment.core.network.datasource.implementation

import com.obilet.android.assignment.core.network.datasource.abstraction.ClientRemoteDataSource
import com.obilet.android.assignment.core.network.model.request.get_session.GetSessionRequestModel
import com.obilet.android.assignment.core.network.model.response.base.BaseResponseDTO
import com.obilet.android.assignment.core.network.model.response.get_session.GetSessionDTO
import com.obilet.android.assignment.core.network.service.ClientService
import retrofit2.Response
import javax.inject.Inject

class ClientRemoteDataSourceImpl @Inject constructor(
    private val service: ClientService
) : ClientRemoteDataSource {
    override suspend fun getSession(body: GetSessionRequestModel): Response<BaseResponseDTO<GetSessionDTO>> {
        return service.getSession(body)
    }

}