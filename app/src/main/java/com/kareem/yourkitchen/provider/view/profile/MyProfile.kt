package com.kareem.yourkitchen.provider.view.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.kareem.yourkitchen.provider.navMenu.BottomNavItem
import com.kareem.yourkitchen.provider.viewModel.ProfilePageViewModel
import com.kareem.yourkitchen.utils.ArrowBack

@Composable
fun MyProfile(navController:NavController,profilePageViewModel: ProfilePageViewModel = viewModel()) {
    val getData1 = profilePageViewModel.state.value

//    Text(
//        text = "My Profile",
//        fontSize = 28.sp,
//        fontWeight = FontWeight.Bold,
//        modifier = Modifier.padding(start = 30.dp)
//    )
    ArrowBack {
        navController.navigate(BottomNavItem.Jobs.screen_route){
            popUpTo(BottomNavItem.Jobs.screen_route)
        }   }
    Column(
        modifier = Modifier
            .padding(start = 10.dp, top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .height(680.dp)
                .width(400.dp)
        ) {
            items(getData1) { name ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(160.dp)
                            .clip(RoundedCornerShape(25.dp))
                            .background(
                                color = Color(red = 250, green = 250, blue = 250),
                                shape = RoundedCornerShape(25.dp),
                            ),
                    ) {
                        if (name.imagePath.isNotEmpty()) {
                            Image(
                                painter = rememberImagePainter(data = name.imagePath),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .graphicsLayer(
                                        clip = true,
                                        shape = RoundedCornerShape(30.dp)
                                    ),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(7.dp))


                    Text(
                        text = name.storeName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                    Text(
                        text = name.storeaddress,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp)
                            .background(Color.Transparent),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                    )
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn {
            items(getData1) { name ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "My Name")
                    Row(
                        modifier = Modifier.padding(end = 25.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = name.firstNameP,
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                        Text(
                            text = name.lastNameP,
                            color = Color.Black,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }}

                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Phone")
                        Text(
                            text = "+962 ${name.phoneNumberP}",
                            modifier = Modifier.padding(end = 25.dp),
                            color = Color.Black

                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Email")
                        Text(
                            text = name.emailAddressP,
                            modifier = Modifier.padding(end = 25.dp),
                            color = Color.Black
                        )
                    }
                }
            }


        }



}