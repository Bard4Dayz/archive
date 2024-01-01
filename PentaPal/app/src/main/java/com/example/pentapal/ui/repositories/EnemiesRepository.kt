package com.example.pentapal.ui.repositories

import androidx.compose.runtime.mutableStateOf
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.models.Skill
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.stream.IntStream.range

object EnemiesRepository {
    private val enemiesCache = mutableListOf<Enemy>()
    private var cacheInitialized = false
    private var enemyBuffer = mutableStateOf(Enemy())

    suspend fun getEnemies(): List<Enemy> {
        if (!cacheInitialized) {
            cacheInitialized = true
            val snapshot = Firebase.firestore.collection("enemies").whereEqualTo("userId", UserRepository.getCurrentUserId()).get().await()
            var enemies = snapshot.toObjects<Enemy>()
            var fullEnemies = mutableListOf<Enemy>()
            for (i in range(0, enemies.size)){
                val newEnemy = enemies[i]
                for (j in range(0, (enemies[i].slots ?: listOf()).size)){
                    val skill = SkillsRepository.getSkills().find {it.id == enemies[i].slots?.get(j)?.skill?.id }
                    newEnemy.slots?.get(j)?.skillFull = skill
                }
                fullEnemies.add(newEnemy)
            }
            enemiesCache.addAll(fullEnemies)
        }
        return enemiesCache
    }

    suspend fun saveEnemy(enemy: Enemy): String{
        val doc = enemy.id?.let { Firebase.firestore.collection("enemies").document(it) }
        if (doc != null) {
            doc.set(enemy).await()
        }
        if(cacheInitialized){
            enemiesCache.add(enemy)
        }
        return enemy.id!!
    }

    fun setBuffer(enemy: Enemy){
        enemyBuffer.value = enemy
    }

    fun getBuffer(): Enemy {
        return enemyBuffer.value
    }

    fun resetBuffer(){
        enemyBuffer.value = Enemy()
    }

    fun resetCache() {
        enemiesCache.removeAll { it.userId == UserRepository.getCurrentUserId() }
        cacheInitialized = false
    }

}