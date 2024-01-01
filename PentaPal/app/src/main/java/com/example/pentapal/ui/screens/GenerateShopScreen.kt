package com.example.pentapal.ui.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pentapal.ui.components.AppCheckbox
import com.example.pentapal.ui.components.AppDropdown
import com.example.pentapal.ui.components.AppTextInput
import com.example.pentapal.ui.components.BigAppButton
import com.example.pentapal.ui.navigation.Routes
import com.example.pentapal.ui.repositories.EncountersRepository
import com.example.pentapal.ui.repositories.EnemiesRepository
import com.example.pentapal.ui.repositories.ShopsRepository
import com.example.pentapal.ui.repositories.SkillsRepository
import com.example.pentapal.ui.viewmodels.GenerateEnemyViewModel
import com.example.pentapal.ui.viewmodels.GenerateShopViewModel
import com.example.pentapal.ui.viewmodels.generateEnemy
import com.example.pentapal.ui.viewmodels.generateShop

@Composable
fun GenerateShopScreen(navHostController: NavHostController) {
    val viewModel: GenerateShopViewModel = viewModel()
    val state = viewModel.uiState
    val focusManager = LocalFocusManager.current
    LaunchedEffect(key1 = true) {
        state.skills.addAll(SkillsRepository.getSkills())
        EnemiesRepository.resetBuffer()
        SkillsRepository.resetBuffer()
        EncountersRepository.resetBuffer()
    }
    Column(modifier = Modifier.fillMaxSize().pointerInput(Unit){
        detectTapGestures {
            focusManager.clearFocus()
        }
    }){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppDropdown(onValueChange = {state.level.value = it}, options = listOf("1","2","3","4","5"), label = "Skill Level")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppDropdown(onValueChange = {state.count.value = it}, options = listOf("Any","3","4","5","6","7","8"), label = "No. of Skills")
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppTextInput(value = state.name.value, onValueChange = {state.name.value = it}) {

                }
            }
        }
        BigAppButton(onClick = {
            focusManager.clearFocus()
            val shop = generateShop(state.skills, state.level.value, state.count.value, state.name.value)
            ShopsRepository.setBuffer(shop)
            navHostController.navigate(Routes.result.route)}
            , text = "Generate")
    }
}