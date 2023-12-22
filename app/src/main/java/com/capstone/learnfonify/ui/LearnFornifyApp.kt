package com.capstone.learnfonify.ui

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
import com.capstone.learnfonify.ui.pages.login.LoginPage
import com.capstone.learnfonify.ui.pages.register.RegisterPage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnFornifyApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var tokenState by remember {
        mutableStateOf(null)
    }
    Scaffold(
        bottomBar = {
            if(tokenState != null)  BottomBar(navController = navController)
        },
        modifier = modifier
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if(tokenState == null) Screen.Register.route else Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
            ){
            composable(Screen.Home.route){
                HomePage()
            }
            composable(Screen.Stored.route){
              StoredPage()
            }
            composable(Screen.Profile.route){
               ProfilePage()
            }
            composable(Screen.Login.route){
                LoginPage()
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