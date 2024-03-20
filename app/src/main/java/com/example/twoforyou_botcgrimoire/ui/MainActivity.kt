package com.example.twoforyou_botcgrimoire.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.twoforyou_botcgrimoire.domain.constant.TROUBLE_BREWING_CHARACTERS
import com.example.twoforyou_botcgrimoire.domain.database.remote.FirestoreCharacterDatabase
import com.example.twoforyou_botcgrimoire.domain.enums.Character_Type
import com.example.twoforyou_botcgrimoire.domain.enums.Edition
import com.example.twoforyou_botcgrimoire.domain.models.Character
import com.example.twoforyou_botcgrimoire.navigation.Navigation
import com.example.twoforyou_botcgrimoire.ui.theme.Twoforyou_BotcGrimoireTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Twoforyou_BotcGrimoireTheme {
                //FirestoreCharacterDatabase().getAllCharacters()
                TROUBLE_BREWING_CHARACTERS.addTroubleBrewingCharactersToDatabase()
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

