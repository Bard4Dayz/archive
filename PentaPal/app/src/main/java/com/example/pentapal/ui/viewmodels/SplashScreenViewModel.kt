package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.repositories.UserRepository

class SplashScreenViewModel(application: Application): AndroidViewModel(application) {
    fun isUserLoggedIn() = UserRepository.isUserLoggedIn()
}