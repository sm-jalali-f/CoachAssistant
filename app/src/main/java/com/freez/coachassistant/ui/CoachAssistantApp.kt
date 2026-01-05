package com.freez.coachassistant.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.freez.coachassistant.navigation.BottomNavigationBar
import com.freez.coachassistant.navigation.CoachAssistantNavigationGraph
import com.freez.coachassistant.navigation.Destination
import com.freez.coachassistant.navigation.rememberCoachAssistantNavController
import com.freez.coachassistant.ui.theme.CoachAssistantTheme
import com.freez.domain.model.AppLanguage


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CoachAssistantApp(appLanguage: AppLanguage) {
    val layoutDirection = when (appLanguage) {
        AppLanguage.Persian -> LayoutDirection.Rtl
        AppLanguage.English -> LayoutDirection.Ltr
    }
    CompositionLocalProvider(
        LocalLayoutDirection provides layoutDirection
    ) {
        CoachAssistantTheme(appLanguage) {
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
}
