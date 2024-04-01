package com.example.twoforyou_botcgrimoire.ui.composables

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.twoforyou_botcgrimoire.domain.models.Player
import com.example.twoforyou_botcgrimoire.ui.screen.grimoire.GrimoireViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GrimoirePlayerLayout(
    viewModel: GrimoireViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var zoom by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                //zoom in/out and move around
                detectTransformGestures { gestureCentroid, gesturePan, gestureZoom, _ ->
                    val oldScale = zoom
                    val newScale = (zoom * gestureZoom).coerceIn(0.25f..3f)
                    offset =
                        (offset + gestureCentroid / oldScale) - (gestureCentroid / newScale + gesturePan / oldScale)
                    zoom = newScale
                }
            }) {
        Box(
            Modifier

                .graphicsLayer {
                    translationX = -offset.x * zoom
                    translationY = -offset.y * zoom
                    scaleX = zoom
                    scaleY = zoom
                }

        ) {
            FlowRow(
                    modifier = Modifier
                        .fillMaxSize()
                    ) {
                for (inPlayCharacter in state.inPlayCharacters) {
                    PlayerItem(
                        player = Player(
                            character = inPlayCharacter
                        ),
                        modifier = Modifier
                    )
                }
                for(inPlayReminderToken in state.inPlayReminderTokens) {
                    ReminderToken(
                        inPlayReminderToken
                    )
                }
            }
            
            //여기에 디스플레이
        }
    }
}