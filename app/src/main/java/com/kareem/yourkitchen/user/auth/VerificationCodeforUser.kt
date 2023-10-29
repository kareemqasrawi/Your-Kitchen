package com.kareem.yourkitchen.user.auth

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kareem.yourkitchen.user.auth.viewModel.YourInfoViewModel
import com.kareem.yourkitchen.utils.ArrowBack
import com.kareem.yourkitchen.utils.TextBottom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationCodeScreen(navController: NavController, yourInfoViewModel: YourInfoViewModel = viewModel()) {
    val context = LocalContext.current
    val myColor = Color(0xFFFFFF)
    val focusManager = LocalFocusManager.current
    val digits = remember {
        mutableStateListOf(
            *((0 until 4).map { "" }.toTypedArray())
        )
    }
    val focusRequesters: List<FocusRequester> = remember {
        (0 until 4).map { FocusRequester() }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {

        ArrowBack {
            navController.navigate("LoginUser"){
                popUpTo("LoginUser")
            }
        }
        Spacer(modifier = Modifier.height(18.dp))


        Text(
            text = "Enter Your\nVerification Code",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "You will get a OTP via SMS",
            modifier = Modifier.padding(start = 35.dp)
        )
        Spacer(modifier = Modifier.height(70.dp))





        Row(
            modifier = Modifier
                .padding(start = 40.dp)
                .height(60.dp)
                .width(320.dp)
                .wrapContentHeight(),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            (0 until 4).forEach { index ->
                TextField(
                    modifier = Modifier
                        .weight(0.2f)
                        .padding(end = 4.dp)
                        .border(
                            1.dp,
                            Color(red = 230, green = 20, blue = 0),
                            shape = RoundedCornerShape(150.dp),

                            )

                        .onKeyEvent {
                            if (it.nativeKeyEvent.keyCode == 67) {
                                if (digits[index].isEmpty()) {
                                    focusManager.moveFocus(FocusDirection.Left)
                                }
                                digits[index] = ""
                            }
                            true
                        }
                        .padding(vertical = 3.dp)
                        .focusOrder(focusRequesters[index])
                        .focusRequester(focusRequesters[index]),

                    singleLine = true,
                    value = digits[index],
                    onValueChange = {
                        if (digits[index].isEmpty() && it.isDigitsOnly()) {
                            digits[index] = it
                            focusManager.moveFocus(FocusDirection.Right)
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Right)
                        }
                    ),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    ),
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
            }
        }


        Spacer(modifier = Modifier.height(70.dp))
        val correctOTP = "1111"
        Button(

            onClick = {
                val enteredOTP = digits.joinToString("") // Join the entered digits into a single string
                if (enteredOTP == correctOTP) {
                    yourInfoViewModel.authUser(enteredOTP.toInt(), context){
                        if (it)
                            navController.navigate("homeUser")

                    }

                } else {
                    Toast.makeText(context, "Invalid OTP. Please enter the correct OTP to proceed.", Toast.LENGTH_SHORT).show()
                }
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 230, green = 20, blue = 0)
                ),
                modifier = Modifier
                    .padding(start = 40.dp)
                    .height(50.dp)
                    .width(320.dp),
                shape = MaterialTheme.shapes.small
            ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                TextBottom("VERIFY")

            }

        }
    }
}






