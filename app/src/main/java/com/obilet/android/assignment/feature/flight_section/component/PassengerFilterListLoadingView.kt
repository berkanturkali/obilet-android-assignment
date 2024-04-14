package com.obilet.android.assignment.feature.flight_section.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.obilet.android.assignment.R
import com.obilet.android.assignment.utils.shimmerModifier

@Composable
fun PassengerFilterListLoadingView(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(5) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .height(28.dp)
                        .shimmerModifier()
                )
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .width(60.dp)
                        .shimmerModifier(shape = CircleShape)
                )
            }
            if (it != 5) {
                Divider(color = colorResource(R.color.divider_color))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PassengerFilterListLoadingViewPrev() {
    PassengerFilterListLoadingView()
}