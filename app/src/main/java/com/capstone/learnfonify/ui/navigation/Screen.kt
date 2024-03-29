package com.capstone.learnfonify.ui.navigation


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Saved : Screen("saved")
    object Profile : Screen("profile")
    object Register : Screen("register")
    object SplashLogin : Screen("splash-login")
    object Community : Screen("community")
    object More : Screen("home/{categoryCourses}") {
        fun createRoute(categoryCourses: String) = "home/$categoryCourses"
    }
    object DetailCourse : Screen("home/{courseId}") {
        fun createRoute(courseId: Int) = "home/$courseId"
    }
}