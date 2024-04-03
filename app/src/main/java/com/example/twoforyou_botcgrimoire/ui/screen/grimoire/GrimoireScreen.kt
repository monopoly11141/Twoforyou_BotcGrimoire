package com.example.twoforyou_botcgrimoire.ui.screen.grimoire

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.twoforyou_botcgrimoire.domain.models.CharacterList
import com.example.twoforyou_botcgrimoire.ui.composables.GrimoirePlayerLayout
import com.example.twoforyou_botcgrimoire.ui.composables.Topbar

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "마법서") },
                actions = {
                    IconButton(onClick = {
                        showAllReminderTokens = true
                        Log.d("TAG", "Topbar: showallreminder value changed")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "MoreVert",
                            tint = Color.Black
                        )

                        DropdownMenu(
                            expanded = showAllReminderTokens,
                            onDismissRequest = { showAllReminderTokens = false },
                        ) {
                            for (character in state.inPlayCharacters) {
                                character.reminderTokens.forEach { reminderToken ->
                                    DropdownMenuItem(
                                        text = { Text(reminderToken) },
                                        onClick = {
                                            viewModel.insertInPlayReminderToken(
                                                reminderToken
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            GrimoirePlayerLayout()
        }
    }



}