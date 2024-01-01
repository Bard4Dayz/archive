package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.models.Skill
import kotlin.random.Random

class GenerateSkillState {
    val attackSlot = mutableStateOf(true)
    val movementSlot = mutableStateOf(true)
    val defenseSlot = mutableStateOf(true)
    val supportSlot = mutableStateOf(true)
    val utilitySlot = mutableStateOf(true)
    val floor = mutableStateOf("None")
    val level = mutableStateOf("Any")
    val set = mutableStateOf("Any")
    val type = mutableStateOf("Any")
    val skills = mutableStateListOf<Skill>()
}

class GenerateSkillViewModel(application: Application): AndroidViewModel(application){
    val uiState = GenerateSkillState()
}

fun generateSkill(attackSlot: Boolean,
                  movementSlot: Boolean,
                  defenseSlot: Boolean,
                  supportSlot: Boolean,
                  utilitySlot: Boolean,
                  floor: String,
                  level: String,
                  set: String,
                  type: String,
                  skills: List<Skill>,
                  slot: String? = ""
): Skill{
    var filteredSkills = skills
    if(level != "Any"){
        filteredSkills = skills.filter {skill -> skill.level == level.toInt() }
    } else {
        if(floor != "None"){
            var filterLevel = 0
            if(floor == "1"){
                filterLevel = levelGen(75,16, 5, 3, 1)
            } else if(floor == "2"){
                filterLevel = levelGen(0, 75, 16, 6, 3)
            } else if(floor == "3"){
                filterLevel = levelGen(0, 0, 75, 16, 9)
            } else if(floor == "4"){
                filterLevel = levelGen(0, 0, 0, 75, 25)
            } else if(floor == "5"){
                filterLevel = levelGen(0, 0, 0, 25, 75)
            }
            filteredSkills = skills.filter {skill -> skill.level == filterLevel}
        }
    }
    if(set != "Any"){
        filteredSkills = filteredSkills.filter {skill -> skill.set == set}
    }
    if(type != "Any"){
        filteredSkills = filteredSkills.filter {skill -> skill.type == type}
    } else {
        filteredSkills = filteredSkills.filter {skill -> skill.type != "Enemy"}
    }
    if(!movementSlot){
        filteredSkills = filteredSkills.filter {skill -> !(skill.slots?.contains("Movement") ?: true)}
    }
    if(!attackSlot){
        filteredSkills = filteredSkills.filter {skill -> !(skill.slots?.contains("Attack") ?: true)}
    }
    if(!defenseSlot){
        filteredSkills = filteredSkills.filter {skill -> !(skill.slots?.contains("Defense") ?: true)}
    }
    if(!supportSlot){
        filteredSkills = filteredSkills.filter {skill -> !(skill.slots?.contains("Support") ?: true)}
    }
    if(!utilitySlot){
        filteredSkills = filteredSkills.filter {skill -> !(skill.slots?.contains("Utility") ?: true)}
    }
    if(slot != ""){
        filteredSkills = filteredSkills.filter {skill -> skill.slots?.contains(slot) ?: true}
    }
    if(filteredSkills.isNotEmpty()){
        val randomGenerator = Random(System.currentTimeMillis())
        val random = randomGenerator.nextInt(0, filteredSkills.size)
        return filteredSkills[random]
    } else {
        return Skill(
            description = "No skills fit these criteria yet.  Please wait for the developer to create more.  It may take awhile.",
            name = "Skill Issue",
            set = "Laziness!",
            slots = listOf("None"),
            type = "Nothing",
            traits = listOf(""),
            level = 0,
        )
    }
}

fun levelGen(odds1: Int, odds2: Int, odds3: Int, odds4: Int, odds5: Int): Int{
    val randomGenerator = Random(System.currentTimeMillis())
    val ranges = mutableStateMapOf<Int, Int>()
    val oddsTotal = mutableStateOf(0)
    oddsTotal.value += odds1
    ranges[1] = oddsTotal.value
    oddsTotal.value += odds2
    ranges[2] = oddsTotal.value
    oddsTotal.value += odds3
    ranges[3] = oddsTotal.value
    oddsTotal.value += odds4
    ranges[4] = oddsTotal.value
    oddsTotal.value += odds5
    ranges[5] = oddsTotal.value
    val selection = randomGenerator.nextInt(0, oddsTotal.value)
    var selectedOption = 0
    var lowerRange = -1
    var upperRange = oddsTotal.value + 1
    ranges.forEach { range ->
        if(range.value < upperRange && selection < range.value){
            upperRange = range.value
            if(selection <= upperRange && selection > lowerRange){
                selectedOption = range.key
            }
        }
        if(range.value < selection && range.value > lowerRange){
            lowerRange = range.value
        }
    }
    return selectedOption
}