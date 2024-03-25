package com.example.twoforyou_botcgrimoire.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.twoforyou_botcgrimoire.ui.screen.setup.SetupViewModel
import kotlin.math.roundToInt

@Composable
fun PlayerCountSlider(
    viewModel: SetupViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    val minimumPlayerCount = 5
    val maximumPlayerCount = 15
    var playerCount by remember { mutableFloatStateOf(minimumPlayerCount.toFloat()) }

    viewModel.updatePlayerCount(playerCount.roundToInt())

    Column() {
        Slider(
            value = playerCount,
            onValueChange = {
                playerCount = it
            },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = maximumPlayerCount - minimumPlayerCount - 1,
            valueRange = minimumPlayerCount.toFloat()..maximumPlayerCount.toFloat()
        )
        Text(text = "플레이어 수 : ${state.playerCount}")
    }


}