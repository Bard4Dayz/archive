package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.models.Shop
import com.example.pentapal.ui.models.Skill
import com.example.pentapal.ui.models.skillCost
import com.example.pentapal.ui.repositories.UserRepository
import com.example.pentapal.ui.screens.GenerateShopScreen
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.stream.IntStream.range
import kotlin.random.Random

class GenerateShopState {
    val skills = mutableStateListOf<Skill>()
    val level = mutableStateOf("1")
    val count = mutableStateOf("Any")
    val name = mutableStateOf("Name")
}

class GenerateShopViewModel(application: Application): AndroidViewModel(application){
    val uiState = GenerateShopState()
}

fun generateShop(
    skills: List<Skill>,
    level: String,
    count: String,
    name: String,
): Shop{
    val randomGenerator = Random(System.currentTimeMillis())
    val skillList = mutableListOf<skillCost>()
    val countInt: Int
    if(count == "Any"){
        countInt = randomGenerator.nextInt(3, 9)
    } else {
        countInt = count.toInt()
    }
    for (i in range(0, countInt)){
        for (j in range(0, 100)){
            val skill = skillGen(skills, level, randomGenerator)
            if (!skillList.any{shopSkill -> shopSkill.skillFull?.name == skill.skillFull?.name}){
                skillList.add(skill)
                break
            }
        }
    }
    return Shop(name = name, userId = UserRepository.getCurrentUserId(), options = skillList)
}

fun skillGen(skills: List<Skill>, level: String, gen: Random): skillCost{
    val skill = generateSkill(true,true,true,true,true,"None",level,"Any","Any",skills)
    var cost = 40
    if(skill.focusMode == true){
        cost += 10
    }
    if(skill.type == "Both"){
        cost += 20
    }
    return skillCost(price = cost, skillFull = skill, skill = Firebase.firestore.document("skills/${skill.id ?: "eu1wgkpRTKNy8CDtEgLQ"}"))
}