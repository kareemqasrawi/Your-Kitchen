package com.kareem.yourkitchen.user.home.view.basket

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kareem.yourkitchen.R
import com.kareem.yourkitchen.user.auth.viewModel.MyAddressViewModel
import com.kareem.yourkitchen.utils.TextBottom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAddress(navController:NavController,viewModel: MyAddressViewModel) {
    val myColor = Color(0xFFFFFF)
    val buildingName = remember { mutableStateOf("") }
    val aptNumber = remember { mutableStateOf("") }
    val floor = remember { mutableStateOf("") }
    val street = remember { mutableStateOf("") }
    val context = LocalContext.current
    Column() {


        Box(
            modifier = Modifier
                .background(color = Color.White)
                .width(400.dp)
                .height(60.dp),

            ) {
            Row(
                modifier = Modifier
                    .padding(start = 20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = null,
                    tint = Color(red = 230, green = 20, blue = 0),
                    modifier = Modifier
                        .size(50.dp)
                        .padding(5.dp)
                        .clickable {

                            navController.navigate("Map")

                        })

                Text(
                    text = "New address",
                    fontSize = 23.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top =7.dp)
                )


            }
        }
        Box(
            modifier = Modifier
                .height(200.dp)
                .width(400.dp)
                .background(color = Color.Gray)
        ) {


        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Apartment",
            fontSize = 30.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start =32.dp)

            )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = buildingName.value,
            onValueChange = { buildingName.value = it },
            modifier = Modifier
                .padding(start = 20.dp)
                .height(60.dp)
                .width(340.dp)
                .border(
                    1.dp,
                    Color(red = 230, green = 20, blue = 0),
                    shape = RoundedCornerShape(25.dp)
                ),
            label = { Text(text = "Building Name", color = Color.Black) },
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
            value = aptNumber.value,
            onValueChange = { aptNumber.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(start = 20.dp)
                .height(60.dp)
                .width(340.dp)
                .border(
                    1.dp,
                    Color(red = 230, green = 20, blue = 0),
                    shape = RoundedCornerShape(25.dp)
                ),
            label = { Text(text = "Apt.Number", color = Color.Black) },
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
            value = street.value,
            onValueChange = { street.value = it },
            modifier = Modifier
                .padding(start = 20.dp)
                .height(60.dp)
                .width(340.dp)
                .border(
                    1.dp,
                    Color(red = 230, green = 20, blue = 0),
                    shape = RoundedCornerShape(25.dp)
                ),
            label = { Text(text = "Street", color = Color.Black) },
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
            value = floor.value,
            onValueChange = { floor.value = it },
            modifier = Modifier
                .padding(start = 20.dp)
                .height(60.dp)
                .width(340.dp)
                .border(
                    1.dp,
                    Color(red = 230, green = 20, blue = 0),
                    shape = RoundedCornerShape(25.dp)
                ),
            label = { Text(text = "Floor", color = Color.Black) },
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
        Spacer(modifier = Modifier.height(60.dp))

        Button(

            onClick = {
               if (buildingName.value.isNotEmpty() && aptNumber.value.isNotEmpty() && street.value.isNotEmpty() && floor.value.isNotEmpty()) {
                   viewModel.saveMyAddress(
                       buildingName.value,
                        aptNumber.value,
                        street.value,
                        floor.value
                    )
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
                .padding(start = 20.dp)
                .height(50.dp)
                .width(340.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                TextBottom("Save Address")

            }
        }

    }
}