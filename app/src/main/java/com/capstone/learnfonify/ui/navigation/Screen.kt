package com.capstone.learnfonify.ui.navigation


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Stored : Screen("stored")
    object Profile : Screen("profile")
    object Login : Screen("login")
    object Register : Screen("register")
}