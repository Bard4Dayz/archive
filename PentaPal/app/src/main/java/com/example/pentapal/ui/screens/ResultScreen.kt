package com.example.pentapal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pentapal.ui.components.AppButton
import com.example.pentapal.ui.components.AppEnemyCard
import com.example.pentapal.ui.components.AppShopCard
import com.example.pentapal.ui.components.AppSkillCard
import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.models.Shop
import com.example.pentapal.ui.models.Skill
import com.example.pentapal.ui.navigation.Routes
import com.example.pentapal.ui.repositories.EncountersRepository
import com.example.pentapal.ui.repositories.EnemiesRepository
import com.example.pentapal.ui.repositories.ShopsRepository
import com.example.pentapal.ui.repositories.SkillsRepository
import com.example.pentapal.ui.viewmodels.ResultViewModel
import com.example.pentapal.ui.viewmodels.ViewSkillViewModel
import kotlinx.coroutines.launch

@Composable
fun ResultScreen(navHostController: NavHostController) {
    val viewModel: ResultViewModel = viewModel()
    val state = viewModel.uiState
    val scope = rememberCoroutineScope()
    state.skill.value = SkillsRepository.getBuffer()
    state.enemy.value = EnemiesRepository.getBuffer()
    state.encounter.value = EncountersRepository.getBuffer()
    state.shop.value = ShopsRepository.getBuffer()
    LaunchedEffect(state.saveSuccess) {
        if (state.saveSuccess) {
            navHostController.navigate(Routes.generate.route)
        }
    }
    if(state.skill.value.name != null){
        Column(modifier = Modifier.fillMaxSize()){
            AppSkillCard(deck = listOf(state.skill.value))
            Row(modifier = Modifier.fillMaxWidth()) {
                AppButton(onClick = {
                    SkillsRepository.resetBuffer()
                    navHostController.navigate(Routes.generate.route)
                                    }, text = "Cancel")
            }
        }
    } else if(state.enemy.value.name != null) {
        Column(modifier = Modifier.fillMaxSize()) {
            AppEnemyCard(deck = listOf(state.enemy.value), navHostController = navHostController)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                AppButton(onClick = {
                    EnemiesRepository.resetBuffer()
                    navHostController.navigate(Routes.generate.route)
                }, text = "Cancel")
                AppButton(onClick = { scope.launch {
                    viewModel.saveEnemy()
                } }, text = "Save")
            }
        }
    } else if(state.encounter.value.name != null){
        Column(modifier = Modifier.fillMaxSize()){
            state.encounter.value.enemiesFull?.let { AppEnemyCard(deck = it, navHostController = navHostController) }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                AppButton(onClick = {
                    EncountersRepository.resetBuffer()
                    navHostController.navigate(Routes.generate.route)
                }, text = "Cancel")
                AppButton(onClick = { scope.launch {
                    viewModel.saveEncounter()
                } }, text = "Save")
            }
        }
    }
    else if(state.shop.value.name != null){
        Column(modifier = Modifier.fillMaxSize()){
            state.shop.value.options?.let { AppShopCard(deck = it) }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                AppButton(onClick = { ShopsRepository.resetBuffer()
                    navHostController.navigate(Routes.generate.route)}, text = "Cancel")
                AppButton(onClick = { scope.launch {
                    viewModel.saveShop()
                } }, text = "Save")
            }
        }
    }

}
