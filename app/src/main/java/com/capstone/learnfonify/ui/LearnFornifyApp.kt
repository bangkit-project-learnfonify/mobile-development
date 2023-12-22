package com.capstone.learnfonify.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.capstone.learnfonify.ui.pages.login.LoginPage
import com.capstone.learnfonify.ui.theme.LearnfonifyTheme

@Composable
fun LearnFornifyApp() {
   LoginPage()
}



@Preview(showBackground = true)
@Composable
fun LearnFornifyAppPreview() {
    LearnfonifyTheme {
        LearnFornifyApp()
    }
}