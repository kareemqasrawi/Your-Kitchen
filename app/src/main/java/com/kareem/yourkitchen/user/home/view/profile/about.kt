package com.kareem.yourkitchen.user.home.view.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kareem.yourkitchen.R
import com.kareem.yourkitchen.provider.navMenu.BottomNavItem
import com.kareem.yourkitchen.user.home.view.BottomNavItem1
import com.kareem.yourkitchen.utils.ArrowBack

@Composable
fun AboutUser(faqList: List<FaqItemData>,navController: NavController) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ArrowBack {
            navController.navigate(BottomNavItem1.JobsUser.screen_route){
                popUpTo(BottomNavItem1.JobsUser.screen_route)
            }
        }
        Spacer(modifier = Modifier.width(100.dp))
        Text(
            text = "FAQ ",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

    }

    LazyColumn(modifier = Modifier.padding(top = 70.dp)) {
        items(faqList) { faqItem ->
            val expanded = remember { mutableStateOf(false) }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {

                Column(modifier = Modifier.padding(top = 16.dp)) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                            .border(0.5.dp, Color.Gray)
                            .background(color = Color(red = 235, green = 235, blue = 235))
                            .clickable { expanded.value = !expanded.value },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = faqItem.question,
                                modifier = Modifier
                                .weight(1f)
                                .padding(start = 10.dp, top = 16.dp)
                        )

                        IconButton(
                            onClick = { expanded.value = !expanded.value }
                        ) {
                            Icon(
                                modifier = Modifier.padding(bottom = 5.dp),
                                imageVector = if (expanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = Color(red = 240, green = 20, blue = 0),
                            )
                        }
                    }
                }
                if (expanded.value) {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .border(0.5.dp, Color.Gray)
                    ) {
                        Text(
                            text = faqItem.answer,
                            modifier = Modifier
                                .padding(16.dp)

                        )
                    }
                }
            }
        }
    }
}





@Composable
fun AboutProvider(faqList: List<FaqItemData>,navController: NavController) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ArrowBack {
            navController.navigate(BottomNavItem.Jobs.screen_route){
                popUpTo(BottomNavItem.Jobs.screen_route)
            }
        }
        Spacer(modifier = Modifier.width(100.dp))
        Text(
            text = "FAQ ",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

    }

    LazyColumn(modifier = Modifier.padding(top = 70.dp)) {
        items(faqList) { faqItem ->
            val expanded = remember { mutableStateOf(false) }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {

                Column(modifier = Modifier.padding(top = 16.dp)) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                            .border(0.5.dp, Color.Gray)
                            .background(color = Color(red = 235, green = 235, blue = 235))
                            .clickable { expanded.value = !expanded.value },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = faqItem.question,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 10.dp, top = 16.dp)
                        )

                        IconButton(
                            onClick = { expanded.value = !expanded.value }
                        ) {
                            Icon(
                                modifier = Modifier.padding(bottom = 5.dp),
                                imageVector = if (expanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = Color(red = 240, green = 20, blue = 0),
                            )
                        }
                    }
                }
                if (expanded.value) {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .border(0.5.dp, Color.Gray)
                    ) {
                        Text(
                            text = faqItem.answer,
                            modifier = Modifier
                                .padding(16.dp)

                        )
                    }
                }
            }
        }
    }
}




data class FaqItemData(val question: String, val answer: String)

val faqData = listOf(
    FaqItemData("What is Your kitchen?", "Your kitchen is a leading online food delivery service that operates in Jordan.\n" +
            "\n" +
            "We seamlessly connect customers with their favorite restaurants. It takes just few taps from our platform to place an order through Your kitchen from your favorite place.\n"),

    FaqItemData("What does Your kitchen do?", "We simply take your submitted order and send it to the restaurant or Kitchen through a completely automated process, so you don’t have to deal with all the hassle of ordering and we make sure that you receive your order on time, every-time!2"),

    FaqItemData("Why should I use Your kitchen on a phone?", "Your Kitchen is the one huge food court for many restaurants and kitchens, so you don’t need to go through the hassle of finding restaurants’ numbers, waiting on hold, or getting a busy signal while dialing a restaurant or kitchen number, or getting the wrong order due to miscommunication over the phone! Besides, by using Your kitchen, you can view menus with pictures of all your favorite restaurants on our easy-to-use app."),
    FaqItemData("How much will it cost me to use Your kitchen services?", "The only extra charges that might be applied are the restaurant's or kitchen's delivery fees."),
    FaqItemData("Do you have special offers?", "Yes. You can view the latest restaurant promotions and discount coupon in offers tab."),
    FaqItemData("How do I place an order on Your kitchen?", "Go to Your kitchen app, log in with your account, then place an order from your favorite restaurant or kitchen."),

)
