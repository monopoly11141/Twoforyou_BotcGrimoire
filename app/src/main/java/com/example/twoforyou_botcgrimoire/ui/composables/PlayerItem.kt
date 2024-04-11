package com.example.twoforyou_botcgrimoire.ui.composables

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.twoforyou_botcgrimoire.domain.models.Player
import com.example.twoforyou_botcgrimoire.ui.screen.grimoire.GrimoireViewModel
import com.example.twoforyou_botcgrimoire.ui.utils.StringUtils
import me.saket.cascade.CascadeDropdownMenu

@Composable
fun PlayerItem(
    player: Player,
    modifier: Modifier = Modifier,
    viewModel: GrimoireViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val density = LocalDensity.current

    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    val thisPlayer by remember { mutableStateOf(player) }

    var isDropDownMenuExpanded by remember { mutableStateOf(false) }

    var backgroundColor by remember { mutableStateOf(Color.Gray) }
    var alpha by remember { mutableFloatStateOf(1f) }

    var showDialog by remember {mutableStateOf(false)}

    Box(
        modifier = modifier
            .offset(
                (offsetX / density.density).dp,
                (offsetY / density.density).dp,
            )
            .size(100.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
            .clickable {
                isDropDownMenuExpanded = true
            }
    ) {
        AsyncImage(
            model = thisPlayer.character?.imageUrl,
            contentDescription = "Image of ${player.character?.name ?: "NO_CHARACTER"}",
            modifier = Modifier
                .alpha(alpha)
        )

        CascadeDropdownMenu(
            modifier = Modifier
                .wrapContentSize(),
            expanded = isDropDownMenuExpanded,
            onDismissRequest = { isDropDownMenuExpanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "캐릭터 배정/변경"
                    )
                },
                children = {
                    for (possibleCharacter in state.possibleCharacters) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = StringUtils.filterKoreanCharacters(possibleCharacter.name)
                                )
                            },
                            onClick = {
                                viewModel.changePlayerCharacter(thisPlayer, possibleCharacter)
                                //player.character = possibleCharacter
                                isDropDownMenuExpanded = false
                            }
                        )
                    }

                }
            )

            DropdownMenuItem(
                text = {
                    Text(
                        "삶/죽음"
                    )
                },
                children = {
                    DropdownMenuItem(
                        text = {
                            Text(
                                "삶 표시하기"
                            )
                        },
                        onClick = {
                            alpha = 1f
                            backgroundColor = Color.Gray
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(
                                "죽음 표시하기"
                            )
                        },
                        onClick = {
                            alpha = 0.25f
                            backgroundColor = Color.Black
                        }
                    )

                }
            )

            DropdownMenuItem(
                text = {
                    Text(
                        text = "캐릭터 설명 보기"
                    )
                },

                children = {
                    showDialog = true
                    isDropDownMenuExpanded = false
                }
            )

        }
        if(showDialog) {
            CharacterEffectDialog(
                character = thisPlayer.character!!
            ) {
                showDialog = false
            }
        }

    }

}