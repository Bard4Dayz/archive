package com.example.pentapal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pentapal.ui.components.FormField
import com.example.pentapal.ui.navigation.Routes
import com.example.pentapal.ui.viewmodels.SignInViewModel
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(navHostController: NavHostController) {
    val viewModel: SignInViewModel = viewModel()
    val scope = rememberCoroutineScope()
    val state = viewModel.uiState
    LaunchedEffect(state.loginSuccess) {
        if (state.loginSuccess) {
            navHostController.navigate(Routes.appNavigation.route) {
                popUpTo(0)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround) {
        Surface(elevation = 2.dp) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(text = "Sign In", style = MaterialTheme.typography.h2)
                FormField(
                    value = state.email,
                    onValueChange = { state.email = it },
                    placeholder = { Text("Email") },
                    error = state.emailError
                )
                FormField(
                    value = state.password,
                    onValueChange = { state.password = it },
                    placeholder = { Text("Password") },
                    error = state.passwordError,
                    password = true
                )
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ){
                    TextButton(onClick = { navHostController.popBackStack() }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = { scope.launch { viewModel.signIn() } }, elevation = null) {
                        Text(text = "Sign in")
                    }
                }
                Text(
                    text = state.errorMessage,
                    style = TextStyle(color = MaterialTheme.colors.error),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Right
                )
            }
        }
    }
}