package com.example.pentapal.ui.repositories

import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.models.Shop
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.stream.IntStream

object ShopsRepository {
    private val shopsCache = mutableListOf<Shop>()
    private var cacheInitialized = false
    private var shopBuffer = Shop()

    suspend fun getShops(): List<Shop> {
        if (!cacheInitialized) {
            cacheInitialized = true
            val snapshot = Firebase.firestore.collection("shops").whereEqualTo("userId", UserRepository.getCurrentUserId()).get().await()
            var shops = snapshot.toObjects<Shop>()
            var fullShops = mutableListOf<Shop>()
            for (i in IntStream.range(0, shops.size)){
                val newShop = shops[i]
                for (j in IntStream.range(0, (shops[i].options ?: listOf()).size)){
                    val skill = SkillsRepository.getSkills().find {it.id == shops[i].options?.get(j)?.skill?.id }
                    newShop.options?.get(j)?.skillFull = skill
                }
                fullShops.add(newShop)
            }
            shopsCache.addAll(fullShops)
        }
        return shopsCache
    }

    suspend fun saveShop(shop: Shop){
        val doc = Firebase.firestore.collection("shops").document()
        shop.id = doc.id
        doc.set(shop).await()
        if(cacheInitialized){
            shopsCache.add(shop)
        }
    }

    fun setBuffer(shop: Shop){
        shopBuffer = shop
    }

    fun getBuffer(): Shop {
        return shopBuffer
    }

    fun resetBuffer(){
        shopBuffer = Shop()
    }

    fun resetCache() {
        shopsCache.removeAll { it.userId == UserRepository.getCurrentUserId() }
        cacheInitialized = false
    }
}