package com.kareem.yourkitchen.user.auth.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.kareem.yourkitchen.user.auth.viewModel.YourInfoViewModel
import com.kareem.yourkitchen.utils.AppData1
import com.kareem.yourkitchen.utils.ArrowBack
import com.kareem.yourkitchen.utils.TextBottom


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginUser(navController: NavController,viewModel: YourInfoViewModel) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {

        ArrowBack{
            navController.navigate("Login"){
                popUpTo("Login")
            }
        }

        Spacer(modifier = Modifier.height(18.dp))


        Text(
            text = "Enter Your\nPhone Number",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 30.dp)
        )


        Spacer(modifier = Modifier.height(18.dp))


        Text(
            text = "Please Input Your Number To Verify",
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 34.dp)
        )


        Spacer(modifier = Modifier.height(40.dp))


        val valueU = remember { mutableStateOf("") }
        val countryCode = "+962"

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 30.dp)
                .height(70.dp)
                .width(320.dp),
            horizontalArrangement = Arrangement.Start
        ) {

            Text(
                text = countryCode,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Blue.copy(alpha = 0.5f),
                modifier = Modifier.padding(end = 8.dp)
            )

            Divider(
                color = Color(red = 230, green = 20, blue = 0),
                thickness = 3.dp,
                modifier = Modifier
                    .height(40.dp)
                    .width(1.dp)
            )

            TextField(
                value = valueU.value,
                onValueChange = { valueU.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Enter Phone Number") },
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                textStyle = TextStyle(
                    color = Color(red = 0, green = 0, blue = 0),
                    fontSize = 20.sp
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = Color(red = 230, green = 20, blue = 0),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,

                )
            )
        }

        Divider(
            color = Color(red = 230, green = 20, blue = 0),
            thickness = 3.dp,
            modifier = Modifier
                .width(350.dp)
                .height(1.dp)
                .padding(start = 30.dp)
                )



        val isAgreed = remember { mutableStateOf(false) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 30.dp)
                .height(90.dp)
                .width(320.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            RadioButton(
                selected = isAgreed.value,
                onClick = { isAgreed.value = !isAgreed.value },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(red = 230, green = 20, blue = 0)
                )
            )
            val underlineText = buildAnnotatedString {
                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline, color = Color.Blue.copy(alpha = 0.5f))) {
                    append("Policy & Terms and Conditions")
                }
            }

            Text(
                text = underlineText,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color=Color.Blue.copy(alpha = 0.5f),
                modifier = Modifier.clickable {
                    val uri = "https://help.qawn.com/hubfs/tc/TC_EN.html".toUri()
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context.startActivity(intent)
                }
            )

        }



        Spacer(modifier = Modifier.height(5.dp))
        val isPhoneNumberVerifiedU = remember { mutableStateOf(false) }

        LaunchedEffect(valueU.value) {

                isPhoneNumberVerifiedU.value = viewModel.checkPhoneNumberExistsU(valueU.value)

        }

        Button(
            onClick = {

                if (isAgreed.value && isPhoneNumberVerifiedU.value) {
                    AppData1.phoneNumberUser=valueU.value
                    navController.navigate("VerificationCodeScreen")
                    valueU.value=""
                } else {
                    Toast.makeText(context, "Agreement required or incorrect phone number", Toast.LENGTH_SHORT).show()
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

                TextBottom(Text = "Next")

            }
        }
        Spacer(modifier = Modifier.height(0.dp))

        Row(
            modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .padding(start = 74.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Don't have an account?",
                color = Color.Black,
                fontSize = 15.sp
            )
            val underlineColor = Color(red = 230, green = 20, blue = 0)
            Box(modifier = Modifier
                .clickable {
                    navController.navigate("YourInfoPage")
                }
                .drawWithContent {
                    drawContent()
                    drawLine(
                        color = underlineColor,
                        start = Offset(0f, size.height - 1.dp.toPx()),
                        end = Offset(size.width, size.height - 1.dp.toPx()),
                        strokeWidth = 2.dp.toPx()
                    )
                }
            ) {

                Text(text ="Sign Up",

                    color = Color(red = 230, green = 20, blue = 0),
                    fontSize = 15.sp
                )
            }
        }


    }
}


