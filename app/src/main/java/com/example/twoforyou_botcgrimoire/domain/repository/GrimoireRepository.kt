package com.example.twoforyou_botcgrimoire.domain.repository

import com.example.twoforyou_botcgrimoire.domain.enums.Edition
import com.example.twoforyou_botcgrimoire.domain.models.Character
import kotlinx.coroutines.flow.StateFlow

interface GrimoireRepository {

    val inPlayCharacters: StateFlow<List<Character>>
    val possibleCharacters: StateFlow<List<Character>>

    val inPlayReminderTokens : StateFlow<List<String>>

    fun updateInPlayCharacters(updatedInPlayCharacters: List<Character>)
    fun insertInPlayerCharacter(insertedInPlayCharacter : Character)
    fun deleteInPlayCharacter(deletedInPlayCharacter : Character)

    fun updatePossibleCharactersByEdition(edition : Edition)

    fun updateInPlayReminderTokens(updatedInPlayReminderTokens : List<String>)
    fun insertInPlayReminderToken(insertedInPlayReminderToken : String)
    fun deleteInPlayReminderToken(deletedInPlayReminderToken : String)
}