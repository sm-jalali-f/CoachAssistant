package com.freez.coachassistant.ui

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.freez.coachassistant.navigation.BottomNavigationBar
import com.freez.coachassistant.navigation.CoachAssistantNavController
import com.freez.coachassistant.navigation.Destination
import com.freez.coachassistant.navigation.MainScreenNavigationGraph

@Composable
fun MainScreen(rootController: CoachAssistantNavController) {
    val bottomNavController = rememberNavController()
    SharedTransitionLayout {
        Scaffold(
            bottomBar = { BottomNavigationBar(navController = bottomNavController) }
        ) { paddingValues ->
            MainScreenNavigationGraph(
                modifier = Modifier.padding(paddingValues),
                rootController = rootController,
                navController = bottomNavController,
                startDestination = Destination.Home
            )
        }
    }

}