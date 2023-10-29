package com.kareem.yourkitchen.base.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kareem.yourkitchen.R
import com.kareem.yourkitchen.utils.TextBottom

@Composable
fun Login(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Image(
                painter = painterResource(id = R.drawable.yourkitchen),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "Grab Your\nDelicious food",
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,

            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Order now and enjoy a wide selection of mouthwatering dishes!",

                fontSize = 14.sp,
                modifier = Modifier.padding(start = 34.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { navController.navigate("LoginUser") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 230, green = 20, blue = 0)
                ),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .height(50.dp)
                    .width(320.dp),
                shape = MaterialTheme.shapes.small // انحناء قليل
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    TextBottom("SIGNUP / IN")

                }
            }

            Spacer(modifier = Modifier.width(30.dp))


            Button(
                onClick = { navController.navigate("LoginProvider") },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .height(50.dp)
                    .width(320.dp)
                    .border(
                        width = 2.dp,
                        color = Color(red = 230, green = 20, blue = 0),
                        shape = MaterialTheme.shapes.small
                    ),
                shape = MaterialTheme.shapes.small, // انحناء قليل
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 255, green = 255, blue = 255)
                ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_food_bank_24),
                        contentDescription = null,
                        tint = Color(red = 230, green = 20, blue = 0),
                        modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                    )
                    Text(
                        text = "Join as a client or provider",
                        color = Color(red = 0, green = 0, blue = 0)
                    )
                }
            }
            }
        }
    }




