package com.capstone.learnfonify.data.repository

import com.capstone.learnfonify.data.local.entity.SavedCourseEntity
import com.capstone.learnfonify.data.local.room.SavedCourseDao
import com.capstone.learnfonify.data.preferences.SessionPreference
import com.capstone.learnfonify.data.response.CategoryItem
import com.capstone.learnfonify.data.response.CourseItem
import com.capstone.learnfonify.data.response.DetailCourseItem
import com.capstone.learnfonify.data.response.LoginWithEmailReponse
import com.capstone.learnfonify.data.response.RegisterWithEmailResponse
import com.capstone.learnfonify.data.response.UserFromIdItem
import com.capstone.learnfonify.data.retrofit.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch


class CourseRepository( private val apiService: ApiService,
                        private  val pref: SessionPreference,
                        private  val mSavedCourseDao: SavedCourseDao) {


    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    fun getIsAuthLogin(): Flow<Boolean> {
        return pref.getSessionLogin()
    }

    suspend fun saveSessionData(token: String, userId: Int){
        pref.saveTokenSession(token, userId)
    }

    fun removeSession(){
        coroutineScope.launch(Dispatchers.IO) {
            pref.removeSession()
        }

    }

     fun getUserIdSession() : Int{
        return pref.getIdUser()
    }

    suspend fun getUserFormId(id: Int): Flow<UserFromIdItem>{
       return flowOf( apiService.getUserFromId(id).data[0])
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

    suspend fun getCoursesFromCategory( category: String?): Flow<List<CourseItem>>{
        return flowOf(apiService.getCoursesFromCategory(category).data)
    }

    suspend fun getTopCourse(idUser: Int): Flow<List<CourseItem>>{
        return flowOf(apiService.getTopCourse(idUser))
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


     fun postRating(userId: Int, courseId: Int, userRating: Int){
        coroutineScope.launch(Dispatchers.IO) {
            apiService.postRating(userId, courseId, userRating)
        }
    }

     fun insert(course: SavedCourseEntity){
         coroutineScope.launch(Dispatchers.IO) {
             mSavedCourseDao.addToSaved(course)
         }

     }

    fun getSavedCourse(): Flow<List<SavedCourseEntity>>{
        return mSavedCourseDao.getSavedCourse()
    }

    fun checkSavedCourse(id: Int): Flow<Int>{
            return mSavedCourseDao.checkCourse(id)
    }

    fun removeSavedCourse(id: Int){
        coroutineScope.launch(Dispatchers.IO) {
            mSavedCourseDao.removeCourse(id)
        }
    }




    companion object {
        fun getInstance(
            apiService: ApiService,
            pref: SessionPreference,
            mSavedCourseDao: SavedCourseDao
        ): CourseRepository = CourseRepository( apiService, pref, mSavedCourseDao)
    }
}