package com.obilet.android.assignment.feature.journeys.component

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.obilet.android.assignment.R
import com.obilet.android.assignment.core.model.BusJourney
import com.obilet.android.assignment.core.model.Stop

const val EXPAND_ANIMATION_DURATION = 100

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun JourneyItem(
    journey: BusJourney,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }

    val transition = updateTransition(transitionState, label = "")

    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    }, label = "") {
        if (expanded) 180f else 0f
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth(),
            ) {
                val (logo, time, price, busType, duration) = createRefs()
                BusFirmLogo(url = journey.busFirmLogoUrl, modifier = Modifier.constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })

                journey.journeyDeparture?.let { departureTime ->
                    JourneyTime(
                        time = departureTime,
                        modifier = Modifier.constrainAs(time) {
                            top.linkTo(logo.top)
                            bottom.linkTo(logo.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        })
                }

                journey.journeyPrice?.let { journeyPrice ->

                    JourneyPrice(price = journeyPrice, modifier = Modifier.constrainAs(price) {
                        top.linkTo(time.top)
                        bottom.linkTo(time.bottom)
                        end.linkTo(parent.end)
                    })
                }

                journey.busType?.let { type ->

                    BusType(type = type, modifier = Modifier.constrainAs(busType) {
                        top.linkTo(logo.bottom, margin = 12.dp)
                        start.linkTo(logo.start)
                    })

                }
                journey.journeyDuration?.let { journeyDuration ->
                    Duration(duration = journeyDuration, modifier = Modifier.constrainAs(duration) {
                        top.linkTo(busType.top)
                        bottom.linkTo(busType.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })

                }
            }

            Spacer(modifier = Modifier.height(8.dp))



            OriginAndDestination(originAndDestination = "${journey.journeyOrigin} > ${journey.journeyDestination}")

            Spacer(modifier = Modifier.height(2.dp))

            Divider(color = colorResource(id = R.color.divider_color), thickness = 0.5.dp)

            Spacer(modifier = Modifier.height(6.dp))

            if (!journey.features.isNullOrEmpty()) {

                Features(
                    features = journey.features!!,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Divider(color = colorResource(id = R.color.divider_color), thickness = 0.5.dp)
            }

            Spacer(modifier = Modifier.height(6.dp))

            ExpandableContent(visible = expanded, stops = journey.stops ?: emptyList())

            Spacer(modifier = Modifier.height(2.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {

                BuyTicketButton(modifier = Modifier.align(Alignment.Center)) {
                    onItemClick()
                }
                ReviewButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    degrees = arrowRotationDegree,
                    expanded = expanded,
                ) { expanded = !expanded }
            }


        }
    }

}

@Composable
private fun ExpandableContent(
    visible: Boolean,
    stops: List<Stop>,
) {
    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(100)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(100)
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(100)
        ) + fadeOut(
            animationSpec = tween(100)
        )
    }

    AnimatedVisibility(visible = visible, enter = enterTransition, exit = exitTransition) {
        JourneyTimeLineView(stopList = stops)
    }
}
