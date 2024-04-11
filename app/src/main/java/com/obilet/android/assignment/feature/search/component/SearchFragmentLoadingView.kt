package com.obilet.android.assignment.feature.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.obilet.android.assignment.utils.shimmerModifier

@Composable
fun SearchFragmentLoadingView(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {

            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (appbar, tabLayout) = createRefs()
                ShimmerItem(
                    modifier = Modifier
                        .constrainAs(appbar) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .height(150.dp)
                )
                ShimmerItem(
                    shape = CircleShape,
                    modifier = Modifier
                        .constrainAs(tabLayout) {
                            top.linkTo(appbar.bottom)
                            bottom.linkTo(appbar.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .height(40.dp)
                        .padding(horizontal = 40.dp)
                )
            }

        }

        items(2) {
            ShimmerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
        }

        item {
            ShimmerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
        }

        item {
            ShimmerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
        }

        item {
            ShimmerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(horizontal = 64.dp)
            )
        }

        item {
            ShimmerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .padding(horizontal = 8.dp)
            )
        }

        item {
            ShimmerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .padding(horizontal = 64.dp)
            )
        }
    }

}

@Composable
fun ShimmerItem(modifier: Modifier = Modifier, shape: Shape = RoundedCornerShape(8.dp)) {
    Box(
        modifier = modifier
            .shimmerModifier(shape = shape)
    )
}

@Preview
@Composable
fun SearchFragmentLoadingViewPrev() {
    SearchFragmentLoadingView()
}