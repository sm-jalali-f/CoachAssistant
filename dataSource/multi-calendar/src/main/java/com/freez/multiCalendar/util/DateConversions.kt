package com.freez.multiCalendar.util

import com.freez.multiCalendar.model.CalendarDate
import com.freez.multiCalendar.provider.CalendarProvider
import java.util.Calendar

data class GregorianDateComponents(val year: Int, val month: Int, val day: Int)

fun CalendarProvider.toGregorianDateComponents(calendarDate: CalendarDate): GregorianDateComponents {
    val resolved = getDates(calendarDate, previousDaysCount = 0, nextDaysCount = 0).first()
    val iso = requireNotNull(resolved.isoDate) { "Could not resolve ISO date for $calendarDate" }
    return GregorianDateComponents(iso.year, iso.monthValue, iso.dayOfMonth)
}

fun GregorianDateComponents.startOfDayMillis(): Long = Calendar.getInstance().run {
    set(year, month - 1, day, 0, 0, 0)
    set(Calendar.MILLISECOND, 0)
    timeInMillis
}

fun GregorianDateComponents.endOfDayMillis(): Long = Calendar.getInstance().run {
    set(year, month - 1, day, 23, 59, 59)
    set(Calendar.MILLISECOND, 999)
    timeInMillis
}
