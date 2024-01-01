package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.models.Skill
import com.example.pentapal.ui.models.Slot
import com.example.pentapal.ui.models.Stats
import com.example.pentapal.ui.repositories.UserRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Integer.min
import java.util.stream.IntStream.range
import kotlin.random.Random

class GenerateEnemyState {
    val skills = mutableStateListOf<Skill>()
    val type = mutableStateOf("Basic")
    val set = mutableStateOf("Any")
    val attackSlot = mutableStateOf(true)
    val movementSlot = mutableStateOf(true)
    val defenseSlot = mutableStateOf(true)
    val supportSlot = mutableStateOf(true)
    val utilitySlot = mutableStateOf(true)
    val floor = mutableStateOf("1")
}

class GenerateEnemyViewModel(application: Application): AndroidViewModel(application){
    val uiState = GenerateEnemyState()
}

fun generateEnemy(
    attackSlot: Boolean,
    movementSlot: Boolean,
    defenseSlot: Boolean,
    supportSlot: Boolean,
    utilitySlot: Boolean,
    type: String,
    set: String,
    floor: String,
    skills: List<Skill>
): Enemy{
    var typeInt = 3
    var passive = "None"
    if(type == "Special"){
        typeInt = 4
    } else if(type == "Elite"){
        typeInt = 4
        passive = passiveGen(skills, floor, set)
    } else if(type == "Miniboss"){
        typeInt = 5
        passive = passiveGen(skills, floor, set)
    } else if(type == "Boss"){
        typeInt = 6
        passive = passiveGen(skills, floor, set)
    }
    val stats = statGen(type, floor)
    val userId = UserRepository.getCurrentUserId()
    val slotTypes = mutableStateListOf<String>()
    val slotNames = mutableStateListOf<String>()
    if(attackSlot){
        slotTypes.add("Attack")
    }
    if(movementSlot){
        slotTypes.add("Movement")
    }
    if(defenseSlot){
        slotTypes.add("Defense")
    }
    if(supportSlot){
        slotTypes.add("Support")
    }
    if(utilitySlot){
        slotTypes.add("Utility")
    }
    slotTypes.shuffle()
    for (i in range(0, typeInt)){
        if (i <= slotTypes.size - 1){
            slotNames.add(slotTypes[i])
        } else if (i <= slotTypes.size*2-1) {
            slotNames.add(slotTypes[i-slotTypes.size] + " 2")
        } else if (i <= slotTypes.size*3-1) {
            slotNames.add(slotTypes[i-slotTypes.size*2] + " 3")
        } else if (i <= slotTypes.size*4-1) {
            slotNames.add(slotTypes[i-slotTypes.size*3] + " 4")
        } else if (i <= slotTypes.size*5-1) {
            slotNames.add(slotTypes[i-slotTypes.size*4] + " 5")
        } else if (i <= slotTypes.size*6-1) {
            slotNames.add(slotTypes[i-slotTypes.size*5] + " 6")
        } else if (i <= slotTypes.size*7-1) {
            slotNames.add(slotTypes[i-slotTypes.size*6] + " 7")
        }
    }
    val slots = mutableStateListOf<Slot>()
    var count = 0
    for (slotName in slotNames){
        count ++
        if(count % 2 == 0){
            slots.add(slotGen(slotName, set, floor, skills, true))
        } else {
            slots.add(slotGen(slotName, set, floor, skills, false))
        }
    }
    val finalSlots = connectionGen(slots.toList())
    val name = nameGen(finalSlots[1].skillFull?.name?.split(" ")?.get(0), type)
    val doc = Firebase.firestore.collection("enemies").document()
    return Enemy(name = name, slots = finalSlots, stats = stats, type = type, passive = passive, userId = userId, floor = floor, id = doc.id)
}

fun nameGen(title: String? = "Normal", type: String): String{
    val nameOptions: List<String>
    val randomGenerator = Random(System.currentTimeMillis())
    if(type == "Basic"){
        nameOptions = listOf("Goon",
            "Ratman",
            "Runt",
            "Henchman",
            "Bandit",
            "Wolf",
            "Gnome",
            "Spook",
            "Nuisance",
            "Basic"
        )
    } else if(type == "Special"){
        nameOptions = listOf(
            "Knight",
            "Guard",
            "Monster",
            "Tiger",
            "Captain",
            "Leader",
            "Special"
        )
    } else if(type == "Elite"){
        nameOptions = listOf(
            "Black Knight",
            "Demigod",
            "Prophet",
            "Demon",
            "Young Dragon",
            "Upper Echelon",
            "Elite"
        )
    } else if(type == "Miniboss"){
        nameOptions = listOf(
            "Giant",
            "Troll Warlord",
            "Calamity",
            "Tortured Creation",
            "Dragon",
            "Powerhouse",
            "MiniBoss"
        )
    } else {
        nameOptions = listOf(
            "God",
            "Anathor",
            "Keeper of Souls",
            "Death Incarnate",
            "Big Bad Evil Guy",
            "The Beast of the Pit",
            "Boss"
        )
    }
    return title + " " + nameOptions[randomGenerator.nextInt(0, nameOptions.size)]
}

