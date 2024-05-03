package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.obilet.android.assignment.R
import com.obilet.android.assignment.core.model.Feature

@Composable
fun FeatureItem(
    feature: Feature,
    modifier: Modifier = Modifier
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(feature.imageUrl)
            .decoderFactory(SvgDecoder.Factory())
            .crossfade(true)
            .build()
    )

    Row(
        modifier = modifier
            .border(
                0.3.dp,
                color = colorResource(id = R.color.button_color),
                shape = CircleShape
            )
            .padding(horizontal = 4.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        feature.imageUrl?.let {
            if (painter.state !is AsyncImagePainter.State.Error) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    colorFilter = ColorFilter.tint(
                        color = colorResource(
                            id = R.color.icon_primary_color
                        )
                    )
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_image),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = colorResource(
                        id = R.color.icon_primary_color
                    )
                )
            }
        }

        feature.name?.let { name ->
            Text(
                text = name,
                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                color = colorResource(
                    id = R.color.primary_text_color
                ),
                fontSize = 10.sp
            )

        }
    }

}