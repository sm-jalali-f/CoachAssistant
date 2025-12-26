package com.freez.multiCalendar.model

import java.time.LocalDate

data class CalendarDate(
    val year: Int,
    val month: Int,
    val day: Int,
    val dayName: String,
    var isoDate: LocalDate?,
)
