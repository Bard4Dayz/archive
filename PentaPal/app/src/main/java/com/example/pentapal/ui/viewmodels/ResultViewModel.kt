package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.models.Shop
import com.example.pentapal.ui.models.Skill
import com.example.pentapal.ui.repositories.EncountersRepository
import com.example.pentapal.ui.repositories.EnemiesRepository
import com.example.pentapal.ui.repositories.ShopsRepository

class ResultState {
    var skill = mutableStateOf(Skill())
    var enemy = mutableStateOf(Enemy())
    var shop = mutableStateOf(Shop())
    var encounter = mutableStateOf(Encounter())
    var saveSuccess by mutableStateOf(false)
}

class ResultViewModel(application: Application): AndroidViewModel(application){
    val uiState = ResultState()

    suspend fun saveEnemy(){
        EnemiesRepository.saveEnemy(uiState.enemy.value)
        uiState.saveSuccess = true
    }

    suspend fun saveEncounter() {
        EncountersRepository.saveEncounter(uiState.encounter.value)
        uiState.saveSuccess = true
    }

    suspend fun saveShop(){
        ShopsRepository.saveShop(uiState.shop.value)
        uiState.saveSuccess = true
    }
}