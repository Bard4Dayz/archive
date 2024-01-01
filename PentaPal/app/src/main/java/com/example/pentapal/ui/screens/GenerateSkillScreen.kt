package com.example.pentapal.ui.screens

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

import com.example.pentapal.ui.viewmodels.GenerateSkillViewModel
import com.example.pentapal.ui.viewmodels.generateSkill

@Composable
fun GenerateSkillScreen(navHostController: NavHostController) {
    val viewModel: GenerateSkillViewModel = viewModel()
    val state = viewModel.uiState
    LaunchedEffect(key1 = true) {
        state.skills.addAll(SkillsRepository.getSkills())
        EnemiesRepository.resetBuffer()
        ShopsRepository.resetBuffer()
        EncountersRepository.resetBuffer()
    }
    Column(modifier = Modifier.fillMaxSize()){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppDropdown(onValueChange = {state.floor.value = it}, options = listOf("None","1","2","3","4","5"), label = "Floor")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppDropdown(onValueChange = {state.level.value = it}, options = listOf("Any","1","2","3","4","5"), label = "Level")
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppDropdown(onValueChange = { state.type.value = it }, options = listOf("Any","Active","Passive","Both", "Enemy"), label = "Type")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppDropdown(onValueChange = {state.set.value = it}, options = listOf("Any","CalCult"), label = "Set")
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
            val skillPick = generateSkill(state.attackSlot.value, state.movementSlot.value, state.defenseSlot.value, state.supportSlot.value, state.utilitySlot.value, state.floor.value, state.level.value, state.set.value, state.type.value, state.skills, slot="")
            SkillsRepository.setBuffer(skillPick)
            navHostController.navigate(Routes.result.route)}
            , text = "Generate")
    }
}