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
import com.example.pentapal.ui.components.EnemyListItem
import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.viewmodels.BrowseEncountersViewModel
import com.example.pentapal.ui.viewmodels.BrowseEnemiesViewModel

@Composable
fun BrowseEncountersScreen(navHostController: NavHostController) {
    val viewModel: BrowseEncountersViewModel = viewModel()
    val state = viewModel.uiState
    LaunchedEffect(key1 = true){
        viewModel.getEncounters()
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
            items(state.encounters.sortedWith(comparator = compareBy<Encounter>({it.name})), {it.id!!}) {
                EncounterListItem(encounter = it, onViewPressed = { navHostController.navigate("viewencounter?id=${it.id}") })
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}