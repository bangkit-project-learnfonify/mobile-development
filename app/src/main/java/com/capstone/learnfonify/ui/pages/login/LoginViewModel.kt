package com.capstone.learnfonify.ui.pages.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.learnfonify.data.repository.CourseRepository
import com.capstone.learnfonify.data.response.DetailCourseItem
import com.capstone.learnfonify.data.response.LoginWithEmailReponse
import com.capstone.learnfonify.data.signin.SignInResult
import com.capstone.learnfonify.data.signin.SignInState
import com.kyy47.kyyairlines.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginInViewModel( private val courseRepository: CourseRepository
): ViewModel() {

    private val _loginState: MutableStateFlow<UiState<LoginWithEmailReponse>> = MutableStateFlow(
        UiState.Loading)
    val loginState: StateFlow<UiState<LoginWithEmailReponse>> get()  = _loginState


    private val _isLogin: MutableStateFlow<UiState<Boolean>> = MutableStateFlow(
        UiState.Loading)
    val isLogin: StateFlow<UiState<Boolean>> get()  = _isLogin

//    private val _state = MutableStateFlow(SignInState())
//    val state = _state.asStateFlow()

//    fun onLoginResult(result: SignInResult) {
//        _state.update { it.copy(
//            isSignInSuccessful = result.data != null,
//            signInError = result.errorMessage
//        ) }
//    }
//
//    fun resetState() {
//        _state.update { SignInState() }
//    }

    fun setLoginState(state: UiState<LoginWithEmailReponse>){
        _loginState.value = state
    }

    fun getIsAuthLogin(){
        viewModelScope.launch {
            courseRepository.getIsAuthLogin()
                .catch {
                    _isLogin.value = UiState.Error("Failed")
                }
                .collect{
                    _isLogin.value = UiState.Success(it)
                }
        }

    }

    fun deleteToken(){
        viewModelScope.launch {
            courseRepository.removeSession()
        }
    }

    fun loginWithEmail(email: String, password: String){
        viewModelScope.launch{
            courseRepository.loginWithEmail(email, password)
                .catch {
                    _loginState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    if (it.email != null){
                        _loginState.value = UiState.Success(it)
                        courseRepository.saveSessionData(it.accessToken.toString())
                    }else{
                        _loginState.value = UiState.Error("Failed")
                    }

                }
        }

    }


}