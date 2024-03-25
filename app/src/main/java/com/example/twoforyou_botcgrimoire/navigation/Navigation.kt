package com.example.twoforyou_botcgrimoire.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.twoforyou_botcgrimoire.domain.models.AssetParamType
import com.example.twoforyou_botcgrimoire.domain.models.CharacterList
import com.example.twoforyou_botcgrimoire.ui.screen.grimoire.GrimoireScreen
import com.example.twoforyou_botcgrimoire.ui.screen.result.ResultScreen
import com.example.twoforyou_botcgrimoire.ui.screen.setup.SetupScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SetupScreen.route
    ) {
//        composable(route = Screen.GrimoireScreen.route) {
//            GrimoireScreen()
//        }

        composable(
            "${Screen.GrimoireScreen.route}/{inPlayCharacters}",
            arguments = listOf(
                navArgument("inPlayCharacters") {
                    type = AssetParamType()
                }
            )
        ) {
            val characterList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.arguments?.getParcelable("inPlayCharacters", CharacterList::class.java)
            } else {
                it.arguments?.getParcelable("inPlayCharacters")
            }
            GrimoireScreen(
                characterList!!,
                navController = navController
            )
        }

        composable(route = Screen.ResultScreen.route) {
            ResultScreen()
        }

        composable(route = Screen.SetupScreen.route) {
            SetupScreen(
                navController = navController
            )
        }
    }


}