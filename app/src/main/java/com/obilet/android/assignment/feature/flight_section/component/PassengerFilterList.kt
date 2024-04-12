package com.obilet.android.assignment.feature.flight_section.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.obilet.android.assignment.R

@Composable
fun PassengerFilterList(
    filterList: List<Int>,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(filterList) {
            PassengerFilterItem(filter = "$it ADULT")
            if (it != filterList.last()) {
                Divider(color = colorResource(id = R.color.divider_color), thickness = 0.3.dp)
            }
        }
    }
}

@Preview
@Composable
fun PassengerFilterListPrev() {
    PassengerFilterList(filterList = (0..5).toList())
}