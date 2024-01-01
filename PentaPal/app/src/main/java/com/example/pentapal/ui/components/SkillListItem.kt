package com.example.pentapal.ui.components

import androidx.compose.runtime.Composable
import com.example.pentapal.ui.models.Skill
import java.util.stream.IntStream.range

@Composable
fun SkillListItem(
    skill: Skill,
    onViewPressed: () -> Unit = {}
) {
    var slots = ""
    for (i in range(0, skill.slots!!.size)){
        slots += skill.slots[i] + "/"
    }
    slots = slots.slice(IntRange(0, slots.length-2))
    AppListItem(header = skill.name ?: "No Name Found", subtitle = "Level: ${skill.level} | ${skill.type} | ${slots}", onViewPressed = onViewPressed)
}