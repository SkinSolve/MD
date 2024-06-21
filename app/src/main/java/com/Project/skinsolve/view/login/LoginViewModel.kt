package com.project.skinsolve.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.skinsolve.data.UserRepository
import com.project.skinsolve.pref.Result
import com.project.skinsolve.pref.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun login(email: String, password: String): LiveData<Result<String>> {
        return userRepository.login(email, password)
    }

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userRepository.saveSession(user)
        }
    }
}
