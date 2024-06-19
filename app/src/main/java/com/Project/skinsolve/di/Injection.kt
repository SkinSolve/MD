package com.project.skinsolve.di

import android.content.Context
import com.project.skinsolve.data.UserRepository
import com.project.skinsolve.pref.UserPreference
import com.project.skinsolve.pref.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}