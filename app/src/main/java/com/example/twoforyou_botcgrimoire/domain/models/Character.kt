package com.example.twoforyou_botcgrimoire.domain.models

import com.example.twoforyou_botcgrimoire.domain.enums.Character_Type
import com.example.twoforyou_botcgrimoire.domain.enums.Edition

data class Character(
    val name : String,
    val type : Character_Type,
    val ability : String,
    val fromWhichEdition : Edition,
    val numberOfReminderTokens : Int,
    var reminderTokens : List<String> = emptyList(),
    val imageUrl: String,
    val firstNightOrder : Int? = null,
    val otherNightOrder : Int? = null,
    val isFormatChangingRole : Boolean,
)
