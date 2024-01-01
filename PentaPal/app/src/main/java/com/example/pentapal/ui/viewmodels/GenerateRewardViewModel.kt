package com.example.pentapal.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import kotlin.random.Random

class GenerateRewardState {
    val singleSkill = mutableStateOf(true)
    val multiSkill = mutableStateOf(true)
    val singleUpgrade = mutableStateOf(true)
    val multiUpgrade = mutableStateOf(true)
    val multiStat = mutableStateOf(true)
    val singleStat = mutableStateOf(true)
    val multiHeal = mutableStateOf(true)
    val singleHeal = mutableStateOf(true)
    val money = mutableStateOf(true)
    val nothing = mutableStateOf(true)
    val showResult = mutableStateOf(false)
    val result = mutableStateOf("")
}

class GenerateRewardViewModel(application: Application): AndroidViewModel(application){
    val uiState = GenerateRewardState()

    fun generateReward(){
        var randomGenerator = Random(System.currentTimeMillis())
        val nothingRange = 51
        val moneyRange = 50
        val sStatRange = 30
        val mStatRange = 15
        val sHealRange = 40
        val mHealRange = 20
        val sUpgradeRange = 8
        val sSkillRange = 5
        val mUpgradeRange = 4
        val mSkillRange = 2
        val oddsTotal = mutableStateOf(0)
        val options = mutableStateMapOf<String, Int>()
        if(uiState.nothing.value){
            oddsTotal.value = oddsTotal.value + nothingRange
            options["Nothing"] = oddsTotal.value
        }
        if(uiState.money.value){
            oddsTotal.value += moneyRange
            options["Money"] = oddsTotal.value
        }
        if(uiState.singleStat.value){
            oddsTotal.value += sStatRange
            options["Single Stat"] = oddsTotal.value
        }
        if(uiState.multiStat.value){
            oddsTotal.value += mStatRange
            options["All Stat"] = oddsTotal.value
        }
        if(uiState.singleHeal.value){
            oddsTotal.value += sHealRange
            options["Single Heal"] = oddsTotal.value
        }
        if(uiState.multiHeal.value){
            oddsTotal.value += mHealRange
            options["All Heal"] = oddsTotal.value
        }
        if(uiState.singleSkill.value){
            oddsTotal.value += sSkillRange
            options["Single Skill"] = oddsTotal.value
        }
        if(uiState.multiSkill.value){
            oddsTotal.value += mSkillRange
            options["All Skill"] = oddsTotal.value
        }
        if(uiState.singleUpgrade.value){
            oddsTotal.value += sUpgradeRange
            options["Single Upgrade"] = oddsTotal.value
        }
        if(uiState.multiUpgrade.value){
            oddsTotal.value += mUpgradeRange
            options["All Upgrade"] = oddsTotal.value
        }
        if (oddsTotal.value > 0){
            val selection = randomGenerator.nextInt(0, oddsTotal.value)
            var selectedOption = ""
            var lowerRange = -1
            var upperRange = oddsTotal.value + 1
            options.forEach { option ->
                if(option.value < upperRange && selection < option.value){
                    upperRange = option.value
                    if(selection <= upperRange && selection > lowerRange){
                        selectedOption = option.key
                    }
                }
                if(option.value < selection && option.value > lowerRange){
                    lowerRange = option.value
                }
            }
            if (selectedOption == "Money"){
                val money = randomGenerator.nextInt(1, 6) * 10
                uiState.result.value = "Currency per Player: ${money}"
            } else if (selectedOption == "All Stat" || selectedOption == "Single Stat"){
                val statInt = randomGenerator.nextInt(1,6)
                var stat = ""
                if (statInt == 1){
                    stat = "Power"
                } else if (statInt == 2){
                    stat = "Speed"
                } else if (statInt == 3){
                    stat = "Vigor"
                } else if (statInt == 4){
                    stat = "Magic"
                } else if (statInt == 5){
                    stat = "Focus"
                }
                uiState.result.value = "${selectedOption}: ${stat} + 1"
            } else {
                uiState.result.value = selectedOption
            }
        } else {
            uiState.result.value = "No options selected. Please select type of reward to be generated."
        }
        uiState.showResult.value = true
    }
}