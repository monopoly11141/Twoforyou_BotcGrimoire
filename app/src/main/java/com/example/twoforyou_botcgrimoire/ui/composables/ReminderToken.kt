package com.example.twoforyou_botcgrimoire.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.twoforyou_botcgrimoire.ui.screen.grimoire.GrimoireViewModel
import com.example.twoforyou_botcgrimoire.ui.utils.StringUtils

@Composable
fun ReminderToken(
    reminderToken: String,
    modifier: Modifier = Modifier,
    viewModel: GrimoireViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val density = LocalDensity.current

    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    var showDropDownMenu by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .offset(
                (offsetX / density.density).dp,
                (offsetY / density.density).dp,
            )
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.Gray)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
            .clickable {
                showDropDownMenu = true
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = StringUtils.filterKoreanCharacters(reminderToken),
            modifier = modifier
                .fillMaxSize(),
            textAlign = TextAlign.Center
        )

        DropdownMenu(
            expanded = showDropDownMenu,
            onDismissRequest = { showDropDownMenu = false },
            modifier = Modifier
                .wrapContentSize(),
        ) {
            DropdownMenuItem(
                text = { "없애기" },
                onClick = { viewModel.deleteInPlayReminderToken(reminderToken) }
            )
        }

    }

}