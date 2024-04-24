package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obilet.android.assignment.R

@Composable
fun ReviewButton(
    expanded: Boolean,
    degrees: Float,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
) {

    val buttonText =
        if (expanded) stringResource(id = R.string.close) else stringResource(id = R.string.review)

    Row(
        modifier = modifier.padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier
                .rotate(degrees)
                .clickable {
                    onButtonClick()
                },
            painter = painterResource(id = R.drawable.ic_down),
            contentDescription = null,
            tint = colorResource(
                id = R.color.icon_primary_color
            )
        )

        Text(
            text = buttonText,
            fontFamily = FontFamily(Font(R.font.nunito_medium)),
            color = colorResource(
                id = R.color.primary_text_color
            ),
            fontSize = 10.sp
        )
    }

}

@Preview
@Composable
fun ReviewButtonPrev() {
    ReviewButton(degrees = 0f, expanded = false) {}
}