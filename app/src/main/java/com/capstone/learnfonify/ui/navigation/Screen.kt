package com.capstone.learnfonify.ui.navigation


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Stored : Screen("stored")
    object Profile : Screen("profile")
    object Register : Screen("register")
    object SplashLogin : Screen("splash-login")
}