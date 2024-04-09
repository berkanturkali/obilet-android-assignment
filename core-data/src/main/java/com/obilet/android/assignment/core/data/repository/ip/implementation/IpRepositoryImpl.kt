package com.obilet.android.assignment.core.data.repository.ip.implementation

import com.obilet.android.assignment.core.data.ApiManager
import com.obilet.android.assignment.core.data.R
import com.obilet.android.assignment.core.data.repository.ip.abstraction.IpRepository
import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.model.UiText
import com.obilet.android.assignment.core.network.datasource.abstraction.IpRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IpRepositoryImpl @Inject constructor(
    private val ipRemoteDataSource: IpRemoteDataSource,
) : IpRepository {
    override fun getOutboundIpAddress(): Flow<Resource<String>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = withContext(Dispatchers.IO) { ipRemoteDataSource.getOutboundIpAddress().execute() }
                if (response.isSuccessful && !response.body().isNullOrBlank()) {
                    emit(Resource.Success(response.body()))
                } else {
                    emit(Resource.Error(text = UiText.StringResource(R.string.something_went_wrong_please_try_again_later)))
                }

            } catch (e: Exception) {
                emit(ApiManager.handleException(e))
            }
        }
    }
}