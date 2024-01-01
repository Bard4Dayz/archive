package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.repositories.UserRepository

class RootNavigationViewModel(application: Application): AndroidViewModel(application) {
    fun signOutUser() = UserRepository.signOutUser()
}