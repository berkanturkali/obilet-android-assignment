package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.obilet.android.assignment.core.model.Stop

@Composable
fun JourneyTimeLineView(
    stopList: List<Stop>,
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
                firstItem = firstItem,
                lastItem = lastItem
            )
        }
    }
}