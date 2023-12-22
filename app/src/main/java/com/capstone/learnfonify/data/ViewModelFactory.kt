package com.capstone.learnfonify.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.learnfonify.data.repository.CourseRepository
import com.capstone.learnfonify.ui.pages.coursedetail.CourseDetailViewModel
import com.capstone.learnfonify.ui.pages.home.HomeViewModel

class ViewModelFactory(private val repository: CourseRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(CourseDetailViewModel::class.java)) {
            return CourseDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}