package com.obilet.android.assignment.feature.flight_section.component


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obilet.android.assignment.R
import com.obilet.android.assignment.core.model.flight_section.PassengerFilter

private const val TAG = "PassengerCounter"

@Composable
fun PassengerCounter(
    filter: PassengerFilter,
    increaseButtonEnabled: Boolean,
    decreaseButtonEnabled: Boolean,
    increaseButtonBackgroundColor: Color,
    decreaseButtonBackgroundColor: Color,
    setIncreaseDecreaseStatusOfButtons: (PassengerFilter) -> Unit,
    modifier: Modifier = Modifier,
) {

    var count by rememberSaveable {
        mutableStateOf(filter.count)
    }

    Row(
        modifier = modifier
            .background(
                colorResource(id = R.color.primary_color),
                shape = CircleShape
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // region Decrease button
        IconButton(
            enabled = decreaseButtonEnabled,
            onClick = {
                if (filter.canDecrease) {
                    count -= 1
                    filter.count = count
                    setIncreaseDecreaseStatusOfButtons(filter)
                }
            }, modifier = Modifier
                .size(18.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_remove),
                contentDescription = null,
                tint = decreaseButtonBackgroundColor
            )
        }
        //endregion

        //region Counter

        Box(
            modifier = modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {

            AnimatedContent(targetState = count, transitionSpec = {
                if (targetState > initialState) {
                    (slideInVertically { height ->
                        -height
                    }).togetherWith(slideOutVertically { height -> height })
                } else {
                    (slideInVertically { height ->
                        height
                    }).togetherWith(slideOutVertically { height -> -height })
                }
            }, label = "") { targetCount ->
                Text(
                    text = targetCount.toString(),
                    fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
                    color = colorResource(id = R.color.on_primary),
                    fontSize = 16.sp,
                )
            }
        }

        //endregion

        // region Increase button
        IconButton(
            enabled = increaseButtonEnabled,
            onClick = {
                if (filter.canIncrease) {
                    count += 1
                    filter.count = count
                    setIncreaseDecreaseStatusOfButtons(filter)

                }
            }, modifier = Modifier
                .size(18.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = null,
                tint = increaseButtonBackgroundColor
            )
        }
        //endregion
    }
}

@Preview
@Composable
fun PassengerCounterPrev() {
    PassengerCounter(
        filter = PassengerFilter.ADULT,
        increaseButtonBackgroundColor = Color.White,
        decreaseButtonBackgroundColor = Color.White,
        increaseButtonEnabled = true,
        decreaseButtonEnabled = false,
        setIncreaseDecreaseStatusOfButtons = {}
    )
}