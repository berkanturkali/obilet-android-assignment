package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun JourneyTimeLineView(
    stopList: List<String>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
    ) {
        itemsIndexed(stopList) { index, stop ->
            val firstItem = index == 0
            val lastItem = index == stopList.lastIndex
            JourneyTimeLineItem(
                stop = stop,
                time = "10:00",
                firstItem = firstItem,
                lastItem = lastItem
            )
        }
    }
}

@Preview
@Composable
fun JourneyTimeLineViewPrev() {
    JourneyTimeLineView(stopList = (0..10).map { "Esenler Terminali" })
}