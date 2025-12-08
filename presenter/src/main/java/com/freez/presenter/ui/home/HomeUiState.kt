package com.freez.presenter.ui.home

import com.freez.domain.model.AppDate

data class HomeUiState(val greeting: GreetingState)

data class GreetingState(val name: String?, val greeting: String?)

data class ClassSessionState(val studentName: String, val date: AppDate, val startTime: Int)