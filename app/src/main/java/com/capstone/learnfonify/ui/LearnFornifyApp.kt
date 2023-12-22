package com.capstone.learnfonify.ui

import android.app.Activity.RESULT_OK
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.learnfonify.data.signin.GoogleAuthUiClient
import com.capstone.learnfonify.ui.pages.login.LoginInViewModel
import com.capstone.learnfonify.ui.pages.register.RegisterPage
import com.google.android.gms.auth.api.identity.Identity
import androidx.lifecycle.lifecycleScope
import com.capstone.learnfonify.ui.pages.login.LoginPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnFornifyApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
     context : Context = LocalContext.current
) {
    var tokenState by remember {
        mutableStateOf(null)
    }

     val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if(currentRoute != Screen.Login.route)  BottomBar(navController = navController)
        },
        modifier = modifier
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if(tokenState == null) Screen.Login.route else Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
            ){
            composable(Screen.Home.route){
                HomePage()
            }
            composable(Screen.Stored.route){
              StoredPage()
            }
            composable(Screen.Profile.route){
               ProfilePage(
                   onSignOut = {
                    CoroutineScope(Dispatchers.Default).launch {
                    googleAuthUiClient.signOut()
                   }
                       Toast.makeText(
                           context,
                           "Signed out",
                           Toast.LENGTH_LONG
                       ).show()
                       navController.popBackStack()
               })
            }
            composable(Screen.Login.route){
                val viewModel = viewModel<LoginInViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = Unit) {
                    if(googleAuthUiClient.getSignedInUser() != null) {
                        navController.navigate("profile")
                    }
                }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                    onResult = { result ->
                        if(result.resultCode == RESULT_OK) {

                           CoroutineScope(Dispatchers.Default).launch {
                                val signInResult = googleAuthUiClient.signInWithIntent(
                                    intent = result.data ?: return@launch
                                )
                                viewModel.onLoginResult(signInResult)
                            }
                        }

                    }
                )


                LaunchedEffect(key1 = state.isSignInSuccessful) {
                    if(state.isSignInSuccessful) {
                        Toast.makeText(
                            context,
                            "Sign in successful",
                            Toast.LENGTH_LONG
                        ).show()

                        navController.navigate("profile")
                        viewModel.resetState()
                    }
                }
                
                LoginPage(state = state, onSignInClick = {
                    CoroutineScope(Dispatchers.Default).launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                })


            }
            composable(Screen.Register.route){
                RegisterPage()
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

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_stored),
                icon = Icons.Default.List,
                screen = Screen.Stored
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.Person,
                screen = Screen.Profile
            ),
        )
        navigationItems.map {item ->
        NavigationBarItem(
            selected = item.screen.route == currentRoute,
            onClick = {
                navController.navigate(item.screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            },
            icon = {
               Icon(
                   imageVector = item.icon ,
                   contentDescription = item.title)
            },
            label = { Text(text = item.title)}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    LearnfonifyTheme {
        BottomBar(navController = rememberNavController())
    }
}