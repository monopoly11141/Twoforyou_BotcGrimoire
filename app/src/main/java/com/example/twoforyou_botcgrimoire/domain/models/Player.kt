package com.example.twoforyou_botcgrimoire.domain.models

import com.example.twoforyou_botcgrimoire.domain.enums.Alignment

data class Player(
    val character: Character? = null,
    val alignment: Alignment? = null,
    val isAlive: Boolean = true,
    val reminderTokens : List<String>? = null,
    val hasOneTimeVoteToken: Boolean = true
)
