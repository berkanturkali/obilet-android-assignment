package com.obilet.android.assignment.feature.flight_section.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obilet.android.assignment.R

@Composable
fun PassengerCounter(
    modifier: Modifier = Modifier,
    initialCount: Int = 0,
) {

    var count by rememberSaveable {
        mutableStateOf(initialCount)
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // region Remove button
        IconButton(
            onClick = {
                if (count - 1 >= 0) {
                    count -= 1
                }
            },
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.button_color),
                    shape = CircleShape
                )
                .size(28.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_remove),
                contentDescription = null,
                tint = colorResource(
                    id = R.color.on_primary
                )
            )
        }
        //endregion

        //region Counter

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
                color = colorResource(id = R.color.primary_text_color),
                fontSize = 20.sp
            )
        }

        //endregion

        // region Add button
        IconButton(
            onClick = {
                if (count + 1 <= 4) {
                    count += 1
                }
            },
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.button_color),
                    shape = CircleShape
                )
                .size(28.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = null,
                tint = colorResource(
                    id = R.color.on_primary
                )
            )
        }
        //endregion
    }

}

@Preview
@Composable
fun PassengerCounterPrev() {
    PassengerCounter()
}