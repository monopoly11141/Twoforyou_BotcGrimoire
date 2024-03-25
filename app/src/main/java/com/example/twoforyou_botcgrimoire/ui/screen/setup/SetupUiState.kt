package com.example.twoforyou_botcgrimoire.ui.screen.setup

import com.example.twoforyou_botcgrimoire.domain.enums.Edition
import com.example.twoforyou_botcgrimoire.domain.models.Character

data class SetupUiState(
    val edition: Edition = Edition.소란발생_TROUBLE_BREWING,
    val playerCount: Int = 0,
    val possibleCharacters: List<Character> = emptyList(),
    val inPlayCharacters: List<Character> = emptyList(),
    val maximumCharacterTypeCountList: MutableList<Int> = mutableListOf(0, 0, 0, 0),
    val currentCharacterTypeCountList: MutableList<Int> = mutableListOf(0, 0, 0, 0)
)