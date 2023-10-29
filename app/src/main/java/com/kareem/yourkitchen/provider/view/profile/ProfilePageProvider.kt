package com.kareem.yourkitchen.provider.view.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.kareem.yourkitchen.base.db.PaperDB
import com.kareem.yourkitchen.provider.viewModel.ProfilePageViewModel
import com.kareem.yourkitchen.utils.ArrowBack1

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun ProfilePageProvider(navController: NavController, profilePageViewModel: ProfilePageViewModel = viewModel(), ) {

    val context = LocalContext.current
    val getProfileData = profilePageViewModel.state.value
    Log.d("ddaa1", "data is$getProfileData")
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
            items(getProfileData) { name ->
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
                        text = "+962 ${name.phoneNumberP}",
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
        Row(
            modifier = Modifier.fillMaxWidth().clickable {
                navController.navigate("MyProfile")
            },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "My Profile")
            ArrowBack1 { }
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Language")
            ArrowBack1 {

            }
        }


        Row(
            modifier = Modifier.fillMaxWidth().clickable {navController.navigate("AboutProvider") },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "About Us")
            ArrowBack1 {

            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().clickable {
                PaperDB.SetData(context).setIsLoginProvider("false")
                navController.navigate("login")
            },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Logout")
            ArrowBack1 { }
        }


    }

}


