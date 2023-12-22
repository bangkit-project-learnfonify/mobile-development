package com.capstone.learnfonify.ui

import android.app.Activity.RESULT_OK
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.capstone.learnfonify.R
import com.capstone.learnfonify.ui.navigation.NavigationItem
import com.capstone.learnfonify.ui.navigation.Screen
import com.capstone.learnfonify.ui.pages.home.HomePage
import com.capstone.learnfonify.ui.pages.profile.ProfilePage
import com.capstone.learnfonify.ui.pages.stored.StoredPage
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.learnfonify.data.signin.GoogleAuthUiClient
import com.capstone.learnfonify.ui.pages.login.LoginInViewModel
import com.capstone.learnfonify.ui.pages.register.RegisterPage
import com.google.android.gms.auth.api.identity.Identity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.capstone.learnfonify.data.ViewModelFactory
import com.capstone.learnfonify.ui.components.BottomBar
import com.capstone.learnfonify.ui.components.DetailBar
import com.capstone.learnfonify.ui.pages.coursedetail.CourseDetailPage
import com.capstone.learnfonify.ui.pages.login.LoginPage
import com.capstone.learnfonify.ui.pages.register.RegisterViewModel
import com.capstone.learnfonify.ui.pages.splashscreen.LearnFornifySplashScreen
import com.kyy47.kyyairlines.common.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnFornifyApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) {
    var tokenState by remember {
        mutableStateOf(null)
    }
    val loginViewModel: LoginInViewModel = viewModel(factory = ViewModelFactory.getInstance(context))


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            when (currentRoute) {
                Screen.SplashLogin.route -> {}
                Screen.DetailCourse.route -> {
                    DetailBar()
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
            startDestination = if (tokenState == null) Screen.SplashLogin.route else Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomePage(username = "Users", urlProfile = "", onNagivateToDetail = { id ->
                    navController.navigate(Screen.DetailCourse.createRoute(id))
                }, context = context)

            }
            composable(Screen.Stored.route) {
                StoredPage()
            }
            composable(
                Screen.DetailCourse.route,
                arguments = listOf(navArgument("courseId") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("courseId") ?: 12
                CourseDetailPage(courseId = id, context = context)
            }
            composable(Screen.Profile.route) {
                ProfilePage(
                    onSignOut = {
                        loginViewModel.deleteToken()

                        Toast.makeText(
                            context,
                            "Signed out",
                            Toast.LENGTH_LONG
                        ).show()
                        navController.popBackStack()
                    }
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
                            LaunchedEffect(key1 =  "error"){
                                if(uiState.data){
                                    navController.navigate(Screen.Home.route)
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

