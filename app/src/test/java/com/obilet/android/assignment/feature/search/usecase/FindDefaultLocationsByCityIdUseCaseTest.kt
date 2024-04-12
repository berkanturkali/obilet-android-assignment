package com.obilet.android.assignment.feature.search.usecase

import com.google.common.truth.Truth
import com.obilet.android.assignment.core.model.bus_location.BusLocation
import org.junit.Test

class FindDefaultLocationsByCityIdUseCaseTest {


    private val findDefaultLocationsByCityId = FindDefaultLocationsByCityIdUseCase()

    @Test
    fun `check that findDefaultLocationsByCityId returns null pair if the destination is null`() {
        val locationList = listOf(BusLocation(cityId = 2), BusLocation(cityId = 2))
        val actualPair = findDefaultLocationsByCityId(locationList)
        val expectedPair = Pair(null, null)
        Truth.assertThat(actualPair).isEqualTo(expectedPair)
    }

    @Test
    fun `check that findDefaultLocationsByCityId returns pair that contains origin and destination objects if the city id of the two are differs`() {
        val locationList = (0..10).map { BusLocation(cityId = it) }
        val actualPair = findDefaultLocationsByCityId(locationList)
        val expectedPair = Pair(BusLocation(cityId = 0), BusLocation(cityId = 1))
        Truth.assertThat(actualPair).isEqualTo(expectedPair)
    }

}