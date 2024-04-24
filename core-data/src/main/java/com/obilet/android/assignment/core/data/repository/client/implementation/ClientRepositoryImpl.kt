package com.obilet.android.assignment.core.data.repository.client.implementation

import com.obilet.android.assignment.core.data.ApiManager
import com.obilet.android.assignment.core.network.mapper.getsession.SessionRemoteResponseMapper
import com.obilet.android.assignment.core.data.repository.client.abstraction.ClientRepository
import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.model.getsession.Session
import com.obilet.android.assignment.core.network.datasource.abstraction.ClientRemoteDataSource
import com.obilet.android.assignment.core.network.model.request.get_session.GetSessionRequestModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClientRepositoryImpl @Inject constructor(
    private val remoteDataSource: ClientRemoteDataSource,
    private val sessionRemoteResponseMapper: SessionRemoteResponseMapper
) : ClientRepository {
    override fun getSession(body: GetSessionRequestModel): Flow<Resource<Session>> {
        return ApiManager.safeApiCall(mapFromModel = sessionRemoteResponseMapper::mapFromModel) {
            remoteDataSource.getSession(body)
        }
    }
}