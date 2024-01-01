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
import com.example.pentapal.ui.viewmodels.GenerateEncounterViewModel
import com.example.pentapal.ui.viewmodels.GenerateShopViewModel
import com.example.pentapal.ui.viewmodels.generateEncounter
import com.example.pentapal.ui.viewmodels.generateShop

@Composable
fun GenerateEncounterScreen(navHostController: NavHostController) {
    val viewModel: GenerateEncounterViewModel = viewModel()
    val state = viewModel.uiState
    val focusManager = LocalFocusManager.current
    LaunchedEffect(key1 = true) {
        state.skills.addAll(SkillsRepository.getSkills())
        state.enemies.addAll(EnemiesRepository.getEnemies())
        EnemiesRepository.resetBuffer()
        SkillsRepository.resetBuffer()
        ShopsRepository.resetBuffer()
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
                AppDropdown(onValueChange = {state.floor.value = it}, options = listOf("1","2","3","4","5"), label = "Floor")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppDropdown(onValueChange = {state.difficulty.value = it}, options = listOf("Very Easy","Easy","Medium","Hard","Very Hard","Boss"), label = "Difficulty")
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppDropdown(onValueChange = {state.count.value = it}, options = listOf("Random","1","2","3","4","5","6+"), label = "No. of Enemies")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Use Saved Enemies?", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.savedEnemies.value, onCheckedChange = {state.savedEnemies.value = it})
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppTextInput(value = state.name.value, onValueChange = {state.name.value = it}) {}
            }
        }
        BigAppButton(onClick = {
            focusManager.clearFocus()
            val encounter = generateEncounter(state.skills, state.enemies, state.name.value, state.difficulty.value, state.floor.value, state.count.value, state.savedEnemies.value)
            EncountersRepository.setBuffer(encounter)
            navHostController.navigate(Routes.result.route)}
            , text = "Generate")
    }
}