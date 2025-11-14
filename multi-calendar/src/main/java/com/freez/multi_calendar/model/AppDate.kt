package com.freez.multi_calendar.model

import java.time.LocalDate

data class AppDate(
    val year: Int,
    val month: Int,
    val day: Int,
    val dayName: String,
    val isoDate: LocalDate
)