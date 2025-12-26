package com.freez.domain.repositories

import com.freez.domain.model.AppDate

interface CalendarRepository {

    fun getToday(): AppDate
    fun getAroundDays(
        selectDate: AppDate = getToday(),
        previousDaysCount: Int = 60,
        nextDaysCount: Int = 60
    ): List<AppDate>
}