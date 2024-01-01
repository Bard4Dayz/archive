package com.example.pentapal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pentapal.ui.components.AppSkillCard
import com.example.pentapal.ui.components.SkillListItem
import com.example.pentapal.ui.models.Skill
import com.example.pentapal.ui.theme.Black
import com.example.pentapal.ui.viewmodels.BrowseSkillsViewModel

@Composable
fun BrowseSkillsScreen(navHostController: NavHostController) {
    val viewModel: BrowseSkillsViewModel = viewModel()
    val state = viewModel.uiState
    LaunchedEffect(key1 = true){
        viewModel.getSkills()
//        viewModel.loadSkills()
    }
    Box(modifier = Modifier.fillMaxSize()){
        Column(){
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxSize()
            ) {
                item {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        IconButton(onClick = { /* TODO */ }){
                            Icon(Icons.Default.MoreVert, contentDescription = "Menu Button")
                        }
                    }
                }
                items(state.skills.sortedWith(comparator = compareBy<Skill>({it.name})), {it.id!!}) {
                    SkillListItem(skill = it, onViewPressed = { navHostController.navigate("viewskill?id=${it.id}") })
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }

    }

}