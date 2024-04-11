package com.obilet.android.assignment.core.data.usecase

import com.google.common.truth.Truth
import org.junit.Test

class GetDeviceLanguageUseCaseTest {

    private val getDeviceLanguage = GetDeviceLanguageUseCase()


    @Test
    fun `check that getDeviceLanguage return language as expected`() {
        val expectedResult = "en-EN"
        val actualResult = getDeviceLanguage()
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }
}