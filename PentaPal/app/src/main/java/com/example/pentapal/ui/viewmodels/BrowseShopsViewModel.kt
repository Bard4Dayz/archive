package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Shop
import com.example.pentapal.ui.repositories.EncountersRepository
import com.example.pentapal.ui.repositories.ShopsRepository

class BrowseShopsState {
    val _shops = mutableStateListOf<Shop>()
    val shops: List<Shop> get() = _shops
}

class BrowseShopsViewModel(application: Application): AndroidViewModel(application) {
    val uiState = BrowseShopsState()

    suspend fun getShops() {
        val shops = ShopsRepository.getShops()
        uiState._shops.clear()
        uiState._shops.addAll(shops)
    }
}