package com.freez.coachassistant.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.freez.coachassistant.ui.clubs.ClubListScreen
import com.freez.coachassistant.ui.finance.TransactionListScreen
import com.freez.coachassistant.ui.home.HomeScreen
import com.freez.coachassistant.ui.students.StudentListScreen


@Composable
fun CoachAssistantNavigationGraph(
    coachNavController: CoachAssistantNavController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = coachNavController.navController,
        startDestination = startDestination.route,
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        composable(Destination.Home.route) {
            HomeScreen()
        }
        composable(Destination.StudentList.route) {
            StudentListScreen()
        }
        composable(Destination.TransactionList.route) {
            TransactionListScreen()
        }
        composable(Destination.ClubList.route) {
            ClubListScreen()
        }
    }
}