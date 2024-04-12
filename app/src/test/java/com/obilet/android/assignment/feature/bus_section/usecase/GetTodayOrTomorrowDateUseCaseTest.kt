package com.obilet.android.assignment.feature.bus_section.usecase

import com.google.common.truth.Truth
import org.junit.Test

class GetTodayOrTomorrowDateUseCaseTest {

    private val getTodayOrTomorrowDate = GetTodayOrTomorrowDateUseCase()
    @Test
    fun `check that getTodayOrTomorrowDate returns today date as string format if isTomorrow is false`() {
        val actualDate = getTodayOrTomorrowDate(isTomorrow = false)
        val expectedDate = "12 April Friday"
        Truth.assertThat(actualDate).isEqualTo(expectedDate)
    }

    @Test
    fun `check that getTodayOrTomorrowDate returns tomorrow date as string format if isTomorrow is true`() {
        val actualDate = getTodayOrTomorrowDate(isTomorrow = true)
        val expectedDate = "13 April Saturday"
        Truth.assertThat(actualDate).isEqualTo(expectedDate)
    }
}