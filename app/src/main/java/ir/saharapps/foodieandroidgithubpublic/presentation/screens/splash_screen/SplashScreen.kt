package ir.saharapps.foodieapp.presentation.screens.splash_screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.saharapps.foodieapp.R
import ir.saharapps.foodieapp.common.rememberWindowInfo
import ir.saharapps.foodieapp.navigation.Screen
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor
import ir.saharapps.foodieapp.presentation.ui.theme.mainLight
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect


@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val onBoardShowStatus by splashViewModel.readOnBoardStatus.collectAsState()
    val loginPageStatus by splashViewModel.userLoginState.collectAsState()

    var isMoved by remember { mutableStateOf(false) }
    val degrees = remember { Animatable(0f) }
    val windowInfo = rememberWindowInfo()
    val height = windowInfo.screenHeight / 2 - 150.dp


    LaunchedEffect(key1 = true) {
        isMoved = true

        degrees.animateTo(
            targetValue = 360f,
            animationSpec = repeatable( 10,
                animation = tween(100)
            )
        )
        coroutineScope {
            delay(2000)
            navController.popBackStack()
            if(!onBoardShowStatus){
                navController.navigate(Screen.Welcome.route)
            }else if(loginPageStatus){
                navController.navigate(Screen.Login.route)
            }else{
                navController.navigate(Screen.Main.route)
            }
        }

    }

    val transition = updateTransition(targetState = isMoved, label = null)
    val offset by transition.animateDp(
        transitionSpec = { tween(3000) },
        label = "border radius",
        targetValueByState = { isMoved ->
            if(isMoved)  height else 0.dp
        }
    )

    Splash(offset, degrees.value)

}

@Composable
fun Splash(offset: Dp, degree:Float) {

    val titleFont = FontFamily(
        Font(R.font.exo2romanbold)
    )
    Box(
        modifier = Modifier
            .background(Brush.verticalGradient(listOf(mainColor, mainLight)))
            .fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Foodie",
                fontSize = MaterialTheme.typography.h1.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = TextStyle(fontFamily = titleFont, fontSize = 24.sp),
                modifier = Modifier
                    .offset(y = offset)
                    .weight(2f)

            )

            Image(
                modifier = Modifier
                    .rotate(degree)
                    .weight(7f)
                    .drawBehind {
                        drawCircle(
                            color = Color.White,
                            radius = 200.0F
                        )
                    },
                painter = painterResource(id = R.drawable.img_foodie_logo),
                contentDescription = stringResource(R.string.app_logo),
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    Splash(100.dp,50f)
}