package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Features(
    features: List<String>,
    modifier: Modifier = Modifier
) {

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
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
            "Molalı",
            "220 Voltluk Priz",
            "USB ile Şarj İmkanı",
            "Kablosuz Internet (WiFi)",
            "Koltuk ekraninda TV yayını",
            "Koltuk ekraninda müzik yayını",
            "Cep telefonu kullanımı serbest",
            "Rahat Koltuk"
        )
    )
}