package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.models.Skill
import com.example.pentapal.ui.repositories.EnemiesRepository
import com.example.pentapal.ui.repositories.SkillsRepository
import java.util.stream.IntStream

class ViewEnemyState {
    val _enemies = mutableStateListOf<Enemy>()
    val enemies: List<Enemy> get() = _enemies
}

class ViewEnemyViewModel(application: Application): AndroidViewModel(application) {
    val uiState = ViewEnemyState()
    var id: String? = null

    suspend fun getEnemy(id: String) {
        val enemy = EnemiesRepository.getEnemies().find {it.id == id}
        uiState._enemies.clear()
        if (enemy != null) {
            uiState._enemies.add(enemy)
        }
    }

}