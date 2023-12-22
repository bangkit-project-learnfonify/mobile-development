package com.capstone.learnfonify.data.retrofit

import com.capstone.learnfonify.data.response.CourseFromCategory
import com.capstone.learnfonify.data.response.ListCategory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("list-category")
    suspend fun getListCategory(
    ): ListCategory

    @GET("courses/{category}")
    suspend fun getCoursesFromCategory(
        @Path("category") category : String
    ): CourseFromCategory
}