fun statGen(type: String, floor: String): Stats {
    val randomGenerator = Random(System.currentTimeMillis())
    val stats = Stats()
    val skillTotal: Int
    var skillPoints: Int
    val floorBonus = floor.toInt() * 5
    if(type == "Basic"){
        skillTotal = 15 + floorBonus
        skillPoints = 15 + floorBonus
        stats.vigor = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.vigor!!

        stats.power = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.power!!
        stats.magic = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.magic!!
        stats.speed = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.speed!!
        stats.focus = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.focus!!
    } else if(type == "Special"){
        skillTotal = 15 + floorBonus
        skillPoints = 15 + floorBonus
        stats.vigor = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1)) + 3
        skillPoints -= stats.vigor!!

        stats.power = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.power!!
        stats.magic = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.magic!!
        stats.speed = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.speed!!
        stats.focus = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.focus!!
    } else if(type == "Elite"){
        skillTotal = 20 + floorBonus
        skillPoints = 20 + floorBonus
        stats.vigor = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1)) + 3
        skillPoints -= stats.vigor!!

        stats.power = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.power!!
        stats.magic = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.magic!!
        stats.speed = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.speed!!
        stats.focus = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.focus!!
    } else if(type == "Miniboss"){
        skillTotal = 20 + floorBonus
        skillPoints = 20 + floorBonus
        stats.vigor = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1)) + 4
        skillPoints -= stats.vigor!!

        stats.power = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.power!!
        stats.magic = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.magic!!
        stats.speed = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.speed!!
        stats.focus = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.focus!!
    } else if(type == "Boss"){
        skillTotal = 25 + floorBonus
        skillPoints = 25 + floorBonus
        stats.vigor = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1)) + 6
        skillPoints -= stats.vigor!!

        stats.power = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.power!!
        stats.magic = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.magic!!
        stats.speed = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.speed!!
        stats.focus = randomGenerator.nextInt(min(skillPoints, (skillTotal/7)), min((skillTotal / 3), skillPoints+1))
        skillPoints -= stats.focus!!
    }
    return stats
}

fun slotGen(slot: String, set: String, floor: String, skills: List<Skill>, special: Boolean): Slot {
    val parsedSlot = slot.split(" ")
    val skill: Skill
    if (special){
        skill = generateSkill(true, true, true, true, true, floor, "Any", set, "Active", skills, parsedSlot[0])
    } else {
        if (parsedSlot[0] == "Utility"){
            skill = generateSkill(true, true, true, true, true, "None", "Any", set, "Enemy", skills)
        } else {
            skill = generateSkill(true, true, true, true, true, "None", "Any", set, "Enemy", skills, parsedSlot[0])
        }
    }
    return Slot(skillFull = skill, slot = slot, connections = null, skill = Firebase.firestore.document("skills/${skill.id ?: "eu1wgkpRTKNy8CDtEgLQ"}"))
}

fun connectionGen(slots: List<Slot>): List<Slot>{
    val slotMaps = mutableStateMapOf<String, String>()
    val slotsNames = mutableStateListOf<String>()
    val finalSlots = mutableStateListOf<Slot>()
    for (slot in slots){
        val splitSlot = slot.slot?.split(" ")
        val initial = splitSlot?.get(0)?.get(0)
        var abbreviate: String
        if((splitSlot?.size ?: 0) >= 2){
            if (initial != null) {
                abbreviate = initial + splitSlot[1]
            } else {
                abbreviate = initial.toString()
            }
        } else {
            abbreviate = initial.toString()
        }
        slotsNames.add(abbreviate)
        slotMaps[abbreviate] = ""
    }
    for(i in range(0, slotsNames.size)){
        if(i > 0){
            slotMaps[slotsNames[i-1]] += "${slotsNames[i]}/"
        } else {
            slotMaps[slotsNames[slotsNames.size-1]] += "${slotsNames[i]}/"
        }
        if(i < slotsNames.size-1){
            slotMaps[slotsNames[i+1]] += "${slotsNames[i]}/"
        } else {
            slotMaps[slotsNames[0]] += "${slotsNames[i]}/"
        }
    }
    slotsNames.forEach { slot ->
        val newString = slotMaps[slot]?.dropLast(1)
        slotMaps[slot] = newString!!
    }
    val goodSlotNames = slotsNames.toList()
    val goodSlotMap = slotMaps.toMap()
    for (i in range(0, goodSlotNames.size)){
        val newSlot = slots[i]
        newSlot.connections = goodSlotMap[goodSlotNames[i]]
        finalSlots.add(newSlot)
    }
    return finalSlots.toList()
}

fun passiveGen(skills: List<Skill>, floor: String, set: String): String{
    val skill = generateSkill(true, true, true, true, true, floor, "Any", set, "Passive", skills)
    return skill.description ?: ""
}