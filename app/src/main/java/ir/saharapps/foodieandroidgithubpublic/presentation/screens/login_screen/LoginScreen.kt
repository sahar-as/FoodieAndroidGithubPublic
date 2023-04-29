package ir.saharapps.foodieapp.presentation.screens.login_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.saharapps.foodieapp.R
import ir.saharapps.foodieapp.navigation.Screen
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor
import ir.saharapps.foodieapp.presentation.ui.theme.secondColor
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val titleFont = FontFamily(Font(R.font.exo2romanbold))
    val sansFont = FontFamily(Font(R.font.open_sans_regular))

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    var progressBarStatus by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        loginViewModel.loginResultFlow.collect { state ->
            when (state) {
                is LoginViewModel.LoginChannelState.Success -> {
                    navController.popBackStack()
                    navController.navigate(Screen.Main.route)
                }

                is LoginViewModel.LoginChannelState.Error -> {
                    Toast.makeText(context, "Something goes wronged! try later", Toast.LENGTH_SHORT).show()
                }
                is LoginViewModel.LoginChannelState.Internet ->{
                    navController.navigate(Screen.Error.passErrorMsg("Check your Internet connection"))
                }
                is LoginViewModel.LoginChannelState.Server ->{
                    navController.navigate(Screen.Error.passErrorMsg("Server is disconnected"))
                }
                is LoginViewModel.LoginChannelState.StartLoading ->{
                    progressBarStatus = true
                }
                is LoginViewModel.LoginChannelState.StopLoading -> {
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
                modifier = Modifier.fillMaxSize(),
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
                    Text(
                        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, end = 16.dp),
                        text = "Foodie",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        style = TextStyle(fontFamily = titleFont, fontSize = 85.sp)
                    )
                }

                Box(
                    modifier = Modifier.padding(bottom = 16.dp),

                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .background(
                                Brush.linearGradient(listOf(Color.Black, Color.Black)),
                                CircleShape,
                                0.5f
                            )
                            .padding(8.dp)
                            .size(100.dp),
                        painter = painterResource(id = R.drawable.img_foodie_logo),
                        contentDescription = stringResource(R.string.app_logo),
                    )
                }

                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(listOf(Color.Black, Color.Black)),
                            shape = RoundedCornerShape(40.dp), alpha = 0.5f
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column() {
                        Text(
                            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                            text = "Welcome to Foodie",
                            fontSize = MaterialTheme.typography.h4.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            style = TextStyle(fontFamily = titleFont, fontSize = 24.sp)
                        )
                        Text(
                            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp),
                            text = "Sign in to Continue...",
                            fontSize = 18.sp,
                            color = Color.White,
                            style = TextStyle(fontFamily = titleFont, fontSize = 24.sp)
                        )
                    }
                }

                var phoneNumber by rememberSaveable { mutableStateOf("") }
                val maxChar = 11

                OutlinedTextField(
                    modifier = Modifier
                        .padding(top = 24.dp),
                    value = phoneNumber,
                    onValueChange = {
                        val startWithZero = it.length == 1 && it.startsWith("0")
                        val startWithZeroNine = it.length >= 2 && it.startsWith("09")
                        val isNumber = it.isDigitsOnly()
                        val isLessThanElevenChar = it.length <= maxChar
                        val isGraterThanElevenChar = it.length > maxChar

                        if (it.isEmpty()) {
                            phoneNumber = ""
                        } else if (startWithZero && isNumber) {
                            phoneNumber = it
                        } else if (isNumber && startWithZeroNine && isLessThanElevenChar) {
                            phoneNumber = it
                        } else if (isGraterThanElevenChar) {
                            Toast.makeText(
                                context,
                                "Phone number length should not be more than 11 ",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else if (!isNumber) {
                            Toast.makeText(context, "Please enter number", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(
                                context,
                                "Phone number should start with 09",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    placeholder = { Text(text = "Phone Number") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Phone,
                            contentDescription = "Phone Number"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        leadingIconColor = mainColor,
                        placeholderColor = Color.LightGray
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )

                var password by rememberSaveable { mutableStateOf("") }
                var passwordVisibility by remember { mutableStateOf(false) }

                val icon = if (passwordVisibility)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

                OutlinedTextField(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    value = password,
                    onValueChange = { password = it},
                    placeholder = { Text(text = "password") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Password"
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                imageVector = icon,
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

                Button(
                    modifier = Modifier
                        .padding(top = 24.dp),
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = mainColor, contentColor = Color.White),
                    onClick = {
                        val approvedPhoneNumber = phoneNumber.length == 11
                        val areFieldsEmpty = phoneNumber.isEmpty() || password.isEmpty()
                        val isPasswordShort = password.length < 8
                        val passContainCaps = password.contains("[A-Z]".toRegex())

                        if (areFieldsEmpty) {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Please complete all fields",
                                    actionLabel = "Ok"
                                )
                            }
                        } else if (!approvedPhoneNumber) {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Phone number should be 11 character",
                                    actionLabel = "Ok"
                                )
                            }
                        } else if (isPasswordShort) {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Password length should be at least 8 character.",
                                    actionLabel = "Ok"
                                )
                            }
                        } else if (!passContainCaps) {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Password should contain at least One upper letter.",
                                    actionLabel = "Ok"
                                )
                            }
                        } else {
                            loginViewModel.signInUser(phoneNumber, password)
                        }

                    }) {
                    Text(
                        text = "Sign in",
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontFamily = sansFont, fontSize = 20.sp)
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .background(
                            brush = Brush.linearGradient(listOf(Color.Black, Color.Black)),
                            shape = RoundedCornerShape(40.dp), alpha = 0.5f
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row {
                            Text(
                                modifier = Modifier
                                    .padding(top = 16.dp, start = 16.dp, bottom = 16.dp),
                                text = "Forget your ",
                                color = Color.White,
                                style = TextStyle(fontFamily = sansFont, fontSize = 16.sp)
                            )
                            Text(
                                modifier = Modifier
                                    .padding(top = 16.dp, end = 16.dp, bottom = 16.dp)
                                    .clickable {
                                        navController.navigate(Screen.ResetPassword.route)
                                    },
                                text = "Password?",
                                color = Color.White,
                                style = TextStyle(fontFamily = sansFont, fontSize = 16.sp),
                                textDecoration = TextDecoration.Underline
                            )
                        }
                        Row {
                            Text(
                                modifier = Modifier
                                    .padding(top = 36.dp, bottom = 16.dp, start = 16.dp),
                                text = "Don't have an account? ",
                                color = Color.White,
                                style = TextStyle(fontFamily = sansFont, fontSize = 16.sp)
                            )
                            Text(
                                modifier = Modifier
                                    .padding(top = 36.dp, bottom = 16.dp, end = 16.dp)
                                    .clickable {
                                        navController.navigate(Screen.SignUpPhone.route)
                                    },
                                text = "Sign up?",
                                color = Color.White,
                                textDecoration = TextDecoration.Underline,
                                style = TextStyle(fontFamily = sansFont, fontSize = 16.sp)
                            )
                        }
                    }
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


//@Preview
//@Composable
//fun LoginPreview() {
//    Login()
//}