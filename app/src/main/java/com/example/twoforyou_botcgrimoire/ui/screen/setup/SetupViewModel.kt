package com.example.twoforyou_botcgrimoire.ui.screen.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twoforyou_botcgrimoire.domain.enums.Edition
import com.example.twoforyou_botcgrimoire.domain.repository.SetupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import com.example.twoforyou_botcgrimoire.domain.models.Character
import kotlinx.coroutines.flow.update

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val repository: SetupRepository
) : ViewModel(){
    private val _state = MutableStateFlow(SetupUiState())
    val state = combine(
        repository.edition,
        repository.playerCount,
        repository.possibleCharacters,
        repository.inPlayCharacters,
        repository.maximumCharacterTypeCountList,
        repository.currentCharacterTypeCountList,
        _state
    ){array ->
        SetupUiState(
            edition = array[0] as Edition,
            playerCount = array[1] as Int,
            possibleCharacters = array[2] as List<Character>,
            inPlayCharacters = array[3] as List<Character>,
            maximumCharacterTypeCountList = array[4] as MutableList<Int>,
            currentCharacterTypeCountList = array[5] as MutableList<Int>,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    init {
        this.updateEdition(repository.edition.value)
    }

    fun updateEdition(newEdition: Edition) {
        repository.updateEdition(newEdition)
        _state.update{
            it.copy(
                edition = newEdition,
                possibleCharacters = repository.possibleCharacters.value
            )
        }
    }

    fun updatePlayerCount(newPlayerCount: Int) {
        repository.updatePlayerCount(newPlayerCount)

        _state.update{
            it.copy(
                playerCount = newPlayerCount,
                maximumCharacterTypeCountList = repository.maximumCharacterTypeCountList.value
            )
        }
    }

    fun updateInPlayCharacters(newInPlayCharacters : List<Character>) {
        _state.update{
            it.copy(
                inPlayCharacters = newInPlayCharacters
            )
        }

        repository.updateInPlayCharacters(newInPlayCharacters)
    }

    fun insertInPlayCharacter(insertingInPlayCharacter: Character) {
        repository.insertInPlayCharacter(insertingInPlayCharacter)
        _state.update {
            it.copy(
                inPlayCharacters = repository.inPlayCharacters.value + insertingInPlayCharacter,
                currentCharacterTypeCountList = repository.currentCharacterTypeCountList.value
            )
        }
    }

    fun deleteInPlayCharacter(deletingInPlayCharacter: Character) {
        repository.deleteInPlayCharacter(deletingInPlayCharacter)
        _state.update {
            it.copy(
                inPlayCharacters = repository.inPlayCharacters.value - deletingInPlayCharacter,
                currentCharacterTypeCountList = repository.currentCharacterTypeCountList.value
            )
        }
    }

    fun updateMaximumCharacterTypeCountList(newMaximumCharacterTypeCountList : MutableList<Int>) {
        _state.update{
            it.copy(
                maximumCharacterTypeCountList = newMaximumCharacterTypeCountList
            )
        }

        repository.updateMaximumCharacterTypeCountList(newMaximumCharacterTypeCountList)
    }

    fun updateCurrentCharacterTypeCountList(newCurrentCharacterTypeCountList : MutableList<Int>) {
        _state.update{
            it.copy(
                currentCharacterTypeCountList = newCurrentCharacterTypeCountList
            )
        }

        repository.updateCurrentCharacterTypeCountList(newCurrentCharacterTypeCountList)
    }


}