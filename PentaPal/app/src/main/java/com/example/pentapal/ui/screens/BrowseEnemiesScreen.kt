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
import com.example.pentapal.ui.components.EnemyListItem
import com.example.pentapal.ui.components.SkillListItem
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.models.Skill
import com.example.pentapal.ui.viewmodels.BrowseEnemiesViewModel
import com.example.pentapal.ui.viewmodels.BrowseSkillsViewModel

@Composable
fun BrowseEnemiesScreen(navHostController: NavHostController) {
    val viewModel: BrowseEnemiesViewModel = viewModel()
    val state = viewModel.uiState
    LaunchedEffect(key1 = true){
        viewModel.getEnemies()
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
            items(state.enemies.sortedWith(comparator = compareBy<Enemy>({it.name})), {it.id!!}) {
                EnemyListItem(enemy = it, onViewPressed = { navHostController.navigate("viewenemy?id=${it.id}") })
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}