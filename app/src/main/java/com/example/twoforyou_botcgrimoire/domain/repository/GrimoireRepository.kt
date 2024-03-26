package com.example.twoforyou_botcgrimoire.domain.repository

import com.example.twoforyou_botcgrimoire.domain.models.Character
import kotlinx.coroutines.flow.StateFlow

interface GrimoireRepository {

    val inPlayCharacters: StateFlow<List<Character>>
    val possibleCharacters: StateFlow<List<Character>>

    fun updateInPlayCharacters(updatedInPlayCharacters: List<Character>)
}