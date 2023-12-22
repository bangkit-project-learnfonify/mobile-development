package com.capstone.learnfonify.di

import com.capstone.learnfonify.data.repository.CourseRepository


object Injection {
    fun provideRepository(): CourseRepository {
        return CourseRepository.getInstance()
    }
}