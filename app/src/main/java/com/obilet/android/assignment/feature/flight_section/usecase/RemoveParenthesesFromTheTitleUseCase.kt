package com.obilet.android.assignment.feature.flight_section.usecase

import javax.inject.Inject

class RemoveParenthesesFromTheTitleUseCase @Inject constructor() {
    operator fun invoke(title: String): String {
        return title.substringBefore(" (")
    }
}