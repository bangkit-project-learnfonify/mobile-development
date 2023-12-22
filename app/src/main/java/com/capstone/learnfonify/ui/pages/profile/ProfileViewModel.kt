package com.capstone.learnfonify.ui.pages.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.learnfonify.data.repository.CourseRepository
import com.capstone.learnfonify.data.response.DetailCourseItem
import com.capstone.learnfonify.data.response.UserFromIdItem
import com.kyy47.kyyairlines.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProfileViewModel( private val courseRepository: CourseRepository
): ViewModel() {
    private val _userState: MutableStateFlow<UiState<UserFromIdItem>> = MutableStateFlow(
        UiState.Loading)
    val userState: StateFlow<UiState<UserFromIdItem>> get()  = _userState

     fun getUserFromId(id: Int){

         viewModelScope.launch {
             courseRepository.getUserFormId(id)
                 .catch {
                     _userState.value = UiState.Error("data not found")
                 }.collect{
                    _userState.value = UiState.Success(it)
                 }
         }

    }

    fun deleteToken(){
            courseRepository.removeSession()
    }

}