package com.example.pentapal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pentapal.ui.components.AppCheckbox
import com.example.pentapal.ui.components.BigAppButton
import com.example.pentapal.ui.viewmodels.GenerateRewardState
import com.example.pentapal.ui.viewmodels.GenerateRewardViewModel
import kotlin.random.Random

@Composable
fun GenerateRewardScreen(navHostController: NavHostController) {
    val viewModel: GenerateRewardViewModel = viewModel()
    val state = viewModel.uiState
    Column(modifier = Modifier.fillMaxSize()){
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Nothing", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.nothing.value, onCheckedChange = {state.nothing.value = it})
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Currency", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.money.value, onCheckedChange = {state.money.value = it})
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Single Heal", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.singleHeal.value, onCheckedChange = {state.singleHeal.value = it})
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "All Heal", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.multiHeal.value, onCheckedChange = {state.multiHeal.value = it})
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Single Stat", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.singleStat.value, onCheckedChange = {state.singleStat.value = it})
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "All Stat", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.multiStat.value, onCheckedChange = {state.multiStat.value = it})
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Single Skill", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.singleSkill.value, onCheckedChange = {state.singleSkill.value = it})
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "All Skill", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.multiSkill.value, onCheckedChange = {state.multiSkill.value = it})
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Single Upgrade", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.singleUpgrade.value, onCheckedChange = {state.singleUpgrade.value = it})
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "All Upgrade", style = MaterialTheme.typography.overline)
                AppCheckbox(checked = state.multiUpgrade.value, onCheckedChange = {state.multiUpgrade.value = it})
            }
        }
        BigAppButton(onClick = { viewModel.generateReward() }, text = "Generate")
        if (state.showResult.value) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Result:", style = MaterialTheme.typography.h3)
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = state.result.value, style = MaterialTheme.typography.h2)
            }

        }
    }
}