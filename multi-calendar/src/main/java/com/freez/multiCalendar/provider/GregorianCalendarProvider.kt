package com.freez.multiCalendar.provider

import com.freez.multiCalendar.model.AppDate
import java.time.LocalDate

class GregorianCalendarProvider : CalendarProvider {
    override fun getCurrentDate(): AppDate {
        val date = LocalDate.now()
        return AppDate(
            date.year,
            date.monthValue,
            date.dayOfMonth,
            date.dayOfWeek.name,
            date,
        )
    }

    override fun getDates(
        from: AppDate,
        to: AppDate,
    ): List<AppDate> {
        val dates = mutableListOf<AppDate>()
        var indexDate = LocalDate.of(from.year, from.month, from.day)
        val endDate = LocalDate.of(to.year, to.month, to.day)

        while (indexDate.isBefore(endDate) || indexDate.isEqual(endDate)) {
            dates.add(
                AppDate(
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
