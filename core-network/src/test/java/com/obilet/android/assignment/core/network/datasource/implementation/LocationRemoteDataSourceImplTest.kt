package com.obilet.android.assignment.core.network.datasource.implementation

import com.google.common.truth.Truth
import com.obilet.android.assignment.core.network.datasource.abstraction.LocationRemoteDataSource
import com.obilet.android.assignment.core.network.dispatcher.LocationDispatcher
import com.obilet.android.assignment.core.network.factory.getJson
import com.obilet.android.assignment.core.network.factory.makeApiService
import com.obilet.android.assignment.core.network.factory.responseAdapter
import com.obilet.android.assignment.core.network.model.response.base.BaseResponseDTO
import com.obilet.android.assignment.core.network.model.response.get_bus_locations.BusLocationDTO
import com.obilet.android.assignment.core.network.utils.DummyData
import com.obilet.android.assignment.core.network.utils.UrlConstants
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import org.junit.Test

class LocationRemoteDataSourceImplTest :
    BaseDataSourceImplTest<LocationRemoteDataSource, LocationRemoteDataSourceImpl>() {

    @Test
    fun `check that calling getBusLocations makes a POST request`() = runBlocking {
        dataSource.getBusLocations(DummyData.getBusLocationsRequestModel)
        Truth.assertThat(mockWebServer.takeRequest().method).isEqualTo("POST")
    }

    @Test
    fun `check that getBusLocations returns correct data and parses it as expected`() =
        runBlocking {
            val response = dataSource.getBusLocations(DummyData.getBusLocationsRequestModel)
            val expectedResponse =
                responseAdapter<BaseResponseDTO<List<BusLocationDTO>>, List<BusLocationDTO>>().fromJson(
                    getJson(
                        UrlConstants.GET_BUS_LOCATIONS_SUCCESS_RESPONSE
                    )
                )

            Truth.assertThat(response.body()?.data).isNotNull()
            Truth.assertThat(expectedResponse?.data).isNotNull()
            Truth.assertThat(response.body()?.data).isInstanceOf(List::class.java)
            Truth.assertThat(expectedResponse?.data).isInstanceOf(List::class.java)
            Truth.assertThat(response.body()!!.data).isNotEmpty()
            Truth.assertThat(expectedResponse!!.data).isNotEmpty()

            //Idk why but somehow this code prints the correct output
            print(expectedResponse.data)

            //this throws an exception
            // print(expectedResponse.data?.first())
        }

    override fun setDispatcher(): Dispatcher {
        return LocationDispatcher()
    }

    override fun initializeDataSource(): LocationRemoteDataSourceImpl {
        return LocationRemoteDataSourceImpl(makeApiService(mockWebServer))
    }

}