package com.freez.coachassistant.ui.home

import com.freez.domain.model.AppDate
import com.freez.domain.model.Money

data class HomeUiState(
    val loading: Boolean = true,
    val greeting: GreetingState,
    val monthlyReport: MonthlyReport,
    val datesList: List<AppDate>,
    val today: AppDate,
    val selectedDate: AppDate = today,
    val classSessionList: List<ClassSessionUi>?,
    val loadingClassSessions: Boolean = true,
)

data class GreetingState(val name: String = "", val greeting: String = "Good Morning")

data class MonthlyReport(val sessionCount: Int, val income: Money)

data class ClassSessionUi(
    val studentName: String,
    val date: AppDate,
    val startTimeHour: Int,
    val endTimeHour: Int
)