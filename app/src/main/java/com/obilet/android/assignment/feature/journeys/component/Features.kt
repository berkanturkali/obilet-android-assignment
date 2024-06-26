package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.obilet.android.assignment.core.model.Feature

@Composable
fun Features(
    features: List<Feature>,
    modifier: Modifier = Modifier
) {

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(features) { feature ->
            FeatureItem(feature = feature)

        }
    }

}

@Preview
@Composable
fun FeaturesPrev() {
    Features(
        features = listOf(
            Feature(
                "220 Voltluk Priz"
            )
        )
    )
}