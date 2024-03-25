package com.example.twoforyou_botcgrimoire.ui.screen.grimoire

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.twoforyou_botcgrimoire.domain.models.CharacterList

@Composable
fun GrimoireScreen(
    characterList: CharacterList,
    navController: NavController,
    //viewModel: GrimoireViewModel = hiltViewModel()
) {
    Text(characterList.toString())
}