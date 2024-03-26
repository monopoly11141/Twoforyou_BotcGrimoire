package com.example.twoforyou_botcgrimoire.ui.screen.grimoire

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.size.Size
import com.example.twoforyou_botcgrimoire.R
import com.example.twoforyou_botcgrimoire.domain.models.CharacterList
import com.example.twoforyou_botcgrimoire.domain.models.Player
import com.example.twoforyou_botcgrimoire.ui.composables.CharacterItem
import com.example.twoforyou_botcgrimoire.ui.composables.PlayerItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GrimoireScreen(
    characterList: CharacterList,
    navController: NavController,
    viewModel: GrimoireViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    viewModel.updateInPlayCharacters(characterList.inPlayCharacters)

    var zoom by remember { mutableStateOf(1f) }
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
            }
        }
    }


}