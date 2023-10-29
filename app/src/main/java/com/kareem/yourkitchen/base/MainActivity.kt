package com.kareem.yourkitchen.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kareem.yourkitchen.user.home.view.BottomNavigationUser
import com.kareem.yourkitchen.provider.navMenu.BottomNavigationProvider
import com.kareem.yourkitchen.nav.Nav

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
          //  val navController1 = rememberNavController()
            Scaffold(
                bottomBar = {
                    val  navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route ?: ""
                    if (currentRoute == "homeUser"//|| currentRoute == "add_postUser"
                        || currentRoute == "Profile_settingUser"||currentRoute=="Basket") {
                        BottomNavigationUser(navController)
                    } else if (currentRoute == "home"|| currentRoute == "add_post"|| currentRoute == "Profile_setting") {
                        BottomNavigationProvider(navController)
                    }
                },
                content = { padding ->
                    Nav(navController = navController)
                }
            )
        }
    }
}

