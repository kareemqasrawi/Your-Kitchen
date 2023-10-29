package com.kareem.yourkitchen.user.home.view.profile

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.kareem.yourkitchen.base.db.PaperDB
import com.kareem.yourkitchen.user.home.viewModel.ProfilePageUserViewModel
import com.kareem.yourkitchen.utils.ArrowBack1

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfilePageUser(navController: NavHostController, profilePageUserViewModel: ProfilePageUserViewModel = viewModel(),sivePhotoUser: SivePhotoUser= viewModel()) {
    val getPhoto =sivePhotoUser.state.value
    Log.d("Photo12345125","$getPhoto")
    val context = LocalContext.current
    val getProfileUserData = profilePageUserViewModel.state1.value
    val uri by remember {
        mutableStateOf<Uri?>(null)
    }

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
            items(getProfileUserData) { name ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .clip(RoundedCornerShape(75.dp))
                            .background(
                                color = Color(red = 220, green = 220, blue = 220),
                                shape = RoundedCornerShape(25.dp),
                            ),
                    ) {

                            Image(
                                painter = rememberImagePainter(getPhoto),
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
                    Spacer(modifier = Modifier.height(7.dp))


                    Text(
                        text = name.firstName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "+962 ${name.phoneNumber}",
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
        Spacer(modifier = Modifier.height(170.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("MyProfileUser")

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
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("AboutUser")

                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "About Us")
            ArrowBack1 {}
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    PaperDB
                        .SetData(context)
                        .setIsLoginUser("false")
                    navController.navigate("Login")
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Logout")
            ArrowBack1 { }
        }
        Spacer(modifier = Modifier.height(70.dp))


    }
}