package ir.saharapps.foodieapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor
import ir.saharapps.foodieapp.presentation.ui.theme.secondColor
import ir.saharapps.foodieapp.R

@Composable
fun ErrorScreen(
    errorViewModel: ErrorViewModel = hiltViewModel()
) {
    val errorMsg = errorViewModel.errorMsg

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ){
            Text(
                text = "Ooops...",
                color = secondColor,
                style = TextStyle(fontSize = 50.sp),
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ){
            Text(
                text = errorMsg ?:"Something went wrong",
                color = secondColor,
                style = TextStyle(fontSize = 24.sp),
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .requiredSize(250.dp)
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.error_image),
                contentDescription = "Error Image",
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    ErrorScreen()
}