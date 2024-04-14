package com.obilet.android.assignment.feature.flight_section.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obilet.android.assignment.R
import com.obilet.android.assignment.core.model.flight_section.PassengerFilter

@Composable
fun PassengerFilterItem(
    filter: PassengerFilter,
    increaseButtonEnabled: Boolean,
    decreaseButtonEnabled: Boolean,
    increaseButtonBackgroundColor: Color,
    decreaseButtonBackgroundColor: Color,
    setIncreaseDecreaseStatusOfButtons: (PassengerFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    val title = stringResource(id = filter.title)
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily(Font(R.font.nunito_medium)),
                color = colorResource(id = R.color.primary_text_color),
                fontSize = 16.sp
            )
        ) {
            append(title.substringBefore("("))
        }
        if (title.contains("(")) {
            val startOfStyledText = title.indexOf("(")
            withStyle(
                style = SpanStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_medium)),
                    color = colorResource(id = R.color.primary_text_color)
                )
            ) {
                append(title.substring(startOfStyledText))
            }
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            text = annotatedString,
        )

        PassengerCounter(
            filter = filter,
            increaseButtonEnabled = increaseButtonEnabled,
            decreaseButtonEnabled = decreaseButtonEnabled,
            increaseButtonBackgroundColor = increaseButtonBackgroundColor,
            decreaseButtonBackgroundColor = decreaseButtonBackgroundColor,
            setIncreaseDecreaseStatusOfButtons = setIncreaseDecreaseStatusOfButtons,
        )
    }
}