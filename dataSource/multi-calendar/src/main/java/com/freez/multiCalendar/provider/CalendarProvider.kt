package com.freez.multiCalendar.provider

import com.freez.multiCalendar.model.CalendarDate

interface CalendarProvider {
    fun getCurrentDate(): CalendarDate

    fun getDates(
        pointDate: CalendarDate,
        previousDaysCount: Int,
        nextDaysCount: Int
    ): List<CalendarDate>
}
