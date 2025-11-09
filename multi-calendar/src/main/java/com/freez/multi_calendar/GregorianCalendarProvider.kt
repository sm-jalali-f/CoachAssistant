package com.freez.multi_calendar

import android.annotation.SuppressLint
import android.os.Build
import com.freez.multi_calendar.model.AppDate
import com.freez.multi_calendar.provider.CalendarProvider
import java.time.LocalDate
import java.time.ZoneId

class GregorianCalendarProvider : CalendarProvider {
    override fun getCurrentDate(): AppDate {
        @SuppressLint("NewApi")
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
        from: String,
        to: String
    ): List<AppDate> {
        TODO("Not yet implemented")
    }
}