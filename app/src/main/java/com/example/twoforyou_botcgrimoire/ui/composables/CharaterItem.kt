package com.example.twoforyou_botcgrimoire.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
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

    Column(
        modifier = Modifier
            .fillMaxWidth(1f/7f)
            .clickable { onCharacterClicked(character) },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = character.imageUrl,
            contentDescription = "Image of ${character.name}",
            modifier = if (state.inPlayCharacters.contains(character)) Modifier else Modifier.alpha(
                0.3f
            )
        )

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

}