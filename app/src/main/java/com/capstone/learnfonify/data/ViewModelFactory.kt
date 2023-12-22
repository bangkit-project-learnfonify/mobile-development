package com.capstone.learnfonify.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.learnfonify.data.repository.CourseRepository
import com.capstone.learnfonify.di.Injection
import com.capstone.learnfonify.ui.pages.coursedetail.CourseDetailViewModel
import com.capstone.learnfonify.ui.pages.home.HomeViewModel
import com.capstone.learnfonify.ui.pages.login.LoginInViewModel
import com.capstone.learnfonify.ui.pages.register.RegisterViewModel

class ViewModelFactory(private val repository: CourseRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(CourseDetailViewModel::class.java)) {
            return CourseDetailViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(LoginInViewModel::class.java)) {
            return LoginInViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
    companion object {
        fun getInstance(context: Context): ViewModelFactory = ViewModelFactory(Injection.provideRepository(context))
    }
}