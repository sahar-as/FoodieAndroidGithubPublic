package ir.saharapps.foodieapp.presentation.screens.new_password_screen


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.saharapps.foodieapp.R
import ir.saharapps.foodieapp.navigation.Screen
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor
import ir.saharapps.foodieapp.presentation.ui.theme.secondColor
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewPasswordScreen(
    navController: NavHostController,
    newPasswordViewModel: NewPasswordViewModel = hiltViewModel()
) {
    val titleFont = FontFamily(Font(R.font.exo2romanbold))
    val sansFont = FontFamily(Font(R.font.open_sans_regular))

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val phoneNumber = newPasswordViewModel.phoneNumber
    var progressBarStatus by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true){
        newPasswordViewModel.updateUserPassResultFlow.collect{state ->
            when(state){
                is NewPasswordViewModel.NewPasswordChannelState.Success ->{
                    navController.navigate(Screen.Main.route)
                }
                is NewPasswordViewModel.NewPasswordChannelState.Error ->{
                    Toast.makeText(context, "Something went wrong, try later!", Toast.LENGTH_SHORT).show()
                }
                is NewPasswordViewModel.NewPasswordChannelState.Internet ->{
                    navController.navigate(Screen.Error.passErrorMsg("Check your Internet connection"))
                }
                is NewPasswordViewModel.NewPasswordChannelState.Server ->{
                    navController.navigate(Screen.Error.passErrorMsg("Server is disconnected"))
                }
                is NewPasswordViewModel.NewPasswordChannelState.StartLoading ->{
                    progressBarStatus = true
                }
                is NewPasswordViewModel.NewPasswordChannelState.StopLoading ->{
                    progressBarStatus = false
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.9f),
                painter = painterResource(id = R.drawable.img_foodybackground),
                contentDescription = "background",
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .background(
                            brush = Brush.linearGradient(listOf(Color.Black, Color.Black)),
                            shape = RoundedCornerShape(40.dp), alpha = 0.5f
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row() {
                        Text(
                            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                            text = "Foodie",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            style = TextStyle(fontFamily = titleFont, fontSize = 85.sp)
                        )

                        Image(
                            modifier = Modifier
                                .padding(top = 36.dp)
                                .size(80.dp)
                                .background(
                                    Brush.linearGradient(listOf(Color.White, Color.White)),
                                    CircleShape,
                                    0.3f
                                ),
                            painter = painterResource(id = R.drawable.img_foodie_logo),
                            contentDescription = stringResource(R.string.app_logo),
                        )

                    }
                }

                Box(
                    modifier = Modifier
                        .padding(top = 64.dp)
                        .padding(16.dp)
                        .background(
                            brush = Brush.linearGradient(listOf(Color.Black, Color.Black)),
                            shape = RoundedCornerShape(40.dp), alpha = 0.5f
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Enter new password:",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontFamily = sansFont, fontSize = 26.sp)
                    )
                }


                var password by rememberSaveable { mutableStateOf("")}
                var passwordVisibility by remember {mutableStateOf(false)}

                val trailingIcon = if(passwordVisibility)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

                OutlinedTextField(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    value = password,
                    onValueChange ={
                        password = it
                    },
                    placeholder = { Text(text = "Password")},
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Password"
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                imageVector = trailingIcon,
                                contentDescription = "Visibility Icon"
                            )
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        leadingIconColor = mainColor,
                        trailingIconColor = mainColor,
                        placeholderColor = Color.LightGray
                    ),
                    visualTransformation = if (passwordVisibility) VisualTransformation.None
                    else PasswordVisualTransformation()
                )

                var verifyPassword by rememberSaveable { mutableStateOf("")}
                var verifyPasswordVisibility by remember {mutableStateOf(false)}

                val verifyPasswordTrailingIcon = if(passwordVisibility)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

                OutlinedTextField(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    value = verifyPassword,
                    onValueChange ={
                        verifyPassword = it
                    },
                    placeholder = { Text(text = "Verify Password")},
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Verify Password"
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { verifyPasswordVisibility = !verifyPasswordVisibility }) {
                            Icon(
                                imageVector = verifyPasswordTrailingIcon,
                                contentDescription = "Visibility Icon"
                            )
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        leadingIconColor = mainColor,
                        trailingIconColor = mainColor,
                        placeholderColor = Color.LightGray
                    ),
                    visualTransformation = if (verifyPasswordVisibility) VisualTransformation.None
                    else PasswordVisualTransformation()
                )

                Button(
                    modifier = Modifier.padding(16.dp),
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = mainColor, contentColor = Color.White),
                    onClick = {
                        val areFieldsEmpty = password.isEmpty() || verifyPassword.isEmpty()
                        val isPasswordShort = password.length<8
                        val passContainCaps = password.contains("[A-Z]".toRegex())
                        val arePasswordSame = password == verifyPassword

                        if(areFieldsEmpty){
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Please complete all fields",
                                    actionLabel = "Ok"
                                )
                            }
                        }else if(isPasswordShort){
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Password length should be at least 8 character.",
                                    actionLabel = "Ok"
                                )
                            }
                        }else if(!passContainCaps){
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Password should contain at least One upper letter.",
                                    actionLabel = "Ok"
                                )
                            }
                        }else if (!arePasswordSame){
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Password and verity password should be same.",
                                    actionLabel = "Ok"
                                )
                            }
                        }else{
                            phoneNumber?.let {
                                coroutineScope.launch {
                                    newPasswordViewModel.updateUserPassword(phoneNumber, password)
                                }
                            }
                        }
                    }
                ) {
                    Text(
                        text = "Submit",
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontFamily = sansFont, fontSize = 18.sp)
                    )
                }
            }
            if(progressBarStatus){
                CircularProgressIndicator(
                    modifier = Modifier.size(100.dp),
                    color = secondColor,
                    strokeWidth = 10.dp
                )
            }
        }
    }

}


@Composable
fun PasswordEditText(status: String, passwordValue: MutableState<String>, leadingIcon: ImageVector) {

}

//@Preview
//@Composable
//fun PhoneCodeAuthPreview() {
//    NewPasswordScreen()
//}