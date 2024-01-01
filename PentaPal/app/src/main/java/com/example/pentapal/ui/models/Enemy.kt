package com.example.pentapal.ui.models

import com.google.firebase.firestore.DocumentReference

data class Slot(
    val skill: DocumentReference? = null,
    val slot: String? = null,
    var connections: String? = null,
    var skillFull: Skill? = null,
){}

data class Stats(
    var focus: Int? = null,
    var magic: Int? = null,
    var power: Int? = null,
    var speed: Int? = null,
    var vigor: Int? = null,
){}

data class Enemy(
    var id: String? = null,
    val name: String? = null,
    val slots: List<Slot>? = null,
    val stats: Stats? = null,
    val type: String? = null,
    val passive: String? = null,
    val userId: String? = null,
    val floor: String? = null,
) {}