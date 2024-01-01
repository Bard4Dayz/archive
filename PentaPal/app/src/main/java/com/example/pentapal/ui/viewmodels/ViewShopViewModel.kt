package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Shop
import com.example.pentapal.ui.repositories.EncountersRepository
import com.example.pentapal.ui.repositories.ShopsRepository

class ViewShopState {
    val _shops = mutableStateListOf<Shop>()
    val shops: List<Shop> get() = _shops
}

class ViewShopViewModel(application: Application): AndroidViewModel(application) {
    val uiState = ViewShopState()
    var id: String? = null

    suspend fun getShop(id: String) {
        val shop = ShopsRepository.getShops().find {it.id == id}
        uiState._shops.clear()
        if (shop != null) {
            uiState._shops.add(shop)
        }
    }

}