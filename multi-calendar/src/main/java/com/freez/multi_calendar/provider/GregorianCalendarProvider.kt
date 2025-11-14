package com.freez.multi_calendar.provider

import com.freez.multi_calendar.model.AppDate
import java.time.LocalDate

class GregorianCalendarProvider : CalendarProvider {
    override fun getCurrentDate(): AppDate {
        val date = LocalDate.now()
        return AppDate(
            date.year,
            date.monthValue,
            date.dayOfMonth,
            date.dayOfWeek.name,
            date
        )

    }

    override fun getDates(
        from: AppDate,
        to: AppDate
    ): List<AppDate> {
        val dates = mutableListOf<AppDate>()
        var currentDate = from.nativeDate as LocalDate
        val endDate = to.nativeDate as LocalDate

        while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
            dates.add(
                AppDate(
                    currentDate.year,
                    currentDate.monthValue,
                    currentDate.dayOfMonth,
                    currentDate.dayOfWeek.name,
                    currentDate
                )
            )
            currentDate = currentDate.plusDays(1)
        }
        return dates
    }
}