package com.freez.multiCalendar.provider

import com.freez.multiCalendar.model.AppDate
import com.freez.multiCalendar.model.WeekDay
import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Test

class JalaliCalendarProviderTest {

    private val provider = JalaliCalendarProvider()

    @Test
    fun test_getCurrentDate_returns_correct_date() {
        val expected = provider.getCurrentDate()
        val actual = AppDate(1404, 8, 25, WeekDay.Sunday.getPersianName(), LocalDate.now())
        assertEquals(expected, actual)
    }

    @Test
    fun test_getDates_returns_correct_dates() {
        val from = AppDate(1404, 6, 25, WeekDay.Tuesday.getPersianName(), LocalDate.of(2025, 9, 16))
        val to = AppDate(1404, 7, 2, WeekDay.Wednesday.getPersianName(), LocalDate.of(2025, 9, 24))
        val dates = provider.getDates(from, to)
        assertEquals(9, dates.size)
        assertEquals(from, dates.first())
        assertEquals(
            AppDate(1404, 6, 31, WeekDay.Monday.getPersianName(), LocalDate.of(2025, 9, 22)),
            dates[6],
        )
        assertEquals(to, dates.last())
    }
}
