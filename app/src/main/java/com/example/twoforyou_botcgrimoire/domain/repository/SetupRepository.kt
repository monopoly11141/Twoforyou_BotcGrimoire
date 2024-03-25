package com.example.twoforyou_botcgrimoire.domain.repository

import com.example.twoforyou_botcgrimoire.domain.enums.Edition
import com.example.twoforyou_botcgrimoire.domain.models.Character
import kotlinx.coroutines.flow.StateFlow

interface SetupRepository {
    val edition: StateFlow<Edition>

    val playerCount: StateFlow<Int>

    val possibleCharacters: StateFlow<List<Character>>
    val inPlayCharacters: StateFlow<List<Character>>

    val maximumCharacterTypeCountList: StateFlow<MutableList<Int>>
    val currentCharacterTypeCountList: StateFlow<MutableList<Int>>

    fun updateEdition(newEdition: Edition)

    fun updatePlayerCount(newPlayerCount: Int)

    fun updateInPlayCharacters(inPlayCharacters : List<Character>)
    fun insertInPlayCharacter(insertingInPlayCharacter: Character)
    fun deleteInPlayCharacter(deletingInPlayCharacter : Character)

    fun updateMaximumCharacterTypeCountList(newMaximumCharacterTypeCountList : MutableList<Int>)
    fun updateCurrentCharacterTypeCountList(newCurrentCharacterTypeCountList : MutableList<Int>)
}