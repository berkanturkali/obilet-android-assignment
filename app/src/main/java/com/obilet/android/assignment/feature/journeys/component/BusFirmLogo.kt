package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.obilet.android.assignment.R

@Composable
fun BusFirmLogo(logo: Int?, modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(id = logo ?: R.drawable.ic_bus),
        contentDescription = null,
        modifier = modifier
    )
}