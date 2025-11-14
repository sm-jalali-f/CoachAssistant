package com.freez.multi_calendar.provider

import com.freez.multi_calendar.model.AppDate

interface CalendarProvider {
    fun getCurrentDate(): AppDate

    /**
     * Returns a list of `AppDate` objects for every calendar day in the inclusive range from `from` to `to`.
     *
     * Dates must be provided in the `yyyy-MM-dd` (year-month-day) format, e.g. `2025-11-08`.
     *
     * @param from Start date (inclusive) in `yyyy-MM-dd` format.
     * @param to End date (inclusive) in `yyyy-MM-dd` format.
     * @return A `List<AppDate>` containing one entry per day from `from` to `to` in ascending order.
     * @throws IllegalArgumentException If `from` or `to` is not a valid date in `yyyy-MM-dd` format.
     *
     * Note: If `from` is after `to`, implementations should return an empty list.
     */
    fun getDates(from: AppDate, to: AppDate): List<AppDate>
}