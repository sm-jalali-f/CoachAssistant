package com.freez.coachassistant.ui.home

import com.freez.domain.model.AppDate

sealed interface HomeIntent {
    // system
    data object LoadInitial : HomeIntent

    // user
    data class SelectDate(val date: AppDate) : HomeIntent
}