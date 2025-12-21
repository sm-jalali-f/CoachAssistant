package com.freez.coachassistant.navigation

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object StudentList : Destination("student_list")
    object TransactionList : Destination("finance")
    object ClubList : Destination("clubs")
    object StudentDetails : Destination("student_details/{studentId}") {
        fun createRoute(studentId: Long) = "student_details/$studentId"
    }
    object SessionDetails : Destination("session_details/{sessionId}") {
        fun createRoute(sessionId: Long) = "session_details/$sessionId"
    }
    object Settings : Destination("settings")
}