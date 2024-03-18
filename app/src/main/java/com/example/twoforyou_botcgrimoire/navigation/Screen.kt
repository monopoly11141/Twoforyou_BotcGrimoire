package com.example.twoforyou_botcgrimoire.navigation

sealed class Screen(val route: String) {
    object GrimoireScreen: Screen(route = "grimoire_screen")
    object ResultScreen: Screen(route = "result_screen")
    object SetupScreen: Screen(route = "setup_screen")
}