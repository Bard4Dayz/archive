package com.example.pentapal.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pentapal.ui.components.AppButton
import com.example.pentapal.ui.components.AppEnemyCard
import com.example.pentapal.ui.components.AppSkillCard
import com.example.pentapal.ui.navigation.Routes
import com.example.pentapal.ui.viewmodels.ViewEnemyViewModel
import com.example.pentapal.ui.viewmodels.ViewSkillViewModel

@Composable
fun ViewEnemyScreen(navHostController: NavHostController, id: String?) {
    val viewModel: ViewEnemyViewModel = viewModel()
    val state = viewModel.uiState
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        if (id != null) {
            viewModel.getEnemy(id)
        }
    }
    Column(modifier = Modifier.fillMaxSize()){
        if(state.enemies.isNotEmpty()) AppEnemyCard(deck = state.enemies, navHostController)
        Row(modifier = Modifier.fillMaxWidth()) {
            AppButton(onClick = {
                navHostController.popBackStack()
            }, text = "Back")
        }
    }
}