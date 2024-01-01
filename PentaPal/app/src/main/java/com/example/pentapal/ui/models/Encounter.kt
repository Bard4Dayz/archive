package com.example.pentapal.ui.models

import com.google.firebase.firestore.DocumentReference

data class Encounter(
    var id: String? = null,
    val difficulty: String? = null,
    var enemies: List<DocumentReference>? = null,
    var enemiesFull: List<Enemy>?= null,
    val name: String? = null,
    val userId: String? = null,
) {}