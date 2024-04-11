package com.obilet.android.assignment.core.data.usecase

import com.google.common.truth.Truth
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date

class GenerateDateForBaseRequestModelUseCaseTest {

    private val generateDateForBaseRequestModel = GenerateDateForBaseRequestModelUseCase()
    @Test
    fun `check that generateDateForBaseRequestModel returns date as expected`() {
        val now = System.currentTimeMillis()
        val pattern = "yyyy-MM-dd'T'HH:mm"
        val currentDate = Date(now)
        val simpleDateFormat = SimpleDateFormat(pattern)
        val dateAsFormatted = simpleDateFormat.format(currentDate)
        val result = generateDateForBaseRequestModel()
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(dateAsFormatted).isEqualTo(result!!.substring(0, result.length - 3))
    }
}