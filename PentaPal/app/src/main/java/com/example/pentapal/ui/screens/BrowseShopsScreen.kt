package com.example.pentapal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pentapal.ui.components.EncounterListItem
import com.example.pentapal.ui.components.ShopListItem
import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Shop
import com.example.pentapal.ui.viewmodels.BrowseEncountersViewModel
import com.example.pentapal.ui.viewmodels.BrowseShopsViewModel

@Composable
fun BrowseShopsScreen(navHostController: NavHostController) {
    val viewModel: BrowseShopsViewModel = viewModel()
    val state = viewModel.uiState
    LaunchedEffect(key1 = true){
        viewModel.getShops()
    }
    Column(){
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
//            IconButton(onClick = { /* TODO */ }){
//                Icon(Icons.Default.MoreVert, contentDescription = "Menu Button")
//            }
//        }
        LazyColumn(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            items(state.shops.sortedWith(comparator = compareBy<Shop>({it.name})), {it.id!!}) {
                ShopListItem(shop = it, onViewPressed = { navHostController.navigate("viewshop?id=${it.id}") })
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}