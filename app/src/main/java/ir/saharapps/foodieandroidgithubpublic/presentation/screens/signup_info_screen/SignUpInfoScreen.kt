package ir.saharapps.foodieapp.presentation.screens.signup_info_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.saharapps.foodieapp.R
import ir.saharapps.foodieapp.presentation.ui.theme.mainColor
import kotlinx.coroutines.launch
import androidx.compose.material.Scaffold
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.saharapps.foodieapp.navigation.Screen
import ir.saharapps.foodieapp.presentation.ui.theme.secondColor


@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignUpInfoScreen(
    navController: NavHostController,
    signUpInfoViewModel: SignUpInfoViewModel = hiltViewModel()
) {
    val phone = signUpInfoViewModel.phoneNumber
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val titleFont = FontFamily(Font(R.font.exo2romanbold))
    val sansFont = FontFamily(Font(R.font.open_sans_regular))

    val context = LocalContext.current
    var progressBarStatus by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true ){
        signUpInfoViewModel.signUpInfoResultFlow.collect{state ->
            when(state){
                is SignUpInfoViewModel.SignUpChannelState.Success -> {
                    navController.popBackStack()
                    navController.navigate(Screen.Main.route)
                }
                is SignUpInfoViewModel.SignUpChannelState.Error -> {
                    Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT).show()
                }
                is SignUpInfoViewModel.SignUpChannelState.Internet -> {
                    navController.navigate(Screen.Error.passErrorMsg("Check your Internet connection"))
                }
                is SignUpInfoViewModel.SignUpChannelState.Server -> {
                    navController.navigate(Screen.Error.passErrorMsg("Server is disconnected"))
                }
                is SignUpInfoViewModel.SignUpChannelState.StartLoading -> {
                    progressBarStatus = true
                }
                is SignUpInfoViewModel.SignUpChannelState.StopLoading -> {
                    progressBarStatus = false
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {
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
                        .padding(top = 24.dp)
                        .padding(bottom = 24.dp)
                        .background(
                            brush = Brush.linearGradient(listOf(Color.Black, Color.Black)),
                            shape = RoundedCornerShape(40.dp), alpha = 0.5f
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Complete information for \n$phone",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontFamily = sansFont, fontSize = 24.sp),
                        textAlign = TextAlign.Center
                    )
                }

                var firstName by remember{ mutableStateOf("")}
                OutlinedTextField(
                    modifier = Modifier.padding(top = 8.dp),
                    value = firstName,
                    onValueChange ={ firstName = it},
                    placeholder = { Text(text = "User Name" ) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "User Name"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        leadingIconColor = mainColor,
                        placeholderColor = Color.LightGray
                    ),
                )

                var password by remember {mutableStateOf("")}
                var passwordVisibility by remember {mutableStateOf(false)}
                val trailingIcon = if(passwordVisibility)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

                OutlinedTextField(
                    modifier = Modifier.padding(top = 8.dp),
                    value = password,
                    onValueChange ={ password = it },
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

                var verifyPassword by remember{mutableStateOf("")}
                var verifyPasswordVisibility by remember {mutableStateOf(false)}
                val verifyTrailingIcon = if(verifyPasswordVisibility)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

                OutlinedTextField(
                    modifier = Modifier.padding(top = 8.dp),
                    value = verifyPassword,
                    onValueChange ={ verifyPassword = it },
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
                                imageVector = verifyTrailingIcon,
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

                var isConditionAccepted = remember{ mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .padding(bottom = 24.dp)
                        .background(
                            brush = Brush.linearGradient(listOf(Color.Black, Color.Black)),
                            shape = RoundedCornerShape(40.dp), alpha = 0.5f
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Checkbox(
                            checked = isConditionAccepted.value,
                            onCheckedChange = {isConditionAccepted.value = it},
                            enabled= true,
                            colors = CheckboxDefaults.colors(uncheckedColor = Color.White)
                        )

                        Text(
                            text = "I agree to the ",
                            color = Color.White,
                            style = TextStyle(fontFamily = sansFont, fontSize = 16.sp)
                        )
                        Text(
                            modifier = Modifier
                                .clickable { //todo show terms and condition
                                     },
                            text = "Terms & Condition",
                            textDecoration = TextDecoration.Underline,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontFamily = sansFont, fontSize = 16.sp),
                        )
                    }
                }

                Button(
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = mainColor, contentColor = Color.White),
                    onClick = {
                        val areFieldsEmpty = firstName.isEmpty() || password.isEmpty() || verifyPassword.isEmpty()
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
                        }else if(!isConditionAccepted.value){
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "You should accept Terms and Condition",
                                    actionLabel = "Ok"
                                )
                            }
                        }else{
                            phone?.let {
                                signUpInfoViewModel.signUpUser(phone,firstName, password)
                            }
                        }
                    }
                ) {
                    Text(
                        text = "Sign up",
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontFamily = sansFont, fontSize = 18.sp)
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .padding(bottom = 24.dp)
                        .background(
                            brush = Brush.linearGradient(listOf(Color.Black, Color.Black)),
                            shape = RoundedCornerShape(40.dp), alpha = 0.5f
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(modifier = Modifier
                        .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Already have an account? ",
                            color = Color.White,
                            style = TextStyle(fontFamily = sansFont, fontSize = 16.sp)
                        )
                        Text(
                            modifier = Modifier
                                .clickable { println("222222222222")},
                            text = "Login",
                            textDecoration = TextDecoration.Underline,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontFamily = sansFont, fontSize = 16.sp),
                        )
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

@Composable
fun PasswordEditText(status: String ,passwordValue: MutableState<String>, leadingIcon: ImageVector) {
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
            passwordValue.value = it
                       },
        placeholder = { Text(text = status)},
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = status
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
}

@Preview
@Composable
fun SignUpPreview() {
//    SignUpInfoScreen()
}