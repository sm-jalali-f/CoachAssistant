package com.freez.repository

import com.freez.domain.model.AppDate
import com.freez.domain.repositories.CalendarRepository
import com.freez.multiCalendar.model.CalendarDate
import com.freez.multiCalendar.provider.CalendarProvider
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val calendarProvider: CalendarProvider
) : CalendarRepository {
    override fun getToday(): AppDate {
        return calendarProvider.getCurrentDate().toAppDate()
    }

    override fun getAroundDays(
        selectDate: AppDate,
        previousDaysCount: Int,
        nextDaysCount: Int
    ): List<AppDate> {
        return calendarProvider.getDates(
            pointDate = selectDate.toCalendarDate(),
            previousDaysCount = previousDaysCount,
            nextDaysCount = nextDaysCount
        ).map { it.toAppDate() }

    }

}

private fun AppDate.toCalendarDate(): CalendarDate {
    return CalendarDate(this.year, this.month, this.day, this.dayOfWeek, this.monthName, null)

}

private fun CalendarDate.toAppDate(): AppDate {
    return AppDate(
        year = this.year,
        month = this.month,
        day = this.day,
        monthName = this.monthName,
        dayOfWeek = this.dayName,
    )
}
