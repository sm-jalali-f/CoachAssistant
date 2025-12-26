package com.freez.repository.setting

interface CalendarSettingsDataSource {
    fun getCalendarType(): CalendarType
}

enum class CalendarType {
    JALALI,
    GREGORIAN
}