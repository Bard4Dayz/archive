package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.models.Skill
import com.example.pentapal.ui.repositories.SkillsRepository
import java.util.stream.IntStream

class ViewSkillState {
    var name by mutableStateOf("")
    var set by mutableStateOf("")
    var type by mutableStateOf("")
    val _skills = mutableStateListOf<Skill>()
    val skills: List<Skill> get() = _skills
}

class ViewSkillViewModel(application: Application): AndroidViewModel(application) {
    val uiState = ViewSkillState()
    var id: String? = null

    suspend fun getSkill(id: String) {
        val skill = SkillsRepository.getSkills().find {it.id == id}
        uiState._skills.clear()
        if (skill != null) {
            uiState._skills.add(skill)
        }
    }
}