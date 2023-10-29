package com.kareem.yourkitchen.provider.auth.login

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.kareem.yourkitchen.R
import com.kareem.yourkitchen.base.db.PaperDB
import com.kareem.yourkitchen.provider.auth.viewModel.YourInfoProviderViewModel
import com.kareem.yourkitchen.utils.AppData
import com.kareem.yourkitchen.utils.TextBottom

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class, ExperimentalCoilApi::class)
@Composable
fun YourInfoProvideView(navController: NavController,viewModel: YourInfoProviderViewModel) {


    val myColor = Color(0xFFFFFF)
    val firstNameP = remember { mutableStateOf("") }
    val lastNameP = remember { mutableStateOf("") }
    val emailAddressP = remember { mutableStateOf("") }
    val phoneNumberP = remember { mutableStateOf("") }
    val storeName = remember { mutableStateOf("") }
    val showProgress = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val options = listOf(
        "Amman",
        "Zarqa",
        "Irbid",
        "Balqa",
        "Mafraq",
        "Jerash",
        "Ajloun",
        "Aqaba",
        "Madaba",
        "Karak",
        "Ma'an",
        "Tafilah"
    )
    var expanded by remember { mutableStateOf(false) }
    val storeaddress = remember { mutableStateOf(options[0]) }
    val options1 = listOf("Fast Food", "Seafood", "candies", "Natural juices")
    var expanded1 by remember { mutableStateOf(false) }
    val businesstype = remember { mutableStateOf("") }



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {


        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
            contentDescription = null,
            tint = Color(red = 230, green = 20, blue = 0),
            modifier = Modifier
                .size(70.dp)
                .padding(16.dp)
                .clickable {
                    navController.navigate("LoginProvider") {
                        popUpTo("LoginProvider")
                    }
                }
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "Your Info Provider",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 30.dp)
        )
        LazyColumn(

        ) {
            item {

                Spacer(modifier = Modifier.height(10.dp))



                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    },
                    modifier = Modifier.padding(start = 32.dp)
                ) {
                    TextField(
                        readOnly = true,
                        label = { Text("Select Kitchen address") },
                        value = storeaddress.value,
                        onValueChange = {},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Default
                        ),


                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .menuAnchor()

                            .height(60.dp)
                            .width(320.dp)
                            .border(
                                1.dp,
                                Color(red = 230, green = 20, blue = 0),
                                shape = RoundedCornerShape(25.dp)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = Color(red = 230, green = 20, blue = 0),
                            textColor = Color(red = 0, green = 0, blue = 0),
                            containerColor = myColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(25.dp)
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(color = Color.White)
                    ) {
                        options.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    storeaddress.value = item
                                    expanded = false
                                    // Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                }


                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = storeName.value,
                    onValueChange = { storeName.value = it },
                    modifier = Modifier
                        .padding(start = 32.dp)
                        .height(60.dp)
                        .width(320.dp)
                        .border(
                            1.dp,
                            Color(red = 230, green = 20, blue = 0),
                            shape = RoundedCornerShape(25.dp)
                        ),
                    label = { Text(text = "Enter Kitchen name", color = Color.Black) },
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
                Spacer(modifier = Modifier.height(15.dp))
                ExposedDropdownMenuBox(
                    expanded = expanded1,
                    onExpandedChange = {
                        expanded1 = !expanded1
                    }, modifier = Modifier.padding(start = 32.dp)
                ) {
                    TextField(
                        value = businesstype.value,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Select Cooking type") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded1) },
                        modifier = Modifier
                            .menuAnchor()
                            .height(60.dp)
                            .width(320.dp)
                            .border(
                                1.dp,
                                Color(red = 230, green = 20, blue = 0),
                                shape = RoundedCornerShape(25.dp)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color(red = 0, green = 0, blue = 0),
                            containerColor = myColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(25.dp)
                    )

                    ExposedDropdownMenu(
                        expanded = expanded1,
                        onDismissRequest = { expanded1 = false },
                        modifier = Modifier.background(color = Color.White)
                    ) {
                        options1.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    businesstype.value = item
                                    expanded1 = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    value = firstNameP.value,
                    onValueChange = { firstNameP.value = it },
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



                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    value = lastNameP.value,
                    onValueChange = { lastNameP.value = it },
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

                Spacer(modifier = Modifier.height(15.dp))
                var isEmailValid by remember { mutableStateOf(true) }
                var errorTextEmail by remember { mutableStateOf("") }

                TextField(
                    value = emailAddressP.value,
                    onValueChange = {
                        emailAddressP.value = it
                        isEmailValid =
                            it.matches(Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"))
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

                Spacer(modifier = Modifier.height(15.dp))


                // var phoneNumber by remember { mutableStateOf(TextFieldValue()) }
                var errorText by remember { mutableStateOf("") }

                TextField(
                    value = phoneNumberP.value,
                    onValueChange = {
                        // phoneNumberP.value = it

                        if (it.length <= 10) {
                            phoneNumberP.value = it
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



                Spacer(modifier = Modifier.height(10.dp))
                if (showProgress.value) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(35.dp)
                            .align(Alignment.CenterHorizontally)
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
                    Text("Select Your Kitchen photo")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .padding(start = 32.dp)
                        .height(200.dp)
                        .width(320.dp)
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
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(15.dp))



                Button(

                    onClick = {
                        viewModel.progressKey.value = true
                        uri?.let {
                            viewModel.uploadToStorage(
                                uri = it,
                                context = context,
                                type = "image"
                            ) { imageUrl ->
                                if (imageUrl != null) {
                                    if (firstNameP.value.isNotEmpty() && lastNameP.value.isNotEmpty() && emailAddressP.value.isNotEmpty()
                                        && phoneNumberP.value.isNotEmpty() && storeaddress.value.isNotEmpty() && storeName.value.isNotEmpty()
                                        && businesstype.value.isNotEmpty()
                                    ) {
                                        PaperDB.SetData(context)
                                            .setProviderPhone(phoneNumberP.value)
                                        AppData.phoneNumberProvider = phoneNumberP.value
                                        showProgress.value = true
                                        viewModel.saveInfoProvider(
                                            firstNameP.value,
                                            lastNameP.value,
                                            emailAddressP.value,
                                            phoneNumberP.value,
                                            storeaddress.value,
                                            storeName.value,
                                            businesstype.value,
                                            imageUrl

                                        )
                                        navController.navigate("VerificationCodeProvider")

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
                Spacer(modifier = Modifier.height(15.dp))


            }
        }


    }


}
