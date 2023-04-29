package ir.saharapps.foodieapp.presentation.screens.welcome_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import ir.saharapps.foodieapp.R
import ir.saharapps.foodieapp.common.Constants.ON_BOARDING_PAGE_COUNT
import ir.saharapps.foodieapp.domain.model.OnBoardingPage
import ir.saharapps.foodieapp.navigation.Screen
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor
import ir.saharapps.foodieapp.presentation.ui.theme.secondColor

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val loginPageStatus by welcomeViewModel.userLoginState.collectAsState()

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
        OnBoardingPage.Forth
    )

    val pagerState = rememberPagerState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(secondColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            state = pagerState,
            count = ON_BOARDING_PAGE_COUNT,
            verticalAlignment = Alignment.Top
        ) {page ->
            WelcomePagerScreen(onBoardingPage = pages[page])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            activeColor = mainColor,
            inactiveColor = Color.White,
            indicatorWidth = 12.dp,
            spacing = 8.dp
        )
        SkipButton(
            modifier = Modifier
                .weight(2f)
                .align(Alignment.CenterHorizontally),
            pagerState = pagerState
        ) {
            navController.popBackStack()
            if(loginPageStatus){
                navController.popBackStack()
                navController.navigate(Screen.Login.route)
            }else{
                navController.popBackStack()
                navController.navigate(Screen.Main.route)
            }
            welcomeViewModel.saveOnBoardStatus(true)
        }
    }
}

@Composable
fun WelcomePagerScreen(onBoardingPage: OnBoardingPage) {
    val textFont = FontFamily(
        Font(R.font.roboto_black)
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = onBoardingPage.subjectImage),
            contentDescription = "status image",
            modifier = Modifier
                .weight(1f)
                .size(300.dp)
        )

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = onBoardingPage.chefImage) ,
                contentDescription = "chef image",
                modifier = Modifier
                    .weight(1f)
                    .width(200.dp)
                    .height(250.dp)
            )
            Text(
                text = onBoardingPage.text,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontFamily = textFont,
                color = Color.White,
                modifier = Modifier
                    .weight(1.5f)
                    .padding(end = 16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SkipButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = pagerState.currentPage == 3
        ) {
            Button(
                modifier = modifier.width(200.dp),
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = mainColor
                )
            ) {
                Text(text = "Skip")
            }
        }
    }
}

@Preview
@Composable
fun WelcomePreview() {
    WelcomePagerScreen(OnBoardingPage.First)
}