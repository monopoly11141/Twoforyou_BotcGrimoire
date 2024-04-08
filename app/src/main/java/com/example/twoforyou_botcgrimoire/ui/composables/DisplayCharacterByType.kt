package com.example.twoforyou_botcgrimoire.ui.composables

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.twoforyou_botcgrimoire.domain.enums.Character_Type
import com.example.twoforyou_botcgrimoire.domain.models.Character
import com.example.twoforyou_botcgrimoire.ui.screen.setup.SetupViewModel
import com.example.twoforyou_botcgrimoire.ui.utils.StringUtils

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DisplayCharacterByType(
    index: Int,
    characterList: List<Character>,
    viewModel: SetupViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var progress by remember { mutableFloatStateOf(0f) }

    val maxCharacterCount = state.maximumCharacterTypeCountList[index]

    progress =
        if (maxCharacterCount == 0) 1f else (state.currentCharacterTypeCountList[index].toFloat() / maxCharacterCount)

    LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp),
        color = if (state.currentCharacterTypeCountList[index] == maxCharacterCount) Color.Blue else Color.Red,
    )
    Text(
        text = "${
            StringUtils.filterKoreanCharacters(Character_Type.entries[index].toString())
        } ${state.currentCharacterTypeCountList[index]} / $maxCharacterCount",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth(),
        color = if (state.currentCharacterTypeCountList[index] == maxCharacterCount) Color.Blue else Color.Red
    )

    FlowRow(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        for (character in characterList) {
            Log.d("TAG", "DisplayCharacterByType: ${character.name} : ${character.isFormatChangingRole}")
            CharacterItem(
                character = character
            ) {
                if (state.inPlayCharacters.contains(it)) {
                    viewModel.deleteInPlayCharacter(it)
                } else {
                    viewModel.insertInPlayCharacter(it)
                }

            }
        }
    }
    HorizontalDivider(
        thickness = 1.dp,
        color = Color.Black
    )
}