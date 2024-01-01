package com.example.pentapal.ui.components

import androidx.compose.runtime.Composable
import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Enemy

@Composable
fun EncounterListItem(
    encounter: Encounter,
    onViewPressed: () -> Unit = {}
) {
    AppListItem(header = encounter.name ?: "No Name Found", subtitle = "Difficulty: ${encounter.difficulty}", onViewPressed = onViewPressed)
}