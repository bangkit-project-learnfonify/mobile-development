package com.capstone.learnfonify.ui.pages.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.learnfonify.data.local.entity.SavedCourseEntity
import com.capstone.learnfonify.data.repository.CourseRepository
import com.capstone.learnfonify.data.response.CourseItem
import com.kyy47.kyyairlines.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SavedViewModel( private val courseRepository: CourseRepository
): ViewModel() {

    private val _savedCourseState: MutableStateFlow<UiState<List<SavedCourseEntity>>> = MutableStateFlow(
        UiState.Loading)
    val savedCourseState: StateFlow<UiState<List<SavedCourseEntity>>> get()  = _savedCourseState

    fun insertToSavedCourse(course: SavedCourseEntity){
        courseRepository.insert(course)
    }

    fun checkCourse(id: Int):Int{
       return courseRepository.checkSavedCourse(id)
    }

    fun removeCourse(id: Int){
        courseRepository.removeSavedCourse(id)
    }



    fun getSavedCourse(){

         viewModelScope.launch{
             courseRepository.getSavedCourse()
                 .catch {
                     _savedCourseState.value = UiState.Error("cannot get data")
                 }.collect{
                     _savedCourseState.value = UiState.Success(it)
                 }
         }

    }
}