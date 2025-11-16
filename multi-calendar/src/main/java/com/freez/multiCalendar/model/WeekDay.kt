package com.freez.multiCalendar.model

enum class WeekDay(val value: Int) {

    Monday(1),
    Tuesday(2),
    Wednesday(3),
    Thursday(4),
    Friday(5),
    Saturday(6),
    Sunday(7),
    ;

    fun getPersianName(): String {
        return when (this) {
            Saturday -> "شنبه"
            Sunday -> "یکشنبه"
            Monday -> "دوشنبه"
            Tuesday -> "سه‌شنبه"
            Wednesday -> "چهارشنبه"
            Thursday -> "پنج‌شنبه"
            Friday -> "جمعه"
        }
    }

    fun getEnglishName(): String {
        return when (this) {
            Saturday -> "Saturday"
            Sunday -> "Sunday"
            Monday -> "Monday"
            Tuesday -> "Tuesday"
            Wednesday -> "Wednesday"
            Thursday -> "Thursday"
            Friday -> "Friday"
        }
    }

    companion object {
        fun fromInt(value: Int): WeekDay {
            return WeekDay.entries.first { it.value == value }
        }
    }
}
