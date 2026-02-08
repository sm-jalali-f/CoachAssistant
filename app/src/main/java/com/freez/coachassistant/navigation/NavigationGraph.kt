package com.freez.coachassistant.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.freez.coachassistant.ui.MainScreen
import com.freez.coachassistant.ui.newEvent.NewClassEventScreen
import com.freez.coachassistant.ui.newEvent.NewEventViewModel


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
        composable(Destination.Main.route) {
            MainScreen(rootController = coachNavController)
        }
        /* composable(Destination.StudentList.route) {
             StudentListScreen()
         }
         composable(Destination.TransactionList.route) {
             TransactionListScreen()
         }
         composable(Destination.ClubList.route) {
             ClubListScreen()
         }*/


        composable(Destination.NewEventScreen.route) { backStackEntry ->
            val viewModel: NewEventViewModel =
                hiltViewModel(backStackEntry)
            NewClassEventScreen({}, {}, viewModel)
        }
    }
}