package com.kareem.yourkitchen.base.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kareem.yourkitchen.R
import com.kareem.yourkitchen.base.db.PaperDB
import com.kareem.yourkitchen.provider.navMenu.BottomNavItem
import com.kareem.yourkitchen.utils.AppData
import com.kareem.yourkitchen.utils.AppData1
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        delay(5000L)


        when {
            PaperDB.GetData(context)
                .getIsLoginProvider<String>().toString() == "true" -> {
                navController.navigate(BottomNavItem.Home.screen_route)

                AppData.phoneNumberProvider =
                    PaperDB.GetData(context).getProviderPhone<String>().toString()

            }

            PaperDB.GetData(context).getIsLoginUser<String>().toString() == "true" -> {
                navController.navigate("homeUser")
                AppData1.phoneNumberUser =
                    PaperDB.GetData(context).getUserPhone<String>().toString()
            }

            else -> {
                navController.navigate("Login")
            }
        }


    }
//    Box(
//        modifier = Modifier
//            .fillMaxSize().width(500.dp).height(500.dp)
//            .background(color = Color(red = 240, green = 20, blue = 0))
//    ) {
//
//    }

    Image(
        painter = painterResource(id = R.drawable.splachscreen),
        contentDescription = "Splash Screen Image",
        modifier = Modifier
            .fillMaxSize()
           // .background(Color.White) // Set a white background to change the image color
          //  .then(Modifier.colorFilter(ColorFilter.tint(Color.White))) // Apply a color filter to make the image white
    )


}
