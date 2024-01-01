package com.example.pentapal.ui.models

import com.google.firebase.firestore.DocumentReference

data class skillCost(
    val price: Int? = null,
    val skill: DocumentReference? = null,
    var skillFull: Skill? = null,
){}

data class Shop(
    var id: String? = null,
    val name: String? = null,
    val options: List<skillCost>? = null,
    val userId: String? = null,
) {}