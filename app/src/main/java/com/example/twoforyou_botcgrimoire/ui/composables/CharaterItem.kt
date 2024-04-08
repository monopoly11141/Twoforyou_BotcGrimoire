package com.example.twoforyou_botcgrimoire.ui.composables

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.twoforyou_botcgrimoire.ui.screen.setup.SetupViewModel
import com.example.twoforyou_botcgrimoire.domain.models.Character
import com.example.twoforyou_botcgrimoire.ui.utils.StringUtils

@Composable
fun CharacterItem(
    character: Character,
    viewModel: SetupViewModel = hiltViewModel(),
    onCharacterClicked: (Character) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    val TAG = ""
    Log.d(TAG, "CharacterItem: ${character.name} ${character.isFormatChangingRole}")
    Column(
        modifier = Modifier
            .fillMaxWidth(1f / 5f)
            .clickable {
                onCharacterClicked(character)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = character.imageUrl,
                contentDescription = "Image of ${character.name}",
                modifier = if (state.inPlayCharacters.contains(character)) Modifier else Modifier
                    .alpha(
                        0.3f
                    )
                    .weight(0.8f),
            )
            Column(
                modifier = Modifier
                    .weight(0.2f)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Info,
                    contentDescription = "캐릭터 효과",
                    modifier = Modifier
                        .clickable {
                            showDialog = true
                        }
                )
                if (character.isFormatChangingRole) {

                    Icon(
                        imageVector = Icons.Rounded.Warning,
                        contentDescription = "세팅 바꾸는 캐릭터",
                        tint = Color.Yellow
                    )
                }
            }


        }

        Text(
            text = StringUtils.filterKoreanCharacters(character.name),
            fontSize = 10.sp,
            maxLines = 2,
            textAlign = TextAlign.Center,
            modifier = if (state.inPlayCharacters.contains(character)) Modifier else Modifier.alpha(
                0.3f
            )
        )
    }
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = character.ability,
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}