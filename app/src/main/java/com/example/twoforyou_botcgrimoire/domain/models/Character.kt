package com.example.twoforyou_botcgrimoire.domain.models

import com.example.twoforyou_botcgrimoire.domain.enums.Character_Type
import com.example.twoforyou_botcgrimoire.domain.enums.Edition

data class Character(
    val name : String? = null,
    val type : Character_Type? = null,
    val firstNightOrder : Int? = null,
    val otherNightOrder : Int? = null,
    val imageUrl: String? = null,
    val fromWhichEdition : Edition? = null,
    val numberOfReminderTokens : Int = 0
)
