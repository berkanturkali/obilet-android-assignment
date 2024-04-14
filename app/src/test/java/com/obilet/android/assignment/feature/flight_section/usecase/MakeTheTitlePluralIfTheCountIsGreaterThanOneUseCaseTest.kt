package com.obilet.android.assignment.feature.flight_section.usecase

import com.google.common.truth.Truth
import org.junit.Test

class MakeTheTitlePluralIfTheCountIsGreaterThanOneUseCaseTest {

    private val makeTheTitlePluralIfTheCountIsGreaterThanOneUseCase =
        MakeTheTitlePluralIfTheCountIsGreaterThanOneUseCase()

    @Test
    fun `check that makeTheTitlePluralIfTheCountIsGreaterThanOne appends 's' suffix at the end of a string if the count is greater than 1`() {
        val count = 2
        val title = "Student"
        val modifiedTitle =
            makeTheTitlePluralIfTheCountIsGreaterThanOneUseCase(count = count, title = title)
        val expectedResult = "Students"
        Truth.assertThat(modifiedTitle).isEqualTo(expectedResult)
    }

    @Test
    fun `check that makeTheTitlePluralIfTheCountIsGreaterThanOne returns title as it is if the count not greater than 1`() {
        val count = 1
        val title = "Student"
        val modifiedTitle =
            makeTheTitlePluralIfTheCountIsGreaterThanOneUseCase(count = count, title = title)
        val expectedResult = "Student"
        Truth.assertThat(modifiedTitle).isEqualTo(expectedResult)
    }

    @Test
    fun `check that makeTheTitlePluralIfTheCountIsGreaterThanOne formats the 'Child' into plural form as expected`() {
        val count = 2
        val title = "Child"
        val modifiedTitle =
            makeTheTitlePluralIfTheCountIsGreaterThanOneUseCase(count = count, title = title)
        val expectedResult = "Children"
        Truth.assertThat(modifiedTitle).isEqualTo(expectedResult)
    }

    @Test
    fun `check that makeTheTitlePluralIfTheCountIsGreaterThanOne removes last char and appends 'ies' plural suffix if the title ends with 'y'`() {
        val count = 2
        val title = "Baby"
        val modifiedTitle =
            makeTheTitlePluralIfTheCountIsGreaterThanOneUseCase(count = count, title = title)
        val expectedResult = "Babies"
        Truth.assertThat(modifiedTitle).isEqualTo(expectedResult)
    }
}