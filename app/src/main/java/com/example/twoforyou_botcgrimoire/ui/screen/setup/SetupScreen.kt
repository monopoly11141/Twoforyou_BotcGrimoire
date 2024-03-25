package com.example.twoforyou_botcgrimoire.ui.screen.setup

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.twoforyou_botcgrimoire.domain.models.Character
import com.example.twoforyou_botcgrimoire.domain.models.CharacterList
import com.example.twoforyou_botcgrimoire.navigation.Screen
import com.example.twoforyou_botcgrimoire.ui.composables.DisplayCurrentEditionCharacters
import com.example.twoforyou_botcgrimoire.ui.composables.EditionRadioGroup
import com.example.twoforyou_botcgrimoire.ui.composables.MakeGrimoireButton
import com.example.twoforyou_botcgrimoire.ui.composables.PlayerCountSlider
import com.google.gson.Gson

@Composable
fun SetupScreen(
    navController: NavController,
    viewModel: SetupViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        EditionRadioGroup()

        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Black
        )

        PlayerCountSlider()

        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Black
        )

        MakeGrimoireButton(
            onClickButton = {
                val inPlayCharacters = CharacterList(state.inPlayCharacters)
                val json = Uri.encode(Gson().toJson(inPlayCharacters))
                navController.navigate("${Screen.GrimoireScreen.route}/$json")
            }
        )

        DisplayCurrentEditionCharacters()
    }

}

