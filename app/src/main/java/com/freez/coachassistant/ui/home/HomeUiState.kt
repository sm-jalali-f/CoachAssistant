package com.freez.coachassistant.ui.home

import com.freez.domain.model.AppDate
import com.freez.domain.model.Money

data class HomeUiState(
    val loading: Boolean = true,
    val greeting: GreetingState? = null,
    val monthlyReport: MonthlyReport? = null,
    val datesList: List<AppDate>? = null,
    val today: AppDate? = null,
    val selectedDate: AppDate? = today,
    val classSessionList: List<ClassSessionUi>? = null,
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