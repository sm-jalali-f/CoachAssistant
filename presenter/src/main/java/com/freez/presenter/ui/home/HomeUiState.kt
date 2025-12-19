package com.freez.presenter.ui.home

import com.freez.domain.model.AppDate
import com.freez.domain.model.Money

data class HomeUiState(val greeting: GreetingState, val monthlyReport: MonthlyReport)

data class GreetingState(val name: String = "", val greeting: String = "Good Morning")

data class MonthlyReport(val sessionCount: Int, val income: Money)

data class ClassSessionState(val studentName: String, val date: AppDate, val startTime: Int)