package com.freez.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost


@Composable
fun CoachAssistantNavigationGraph(
    coachNavController: CoachAssistantNavController,
    startDestination: Destination
) {

    NavHost(
        navController = coachNavController.navController,
        startDestination = startDestination.route
    ) {

    }
}