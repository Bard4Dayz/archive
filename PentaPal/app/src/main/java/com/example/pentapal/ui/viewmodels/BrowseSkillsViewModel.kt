package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.models.Skill
import com.example.pentapal.ui.repositories.SkillsRepository

class BrowseSkillsState {
    val _skills = mutableStateListOf<Skill>()
    val skills: List<Skill> get() = _skills
}

class BrowseSkillsViewModel(application: Application): AndroidViewModel(application) {
    val uiState = BrowseSkillsState()

    suspend fun getSkills() {
        val skills = SkillsRepository.getSkills()
        uiState._skills.clear()
        uiState._skills.addAll(skills)
    }

    suspend fun loadSkills() {
        SkillsRepository.loadSkills()
    }
}