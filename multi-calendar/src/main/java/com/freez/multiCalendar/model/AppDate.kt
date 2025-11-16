package com.freez.multiCalendar.model

import java.time.LocalDate

data class AppDate(
    val year: Int,
    val month: Int,
    val day: Int,
    val dayName: String,
    val isoDate: LocalDate,
)
