package com.obilet.android.assignment.core.data.mapper.getsession

import com.google.common.truth.Truth
import com.obilet.android.assignment.core.data.utils.DummyData
import org.junit.Test

class SessionRemoteResponseMapperTest {


    private val mapper = SessionRemoteResponseMapper()

    @Test
    fun `check that mapFromModel maps data as expected`() {
        val model = DummyData.sessionDTO
        val domainModel = mapper.mapFromModel(model)
        Truth.assertThat(domainModel.sessionId).isEqualTo(model.sessionId)
        Truth.assertThat(domainModel.deviceId).isEqualTo(model.deviceId)
    }
}