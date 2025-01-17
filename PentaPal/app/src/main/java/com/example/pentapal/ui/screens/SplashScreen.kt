package com.example.pentapal.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.pentapal.ui.navigation.Routes
import com.example.pentapal.ui.viewmodels.SplashScreenViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {
    val viewModel: SplashScreenViewModel = viewModel()
    LaunchedEffect(true) {
        delay(1000)
        navHostController.navigate(
            if(viewModel.isUserLoggedIn()){
                Routes.appNavigation.route
            } else {
                Routes.launchNavigation.route
            }
        ) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                inclusive = true
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Penta Pal",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center
        )
    }
}