package com.example.twoforyou_botcgrimoire.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.twoforyou_botcgrimoire.domain.enums.Edition
import com.example.twoforyou_botcgrimoire.ui.screen.setup.SetupViewModel


@Composable
fun EditionRadioGroup(
    viewModel: SetupViewModel = hiltViewModel()
) {
    val editionRadioButtonOptions = Edition.entries.toTypedArray()
    var selectedEditionRadioButtonOption by remember { mutableStateOf(editionRadioButtonOptions[0]) }

    editionRadioButtonOptions.forEach { editionName ->
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = (editionName == selectedEditionRadioButtonOption),
                onClick = {
                    selectedEditionRadioButtonOption = editionName
                    viewModel.updateEdition(editionName)
                }
            )
            Text(
                text = editionName.toString(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 2.dp)
            )
        }
    }
}
