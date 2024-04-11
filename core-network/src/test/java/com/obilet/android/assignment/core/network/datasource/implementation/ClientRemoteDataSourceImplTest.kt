package com.obilet.android.assignment.core.network.datasource.implementation

import com.google.common.truth.Truth
import com.obilet.android.assignment.core.network.datasource.abstraction.ClientRemoteDataSource
import com.obilet.android.assignment.core.network.dispatcher.ClientDispatcher
import com.obilet.android.assignment.core.network.factory.getJson
import com.obilet.android.assignment.core.network.factory.makeApiService
import com.obilet.android.assignment.core.network.factory.responseAdapter
import com.obilet.android.assignment.core.network.model.response.base.BaseResponseDTO
import com.obilet.android.assignment.core.network.model.response.get_session.GetSessionDTO
import com.obilet.android.assignment.core.network.utils.DummyData
import com.obilet.android.assignment.core.network.utils.UrlConstants
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import org.junit.Test

class ClientRemoteDataSourceImplTest :
    BaseDataSourceImplTest<ClientRemoteDataSource, ClientRemoteDataSourceImpl>() {

    @Test
    fun `check that calling getSession makes a POST request`() = runBlocking {
        dataSource.getSession(DummyData.getSessionRequestModel)
        Truth.assertThat(mockWebServer.takeRequest().method).isEqualTo("POST")
    }

    @Test
    fun `check that getSession returns correct data`() = runBlocking {
        val response = dataSource.getSession(DummyData.getSessionRequestModel)
        val expectedResponse =
            responseAdapter<BaseResponseDTO<GetSessionDTO>, GetSessionDTO>().fromJson(
                getJson(
                    UrlConstants.GET_SESSION_SUCCESS_RESPONSE
                )
            )
        Truth.assertThat(expectedResponse).isEqualTo(response.body())
    }


    override fun setDispatcher(): Dispatcher {
        return ClientDispatcher()
    }

    override fun initializeDataSource(): ClientRemoteDataSourceImpl {
        return ClientRemoteDataSourceImpl(makeApiService(mockWebServer))
    }
}