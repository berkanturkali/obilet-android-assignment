package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.obilet.android.assignment.core.model.BusJourney

@Composable
fun BusJourneys(
    journeyList: List<BusJourney>,
    modifier: Modifier = Modifier,
    onJourneyItemClick: (BusJourney) -> Unit,
) {

    LazyColumn(modifier = modifier) {
        items(journeyList, key = { it.id!! }) { busJourney ->
            JourneyItem(busJourney) {
                onJourneyItemClick(busJourney)
            }
        }
    }
}