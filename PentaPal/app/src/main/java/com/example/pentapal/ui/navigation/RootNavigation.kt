package com.example.pentapal.ui.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.pentapal.ui.models.Encounter
import com.example.pentapal.ui.models.Enemy
import com.example.pentapal.ui.models.Shop
import com.example.pentapal.ui.models.Skill
import com.example.pentapal.ui.screens.*
import com.example.pentapal.ui.viewmodels.GenerateEnemyScreen
import com.example.pentapal.ui.viewmodels.RootNavigationViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RootNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val viewModel: RootNavigationViewModel = viewModel()
    Scaffold(
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        scaffoldState = scaffoldState,
        topBar = {
            if(currentDestination?.hierarchy?.none { it.route == Routes.launchNavigation.route || it.route == Routes.splash.route } == true) {
                TopAppBar(modifier = Modifier, backgroundColor = MaterialTheme.colors.primary) {
                    IconButton(onClick = { scope.launch{ scaffoldState.drawerState.open() } }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu Button")
                    }
                    if (currentDestination.route == Routes.home.route) {
                        Text(text = "Home", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.saved.route) {
                        Text(text = "Saved Content", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.generate.route) {
                        Text(text = "Generate", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.browse.route) {
                        Text(text = "Browse", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.browseSkills.route) {
                        Text(text = "Browse Skills", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == "viewskill?id={id}") {
                        Text(text = "View Skill", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.browseEnemies.route) {
                        Text(text = "Saved Enemies", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == "viewenemy?id={id}") {
                        Text(text = "View Saved Enemy", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.browseEncounters.route) {
                        Text(text = "Saved Encounters", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == "viewencounter?id={id}") {
                        Text(text = "View Saved Encounter", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.browseShops.route) {
                        Text(text = "Saved Shops", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == "viewshop?id={id}") {
                        Text(text = "View Saved Shop", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.generateReward.route) {
                        Text(text = "Generate Room Reward", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.generateSkill.route) {
                        Text(text = "Generate Skill", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.result.route) {
                        Text(text = "Newly Generated", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.generateEnemy.route) {
                        Text(text = "Generate New Enemy", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.generateShop.route) {
                        Text(text = "Generate New Shop", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.generateEncounter.route) {
                        Text(text = "Generate New Encounter", style = MaterialTheme.typography.h3)
                    } else if (currentDestination.route == Routes.load.route) {
                        Text(text = "Loading", style = MaterialTheme.typography.h3)
                    }
                }
            }
        },
        drawerContent = {
            if (currentDestination?.hierarchy?.none { it.route == Routes.launchNavigation.route || it.route == Routes.splash.route } == true) {
                DropdownMenuItem(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.snapTo(DrawerValue.Closed)
                    }
                    navController.navigate(Routes.home.route)
                }) {
                    Icon(Icons.Default.Home, "Home Page")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Home", style = MaterialTheme.typography.h3)
                }
                DropdownMenuItem(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.snapTo(DrawerValue.Closed)
                    }
                    navController.navigate(Routes.saved.route)
                }) {
                    Icon(Icons.Default.Download, "Saved Content Page")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Saved Content", style = MaterialTheme.typography.h3)
                }
                DropdownMenuItem(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.snapTo(DrawerValue.Closed)
                    }
                    navController.navigate(Routes.generate.route)
                }) {
                    Icon(Icons.Default.NoteAdd, "Generate Content Page")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Generate", style = MaterialTheme.typography.h3)
                }
                DropdownMenuItem(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.snapTo(DrawerValue.Closed)
                    }
                    navController.navigate(Routes.browse.route)
                }) {
                    Icon(Icons.Default.Book, "Browse Content Page")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Browse", style = MaterialTheme.typography.h3)
                }
                DropdownMenuItem(onClick = {
                    viewModel.signOutUser()
                    scope.launch {
                        scaffoldState.drawerState.snapTo(DrawerValue.Closed)
                    }
                    navController.navigate(Routes.launchNavigation.route) {
                        popUpTo(0)
                    }
                }) {
                    Icon(Icons.Default.ExitToApp, "Logout")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Logout", style = MaterialTheme.typography.h3)
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.splash.route,
            modifier = Modifier.padding(paddingValues = it)
        ) {
            navigation(route = Routes.launchNavigation.route, startDestination = Routes.launch.route) {
                composable(route = Routes.launch.route) { LaunchScreen(navController) }
                composable(route = Routes.signIn.route) { SignInScreen(navController) }
                composable(route = Routes.signUp.route) { SignUpScreen(navController) }
            }
            navigation(route = Routes.appNavigation.route, startDestination = Routes.home.route) {
                composable(route = Routes.home.route) { HomeScreen(navController) }
                composable(route = Routes.saved.route) { SavedContentScreen(navController) }
                composable(route = Routes.generate.route) { GenerateScreen(navController) }
                composable(route = Routes.browse.route) { BrowseScreen(navController) }
                composable(route = Routes.browseSkills.route) { BrowseSkillsScreen(navController) }
                composable(route = "viewskill?id={id}",
                arguments = listOf(navArgument("id") {defaultValue = ""})) {
                    navBackStackEntry -> ViewSkillScreen(navHostController = navController, id = navBackStackEntry.arguments?.get("id").toString())
                }
                composable(route = Routes.browseEnemies.route) { BrowseEnemiesScreen(navController) }
                composable(route = "viewenemy?id={id}",
                    arguments = listOf(navArgument("id") {defaultValue = ""})) {
                        navBackStackEntry -> ViewEnemyScreen(navHostController = navController, id = navBackStackEntry.arguments?.get("id").toString())
                }
                composable(route = Routes.browseEncounters.route) { BrowseEncountersScreen(navController) }
                composable(route = "viewencounter?id={id}",
                    arguments = listOf(navArgument("id") {defaultValue = ""})) {
                        navBackStackEntry -> ViewEncounterScreen(navHostController = navController, id = navBackStackEntry.arguments?.get("id").toString())
                }
                composable(route = Routes.browseShops.route) { BrowseShopsScreen(navController) }
                composable(route = "viewshop?id={id}",
                    arguments = listOf(navArgument("id") {defaultValue = ""})) {
                        navBackStackEntry -> ViewShopScreen(navHostController = navController, id = navBackStackEntry.arguments?.get("id").toString())
                }
                composable(route = Routes.generateReward.route) { GenerateRewardScreen(navController) }
                composable(route = Routes.generateSkill.route) { GenerateSkillScreen(navController) }
                composable(route = Routes.generateEnemy.route) { GenerateEnemyScreen(navController) }
                composable(route = Routes.generateShop.route) { GenerateShopScreen(navController) }
                composable(route = Routes.generateEncounter.route) { GenerateEncounterScreen(navController) }
                composable(route = Routes.result.route) { ResultScreen(navController) }
                composable(route = Routes.load.route) { LoadScreen(navController) }
            }
            composable(route = Routes.splash.route) { SplashScreen(navController) }
        }
    }
}

