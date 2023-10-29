package com.kareem.yourkitchen.user.home.view

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.kareem.yourkitchen.R

@Composable
//@Preview
fun Favorite(
    navController: NavHostController
) {
    LazyColumn(
        modifier = Modifier
            .height(680.dp)
            .width(400.dp)
    ) {
        item {

            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .padding(start = 25.dp, top = 10.dp)
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
                    )
//                    .shadow(
//                        elevation = 1.dp,
//                        shape = RoundedCornerShape(25.dp),
//                    )
                     ,
                contentAlignment = Alignment.Center
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

                        // if (name.imagePath.isNotEmpty()) {
                        Image(
                            painter = rememberImagePainter(data = R.drawable.yourkitchen),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer(
                                    clip = true,
                                    shape = RoundedCornerShape(30.dp)
                                ),
                            contentScale = ContentScale.Crop
                        )

                        // }
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_favorite_24),
                            contentDescription = "Delete Icon",
                            modifier = Modifier.size(35.dp),
                            tint = Color.Red
                        )


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
                                text = "name.storeName",
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
                                text = "name.businesstype",
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 18.dp, end = 8.dp)
                                    .background(Color.Transparent),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                            )
                            Text(
                                text = "name.storeaddress",
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


        }
    }
}