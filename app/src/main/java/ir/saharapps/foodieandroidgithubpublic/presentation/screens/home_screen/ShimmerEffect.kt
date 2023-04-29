package ir.saharapps.foodieapp.presentation.screens.home_screen

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import ir.saharapps.foodieapp.presentation.ui.theme.ShimmerLightGray
import ir.saharapps.foodieapp.presentation.ui.theme.ShimmerMediumGray


@Composable
fun ShimmerFav() {
    val transition = rememberInfiniteTransition()
    val alpha by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ))

    Surface(
        modifier = Modifier
            .requiredWidth(220.dp)
            .height(250.dp)
            .padding(16.dp),
        color = ShimmerLightGray,
        shape = RoundedCornerShape(size = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .alpha(alpha)
                    .height(30.dp)
                    .fillMaxWidth(0.5f),
                color = ShimmerMediumGray,
                shape = RoundedCornerShape(size = 4.dp)
            ) {}
            Spacer(modifier = Modifier.padding(all = 4.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp)
            ) {
                repeat(2){
                    Surface(
                        modifier = Modifier
                            .alpha(alpha)
                            .size(20.dp),
                        color = ShimmerMediumGray,
                        shape = RoundedCornerShape(size = 4.dp)
                    ) {}
                    Spacer(modifier = Modifier.padding(all = 4.dp))
                }
            }
        }
    }
}

@Composable
fun ShimmerMenu() {
    val transition = rememberInfiniteTransition()
    val alpha by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ))

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 24.dp, bottom = 24.dp)
    ) {
        repeat(6){
            Surface(
                modifier = Modifier
                    .alpha(alpha)
                    .size(50.dp),
                color = ShimmerMediumGray,
                shape = RoundedCornerShape(size = 8.dp)
            ) {}
            Spacer(modifier = Modifier.padding(all = 4.dp))
        }
    }
}

@Composable
fun ShimmerFoodItem() {
    val transition = rememberInfiniteTransition()
    val alpha by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ))

    Surface(
        modifier = Modifier
            .alpha(alpha)
            .fillMaxWidth()
            .height(150.dp)
            .padding(start= 16.dp, end=16.dp, top=4.dp, bottom =4.dp),
        color = ShimmerLightGray,
        shape = RoundedCornerShape(size = 24.dp)
    ) {
        Row() {
            Surface(
                modifier = Modifier
                    .alpha(alpha)
                    .padding(16.dp)
                    .requiredWidth(100.dp)
                    .requiredHeight(100.dp),
                color = ShimmerMediumGray,
                shape = RoundedCornerShape(size = 24.dp)
            ) {

            }
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Surface(
                    modifier = Modifier
                        .alpha(alpha)
                        .height(30.dp)
                        .fillMaxWidth(0.5f)
                        .padding(top = 16.dp),
                    color = ShimmerMediumGray,
                    shape = RoundedCornerShape(size = 4.dp)
                ) {}
                Surface(
                    modifier = Modifier
                        .alpha(alpha)
                        .height(60.dp)
                        .fillMaxWidth(0.5f)
                        .padding(top = 16.dp),
                    color = ShimmerMediumGray,
                    shape = RoundedCornerShape(size = 4.dp)
                ) {}
                Surface(
                    modifier = Modifier
                        .alpha(alpha)
                        .height(30.dp)
                        .fillMaxWidth(0.2f)
                        .padding(top = 16.dp),
                    color = ShimmerMediumGray,
                    shape = RoundedCornerShape(size = 4.dp)
                ) {}
            }
        }
    }
}
