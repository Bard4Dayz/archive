package com.example.pentapal.ui.navigation

data class Screen(val route: String)

object Routes {
    val launchNavigation = Screen("launchnavigation")
    val appNavigation = Screen("appnavigation")
    val launch = Screen("launch")
    val signIn = Screen("signin")
    val signUp = Screen("sighup")
    val home = Screen("home")
    val saved = Screen("saved")
    val generate = Screen("generate")
    val browse = Screen("browse")
    val browseSkills = Screen("browseskills")
    val browseEnemies = Screen("browseenemies")
    val browseEncounters = Screen("browseencounters")
    val browseShops = Screen("browseshops")
    val generateReward = Screen("generatereward")
    val generateSkill = Screen("generateskill")
    val generateEnemy = Screen("generateenemy")
    val generateShop = Screen("generateshop")
    val generateEncounter = Screen("generateencounter")
    val result = Screen("result")
    val splash = Screen("splash")
    val load = Screen("load")
}