package com.kareem.yourkitchen.user.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kareem.yourkitchen.R
import com.kareem.yourkitchen.provider.auth.viewModel.YourInfoProviderViewModel

@Composable
fun HomePageUser(navController: NavController,yourInfoProviderViewModel: YourInfoProviderViewModel = viewModel()
                 ) {

    val getYourInfoPData = yourInfoProviderViewModel.state.value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_format_align_right_24),
                contentDescription = null,
                tint = Color(red = 230, green = 20, blue = 0),
                modifier = Modifier
                    .size(70.dp)
                    .padding(16.dp)
                    .clickable {
                    }
            )

            Text(
                text = "Home ",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

        }

        LazyColumn(
            modifier = Modifier
                .height(680.dp)
                .width(400.dp)
        ) {
            item {

                Text(
                    text = "Ready to order\nyour favorite food?",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 30.dp)
                )

                Spacer(modifier = Modifier.height(15.dp))

                Box(
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .height(170.dp)
                        .width(335.dp)
                        .background(
                            color = Color(red = 230, green = 230, blue = 230),
                            shape = RoundedCornerShape(25.dp),
                        ).shadow(
                            elevation = 20.dp,
                            shape = RoundedCornerShape(25.dp),
                        ).clickable {
                             navController.navigate("Product")  },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.panner ),
                        contentDescription = "Description for accessibility",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(25.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Popular Food ",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 30.dp),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            items(getYourInfoPData) { name ->
                UserHomeStoreItems(navController,name)
            }


        }



    }

}




