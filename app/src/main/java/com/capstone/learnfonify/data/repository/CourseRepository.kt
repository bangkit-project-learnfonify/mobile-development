package com.capstone.learnfonify.data.repository

import com.capstone.learnfonify.data.response.CategoryItem
import com.capstone.learnfonify.data.response.CourseItem
import com.capstone.learnfonify.data.response.ListCategory
import com.capstone.learnfonify.data.retrofit.ApiConfig
import com.capstone.learnfonify.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class CourseRepository {

    val apiService = ApiConfig.getApiService()

    suspend fun getListCategory(): List<CategoryItem>{
      return apiService.getListCategory().data
    }

    suspend fun getCoursesFromCategory( category: String): Flow<List<CourseItem>>{
        return flowOf(apiService.getCoursesFromCategory(category).data)
    }


    companion object {
        @Volatile
        private var instance: CourseRepository? = null

        fun getInstance(): CourseRepository =
            instance ?: synchronized(this) {
                CourseRepository().apply {
                    instance = this
                }
            }
    }
}