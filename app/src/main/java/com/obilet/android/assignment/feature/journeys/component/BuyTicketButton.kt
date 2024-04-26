package com.obilet.android.assignment.feature.journeys.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obilet.android.assignment.R

@Composable
fun BuyTicketButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
) {
    Surface(
        modifier = modifier.clickable {
            onButtonClick()
        },
        color = colorResource(
            R.color.button_color
        ),
        contentColor = colorResource(id = R.color.button_text_color),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 6.dp),
            text = stringResource(id = R.string.buy_ticket),
            fontFamily = FontFamily(Font(R.font.nunito_medium)),
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun BuyTicketButtonPrev() {
    BuyTicketButton {

    }
}