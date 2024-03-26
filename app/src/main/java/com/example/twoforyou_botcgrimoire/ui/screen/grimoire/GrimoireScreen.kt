package com.example.twoforyou_botcgrimoire.ui.screen.grimoire

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.twoforyou_botcgrimoire.domain.models.CharacterList
import com.example.twoforyou_botcgrimoire.domain.models.Player
import com.example.twoforyou_botcgrimoire.ui.composables.PlayerItem

@Composable
fun GrimoireScreen(
    characterList: CharacterList,
    navController: NavController,
    viewModel: GrimoireViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    viewModel.updateInPlayCharacters(characterList.inPlayCharacters)
    Column(

    ) {
        for(inPlayCharacter in state.inPlayCharacters) {
            PlayerItem(
                player = Player(
                    character = inPlayCharacter
                )
            )
        }
    }

}