package com.capstone.learnfonify.data.repository

import com.capstone.learnfonify.data.preferences.SessionPreference
import com.capstone.learnfonify.data.response.CategoryItem
import com.capstone.learnfonify.data.response.CourseFromId
import com.capstone.learnfonify.data.response.CourseItem
import com.capstone.learnfonify.data.response.DetailCourseItem
import com.capstone.learnfonify.data.response.ListCategory
import com.capstone.learnfonify.data.response.LoginWithEmailReponse
import com.capstone.learnfonify.data.response.RegisterWithEmailResponse
import com.capstone.learnfonify.data.retrofit.ApiConfig
import com.capstone.learnfonify.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class CourseRepository( private val apiService: ApiService, private  val pref: SessionPreference) {


    fun getIsAuthLogin(): Flow<Boolean> {
        return pref.getSessionLogin()
    }

    suspend fun saveSessionData(token: String){
        pref.saveTokenSession(token)
    }

    suspend fun removeSession(){
        pref.removeSession()
    }



    suspend fun loginWithEmail(email: String, password: String): Flow<LoginWithEmailReponse> {
        return flowOf( apiService.loginWithEmail(email, password))
    }
    suspend fun registerWithEmail(username: String, email: String,
                                  password: String, confirmPasswod: String): Flow<RegisterWithEmailResponse> {
        return flowOf( apiService.registerWithEmail(username, email, password, confirmPasswod))
    }

    suspend fun getListCategory(): List<CategoryItem>{
        return apiService.getListCategory().data
    }

    suspend fun getCoursesFromCategory( category: String?): Flow<List<CourseItem>?>{
        return flowOf(apiService.getCoursesFromCategory(category).data)
    }

    suspend fun getCourseFromId(id: Int): Flow<DetailCourseItem>{
        val detailDefault = DetailCourseItem(
            image = "",
            instructor = "",
            level = "",
            organizer = "",
            fee = "",
            link = "",
            rating = "",
            createdAt = "",
            description = "",
            id = -1,
            title = "",
            category = ""

        )
        return flowOf(apiService.getCoursesFromId(id).data?.get(0) ?: detailDefault)
    }

    companion object {
        fun getInstance(
            apiService: ApiService,
            pref: SessionPreference
        ): CourseRepository = CourseRepository( apiService, pref)
    }
}