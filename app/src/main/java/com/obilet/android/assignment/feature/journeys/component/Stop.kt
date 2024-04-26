package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obilet.android.assignment.R
import com.obilet.android.assignment.core.model.Stop

@Composable
fun Stop(
    stop: Stop,
    modifier: Modifier = Modifier
) {

    stop.name?.let {
        Text(
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 10.dp)
                .fillMaxWidth(),
            text = it,
            fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
            color = colorResource(
                id = R.color.primary_text_color
            ),
            fontSize = 8.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}