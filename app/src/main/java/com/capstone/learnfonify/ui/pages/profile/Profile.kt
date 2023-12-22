package com.capstone.learnfonify.ui.pages.profile

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ProfilePage(onSignOut: () -> Unit) {
    ProfileContent(onSignOut)
}

@Composable
fun ProfileContent(onSignOut: () -> Unit) {
    Button(onClick = { onSignOut()}) {
        Text(text = "Logout Dulu Bosku!")
    }
    
}