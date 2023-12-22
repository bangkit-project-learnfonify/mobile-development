package com.capstone.learnfonify.ui.pages.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.learnfonify.data.repository.CourseRepository
import com.capstone.learnfonify.data.response.LoginWithEmailReponse
import com.capstone.learnfonify.data.response.RegisterWithEmailResponse
import com.kyy47.kyyairlines.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterViewModel( private val courseRepository: CourseRepository
): ViewModel() {

    private val _registerState: MutableStateFlow<UiState<RegisterWithEmailResponse>> = MutableStateFlow(
        UiState.Loading)
    val registerState: StateFlow<UiState<RegisterWithEmailResponse>> get()  = _registerState


    fun setRegisterState(state: UiState<RegisterWithEmailResponse>){
        _registerState.value = state
    }


    fun registerWithEmail(username: String, email: String,
    password: String, confirmPasswod: String){
    viewModelScope.launch {
        courseRepository.registerWithEmail(username, email, password, confirmPasswod)
            .catch {
            _registerState.value = UiState.Error("cannot post data")
            }
            .collect{registerResponse ->
                    _registerState.value = UiState.Success(registerResponse)

            }
    }
    }
}