package com.project.skinsolve.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.appstory.config.ApiService
import com.google.gson.Gson
import com.project.skinsolve.api.ApiService
import com.project.skinsolve.pref.UserModel
import com.project.skinsolve.pref.UserPreference
import com.project.skinsolve.response.LoginResponse
import com.project.skinsolve.response.RegisterResponse
import com.project.skinsolve.utils.ResultValue
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    fun register(name: String, email: String, password: String): LiveData<Result<String>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(name, email, password)
            emit(Result.Success(response.message ?: "Registration successful"))
        } catch (e: HttpException) {
            val errorMessage = if (e.code() == 400) {
                "Email sudah dimasukan"
            } else {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, RegisterResponse::class.java)
                errorBody.message ?: "Unknown error"
            }
            emit(Result.Error(errorMessage))
        } catch (e: Exception) {
            emit(Result.Error("Network error: ${e.message}"))
        }
    }

    fun login(email: String, password: String): LiveData<Result<String?>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, password)
            emit(Result.Success(response.loginResult?.token))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(Result.Error(errorResponse.message!!))
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
    }
}
