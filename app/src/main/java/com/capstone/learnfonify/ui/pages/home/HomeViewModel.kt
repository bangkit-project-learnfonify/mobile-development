package com.capstone.learnfonify.ui.pages.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.learnfonify.data.repository.CourseRepository
import com.capstone.learnfonify.data.response.CategoryItem
import com.capstone.learnfonify.data.response.CourseItem
import com.kyy47.kyyairlines.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(
    private val courseRepository: CourseRepository
) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<List<CourseItem>>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<List<CourseItem>>>> get()  = _uiState

      var listAllCourse: MutableList<List<CourseItem>> = mutableListOf()


    fun deleteToken(){
        viewModelScope.launch {
            courseRepository.removeSession()
        }
    }

    fun getCoursesFromCategory(){

        viewModelScope.launch {
            val listCategory = courseRepository.getListCategory()
            if(!listCategory.isNullOrEmpty()){
                listCategory.forEach {
                    courseRepository.getCoursesFromCategory(it.category)
                        .catch {
                            _uiState.value = UiState.Error("Error")
                        }
                        .collect{courses ->
                            if(!courses.isNullOrEmpty()){
                                listAllCourse.add(courses)
                                if (courses[0].category == listCategory[listCategory.lastIndex].category)  _uiState.value = UiState.Success(listAllCourse!!)
                            }else{
                                deleteToken()
                                _uiState.value = UiState.Error("data is null")
                            }

                        }

                }
            }



        }

    }
}