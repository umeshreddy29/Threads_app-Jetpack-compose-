package com.example.threadyoutube.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.threadyoutube.screens.AddThreads
import com.example.threadyoutube.screens.BottomNav
import com.example.threadyoutube.screens.Home
import com.example.threadyoutube.screens.Login
import com.example.threadyoutube.screens.Notification
import com.example.threadyoutube.screens.OtherUser
import com.example.threadyoutube.screens.Profile
import com.example.threadyoutube.screens.Register
import com.example.threadyoutube.screens.Search
import com.example.threadyoutube.screens.Splash

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.Splash.routes)
    {
        composable(Routes.Splash.routes) {
            Splash(navController)
        }
        composable(Routes.Home.routes) {
            Home(navController)
        }
        composable(Routes.Notification.routes) {
            Notification()
        }
        composable(Routes.Search.routes) {
            Search(navController)
        }
        composable(Routes.Profile.routes) {
            Profile(navController)
        }
        composable(Routes.AddThreads.routes) {
            AddThreads(navController)
        }
        composable(Routes.BottomNav.routes) {
            BottomNav(navController)
        }
        composable(Routes.Login.routes) {
            Login(navController)
        }
        composable(Routes.Register.routes) {
            Register(navController)
        }
        composable(Routes.OtherUsers.routes) {
            val data = it.arguments!!.getString("data")
            OtherUser(navController, data!!)

        }
    }
}