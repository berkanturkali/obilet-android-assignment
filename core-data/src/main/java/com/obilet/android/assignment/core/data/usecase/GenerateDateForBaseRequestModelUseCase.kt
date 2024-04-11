package com.obilet.android.assignment.core.data.usecase

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GenerateDateForBaseRequestModelUseCase @Inject constructor() {
    companion object {
        private const val PATTERN = "yyyy-MM-dd'T'HH:mm:ss"
    }
    operator fun invoke(): String? {
        val now = System.currentTimeMillis()
        val simpleDateFormat = SimpleDateFormat(PATTERN, Locale.getDefault())
        val currentDate = Date(now)
        return try {
            simpleDateFormat.format(currentDate)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}