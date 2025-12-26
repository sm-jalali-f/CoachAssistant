package com.freez.multiCalendar.provider

import com.freez.multiCalendar.model.CalendarDate
import com.freez.multiCalendar.model.JalaliMonth
import com.freez.multiCalendar.util.SolarCalendar
import java.time.LocalDate

class JalaliCalendarProvider : CalendarProvider {
    override fun getCurrentDate(): CalendarDate {
        val date = LocalDate.now()
        return getDate(date)
    }

    override fun getDates(
        pointDate: CalendarDate,
        previousDaysCount: Int,
        nextDaysCount: Int
    ): List<CalendarDate> {
        val dates = mutableListOf<CalendarDate>()
        var indexDate = pointDate.isoDate ?: run {
            val computed = computeIsoDate(pointDate)
            pointDate.isoDate = computed
            computed
        }
        val toDate = indexDate.plusDays(nextDaysCount.toLong())
        indexDate = indexDate.minusDays(previousDaysCount.toLong())
        while (indexDate.isBefore(toDate) || indexDate.isEqual(toDate)) {
            dates.add(getDate(indexDate))
            indexDate = indexDate.plusDays(1)
        }
        return dates
    }

    private fun computeIsoDate(pointDate: CalendarDate): LocalDate {
        return SolarCalendar().apply {
            this.year = pointDate.year
            this.month = JalaliMonth.fromInt(pointDate.month)
            this.date = pointDate.day
        }.toGregorianDate()
    }

    private fun getDate(localDate: LocalDate): CalendarDate {
        val solar = SolarCalendar(localDate)

        return CalendarDate(
            solar.year,
            solar.month.value,
            solar.date,
            solar.weekDay.getPersianName(),
            solar.month.getPersianName(),
            localDate,
        )
    }
}
