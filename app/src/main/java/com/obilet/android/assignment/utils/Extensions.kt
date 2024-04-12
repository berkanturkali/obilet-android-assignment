package com.obilet.android.assignment.utils

import android.content.Context
import com.obilet.android.assignment.core.data.R
import com.obilet.android.assignment.core.model.UiText

fun UiText?.getThisOrDefaultErrorMessage(context: Context): String? {
    val uiText = this
        ?: UiText.StringResource(R.string.something_went_wrong_please_try_again_later)
    return uiText.asString(context)

}