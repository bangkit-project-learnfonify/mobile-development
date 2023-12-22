package com.capstone.learnfonify.data.retrofit

import com.capstone.learnfonify.data.response.CourseFromCategory
import com.capstone.learnfonify.data.response.CourseFromId
import com.capstone.learnfonify.data.response.ListCategory
import com.capstone.learnfonify.data.response.LoginWithEmailReponse
import com.capstone.learnfonify.data.response.RegisterWithEmailResponse
import com.capstone.learnfonify.data.response.UserFromId
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServi
interface ApiService {
    @GET("list-category")
    suspend fun getListCategory(
    ): ListCategory

    @GET("course-id/{id}")
    suspend fun getCoursesFromId(
        @Path("id") id: Int
    ): CourseFromId

    @GET("courses/{category}")
    suspend fun getCoursesFromCategory(
        @Path("category") category : String?
    ): CourseFromCategory

    @FormUrlEncoded
    @POST("login")
    suspend fun loginWithEmail(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginWithEmailReponse

    @FormUrlEncoded
    @POST("register")
    suspend fun registerWithEmail(
        @Field("fullname") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirmPassword") confirmPassword: String
    ): RegisterWithEmailResponse

    @GET("user-id/{userId}")
    suspend fun getUserFromId(
        @Path("userId") userId: Int
    ): UserFromId

}