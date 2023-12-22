package com.capstone.learnfonify.di

import android.content.Context
import com.capstone.learnfonify.data.preferences.SessionPreference
import com.capstone.learnfonify.data.preferences.dataStore
import com.capstone.learnfonify.data.repository.CourseRepository
import com.capstone.learnfonify.data.retrofit.ApiConfig
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): CourseRepository {
        val pref = SessionPreference.getInstance(context.dataStore)
        val token = runBlocking { pref.getSessionToken()    }
        val apiService = ApiConfig.getApiService(token)
        return CourseRepository.getInstance(apiService, pref)
    }
}