package com.freez.domain.model

data class AppDateTime(val appDate: AppDate, val hour: Hour, val minute: Minute)

@JvmInline
value class Hour private constructor(val value: Int) {
    companion object {
        fun of(value: Int): Hour =
            requireNotNull(ofOrNull(value))

        fun ofOrNull(value: Int): Hour? =
            if (value in 0..23) Hour(value) else null
    }
}

@JvmInline
value class Minute private constructor(val value: Int) {
    companion object {
        fun of(value: Int): Minute =
            requireNotNull(ofOrNull(value))

        fun ofOrNull(value: Int): Minute? =
            if (value in 0..59) Minute(value) else null
    }
}