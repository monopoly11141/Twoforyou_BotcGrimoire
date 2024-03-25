package com.example.twoforyou_botcgrimoire.ui.utils

class StringUtils {

    companion object {
        fun filterKoreanCharacters(string : String) : String {
            return string.filter {
                "^[ㄱ-ㅎ가-힣]*$".toRegex().containsMatchIn(it.toString())
            }
        }
    }
}