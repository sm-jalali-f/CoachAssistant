package com.freez.coachassistant.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.freez.coachassistant.ui.home.HomeScreen

@Composable
fun MainScreenNavigationGraph(
    modifier: Modifier,
    rootController: CoachAssistantNavController,
    navController: NavHostController,
    startDestination: Destination
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
    ) {
        composable (Destination.Home.route) {
            HomeScreen(navController = rootController)
        }
        /*Composable(Destination.Home.route) {
            HomeScreen(navController = rootController)
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
        composable(Destination.NewEventScreen.route) {
            NewClassEventScreen({}, {})
        }*/
    }
}
