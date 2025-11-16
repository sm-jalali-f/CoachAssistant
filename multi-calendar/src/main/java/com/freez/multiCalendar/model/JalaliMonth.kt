package com.freez.multiCalendar.model

enum class JalaliMonth(val value: Int) {
    Farvardin(1),
    Ordibehesht(2),
    Khordad(3),
    Tir(4),
    Mordad(5),
    Shahrivar(6),
    Mehr(7),
    Aban(8),
    Azar(9),
    Dey(10),
    Bahman(11),
    Esfand(12),
    ;

    fun getPersianName(): String {
        return when (this) {
            Farvardin -> "فروردین"
            Ordibehesht -> "اردیبهشت"
            Khordad -> "خرداد"
            Tir -> "تیر"
            Mordad -> "مرداد"
            Shahrivar -> "شهریور"
            Mehr -> "مهر"
            Aban -> "آبان"
            Azar -> "آذر"
            Dey -> "دی"
            Bahman -> "بهمن"
            Esfand -> "اسفند"
        }
    }

    companion object {
        fun fromInt(value: Int): JalaliMonth {
            return JalaliMonth.entries.first { it.value == value }
        }
    }
}
