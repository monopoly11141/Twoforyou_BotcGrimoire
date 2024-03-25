package com.example.twoforyou_botcgrimoire.data.repository

import com.example.twoforyou_botcgrimoire.domain.database.remote.FirebaseCharacterDatabase
import com.example.twoforyou_botcgrimoire.domain.enums.Character_Type
import com.example.twoforyou_botcgrimoire.domain.enums.Edition
import com.example.twoforyou_botcgrimoire.domain.models.Character
import com.example.twoforyou_botcgrimoire.domain.repository.SetupRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SetupRepositoryImpl @Inject constructor(
    private val firebaseCharacterDatabase: FirebaseCharacterDatabase
) : SetupRepository {

    private val _edition = MutableStateFlow<Edition>(Edition.소란발생_TROUBLE_BREWING)
    override val edition: StateFlow<Edition>
        get() = _edition.asStateFlow()

    private val _playerCount = MutableStateFlow<Int>(0)
    override val playerCount: StateFlow<Int>
        get() = _playerCount.asStateFlow()

    override val possibleCharacters: StateFlow<List<Character>> by lazy {
        firebaseCharacterDatabase.possibleCharacterList
    }

    private val _inPlayCharacters = MutableStateFlow<List<Character>>(emptyList())
    override val inPlayCharacters = _inPlayCharacters.asStateFlow()

    private val _maximumCharacterTypeCountList =
        MutableStateFlow<MutableList<Int>>(mutableListOf(0, 0, 0, 0))
    override val maximumCharacterTypeCountList: StateFlow<MutableList<Int>>
        get() = _maximumCharacterTypeCountList.asStateFlow()

    private val _currentCharacterTypeCountList =
        MutableStateFlow<MutableList<Int>>(mutableListOf(0, 0, 0, 0))
    override val currentCharacterTypeCountList: StateFlow<MutableList<Int>>
        get() = _currentCharacterTypeCountList.asStateFlow()

    override fun updateEdition(newEdition: Edition) {
        _edition.value = newEdition
        firebaseCharacterDatabase.getAllCharactersFromEdition(newEdition)
    }

    override fun updatePlayerCount(newPlayerCount: Int) {
        _playerCount.value = newPlayerCount

        _maximumCharacterTypeCountList.value =
            when(newPlayerCount) {
                5 -> mutableListOf(3, 0, 1, 1)
                6 -> mutableListOf(3, 1, 1, 1)
                7 -> mutableListOf(5, 0, 1, 1)
                8 -> mutableListOf(5, 1, 1, 1)
                9 -> mutableListOf(5, 2, 1, 1)
                10 -> mutableListOf(7, 0, 2, 1)
                11 -> mutableListOf(7, 1, 2, 1)
                12 -> mutableListOf(7, 2, 2, 1)
                13 -> mutableListOf(9, 0, 3, 1)
                14 -> mutableListOf(9, 1, 3, 1)
                15 -> mutableListOf(9, 2, 3, 1)
                else -> mutableListOf(-1, -1, -1, -1)
            }
    }

    override fun updateInPlayCharacters(inPlayCharacters : List<Character>) {
        _inPlayCharacters.value = inPlayCharacters
        _currentCharacterTypeCountList.value = mutableListOf(0, 0, 0, 0)
        for(character in inPlayCharacters) {
            _currentCharacterTypeCountList.value[Character_Type.entries.indexOf(character.type)]++
        }
    }

    override fun insertInPlayCharacter(insertingInPlayCharacter: Character) {
        _inPlayCharacters.value += insertingInPlayCharacter
        _currentCharacterTypeCountList.value[Character_Type.entries.indexOf(insertingInPlayCharacter.type)]++
    }

    override fun deleteInPlayCharacter(deletingInPlayCharacter : Character) {
        _inPlayCharacters.value -= deletingInPlayCharacter
        _currentCharacterTypeCountList.value[Character_Type.entries.indexOf(deletingInPlayCharacter.type)]--
    }

    override fun updateMaximumCharacterTypeCountList(newMaximumCharacterTypeCountList: MutableList<Int>) {
        _maximumCharacterTypeCountList.value = newMaximumCharacterTypeCountList
    }

    override fun updateCurrentCharacterTypeCountList(newCurrentCharacterTypeCountList: MutableList<Int>) {
        _currentCharacterTypeCountList.value = newCurrentCharacterTypeCountList
    }
}