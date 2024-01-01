package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.repositories.EncountersRepository
import com.example.pentapal.ui.repositories.EnemiesRepository

class ViewEncounterState {
    val _encounters = mutableStateListOf<Encounter>()
    val encounters: List<Encounter> get() = _encounters
}

class ViewEncounterViewModel(application: Application): AndroidViewModel(application) {
    val uiState = ViewEncounterState()
    var id: String? = null

    suspend fun getEncounter(id: String) {
        val encounter = EncountersRepository.getEncounters().find {it.id == id}
        uiState._encounters.clear()
        if (encounter != null) {
            uiState._encounters.add(encounter)
        }
    }

}