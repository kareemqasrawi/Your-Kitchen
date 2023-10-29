package com.kareem.yourkitchen.provider.view.addProduct

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.kareem.yourkitchen.provider.viewModel.AddPostProviderViewModel
import com.kareem.yourkitchen.utils.AppData
import com.kareem.yourkitchen.utils.TextBottom

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun ShowPostProvider(viewModel1: AddPostProviderViewModel = viewModel()) {
    val myColor = Color(0xFFFFFF)
    val description = remember { mutableStateOf("") }
    val nameFood = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val rate = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "add new item",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 40.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = nameFood.value,
            onValueChange = { nameFood.value = it },
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
            label = { Text(text = "Enter Product Name", color = Color.Black) },
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
            value = description.value,
            onValueChange = { description.value = it },
            modifier = Modifier
                .height(150.dp)
                .padding(start = 32.dp)
                .width(320.dp)
                .border(
                    1.dp,
                    Color(red = 230, green = 20, blue = 0),
                    shape = RoundedCornerShape(25.dp)
                ),
            label = { Text(text = "Enter Description", color = Color.Black) },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = myColor,
                focusedLabelColor = myColor,
                cursorColor = Color(red = 230, green = 20, blue = 0),
                textColor = Color(red = 0, green = 0, blue = 0),
                containerColor = myColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            maxLines = 5,
            shape = RoundedCornerShape(25.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp),

            ) {
            TextField(
                value = price.value,
                onValueChange = { price.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .height(60.dp)
                    .width(150.dp)

                    .border(
                        1.dp,
                        Color(red = 230, green = 20, blue = 0),
                        shape = RoundedCornerShape(25.dp)
                    ),
                label = { Text(text = "Enter Price JOD", color = Color.Black, fontSize = 12.sp) },

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
            Spacer(modifier = Modifier.width(20.dp))
            TextField(
                value = rate.value,
                onValueChange = { rate.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .height(60.dp)
                    .width(150.dp)
                    .border(
                        1.dp,
                        Color(red = 230, green = 20, blue = 0),
                        shape = RoundedCornerShape(25.dp)
                    ),
                label = {
                    Text(
                        text = "Enter Rate",
                        color = Color.Black,
                        fontSize = 12.sp
                    )
                },
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
        var uri by remember {
            mutableStateOf<Uri?>(null)
        }
        val singlePhotoPicker = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia()
        ) {
            uri = it
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                singlePhotoPicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )

            },
            modifier = Modifier
                .padding(start = 78.dp)
                .height(50.dp)
                .width(230.dp)
                .border(
                    width = 1.dp,
                    color = Color(red = 230, green = 20, blue = 0),
                    shape = RoundedCornerShape(25.dp)
                ),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 230, green = 20, blue = 0)

            ),
        ) {
            Text("Select Product photo")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .padding(start = 78.dp)
                .height(200.dp)
                .width(230.dp)
                .background(
                    color = Color(red = 245, green = 245, blue = 245),
                    shape = RoundedCornerShape(25.dp),
                ),

            ) {
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberImagePainter(uri),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        clip = true,
                        shape = RoundedCornerShape(30.dp)
                    )
            )

        }
        val context = LocalContext.current
        Spacer(modifier = Modifier.height(8.dp))

        Button(

            onClick = {
                viewModel1.progressKey.value = true
                uri?.let {
                    viewModel1.uploadPhoto(
                        uri = it,
                        context = context,
                        type = "image"
                    ) { imageUrl ->
                        if (imageUrl != null) {
                            if (nameFood.value.isNotEmpty() && description.value.isNotEmpty() && price.value.isNotEmpty()
                                && rate.value.isNotEmpty()
                            ) {

                                viewModel1.addDetailsFood(
                                    nameFood.value,
                                    description.value,
                                    price.value,
                                    rate.value,
                                    imageUrl,
                                    AppData.phoneNumberProvider.toString()
                                ) { result ->
                                    if (result) {
                                        clearUI()
                                        Toast.makeText(
                                            context,
                                            "Item add successfully",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Item Not Add !!",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }


                            } else {
                                Toast.makeText(
                                    context,
                                    "Please select all missing",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
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
                if (viewModel1.progressKey.value){
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 4.dp)
                }else{
                TextBottom("Add")
                }

            }
        }

    }
}

fun clearUI() {
// clear all ui fields and images
}
