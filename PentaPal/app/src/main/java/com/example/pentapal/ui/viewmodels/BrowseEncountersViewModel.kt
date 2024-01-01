package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.repositories.EncountersRepository
import com.example.pentapal.ui.repositories.EnemiesRepository
import com.example.pentapal.ui.repositories.SkillsRepository

class BrowseEncountersState {
    val _encounters = mutableStateListOf<Encounter>()
    val encounters: List<Encounter> get() = _encounters
}

class BrowseEncountersViewModel(application: Application): AndroidViewModel(application) {
    val uiState = BrowseEncountersState()

    suspend fun getEncounters() {
        val encounters = EncountersRepository.getEncounters()
        uiState._encounters.clear()
        uiState._encounters.addAll(encounters)
    }
}