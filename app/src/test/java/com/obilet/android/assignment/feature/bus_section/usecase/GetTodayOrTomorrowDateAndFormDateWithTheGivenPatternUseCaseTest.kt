package com.obilet.android.assignment.feature.bus_section.usecase

import com.google.common.truth.Truth
import org.junit.Test

class GetTodayOrTomorrowDateUseCaseTestGetTodayOrTomorrowDateUseCaseTest {

    private val getTodayOrTomorrowDate = GetTodayOrTomorrowDateUseCase()
    private val formatDateWithTheGivenPatternUseCase = FormatDateWithTheGivenPatternUseCase()

    @Test
    fun `check that getTodayOrTomorrowDate returns today date as string format if isTomorrow is false`() {
        val actualDate = getTodayOrTomorrowDate(isTomorrow = false)
        val actual = formatDateWithTheGivenPatternUseCase(actualDate)
        val expectedDate = "16 April Tuesday"
        Truth.assertThat(actual).isEqualTo(expectedDate)
    }

    @Test
    fun `check that getTodayOrTomorrowDate returns tomorrow date as string format if isTomorrow is true`() {
        val actualDate = getTodayOrTomorrowDate(isTomorrow = true)
        val actual = formatDateWithTheGivenPatternUseCase(actualDate)
        val expectedDate = "17 April Wednesday"
        Truth.assertThat(actual).isEqualTo(expectedDate)
    }
}