package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.obilet.android.assignment.R

@Composable
fun JourneyTime(
    time: String,
    modifier: Modifier = Modifier
) {

    Text(
        modifier = modifier,
        text = time,
        fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
        fontSize = 14.sp,
        color = colorResource(
            id = R.color.secondary_text_color
        ),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun JourneyTimePrev() {
    JourneyTime(time = "09:00")
}