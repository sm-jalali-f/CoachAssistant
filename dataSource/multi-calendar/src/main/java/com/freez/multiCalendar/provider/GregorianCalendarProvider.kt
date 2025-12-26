package com.freez.multiCalendar.provider

import com.freez.multiCalendar.model.CalendarDate
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class GregorianCalendarProvider : CalendarProvider {
    override fun getCurrentDate(): CalendarDate {
        val date = LocalDate.now()
        return CalendarDate(
            date.year,
            date.monthValue,
            date.dayOfMonth,
            date.dayOfWeek.name,
            date.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
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
                    year = indexDate.year,
                    month = indexDate.monthValue,
                    day=indexDate.dayOfMonth,
                    dayName = indexDate.dayOfWeek.name,
                    monthName = indexDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
                    isoDate = indexDate,
                ),
            )
            indexDate = indexDate.plusDays(1)
        }
        return dates
    }
}
