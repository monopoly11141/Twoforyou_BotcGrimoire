package com.example.twoforyou_botcgrimoire.ui.screen.grimoire

import com.example.twoforyou_botcgrimoire.domain.models.Character

data class GrimoireUiState(
    var inPlayCharacters: List<Character> = emptyList(),
    var possibleCharacters: List<Character> = emptyList()
)