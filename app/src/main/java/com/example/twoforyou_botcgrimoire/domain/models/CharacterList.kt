package com.example.twoforyou_botcgrimoire.domain.models

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CharacterList(
    val inPlayCharacters: @RawValue List<Character> = emptyList(),
) : Parcelable

class AssetParamType : NavType<CharacterList>(isNullableAllowed = false) {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun get(bundle: Bundle, key: String): CharacterList? {
        return bundle.getParcelable(key, CharacterList::class.java)
    }

    override fun parseValue(value: String): CharacterList {
        return Gson().fromJson(value, CharacterList::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: CharacterList) {
        bundle.putParcelable(key, value)
    }
}