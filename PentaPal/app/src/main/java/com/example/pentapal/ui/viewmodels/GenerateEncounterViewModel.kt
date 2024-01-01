package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.models.Skill
import com.example.pentapal.ui.repositories.UserRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.stream.IntStream.range
import kotlin.random.Random

class GenerateEncounterState {
    val skills = mutableStateListOf<Skill>()
    val enemies = mutableStateListOf<Enemy>()
    val name = mutableStateOf("Name")
    val difficulty = mutableStateOf("Very Easy")
    val floor = mutableStateOf("1")
    val count = mutableStateOf("Random")
    val savedEnemies = mutableStateOf(false)
}

class GenerateEncounterViewModel(application: Application): AndroidViewModel(application){
    val uiState = GenerateEncounterState()
}

fun generateEncounter(
    skills: List<Skill>,
    enemies: List<Enemy>,
    name: String,
    difficulty: String,
    floor: String,
    count: String,
    savedEnemies: Boolean,
): Encounter{
    val randomGenerator = Random(System.currentTimeMillis())
    var countInt = 0
    if(count == "Random"){
        if(difficulty == "Very Easy"){
            countInt = 2 + randomGenerator.nextInt(0,2)
        } else if(difficulty == "Easy"){
            countInt = 2 + randomGenerator.nextInt(0,4)
        } else if(difficulty == "Medium"){
            countInt = 4 + randomGenerator.nextInt(-1,3)
        } else if(difficulty == "Hard"){
            countInt = 5 + randomGenerator.nextInt(-1,2)
        } else if(difficulty == "Very Hard"){
            countInt = 5 + randomGenerator.nextInt(-2,2)
        } else if(difficulty == "Boss"){
            countInt = 1 + randomGenerator.nextInt(0,5)
        }
    } else if(count == "6+"){
        countInt = randomGenerator.nextInt(6, 11)
    } else {
        countInt = count.toInt()
    }
    val classList = mutableListOf<String>()
    if(difficulty == "Very Easy"){
        classList.add("Basic")
        classList.add("Basic")
        classList.add("Basic")
        classList.add("Special")
    } else if(difficulty == "Easy"){
        classList.add("Special")
        classList.add("Basic")
        classList.add("Basic")
        classList.add("Special")
        classList.add("Basic")
        classList.add("Elite")
    } else if(difficulty == "Medium"){
        classList.add("Special")
        classList.add("Basic")
        classList.add("Basic")
        classList.add("Elite")
        classList.add("Basic")
        classList.add("Special")
    } else if(difficulty == "Hard"){
        classList.add("Elite")
        classList.add("Special")
        classList.add("Basic")
        classList.add("Special")
        classList.add("Basic")
        classList.add("Special")
    } else if(difficulty == "Very Hard"){
        classList.add("Miniboss")
        classList.add("Special")
        classList.add("Basic")
        classList.add("Basic")
        classList.add("Basic")
        classList.add("Elite")
    } else if(difficulty == "Boss"){
        classList.add("Boss")
        classList.add("Basic")
        classList.add("Basic")
        classList.add("Basic")
        classList.add("Basic")
        classList.add("Special")
    }
    val enemiesFull = mutableListOf<Enemy>()
    val enemiesRefs = mutableListOf<DocumentReference>()
    if(savedEnemies){
        for(i in range(0, countInt)) {
            val currentType: String
            if(i < classList.size){
                currentType = classList[i]
            } else if (i < classList.size * 2) {
                currentType = classList[i - classList.size]
            } else {
                currentType = classList[i - classList.size*2]
            }
            val filteredEnemies = enemies.filter {enemy -> enemy.type == currentType && enemy.floor == floor}
            val newEnemy: Enemy
            if(filteredEnemies.isEmpty()){
                newEnemy = generateEnemy(true,true,true,true,true,currentType,"Any",floor,skills)
            } else {
                newEnemy = filteredEnemies[randomGenerator.nextInt(0, filteredEnemies.size)]
            }
            enemiesFull.add(newEnemy)
            enemiesRefs.add(Firebase.firestore.document("enemies/${newEnemy.id}"))
        }
    } else {
        for (i in range(0, countInt)){
            val currentType: String
            if(i < classList.size){
                currentType = classList[i]
            } else if (i < classList.size * 2) {
                currentType = classList[i - classList.size]
            } else {
                currentType = classList[i - classList.size*2]
            }
            val newEnemy = generateEnemy(true,true,true,true,true,currentType,"Any",floor,skills)
            enemiesFull.add(newEnemy)
            enemiesRefs.add(Firebase.firestore.document("enemies/${newEnemy.id}"))
        }
    }
    return Encounter(enemies = enemiesRefs, enemiesFull = enemiesFull, name = name, userId = UserRepository.getCurrentUserId(), difficulty = difficulty)
}