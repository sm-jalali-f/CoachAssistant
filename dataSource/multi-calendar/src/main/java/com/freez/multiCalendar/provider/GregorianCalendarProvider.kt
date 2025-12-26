package com.freez.multiCalendar.provider

import com.freez.multiCalendar.model.CalendarDate
import java.time.LocalDate

class GregorianCalendarProvider : CalendarProvider {
    override fun getCurrentDate(): CalendarDate {
        val date = LocalDate.now()
        return CalendarDate(
            date.year,
            date.monthValue,
            date.dayOfMonth,
            date.dayOfWeek.name,
            date,
        )
    }

    override fun getDates(
        pointDate: CalendarDate,
        previousDaysCount: Int,
        nextDaysCount: Int
    ): List<CalendarDate> {
        val dates = mutableListOf<CalendarDate>()
        var indexDate = LocalDate.of(pointDate.year, pointDate.month, pointDate.day)
        val endDate = indexDate.plusDays(nextDaysCount.toLong())
        indexDate = indexDate.minusDays(previousDaysCount.toLong())


        while (indexDate.isBefore(endDate) || indexDate.isEqual(endDate)) {
            dates.add(
                CalendarDate(
                    indexDate.year,
                    indexDate.monthValue,
                    indexDate.dayOfMonth,
                    indexDate.dayOfWeek.name,
                    indexDate,
                ),
            )
            indexDate = indexDate.plusDays(1)
        }
        return dates
    }
}
