package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obilet.android.assignment.R

@Composable
fun BusType(
    type: String,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_chair),
            contentDescription = null,
            tint = colorResource(
                id = R.color.icon_primary_color
            )
        )

        Text(
            text = type,
            fontFamily = FontFamily(Font(R.font.nunito_medium)),
            color = colorResource(
                id = R.color.primary_text_color
            ),
            fontSize = 12.sp
        )
    }

}

@Preview
@Composable
fun BusTypePrev() {
    BusType(type = "2+2")
}