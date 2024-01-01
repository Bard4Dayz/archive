package com.example.pentapal.ui.models

data class Skill (
    val id: String? = null,
    val description: String? = null,
    val focusMode: Boolean? = null,
    val level: Int? = null,
    val name: String? = null,
    val set: String? = null,
    val slots: List<String>? = null,
    val traits: List<String>? = null,
    val type: String? = null,
) {}