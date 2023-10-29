package com.kareem.yourkitchen.nav


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kareem.yourkitchen.provider.view.home.HomePageProvider

import com.kareem.yourkitchen.provider.viewModel.AddPostProviderViewModel
import com.kareem.yourkitchen.user.home.view.BottomNavItem1
import com.kareem.yourkitchen.user.home.view.HomePageUser
import com.kareem.yourkitchen.user.home.view.profile.MyProfileUser
import com.kareem.yourkitchen.user.home.view.profile.ProfilePageUser
import com.kareem.yourkitchen.base.view.Login
import com.kareem.yourkitchen.user.auth.login.LoginUser
import com.kareem.yourkitchen.base.view.SplashScreen
import com.kareem.yourkitchen.provider.navMenu.BottomNavItem
import com.kareem.yourkitchen.provider.view.profile.MyProfile
import com.kareem.yourkitchen.provider.view.profile.ProfilePageProvider
import com.kareem.yourkitchen.provider.view.addProduct.ShowPostProvider
import com.kareem.yourkitchen.user.auth.VerificationCodeScreen
import com.kareem.yourkitchen.user.auth.login.YourInfoPage
import com.kareem.yourkitchen.user.auth.viewModel.YourInfoViewModel
import com.kareem.yourkitchen.provider.auth.viewModel.YourInfoProviderViewModel
import com.kareem.yourkitchen.provider.auth.login.LoginProvider
import com.kareem.yourkitchen.provider.auth.login.YourInfoProvideView
import com.kareem.yourkitchen.provider.auth.VerificationCodeProvider
import com.kareem.yourkitchen.user.auth.viewModel.MyAddressViewModel
import com.kareem.yourkitchen.user.home.view.Favorite
import com.kareem.yourkitchen.user.home.view.Product
import com.kareem.yourkitchen.user.home.view.basket.Basket
import com.kareem.yourkitchen.user.home.view.map.Map
import com.kareem.yourkitchen.user.home.view.basket.MyAddress

import com.kareem.yourkitchen.user.home.view.profile.AboutProvider
import com.kareem.yourkitchen.user.home.view.profile.AboutUser
import com.kareem.yourkitchen.user.home.view.profile.SivePhotoUser
import com.kareem.yourkitchen.user.home.view.profile.faqData
import com.kareem.yourkitchen.utils.AppData

@Composable
fun Nav(navController: NavHostController , context: Context = LocalContext.current) {

    val startDestination = remember { mutableStateOf("SplashScreen") }

    NavHost(navController = navController, startDestination = startDestination.value) {
        composable("SplashScreen") {
            SplashScreen(navController = navController)
        }
        composable("Login") {
            Login(navController = navController)
            startDestination.value = "Login"
        }

        composable("LoginUser") {
            LoginUser(navController = navController, YourInfoViewModel())
        }

        composable("LoginProvider") {
            LoginProvider(navController = navController, viewModel = YourInfoProviderViewModel())
        }

        composable("YourInfoPage") {
            YourInfoPage(navController = navController, viewModel = YourInfoViewModel())
        }

        composable("YourInfoProvideView") {
            YourInfoProvideView(
                navController = navController,
                viewModel = YourInfoProviderViewModel()
            )
        }

        composable("VerificationCodeScreen") {
            VerificationCodeScreen(navController = navController)
        }

        composable("VerificationCodeProvider") {
            VerificationCodeProvider(navController = navController)
        }


        composable(route = BottomNavItem.Home.screen_route) {
            HomePageProvider(navController)
            startDestination.value = BottomNavItem.Home.screen_route
        }

        composable(route = BottomNavItem.AddPost.screen_route) {
            ShowPostProvider(viewModel1 = AddPostProviderViewModel(AppData.phoneNumberProvider))
        }
        composable(route = BottomNavItem.Jobs.screen_route) {
            ProfilePageProvider(navController = navController)
        }

        composable(route = BottomNavItem1.HomeUser.screen_route) {
            HomePageUser(navController = navController)
            startDestination.value = BottomNavItem1.HomeUser.screen_route
        }

//        composable(route = BottomNavItem1.AddPostUser.screen_route) {
//            Favorite(navController)
//        }
        composable(route = BottomNavItem1.Basket.screen_route) {
            Basket(navController)
        }
        composable(route = "Map") {
            Map(navController = navController)
        }
        composable(route = "MyAddress") {
            MyAddress(navController = navController, viewModel = MyAddressViewModel())
        }
        composable(route = "Product") {
            Product(navController = navController)
        }

        composable(route = BottomNavItem1.JobsUser.screen_route) {
            ProfilePageUser(navController = navController)
        }
        composable("MyProfile") {
            MyProfile(navController = navController)
        }
        composable("MyProfileUser") {
            MyProfileUser(navController = navController, viewModel = SivePhotoUser())
        }
        composable("AboutUser") {
            AboutUser(faqData,navController=navController)
        }
        composable("AboutProvider") {
            AboutProvider(faqData,navController=navController)
        }

    }
}
