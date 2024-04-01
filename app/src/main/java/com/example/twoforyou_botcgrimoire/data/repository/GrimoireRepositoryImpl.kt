package com.example.twoforyou_botcgrimoire.data.repository

import com.example.twoforyou_botcgrimoire.domain.database.remote.FirebaseCharacterDatabase
import com.example.twoforyou_botcgrimoire.domain.enums.Edition
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

    private val _inPlayReminderTokens = MutableStateFlow<List<String>>(emptyList())
    override val inPlayReminderTokens: StateFlow<List<String>>
        get() = _inPlayReminderTokens.asStateFlow()

    override fun updateInPlayCharacters(updatedInPlayCharacters: List<Character>) {
        _inPlayCharacters.value = updatedInPlayCharacters
    }

    override fun insertInPlayerCharacter(insertedInPlayCharacter: Character)  {
        _inPlayCharacters.value += insertedInPlayCharacter

    }

    override fun deleteInPlayCharacter(deletedInPlayCharacter: Character) {
        _inPlayCharacters.value -= deletedInPlayCharacter
    }

    override fun updatePossibleCharactersByEdition(edition: Edition) {
        firebaseCharacterDatabase.getAllCharactersFromEdition(edition)
    }

    override fun updateInPlayReminderTokens(updatedInPlayReminderTokens: List<String>) {
        _inPlayReminderTokens.value = updatedInPlayReminderTokens
    }

    override fun insertInPlayReminderToken(insertedInPlayReminderToken: String) {
        _inPlayReminderTokens.value += insertedInPlayReminderToken
    }

    override fun deleteInPlayReminderToken(deletedInPlayReminderToken: String) {
        _inPlayReminderTokens.value -= deletedInPlayReminderToken
    }


}