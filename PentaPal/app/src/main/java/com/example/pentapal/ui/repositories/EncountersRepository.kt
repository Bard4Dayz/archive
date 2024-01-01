package com.example.pentapal.ui.repositories

import androidx.compose.runtime.mutableStateOf
import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Enemy
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.stream.IntStream
import java.util.stream.IntStream.range

object EncountersRepository {
    private val encountersCache = mutableListOf<Encounter>()
    private var cacheInitialized = false
    private var encounterBuffer = mutableStateOf(Encounter())

    suspend fun getEncounters(): List<Encounter> {
        if (!cacheInitialized) {
            cacheInitialized = true
            val snapshot = Firebase.firestore.collection("encounters").whereEqualTo("userId", UserRepository.getCurrentUserId()).get().await()
            val encounters = snapshot.toObjects<Encounter>()
            val fullEncounters = mutableListOf<Encounter>()
            for (i in IntStream.range(0, encounters.size)){
                val newEncounter = encounters[i]
                val enemyList = mutableListOf<Enemy>()
                for (j in IntStream.range(0, (encounters[i].enemies ?: listOf()).size)){
                    val enemy = EnemiesRepository.getEnemies().find {it.id == encounters[i].enemies?.get(j)?.id }
                    if (enemy != null) {
                        enemyList.add(enemy)
                    }
                }
                newEncounter.enemiesFull = enemyList
                fullEncounters.add(newEncounter)
            }
            encountersCache.addAll(encounters)
        }
        return encountersCache
    }

    suspend fun saveEncounter(encounter: Encounter){
        val doc = Firebase.firestore.collection("encounters").document()
        encounter.id = doc.id
        for (enemy in encounter.enemiesFull!!){
            EnemiesRepository.saveEnemy(enemy)
        }
        doc.set(encounter).await()
        if(cacheInitialized){
            encountersCache.add(encounter)
        }
    }

    fun setBuffer(encounter: Encounter){
        encounterBuffer.value = encounter
    }

    fun getBuffer(): Encounter{
        return encounterBuffer.value
    }

    fun resetBuffer(){
        encounterBuffer.value = Encounter()
    }

    fun resetCache() {
        encountersCache.removeAll { it.userId == UserRepository.getCurrentUserId() }
        cacheInitialized = false
    }
}