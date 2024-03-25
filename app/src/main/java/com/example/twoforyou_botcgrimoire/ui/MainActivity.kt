package com.example.twoforyou_botcgrimoire.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.twoforyou_botcgrimoire.domain.constant.TROUBLE_BREWING_CHARACTERS
import com.example.twoforyou_botcgrimoire.domain.database.remote.FirebaseCharacterDatabase
import com.example.twoforyou_botcgrimoire.domain.enums.Edition
import com.example.twoforyou_botcgrimoire.navigation.Navigation
import com.example.twoforyou_botcgrimoire.ui.theme.Twoforyou_BotcGrimoireTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TROUBLE_BREWING_CHARACTERS.addTroubleBrewingCharactersToDatabase()

        FirebaseCharacterDatabase().getAllCharactersFromEdition(Edition.소란발생_TROUBLE_BREWING)
        setContent {
            Twoforyou_BotcGrimoireTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Navigation(navController)
                }

            }
        }
    }

}

