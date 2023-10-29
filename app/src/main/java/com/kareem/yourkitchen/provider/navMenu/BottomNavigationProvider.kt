package com.kareem.yourkitchen.provider.navMenu

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kareem.yourkitchen.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    var screen_route: String
) {
    object Home : BottomNavItem("Home", R.drawable.ic_home, "home")
    object AddPost : BottomNavItem("Menu", R.drawable.ic_food, "add_post")
    object Jobs : BottomNavItem("Profile", R.drawable.ic_profile, "Profile_setting")

}

@Composable
fun BottomNavigationProvider(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.AddPost,
        BottomNavItem.Jobs
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""




    val visibleRoutes = listOf(
        BottomNavItem.Home.screen_route,
        BottomNavItem.AddPost.screen_route,
        BottomNavItem.Jobs.screen_route
    )

   if (currentRoute in visibleRoutes) {
        BottomNavigation(
            backgroundColor = colorResource(id = R.color.white),
            modifier = Modifier
                .fillMaxWidth().height(60.dp).padding(bottom = 0.1.dp)
                .clip(RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp))
                .border(
                    width = 0.5.dp,
                    color =  Color(red = 150, green = 150, blue = 150),
                )
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.screen_route
                BottomNavigationItem(

                    icon = {
                        val iconTint = if (isSelected) {
                            Color(red = 240, green = 20, blue = 0)
                        } else {
                            Color(red = 150, green = 150, blue = 150)
                        }
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            tint = iconTint
                        )
                    },
                    label = { Text(text = item.title, fontSize = 9.sp) },
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}
