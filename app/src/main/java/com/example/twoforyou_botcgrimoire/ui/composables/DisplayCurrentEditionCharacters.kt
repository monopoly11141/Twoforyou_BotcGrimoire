package com.example.twoforyou_botcgrimoire.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.twoforyou_botcgrimoire.domain.enums.Character_Type
import com.example.twoforyou_botcgrimoire.ui.screen.setup.SetupViewModel

@Composable
fun DisplayCurrentEditionCharacters(
    viewModel: SetupViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val townsFolk = state.possibleCharacters.filter {
        it.type == Character_Type.마을주민_TOWNSFOLK
    }

    val outsider = state.possibleCharacters.filter {
        it.type == Character_Type.외부인_OUTSIDER
    }

    val minion = state.possibleCharacters.filter {
        it.type == Character_Type.하수인_MINION
    }

    val demon = state.possibleCharacters.filter {
        it.type == Character_Type.악마_DEMON
    }

    val characterTypeList = listOf(townsFolk, outsider, minion, demon)

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        for (i in characterTypeList.indices) {
            DisplayCharacterByType(
                index = i,
                characterList = characterTypeList[i],
            )
        }
    }
}