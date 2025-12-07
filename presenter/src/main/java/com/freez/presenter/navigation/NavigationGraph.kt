package com.freez.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.freez.presenter.ui.clubs.ClubListScreen
import com.freez.presenter.ui.finance.TransactionListScreen
import com.freez.presenter.ui.home.HomeScreen
import com.freez.presenter.ui.students.StudentListScreen


@Composable
fun CoachAssistantNavigationGraph(
    coachNavController: CoachAssistantNavController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = coachNavController.navController,
        startDestination = startDestination.route,
        modifier = modifier
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