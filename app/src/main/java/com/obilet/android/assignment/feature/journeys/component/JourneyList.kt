package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun JourneyList(
    journeyList: List<String>,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier) {
        items(journeyList) { journey ->
            JourneyItem()
        }
    }
}

@Preview
@Composable
fun JourneyListPrev() {
    JourneyList(journeyList = (0..10).map { it.toString() })
}
