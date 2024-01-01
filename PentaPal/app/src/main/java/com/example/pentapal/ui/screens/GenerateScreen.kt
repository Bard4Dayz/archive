package com.example.pentapal.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pentapal.ui.components.BigAppButton
import com.example.pentapal.ui.navigation.Routes

@Composable
fun GenerateScreen(navHostController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(4.dp))
        BigAppButton(onClick = { navHostController.navigate(Routes.generateSkill.route) }, text = "Skill")
        BigAppButton(onClick = { navHostController.navigate(Routes.generateShop.route) }, text = "Shop")
        BigAppButton(onClick = { navHostController.navigate(Routes.generateEnemy.route) }, text = "Enemy")
        BigAppButton(onClick = { navHostController.navigate(Routes.generateEncounter.route) }, text = "Encounter")
        BigAppButton(onClick = { navHostController.navigate(Routes.generateReward.route) }, text = "Room Reward")
    }
}