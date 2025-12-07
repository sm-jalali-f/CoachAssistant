package com.freez.presenter

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.freez.presenter.navigation.BottomNavigationBar
import com.freez.presenter.navigation.CoachAssistantNavigationGraph
import com.freez.presenter.navigation.Destination
import com.freez.presenter.navigation.rememberCoachAssistantNavController
import com.freez.presenter.theme.CoachAssistantTheme


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CoachAssistantApp() {
    CoachAssistantTheme {
        val assistantNavController = rememberCoachAssistantNavController()

        SharedTransitionLayout {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController = assistantNavController.navController) }
            ) { paddingValues ->
                CoachAssistantNavigationGraph(
                    modifier = Modifier.padding(paddingValues),
                    coachNavController = assistantNavController,
                    startDestination = Destination.Home
                )
            }
        }
    }
}
