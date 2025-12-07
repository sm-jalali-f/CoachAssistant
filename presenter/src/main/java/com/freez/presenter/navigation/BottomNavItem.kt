package com.freez.presenter.navigation

import com.freez.presenter.R

sealed class BottomNavItem(val route: String, val title: String, val icon: Int) {
    object Home : BottomNavItem(Destination.Home.route, "Home", R.drawable.ic_dashboard)
    object Students :
        BottomNavItem(Destination.StudentList.route, "Students", R.drawable.ic_tennis_player)

    object Finance :
        BottomNavItem(Destination.TransactionList.route, "Finance", R.drawable.ic_bag_money)

    object Clubs : BottomNavItem(Destination.ClubList.route, "Courts", R.drawable.ic_tennis_court)
}