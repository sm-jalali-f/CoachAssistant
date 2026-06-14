package com.freez.coachassistant.util

object PersianNumberFormatter {

    private const val PERSIAN_DIGITS = "۰۱۲۳۴۵۶۷۸۹"
    private const val ENGLISH_DIGITS = "0123456789"

    fun toPersianDigits(text: String): String = buildString(text.length) {
        text.forEach { char ->
            val index = ENGLISH_DIGITS.indexOf(char)
            append(if (index >= 0) PERSIAN_DIGITS[index] else char)
        }
    }

    fun toEnglishDigits(text: String): String = buildString(text.length) {
        text.forEach { char ->
            val index = PERSIAN_DIGITS.indexOf(char)
            append(if (index >= 0) ENGLISH_DIGITS[index] else char)
        }
    }

    fun parseDigitsToLong(text: String): Long? {
        val digits = toEnglishDigits(text).filter { it.isDigit() }
        if (digits.isEmpty()) return null
        return digits.toLongOrNull()
    }

    fun formatCurrency(value: Long): String {
        val grouped = value.toString()
            .reversed()
            .chunked(3)
            .joinToString(",")
            .reversed()
        return toPersianDigits(grouped)
    }

    fun formatCurrencyDisplay(value: Long?): String =
        value?.let { formatCurrency(it) }.orEmpty()

    fun formatTime(hour: Int, minute: Int): String =
        toPersianDigits("%02d:%02d".format(hour, minute))
}
