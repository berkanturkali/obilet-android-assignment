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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.obilet.android.assignment.R

const val EXPAND_ANIMATION_DURATION = 100

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun JourneyItem(
    modifier: Modifier = Modifier
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
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth(),
            ) {
                val (logo, time, price, feature, duration) = createRefs()
                BusFirmLogo(logo = R.drawable.ic_bus, modifier = Modifier.constrainAs(logo) {
                    top.linkTo(parent.top, margin = 8.dp)
                    start.linkTo(parent.start)
                })

                JourneyTime(time = "09:00", modifier = Modifier.constrainAs(time) {
                    top.linkTo(logo.top)
                    bottom.linkTo(logo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

                JourneyPrice(price = "699 TL", modifier = Modifier.constrainAs(price) {
                    top.linkTo(time.top)
                    bottom.linkTo(time.bottom)
                    end.linkTo(parent.end)
                })

                FeatureItem(feature = "2 + 1", modifier = Modifier.constrainAs(feature) {
                    top.linkTo(logo.bottom, margin = 22.dp)
                    start.linkTo(logo.start)
                })
                Duration(duration = "7s 30dk", modifier = Modifier.constrainAs(duration) {
                    top.linkTo(feature.top)
                    bottom.linkTo(feature.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            }

            Spacer(modifier = Modifier.height(8.dp))


            OriginAndDestination(originAndDestination = "İzmir Otogarı > Ankara (Aşti) Otogarı")

            Spacer(modifier = Modifier.height(2.dp))

            Divider(color = colorResource(id = R.color.divider_color), thickness = 0.5.dp)

            Spacer(modifier = Modifier.height(6.dp))

            Features(
                features = listOf(
                    "Molalı",
                    "220 Voltluk Priz",
                    "USB ile Şarj İmkanı",
                    "Kablosuz Internet (WiFi)",
                    "Koltuk ekraninda TV yayını",
                    "Koltuk ekraninda müzik yayını",
                    "Cep telefonu kullanımı serbest",
                    "Rahat Koltuk"
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Divider(color = colorResource(id = R.color.divider_color), thickness = 0.5.dp)

            Spacer(modifier = Modifier.height(6.dp))

            ExpandableContent(visible = expanded)

            Spacer(modifier = Modifier.height(2.dp))

            Box(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {

                BuyTicketButton(modifier = Modifier.align(Alignment.Center)) {

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
        JourneyTimeLineView(stopList = (0..5).map { "Esenler Terminali" })
    }
}

@Preview
@Composable
fun JourneyItemPrev() {
    JourneyItem()
}
