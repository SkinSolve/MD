package com.project.skinsolve.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.skinsolve.data.UserRepository
import com.project.skinsolve.pref.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
}