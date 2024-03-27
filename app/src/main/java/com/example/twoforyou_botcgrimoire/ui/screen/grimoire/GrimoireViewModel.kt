package com.example.twoforyou_botcgrimoire.ui.screen.grimoire

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twoforyou_botcgrimoire.domain.enums.Edition
import com.example.twoforyou_botcgrimoire.domain.models.Character
import com.example.twoforyou_botcgrimoire.domain.repository.GrimoireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GrimoireViewModel @Inject constructor(
    private val repository: GrimoireRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(GrimoireUiState())
    val state = combine(
        repository.inPlayCharacters,
        repository.possibleCharacters,
        _state
    ) { array ->
        GrimoireUiState(
            inPlayCharacters = array[0] as List<Character>,
            possibleCharacters = array[1] as List<Character>,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    fun updateInPlayCharacters(updatedInPlayCharacters: List<Character>) {
        repository.updateInPlayCharacters(updatedInPlayCharacters)

        _state.update {
            it.copy (
                inPlayCharacters = updatedInPlayCharacters
            )
        }
    }

    fun updatePossibleCharactersByEdition(edition : Edition) {
        repository.updatePossibleCharactersByEdition(edition)

        _state.update {
            it.copy (
                possibleCharacters = repository.possibleCharacters.value
            )
        }
    }

}