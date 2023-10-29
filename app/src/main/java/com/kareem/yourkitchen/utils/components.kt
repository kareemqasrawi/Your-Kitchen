package com.kareem.yourkitchen.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.kareem.yourkitchen.R

@Composable
fun ArrowBack( action: () -> (Unit)){
    Icon(
        painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
        contentDescription = null,
        tint = Color(red = 230, green = 20, blue = 0),
        modifier = Modifier
            .size(70.dp)
            .padding(16.dp)
            .clickable {

                action()

            }
    )
}

@Composable
fun ArrowBack1( action: () -> (Unit)){
    Icon(
        painter = painterResource(id = R.drawable.ic_right),
        contentDescription = null,
        tint = Color(red = 240, green = 20, blue = 0),
        modifier = Modifier
            .size(60.dp)
            .padding(16.dp)
            .clickable {
                action()

            })
}
@Composable
fun TextBottom(Text:String){

    Text(text =Text)
}

object AppData {
    var phoneNumberProvider: String = ""
   // Log.d("ph4444","data is:${phoneNumber1}")
}

object AppData1 {
    var phoneNumberUser: String = ""
    // Log.d("ph4444","data is:${phoneNumber1}")
}
