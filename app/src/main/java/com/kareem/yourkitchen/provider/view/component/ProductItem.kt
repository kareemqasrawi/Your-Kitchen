package com.kareem.yourkitchen.provider.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.kareem.yourkitchen.R
import com.kareem.yourkitchen.provider.navMenu.BottomNavItem
import com.kareem.yourkitchen.provider.viewModel.HomePageProviderViewModel
import com.kareem.yourkitchen.utils.AppData

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductItem(productName: String = "", desc: String = "", image:String? =null,price:String="",navController: NavController,homePageProviderViewModel: HomePageProviderViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .height(140.dp)
            .padding(top = 5.dp, bottom = 10.dp)
            .width(360.dp)
            .background(
                color = Color(red = 230, green = 230, blue = 230),
                shape = RoundedCornerShape(400.dp),
            )
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(25.dp),
            )
    ) {



        Row(
            Modifier
                .size(width = 360.dp, height = 140.dp)
                .background(Color.White)
                .padding(top = 2.dp)
                .clip(RoundedCornerShape(30.dp)),
            Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Spacer(modifier = Modifier.width(10.dp))

            val painterImage = rememberImagePainter(data = image, builder = {
                crossfade(true)
                placeholder(R.drawable.yourkitchen)
            })// optional placeholder

            Image(
                // optional error image
                painter = painterImage,
                contentDescription = "Description for accessibility",  // Describe the image for accessibility purposes
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))



                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 30.dp)
                ) {
                    Text(
                        text = productName,
                        modifier = Modifier
                            .background(Color.Transparent),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    )
                    Text(
                        text = desc,
                        modifier = Modifier
                            .background(Color.Transparent),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Thin,
                        fontSize = 12.sp,
                    )
                }
            }



        androidx.compose.material3.Icon(
            painter = painterResource(id = R.drawable.baseline_delete_24),
            contentDescription = "Delete Icon",
            modifier = Modifier
                .clickable {
                    homePageProviderViewModel.deleteProduct(
                        AppData.phoneNumberProvider,productName.toString(),
//                        onDeleteSuccess = {
//
//                        }
                        ){
                       // if (it){
                            navController.navigate(BottomNavItem.AddPost.screen_route)
                    //}
                    }
                }
                .padding(top = 10.dp, start = 310.dp),
            tint = Color.Red
        )
        Text(
            text = "JOD$price",
            modifier = Modifier
                .background(Color.Transparent)
                .padding(top = 90.dp, start = 280.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
        )

    }
}