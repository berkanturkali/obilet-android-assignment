package com.obilet.android.assignment.core.data.datasource

import com.obilet.android.assignment.core.data.utils.DummyData
import com.obilet.android.assignment.core.network.datasource.abstraction.ClientRemoteDataSource
import com.obilet.android.assignment.core.network.model.request.get_session.GetSessionRequestModel
import com.obilet.android.assignment.core.network.model.response.Status
import com.obilet.android.assignment.core.network.model.response.base.BaseResponseDTO
import com.obilet.android.assignment.core.network.model.response.get_session.GetSessionDTO
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeClientRemoteDataSource : ClientRemoteDataSource, BaseFakeDataSource() {
    override suspend fun getSession(body: GetSessionRequestModel): Response<BaseResponseDTO<GetSessionDTO>> {
        return if (!throwException) {
            if (returnError) {
                Response.error(
                    500,
                    errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
                )
            } else {
                if (returnSuccessResponseButNull) {
                    Response.success(null)
                } else if (returnSuccessResponseWithBaseResponseButNullData) {
                    Response.success(
                        BaseResponseDTO(
                            status = Status.Success,
                            data = null
                        )
                    )
                } else {
                    Response.success(
                        BaseResponseDTO(
                            status = Status.Success,
                            data = DummyData.sessionDTO
                        )
                    )
                }
            }
        } else {
            throw exception
        }
    }
}