package com.capstone.learnfonify.ui.pages.coursedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.learnfonify.data.local.entity.SavedCourseEntity
import com.capstone.learnfonify.data.repository.CourseRepository
import com.capstone.learnfonify.data.response.CourseItem
import com.capstone.learnfonify.data.response.DetailCourseItem
import com.kyy47.kyyairlines.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CourseDetailViewModel(
    private val courseRepository: CourseRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<DetailCourseItem>> = MutableStateFlow(
        UiState.Loading)
    val uiState: StateFlow<UiState<DetailCourseItem>> get()  = _uiState


    private val _isSavedState: MutableStateFlow<UiState<Boolean>> = MutableStateFlow(
        UiState.Loading)
    val isSavedState: StateFlow<UiState<Boolean>> get()  = _isSavedState



    fun deleteToken(){
        viewModelScope.launch {
            courseRepository.removeSession()
        }
    }


    fun insertToSavedCourse(course: SavedCourseEntity){
            courseRepository.insert(course)

    }
    fun removeSavedCourse(id: Int){
        courseRepository.removeSavedCourse(id)
    }

    fun checkSavedCourse(id: Int){
        viewModelScope.launch {
            courseRepository.checkSavedCourse(id)
                .catch {
                    _isSavedState.value = UiState.Error(it.message.toString())
                }.collect{
                    _isSavedState.value =  UiState.Success(it != 0)
                }
        }

    }

    fun getCourseFromId(id: Int) {
        viewModelScope.launch {
            courseRepository.getCourseFromId(id)
                .catch {
                    deleteToken()
                    _uiState.value = UiState.Error("Error")

                }
                .collect{course ->
                    _uiState.value = UiState.Success(course)
                }
        }
    }
}