package com.kareem.yourkitchen.user.auth.login

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kareem.yourkitchen.user.auth.viewModel.YourInfoViewModel
import com.kareem.yourkitchen.utils.AppData1
import com.kareem.yourkitchen.utils.ArrowBack
import com.kareem.yourkitchen.utils.TextBottom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourInfoPage(navController: NavController,viewModel: YourInfoViewModel) {


    val myColor = Color(0xFFFFFF)
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val emailAddress = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val showProgress = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        ArrowBack {
            navController.navigate("LoginUser") {
                popUpTo("LoginUser")
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Your Info",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(28.dp))

        TextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            modifier = Modifier
                .padding(start = 32.dp)
                .height(60.dp)
                .width(320.dp)
                .border(
                    1.dp,
                    Color(red = 230, green = 20, blue = 0),
                    shape = RoundedCornerShape(25.dp)
                ),
            label = { Text(text = "Enter First name", color = Color.Black) },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = myColor,
                focusedLabelColor = myColor,
                cursorColor = Color(red = 230, green = 20, blue = 0),
                textColor = Color(red = 0, green = 0, blue = 0),
                containerColor = myColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(25.dp)
        )



        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            modifier = Modifier
                .padding(start = 32.dp)
                .height(60.dp)
                .width(320.dp)
                .border(
                    1.dp,
                    Color(red = 230, green = 20, blue = 0),
                    shape = RoundedCornerShape(25.dp)
                ),
            label = { Text(text = "Enter Last name", color = Color.Black) },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = myColor,
                focusedLabelColor = myColor,
                cursorColor = Color(red = 230, green = 20, blue = 0),
                textColor = Color(red = 0, green = 0, blue = 0),
                containerColor = myColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(25.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        var isEmailValid by remember { mutableStateOf(true) }
        var errorTextEmail by remember { mutableStateOf("") }

        TextField(
            value = emailAddress.value,
            onValueChange = {
                emailAddress.value = it
                isEmailValid = it.matches(Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"))
                errorTextEmail =
                    if (isEmailValid) "" else "Invalid email address. Please enter a valid email."
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .padding(start = 32.dp)
                .height(60.dp)
                .width(320.dp)
                .border(
                    1.dp,
                    Color(red = 230, green = 20, blue = 0),
                    shape = RoundedCornerShape(25.dp)
                ),
            label = { Text(text = "Enter Email Address", color = Color.Black) },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = myColor,
                focusedLabelColor = myColor,
                cursorColor = Color(red = 230, green = 20, blue = 0),
                textColor = Color(red = 0, green = 0, blue = 0),
                containerColor = myColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(25.dp)
        )

        if (errorTextEmail.isNotEmpty()) {
            Text(
                text = errorTextEmail,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        var errorText by remember { mutableStateOf("") }

        TextField(
            value = phoneNumber.value,
            onValueChange = {
                // phoneNumberP.value = it

                if (it.length <= 10) {
                    phoneNumber.value = it
                }

                if (it.length > 1 && it.length < 10) {
                    errorText = "Please enter a 10-digit phone number."
                } else {
                    errorText = ""
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(start = 32.dp)
                .height(60.dp)
                .width(320.dp)
                .border(
                    1.dp,
                    Color(red = 230, green = 20, blue = 0),
                    shape = RoundedCornerShape(25.dp)
                ),
            label = { Text(text = "Enter Phone Number", color = Color.Black) },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = myColor,
                focusedLabelColor = myColor,
                cursorColor = Color(red = 230, green = 20, blue = 0),
                textColor = Color(red = 0, green = 0, blue = 0),
                containerColor = myColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(25.dp)
        )

        if (errorText.isNotEmpty()) {
            Text(
                text = errorText,
                color = Color.Red,
                modifier = Modifier.padding(start = 32.dp)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
        if (showProgress.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        Button(

            onClick = {
                viewModel.progressKey.value = true
                if (firstName.value.isNotEmpty() && lastName.value.isNotEmpty() && emailAddress.value.isNotEmpty() && phoneNumber.value.isNotEmpty()) {
                    showProgress.value = true
                    viewModel.saveInfo(
                        firstName.value,
                        lastName.value,
                        emailAddress.value,
                        phoneNumber.value
                    )
                    AppData1.phoneNumberUser = phoneNumber.value
                    navController.navigate("VerificationCodeScreen")
                } else {
                    Toast.makeText(
                        context,
                        "Please select all missing",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 230, green = 20, blue = 0)
            ),
            modifier = Modifier
                .padding(start = 40.dp)
                .height(50.dp)
                .width(310.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                if (viewModel.progressKey.value) {
                    androidx.compose.material.CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 4.dp
                    )
                } else {

                    TextBottom("SIGN UP")
                }
            }
        }


    }
}



