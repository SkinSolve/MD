package com.project.skinsolve.view.splashscreen

import androidx.lifecycle.ViewModel

class RoutingViewModel(private val authRepository: AuthRepository): ViewModel() {
    fun isLogin() = authRepository.isLogin()
}