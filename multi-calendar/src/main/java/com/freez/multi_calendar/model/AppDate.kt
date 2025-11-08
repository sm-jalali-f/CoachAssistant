package com.freez.multi_calendar.model

import java.time.LocalDate

data class AppDate(
    val year: Int,
    val month: Int,
    val day: Int,
    val displayName: String,
    val isoDate: LocalDate
)