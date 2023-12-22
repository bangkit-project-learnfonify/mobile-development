package com.capstone.learnfonify.data.signin

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)