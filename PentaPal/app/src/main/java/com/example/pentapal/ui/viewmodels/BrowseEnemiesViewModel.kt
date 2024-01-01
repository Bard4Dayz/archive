package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.models.Skill
import com.example.pentapal.ui.repositories.EnemiesRepository
import com.example.pentapal.ui.repositories.SkillsRepository

class BrowseEnemiesState {
    val _enemies = mutableStateListOf<Enemy>()
    val enemies: List<Enemy> get() = _enemies
}

class BrowseEnemiesViewModel(application: Application): AndroidViewModel(application) {
    val uiState = BrowseEnemiesState()

    suspend fun getEnemies() {
        val enemies = EnemiesRepository.getEnemies()
        uiState._enemies.clear()
        uiState._enemies.addAll(enemies)
    }

    suspend fun loadSkills() {
        SkillsRepository.loadSkills()
    }
}