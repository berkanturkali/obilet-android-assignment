package com.obilet.android.assignment.feature.flight_section.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.obilet.android.assignment.R
import com.obilet.android.assignment.core.model.flight_section.PassengerFilter

@Composable
fun PassengerFilterList(
    filterList: List<PassengerFilter>,
    setIncreaseDecreaseStatusOfButtons: (PassengerFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        filterList.forEach { filter ->
            PassengerFilterItem(
                filter = filter,
                increaseButtonEnabled = filter.canIncrease,
                decreaseButtonEnabled = filter.canDecrease,
                increaseButtonBackgroundColor = if (filter.canIncrease) colorResource(id = R.color.on_primary) else Color.LightGray,
                decreaseButtonBackgroundColor = if (filter.canDecrease) colorResource(id = R.color.on_primary) else Color.LightGray,
                setIncreaseDecreaseStatusOfButtons = setIncreaseDecreaseStatusOfButtons
            )
            if (filter != filterList.last()) {
                Divider(color = colorResource(id = R.color.divider_color), thickness = 0.5.dp)
            }
        }
    }
}

@Preview
@Composable
fun PassengerFilterListPrev() {
    PassengerFilterList(
        filterList = PassengerFilter.entries.toList(),
        setIncreaseDecreaseStatusOfButtons = {}
    )
}