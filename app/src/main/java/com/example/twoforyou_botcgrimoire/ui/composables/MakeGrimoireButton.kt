package com.example.twoforyou_botcgrimoire.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.twoforyou_botcgrimoire.ui.screen.setup.SetupViewModel

@Composable
fun MakeGrimoireButton(
    onClickButton: () -> Unit,
    viewModel: SetupViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    var isMakeGameButtonEnabled by remember { mutableStateOf(false) }

    isMakeGameButtonEnabled =
        state.inPlayCharacters.size == state.playerCount

    Button(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClickButton,
        enabled = isMakeGameButtonEnabled
    ) {
        Text(
            "마법서 만들기",
            textAlign = TextAlign.Center
        )
    }
}