package com.freez.multiCalendar.util

import com.freez.multiCalendar.model.JalaliMonth
import com.freez.multiCalendar.model.WeekDay
import java.time.LocalDate
import kotlin.collections.minusAssign
import kotlin.collections.plusAssign
import kotlin.compareTo
import kotlin.div
import kotlin.rem
import kotlin.text.toInt
import kotlin.times

internal class SolarCalendar {

    var date: Int = 0
    private var monthValue: Int = 0
    var month: JalaliMonth = JalaliMonth.Farvardin
    var year: Int = 0
    var weekDay: WeekDay = WeekDay.Monday

    constructor() {
        val gregorianDate = LocalDate.now()
        calcSolarCalendar(gregorianDate)
    }

    constructor(gregorianDate: LocalDate) {
        calcSolarCalendar(gregorianDate)
    }
    // kotlin
    fun toGregorianDate(): LocalDate {
        val jy = this.year
        val jm = this.month.value
        val jd = this.date

        val jMonthDays = intArrayOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29)
        val gMonthDays = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

        var jyAdj = jy + 1595
        var days = -355668L + 365L * jyAdj + (jyAdj / 33L) * 8L + ((jyAdj % 33L + 3L) / 4L)
        for (i in 0 until (jm - 1)) {
            days += jMonthDays[i]
        }
        days += (jd - 1)

        var gDayNo = days
        var gy = (400L * gDayNo) / 146097L
        gDayNo %= 146097L

        var leap = true
        if (gDayNo >= 36525L) {
            gDayNo -= 1L
            gy += (100L * gDayNo) / 36524L
            gDayNo %= 36524L
            if (gDayNo >= 365L) {
                gDayNo += 1L
            } else {
                leap = false
            }
        }

        gy += (4L * gDayNo) / 1461L
        gDayNo %= 1461L

        if (gDayNo >= 366L) {
            leap = false
            gDayNo -= 366L
            gy += gDayNo / 365L
            gDayNo %= 365L
        }

        gy += 1L

        var gm = 0
        var gd: Long = 0
        var i = 0
        while (i < 12) {
            var daysInMonth = gMonthDays[i].toLong()
            if (i == 1 && leap) daysInMonth += 1L
            if (gDayNo < daysInMonth) {
                gm = i + 1
                gd = gDayNo + 1L
                break
            }
            gDayNo -= daysInMonth
            i++
        }

        return LocalDate.of(gy.toInt(), gm, gd.toInt())
    }


    private fun calcSolarCalendar(gregorianDate: LocalDate) {
        val ld: Int

        val gregorianYear = gregorianDate.year
        val gregorianMonth = gregorianDate.month.value
        val gregorianDay = gregorianDate.dayOfMonth
        weekDay = WeekDay.fromInt(gregorianDate.dayOfWeek.value)

        val buf1 = IntArray(12)
        val buf2 = IntArray(12)

        buf1[0] = 0
        buf1[1] = 31
        buf1[2] = 59
        buf1[3] = 90
        buf1[4] = 120
        buf1[5] = 151
        buf1[6] = 181
        buf1[7] = 212
        buf1[8] = 243
        buf1[9] = 273
        buf1[10] = 304
        buf1[11] = 334

        buf2[0] = 0
        buf2[1] = 31
        buf2[2] = 60
        buf2[3] = 91
        buf2[4] = 121
        buf2[5] = 152
        buf2[6] = 182
        buf2[7] = 213
        buf2[8] = 244
        buf2[9] = 274
        buf2[10] = 305
        buf2[11] = 335

        if ((gregorianYear % 4) != 0) {
            date = buf1[gregorianMonth - 1] + gregorianDay

            if (date > 79) {
                date = date - 79
                if (date <= 186) {
                    when (date % 31) {
                        0 -> {
                            monthValue = date / 31
                            date = 31
                        }
                        else -> {
                            monthValue = (date / 31) + 1
                            date = (date % 31)
                        }
                    }
                    year = gregorianYear - 621
                } else {
                    date = date - 186

                    when (date % 30) {
                        0 -> {
                            monthValue = (date / 30) + 6
                            date = 30
                        }
                        else -> {
                            monthValue = (date / 30) + 7
                            date = (date % 30)
                        }
                    }
                    year = gregorianYear - 621
                }
            } else {
                ld =
                    if ((gregorianYear > 1996) && (gregorianYear % 4) == 1) {
                        11
                    } else {
                        10
                    }
                date = date + ld

                when (date % 30) {
                    0 -> {
                        monthValue = (date / 30) + 9
                        date = 30
                    }
                    else -> {
                        monthValue = (date / 30) + 10
                        date = (date % 30)
                    }
                }
                year = gregorianYear - 622
            }
        } else {
            date = buf2[gregorianMonth - 1] + gregorianDay

            ld =
                if (gregorianYear >= 1996) {
                    79
                } else {
                    80
                }
            if (date > ld) {
                date = date - ld

                if (date <= 186) {
                    when (date % 31) {
                        0 -> {
                            monthValue = (date / 31)
                            date = 31
                        }
                        else -> {
                            monthValue = (date / 31) + 1
                            date = (date % 31)
                        }
                    }
                    year = gregorianYear - 621
                } else {
                    date = date - 186

                    when (date % 30) {
                        0 -> {
                            monthValue = (date / 30) + 6
                            date = 30
                        }
                        else -> {
                            monthValue = (date / 30) + 7
                            date = (date % 30)
                        }
                    }
                    year = gregorianYear - 621
                }
            } else {
                date = date + 10

                when (date % 30) {
                    0 -> {
                        monthValue = (date / 30) + 9
                        date = 30
                    }
                    else -> {
                        monthValue = (date / 30) + 10
                        date = (date % 30)
                    }
                }
                year = gregorianYear - 622
            }
        }
        month = JalaliMonth.fromInt(monthValue)
    }
}
