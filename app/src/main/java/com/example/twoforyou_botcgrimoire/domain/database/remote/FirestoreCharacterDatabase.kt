package com.example.twoforyou_botcgrimoire.domain.database.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.example.twoforyou_botcgrimoire.domain.enums.Character_Type
import com.example.twoforyou_botcgrimoire.domain.enums.Edition
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.twoforyou_botcgrimoire.domain.models.Character

class FirestoreCharacterDatabase {
    val characterDb = Firebase.firestore

    private val _characterList = MutableStateFlow<List<Character>>(emptyList())
    val characterList = _characterList.asStateFlow()
    lateinit var character: Character

    fun getAllCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            characterDb
                .collection("characters")
                .document("소란발생_TROUBLE_BREWING")
                .get()
                .addOnSuccessListener { result ->
                    val dataFromResult = result.data!!
                    character = Character(
                        name = dataFromResult["name"].toString(),
                        type = Character_Type.valueOf(
                            dataFromResult["type"].toString()
                        ),
                        ability = dataFromResult["ability"].toString(),
                        fromWhichEdition = Edition.valueOf(
                            dataFromResult["fromWhichEdition"].toString()
                        ),
                        numberOfReminderTokens = dataFromResult["numberOfReminderTokens"] as Int,
                        reminderTokens = dataFromResult["reminderTokens"] as List<String>,
                        imageUrl = dataFromResult["imageUrl"].toString(),
                        firstNightOrder = dataFromResult["firstNightOrder"] as Int,
                        otherNightOrder = dataFromResult["otherNightOrder"] as Int,
                        isFormatChangingRole = dataFromResult["isFormatChangingRole"] as Boolean,
                    )

                    _characterList.value += character

                }
            Log.d(TAG, characterList.value.toString())
        }
    }
}
