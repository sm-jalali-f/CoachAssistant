package com.freez.presenter

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import com.freez.presenter.navigation.CoachAssistantNavigationGraph
import com.freez.presenter.navigation.rememberCoachAssistantNavController
import com.freez.presenter.theme.CoachAssistantTheme


@Composable
fun CoachAssistantApp() {
    CoachAssistantTheme {
        val assistantNavController = rememberCoachAssistantNavController()
        SharedTransitionLayout {

            CoachAssistantNavigationGraph(coachNavController = assistantNavController)
        }

    }

}
