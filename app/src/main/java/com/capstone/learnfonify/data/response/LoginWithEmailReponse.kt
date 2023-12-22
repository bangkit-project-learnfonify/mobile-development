package com.capstone.learnfonify.data.response

import com.google.gson.annotations.SerializedName

data class LoginWithEmailReponse(

    @field:SerializedName("majoring")
    val majoring: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("accessToken")
    val accessToken: String? = null,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("age")
    val age: Int? = null,

    @field:SerializedName("refreshToken")
    val refreshToken: String? = null
)
