package com.example.twoforyou_botcgrimoire.domain.database.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.example.twoforyou_botcgrimoire.domain.enums.Character_Type
import com.example.twoforyou_botcgrimoire.domain.enums.Edition
import com.example.twoforyou_botcgrimoire.domain.models.Character
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class FirebaseCharacterDatabase {
    private lateinit var database: DatabaseReference

    private val _possibleCharacterList = MutableStateFlow<List<Character>>(emptyList())
    val possibleCharacterList = _possibleCharacterList.asStateFlow()

    fun getAllCharactersFromEdition(edition: Edition) {
        CoroutineScope(Dispatchers.IO).launch {
            lateinit var character: Character
            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    _possibleCharacterList.value = emptyList()
                    for (characterTypeCharacters in dataSnapshot.child(edition.toString()).children) {
                        for (thisCharacter in characterTypeCharacters.children) {
                            character = Character(
                                thisCharacter.child("name").value.toString(),
                                Character_Type.valueOf(thisCharacter.child("type").value.toString()),
                                thisCharacter.child("ability").value.toString(),
                                Edition.valueOf(thisCharacter.child("fromWhichEdition").value.toString()),
                                thisCharacter.child("numberOfReminderTokens").value.toString()
                                    .toInt(),
                                thisCharacter.child("reminderTokens").value.toString()
                                    .replace("[", "").replace("]", "").split(","),
                                thisCharacter.child("imageUrl").value.toString(),
                                thisCharacter.child("firstNightOrder").value.toString()
                                    .toIntOrNull(),
                                thisCharacter.child("otherNightOrder").value.toString()
                                    .toIntOrNull(),
                                thisCharacter.child("isFormatChangingRole").value.toString()
                                    .toBoolean(),
                            )
                            _possibleCharacterList.value += character
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                }
            }

            database = Firebase.database.reference
            database.addValueEventListener(postListener)
        }
    }

}