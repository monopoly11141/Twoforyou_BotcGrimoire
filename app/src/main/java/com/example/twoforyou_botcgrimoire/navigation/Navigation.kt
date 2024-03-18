package com.example.twoforyou_botcgrimoire.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
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
        composable(route = Screen.GrimoireScreen.route) {
            GrimoireScreen()
        }

        composable(route = Screen.ResultScreen.route) {
            ResultScreen()
        }

        composable(route = Screen.SetupScreen.route) {
            SetupScreen()
        }
    }


}