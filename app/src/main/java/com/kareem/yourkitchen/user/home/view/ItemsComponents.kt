package com.kareem.yourkitchen.user.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.kareem.yourkitchen.R
import com.kareem.yourkitchen.provider.auth.viewModel.YourInfoProviderViewModel
import com.kareem.yourkitchen.dto.ProductDto
import com.kareem.yourkitchen.user.home.viewModel.BasketViewModel
import com.kareem.yourkitchen.utils.AppData1
import com.kareem.yourkitchen.utils.TextBottom

@Composable
fun UserHomeStoreItems(navController: NavController, name: YourInfoProviderViewModel.About) {
    Spacer(modifier = Modifier.height(15.dp))
    Row(
        modifier = Modifier
            .clickable {
                dataStore = name
                navController.navigate("Product")

            }
            .padding(start = 25.dp)
            .height(300.dp)
            .width(340.dp)
            .border(
                width = 0.2.dp,
                color = Color(red = 200, green = 200, blue = 200),
                shape = RoundedCornerShape(25.dp)
            )
            .background(
                color = Color(red = 255, green = 255, blue = 255),
                shape = RoundedCornerShape(25.dp),
            ),
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(210.dp)
                    .width(430.dp)
                    .padding(start = 7.dp, end = 7.dp)
                    .background(
                        color = Color.Transparent,
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
//                Icon(
//                    painter = painterResource(id = R.drawable.baseline_favorite_24),
//                    contentDescription = "Delete Icon",
//                    modifier = Modifier.size(35.dp),
//                    tint = Color.Red
//                )



            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = name.storeName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 18.dp, end = 8.dp)
                            .background(Color.Transparent),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = name.businesstype,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 18.dp, end = 8.dp)
                            .background(Color.Transparent),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    )
                    Text(
                        text = name.storeaddress,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 80.dp)
                            .background(Color.Transparent),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    )

                }

            }
        }
    }



    Spacer(modifier = Modifier.height(15.dp))
}


@Composable
fun ProductItemForUser(providerID:String, image:String, productName:String, desc:String, price:String, basketViewModel: BasketViewModel = viewModel()) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .height(140.dp)
            .width(360.dp)
            .padding(start = 25.dp, top = 10.dp)
            .background(
                color = Color(red = 230, green = 230, blue = 230),
                shape = RoundedCornerShape(400.dp),
            )
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(25.dp),
            )
    ) {



        Row(
            Modifier
                .size(width = 360.dp, height = 130.dp)
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
        Button(

            onClick = { },

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 230, green = 20, blue = 0)
            ),
            modifier = Modifier
                .padding(top = 10.dp, start = 250.dp, end = 10.dp)
                .height(35.dp)
                .width(150.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(
                modifier = Modifier.clickable {
                 basketViewModel.saveProduct(AppData1.phoneNumberUser, ProductDto(productName,desc,image,price,"5",providerID),context)
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                TextBottom("add")

            }
        }


        Text(
            text = price,
            modifier = Modifier
                .background(Color.Transparent)
                .padding(top = 97.dp, start = 275.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )



    }
}