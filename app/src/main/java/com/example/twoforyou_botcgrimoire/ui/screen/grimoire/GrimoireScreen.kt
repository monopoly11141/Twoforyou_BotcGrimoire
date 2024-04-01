package com.example.twoforyou_botcgrimoire.ui.screen.grimoire

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.twoforyou_botcgrimoire.domain.models.CharacterList
import com.example.twoforyou_botcgrimoire.ui.composables.GrimoirePlayerLayout
import com.example.twoforyou_botcgrimoire.ui.composables.ReminderToken

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GrimoireScreen(
    characterList: CharacterList,
    navController: NavController,
    viewModel: GrimoireViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    viewModel.updateInPlayCharacters(characterList.inPlayCharacters)
    viewModel.updatePossibleCharactersByEdition(characterList.edition)

    var showAllReminderTokens by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        //TODO : 1) Make Surface Layer,
        // 2) create : button.
        // 3) When clicked, dropdownmenu{add reminder token{allReminderTokens}, }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                checked = showAllReminderTokens,
                onCheckedChange = {
                    showAllReminderTokens = it
                }
            )
        }


        if (showAllReminderTokens) {
            FlowRow() {
                for (inPlayCharacter in state.inPlayCharacters) {
                    inPlayCharacter.reminderTokens.forEach {
                        ReminderToken(reminderToken = it)
                    }
                }
            }
        }


        GrimoirePlayerLayout()
    }


}