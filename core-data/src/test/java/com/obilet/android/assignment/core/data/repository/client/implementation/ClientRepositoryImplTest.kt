package com.obilet.android.assignment.core.data.repository.client.implementation

import com.google.common.truth.Truth
import com.obilet.android.assignment.core.data.R
import com.obilet.android.assignment.core.data.datasource.FakeClientRemoteDataSource
import com.obilet.android.assignment.core.data.mapper.getsession.SessionRemoteResponseMapper
import com.obilet.android.assignment.core.data.repository.client.abstraction.ClientRepository
import com.obilet.android.assignment.core.data.utils.DummyData
import com.obilet.android.assignment.core.model.Resource
import com.obilet.android.assignment.core.model.UiText
import com.obilet.android.assignment.core.network.datasource.abstraction.ClientRemoteDataSource
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ClientRepositoryImplTest {

    private lateinit var clientRemoteDataSource: ClientRemoteDataSource

    private val sessionRemoteResponseMapper = SessionRemoteResponseMapper()

    private lateinit var repository: ClientRepository

    @Before
    fun setup() {
        clientRemoteDataSource = FakeClientRemoteDataSource()
        repository = ClientRepositoryImpl(
            remoteDataSource = clientRemoteDataSource,
            sessionRemoteResponseMapper = sessionRemoteResponseMapper
        )
    }

    @Test
    fun `check that getSession emits loading initially`() = runBlocking {
        val resourceFlow = repository.getSession(body = DummyData.getSessionRequestModel)
        val resource = resourceFlow.toList().firstOrNull()
        Truth.assertThat(resource).isNotNull()
        Truth.assertThat(resource).isInstanceOf(Resource.Loading::class.java)
    }

    @Test
    fun `check that getSession emits success resource if the response is success, response status success also the data is not null`() =
        runBlocking {
            val dataSource = clientRemoteDataSource as FakeClientRemoteDataSource
            dataSource.setThrowException(false)
            dataSource.setReturnError(false)
            dataSource.setReturnSuccessResponseButNull(false)
            val resourceFlow = repository.getSession(DummyData.getSessionRequestModel)
            val resource = resourceFlow.toList()[1]
            Truth.assertThat(resource).isNotNull()
            Truth.assertThat(resource).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(resource.data).isNotNull()
            Truth.assertThat(resource.data?.sessionId).isEqualTo(DummyData.sessionDTO.sessionId)
            Truth.assertThat(resource.data?.deviceId).isEqualTo(DummyData.sessionDTO.deviceId)
        }

    @Test
    fun `check that getSession emits error resource if the response is success and but the response body is null`() =
        runBlocking {
            val dataSource = clientRemoteDataSource as FakeClientRemoteDataSource
            dataSource.setThrowException(false)
            dataSource.setReturnError(false)
            dataSource.setReturnSuccessResponseButNull(true)
            val resourceFlow = repository.getSession(DummyData.getSessionRequestModel)
            val resource = resourceFlow.toList()[1]
            Truth.assertThat(resource).isNotNull()
            Truth.assertThat(resource).isInstanceOf(Resource.Error::class.java)
            Truth.assertThat((resource as Resource.Error).error)
                .isInstanceOf(UiText.StringResource::class.java)
            Truth.assertThat((resource.error as UiText.StringResource).resId)
                .isEqualTo(R.string.no_result_found)

        }

    @Test
    fun `check that getSession emits success resource if the response is success and the response body is not null but the data is null`() =
        runBlocking {
            val dataSource = clientRemoteDataSource as FakeClientRemoteDataSource
            dataSource.setReturnSuccessResponseWithBaseResponseButNullData(true)
            dataSource.setThrowException(false)
            dataSource.setReturnError(false)
            dataSource.setReturnSuccessResponseButNull(false)
            val resourceFlow = repository.getSession(DummyData.getSessionRequestModel)
            val resource = resourceFlow.toList()[1]
            Truth.assertThat(resource).isNotNull()
            Truth.assertThat(resource).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat((resource as Resource.Success).data).isNull()

        }

    @Test
    fun `check that getSession emits error resource if the response is not success`() =
        runBlocking {
            val dataSource = clientRemoteDataSource as FakeClientRemoteDataSource
            dataSource.setReturnSuccessResponseWithBaseResponseButNullData(true)
            dataSource.setThrowException(false)
            dataSource.setReturnError(true)
            dataSource.setReturnSuccessResponseButNull(false)
            val resourceFlow = repository.getSession(DummyData.getSessionRequestModel)
            val resource = resourceFlow.toList()[1]
            Truth.assertThat(resource).isNotNull()
            Truth.assertThat(resource).isInstanceOf(Resource.Error::class.java)
            Truth.assertThat((resource as Resource.Error).error)
                .isInstanceOf(UiText.StringResource::class.java)
            Truth.assertThat((resource.error as UiText.StringResource).resId)
                .isEqualTo(R.string.something_went_wrong)
        }

    @Test
    fun `check that getSession emits error resource with the correct message if the safeApiCall throws io exception`() =
        runBlocking {
            val dataSource = clientRemoteDataSource as FakeClientRemoteDataSource
            dataSource.setReturnSuccessResponseWithBaseResponseButNullData(true)
            dataSource.setThrowException(true)
            dataSource.setReturnError(false)
            dataSource.setReturnSuccessResponseButNull(false)
            dataSource.setException(exception = IOException())
            val resourceFlow = repository.getSession(DummyData.getSessionRequestModel)
            val resource = resourceFlow.toList()[1]
            Truth.assertThat(resource).isNotNull()
            Truth.assertThat(resource).isInstanceOf(Resource.Error::class.java)
            Truth.assertThat((resource as Resource.Error).error)
                .isInstanceOf(UiText.StringResource::class.java)
            Truth.assertThat((resource.error as UiText.StringResource).resId)
                .isEqualTo(R.string.check_your_connection)
        }

    @Test
    fun `check that getSession emits error resource with the correct message if the safeApiCall throws exception`() =
        runBlocking {
            val dataSource = clientRemoteDataSource as FakeClientRemoteDataSource
            dataSource.setReturnSuccessResponseWithBaseResponseButNullData(true)
            dataSource.setThrowException(true)
            dataSource.setReturnError(false)
            dataSource.setReturnSuccessResponseButNull(false)
            dataSource.setException(exception = Exception())
            val resourceFlow = repository.getSession(DummyData.getSessionRequestModel)
            val resource = resourceFlow.toList()[1]
            Truth.assertThat(resource).isNotNull()
            Truth.assertThat(resource).isInstanceOf(Resource.Error::class.java)
            Truth.assertThat((resource as Resource.Error).error)
                .isInstanceOf(UiText.StringResource::class.java)
            Truth.assertThat((resource.error as UiText.StringResource).resId)
                .isEqualTo(R.string.something_went_wrong)
        }

}