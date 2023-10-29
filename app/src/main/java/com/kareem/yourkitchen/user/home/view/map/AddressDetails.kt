package com.kareem.yourkitchen.user.home.view.map

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressDetails(address :(String,String,String,String)->Unit){
    val myColor = Color(0xFFFFFF)
    val buildingName = remember { mutableStateOf("") }
    val aptNumber = remember { mutableStateOf("") }
    val floor = remember { mutableStateOf("") }
    val street = remember { mutableStateOf("") }
    val context = LocalContext.current

    address(buildingName.value,aptNumber.value,floor.value,street.value)

    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Apartment",
            fontSize = 15.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start =25.dp)

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
            label = { Text(text = "Building Name", color = Color.Black, fontSize = 13.sp) },
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
            label = { Text(text = "Apt.Number", color = Color.Black,fontSize = 13.sp) },
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
            label = { Text(text = "Street", color = Color.Black,fontSize = 13.sp) },
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
            label = { Text(text = "Floor", color = Color.Black,fontSize = 13.sp) },
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