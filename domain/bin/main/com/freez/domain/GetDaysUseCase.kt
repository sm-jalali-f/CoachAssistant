package com.freez.domain

import com.freez.domain.model.AppDate
import com.freez.domain.repositories.CalendarRepository

class GetDaysUseCase constructor(
    private val calendarRepository: CalendarRepository
) {

    fun getDays(): List<AppDate> {
        return calendarRepository.getAroundDays()
    }

    fun today(): AppDate {
        return calendarRepository.getToday()
    }
}