package com.kareem.yourkitchen.provider.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.kareem.yourkitchen.provider.viewModel.ProfilePageViewModel
import com.kareem.yourkitchen.R
import com.kareem.yourkitchen.dto.ProductDto
import com.kareem.yourkitchen.provider.view.component.ProductItem
import com.kareem.yourkitchen.provider.viewModel.HomePageProviderViewModel
import com.kareem.yourkitchen.utils.AppData.phoneNumberProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageProvider(navController: NavController,profilePageViewModel: ProfilePageViewModel = viewModel(), homePageProviderViewModel: HomePageProviderViewModel = viewModel()) {
    val getProfileData = profilePageViewModel.state.value


    Column(Modifier.fillMaxHeight()) {
        LazyColumn(

        ) {
            items(getProfileData) { name ->
                Box(
                    modifier = Modifier
                        .width(450.dp)
                        .height(270.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(data = name.imagePath),
                        contentDescription = "",
//                    modifier = Modifier
//                        .width(500.dp)
//                        .height(256.dp)

                    )
                }
            }
        }
//        Image(
//            painter = painterResource(id = R.drawable.open_now),
//            contentDescription = null,
//            modifier = Modifier
//                .size(200.dp)
//        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.height(5.dp))
            LazyColumn(
                verticalArrangement = Arrangement.Top
            ) {
                items(getProfileData) { name ->
                    Row() {
                        Column() {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(start = 3.dp)
                            ) {
                                Text(
                                    text = name.storeName,
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .background(Color.Transparent),
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp,
                                )


                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_location_on_24),
                                    contentDescription = null,
                                    tint = Color(red = 240, green = 20, blue = 0),
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(start = 16.dp)
                                )

                                Text(
                                    text = name.storeaddress,
                                    modifier = Modifier
                                        .background(Color.Transparent),
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                    }

                }

            }

        }
  Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, top = 10.dp)
        )
        {

            MyApp(navController =navController )

        }

    }
    Spacer(modifier = Modifier.height(15.dp))

}

@Composable
fun FoodListDisplay(foods: List<ProductDto?>,navController: NavController) {
    LazyColumn (
        modifier = Modifier
            .padding(top = 5.dp, bottom = 50.dp, end = 5.dp, start = 5.dp)
            .wrapContentHeight()
            .width(400.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start){
        item{

                Image(
                    painter = painterResource(id = R.drawable.offer),
                    contentDescription = "",
                    modifier = Modifier
                        .width(360.dp)
                        .height(90.dp)
                        .clip(RoundedCornerShape(25.dp))
                        ,

                    )

            Spacer(modifier = Modifier.height(5.dp))
            Text(
            text = "My products",
            modifier = Modifier
                .background(Color.Transparent),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
        )
            Spacer(modifier = Modifier.height(15.dp))
        }
        items(foods) { food ->
            ProductItem(food?.FoodName.toString(),food?.Description.toString(),food?.PhotoFood,food?.Price.toString(), navController = navController)
            Spacer(modifier = Modifier.width(205.dp))
        }


    }

}

@Composable
fun MyApp(navController: NavController,homePageProviderViewModel: HomePageProviderViewModel = viewModel()) {
    val foods =  homePageProviderViewModel.getProductList(phoneNumberProvider).value

    FoodListDisplay(foods,navController)
    Spacer(modifier = Modifier.height(15.dp))

}

