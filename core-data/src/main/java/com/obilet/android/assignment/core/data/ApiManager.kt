package com.obilet.android.assignment.core.data

import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.model.UiText
import com.obilet.android.assignment.core.network.model.response.Status
import com.obilet.android.assignment.core.network.model.response.base.BaseResponseDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

object ApiManager {

    /**
     * Utility function to fetch and return the response that OBilet api sends.
     *
     * @param ResponseType represents the response type.
     * @param MappedResponseType represents the [ResponseType] that will be mapped to this type via [mapFromModel] function.
     * @param dispatcher is [CoroutineDispatcher] which will execute the [apiCall] in.
     * @param apiCall is service function that will fetch the data from OBilet api.
     */
    inline fun <reified ResponseType, reified MappedResponseType> safeApiCall(
        noinline mapFromModel: ((ResponseType) -> MappedResponseType)? = null,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        crossinline apiCall: suspend () -> Response<BaseResponseDTO<ResponseType>>
    ): Flow<Resource<MappedResponseType>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = withContext(dispatcher) { apiCall() }
                if (response.isSuccessful) {
                    response.body()?.let { baseResponse ->
                        if (baseResponse.status == Status.Success) {
                            baseResponse.data?.let { data ->
                                emit(
                                    Resource.Success(
                                        mapFromModel?.invoke(data) ?: data as MappedResponseType
                                    )
                                )
                            } ?: emit(
                                Resource.Success(null)
                            )
                        } else {
                            emit(
                                Resource.Error(
                                    text = UiText.StringResource(R.string.something_went_wrong)
                                )
                            )
                        }
                    } ?: emit(
                        Resource.Error(
                            code = response.code(),
                            text = UiText.StringResource(R.string.no_result_found)
                        )
                    )
                } else {
                    emit(
                        Resource.Error(
                            code = response.code(),
                            text = UiText.StringResource(R.string.something_went_wrong)
                        )
                    )

                }
            } catch (exception: Exception) {
                when (exception) {
                    is TimeoutCancellationException -> {
                        emit(Resource.Error(text = UiText.StringResource(R.string.timeout)))
                    }

                    is IOException -> {
                        emit(
                            Resource.Error(
                                text = exception.localizedMessage?.let { message ->
                                    UiText.DynamicString(message)
                                }
                                    ?: UiText.StringResource(R.string.check_your_connection)
                            ))
                    }

                    else -> {
                        emit(Resource.Error(text = UiText.StringResource(R.string.something_went_wrong)))
                    }
                }
            }
        }
    }
}