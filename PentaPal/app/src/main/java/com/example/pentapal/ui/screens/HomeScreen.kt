package com.example.pentapal.ui.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pentapal.ui.components.*
import com.example.pentapal.ui.navigation.Routes

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(4.dp))
        BigAppButton(onClick = { navHostController.navigate(Routes.saved.route) }, text = "Saved Content")
        BigAppButton(onClick = { navHostController.navigate(Routes.generate.route) }, text = "Generate")
        BigAppButton(onClick = { navHostController.navigate(Routes.browse.route) }, text = "Browse")
    }
}