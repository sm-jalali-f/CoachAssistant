package com.freez.multiCalendar.provider

import com.freez.multiCalendar.model.AppDate
import com.freez.multiCalendar.util.SolarCalendar
import java.time.LocalDate

class JalaliCalendarProvider : CalendarProvider {
    override fun getCurrentDate(): AppDate {
        val date = LocalDate.now()
        return getDate(date)
    }

    override fun getDates(
        from: AppDate,
        to: AppDate,
    ): List<AppDate> {
        val dates = mutableListOf<AppDate>()
        var indexDate = from.isoDate

        while (indexDate.isBefore(to.isoDate) || indexDate.isEqual(to.isoDate)) {
            dates.add(getDate(indexDate))
            indexDate = indexDate.plusDays(1)
        }
        return dates
    }

    private fun getDate(localDate: LocalDate): AppDate {
        val solar = SolarCalendar(localDate)

        return AppDate(
            solar.year,
            solar.month.value,
            solar.date,
            solar.weekDay.getPersianName(),
            localDate,
        )
    }
}
