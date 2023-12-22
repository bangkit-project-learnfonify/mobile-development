package com.capstone.learnfonify.ui.pages.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.learnfonify.data.repository.CourseRepository
import com.capstone.learnfonify.data.response.CourseFromCategory
import com.capstone.learnfonify.data.response.CourseItem
import com.capstone.learnfonify.data.response.UserFromIdItem
import com.kyy47.kyyairlines.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoreViewModel( private val courseRepository: CourseRepository
): ViewModel()  {


    private val _coursesState: MutableStateFlow<UiState<List<CourseItem>>> = MutableStateFlow(
        UiState.Loading)
    val coursesState: StateFlow<UiState<List<CourseItem>>> get()  = _coursesState



    fun getCoursesFromCategory(category: String){
        viewModelScope.launch {
            if(category != TOP_COURSES){
                courseRepository.getCoursesFromCategory(category)
                    .catch {
                        _coursesState.value = UiState.Error("cannot get courses")
                    }.collect{
                        _coursesState.value = UiState.Success(it)
                    }
            }else{
                val dummyIdUser = 1
                courseRepository.getTopCourse(dummyIdUser)
                    .catch {
                        _coursesState.value = UiState.Error("cannot get courses")
                    }.collect{
                        _coursesState.value = UiState.Success(it)
                    }
            }

        }
    }

    companion object{
        val TOP_COURSES = "Top Courses"
    }
}