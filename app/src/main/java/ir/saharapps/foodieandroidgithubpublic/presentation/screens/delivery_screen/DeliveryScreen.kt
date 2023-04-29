package ir.saharapps.foodieapp.presentation.screens.delivery_screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor
import ir.saharapps.foodieapp.R
import ir.saharapps.foodieapp.domain.model.BottomBarScreen
import ir.saharapps.foodieapp.navigation.Screen
import ir.saharapps.foodieapp.presentation.ui.theme.secondColor
import kotlinx.coroutines.delay


@Composable
fun DeliveryScreen(
    navController: NavHostController,
    deliveryViewModel: DeliveryViewModel = hiltViewModel()
) {
    BackHandler(true) {
        navController.popBackStack()
        navController.navigate(BottomBarScreen.HomePage.route)
    }

    Column(
        modifier = Modifier
            .background(secondColor)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var animationPlayed by remember { mutableStateOf(false) }
        var deliveryImage by remember { mutableStateOf(R.drawable.delivery2) }
        var deliveryTime by remember { mutableStateOf(30) }


        val currentPercentage = animateFloatAsState(
            targetValue = if (animationPlayed) 1.0f else 0.0f,
            animationSpec = tween(
                durationMillis = 2100000,
                delayMillis = 0
            )
        )

        LaunchedEffect(key1 = true) {
            animationPlayed = true
            while (true) {
                when (deliveryImage) {
                    R.drawable.delivery1 -> deliveryImage = R.drawable.delivery2
                    R.drawable.delivery2 -> deliveryImage = R.drawable.delivery3
                    R.drawable.delivery3 -> deliveryImage = R.drawable.delivery4
                    R.drawable.delivery4 -> deliveryImage = R.drawable.delivery1
                }
                delay(4000)
            }
        }


        LaunchedEffect(key1 = true) {
            while (deliveryTime > 0) {
                delay(60000)
                deliveryTime -= 1
            }

            if (deliveryTime == 0) {
                navController.popBackStack()
                navController.navigate(BottomBarScreen.HomePage.route)
            }
        }



        Column(modifier = Modifier.weight(0.2f)) {
            Text(
                modifier = Modifier
                    .padding(24.dp)
                    .padding(bottom = 24.dp),
                text = "Your Order will be delivered less than $deliveryTime minutes",
                color = mainColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 20.sp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(130.dp * 2f)
                .weight(0.4f)
        ) {
            Canvas(modifier = Modifier.size(130.dp * 2f)) {
                drawArc(
                    color = Color.LightGray,
                    startAngle = -90f,
                    sweepAngle = 360.0f,
                    useCenter = false,
                    style = Stroke(10.dp.toPx(), cap = StrokeCap.Round)
                )
            }

            Canvas(modifier = Modifier.size(130.dp * 2f)) {
                drawArc(
                    color = mainColor,
                    startAngle = -90f,
                    sweepAngle = 360 * currentPercentage.value,
                    useCenter = false,
                    style = Stroke(10.dp.toPx(), cap = StrokeCap.Round)
                )
            }

            Card(
                modifier = Modifier.requiredSize(200.dp),
                shape = RoundedCornerShape(100.dp)
            ) {
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(id = deliveryImage),
                    contentDescription = "delivery"
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Button(
                modifier = Modifier
                    .padding(end = 8.dp),
                onClick = {
                    navController.popBackStack()
                    navController.navigate(BottomBarScreen.HomePage.route)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = mainColor)
            ) {
                Text(
                    text = "Go to Home Page",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 18.sp),
                    color = Color.White
                )
            }
        }
    }
}
