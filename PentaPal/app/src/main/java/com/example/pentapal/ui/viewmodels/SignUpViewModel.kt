package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.repositories.SignUpException
import com.example.pentapal.ui.repositories.UserRepository

class SignUpScreenState {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf("")
    var signUpSuccess by mutableStateOf(false)
}

class SignUpViewModel(application: Application): AndroidViewModel(application) {
    val uiState = SignUpScreenState()

    suspend fun signUp() {

        try {
            UserRepository.createUser(uiState.email, uiState.password)
            uiState.signUpSuccess = true
        } catch (e: SignUpException) {
            uiState.errorMessage = e.message ?: "Something went wrong, please try again."
        }

    }
}