package com.kareem.yourkitchen.user.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import coil.compose.rememberImagePainter
import com.kareem.yourkitchen.R
import com.kareem.yourkitchen.provider.auth.viewModel.YourInfoProviderViewModel
import com.kareem.yourkitchen.provider.viewModel.HomePageProviderViewModel


var dataStore: YourInfoProviderViewModel.About = YourInfoProviderViewModel.About()

@Composable
fun Product(
    navController: NavController, providerViewModel: HomePageProviderViewModel = viewModel()
) {
    val productList = providerViewModel.getProductList(dataStore.phoneNumberP).value
    Column(Modifier.fillMaxHeight()) {

        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "",
            modifier = Modifier
                .width(400.dp)
                .height(256.dp)

        )
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "Trending",
            color = Color(red = 0, green = 0, blue = 0),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 25.dp)
        )

        LazyColumn(modifier = Modifier.width(400.dp).height(500.dp)) {
            items(productList) { item ->
                ProductItemForUser(
                    item!!.ProviderId.toString(),
                    item.PhotoFood.toString(),
                    item.FoodName.toString(), item.Description.toString(), item.Price.toString()
                )
            }
        }
    }


    DetailsBox()


}


@Composable
fun DetailsBox() {
    Box(
        modifier = Modifier
            .background(color = Color.Transparent)
            .width(400.dp)
            .height(330.dp)
            .padding(start = 15.dp, top = 120.dp, end = 15.dp)
            .background(
                color = Color(red = 255, green = 255, blue = 255),
                shape = RoundedCornerShape(25.dp),
            )
            .border(
                width = 0.5.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(25.dp)
            ),
    ) {
        Column() {


            Row() {
                Image(
                    // optional error image
                    painter = rememberImagePainter(dataStore.imagePath),
                    contentDescription = "",  // Describe the image for accessibility purposes
                    modifier = Modifier
                        .width(100.dp)
                        .height(90.dp) .padding(start = 10.dp, top = 8.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
//                Image(
//                    painter = rememberImagePainter(dataStore.imagePath),
//                    contentDescription = "",
//                    modifier = Modifier
//                        .width(100.dp)
//                        .height(100.dp)
//                        .padding(start = 10.dp)
//                        .clip(RoundedCornerShape(15.dp)),
//
//                    )
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 25.dp, start = 10.dp)
                ) {
                    Text(
                        text = dataStore.storeName,
                        modifier = Modifier
                            .background(Color.Transparent),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                    )
                    Text(
                        text = dataStore.businesstype,
                        modifier = Modifier
                            .background(Color.Transparent),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Thin,
                        fontSize = 20.sp,
                    )
                }

            }
        }


        Row() {


            Column(
                modifier = Modifier.padding(top = 140.dp, start = 25.dp)
            ) {


                Text(
                    text = "Delivery fee",
                    modifier = Modifier
                        .background(Color.Transparent),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Thin,
                    fontSize = 15.sp,
                )
                Text(
                    text = "JOD 1.00",
                    modifier = Modifier
                        .background(Color.Transparent),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                )


            }
            Spacer(modifier = Modifier.width(15.dp))
            Divider(
                color = Color(red = 230, green = 20, blue = 0),
                thickness = 3.dp,
                modifier = Modifier
                    .height(190.dp)
                    .width(1.dp)
                    .padding(top = 140.dp)
            )
            Column(
                modifier = Modifier.padding(top = 140.dp, start = 15.dp)
            ) {


                Text(
                    text = "Delivery time",
                    modifier = Modifier
                        .background(Color.Transparent),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Thin,
                    fontSize = 15.sp,
                )
                Text(
                    text = "28 mins",
                    modifier = Modifier
                        .background(Color.Transparent),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                )


            }
            Spacer(modifier = Modifier.width(15.dp))
            Divider(
                color = Color(red = 230, green = 20, blue = 0),
                thickness = 3.dp,
                modifier = Modifier
                    .height(190.dp)
                    .width(2.dp)
                    .padding(top = 140.dp, start = 1.dp)
            )

            Column(
                modifier = Modifier.padding(top = 140.dp, start = 15.dp)
            ) {


                Text(
                    text = "Delivered by",
                    modifier = Modifier
                        .background(Color.Transparent),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Thin,
                    fontSize = 15.sp,
                )
                Text(
                    text = "Your Kitchen",
                    modifier = Modifier
                        .background(Color.Transparent),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )


            }


        }
    }

}

