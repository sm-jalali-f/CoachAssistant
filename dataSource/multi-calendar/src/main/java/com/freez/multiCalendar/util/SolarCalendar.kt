package com.freez.multiCalendar.util

import com.freez.multiCalendar.model.JalaliMonth
import com.freez.multiCalendar.model.WeekDay
import java.time.LocalDate

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

    fun toGregorianDate(): LocalDate {

        val jy2 = this.year - 979
        val jm2 = this.month.value - 1
        val jd2 = this.date - 1

        var jDayNo =
            365 * jy2 +
                    (jy2 / 33) * 8 +
                    ((jy2 % 33) + 3) / 4

        for (i in 0 until jm2) {
            jDayNo += if (i < 6) 31 else 30
        }

        jDayNo += jd2

        var gDayNo = jDayNo + 79

        var gy = 1600 + 400 * (gDayNo / 146097)
        gDayNo %= 146097

        var leap = true
        if (gDayNo >= 36525) {
            gDayNo--
            gy += 100 * (gDayNo / 36524)
            gDayNo %= 36524

            if (gDayNo >= 365) gDayNo++
            else leap = false
        }

        gy += 4 * (gDayNo / 1461)
        gDayNo %= 1461

        if (gDayNo >= 366) {
            leap = false
            gDayNo--
            gy += gDayNo / 365
            gDayNo %= 365
        }

        val gMonthDays = intArrayOf(
            31,
            if (leap) 29 else 28,
            31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        )

        var gm = 0
        while (gm < 12 && gDayNo >= gMonthDays[gm]) {
            gDayNo -= gMonthDays[gm]
            gm++
        }

        val gd = gDayNo + 1

        return LocalDate.of(gy, gm + 1, gd)
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
