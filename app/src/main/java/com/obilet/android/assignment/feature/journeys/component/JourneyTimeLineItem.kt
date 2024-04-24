package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.obilet.android.assignment.R

@Composable
fun JourneyTimeLineItem(
    time: String,
    stop: String,
    modifier: Modifier = Modifier,
    firstItem: Boolean = false,
    lastItem: Boolean = false,
) {

    val lineColor = colorResource(id = R.color.primary_color)

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        ConstraintLayout(constraintSet = constraints(), modifier = Modifier.width(maxWidth).wrapContentHeight()) {

            Canvas(
                modifier = Modifier
                    .then(if (lastItem) Modifier.wrapContentHeight() else Modifier.height(18.dp))
                    .layoutId("line")
            ) {
                val startX = 0f
                val endX = 0f
                val startY = if (firstItem) size.height / 2f else 0f
                val endY = if (lastItem) 0f else size.height * 2f

                drawLine(
                    cap = StrokeCap.Round,
                    start = Offset(x = startX, y = startY),
                    end = Offset(x = endX, y = endY),
                    color = lineColor,
                    strokeWidth = 2f
                )
            }

            Box(
                modifier = Modifier
                    .background(color = lineColor, shape = CircleShape)
                    .size(14.dp)
                    .layoutId("indicator")
            )

            Text(
                modifier = Modifier.layoutId("indicatorContent"),
                text = time,
                fontFamily = FontFamily(Font(R.font.nunito_semi_bold)),
                color = colorResource(
                    id = R.color.on_primary
                ),
                fontSize = 4.sp,
                textAlign = TextAlign.Center
            )

            Stop(stop = stop, modifier = Modifier.layoutId("content"))

        }
    }

}

private fun constraints(
): ConstraintSet {
    return ConstraintSet {
        val line = createRefFor("line")
        val indicator = createRefFor("indicator")
        val indicatorContent = createRefFor("indicatorContent")
        val content = createRefFor("content")

        constrain(line) {
            top.linkTo(indicator.top)
            bottom.linkTo(indicator.bottom)
            start.linkTo(indicator.start)
            end.linkTo(indicator.end)
        }

        constrain(indicator) {
            top.linkTo(content.top)
            bottom.linkTo(content.bottom)
            start.linkTo(parent.start)
            end.linkTo(content.start)
        }

        constrain(indicatorContent) {
            top.linkTo(indicator.top)
            bottom.linkTo(indicator.bottom)
            start.linkTo(indicator.start)
            end.linkTo(indicator.end)
        }

        constrain(content) {
            start.linkTo(indicator.end)
            top.linkTo(parent.top)
            end.linkTo(parent.end, margin = 8.dp)
            width = Dimension.fillToConstraints
        }
    }
}