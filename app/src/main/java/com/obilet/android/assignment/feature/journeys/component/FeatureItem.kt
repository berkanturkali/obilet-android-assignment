package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
fun FeatureItem(
    feature: String,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .border(
                0.3.dp,
                color = colorResource(id = R.color.divider_color),
                shape = CircleShape
            )
            .padding(horizontal = 4.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_time),
            contentDescription = null,
            tint = colorResource(
                id = R.color.icon_primary_color
            )
        )

        Text(
            text = feature,
            fontFamily = FontFamily(Font(R.font.nunito_medium)),
            color = colorResource(
                id = R.color.primary_text_color
            ),
            fontSize = 8.sp
        )

    }

}

@Preview
@Composable
fun FeatureItemPrev() {
    FeatureItem(feature = "Kablosuz Internet (WiFi)")
}