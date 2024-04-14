package com.obilet.android.assignment.feature.flight_section.usecase

import com.google.common.truth.Truth
import org.junit.Test

class RemoveParenthesesFromTheTitleUseCaseTest {


    private val removeParenthesesFromTheTitle = RemoveParenthesesFromTheTitleUseCase()
    @Test
    fun `check that removeParenthesesFromTheTitle returns string as expected`() {
        val title = "1 Student (12-24 Ages)"
        val actualResult = removeParenthesesFromTheTitle.invoke(title)
        val expectedResult = "1 Student"
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

}