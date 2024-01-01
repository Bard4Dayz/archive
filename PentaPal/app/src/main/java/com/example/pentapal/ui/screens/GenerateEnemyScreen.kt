package com.example.pentapal.ui.viewmodels

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pentapal.ui.components.AppCheckbox
import com.example.pentapal.ui.components.AppDropdown
import com.example.pentapal.ui.components.BigAppButton
import com.example.pentapal.ui.navigation.Routes
import com.example.pentapal.ui.repositories.EncountersRepository
import com.example.pentapal.ui.repositories.EnemiesRepository
import com.example.pentapal.ui.repositories.ShopsRepository
import com.example.pentapal.ui.repositories.SkillsRepository

@Composable
fun GenerateEnemyScreen(navHostController: NavHostController) {
    val viewModel: GenerateEnemyViewModel = viewModel()
    val state = viewModel.uiState
    LaunchedEffect(key1 = true) {
        state.skills.addAll(SkillsRepository.getSkills())
        SkillsRepository.resetBuffer()
        ShopsRepository.resetBuffer()
        EncountersRepository.resetBuffer()
    }
    Column(modifier = Modifier.fillMaxSize()){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppDropdown(onValueChange = {state.type.value = it}, options = listOf("Basic","Special","Elite","Miniboss","Boss"), label = "Enemy Type")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppDropdown(onValueChange = {state.set.value = it}, options = listOf("Any","CalCult"), label = "Skills Set")
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppDropdown(onValueChange = {state.floor.value = it}, options = listOf("1","2","3","4","5"), label = "Floor")
            }
        }
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){
            Text(text = "Possible Slots", style = MaterialTheme.typography.h3)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Movement", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.movementSlot.value, onCheckedChange = {state.movementSlot.value = it})
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Attack", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.attackSlot.value, onCheckedChange = {state.attackSlot.value = it})
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Defense", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.defenseSlot.value, onCheckedChange = {state.defenseSlot.value = it})
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Support", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.supportSlot.value, onCheckedChange = {state.supportSlot.value = it})
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Utility", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.utilitySlot.value, onCheckedChange = {state.utilitySlot.value = it})
            }
        }
        BigAppButton(onClick = {
            val enemyPick = generateEnemy(state.attackSlot.value, state.movementSlot.value, state.defenseSlot.value, state.supportSlot.value, state.utilitySlot.value, state.type.value, state.set.value, state.floor.value, state.skills)
            EnemiesRepository.setBuffer(enemyPick)
            navHostController.navigate(Routes.result.route)}
            , text = "Generate")
    }
}