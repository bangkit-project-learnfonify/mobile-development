package com.capstone.learnfonify.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.capstone.learnfonify.ui.navigation.Screen
import com.capstone.learnfonify.ui.pages.home.HomePage
import com.capstone.learnfonify.ui.pages.profile.ProfilePage
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.learnfonify.ui.pages.login.LoginInViewModel
import com.capstone.learnfonify.ui.pages.register.RegisterPage
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.capstone.learnfonify.data.ViewModelFactory
import com.capstone.learnfonify.ui.components.BottomBar
import com.capstone.learnfonify.ui.components.DetailBar
import com.capstone.learnfonify.ui.pages.coursedetail.CourseDetailPage
import com.capstone.learnfonify.ui.pages.register.RegisterViewModel
import com.capstone.learnfonify.ui.pages.saved.SavedContent
import com.capstone.learnfonify.ui.pages.saved.SavedPage
import com.capstone.learnfonify.ui.pages.splashscreen.LearnFornifySplashScreen
import com.kyy47.kyyairlines.common.UiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnFornifyApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) {
    var isLogin by remember {
        mutableStateOf(false)
    }
    val loginViewModel: LoginInViewModel = viewModel(factory = ViewModelFactory.getInstance(context))


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            when (currentRoute) {
                Screen.SplashLogin.route -> {}
                Screen.DetailCourse.route -> {
                }
                Screen.Register.route -> {
                }

                else -> BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (!isLogin) Screen.SplashLogin.route else Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomePage(username = "Users", urlProfile = "", onNagivateToDetail = { id ->
                    navController.navigate(Screen.DetailCourse.createRoute(id))
                }, context = context)

            }
            composable(Screen.Saved.route) {
                SavedPage(
                    context,
                    onNagivateToDetail = {id ->
                        navController.navigate(Screen.DetailCourse.createRoute(id))
                    }
                    )
            }
            composable(
                Screen.DetailCourse.route,
                arguments = listOf(navArgument("courseId") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("courseId") ?: 12
                CourseDetailPage(courseId = id, context = context)
            }
            composable(Screen.Profile.route) {

                val userId =  loginViewModel.getUserIdSession()
                ProfilePage(
                    navController = navController,
                    id = userId,
                    context = context
                )
            }
            composable(Screen.SplashLogin.route) {



                loginViewModel.getIsAuthLogin()


                LearnFornifySplashScreen(
                    onRegisterClick = {
                        navController.navigate(Screen.Register.route)
                    },
                    onSignInClick = { /*TODO*/ },
                    onLoginWithEmailClick = {email, password ->
                        loginViewModel.loginWithEmail(email,password)
                    }
                )

                loginViewModel.isLogin.collectAsState(
                    initial = UiState.Loading
                ).value.let { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                        }

                        is UiState.Success -> {
                            LaunchedEffect(key1 =  "success"){
                                if(uiState.data){
                                    isLogin = uiState.data
                                }
                            }

                        }

                        is UiState.Error -> {

                        }
                    }
                }

                loginViewModel.loginState.collectAsState(
                    initial = UiState.Loading
                ).value.let { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {

                        }

                        is UiState.Success -> {
                            Toast.makeText(context,"Login Success", Toast.LENGTH_SHORT).show()
                            LaunchedEffect(key1 = uiState.data.email ){
                                loginViewModel.setLoginState(UiState.Loading)
                            }
                            navController.navigate(Screen.Home.route)
                        }

                        is UiState.Error -> {
                            Toast.makeText(context,"Login Failed", Toast.LENGTH_SHORT).show()
                            LaunchedEffect(key1 =  "error"){
                                loginViewModel.setLoginState(UiState.Loading)
                            }
                        }
                    }
                }





            }
            composable(Screen.Register.route) {
                val registerViewModel: RegisterViewModel = viewModel(factory = ViewModelFactory.getInstance(context))

                RegisterPage(onRegisterWithEmailClick = {username, email, password->
                    registerViewModel.registerWithEmail(username, email, password, password)
                })

                registerViewModel.registerState.collectAsState(
                    initial = UiState.Loading
                ).value.let { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {

                        }

                        is UiState.Success -> {
                            Toast.makeText(context,"Register Success", Toast.LENGTH_SHORT).show()
                            LaunchedEffect(key1 =  "error"){
                                registerViewModel.setRegisterState(UiState.Loading)
                            }
                            navController.navigate(Screen.SplashLogin.route)
                        }

                        is UiState.Error -> {
                            Toast.makeText(context,"Register Failed", Toast.LENGTH_SHORT).show()
                            LaunchedEffect(key1 =  "error"){
                                registerViewModel.setRegisterState(UiState.Loading)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LearnFornifyAppPreview() {
    LearnfonifyTheme {
        LearnFornifyApp()
    }
}

