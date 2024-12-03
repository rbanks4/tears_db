package com.gaming.android.tearsdatabase.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gaming.android.tearsdatabase.R

const val QUARTER = 1
const val HALF = 2
const val THREE_QUARTER = 3

@Composable
fun HeartMeter(count: Int, modifier: Modifier = Modifier) {
    val hearts = count / 4
    val remainder = count.rem(4)
    val size = 15.dp
    Row (modifier = modifier) {
        if(hearts > 0) {
            for (i in 1..hearts)
                Image(
                    modifier = Modifier.size(size),
                    painter = painterResource(id = R.drawable.heart_full),
                    contentDescription = "heart"
                )
        }
        else if(remainder == 0) {
            Text(modifier = Modifier,
                color = MaterialTheme.colorScheme.secondary,
                text = "$hearts",
                style = MaterialTheme.typography.bodySmall)
        }

        when(remainder) {
            QUARTER ->
                Image(modifier = Modifier.size(size),
                    painter = painterResource(id = R.drawable.heart_quarter),
                    contentDescription = "quarter heart")
            HALF ->
                Image(modifier = Modifier.size(size),
                    painter = painterResource(id = R.drawable.heart_half),
                    contentDescription = "half heart")
            THREE_QUARTER ->
                Image(modifier = Modifier.size(size),
                    painter = painterResource(id = R.drawable.heart_three_quarter),
                    contentDescription = "three quarter heart")

        }
    }
}

@Preview
@Composable
fun HeartMeterPreview() {
    HeartMeter(count = 7)
}