package com.example.twoforyou_botcgrimoire.domain.enums

enum class Character_Type {
    마을주민_TOWNSFOLK,
    외부인_OUTSIDER,
    하수인_MINION,
    악마_DEMON;

    fun getIntValue(): Int {
        return entries.indexOf(this)
    }
}