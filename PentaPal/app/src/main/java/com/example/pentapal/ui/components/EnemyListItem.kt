package com.example.pentapal.ui.components

import androidx.compose.runtime.Composable
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.models.Skill
import java.util.stream.IntStream

@Composable
fun EnemyListItem(
    enemy: Enemy,
    onViewPressed: () -> Unit = {}
) {
    AppListItem(header = enemy.name ?: "No Name Found", subtitle = enemy.type ?: "", onViewPressed = onViewPressed)
}