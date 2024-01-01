package com.example.pentapal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pentapal.ui.components.BigAppButton
import com.example.pentapal.ui.navigation.Routes

@Composable
fun SavedContentScreen(navHostController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(4.dp))
        BigAppButton(onClick = { navHostController.navigate(Routes.browseEnemies.route) }, text = "Enemies")
        BigAppButton(onClick = { navHostController.navigate(Routes.browseEncounters.route) }, text = "Encounters")
        BigAppButton(onClick = { navHostController.navigate(Routes.browseShops.route) }, text = "Shops")
    }
}