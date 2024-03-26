package com.example.twoforyou_botcgrimoire.data.repository

import com.example.twoforyou_botcgrimoire.domain.database.remote.FirebaseCharacterDatabase
import com.example.twoforyou_botcgrimoire.domain.repository.GrimoireRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.twoforyou_botcgrimoire.domain.models.Character
import javax.inject.Inject

class GrimoireRepositoryImpl @Inject constructor(
    private val firebaseCharacterDatabase: FirebaseCharacterDatabase
) : GrimoireRepository {

    private val _inPlayCharacters = MutableStateFlow<List<Character>>(emptyList())
    override val inPlayCharacters: StateFlow<List<Character>>
        get() = _inPlayCharacters.asStateFlow()

    override val possibleCharacters: StateFlow<List<Character>> by lazy {
        firebaseCharacterDatabase.possibleCharacterList
    }

    override fun updateInPlayCharacters(updatedInPlayCharacters: List<Character>) {
        _inPlayCharacters.value = updatedInPlayCharacters
    }

}