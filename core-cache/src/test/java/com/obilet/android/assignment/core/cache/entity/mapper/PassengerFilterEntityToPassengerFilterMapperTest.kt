package com.obilet.android.assignment.core.cache.entity.mapper

import com.google.common.truth.Truth
import com.obilet.android.assignment.core.cache.utils.Dummy
import com.obilet.android.assignment.core.model.flight_section.PassengerFilter
import org.junit.Test

class PassengerFilterEntityToPassengerFilterMapperTest {

    private val passengerFilterEntityToPassengerFilterMapper =
        PassengerFilterEntityToPassengerFilterMapper()

    @Test
    fun `check that mapFromModel maps the entity as expected`() {
        val entity = Dummy.passengerFilterEntity
        val expectedFilter = passengerFilterEntityToPassengerFilterMapper.mapFromModel(entity)
        Truth.assertThat(expectedFilter).isEqualTo(PassengerFilter.ADULT)
    }
}