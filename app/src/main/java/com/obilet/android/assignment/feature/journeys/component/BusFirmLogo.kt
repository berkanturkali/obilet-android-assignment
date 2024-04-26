package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun BusFirmLogo(url: String?, modifier: Modifier = Modifier) {

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .crossfade(true)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = null,
        modifier = modifier.width(70.dp).height(35.dp),
        contentScale = ContentScale.FillBounds
    )
}