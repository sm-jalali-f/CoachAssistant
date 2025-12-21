package com.freez.coachassistant.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberCoachAssistantNavController(navController: NavHostController = rememberNavController()): CoachAssistantNavController =
    remember(navController) {
        CoachAssistantNavController(navController)
    }

@Stable
class CoachAssistantNavController(val navController: NavHostController) {

}